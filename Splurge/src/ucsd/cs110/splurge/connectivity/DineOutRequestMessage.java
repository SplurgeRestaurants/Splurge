package ucsd.cs110.splurge.connectivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class DineOutRequestMessage extends ServerMessage {

	public static final String KIND_CARRY_OUT = "co";
	public static final String KIND_DELIVERY = "d";

	private static final String REQUESTER_NAME = "name";
	private static final String REQUEST_TIME = "time_gimme";
	private static final String REQUESTER_PHONE = "phone_num";
	private static final String REQUESTER_ADDRESS = "address";
	private static final String REQUEST_KIND = "kind";
	private static final String RESTAURANT_ID = "restaurants_id";
	private static final String REQUESTED_MEAL = "meal";

	private JSONObject mJSONRep;
	private int mRestId;
	private String mRequesterName;
	private String mPhoneNum;
	private String mAddress;
	private String mKind;
	private String mMealDesc;
	private Calendar mRequestTime;

	public DineOutRequestMessage(int restaurantId, String requester,
			String phoneNum, String addy, String kind, Calendar requestTime,
			String meal) {
		mJSONRep = new JSONObject();
		setRestaurantId(restaurantId);
		setRequesterName(requester);
		setPhoneNumber(phoneNum);
		setAddress(addy);
		setKind(kind);
		setMeal(meal);
		setRequestTime(requestTime);
	}

	private void setRequestTime(Calendar requestTime) {
		mRequestTime = requestTime;
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
					Locale.US);
			mJSONRep.put(REQUEST_TIME, df.format(mRequestTime.getTime()));
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception when setting dine-out request time: "
							+ e.getMessage());
		}
	}

	private void setMeal(String meal) {
		mMealDesc = meal;
		try {
			mJSONRep.put(REQUESTED_MEAL, mMealDesc);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception setting dine-out meal: "
							+ e.getMessage());
		}
	}

	private void setKind(String kind) {
		mKind = kind;
		try {
			mJSONRep.put(REQUEST_KIND, mKind);
		} catch (JSONException e) {
			Log.e("Splurge", "Unexpected JSON Exception setting dine-out kind:"
					+ e.getMessage());
		}
	}

	private void setRestaurantId(int id) {
		mRestId = id;
		try {
			mJSONRep.put(RESTAURANT_ID, mRestId);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception when setting dine-out restaurant id: "
							+ e.getMessage());
		}
	}

	private void setAddress(String address) {
		mAddress = address;
		try {
			mJSONRep.put(REQUESTER_ADDRESS, mAddress);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON exception when setting the dine-out address: "
							+ e.getMessage());
		}
	}

	private void setPhoneNumber(String number) {
		mPhoneNum = number;
		try {
			mJSONRep.put(REQUESTER_PHONE, mPhoneNum);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception while setting dine-out requester phone number: "
							+ e.getMessage());
		}
	}

	private void setRequesterName(String name) {
		mRequesterName = name;
		try {
			mJSONRep.put(REQUESTER_NAME, mRequesterName);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception while setting dine-out requester name: "
							+ e.getMessage());
		}
	}

	@Override
	public String getMessageType() {
		return "dine_out";
	}

	@Override
	public String compileToJSON() {
		JSONObject wrap = new JSONObject();
		try {
			wrap.put(getMessageType(), mJSONRep);
		} catch (JSONException e) {
			Log.e("Splurge",
					"Unexpected JSON Exception while compiling DineOutRequest.");
			Log.e("Splurge", e.getLocalizedMessage());
		}
		try {
			return wrap.toString(INDENT_SPACES);
		} catch (JSONException ex) {
			return wrap.toString();
		}
	}

	@Override
	public String getURLSuffix() {
		return "dine_outs";
	}
}
