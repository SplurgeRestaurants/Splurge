package ucsd.cs110.splurge.connectivity.tasks;

/**
 * Defines the interface for asynchronously receiving reservation responses.
 */
public interface ReservationRequestListener {

	/**
	 * Callback method for receiving the reservation identification number.
	 * <p>
	 * This will be a number greater than or equal to zero if the reservation
	 * was successful. This will be -1 if the reservation request was denied.
	 * </p>
	 * 
	 * @param id
	 *            Identification number for the reservation.
	 */
	void receiveReservationId(int id);
}
