package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class DiningOutListListener extends SuperListener implements
		OnItemClickListener {

	/**
	 * Create a new DiningOutListListener, designed to listen to a
	 * DiningOutListFragment.
	 * 
	 * @param wrapper
	 *            Context used for spawning a new Activity
	 */
	public DiningOutListListener(WrapperActivity wrapper) {
		super(wrapper);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// starts the FoodItemActivity
		// save position of food item from menu
		Log.e("FoodMenuListListener", "Position is " + position);
		// TODO (trtucker) tell FoodItemFragment which item to display
		mWrapper.changeFragment(new FoodItemFragment(), null);
	}

	public void notifyTakeOut(View v) {
		Toast.makeText(mWrapper, "Take Out Successful", Toast.LENGTH_SHORT)
				.show();
	}

	public void notifyDelivery(View v) {
		Toast.makeText(mWrapper, "Delivery Successful", Toast.LENGTH_SHORT)
				.show();
	}

	public void goToFoodMenu(View v) {
		mWrapper.changeFragment(new FoodMenuListFragment(),
				new FoodMenuListListener(mWrapper));
	}

	public void removeSelectedFoodItems(View v) {
		for (int i = 0; i < FoodMenuListFragment.selected.size(); i++) {
			if (FoodMenuListFragment.selected.get(i).isSelected()) {
				FoodMenuListFragment.selected.remove(i);
				i = -1;
			}
		}
		// TODO (danthai) change to a page refresh
		mWrapper.changeFragment(new DiningOutFragment(),
				new DiningOutListListener(mWrapper));
	}
}
