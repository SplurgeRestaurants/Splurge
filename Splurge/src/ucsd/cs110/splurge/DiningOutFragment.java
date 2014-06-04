package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Fragment that displays the Dining out features
 * 
 */
public class DiningOutFragment extends SuperFragment {
	/**
	 * Reference to the ListView
	 */
	private ListView mListView;
	/**
	 * Adapter to create list entries in ListView
	 */
	private FoodMenuAdapter adapter;
	/**
	 * List of food items that were selected from the food menu
	 */
	private static ArrayList<FoodItem> mSelectedFood;

	/**
	 * Display the layout and populate the list with food items, if any
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.dining_out, container, false);
		mListView = (ListView) ret.findViewById(R.id.dining_out_list);
		FoodMenuListFragment frag = new FoodMenuListFragment();
		mSelectedFood = frag.getSelectedFoodData();

		if (!mSelectedFood.isEmpty()) {
			ret.findViewById(R.id.empty).setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
		} else {
			ret.findViewById(R.id.dining_out_list_background).setVisibility(
					View.GONE);
			// mListView.setVisibility(View.GONE);
		}
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item,
				mSelectedFood);
		mListView.setAdapter(adapter);
		return (ret);
	}

	/**
	 * Set the array with an array with selected food items
	 * 
	 * @param selected
	 *            Array to be set.
	 */
	public void setSelected(ArrayList<FoodItem> selected) {
		DiningOutFragment.mSelectedFood = selected;
	}

	public ArrayList<FoodItem> getSelectedFood() {
		return mSelectedFood;
	}

	/**
	 * Reload the ListView when the contents of the list has been changed
	 */

	public void refresh() {
		try {
			adapter.notifyDataSetChanged();
		} catch (Exception e) {
			Toast.makeText(getWrapperActivity(), "You have no items",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Set listener for list entries
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setDiningOutListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.dining_out_list))
				.setOnItemClickListener(listener);
	}

	/**
	 * Set listener for the buttons on the page
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setDiningOutButtonListener(OnClickListener listener) {
		getView().findViewById(R.id.add_to_order).setOnClickListener(listener);
		getView().findViewById(R.id.delivery).setOnClickListener(listener);
		getView().findViewById(R.id.take_out).setOnClickListener(listener);
		getView().findViewById(R.id.remove_items).setOnClickListener(listener);
	}

	/**
	 * set the mSuperListener to the buttons and list entries
	 */
	@Override
	public void onStart() {
		setDiningOutListListener((OnItemClickListener) mSuperListener);
		setDiningOutButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}
