package ucsd.cs110.splurge;

import java.util.ArrayList;

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
	}

	/**
	 * Class to hold data for the views
	 */
	private class ViewHolder {
		TextView name;
		TextView price;
		CheckBox cb;
		ImageView image;
	}

	/**
	 * @param position
	 *            Location of the food item in the menu
	 * @param convertView
	 *            The new view to be created
	 * @param Parent
	 *            The parent that this view will be attached to
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup Parent) {
		ViewHolder holder = null;
		LayoutInflater vi = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		convertView = vi.inflate(R.layout.menu_item, null);
		// Get view from id
		holder = new ViewHolder();
		holder.name = (TextView) convertView.findViewById(R.id.MenuName);
		holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
		holder.image = (ImageView) convertView.findViewById(R.id.MenuIcon);
		holder.price = (TextView) convertView.findViewById(R.id.MenuPrice);
		convertView.setTag(holder);
		// create listener for check box
		holder.cb.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CheckBox cb = (CheckBox) v;
				FoodItem food = (FoodItem) cb.getTag();
				Log.e("FoodMenuAdapter", "item should be checked");
				// check to see if the check box was clicked or not
				food.setSelected(cb.isChecked());
			}
		});
		// get correct food item from position in menu
		FoodItem food = foodItems.get(position);
		// set values for the views
		holder.name.setText(food.getName());
		holder.image.setImageResource(food.getImage());
		holder.price.setText(Integer.toString(food.getPrice()));
		holder.cb.setChecked(food.isSelected());
		holder.cb.setTag(food);

		return convertView;
	}
}
