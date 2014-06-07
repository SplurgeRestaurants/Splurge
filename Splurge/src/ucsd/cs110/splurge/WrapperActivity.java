package ucsd.cs110.splurge;

import ucsd.cs110.splurge.model.RestaurantModel;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Activity containing all Fragments for viewing. This Activty encapsulates all
 * operations of the app, from switching between Views (Fragments), assigning
 * Controllers (Listeners), and storing the Model (RestaurantModel).
 */
public class WrapperActivity extends Activity {

	/**
	 * The model. Contains business logic and data as per Model-View-Controller.
	 */
	private RestaurantModel mModel;
	static boolean hideMenu = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrapper);
		mModel = new RestaurantModel();
		getActionBar().hide();
		SuperFragment f = new RestaurantListFragment();
		SuperListener l = new RestaurantListListener(this);
		f.setSuperListener(l);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, f);
		ft.commit();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.restaurant_list:
			changeFragment(new RestaurantListFragment(),
					new RestaurantListListener(this));
			return true;
		case R.id.main_menu:
			changeFragment(new RestaurantMainMenuFragment(),
					new RestaurantMainMenuListener(this));
			return true;
		case R.id.information:
			changeFragment(new InformationFragment(), new InformationListener(
					this));
			return true;
		case R.id.reservation:
			changeFragment(new CalendarViewFragment(),
					new CalenderViewFragmentListener(this));
			return true;
		case R.id.dining_out:
			changeFragment(new DiningOutFragment(), new DiningOutListListener(
					this));
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onMenuOpened(int featureId, Menu menu) {
		for (int i = 0; i < menu.size(); i++)
			menu.getItem(i).setVisible(!hideMenu);
		invalidateOptionsMenu();
		return true;
	}

	/**
	 * Replaces the current fragment with the fragment passed in by argument and
	 * attaches the listener.
	 * 
	 * @param f
	 *            The new fragment to slot in.
	 * @param l
	 *            The listener for the new fragment. This should be the
	 *            appropriate subclass for the given fragment, and will yield
	 *            undefined behavior otherwise.
	 */
	public void changeFragment(SuperFragment f, SuperListener l) {
		hideMenu = false;
		f.setSuperListener(l);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.container, f);
		ft.addToBackStack(null);
		ft.commit();
	}

	/**
	 * Retrieves the model for business logic and data.
	 * 
	 * @return The current Model.
	 */
	public RestaurantModel getModel() {
		return mModel;
	}
}
