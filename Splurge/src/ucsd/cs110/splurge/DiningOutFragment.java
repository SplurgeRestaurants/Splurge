package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DiningOutFragment extends SuperFragment {

	private ListView mListView;
	// Adapter to create list entries in ListView
	private FoodMenuAdapter adapter;
	private ArrayList<FoodItem> mSelectedFood;
	private ArrayList<FoodItem> mSelected;
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
		return mSelected;
	}

	/*
	 * set listener for list entries
	 */
	public void setFoodMenuListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.dining_out_list))
				.setOnItemClickListener(listener);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		setFoodMenuListListener((OnItemClickListener) mSuperListener);
		super.onSaveInstanceState(outState);
	}
}
