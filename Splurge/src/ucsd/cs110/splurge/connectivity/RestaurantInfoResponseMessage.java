package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ucsd.cs110.splurge.model.FoodItem;
import ucsd.cs110.splurge.model.FoodMenu;
import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.Timeslot;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.PointF;
import android.util.Base64;
import android.util.Log;

/**
 * Class for handling received JSON queries in response to a restaurant
 * information request.
 */
public class RestaurantInfoResponseMessage {
	private Restaurant mMessageResult;

	private RestaurantInfoResponseMessage(Restaurant r) {
		mMessageResult = r;
	}

	/**
	 * Retrieves the Restaurant which has been constructed from the JSON input.
	 * 
	 * @return The Restaurant fleshed out from the JSON.
	 */
	public Restaurant getRestaurant() {
		return mMessageResult;
	}

	/**
	 * Interprets the JSON object and constructs a Restaurant object wrapped in
	 * the message class.
	 * 
	 * @param input
	 *            JSON string to interpret
	 * @return An object wrapping the decoded Restaurant.
	 */
	public static RestaurantInfoResponseMessage createFromJSON(String input) {
		JSONObject inputJSON;
		try {
			inputJSON = new JSONObject(input);
			String name = inputJSON.getString("restaurantName");
			Restaurant encaps = new Restaurant(name);

			encaps.setId(inputJSON.getInt("restaurantId"));

			if (inputJSON.has("restaurantLocation")) {
				JSONArray locArray = inputJSON
						.getJSONArray("restaurantLocation");
				encaps.setLocation(new PointF((float) locArray.getDouble(0),
						(float) locArray.getDouble(1)));
			}

			// Add unavailable times
			if (inputJSON.has("restaurantAvailability")) {
				JSONArray unavailables = inputJSON
						.getJSONArray("restaurantAvailability");
				for (int i = 0; i < unavailables.length(); ++i) {
					JSONObject obj = unavailables.getJSONObject(i);
					Calendar start, end;
					start = Calendar.getInstance();
					end = Calendar.getInstance();
					start.setTimeInMillis(obj.getLong("start"));
					end.setTimeInMillis(obj.getLong("end"));
					encaps.addUnavailableTime(new Timeslot(start, end));
				}
			}

			// Extract menus
			if (inputJSON.has("restaurantMenus")) {
				JSONArray menuArray = inputJSON.getJSONArray("restaurantMenus");
				for (int menuInd = 0; menuInd < menuArray.length(); ++menuInd) {
					JSONObject currentMenu = menuArray.getJSONObject(menuInd);
					FoodMenu buildMenu = new FoodMenu(
							currentMenu.getString("title"));
					JSONArray menuItems = currentMenu.getJSONArray("items");
					for (int itemInd = 0; itemInd < currentMenu.length(); ++itemInd) {
						JSONObject foodJSON = menuItems.getJSONObject(itemInd);
						FoodItem food = new FoodItem(
								foodJSON.getString("itemName"));
						byte[] decodedImage = Base64
								.decode(foodJSON.getString("itemImage"),
										Base64.DEFAULT);
						Bitmap image = BitmapFactory.decodeByteArray(
								decodedImage, 0, decodedImage.length);
						food.setImage(image);
						buildMenu.addFoodItem(food);
					}
				}
			}

			return new RestaurantInfoResponseMessage(encaps);
		} catch (JSONException e) {
			Log.i("Splurge",
					"Unexpected JSON Error: " + e.getLocalizedMessage());
			Log.i("Splurge", e.getMessage());
			return null;
		}
	}
}
