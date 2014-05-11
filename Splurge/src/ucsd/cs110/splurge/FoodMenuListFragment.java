package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class FoodMenuListFragment extends Fragment {
	private static final int DINNER = 2;
	private static final int LUNCH = 1;
	private static final int BREAKFAST = 0;
	//List
	ListView mListView;
	//Adapter to create list entries in ListView
	FoodMenuAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Get layout
		View ret = inflater.inflate(R.layout.food_menu_list, container, false);
		//get id for ListView
		mListView = (ListView) ret.findViewById(R.id.food_list);
		//get the specified meal option
		Intent intent = getActivity().getIntent();
		int meal = 0;
		meal = intent
				.getIntExtra(RestaurantMainMenuActivity.MEAL, meal);
		switch (meal) {
		case BREAKFAST:
			FoodMenuActivity.data = (ArrayList<FoodItem>) getBreakfastMenuItem();
			break;
		case LUNCH:
			FoodMenuActivity.data = (ArrayList<FoodItem>) getLunchMenuItem();
			break;
		case DINNER:
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
		hashbrowns.setPrice(2000);
		food.add(hashbrowns);
		for(int i = 0; i < 10; i++){
			FoodItem fakeitem = new FoodItem("Fake Breakfast Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			fakeitem.setPrice(300);
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
		burger.setPrice(500);
		food.add(burger);
		for(int i = 0; i < 10; i++){
			FoodItem fakeitem = new FoodItem("Fake Lunch Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			fakeitem.setPrice(300);
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
		steak.setPrice(1000);
		food.add(steak);
		for(int i = 0; i < 10; i++){
			FoodItem fakeitem = new FoodItem("Fake Dinner Item " + i);
			fakeitem.setImage(R.drawable.ic_launcher);
			fakeitem.setPrice(300);
			food.add(fakeitem);
		}
		return food;
	}
	//set listener for list entries
	public void setListListener(OnItemClickListener listener) {
		((ListView) getView().findViewById(R.id.food_list))
				.setOnItemClickListener(listener);
	}

}
