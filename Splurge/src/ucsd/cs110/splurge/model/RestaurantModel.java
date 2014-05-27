package ucsd.cs110.splurge.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import ucsd.cs110.splurge.connectivity.tasks.RestaurantListAsyncTask;
import ucsd.cs110.splurge.connectivity.tasks.RestaurantListRequestListener;
import android.util.Log;

/**
 * Class containing business logic for the Splurge application.
 */
public class RestaurantModel implements RestaurantListRequestListener {
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
	private RestaurantListRequestListener mForwardListener;

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
	public void setRestaurantByName(String name) {
		// Get the restaurant ID from the internal list
		int id = -1;
		for (RestaurantListing l : mAvailableRestaurantNames) {
			if (l.getRestaurantName().equals(name)) {
				id = l.getRestaurantId();
				break;
			}
		}

		if (id != -1) {
			setRestaurantById(id);
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
	public void setRestaurantById(int id) {
		// Check the currently loaded restaurants for the given Id
		for (Restaurant r : mLoadedRestaurants) {
			if (r.getId() == id) {
				mCurrentRestaurant = r;
				Log.i("Splurge", "Model restaurant set to " + r.getName());
				return;
			}
		}

		// Because the restaurant was not found, we must query the server
		mCurrentRestaurant = mConnectionHandler.requestRestaurantInfo(id);
		Log.i("Splurge",
				"Model restaurant set to " + mCurrentRestaurant.getName());
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
	public int requestReservation(int partySize, Calendar startTime) {
		if (mCurrentRestaurant.isTimeUnavailable(startTime))
			return -1;
		return mConnectionHandler.requestReservation(
				mCurrentRestaurant.getId(), partySize, startTime);
	}

	/**
	 * Updates the available reservation times.
	 */
	public void updateAvailableTimes() {
		setRestaurantById(mCurrentRestaurant.getId());
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
	 * @return An array of labels fo rhte menus available for this restaurant.
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
			RestaurantListRequestListener listener) {
		mForwardListener = listener;
		if (mAvailableRestaurantNames == null
				|| mAvailableRestaurantNames.isEmpty()) {
			RestaurantListAsyncTask rlat = new RestaurantListAsyncTask(this);
		}
		return mAvailableRestaurantNames;
	}

	@Override
	public void receiveRetaurantList(Collection<RestaurantListing> list) {
		mAvailableRestaurantNames = list;
		mForwardListener.receiveRetaurantList(list);
	}
}
