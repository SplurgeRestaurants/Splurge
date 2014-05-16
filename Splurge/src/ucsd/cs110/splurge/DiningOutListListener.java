package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class DiningOutListListener extends SuperListener implements
		OnItemClickListener, OnClickListener {

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.delivery:
			notifyDelivery();
			break;
		case R.id.add_to_order:
			goToFoodMenu();
			break;
		case R.id.take_out:
			notifyTakeOut();
			break;
		case R.id.remove_items:
			removeSelectedFoodItems();
			break;
		default:
			break;
		}

	}

	public void notifyTakeOut() {
		Toast.makeText(mWrapper, "Take Out Successful", Toast.LENGTH_SHORT)
				.show();
	}

	public void notifyDelivery() {
		Toast.makeText(mWrapper, "Delivery Successful", Toast.LENGTH_SHORT)
				.show();
	}

	public void goToFoodMenu() {
		mWrapper.changeFragment(new FoodMenuListFragment(),
				new FoodMenuListListener(mWrapper));
	}

	public void removeSelectedFoodItems() {
		for (int i = 0; i < FoodMenuListFragment.selectedFood.size(); i++) {
			if (FoodMenuListFragment.selectedFood.get(i).isSelected()) {
				FoodMenuListFragment.selectedFood.remove(i);
				i = -1;
			}
		}
		// TODO (danthai) change to a page refresh
		mWrapper.changeFragment(new DiningOutFragment(),
				new DiningOutListListener(mWrapper));
	}
}
