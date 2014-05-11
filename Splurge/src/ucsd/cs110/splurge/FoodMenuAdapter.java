package ucsd.cs110.splurge;

import java.util.ArrayList;

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
	
	private ArrayList<FoodItem> foodItems;
	//Store the current context
	Context mContext;

	@SuppressWarnings("unchecked")
	public FoodMenuAdapter(Context context, int resource,
			ArrayList<FoodItem> foodItems) {
		super(context, resource, foodItems);
		// TODO Auto-generated constructor stub
		mContext = context;
		this.foodItems = foodItems;
	}

	private class ViewHolder {
		TextView name;
		CheckBox cb;
		ImageView image;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup Parent) {
		ViewHolder holder = null;
		if (convertView == null) {
			LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = vi.inflate(R.layout.menu_item, null);    

			holder = new ViewHolder();
			holder.name = (TextView) convertView.findViewById(R.id.MenuName);
			holder.cb = (CheckBox) convertView.findViewById(R.id.checkBox1);
			holder.image = (ImageView) convertView.findViewById(R.id.MenuIcon);
			convertView.setTag(holder);

			holder.cb.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {
					CheckBox cb = (CheckBox) v;
					FoodItem food = (FoodItem) cb.getTag();
					Toast.makeText(
							mContext.getApplicationContext(),
							"Clicked on Checkbox: " + cb.getText() + " is "
									+ cb.isChecked(), Toast.LENGTH_LONG).show();
					food.setSelected(cb.isChecked());
				}
			});
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		FoodItem food = foodItems.get(position);
		holder.name.setText(food.getName());
		holder.image.setImageResource(food.getImage());
		//holder.cb.setText(food.getName());
		holder.cb.setChecked(food.isSelected());
		holder.cb.setTag(food);

		return convertView;
	}
}
