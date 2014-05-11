package ucsd.cs110.splurge;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class FoodItemFragment extends Fragment{
	TextView name;
	TextView description;
	ImageView image;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.food_item, container, false);
		name = (TextView) ret.findViewById(R.id.food_name);
		description = (TextView) ret.findViewById(R.id.food_description);
		image = (ImageView) ret.findViewById(R.id.food_image);
		Intent intent = getActivity().getIntent();
		int position = 0;
		intent.getIntExtra(FoodMenuActivity.FOOD_ITEM_POSITION, position);
		name.setText(FoodMenuActivity.data.get(position).getName());
		image.setImageResource(FoodMenuActivity.data.get(position).getImage());
		description.setText(FoodMenuActivity.data.get(position).getName());
		return ret;
	}
}
