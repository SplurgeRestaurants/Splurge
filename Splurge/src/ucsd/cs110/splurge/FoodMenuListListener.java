package ucsd.cs110.splurge;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class FoodMenuListListener implements OnItemClickListener{

	Context mContext;

	/**
	 * Create a new FoodMenuListListener, designed to listen to a
	 * FoodMenuListFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public FoodMenuListListener(Context context) {
		mContext = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		//TODO go to FoodItemActivity
		Log.e("FoodMenuListListener", "dang this works");
	}

}