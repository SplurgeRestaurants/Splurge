package ucsd.cs110.splurge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class DiningOutListListener implements OnItemClickListener {
	Context mContext;

	/**
	 * Create a new DiningOutListListener, designed to listen to a
	 * DiningOutListFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public DiningOutListListener(Context context) {
		mContext = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// statrts the FoodItemActivity
		// Log.e("FoodMenuListListener", "dang this works");
		Intent intent = new Intent(mContext, FoodItemActivity.class);
		// save position of food item from menu
		Log.e("FoodMenuListListener", "Position is " + position);
		intent.putExtra(FoodMenuActivity.FOOD_ITEM_POSITION, position);
		mContext.startActivity(intent);
	}
}
