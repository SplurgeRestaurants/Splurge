package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;

public class FoodMenuActivity extends Activity {
	// fragment for layout
	private FoodMenuListFragment fragment;
	// button listener
	public OnItemClickListener listener;
	// populated with food items from a specified menu
	static ArrayList<FoodItem> data;
	// save position of the chosen food item
	static String FOOD_ITEM_POSITION;
	// populate with selected food items
	static ArrayList<FoodItem> selected = new ArrayList<FoodItem>();

	/*
	 * Get layout from fragment Initialize listener
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.food_menu_activity);
		listener = new FoodMenuListListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new FoodMenuListFragment();
		ft.replace(R.id.food_menu_list_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}

	/*
	 * set listener for the fragment
	 */
	@Override
	protected void onStart() {
		fragment.setListListener(listener);
		super.onStart();
	}

	/*
	 * Get the food items that were selected from the menu Called when user
	 * presses add to order
	 */
	public void getSelected(View view) {
		for (int i = 0; i < data.size(); i++) {
			if (data.get(i).isSelected()) {
				Log.e("Selected", data.get(i).getName());
				selected.add(data.get(i));
			}
		}
		for (int i = 0; i < selected.size(); i++) {
			selected.get(i).setSelected(false);
		}
		Intent intent = new Intent(this, DiningOutActivity.class);
		startActivity(intent);
	}
}
