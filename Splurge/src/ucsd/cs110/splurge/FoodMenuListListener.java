package ucsd.cs110.splurge;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Listener class for FoodMenuListFragment
 * 
 */
public class FoodMenuListListener extends SuperListener implements
		OnItemClickListener, OnClickListener {

	/**
	 * Create a new FoodMenuListListener, designed to listen to a
	 * FoodMenuListFragment.
	 * 
	 * @param wrapper
	 *            Context used for spawning a new Activity
	 */
	public FoodMenuListListener(WrapperActivity wrapper) {
		super(wrapper);
	}

	/**
	 * Display the correct food menu item on the list
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Log.e("FoodMenuListListener", "Position is " + position);
		// TODO (trtucker) remove null value, create FoodItemListener
		Intent intent = new Intent();
		intent.putExtra(FoodMenuListFragment.FOOD_ITEM_POSITION, position);
		mWrapper.setIntent(intent);
		mWrapper.changeFragment(new FoodItemFragment(), null);
	}

	/**
	 * Call the correct method for the button
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.add_to_order_button:
			getSelected();
		default:
			break;
		}
	}

	/**
	 * Scan the list for selected items and add them to the selectedFood array.
	 * Go to the DiningOut page
	 */
	public void getSelected() {
		for (int i = 0; i < FoodMenuListFragment.data.size(); i++) {
			if (FoodMenuListFragment.data.get(i).isSelected()) {
				Log.e("Selected", FoodMenuListFragment.data.get(i).getName());
				FoodMenuListFragment.selectedFood.add(FoodMenuListFragment.data
						.get(i));
			}
		}
		for (int i = 0; i < FoodMenuListFragment.selectedFood.size(); i++) {
			FoodMenuListFragment.selectedFood.get(i).setSelected(false);
		}
		// TODO (dqthai) change to view refresh rather than new view
		mWrapper.changeFragment(new DiningOutFragment(),
				new DiningOutListListener(mWrapper));
	}
}