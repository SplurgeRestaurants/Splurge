package ucsd.cs110.splurge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodItemFragment extends SuperFragment {
	// View Items in fragment
	private TextView name; // food item name
	private TextView description; // food item description
	private TextView price; // food item price
	private ImageView image; // food item image
	@Override
	/**
	 * Get information for the view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the layout
		View ret = inflater.inflate(R.layout.food_item, container, false);
		// get view ids
		name = (TextView) ret.findViewById(R.id.food_name);
		description = (TextView) ret.findViewById(R.id.food_description);
		image = (ImageView) ret.findViewById(R.id.food_image);
		price = (TextView) ret.findViewById(R.id.food_price);
		// get the intent to find the position of the food item in the food menu
		Intent intent = getActivity().getIntent();
		int position = 0;
		position = intent.getIntExtra(FoodMenuListFragment.FOOD_ITEM_POSITION,
				position);
		// set the information to the appropriate views
		name.setText(FoodMenuListFragment.data.get(position).getName());
		image.setImageResource(FoodMenuListFragment.data.get(position)
				.getImage());
		description.setText(FoodMenuListFragment.data.get(position).getName());
		price.setText(Integer.toString(FoodMenuListFragment.data.get(position)
				.getPrice()));

		return ret;
	}
}
