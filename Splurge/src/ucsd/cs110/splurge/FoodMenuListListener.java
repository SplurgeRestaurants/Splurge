package ucsd.cs110.splurge;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		default:
			getSelected();
			break;
		}
	}

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