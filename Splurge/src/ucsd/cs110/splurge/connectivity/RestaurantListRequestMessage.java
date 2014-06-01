package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

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
