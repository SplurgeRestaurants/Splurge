package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;

public class WrapperActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wrapper);
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
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.restaurant_list_fragment_placeholder, f);
		ft.commit();
		f.setSuperListener(l);
	}
}
