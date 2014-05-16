package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.Restaurant;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

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
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.restaurant_list, container, false);
		mListView = (ListView) ret.findViewById(R.id.list);
		ArrayList<Restaurant> data = GetSampleData();
		// SimpleAdapter adapter = new SimpleAdapter(getActivity(), data,
		// R.layout.restaurant_list_entry, new String[] {
		// "RestaurantIcon", "RestaurantName" }, new int[] {
		// R.id.RestaurantIcon, R.id.RestaurantName });
		RestaurantListAdapter adapter = new RestaurantListAdapter(
				getActivity(), R.layout.restaurant_list_entry, data);
		mListView.setAdapter(adapter);
		return ret;
	}

	/*
	 * Get the list of restaurants
	 */
	ArrayList<Restaurant> GetSampleData() {
		ArrayList<Restaurant> ret = new ArrayList<Restaurant>();

		Restaurant bistro = new Restaurant("Bistro");
		bistro.setImage(R.drawable.logo);
		ret.add(bistro);

		for (int i = 0; i < 10; i++) {
			Restaurant res = new Restaurant("Fake Restaurant " + i);
			res.setImage(R.drawable.ic_launcher);
			ret.add(res);
		}
		return ret;
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
