package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FoodMenuActivity extends Activity {

	protected ListView mListView;
	private FoodMenuListFragment fragment;
	public OnItemClickListener listener;

	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.food_menu_list_fragment_placeholder);
		listener = new FoodMenuListListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new FoodMenuListFragment();
		ft.replace(R.id.food_menu_list_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
		//mListView.setOnItemClickListener(listener);
	}
	
	@Override
	protected void onStart() {
		fragment.setListListener(listener);
		super.onStart();
	}
}
