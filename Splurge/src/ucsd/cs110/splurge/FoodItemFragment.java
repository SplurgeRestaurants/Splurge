package ucsd.cs110.splurge;

import java.text.NumberFormat;
import java.util.Locale;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
		mPrice = (TextView) ret.findViewById(R.id.food_price);
		Intent intent = getActivity().getIntent();
		int position = 0;
		position = intent.getIntExtra(FoodMenuListFragment.FOOD_ITEM_POSITION,
				position);
		String origin = intent.getStringExtra(FoodMenuListFragment.ORIGIN);
		NumberFormat currencyFormatter = NumberFormat
				.getCurrencyInstance(Locale.US);
		Double currencyAmount;
		if (origin.compareTo("Food Menu") == 0) {
			FoodMenuListFragment frag = new FoodMenuListFragment();
			mName.setText(frag.getFoodData().get(position).getName());
			mDescription.setText(frag.getFoodData().get(position).getName());
			currencyAmount = (double) frag.getFoodData().get(position)
					.getPrice();
			mPrice.setText(currencyFormatter.format(currencyAmount));
		} else if (origin.compareTo("Dining Out") == 0) {
			DiningOutFragment frag = new DiningOutFragment();
			mName.setText(frag.getSelectedFood().get(position).getName());
			mDescription
					.setText(frag.getSelectedFood().get(position).getName());
			currencyAmount = (double) frag.getSelectedFood().get(position)
					.getPrice();
			mPrice.setText(currencyFormatter.format(currencyAmount));
		} else {
			Log.e("Splurge", "Could not get food item information");
		}
		return ret;
	}
}
