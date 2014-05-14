package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RestaurantListFragment extends SuperFragment {
	// List
	private ListView mListView;
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle) Populate the list with
	 * restaurants
	 */
	@SuppressWarnings("unchecked")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get layout
		View ret = inflater.inflate(R.layout.activity_home_screen, container,
				false);
		// get id for ListView
		mListView = (ListView) ret.findViewById(R.id.list);
		// Populate list with restaurant data
		List<? extends Map<String, ?>> data = (List<? extends Map<String, ?>>) GetSampleData();
		// adapter formats list entries in list
		SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
				R.layout.fragment_home_screen, new String[] { "RestaurantIcon",
						"RestaurantName" }, new int[] { R.id.RestaurantIcon,
						R.id.RestaurantName });
		mListView.setAdapter(adapter);
		return ret;
	}

	/*
	 * Get the list of restaurants
	 */
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

	// set the listener for list entries
	public void setListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.list))
				.setOnItemClickListener(listener);
	}

	@Override
	public void onStart() {
		setListListener((OnItemClickListener) mSuperListener);
		super.onStart();
	}
}
