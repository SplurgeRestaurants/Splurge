package ucsd.cs110.splurge.connectivity;

import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ucsd.cs110.splurge.R;
import ucsd.cs110.splurge.model.FoodItem;
import ucsd.cs110.splurge.model.FoodMenu;
import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.Timeslot;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

/**
 * Class for handling received JSON queries in response to a restaurant
 * information request.
 */
public class RestaurantInfoResponseMessage {
	private static final String RESTAURANT_NAME = "restaurantName";
	private static final String RESTAURANT_ID = "restaurantId";
	private static final String RESTAURANT_ADDRESS = "restaurantLocation";
	private static final String RESTAURANT_MENUS = "restaurantMenus";
	private static final String RESTAURANT_HOURS = "restaurantHours";
	private static final String RESTAURANT_AVAILABILITY = "reservableDays";
	private static final String AVAILABILITY_START = "start";
	private static final String AVAILABILITY_END = "end";
	private static final String MENU_TITLE = "title";
	private static final String MENU_ITEMS = "items";
	private static final String MENU_ITEM_NAME = "itemName";
	private static final String MENU_ITEM_IMAGE = "itemImage";
	private static final String MENU_ITEM_PRICE = "price";

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
	public static RestaurantInfoResponseMessage createFromJSON(String input,
			Context awful) {
		JSONObject inputJSON;
		try {
			inputJSON = new JSONObject(input);
			String name = inputJSON.getString(RESTAURANT_NAME);
			Restaurant encaps = new Restaurant(name);

			encaps.setId(inputJSON.getInt(RESTAURANT_ID));

			if (inputJSON.has(RESTAURANT_ADDRESS)) {
				String fullAddy = inputJSON.getString(RESTAURANT_ADDRESS);
				int zipStart = fullAddy.lastIndexOf(' ');
				encaps.setZipcode(fullAddy.substring(zipStart).trim());
				fullAddy = fullAddy.substring(0, zipStart).trim();
				if (fullAddy.charAt(fullAddy.length() - 1) == ',')
					fullAddy = fullAddy.substring(0, fullAddy.length() - 1);
				encaps.setStreetAddress(fullAddy);
			}

			// Add unavailable times
			if (inputJSON.has(RESTAURANT_AVAILABILITY)) {
				JSONArray unavailables = inputJSON
						.getJSONArray(RESTAURANT_AVAILABILITY);
				for (int i = 0; i < unavailables.length(); ++i) {
					JSONObject obj = unavailables.getJSONObject(i);
					Calendar start, end;
					start = Calendar.getInstance();
					end = Calendar.getInstance();
					start.setTimeInMillis(obj.getLong(AVAILABILITY_START));
					end.setTimeInMillis(obj.getLong(AVAILABILITY_END));
					encaps.addUnavailableTime(new Timeslot(start, end));
				}
			}

			// Extract menus
			if (inputJSON.has(RESTAURANT_MENUS)) {
				JSONArray menuArray = inputJSON.getJSONArray(RESTAURANT_MENUS);
				for (int menuInd = 0; menuInd < menuArray.length(); ++menuInd) {
					JSONObject currentMenu = menuArray.getJSONObject(menuInd);
					FoodMenu buildMenu = new FoodMenu(
							currentMenu.getString(MENU_TITLE));
					JSONArray menuItems = currentMenu.getJSONArray(MENU_ITEMS);
					for (int itemInd = 0; itemInd < currentMenu.length(); ++itemInd) {
						JSONObject foodJSON = menuItems.getJSONObject(itemInd);
						FoodItem food = new FoodItem(
								foodJSON.getString(MENU_ITEM_NAME));
						Bitmap defaultImage = BitmapFactory.decodeResource(
								awful.getResources(), R.drawable.mainlogo6);
						if (foodJSON.has(MENU_ITEM_IMAGE)
								&& foodJSON.getString(MENU_ITEM_IMAGE).length() > 0) {
							byte[] decodedImage = Base64.decode(
									foodJSON.getString(MENU_ITEM_IMAGE),
									Base64.DEFAULT);
							Bitmap image = BitmapFactory.decodeByteArray(
									decodedImage, 0, decodedImage.length);
							food.setImage(image);
						} else {
							food.setImage(defaultImage);
						}
						if (foodJSON.has(MENU_ITEM_PRICE)) {
							food.setPrice(foodJSON.getDouble(MENU_ITEM_PRICE));
						}
						buildMenu.addFoodItem(food);
					}
				}
			}

			if (inputJSON.has(RESTAURANT_HOURS)) {
				// TODO (trtucker) interpret hours array
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
