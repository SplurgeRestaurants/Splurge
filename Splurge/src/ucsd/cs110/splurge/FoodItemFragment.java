package ucsd.cs110.splurge;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Fragment that displays information about the food item
 */
public class FoodItemFragment extends SuperFragment {
	/**
	 * Name of the food item
	 */
	private TextView mName;
	/**
	 * Description of the food item
	 */
	private TextView mDescription;
	/**
	 * Price of the food item
	 */
	private TextView mPrice;
	/**
	 * Image of the food item
	 */
	private ImageView mImage;

	@Override
	/**
	 * Get information for the view
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.food_item, container, false);
		mName = (TextView) ret.findViewById(R.id.food_name);
		mDescription = (TextView) ret.findViewById(R.id.food_description);
		mImage = (ImageView) ret.findViewById(R.id.food_image);
		mPrice = (TextView) ret.findViewById(R.id.food_price);
		Intent intent = getActivity().getIntent();
		int position = 0;
		position = intent.getIntExtra(FoodMenuListFragment.FOOD_ITEM_POSITION,
				position);
		mName.setText(FoodMenuListFragment.data.get(position).getName());
		mImage.setImageResource(FoodMenuListFragment.data.get(position)
				.getImage());
		mDescription.setText(FoodMenuListFragment.data.get(position).getName());
		mPrice.setText("$"
				+ Integer.toString(FoodMenuListFragment.data.get(position)
						.getPrice()));
		return ret;
	}
}
