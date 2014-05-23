package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;

import ucsd.cs110.splurge.model.Restaurant;

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

	public int requestReservation(int partySize, Calendar startTime) {
		String resp = mJSONConnection
				.pushServerMessage(new ReservationRequestMessage(partySize,
						startTime));
		return ReservationResponseMessage.createFromJSON(resp).getId();
	}
}
