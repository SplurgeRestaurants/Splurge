package ucsd.cs110.splurge;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

import ucsd.cs110.splurge.model.FoodItem;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * Creates a custom view for the food menu entries in the food menu.
 * 
 */
public class FoodMenuAdapter extends ArrayAdapter<FoodItem> {
	/**
	 * Populate with food items from the specified menu
	 */
	private ArrayList<FoodItem> foodItems;
	/**
	 * Store the current context
	 */
	private Context mContext;
	/**
	 * Layout to use
	 */
	private int resource;

	/**
	 * Create a new FoodMenuAdapter designed to bind the views to create a list
	 * entry
	 * 
	 * @param context
	 *            current The current context
	 * @param resource
	 *            ID for layout file
	 * @param foodItems
	 *            ArrayList of food items for the food menu entries
	 */
	public FoodMenuAdapter(Context context, int resource,
			ArrayList<FoodItem> foodItems) {
		super(context, resource, foodItems);
		mContext = context;
		this.foodItems = foodItems;
		this.resource = resource;
		Collections.sort(this.foodItems);
	}

	/**
	 * Class to hold data for the views
	 */
	private class ViewHolder {
		/**
		 * Name of the food item
		 */
		TextView name;
		/**
		 * Price of the food item
		 */
		TextView price;
		/**
		 * Check box corresponding to the food item
		 */
		CheckBox cb;
		/**
		 * Image of the food item
		 */
		ImageView image;
	}

	/**
	 * Binds the views together into a single view and set the correct data for
	 * each view. Catches the error if there is not check box view.
	 * 
	 * @param position
	 *            Location of the food item in the menu
	 * @param convertView
	 *            The new view to be created
	 * @param Parent
	 *            The parent that this view will be attached to
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup Parent) {
		ViewHolder holder = new ViewHolder();
		if (convertView == null) {
			LayoutInflater vi = LayoutInflater.from(mContext);
			convertView = vi.inflate(resource, null);
			holder.name = (TextView) convertView.findViewById(R.id.FoodName);
			holder.price = (TextView) convertView.findViewById(R.id.FoodPrice);
			holder.image = (ImageView) convertView.findViewById(R.id.FoodIcon);
			try {
				holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
				holder.cb.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						CheckBox cb = (CheckBox) v;
						FoodItem food = (FoodItem) cb.getTag();
						Log.e("FoodMenuAdapter", food.getName()
								+ " should be checked");
						food.setSelected(cb.isChecked());
					}
				});
			} catch (Exception e) {
				Log.e("Splurge", "View does not have check box");
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FoodItem food = foodItems.get(position);
		holder.name.setText(food.getName());
		holder.image.setImageBitmap(food.getImage());
		NumberFormat currencyFormatter = NumberFormat
				.getCurrencyInstance(Locale.US);
		Double currencyAmount;

		currencyAmount = (double) food.getPrice();
		holder.price.setText(currencyFormatter.format(currencyAmount));
		try {
			holder.cb.setChecked(food.isSelected());
			holder.cb.setTag(food);
		} catch (Exception e) {

		}
		return convertView;
	}
}
