package ucsd.cs110.splurge.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import ucsd.cs110.splurge.connectivity.tasks.RestaurantInfoAsyncTask;
import ucsd.cs110.splurge.connectivity.tasks.RestaurantInfoRequestListener;
import ucsd.cs110.splurge.connectivity.tasks.RestaurantListAsyncTask;
import ucsd.cs110.splurge.connectivity.tasks.RestaurantListRequestListener;
import android.content.Context;
import android.util.Log;

/**
 * Class containing business logic for the Splurge application.
 */
public class RestaurantModel implements RestaurantListRequestListener,
		RestaurantInfoRequestListener {
	/**
	 * Container for Restaurants that the Model has requested from the Server,
	 * but are not necessarily currently viewed. This a cache for the event that
	 * the viewer changes their mind and wants to look back at a previously
	 * viewed restaurant.
	 */
	private Collection<Restaurant> mLoadedRestaurants;
	/**
	 * Currently selected Restaurant. This is the restaurant to which future
	 * function calls should refer, when it is applicable.
	 */
	private Restaurant mCurrentRestaurant;
	/**
	 * Container of all restaurant names which should be available to the user.
	 * The names in this list should be viewer-friendly.
	 */
	private Collection<RestaurantListing> mAvailableRestaurantNames;
	/**
	 * Handler for all calls to external data sources. This will be utilized to
	 * gather restaurant information, request reservations, and other such
	 * calls.
	 */
	private JSONConnectionHandler mConnectionHandler;
	/**
	 * Listener of restaurant list requests to which results should be
	 * forwarded.
	 */
	private RestaurantListRequestListener mListForwardListener;

	/**
	 * Creates a new model with empty lists and no selected restaurant.
	 */
	public RestaurantModel() {
		mLoadedRestaurants = new ArrayList<Restaurant>();
		mAvailableRestaurantNames = new ArrayList<RestaurantListing>();
		mConnectionHandler = new JSONConnectionHandler();
	}

	/**
	 * Selects a restaurant by name. This may be replaced by a future
	 * setRestaurantById(int) method.
	 * 
	 * @param name
	 *            The restaurant's name as it appears to the system.
	 */
	public void setRestaurantByName(String name,
			OnRestaurantReadyListener listener, Context awful) {
		// Get the restaurant ID from the internal list
		int id = -1;
		for (RestaurantListing l : mAvailableRestaurantNames) {
			if (l.getRestaurantName().equals(name)) {
				id = l.getRestaurantId();
				break;
			}
		}

		if (id != -1) {
			setRestaurantById(id, listener, awful);
		} else {
			Log.e("Splurge", "Could not deduce id from restaurant name: "
					+ name);
		}
	}

	/**
	 * Sets the current restaurant for the Model based on the given
	 * identification number. This will use a cached restaurant if one exists,
	 * and will poll the server otherwise.
	 * 
	 * @param id
	 *            The identification number of the restaurant to select.
	 */
	public void setRestaurantById(int id, OnRestaurantReadyListener listener,
			Context awful) {
		// Check the currently loaded restaurants for the given Id
		for (Restaurant r : mLoadedRestaurants) {
			if (r.getId() == id) {
				mCurrentRestaurant = r;
				Log.i("Splurge", "Model restaurant set to " + r.getName());
				listener.onRestaurantReady();
				return;
			}
		}

		// Because the restaurant was not found, we must query the server
		mCurrentRestaurant = getRestaurantByIdAsync(id, listener, awful);
	}

	/**
	 * Attempts to poll the server for the given restaurant. It will wait a set
	 * period of time for a result, and will return null if the result is not
	 * available within that time.
	 * 
	 * @param id
	 *            The identification number of the restaurant to request.
	 * @return The restaurant, or <code>null</code> if it takes too long.
	 */
	private Restaurant getRestaurantByIdAsync(int id,
			OnRestaurantReadyListener listener, Context awful) {
		RestaurantInfoAsyncTask riat = new RestaurantInfoAsyncTask(this);
		riat.execute(mConnectionHandler, id, awful);
		try {
			Restaurant result = riat.get(500, TimeUnit.MILLISECONDS);
			listener.onRestaurantReady();
			return result;
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
		}
		return null;
	}

	/**
	 * Request a reservation at the given start time.
	 * 
	 * @param partySize
	 *            The number of party members for which to request a
	 *            reservation.
	 * @param startTime
	 *            The time at which the reservation is to begin.
	 * @return The identification number for the acquired reservation, or -1 if
	 *         no reservation was available.
	 */
	public int requestReservation(int partySize, String partyName,
			Calendar startTime) {
		if (mCurrentRestaurant.isTimeUnavailable(startTime))
			return -1;
		return mConnectionHandler.requestReservation(
				mCurrentRestaurant.getId(), partySize, partyName, startTime);
	}

	/**
	 * Updates the available reservation times.
	 */
	public void updateAvailableTimes(OnRestaurantReadyListener listener,
			Context awful) {
		setRestaurantById(mCurrentRestaurant.getId(), listener, awful);
	}

	/**
	 * Requests the current restaurant object from the Model.
	 * 
	 * @return The currently selected Restaurant.
	 */
	public Restaurant getRestaurant() {
		return mCurrentRestaurant;
	}

	/**
	 * Retrieves a menu by its title. If no such menu exist, this function will
	 * return <code>null</code>.
	 * 
	 * @param label
	 *            The title of the menu.
	 * @return The menu, or <code>null</code> if no such menu exists.
	 */
	public FoodMenu getFoodMenuByLabel(String label) {
		return getRestaurant().getMenuByLabel(label);
	}

	/**
	 * Retrieves a menu by its index. There is no guarantee that a particular
	 * index will correspond to the same meal across multiple restaurants,
	 * although a given index will always return the same menu for the same
	 * restaurant.
	 * 
	 * @param index
	 *            The index number for the menu to retrieve.
	 * @return A menu.
	 */
	public FoodMenu getFoodMenuByIndex(int index) {
		return getRestaurant().getFoodMenuByIndex(index);
	}

	/**
	 * Retrieves an array of the labels for the menus available for this
	 * restaurant. This is not guaranteed to be in any particular order.
	 * 
	 * @return An array of labels for the menus available for this restaurant.
	 */
	public String[] getMenuLabels() {
		return getRestaurant().getMenuLabels();
	}

	/**
	 * Retrieves the container of all restaurant names which should be available
	 * to the user. These names should be user-friendly and immediately
	 * printable. They are paired with the corresponding restaurant
	 * Identification numbers to reduce confusion.
	 * 
	 * This accepts a listener which shall be notified about the result should
	 * the result not be available locally. In a move inspired by the decorator
	 * pattern, the model is also a listener, and will cache the result and
	 * forward it to the listening class when the result becomes available.
	 * 
	 * @param listener
	 *            Listener to notify when the result has been acquired.
	 * 
	 * @return An unspecified collection of all available restaurant listings,
	 *         or <code>null</code> if it will be returned later.
	 */
	public Collection<RestaurantListing> getAvailableRestaurantNames(
			RestaurantListRequestListener listener, Context awful) {
		Log.i("Splurge", "Restaurant list requested from model");
		mListForwardListener = listener;
		if (mAvailableRestaurantNames == null
				|| mAvailableRestaurantNames.isEmpty()) {
			Log.i("Splurge", "Restaurant list async task started");
			RestaurantListAsyncTask rlat = new RestaurantListAsyncTask(this);
			rlat.execute(mConnectionHandler, awful);
		}
		return mAvailableRestaurantNames;
	}

	@Override
	public void receiveRetaurantList(Collection<RestaurantListing> list) {
		Log.i("Splurge", "List received");
		mAvailableRestaurantNames = list;
		mListForwardListener.receiveRetaurantList(list);
	}

	@Override
	public void receiveRestaurantInfo(Restaurant restaurant) {
		Log.i("Splurge", "Restaurant received asynchronously");
		mCurrentRestaurant = restaurant;
		// Restaurant is not passed along to another listener
	}

	/**
	 * Get the phone number of the currently selected restaurant.
	 * 
	 * @return The restaurant's phone number.
	 */
	public int getRestaurantPhoneNumber() {
		return getRestaurant().getPhoneNumber();
	}

	/**
	 * Gets the street address of the currently restaurant in a human-readable
	 * format.
	 * 
	 * @return The currently selected restaurant's street address.
	 */
	public String getRestaurantStreetAddress() {
		return getRestaurant().getStreetAddress();
	}

	/**
	 * Gets the zip code of the currently selected restaurant.
	 * 
	 * @return The currently selected restaurant's zip code.
	 */
	public String getRestaurantZipcode() {
		return getRestaurant().getZipcode();
	}

	/**
	 * Retrieves the name of the currently selected restaurant.
	 * 
	 * @return The current restaurant's name.
	 */
	public String getRestaurantName() {
		return getRestaurant().getName();
	}
}
