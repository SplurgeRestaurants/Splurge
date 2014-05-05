package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RestaurantMainMenuActivity extends Activity implements OnClickListener {
	TextView mTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_restaurant_view);
		setContentView(R.layout.fragment_main_vertical);
		Intent intent = getIntent();
		String chosenRestaurant = intent.getStringExtra(HomeScreenActivity.RESTAURANT);
		mTextView = (TextView) findViewById(R.id.textView1);
		mTextView.setText(chosenRestaurant);
		View locationButton = findViewById(R.id.location);
		locationButton.setOnClickListener(this);
		View menuButtonView = findViewById(R.id.menu);
		menuButtonView.setOnClickListener(this);
		View reserveButton = findViewById(R.id.reserve);
		reserveButton.setOnClickListener(this);
		View diningOutButton = findViewById(R.id.diningout);
		diningOutButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.restaurant_view, menu);
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
			View rootView = inflater.inflate(R.layout.activity_restaurant_view,
					container, false);
			return rootView;
		}
	}

	public static class LocationFragment extends Fragment {
		public LocationFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.map,
					container, false);
			return rootView;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.location:
			getFragmentManager().beginTransaction()
					.add(R.id.container, new LocationFragment()).commit();
			break;
		// More buttons go here (if any) ...
		case R.id.reserve:
			openReserveDialog();
			break;
		case R.id.menu:
			openMenuDialog();
			break;
		case R.id.diningout:
			openDiningOutDialog();
			break;
		}
	}

	private void openReserveDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.new_reserv_title)
				.setItems(R.array.reservations,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								// do something
							}
						}).show();
	}

	private void openMenuDialog() {
		new AlertDialog.Builder(this).setTitle(R.string.menu_title)
				.setItems(R.array.menus, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialoginterface, int i) {
						// do something
					}
				}).show();
	}

	private void openDiningOutDialog() {
		new AlertDialog.Builder(this)
				.setTitle(R.string.dining_out_title)
				.setItems(R.array.dining_out,
						new DialogInterface.OnClickListener() {
							public void onClick(
									DialogInterface dialoginterface, int i) {
								switch(i){
								case 0:
									getFragmentManager().beginTransaction()
									.add(R.id.container, new LocationFragment()).commit();
									//take out
									break;
								case 1:
									getFragmentManager().beginTransaction()
									.add(R.id.container, new PlaceholderFragment()).commit();
									//delivery
									break;
								default:
									break;
								}
							}
						}).show();
	}
}
