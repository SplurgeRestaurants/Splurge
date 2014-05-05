package ucsd.cs110.splurge;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

public class RestaurantListListener implements OnItemClickListener {

	Context mContext;

	/**
	 * Create a new RestaurantListListener, designed to listen to a
	 * RestaurantListFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public RestaurantListListener(Context context) {
		mContext = context;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		String chosenRestaurant = parent.getItemAtPosition(position).toString();
		chosenRestaurant = chosenRestaurant.substring(16,
				chosenRestaurant.indexOf(","));
		Log.e("ListView", "restuarant: " + chosenRestaurant);
		Intent intent = new Intent(mContext, RestaurantMainMenuActivity.class);
		intent.putExtra(HomeScreenActivity.RESTAURANT, chosenRestaurant);
		mContext.startActivity(intent);
	}
}
