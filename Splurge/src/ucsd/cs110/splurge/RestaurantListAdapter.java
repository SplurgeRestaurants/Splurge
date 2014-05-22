package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.Restaurant;
import android.content.Context;
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
public class RestaurantListAdapter extends ArrayAdapter<Restaurant> {
	/**
	 * Populate with restaurants
	 */
	private ArrayList<Restaurant> restaurants;
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
			ArrayList<Restaurant> Restaurants) {
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
			holder.image = (ImageView) convertView
					.findViewById(R.id.RestaurantIcon);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		Restaurant res = restaurants.get(position);
		holder.name.setText(res.getName());
		holder.image.setImageResource(res.getImage());
		return convertView;
	}
}
