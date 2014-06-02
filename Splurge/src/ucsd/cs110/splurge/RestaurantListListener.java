package ucsd.cs110.splurge;

import ucsd.cs110.splurge.model.OnRestaurantReadyListener;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * Listener for the RestaurantListFragment class
 * 
 */
public class RestaurantListListener extends SuperListener implements
		OnItemClickListener, OnRestaurantReadyListener {

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

	/**
	 * Display the main menu for the correct restaurant
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		TextView restaurantName = (TextView) (view
				.findViewById(R.id.RestaurantName));
		String chosenRestaurant = restaurantName.getText().toString();
		mWrapper.getModel().setRestaurantByName(chosenRestaurant, this);
	}

	@Override
	public void onRestaurantReady() {
		mWrapper.changeFragment(new RestaurantMainMenuFragment(),
				new RestaurantMainMenuListener(mWrapper));
	}
}
