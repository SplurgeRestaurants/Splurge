package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;
import java.util.Collection;

import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.RestaurantListing;
import android.content.Context;

/**
 * Deals with the connection specifics and acquires information as requested.
 */
public class JSONConnectionHandler {

	/**
	 * The handler for the connection as it goes along to the server.
	 */
	private ServerHttpJSONConnection mJSONConnection;

	public JSONConnectionHandler() {
		mJSONConnection = new ServerHttpJSONConnection();
	}

	/**
	 * Requests information about the restaurant given by the identification
	 * number. This includes menu information, availability, and most other key
	 * pieces of information.
	 * 
	 * @param id
	 *            The identification number to pull.
	 * @return An object containing most information about the restaurant.
	 */
	public Restaurant requestRestaurantInfo(Context awful, int id) {
		String resp = mJSONConnection
				.pushServerMessage(new RestaurantInfoRequestMessage(id));
		return RestaurantInfoResponseMessage.createFromJSON(resp, awful)
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
	 * @param partyName
	 *            The name under which the reservation is to be made
	 * @param startTime
	 *            The time at which the reservation is to begin.
	 * @return The identification number for the reservation.
	 */
	public int requestReservation(int restaurantId, int partySize,
			String partyName, Calendar startTime) {
		String resp = mJSONConnection
				.pushServerMessage(new ReservationRequestMessage(restaurantId,
						partySize, partyName, startTime));
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
	public Collection<RestaurantListing> requestRestaurantList(Context awful) {
		String resp = mJSONConnection
				.pushServerMessage(new RestaurantListRequestMessage());
		return RestaurantListResponseMessage.createFromJSON(resp, awful)
				.getRestaurantList();
	}

	/**
	 * Requests a dine-out order (either take-out or delivery).
	 * 
	 * @param restaurantId
	 *            Identification number of the restaurant at which the request
	 *            is to be made.
	 * @param requesterName
	 *            The name of the individual requesting the order.
	 * @param phoneNum
	 *            The phone number at which the requester may be conacted.
	 * @param addy
	 *            The address to which the delivery shall be made.
	 * @param kind
	 *            The kind of order; either DineOutRequestMessage.KIND_TAKE_OUT
	 *            or DineOutRequestMessage.KIND_DELIVERY.
	 * @param requestTime
	 *            The time around which the request should be realized.
	 * @param meal
	 *            Long, ugly String including item names, quantities, and
	 *            special notes.
	 * @return The integer representing the identification number for the
	 *         successful order or -1 if the order was unsuccessful.
	 */
	public int requestDineOut(int restaurantId, String requesterName,
			String phoneNum, String addy, String kind, Calendar requestTime,
			String meal) {
		String resp = mJSONConnection
				.pushServerMessage(new DineOutRequestMessage(restaurantId,
						requesterName, phoneNum, addy, kind, requestTime, meal));
		return DineOutResponseMessage.createFromJSON(resp).getId();
	}
}
