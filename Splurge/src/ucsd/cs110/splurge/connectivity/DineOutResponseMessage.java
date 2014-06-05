package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for handling the JSON response to dine-out requests.
 */
public class DineOutResponseMessage {

	private static final String RESPONSE_STATUS = "status";
	private static final String RESPONSE_ID = "dineout_id";

	/**
	 * Identification number for the dine-out order requested by the user. This
	 * identification number may or may not be immediately relevant to the user.
	 */
	private int mResponseId;

	/**
	 * Creates a new response message class wrapping the dine-out identification
	 * number.
	 * 
	 * @param id
	 *            The wrapped identification number.
	 */
	private DineOutResponseMessage(int id) {
		mResponseId = id;
	}

	/**
	 * Gets the identification number being wrapped by this message class.
	 * <p>
	 * This is the identification number given in the JSON response message from
	 * the server. If the dine-out request was denied, then it is assumed that
	 * this is -1, and so this number should be assumed to be a request denial.
	 * </p>
	 * 
	 * @return The dine-out identification number being wrapped by this class.
	 */
	public int getId() {
		return mResponseId;
	}

	/**
	 * Inflates the given JSON string into a dine-out response message.
	 * 
	 * @param input
	 *            The JSON to inflate.
	 * @return A message class wrapping the dine-out response.
	 */
	public static DineOutResponseMessage createFromJSON(String input) {
		try {
			JSONObject jsonInput = new JSONObject(input);
			int response;
			if (jsonInput.getString(RESPONSE_STATUS).equals("Denied")) {
				response = -1;
			} else {
				response = jsonInput.getInt(RESPONSE_ID);
			}
			return new DineOutResponseMessage(response);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception while inflating dine-out reponse.");
			e.printStackTrace();
		}
		Log.e("Splurge", "Could not inflate dine-out response.");
		return null;
	}
}
