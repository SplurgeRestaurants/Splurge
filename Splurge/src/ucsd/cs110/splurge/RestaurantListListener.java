package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class RestaurantListListener extends SuperListener implements
		OnItemClickListener {

	/**
	 * Create a new RestaurantListListener, designed to listen to a
	 * RestaurantListFragment.
	 * 
	 * @param wrapper
	 *            Context used for spawning a new Activity
	 */
	public RestaurantListListener(WrapperActivity wrapper) {
		super(wrapper);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.AdapterView.OnItemClickListener#onItemClick(android.widget
	 * .AdapterView, android.view.View, int, long) Saves the chosen restaurant
	 * in the intent and starts RestaurantMainMenuActivity
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView restaurantName = (TextView) (view.findViewById(R.id.RestaurantName));
		String chosenRestaurant = restaurantName.getText().toString();
		Log.e("ListView", "restuarant: " + chosenRestaurant);
		mWrapper.getActionBar().setTitle(chosenRestaurant);
		mWrapper.changeFragment(new RestaurantMainMenuFragment(),
				new RestaurantMainMenuListener(mWrapper));
	}
}
