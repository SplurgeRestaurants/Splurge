package ucsd.cs110.splurge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FoodMenuListListener implements OnItemClickListener {

	Context mContext;

	/**
	 * Create a new RestaurantListListener, designed to listen to a
	 * RestaurantListFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public FoodMenuListListener(Context context) {
		mContext = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		//TODO
	}
}