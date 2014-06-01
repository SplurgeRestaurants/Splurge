package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for handling the JSON creation for information requests.
 */
public class RestaurantInfoRequestMessage extends ServerMessage {

	/**
	 * ID of the restaurant to be requested.
	 */
	private int mId;

	/**
	 * Creates a new JSON compilation unit which will request the restaurant
	 * with the given id.
	 */
	public RestaurantInfoRequestMessage(int id) {
		setId(id);
	}

	@Override
	public String getMessageType() {
		return "Information";
	}

	@Override
	public String compileToJSON() {
		JSONObject jsonObj = new JSONObject();
		try {
			jsonObj.put(MESSAGE_TYPE, getMessageType())
					.put("request", "Restaurant").put("restaurantId", getId());
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSONException in RestaurantINfoRequestMessage.");
			Log.e("Splurge", e.getLocalizedMessage());
		}
		try {
			return jsonObj.toString(INDENT_SPACES);
		} catch (JSONException e) {
			return jsonObj.toString();
		}
	}

	/**
	 * Gets the identification number for the restaurant, as it will be sent to
	 * the server.
	 * 
	 * @return The id number which will be requested.
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Sets the identification number to be requested.
	 * 
	 * @param id
	 *            The new identification number to request.
	 */
	private void setId(int id) {
		mId = id;
	}

	@Override
	public String getURLSuffix() {
		return "restaurants/info_restaurant";
	}
}
