package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Fragment;
import android.app.FragmentTransaction;
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

public class RestaurantMainMenuActivity extends Activity implements
		OnClickListener {
	TextView mTextView;
	static String CATEGORY;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.activity_restaurant_view);
		setContentView(R.layout.main_menu);
		Intent intent = getIntent();
		String chosenRestaurant = intent
				.getStringExtra(HomeScreenActivity.RESTAURANT);
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

	public static class LocationFragment extends Fragment {
		public LocationFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.map, container, false);
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
			openFoodMenuDialog();
			break;
		case R.id.diningout:
			openDiningOutDialog();
			break;
		}
	}

	private void openReserveDialog() {
		Builder dialog = new AlertDialog.Builder(this);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialoginterface, int i) {
				// do something
			}
		};
		dialog.setTitle(R.string.new_reserv_title);
		dialog.setItems(R.array.reservations, diaIn);
		dialog.show();
	}

	private void openFoodMenuDialog() {
		Builder dialog = new AlertDialog.Builder(this);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialoginterface, int i) {
				Intent intent = new Intent(RestaurantMainMenuActivity.this,
						FoodMenuActivity.class);
				intent.putExtra(CATEGORY, i);
				startActivity(intent);
			}
		};
		dialog.setTitle(R.string.menu_title);
		dialog.setItems(R.array.menus, diaIn);
		dialog.show();
	}

	private void openDiningOutDialog() {
		Builder dialog = new AlertDialog.Builder(this);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialoginterface, int i) {
				FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				switch (i) {
				case 0:
					ft.add(R.id.container, new LocationFragment());
					ft.commit();
					// take out
					break;
				case 1:
					ft.add(R.id.container, new LocationFragment());
					ft.commit();
					// delivery
					break;
				default:
					break;
				}
			}
		};
		dialog.setTitle(R.string.dining_out_title);
		dialog.setItems(R.array.dining_out, diaIn);
		dialog.show();
	}
}
