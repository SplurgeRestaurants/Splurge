package ucsd.cs110.splurge.connectivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ucsd.cs110.splurge.model.RestaurantListing;
import android.content.Context;
import android.util.Log;

/**
 * Class for handling JSON input of restaurant lists.
 * <p>
 * Usage of this class generally follows these steps: First, call
 * <code>createFromJSON()</code> in order to inflate a JSON string into an
 * object. Second, call <code>getRestaurantList()</code> in order to retrive the
 * restaurant list which was inflated and then wrapped in the message object.
 * </p>
 * <p>
 * This process may seem to include an extra step in retrieving the restaurant
 * list from the message object. One might wonder why this class is not final
 * and/or abstract, and only contain the static method
 * <code>createFromJSON()</code> which returns the
 * <code>Collection&lt;RestaurantListing&gt;</code> which is retrieved from the
 * wrapper object at a later time anyway. Such a person may keep wondering.
 * </p>
 */
public class RestaurantListResponseMessage {

	/**
	 * The restaurant list which has been retrieved by inflating the JSON input
	 * string.
	 */
	private Collection<RestaurantListing> mRestaurantList;

	/**
	 * Creates a new message wrapping the given list.
	 * 
	 * @param list
	 *            The list of restaurants to wrap.
	 */
	private RestaurantListResponseMessage(Collection<RestaurantListing> list) {
		setRestaurantList(list);
	}

	/**
	 * Interprets the JSON string and inflates it into a list of restaurant
	 * information objects wrapped within the message class.
	 * 
	 * @param json
	 *            The JSON string to inflate into the list.
	 * @param awful
	 *            A Context to access application resources for setting default
	 *            restaurant logos.
	 * @return A message wrapping the restaurant listings.
	 */
	public static RestaurantListResponseMessage createFromJSON(String json,
			Context awful) {
		JSONObject jsonInput;
		Collection<RestaurantListing> list = new ArrayList<RestaurantListing>();
		try {
			jsonInput = new JSONObject(json);
			JSONArray arr = jsonInput.getJSONArray("list");
			for (int i = 0; i < arr.length(); ++i) {
				JSONObject obj = arr.getJSONObject(i);
				list.add(new RestaurantListing(obj.getString("name")
						.toUpperCase(Locale.US), obj.getInt("id"), obj
						.getString("icon"), awful));
			}
			return new RestaurantListResponseMessage(list);
		} catch (JSONException ex) {
			Log.e("Splurge",
					"Unexpected JSON exception in RestaurantListResponseMessage.");
			Log.e("Splurge", ex.getLocalizedMessage());
		}
		return null;
	}

	/**
	 * Sets the wrapped restaurant list.
	 * 
	 * @param list
	 *            The new list to wrap.
	 */
	private void setRestaurantList(Collection<RestaurantListing> list) {
		mRestaurantList = list;
	}

	/**
	 * Retrieves the restaurant list wrapped by the message object.
	 * 
	 * @return The restaurant list.
	 */
	public Collection<RestaurantListing> getRestaurantList() {
		return mRestaurantList;
	}
}
