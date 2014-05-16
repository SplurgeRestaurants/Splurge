package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * 
 * @author Dan Thai
 * 
 */
public class FoodMenuListFragment extends SuperFragment {
	private static final String DINNER = "Dinner";
	private static final String LUNCH = "Lunch";
	private static final String BREAKFAST = "Breakfast";
	private static String MEAL;
	/**
	 * 
	 */
	private ListView mListView;
	/**
	 * Adapter to create list entries in ListView
	 */
	FoodMenuAdapter adapter;
	/**
	 * save position of the chosen food item
	 */
	static String FOOD_ITEM_POSITION;
	/**
	 * populate with selected food items
	 */
	static ArrayList<FoodItem> selectedFood = new ArrayList<FoodItem>();
	/**
	 * populated with food items from a specified menu
	 */
	static ArrayList<FoodItem> data;

	/**
	 * 
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.food_menu_list, container, false);
		mListView = (ListView) ret.findViewById(R.id.food_list);
		// TODO (trtucker) refer to menus by string name, get menu from model
		String meal = getMEAL();
		switch (meal) {
		case BREAKFAST:
			FoodMenuListFragment.data = getBreakfastMenuItem();
			break;
		case LUNCH:
			FoodMenuListFragment.data = getLunchMenuItem();
			break;
		case DINNER:
			FoodMenuListFragment.data = getDinnerMenuItem();
			break;
		default:
			break;
		}
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item,
				FoodMenuListFragment.data);
		mListView.setAdapter(adapter);
		return ret;
	}

	/**
	 * Populate list with breakfast items
	 * 
	 * @return
	 */
	public ArrayList<FoodItem> getBreakfastMenuItem() {
		ArrayList<FoodItem> food = new ArrayList<FoodItem>();
		FoodItem hashbrowns = new FoodItem("Hashbrown");
		hashbrowns.setImage(R.drawable.logo);
		hashbrowns.setPrice(2000);
		food.add(hashbrowns);
		for (int i = 0; i < 10; i++) {
			FoodItem fakeitem = new FoodItem("Fake Breakfast Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			fakeitem.setPrice(300);
			food.add(fakeitem);
		}
		return food;
	}

	/**
	 * Populate list with lunch items
	 * 
	 * @return
	 */
	public ArrayList<FoodItem> getLunchMenuItem() {
		ArrayList<FoodItem> food = new ArrayList<FoodItem>();
		FoodItem burger = new FoodItem("Burger");
		burger.setImage(R.drawable.logo);
		burger.setPrice(500);
		food.add(burger);
		for (int i = 0; i < 10; i++) {
			FoodItem fakeitem = new FoodItem("Fake Lunch Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			fakeitem.setPrice(300);
			food.add(fakeitem);
		}
		return food;
	}

	/**
	 * Populate list with dinner items
	 * 
	 * @return
	 */

	public ArrayList<FoodItem> getDinnerMenuItem() {
		ArrayList<FoodItem> food = new ArrayList<FoodItem>();
		FoodItem steak = new FoodItem("Steak");
		steak.setImage(R.drawable.logo);
		steak.setPrice(1000);
		food.add(steak);
		for (int i = 0; i < 10; i++) {
			FoodItem fakeitem = new FoodItem("Fake Dinner Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			fakeitem.setPrice(300);
			food.add(fakeitem);
		}
		return food;
	}

	/**
	 * set listener for list entries
	 * 
	 * @param listener
	 */
	public void setFoodMenuListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.food_list))
				.setOnItemClickListener(listener);
	}

	/**
	 * 
	 * @param listener
	 */
	public void setFoodMenuButtonListener(OnClickListener listener) {
		getView().findViewById(R.id.add_to_order_button).setOnClickListener(
				listener);
	}

	/**
	 * 
	 */
	@Override
	public void onStart() {
		setFoodMenuListListener((OnItemClickListener) mSuperListener);
		setFoodMenuButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}

	public static String getMEAL() {
		return MEAL;
	}

	public static void setMEAL(String mEAL) {
		MEAL = mEAL;
	}
}
