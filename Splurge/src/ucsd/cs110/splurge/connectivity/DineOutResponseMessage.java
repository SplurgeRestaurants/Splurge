package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DineOutResponseMessage {

	private static final String RESPONSE_STATUS = "status";
	private static final String RESPONSE_ID = "dineout_id";

	private int mResponseId;

	private DineOutResponseMessage(int id) {
		mResponseId = id;
	}

	public int getId() {
		return mResponseId;
	}

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
