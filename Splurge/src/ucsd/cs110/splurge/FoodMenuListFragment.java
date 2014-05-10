package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class FoodMenuListFragment extends Fragment {

	ListView mListView;

	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.food_menu_list, container,
				false);
		mListView = (ListView) ret.findViewById(R.id.food_list);
		Intent intent = getActivity().getIntent();
		int category = 0;
		category = intent.getIntExtra(RestaurantMainMenuActivity.MEAL,
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
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
				R.layout.menu_item, new String[] { "MenuIcon", "MenuName" },
				new int[] { R.id.MenuIcon, R.id.MenuName });
		mListView.setAdapter(adapter);
		return ret;
	}

	/*
	 * Populate list with breakfast items
	 */
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
	/*
	 * Populate list with lunch items
	 */
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
	/*
	 * Populate list with dinner items
	 */
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

	public void setListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.food_list))
				.setOnItemClickListener(listener);
	}
}
