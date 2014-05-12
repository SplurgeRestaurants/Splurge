package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class DiningOutFragment extends Fragment {

	ListView mListView;
	// Adapter to create list entries in ListView
	FoodMenuAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get layout
		View ret = inflater.inflate(R.layout.dining_out, container, false);
		// get id for ListView
		mListView = (ListView) ret.findViewById(R.id.dining_out_list);
		// get data
		DiningOutActivity.selectedFood = getFoodItemsSelected();
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item,
				DiningOutActivity.selectedFood);
		mListView.setAdapter(adapter);
		return (ret);
	}

	public ArrayList<FoodItem> getFoodItemsSelected() {
		return FoodMenuActivity.selected;
	}

	/*
	 * set listener for list entries
	 */
	public void setFoodMenuListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.dining_out_list))
				.setOnItemClickListener(listener);
	}
}
