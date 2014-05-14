package ucsd.cs110.splurge;

import ucsd.cs110.splurge.model.RestaurantModel;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
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
	private SuperFragment mCurrentFragment;
	private SuperListener mCurrentListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrapper);
		mModel = new RestaurantModel();
		changeFragment(new RestaurantListFragment(),
				new RestaurantListListener(this));
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Applies the current listener to the current fragment. This should be
	 * called from SuperFragment in its <code>onStart()</code> method.
	 */
	public void applyListener() {
		mCurrentFragment.setSuperListener(mCurrentListener);
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
		mCurrentFragment = f;
		mCurrentListener = l;
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
