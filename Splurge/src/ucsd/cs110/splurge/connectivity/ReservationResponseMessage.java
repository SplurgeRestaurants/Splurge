package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class ReservationResponseMessage {
	private int mId;

	private ReservationResponseMessage(int id) {
		setId(id);
	}

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

	public int getId() {
		return mId;
	}

	private void setId(int id) {
		mId = id;
	}
}
