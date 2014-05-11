package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("rawtypes")
public class FoodMenuAdapter extends ArrayAdapter {
	//Populate with food items from the specified menu
	private ArrayList<FoodItem> foodItems;
	//Store the current context
	Context mContext;
	
	/*
	 * Overloaded Constructor 
	 */
	@SuppressWarnings("unchecked")
	public FoodMenuAdapter(Context context, int resource,
			ArrayList<FoodItem> foodItems) {
		super(context, resource, foodItems);
		// TODO Auto-generated constructor stub
		mContext = context;
		this.foodItems = foodItems;
	}
	/*
	 * Class to hold data for the views
	 */
	private class ViewHolder {
		TextView name;
		TextView price;
		CheckBox cb;
		ImageView image;
	}
	/*
	 * (non-Javadoc)
	 * @see android.widget.ArrayAdapter#getView(int, android.view.View, android.view.ViewGroup)
	 * Binds the views together and set up a listener for the checkboxes
	 * Sets the correct information for each view
	 */
	@Override
	public View getView(int position, View convertView, ViewGroup Parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.menu_item, null);    
			// Get ids for the views
			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.MenuName);
			holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
			holder.image = (ImageView) convertView.findViewById(R.id.MenuIcon);
			holder.price = (TextView) convertView.findViewById(R.id.MenuPrice);
			convertView.setTag(holder);
			// create listener for checkbox
			holder.cb.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					FoodItem food = (FoodItem) cb.getTag();
					Toast.makeText(
							mContext.getApplicationContext(),
							"Clicked on Checkbox: " + cb.getText() + " is "
									+ cb.isChecked(), Toast.LENGTH_LONG).show();
					//check to see if the checkbox was clicked or not
					food.setSelected(cb.isChecked());
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		//get correct food item from position in menu
		FoodItem food = foodItems.get(position);
		//set values for the views
		holder.name.setText(food.getName());
		holder.image.setImageResource(food.getImage());
		holder.price.setText(Integer.toString(food.getPrice()));
		//holder.cb.setText(food.getName());
		holder.cb.setChecked(food.isSelected());
		holder.cb.setTag(food);

		return convertView;
	}
}
