package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
/**
 * Activity that displays information about each food item
 *
 *
 */
public class FoodItemActivity extends Activity {
	// fragment for layout
	FoodItemFragment fragment;
	@Override
	/**
	 * Get fragment layout
	 */
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_food_item);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new FoodItemFragment();
		ft.replace(R.id.food_item_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}
}
