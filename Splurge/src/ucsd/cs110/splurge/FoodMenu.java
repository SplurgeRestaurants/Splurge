package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Depicts a menu for a single meal of the day, or of a single type of item, or
 * any other applicable specialization of a menu.
 */
public class FoodMenu {

	/**
	 * The title of the menu, as displayed to the user.
	 */
	private String menuName;
	/**
	 * Food items that are listed within this menu.
	 */
	private Collection<FoodItem> mFoodItems;

	/**
	 * Creates an empty menu with the given name and no food items.
	 * 
	 * @param name
	 */
	public FoodMenu(String name) {
		menuName = name;
		mFoodItems = new ArrayList<FoodItem>();
	}

	/**
	 * Adds a food item to the menu listing.
	 * 
	 * @param food
	 *            The item to insert into the listing.
	 * @return <code>true</code> on successful add.
	 */
	public boolean addFoodItem(FoodItem food) {
		return mFoodItems.add(food);
	}

	/**
	 * Retrieves a human-friendly listing of the menu's name.
	 * 
	 * @return A printable menu name.
	 */
	public String getMenuName() {
		return menuName;
	}

	/**
	 * Retrieves the list of food items on this menu.
	 * 
	 * @return The menu's food items.
	 */
	public Collection<FoodItem> getFoodList() {
		return mFoodItems;
	}
}
