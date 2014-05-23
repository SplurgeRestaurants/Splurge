package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Helper class for constructing the JSON request for making a reservation.
 */
public class ReservationRequestMessage extends ServerMessage {

	private static final String TIME_START = "timeStart";
	private static final String PARTY_SIZE = "partySize";
	private static final String REST_ID = "restaurantID";

	/**
	 * The identificaiton number for the restaurant at which to make the
	 * reservation.
	 */
	private int mRestaurantId;
	/**
	 * The party size to request for the reservation.
	 */
	private int mPartySize;
	/**
	 * The time at which the reservation is to begin.
	 */
	private Calendar mStartTime;
	/**
	 * The JSON object used for constructing the final result.
	 */
	private JSONObject mJSONRep;

	/**
	 * Creates a new ReservationRequestMessage with completely invalid fields.
	 * Other than this, the message is valid, and conforms to the specifications
	 * and is usable.
	 */
	public ReservationRequestMessage() {
		this(-1, -1, new GregorianCalendar(0, 0, 0, 0, 0, 0));
	}

	/**
	 * Creates a new ReservationRequestMessage with the specified fields. No
	 * validity checking is enacted. The resulting message object will be valid
	 * in form, although the utility of the object is dependent on the validity
	 * of the input fields.
	 * 
	 * @param partySize
	 *            The size of the party for the reservation.
	 * @param startTime
	 *            The time at which the reservation shall begin.
	 */
	public ReservationRequestMessage(int restaurantId, int partySize,
			Calendar startTime) {
		mJSONRep = new JSONObject();
		try {
			mJSONRep.put(MESSAGE_TYPE, getMessageType());
			mJSONRep.put("status", "request");
		} catch (JSONException e) {
			// This will never occur.
		}

		setRestaurantId(restaurantId);
		setPartySize(partySize);
		setReservationStartTime(startTime);
	}

	/**
	 * Updates the restaurant at which a reservation is to be made.
	 * 
	 * @param restaurantId
	 *            The identification number for the desired restaurant.
	 */
	private void setRestaurantId(int restaurantId) {
		mRestaurantId = restaurantId;
		try {
			mJSONRep.put(REST_ID, mRestaurantId);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON error occurred when setting restaurant ID.");
			Log.e("Splurge", e.getLocalizedMessage());
		}
	}

	/**
	 * Updates the reservation start time.
	 * 
	 * @param startTime
	 *            The new starting time.
	 */
	public void setReservationStartTime(Calendar startTime) {
		mStartTime = startTime;
		try {
			mJSONRep.put(TIME_START, mStartTime.getTimeInMillis());
		} catch (JSONException e) {
		}
	}

	/**
	 * Updates the reservation party size.
	 * 
	 * @param partySize
	 *            The new party size.
	 */
	public void setPartySize(int partySize) {
		mPartySize = partySize;
		try {
			mJSONRep.put(PARTY_SIZE, mPartySize);
		} catch (JSONException e) {
		}
	}

	@Override
	public String getMessageType() {
		return "Reservation";
	}

	@Override
	public String compileToJSON() {
		try {
			// Prefered behaviour is to retrieve a human-readable String.
			return mJSONRep.toString(INDENT_SPACES);
		} catch (JSONException e) {
			// Not sure why this would occur, but this seems to be the best
			// failsafe.
			return mJSONRep.toString();
		}
	}
}
