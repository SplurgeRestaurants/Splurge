package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for handling the JSON responses to a reservation request.
 */
public class ReservationResponseMessage {
	private int mId;

	/**
	 * Creates a new wrapping for the identification number for the reservation
	 * requested.
	 * 
	 * @param id
	 *            The identification number of the received reservation. -1
	 *            signifies no reservation.
	 */
	private ReservationResponseMessage(int id) {
		setId(id);
	}

	/**
	 * Inflates the given JSON string into a wrapper containing the
	 * identification number for the received reservation.
	 * <p>
	 * If no reservation was received, then it is assumed that the reservation
	 * identification number is -1. This identification number is invalid, and
	 * should be used to signify a reservation denial.
	 * </p>
	 * 
	 * @param json
	 *            JSON string to inflate into a reservation response.
	 * @return A wrapper containing the identification number for the
	 *         reservation.
	 */
	public static ReservationResponseMessage createFromJSON(String json) {
		JSONObject inputJSON;
		try {
			inputJSON = new JSONObject(json);
			String messageType = inputJSON
					.getString(ServerMessage.MESSAGE_TYPE);
			if (!messageType.equals("ReservationResponse"))
				Log.e("Splurge", "Incorrect ReservationReponse message type: "
						+ messageType);
			String status = inputJSON.getString("status");
			if (status.equals("Rejected")) {
				return new ReservationResponseMessage(-1);
			} else if (status.equals("Accepted")) {
				int id = inputJSON.getInt("reservationID");
				return new ReservationResponseMessage(id);
			} else {
				Log.e("Splurge", "Improper reservation status: " + status);
				return new ReservationResponseMessage(-1);
			}
		} catch (JSONException e) {
			Log.e("Splurge",
					"Error occurred when unpacking reservation response.");
			Log.e("Splurge", e.getLocalizedMessage());
			return null;
		}
	}

	/**
	 * Retrieves the identification number for the retrieved reservation.
	 * 
	 * @return The received identification number.
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Sets the identification number being wrapped by this message class.
	 * 
	 * @param id
	 *            The new identification number.
	 */
	private void setId(int id) {
		mId = id;
	}
}
