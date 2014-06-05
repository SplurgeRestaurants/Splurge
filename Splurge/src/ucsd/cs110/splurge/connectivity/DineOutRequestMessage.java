package ucsd.cs110.splurge.connectivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

/**
 * Class for handling compilation of requests for dine-out orders into JSON
 * strings which may be sent to the server..
 */
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

	/**
	 * Creates an object which will compile the given inputs into a suitable
	 * JSON request.
	 * 
	 * @param restaurantId
	 *            Identification number of the target restaurant.
	 * @param requester
	 *            Name of the person making the request.
	 * @param phoneNum
	 *            Number at which the requester may be contacted.
	 * @param addy
	 *            Address of the requester, if applicable.
	 * @param kind
	 *            Kind of request to be made. This should be either
	 *            KIND_CARRY_OUT or KIND_DELIVERY.
	 * @param requestTime
	 *            The time at which the request should be realized.
	 * @param meal
	 *            Description of the meal to be requested.
	 */
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

	/**
	 * Updates the request time to the given value. This should be the only
	 * means by which this is done.
	 * 
	 * @param requestTime
	 *            The new time to request.
	 */
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

	/**
	 * Updates the meal to be requested. This should be the only means by which
	 * this is done.
	 * 
	 * @param meal
	 *            New meal to request.
	 */
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

	/**
	 * Updates the kind of request to make. This should be the only means by
	 * which this is done.
	 * 
	 * @param kind
	 *            The new kind of request.
	 */
	private void setKind(String kind) {
		mKind = kind;
		try {
			mJSONRep.put(REQUEST_KIND, mKind);
		} catch (JSONException e) {
			Log.e("Splurge", "Unexpected JSON Exception setting dine-out kind:"
					+ e.getMessage());
		}
	}

	/**
	 * Updates the target restaurant. This should be the only means by which
	 * this is done.
	 * 
	 * @param id
	 *            The new identification number.
	 */
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

	/**
	 * Updates the requester's address. This should be the only means by which
	 * this is done.
	 * 
	 * @param address
	 *            The requester's new address.
	 */
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

	/**
	 * Updates the requester's phone number. This should be the only means by
	 * which this is done.
	 * 
	 * @param number
	 *            The requester's new phone number.
	 */
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

	/**
	 * Updates the requester's name. THis should be the only means by which this
	 * is done.
	 * 
	 * @param name
	 *            The requester's new name.
	 */
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
