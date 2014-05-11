package ucsd.cs110.splurge.model;

import java.util.ArrayList;
import java.util.Collection;

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
	private Collection<String> mAvailableRestaurantNames;

	/**
	 * Creates a new model with empty lists and no selected restaurant.
	 */
	public RestaurantModel() {
		mLoadedRestaurants = new ArrayList<Restaurant>();
		mAvailableRestaurantNames = new ArrayList<String>();
	}

	/**
	 * Selects a restaurant by name. This may be replaced by a future
	 * setRestaurantById(int) method.
	 * 
	 * @param name
	 *            The restaurant's name as it appears to the system.
	 */
	public void setRestaurantByName(String name) {
		for (Restaurant r : mLoadedRestaurants) {
			if (r.getName().equals(name)) {
				mCurrentRestaurant = r;
				return;
			}
		}

		// TODO (trtucker) add code to request a restaurant listing
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
	 * printable.
	 * 
	 * @return An unspecified collection of all available restaurant names.
	 */
	public Collection<String> getAvailableRestaurantNames() {
		return mAvailableRestaurantNames;
	}
}
