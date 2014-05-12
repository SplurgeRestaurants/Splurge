package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class DiningOutActivity extends Activity {
	DiningOutFragment fragment;
	FoodMenuListListener listener;
	static ArrayList<FoodItem> selectedFood = new ArrayList<FoodItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_dining_out);
		listener = new FoodMenuListListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new DiningOutFragment();
		ft.replace(R.id.dining_out_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart() set listener for fragment
	 */
	@Override
	protected void onStart() {
		fragment.setFoodMenuListListener(listener);
		super.onStart();
	}
}
