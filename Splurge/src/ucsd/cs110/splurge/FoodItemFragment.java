package ucsd.cs110.splurge;

import java.text.NumberFormat;
import java.util.Locale;

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
		DiningOutFragment frag = new DiningOutFragment();
		mName.setText(frag.getSelectedFood().get(position).getName());
		mImage.setImageBitmap(frag.getSelectedFood().get(position).getImage());
		mDescription.setText(frag.getSelectedFood().get(position).getName());

		NumberFormat currencyFormatter = NumberFormat
				.getCurrencyInstance(Locale.US);
		Double currencyAmount;

		currencyAmount = (double) frag.getSelectedFood().get(position)
				.getPrice();
		mPrice.setText(currencyFormatter.format(currencyAmount));
		return ret;
	}
}
