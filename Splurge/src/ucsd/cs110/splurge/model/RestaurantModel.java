package ucsd.cs110.splurge.model;

import java.util.ArrayList;
import java.util.Collection;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import android.util.Log;

/**
 * Class containing business logic for the Splurge application.
 */
public class RestaurantModel {
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
	 * Requests the current restaurant object from the Model.
	 * 
	 * @return The currently selected Restaurant.
	 */
	public Restaurant getRestaurant() {
		return mCurrentRestaurant;
	}

	/**
	 * Retrieves the container of all restaurant names which should be available
	 * to the user. These names should be user-friendly and immediately
	 * printable. They are paired with the corresponding restaurant
	 * Identification numbers to reduce confusion.
	 * 
	 * @return An unspecified collection of all available restaurant listings.
	 */
	public Collection<RestaurantListing> getAvailableRestaurantNames() {
		return mAvailableRestaurantNames;
	}
}
