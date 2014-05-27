package ucsd.cs110.splurge.connectivity.tasks;

import java.util.Collection;

import ucsd.cs110.splurge.model.RestaurantListing;

/**
 * Defines the interface for receiving a restaurant list from another source.
 * This is required so that the request can be handled on a separate thread.
 */
public interface RestaurantListRequestListener {

	/**
	 * Receives a list of restaurant listings from an anonymous benefactor.
	 * 
	 * @param list
	 *            The received list of listings.
	 */
	public void receiveRetaurantList(Collection<RestaurantListing> list);
}
