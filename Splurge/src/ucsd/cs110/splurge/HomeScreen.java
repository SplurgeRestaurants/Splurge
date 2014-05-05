package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class HomeScreen extends Activity {

	protected ListView mListView;
	public static String RESTAURANT;
	public OnItemClickListener listener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			String chosenRestaurant = mListView.getItemAtPosition(position)
					.toString();
			chosenRestaurant = chosenRestaurant.substring(16, chosenRestaurant.indexOf(","));
			// RESTAURANT = chosenRestaurant;
			// RestaurantView.mTextView.setText(chosenRestaurant);
			Log.e("ListView", "restuarant: " + chosenRestaurant);
			Intent intent = new Intent(HomeScreen.this, RestaurantView.class);
			intent.putExtra(RESTAURANT, chosenRestaurant);
			//add intent for restaurant id?
			startActivity(intent);
		}
	};

	@SuppressWarnings({ "unchecked" })
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		mListView = (ListView) findViewById(R.id.list);
		List<? extends Map<String, ?>> data = (List<? extends Map<String, ?>>) GetSampleData();
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.fragment_home_screen, new String[] { "RestaurantIcon",
						"RestaurantName" }, new int[] { R.id.RestaurantIcon,
						R.id.RestaurantName });

		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(listener);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<Map> GetSampleData() {
		List<Map> list = new ArrayList<Map>();

		Map map = new HashMap();
		map.put("RestaurantIcon", R.drawable.logo);
		map.put("RestaurantName", "Bistro");
		list.add(map);

		for (int i = 0; i < 10; i++) {
			map = new HashMap();
			map.put("RestaurantIcon", R.drawable.ic_launcher);
			map.put("RestaurantName", "Fake Restaurant" + i);
			list.add(map);
		}
		return list;
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_home_screen,
					container, false);
			return rootView;
		}
	}

}
