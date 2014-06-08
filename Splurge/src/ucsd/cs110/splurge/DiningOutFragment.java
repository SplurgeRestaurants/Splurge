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
import android.widget.TextView;
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
	private static ArrayList<FoodItem> mSelectedFood = new ArrayList<FoodItem>();

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
		mSelectedFood.addAll(frag.getSelectedFoodData());
		frag.getSelectedFoodData().clear();
		if (!mSelectedFood.isEmpty()) {
			ret.findViewById(R.id.empty).setVisibility(View.GONE);
			mListView.setVisibility(View.VISIBLE);
		} else {
			ret.findViewById(R.id.dining_out_list_background).setVisibility(
					View.GONE);
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

	/**
	 * Get the array of selected food items
	 * 
	 * @return Array of selected food items
	 */
	public ArrayList<FoodItem> getSelectedFood() {
		return mSelectedFood;
	}

	/**
	 * Reload the ListView when the contents of the list has been changed
	 */

	public void refresh() {
		try {
			adapter.notifyDataSetChanged();
			updateCost();
		} catch (Exception e) {
			Toast.makeText(getWrapperActivity(), "You have no items",
					Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * Update the total cost of the food items when more items are being added
	 */
	private void updateCost() {
		double totalCost = 0;
		for (FoodItem item : getSelectedFood()) {
			totalCost += item.getPrice();
		}
		setTotal(String.format("Total: $%.2f", totalCost));
	}

	/**
	 * Set the total price
	 * 
	 * @param total
	 *            Price to be set
	 */
	public void setTotal(String total) {
		((TextView) (getView().findViewById(R.id.price_sum))).setText(total);
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
		((DiningOutListListener) mSuperListener).setListened(this);
		updateCost();
		super.onStart();
	}
}
