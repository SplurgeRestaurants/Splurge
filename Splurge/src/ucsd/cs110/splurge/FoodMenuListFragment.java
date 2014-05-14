package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FoodMenuListFragment extends SuperFragment {
	private static final int DINNER = 2;
	private static final int LUNCH = 1;
	private static final int BREAKFAST = 0;
	// List
	ListView mListView;
	// Adapter to create list entries in ListView
	FoodMenuAdapter adapter;
	static ArrayList<FoodItem> selectedFood = new ArrayList<FoodItem>();
	// save position of the chosen food item
	static String FOOD_ITEM_POSITION;
	// populate with selected food items
	static ArrayList<FoodItem> selected = new ArrayList<FoodItem>();
	// populated with food items from a specified menu
	static ArrayList<FoodItem> data;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get layout
		View ret = inflater.inflate(R.layout.food_menu_list, container, false);
		// get id for ListView
		mListView = (ListView) ret.findViewById(R.id.food_list);
		// get the specified meal option
		// TODO (trtucker) refer to menus by string name, get menu from model
		int meal = 0;
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
			FoodMenuListFragment.data = null;
			break;
		}
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item,
				FoodMenuListFragment.data);
		mListView.setAdapter(adapter);
		return ret;
	}

	/*
	 * Populate list with breakfast items
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

	/*
	 * Populate list with lunch items
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

	/*
	 * Populate list with dinner items
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

	// set listener for list entries
	public void setListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.food_list))
				.setOnItemClickListener(listener);
	}

	@Override
	public void setSuperListener(SuperListener l) {
		setListListener((OnItemClickListener) l);
	}
}
