package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ucsd.cs110.splurge.model.FoodMenu;
import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.Timeslot;

/**
 * Class for handling received JSON queries in response to a restaurant
 * information request.
 */
public class RestaurantInfoResponseMessage {
	private Restaurant mMessageResult;

	private RestaurantInfoResponseMessage(Restaurant r) {
		mMessageResult = r;
	}

	public Restaurant getRestaurant() {
		return mMessageResult;
	}

	public static RestaurantInfoResponseMessage createFromJSON(String input) {
		JSONObject inputJSON;
		try {
			inputJSON = new JSONObject(input);
			String name = inputJSON.getString("restaurantName");
			Restaurant encaps = new Restaurant(name);

			// Add unavailable times
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

			// Extract menus
			JSONArray menuArray = inputJSON.getJSONArray("restaurantMenus");
			for (int menuInd = 0; menuInd < menuArray.length(); ++menuInd) {
				JSONObject currentMenu = menuArray.getJSONObject(menuInd);
				FoodMenu buildMenu = new FoodMenu(
						currentMenu.getString("title"));
				JSONArray menuItems = currentMenu.getJSONArray("items");
				for (int itemInd = 0; itemInd < currentMenu.length(); ++itemInd) {

				}
			}

			return new RestaurantInfoResponseMessage(encaps);
		} catch (JSONException e) {
			return null;
		}
	}
}
