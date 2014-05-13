package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Object for composing JSON messages for updating a reservation with waiter and
 * pre-order request information.
 */
public class ReservationUpdateMessage extends ServerMessage {

	private static final String RESERVATION_ID = "reservationID";
	private static final String WAITER_ID = "waiterID";
	private static final String PRE_ORDER = "pre-order";

	/**
	 * Internal JSON Object which is used to compile into a JSON String.
	 */
	private JSONObject mJSONRep;
	/**
	 * Identification number for the reservation to be edited. Should be
	 * provided by the server. Without this value, this message is meaningless.
	 */
	private int mReservationID;
	/**
	 * Identification number of the waiter to request, or -1 if none. This value
	 * will be sent as -1 to notify the server that no waiter is selected.
	 */
	private int mWaiterID;
	/**
	 * Handler object for compiling the JSONArray of pre-order item listings.
	 */
	private PreOrderJSONArrayHandler mArrayHandler;

	/**
	 * Creates a new update message for the specified identification number. All
	 * update fields are initialized to default empty values.
	 * 
	 * @param reservationID
	 *            The reservation to update, specified by identification number.
	 */
	public ReservationUpdateMessage(int reservationID) {
		this(reservationID, -1);
	}

	/**
	 * Creates a new object to compose a JSON message for updating a reservation
	 * to request a particular waiter and food items to pre-order.
	 * 
	 * @param reservationID
	 *            The identification number of the reservation to update.
	 * @param waiterID
	 *            The identification number of the waiter to request. -1
	 *            specifies no waiter.
	 * @param foodItemIDs
	 *            The identification numbers of the food items to request.
	 *            Duplicate ids specify multiple orders of the same item. This
	 *            can be emtpy.
	 */
	public ReservationUpdateMessage(int reservationID, int waiterID,
			int... foodItemIDs) {
		mJSONRep = new JSONObject();
		mArrayHandler = new PreOrderJSONArrayHandler(foodItemIDs);
		try {
			mJSONRep.put(MESSAGE_TYPE, getMessageType());
			mJSONRep.put("status", "Addition");
			mJSONRep.put("pre-order", mArrayHandler.compileArray());
		} catch (JSONException e) {
			// This will never occur.
		}
		setReservationID(reservationID);
		setWaiterID(waiterID);
	}

	@Override
	public String getMessageType() {
		return "Reservation";
	}

	@Override
	public String compileToJSON() {
		try {
			mJSONRep.put(PRE_ORDER, mArrayHandler.compileArray());
		} catch (JSONException e) {
		}
		try {
			return mJSONRep.toString(INDENT_SPACES);
		} catch (JSONException ex) {
			return mJSONRep.toString();
		}
	}

	/**
	 * Retrieves the identification number of the reservation to be updated by
	 * this message.
	 * 
	 * @return This update message's reservation identification number.
	 */
	public int getReservationID() {
		return mReservationID;
	}

	/**
	 * Changes the reservation to which this message refers to the specified
	 * identification number.
	 * 
	 * @param id
	 *            The new reservation to update.
	 */
	public void setReservationID(int id) {
		mReservationID = id;
		try {
			mJSONRep.put(RESERVATION_ID, mReservationID);
		} catch (JSONException e) {
			// Do nothing
		}
	}

	/**
	 * Adds a food item to the pre-order listing for this reservation update
	 * message.
	 * 
	 * @param id
	 *            The identification number of the food item to add.
	 */
	public void addFoodItemById(int id) {
		mArrayHandler.addSingleItemByID(id);
	}

	/**
	 * Updates the requested waiter by identification number.
	 * 
	 * @param id
	 *            The new waiter to request.
	 */
	public void setWaiterID(int id) {
		mWaiterID = id;
		try {
			mJSONRep.put(WAITER_ID, id);
		} catch (JSONException e) {
		}
	}

	/**
	 * Retrieves the identification number for the currently requested waiter.
	 * 
	 * @return The identification number for the currently selected waiter.
	 */
	public int getWaiterID() {
		return mWaiterID;
	}
}
