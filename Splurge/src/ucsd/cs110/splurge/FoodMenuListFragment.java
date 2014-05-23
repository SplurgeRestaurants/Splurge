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
 * Display the list of food items from the appropriate menu
 * 
 */
public class FoodMenuListFragment extends SuperFragment {
	/**
	 * Enumerator for MEAL
	 */
	private static final String DINNER = "Dinner";
	/**
	 * Enumerator for MEAL
	 */
	private static final String LUNCH = "Lunch";
	/**
	 * Enumerator for MEAL
	 */
	private static final String BREAKFAST = "Breakfast";
	/**
	 * Store which meal was chosen
	 */
	private static String MEAL;
	/**
	 * Store the reference to the listview
	 */
	private ListView mListView;
	/**
	 * Adapter to create list entries in ListView
	 */
	FoodMenuAdapter adapter;
	/**
	 * save position of the chosen food item
	 */
	static String FOOD_ITEM_POSITION = "0";
	/**
	 * populate with selected food items
	 */
	static ArrayList<FoodItem> selectedFood = new ArrayList<FoodItem>();
	/**
	 * populated with food items from a specified menu
	 */
	static ArrayList<FoodItem> data;

	/**
	 * Display the correct menu
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.food_menu_list, container, false);
		mListView = (ListView) ret.findViewById(R.id.food_list);
		// TODO (trtucker) refer to menus by string name, get menu from model
		String meal = getMEAL(); // (dqthai) my temp fix so I can get screen
									// shots for artifact
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
	 * @return Array of food items from the breakfast menu.
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
			fakeitem.setPrice(300 + (i *30.3));
			food.add(fakeitem);
		}
		return food;
	}

	/**
	 * Populate list with lunch items
	 * 
	 * @return Array of food items from the lunch menu.
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
			fakeitem.setPrice(300 + (i *30.3));
			food.add(fakeitem);
		}
		return food;
	}

	/**
	 * Populate list with dinner items
	 * 
	 * @return Array of food items from the dinner menu.
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
			fakeitem.setPrice(300 + (i *30.3));
			food.add(fakeitem);
		}
		return food;
	}

	/**
	 * set listener for list entries
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setFoodMenuListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.food_list))
				.setOnItemClickListener(listener);
	}

	/**
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setFoodMenuButtonListener(OnClickListener listener) {
		getView().findViewById(R.id.add_to_order_button).setOnClickListener(
				listener);
	}

	/**
	 * Set mSuperListener to the buttons and list entries
	 */
	@Override
	public void onStart() {
		setFoodMenuListListener((OnItemClickListener) mSuperListener);
		setFoodMenuButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}

	/**
	 * Get meal
	 * 
	 * @return String of meal
	 */
	public static String getMEAL() {
		return MEAL;
	}

	/**
	 * Set meal
	 * 
	 * @param mEAL
	 *            Meal to be set
	 */
	public static void setMEAL(String mEAL) {
		MEAL = mEAL;
	}
}
