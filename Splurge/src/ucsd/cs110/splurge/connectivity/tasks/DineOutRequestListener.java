package ucsd.cs110.splurge.connectivity.tasks;

/**
 * Interface for receiving responses for dine-out requests.
 */
public interface DineOutRequestListener {

	/**
	 * Callback method for receving the identification number of the dine-out
	 * order.
	 * <p>
	 * This will be a number no less than zero if the dine-out request was
	 * accepted. This will be -1 in the event that the request was denied.
	 * </p>
	 * 
	 * @param id
	 *            The identification number of the dine-out request.
	 */
	void receiveDineOutId(int id);
}
