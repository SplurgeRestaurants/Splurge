package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ucsd.cs110.splurge.HomeScreenActivity.PlaceholderFragment;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FoodMenuActivity extends Activity {

	protected ListView mListView;
	public OnItemClickListener listener = new OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			String chosenRestaurant = mListView.getItemAtPosition(position)
					.toString();
			chosenRestaurant = chosenRestaurant.substring(16,
					chosenRestaurant.indexOf(","));
			// RESTAURANT = chosenRestaurant;
			// RestaurantMainMenuActivity.mTextView.setText(chosenRestaurant);
			Log.e("ListView", "restuarant: " + chosenRestaurant);
			// Intent intent = new Intent(MenuMenuActivity.this,
			// RestaurantMainMenuActivity.class);
			// add intent for restaurant id?
			// startActivity(intent);
		}
	};

	@SuppressWarnings("unchecked")
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);
		mListView = (ListView) findViewById(R.id.list);
		Intent intent = getIntent();
		int category = 0;
		category = intent.getIntExtra(RestaurantMainMenuActivity.CATEGORY,
				category);
		List<? extends Map<String, ?>> data;
		switch (category) {
		case 0:
			data = (List<? extends Map<String, ?>>) GetBreakfastMenuItem();
			break;
		case 1:
			data = (List<? extends Map<String, ?>>) GetLunchMenuItem();
			break;
		case 2:
			data = (List<? extends Map<String, ?>>) GetDinnerMenuItem();
			break;
		default:
			data = null;
			break;
		}
		SimpleAdapter adapter = new SimpleAdapter(this, data,
				R.layout.menu_item, new String[] { "MenuIcon", "MenuName" },
				new int[] { R.id.MenuIcon, R.id.MenuName });
		mListView.setAdapter(adapter);
		mListView.setOnItemClickListener(listener);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<Map> GetBreakfastMenuItem() {
		List<Map> list = new ArrayList<Map>();

		Map map = new HashMap();
		map.put("MenuIcon", R.drawable.logo);
		map.put("MenuName", "Hashbrowns");
		list.add(map);

		for (int i = 0; i < 10; i++) {
			map = new HashMap();
			map.put("MenuIcon", R.drawable.ic_launcher);
			map.put("MenuName", "Fake Breakfast Item" + i);
			list.add(map);
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<Map> GetLunchMenuItem() {
		List<Map> list = new ArrayList<Map>();

		Map map = new HashMap();
		map.put("MenuIcon", R.drawable.logo);
		map.put("MenuName", "Burger");
		list.add(map);

		for (int i = 0; i < 10; i++) {
			map = new HashMap();
			map.put("MenuIcon", R.drawable.ic_launcher);
			map.put("MenuName", "Fake Lunch Item" + i);
			list.add(map);
		}
		return list;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	List<Map> GetDinnerMenuItem() {
		List<Map> list = new ArrayList<Map>();

		Map map = new HashMap();
		map.put("MenuIcon", R.drawable.logo);
		map.put("MenuName", "Steak");
		list.add(map);

		for (int i = 0; i < 10; i++) {
			map = new HashMap();
			map.put("MenuIcon", R.drawable.ic_launcher);
			map.put("MenuName", "Fake Dinner Item" + i);
			list.add(map);
		}
		return list;
	}
}
