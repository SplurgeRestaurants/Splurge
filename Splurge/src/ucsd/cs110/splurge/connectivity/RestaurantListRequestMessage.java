package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for handling the creation of JSON queries requesting a list of
 * available restaurants.
 * <p>
 * Usage of this class generally follows the followings steps:
 * <ol>
 * <li>Create an object of this class containing any applicable parameters
 * (there are none).</li>
 * <li>Pass the object to a JSONConnectionHandler, which will deal with sending
 * it to the server.</li>
 * <li>The JSONConnectionHandler will call <code>compileToJSON()</code> in order
 * to extract the request.</li>
 * </ol>
 * </p>
 * <p>
 * This is the simplest implementation of the ServerMessage family.
 * </p>
 */
public class RestaurantListRequestMessage extends ServerMessage {

	@Override
	public String getMessageType() {
		return "Information";
	}

	@Override
	public String compileToJSON() {
		JSONObject json = new JSONObject();
		try {
			json.put(MESSAGE_TYPE, getMessageType());
			json.put("request", "RestaurantList");
		} catch (JSONException ex) {
			Log.e("Splurge",
					"Unexpected JSON Error in RestaurantListRequestMessage.");
			Log.e("Splurge", ex.getLocalizedMessage());
		}
		try {
			return json.toString(INDENT_SPACES);
		} catch (JSONException ex) {
			return json.toString();
		}
	}

	@Override
	public String getURLSuffix() {
		return "restaurants/list_restaurants";
	}
}
