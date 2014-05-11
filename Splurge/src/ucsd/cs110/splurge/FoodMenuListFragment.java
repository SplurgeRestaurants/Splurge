package ucsd.cs110.splurge;

import java.util.ArrayList;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FoodMenuListFragment extends Fragment {

	static ListView mListView;
	FoodMenuAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.food_menu_list, container, false);
		mListView = (ListView) ret.findViewById(R.id.food_list);
		Intent intent = getActivity().getIntent();
		int meal = 0;
		meal = intent
				.getIntExtra(RestaurantMainMenuActivity.MEAL, meal);
		switch (meal) {
		case 0:
			FoodMenuActivity.data = (ArrayList<FoodItem>) getBreakfastMenuItem();
			break;
		case 1:
			FoodMenuActivity.data = (ArrayList<FoodItem>) getLunchMenuItem();
			break;
		case 2:
			FoodMenuActivity.data = (ArrayList<FoodItem>) getDinnerMenuItem();
			break;
		default:
			FoodMenuActivity.data = null;
			break;
		}
		adapter = new FoodMenuAdapter(getActivity(), R.layout.menu_item, FoodMenuActivity.data);
		mListView.setAdapter(adapter);
		return ret;
	}

	/*
	 * Populate list with breakfast items
	 */
	public ArrayList<FoodItem> getBreakfastMenuItem(){
		ArrayList<FoodItem> food = new ArrayList<FoodItem>(11);
		FoodItem hashbrowns = new FoodItem("Hashbrown");
		hashbrowns.setImage(R.drawable.logo);
		food.add(hashbrowns);
		for(int i = 0; i < 10; i++){
			FoodItem fakeitem = new FoodItem("Fake Breakfast Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			food.add(fakeitem);
		}
		return food;
	}

	/*
	 * Populate list with lunch items
	 */
	public ArrayList<FoodItem> getLunchMenuItem(){
		ArrayList<FoodItem> food = new ArrayList<FoodItem>(11);
		FoodItem burger = new FoodItem("Burger");
		burger.setImage(R.drawable.logo);
		food.add(burger);
		for(int i = 0; i < 10; i++){
			FoodItem fakeitem = new FoodItem("Fake Lunch Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			food.add(fakeitem);
		}
		return food;
	}

	/*
	 * Populate list with dinner items
	 */
	
	public ArrayList<FoodItem> getDinnerMenuItem(){
		ArrayList<FoodItem> food = new ArrayList<FoodItem>(11);
		FoodItem steak = new FoodItem("Steak");
		steak.setImage(R.drawable.logo);
		food.add(steak);
		for(int i = 0; i < 10; i++){
			FoodItem fakeitem = new FoodItem("Fake Dinner Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			food.add(fakeitem);
		}
		return food;
	}

	public void setListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.food_list))
				.setOnItemClickListener(listener);
	}

}
