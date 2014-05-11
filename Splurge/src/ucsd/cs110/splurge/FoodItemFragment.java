package ucsd.cs110.splurge;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodItemFragment extends Fragment{
	//View Items in fragment
	TextView name;				//food item name
	TextView description;		//food item description
	ImageView image;			//food item image
	@Override
	/**
	 * Get information for the view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//Get the layout
		View ret = inflater.inflate(R.layout.food_item, container, false);
		// get view ids
		name = (TextView) ret.findViewById(R.id.food_name);
		description = (TextView) ret.findViewById(R.id.food_description);
		image = (ImageView) ret.findViewById(R.id.food_image);
		//get the intent to find the position of the food item in the food menu
		Intent intent = getActivity().getIntent();
		int position = 0;
		position = intent.getIntExtra(FoodMenuActivity.FOOD_ITEM_POSITION, position);
		// set the information to the appropriate views
		name.setText(FoodMenuActivity.data.get(position).getName());
		image.setImageResource(FoodMenuActivity.data.get(position).getImage());
		description.setText(FoodMenuActivity.data.get(position).getName());
		return ret;
	}
}
