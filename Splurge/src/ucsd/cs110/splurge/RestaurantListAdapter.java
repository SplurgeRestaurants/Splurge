package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.RestaurantListing;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 * Creates a custom view for the restaurant entries in the home screen.
 * 
 */
public class RestaurantListAdapter extends ArrayAdapter<RestaurantListing> {
	/**
	 * Populate with restaurants
	 */
	private ArrayList<RestaurantListing> restaurants;
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
	 * @param Restaurants
	 *            ArrayList of restaurants
	 */
	public RestaurantListAdapter(Context context, int resource,
			ArrayList<RestaurantListing> Restaurants) {
		super(context, resource, Restaurants);
		mContext = context;
		this.restaurants = Restaurants;
	}

	/**
	 * Class to hold data for the views
	 */
	private class ViewHolder {
		/**
		 * Name of the restaurant
		 */
		TextView name;
		/**
		 * Image of the restaurant
		 */
		ImageView image;
	}

	/**
	 * Binds the views into a single view and set the correct information for
	 * each of the views
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
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater vi = LayoutInflater.from(mContext);
			convertView = vi.inflate(R.layout.restaurant_list_entry, null);
			holder = new ViewHolder();
			holder.name = (TextView) convertView
					.findViewById(R.id.RestaurantName);
			holder.name.setTextColor(Color.BLACK);
			holder.image = (ImageView) convertView
					.findViewById(R.id.RestaurantIcon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		RestaurantListing res = restaurants.get(position);
		holder.name.setText(res.getRestaurantName());
		holder.image.setImageBitmap(res.getRestaurantImage());
		return convertView;
	}
}
