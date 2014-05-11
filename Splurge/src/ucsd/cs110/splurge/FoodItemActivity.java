package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class FoodItemActivity extends Activity {
	FoodItemFragment fragment;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_food_item);
		//listener = new FoodMenuListListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new FoodItemFragment();
		ft.replace(R.id.food_item_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}
}
