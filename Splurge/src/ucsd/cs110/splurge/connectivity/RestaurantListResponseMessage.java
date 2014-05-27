package ucsd.cs110.splurge.connectivity;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ucsd.cs110.splurge.model.RestaurantListing;
import android.util.Log;

public class RestaurantListResponseMessage {

	private Collection<RestaurantListing> mRestaurantList;

	private RestaurantListResponseMessage(Collection<RestaurantListing> list) {
		setRestaurantList(list);
	}

	public static RestaurantListResponseMessage createFromJSON(String json) {
		JSONObject jsonInput;
		Collection<RestaurantListing> list = new ArrayList<RestaurantListing>();
		try {
			jsonInput = new JSONObject(json);
			JSONArray arr = jsonInput.getJSONArray("list");
			for (int i = 0; i < arr.length(); ++i) {
				JSONObject obj = arr.getJSONObject(i);
				list.add(new RestaurantListing(obj.getString("name"), obj
						.getInt("id"), obj.getString("image")));
			}
			return new RestaurantListResponseMessage(list);
		} catch (JSONException ex) {
			Log.e("Splurge",
					"Unexpected JSON exception in RestaurantListResponseMessage.");
			Log.e("Splurge", ex.getLocalizedMessage());
		}
		return null;
	}

	private void setRestaurantList(Collection<RestaurantListing> list) {
		mRestaurantList = list;
	}

	public Collection<RestaurantListing> getRestaurantList() {
		return mRestaurantList;
	}
}
