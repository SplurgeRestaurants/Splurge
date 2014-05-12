package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

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

	public void goToFoodMenu(View v) {
		Intent intent = new Intent(this, FoodMenuActivity.class);
		startActivity(intent);
	}

	public void removeSelectedFoodItems(View v) {
		for (int i = 0; i < FoodMenuActivity.selected.size(); i++) {
			if (FoodMenuActivity.selected.get(i).isSelected()) {
				FoodMenuActivity.selected.remove(i);
				i = -1;
			}
		}
		Intent intent = new Intent(this, DiningOutActivity.class);
		startActivity(intent);
	}

	public void notifyTakeOut(View v) {
		Toast toast = Toast.makeText(this, "Take Out Successful",
				Toast.LENGTH_SHORT);
		toast.show();
	}

	public void notifyDelivery(View v) {
		Toast toast = Toast.makeText(this, "Delivery Successful",
				Toast.LENGTH_SHORT);
		toast.show();
	}
}
