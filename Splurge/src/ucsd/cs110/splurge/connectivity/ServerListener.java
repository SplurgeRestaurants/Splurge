package ucsd.cs110.splurge.connectivity;

import ucsd.cs110.splurge.model.Restaurant;

public interface ServerListener {

	/**
	 * Callback to receive a restaurant from the Server. This is called after an
	 * information request response is received from the server.
	 * 
	 * @param r
	 *            The restaurant with all info received from the server.
	 */
	public void receiveRestaurant(Restaurant r);

	/**
	 * Callback to receive a reservation confirmation from the Server. This is
	 * called after a reservation request is sent to the server and the response
	 * has been received.
	 * 
	 * @param reservationID
	 *            The identification number of the reservation, or -1 if the
	 *            reservation has been denied.
	 */
	public void receiveReservationConfirmation(int reserverationID);
}
