package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Collection;

public class FoodMenu {

	private String menuName;
	private Collection<FoodItem> mFoodItems;

	public FoodMenu(String name) {
		menuName = name;
		mFoodItems = new ArrayList<FoodItem>();
	}

	public boolean addFoodItem(FoodItem food) {
		return mFoodItems.add(food);
	}

	public String getMenuName() {
		return menuName;
	}

	public Collection<FoodItem> getFoodList() {
		return mFoodItems;
	}
}
