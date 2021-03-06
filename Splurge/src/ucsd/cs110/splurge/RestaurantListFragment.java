package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Collection;

import ucsd.cs110.splurge.connectivity.tasks.RestaurantListRequestListener;
import ucsd.cs110.splurge.model.RestaurantListing;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

/**
 * Fragment to display the list of restaurants
 * 
 */
public class RestaurantListFragment extends SuperFragment implements
		RestaurantListRequestListener {
	/**
	 * Reference to the ListView
	 */
	private ListView mListView;

	/**
	 * Populate the list with restaurants
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.restaurant_list, container, false);
		mListView = (ListView) ret.findViewById(R.id.list);
		ArrayList<RestaurantListing> data = new ArrayList<RestaurantListing>(
				getWrapperActivity().getModel().getAvailableRestaurantNames(
						this, getActivity()));
		RestaurantListAdapter adapter = new RestaurantListAdapter(
				getActivity(), R.layout.restaurant_list_entry, data);
		mListView.setAdapter(adapter);
		return ret;
	}

	/**
	 * Set listener for list entries
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.list))
				.setOnItemClickListener(listener);
	}

	/**
	 * Set the mSuperListener to the list entries
	 */
	@Override
	public void onStart() {
		setListListener((OnItemClickListener) mSuperListener);
		DiningOutFragment frag = new DiningOutFragment();
		try {
			frag.getSelectedFood().clear();
		} catch (Exception e) {

		}
		super.onStart();
	}

	@Override
	public void receiveRetaurantList(Collection<RestaurantListing> list) {
		Log.i("Splurge", "Received a restaurant list. Size is: " + list.size());
		ArrayList<RestaurantListing> data = new ArrayList<RestaurantListing>(
				list);
		RestaurantListAdapter adapter = new RestaurantListAdapter(
				getActivity(), R.layout.restaurant_list_entry, data);
		mListView.setAdapter(adapter);
	}

	@Override
	public void setTitle() {
		getActivity().setTitle(R.string.app_name);
	}
}
