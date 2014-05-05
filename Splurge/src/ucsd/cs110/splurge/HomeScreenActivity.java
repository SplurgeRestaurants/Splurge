package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
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

public class HomeScreenActivity extends Activity {

	protected ListView mListView;
	public static String RESTAURANT;
	private RestaurantListFragment fragment;

	public OnItemClickListener listener;

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
