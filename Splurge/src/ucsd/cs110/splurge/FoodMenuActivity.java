package ucsd.cs110.splurge;

import java.util.ArrayList;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FoodMenuActivity extends Activity {

	protected ListView mListView;
	private FoodMenuListFragment fragment;
	public OnItemClickListener listener;
	static ArrayList<FoodItem> data;

	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.food_menu_activity);
		listener = new FoodMenuListListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new FoodMenuListFragment();
		ft.replace(R.id.food_menu_list_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected void onStart() {
		fragment.setListListener(listener);
		super.onStart();
	}
	
	public void getSelected(View view){
		for(int i = 0; i < data.size(); i++){
			Log.e("Items", data.get(i).getName());
			if(data.get(i).isSelected()){
				Log.e("Selected", data.get(i).getName());
			}
		}
	}
}
