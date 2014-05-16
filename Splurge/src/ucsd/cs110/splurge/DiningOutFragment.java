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

/**
 * 
 * @author Dan Thai
 * 
 */
public class DiningOutFragment extends SuperFragment {
	/**
	 * 
	 */
	private ListView mListView;
	/**
	 * Adapter to create list entries in ListView
	 */
	private FoodMenuAdapter adapter;
	private ArrayList<FoodItem> mSelectedFood = new ArrayList<FoodItem>();

	/**
 * 
 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get layout
		View ret = inflater.inflate(R.layout.dining_out, container, false);
		// get id for ListView
		mListView = (ListView) ret.findViewById(R.id.dining_out_list);
		// get data
		mSelectedFood = getFoodItemsSelected();
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item,
				mSelectedFood);
		mListView.setAdapter(adapter);
		return (ret);
	}

	public ArrayList<FoodItem> getFoodItemsSelected() {
		return FoodMenuListFragment.selectedFood;
	}

	/**
	 * set listener for list entries
	 * 
	 * @param listener
	 */
	public void setFoodMenuListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.dining_out_list))
				.setOnItemClickListener(listener);
	}

	/**
	 * 
	 * @param listener
	 */
	public void setDiningOutButtonListener(OnClickListener listener) {
		getView().findViewById(R.id.add_to_order).setOnClickListener(listener);
		getView().findViewById(R.id.delivery).setOnClickListener(listener);
		getView().findViewById(R.id.take_out).setOnClickListener(listener);
		getView().findViewById(R.id.remove_items).setOnClickListener(listener);
	}

	/**
	 * 
	 */
	@Override
	public void onStart() {
		setFoodMenuListListener((OnItemClickListener) mSuperListener);
		setDiningOutButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}
