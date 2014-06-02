package ucsd.cs110.splurge.connectivity.tasks;

import ucsd.cs110.splurge.model.Restaurant;

/**
 * Defines the interface for asynchronously receiving restaurant information. If
 * restaurant information is not available in a short period, the application
 * will fall back to usage of this interface.
 */
public interface RestaurantInfoRequestListener {

	/**
	 * Receives a restaurant from an anonymous benefactor. This call should take
	 * the restaurant and store it somewhere useful, as well as updating any UI
	 * elements as necessary.
	 * 
	 * @param restaurant
	 *            The received restaurant.
	 */
	public void receiveRestaurantInfo(Restaurant restaurant);
}
