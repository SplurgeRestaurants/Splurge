package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import ucsd.cs110.splurge.model.FoodMenu;
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
	 * The menu which shall be displayed.
	 */
	private FoodMenu mMenu;
	/**
	 * Store the reference to the List View
	 */
	private ListView mListView;
	/**
	 * Adapter to create list entries in ListView
	 */
	private static FoodMenuAdapter adapter;
	/**
	 * save position of the chosen food item
	 */
	public static final String FOOD_ITEM_POSITION = "0";
	public static final String ORIGIN = "where it came from";
	/**
	 * populate with selected food items
	 */
	private static ArrayList<FoodItem> selectedFood = new ArrayList<FoodItem>();
	/**
	 * populated with food items from a specified menu
	 */
	private static ArrayList<FoodItem> data;

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
		FoodMenuListFragment.data = new ArrayList<>(mMenu.getFoodList());
		clearChecked();
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item,
				FoodMenuListFragment.data);
		mListView.setAdapter(adapter);
		return ret;
	}

	/**
	 * Populate list with food items from an arbitrarily-named menu.
	 * 
	 * @param label
	 *            The title of the menu to look up.
	 * @return Array of food items from the requested menu, or an empty array if
	 *         the menu doesn't exist.
	 */
	public ArrayList<FoodItem> getArbitraryMenuItem(String label) {
		ArrayList<FoodItem> food = new ArrayList<FoodItem>();
		FoodMenu menu = getWrapperActivity().getModel().getFoodMenuByLabel(
				label);
		if (menu != null)
			food.addAll(menu.getFoodList());
		return food;
	}

	public ArrayList<FoodItem> getFoodData() {
		return data;
	}

	public ArrayList<FoodItem> getSelectedFoodData() {
		return selectedFood;
	}

	public String getMeal() {
		return mMenu.getMenuName();
	}

	public void setMealByLabel(String meal) {
		mMenu = getWrapperActivity().getModel().getFoodMenuByLabel(meal);
	}

	/**
	 * Remove checked status on all food items in data
	 */
	public void clearChecked() {
		if (data != null) {
			for (int i = 0; i < data.size(); i++) {
				data.get(i).setSelected(false);
			}
		}
	}

	public void refresh() {
		clearChecked();
		adapter.notifyDataSetChanged();
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

	public void setMeal(FoodMenu meal) {
		mMenu = meal;
	}

	public void setMealByIndex(int i) {
		mMenu = getWrapperActivity().getModel().getFoodMenuByIndex(i);
	}
}
