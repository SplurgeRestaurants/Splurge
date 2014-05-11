package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View.OnClickListener;

public class RestaurantMainMenuActivity extends Activity {
	// fragment for layout
	private RestaurantMainMenuFragment fragment;
	// button listener
	public OnClickListener listener;
	// stores the chosen meal
	static String MEAL;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * Get fragment layout and initialize listener
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.restaurant_main_menu_activity);
		listener = new RestaurantMainMenuListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new RestaurantMainMenuFragment();
		ft.replace(R.id.restaurant_main_menu_fragment_place_holder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onStart()
	 * set listener for fragment
	 */
	@Override
	protected void onStart() {
		fragment.setMainMenuListener(listener);
		super.onStart();
	}
}
