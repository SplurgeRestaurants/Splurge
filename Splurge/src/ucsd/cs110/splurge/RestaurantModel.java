package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Collection;

public class RestaurantModel {
	private Collection<Restaurant> mLoadedRestaurants;
	private Restaurant mCurrentRestaurant;
	private Collection<String> mAvailableRestaurantNames;

	public RestaurantModel() {
		mLoadedRestaurants = new ArrayList<Restaurant>();
		mAvailableRestaurantNames = new ArrayList<String>();
	}

	public void setRestaurantByName(String name) {
		for (Restaurant r : mLoadedRestaurants) {
			if (r.getName().equals(name)) {
				mCurrentRestaurant = r;
				return;
			}
		}

		// TODO (trtucker) add code to request a restaurant listing
	}

	public Restaurant getRestaurant() {
		return mCurrentRestaurant;
	}

	public Collection<String> getAvailableRestaurantNames() {
		return mAvailableRestaurantNames;
	}
}
