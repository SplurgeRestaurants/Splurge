package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class HomeScreenActivity extends Activity {
	// List
	protected ListView mListView;
	// Saves the chosen restaurant
	public static String RESTAURANT;
	// fragment for layout
	private RestaurantListFragment fragment;
	//button listener
	public OnItemClickListener listener;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 * get fragment layout and initialize listener
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.home_screen_activity);
		listener = new RestaurantListListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new RestaurantListFragment();
		ft.replace(R.id.restaurant_list_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}
	// set the listener for the fragment
	@Override
	protected void onStart() {
		fragment.setListListener(listener);
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
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
}
