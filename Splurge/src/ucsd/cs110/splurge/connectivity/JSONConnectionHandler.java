package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;
import java.util.Collection;

import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.RestaurantListing;

/**
 * Deals with the connection specifics and acquires information as requested.
 */
public class JSONConnectionHandler {

	/**
	 * The handler for the connection as it goes along to the server.
	 */
	private ServerHttpJSONConnection mJSONConnection;

	/**
	 * Requests information about the restaurant given by the identification
	 * number. This includes menu information, availability, and most other key
	 * pieces of information.
	 * 
	 * @param id
	 *            The identification number to pull.
	 * @return An object containing most information about the restaurant.
	 */
	public Restaurant requestRestaurantInfo(int id) {
		String resp = mJSONConnection
				.pushServerMessage(new ReservationUpdateMessage(id));
		return RestaurantInfoResponseMessage.createFromJSON(resp)
				.getRestaurant();
	}

	/**
	 * Requests a reservation at the given restaurant for the given party size
	 * and starting at the given time.
	 * 
	 * @param restaurantId
	 *            The identification number of the restaurant at which a
	 *            reservation is to be made.
	 * @param partySize
	 *            The number of individuals included in the reservation.
	 * @param startTime
	 *            The time at which the reservation is to begin.
	 * @return The identification number for the reservation.
	 */
	public int requestReservation(int restaurantId, int partySize,
			Calendar startTime) {
		String resp = mJSONConnection
				.pushServerMessage(new ReservationRequestMessage(restaurantId,
						partySize, startTime));
		return ReservationResponseMessage.createFromJSON(resp).getId();
	}

	/**
	 * Requests a list of available restaurants. This includes any restaurant in
	 * the system at which the user could theoretically make a reservation,
	 * about which the user could read information, or for which the user could
	 * utilize any of the Splurge features.
	 * 
	 * @return A list of available restaurants.
	 */
	public Collection<RestaurantListing> requestRestaurantList() {
		String resp = mJSONConnection
				.pushServerMessage(new RestaurantListRequestMessage());
		return RestaurantListResponseMessage.createFromJSON(resp)
				.getRestaurantList();
	}
}
