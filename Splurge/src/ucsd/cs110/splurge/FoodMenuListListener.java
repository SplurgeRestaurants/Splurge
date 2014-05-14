package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FoodMenuListListener extends SuperListener implements
		OnItemClickListener {

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

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// save position of food item from menu
		Log.e("FoodMenuListListener", "Position is " + position);
		// TODO (trtucker) remove null value, create FoodItemListener
		mWrapper.changeFragment(new FoodItemFragment(), null);
	}

	public void getSelected(View view) {
		for (int i = 0; i < FoodMenuListFragment.data.size(); i++) {
			if (FoodMenuListFragment.data.get(i).isSelected()) {
				Log.e("Selected", FoodMenuListFragment.data.get(i).getName());
				FoodMenuListFragment.selected.add(FoodMenuListFragment.data
						.get(i));
			}
		}
		for (int i = 0; i < FoodMenuListFragment.selected.size(); i++) {
			FoodMenuListFragment.selected.get(i).setSelected(false);
		}
		// TODO (dqthai) change to view refresh rather than new view
		mWrapper.changeFragment(new DiningOutFragment(),
				new DiningOutListListener(mWrapper));
	}
}