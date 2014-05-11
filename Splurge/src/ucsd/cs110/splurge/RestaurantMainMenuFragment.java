package ucsd.cs110.splurge;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class RestaurantMainMenuFragment extends Fragment {
	// TextView for restaurant name
	TextView mTextView;
	
	/*
	 * (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 * Set the restaurant name in TextView
	 */
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//get layout
		View ret = inflater.inflate(R.layout.main_menu, container, false);
		// get restaurant name from intent and set it to the Text View
		Intent intent = getActivity().getIntent();
		String chosenRestaurant = intent
				.getStringExtra(HomeScreenActivity.RESTAURANT);
		mTextView = (TextView) ret.findViewById(R.id.textView1);
		mTextView.setText(chosenRestaurant);
		return (ret);
	}
	
	/*
	 * set listener for each button
	 */
	public void setMainMenuListener(OnClickListener listener) {
		(getView().findViewById(R.id.menu)).setOnClickListener(listener);
		(getView().findViewById(R.id.information)).setOnClickListener(listener);
		(getView().findViewById(R.id.diningout)).setOnClickListener(listener);
		(getView().findViewById(R.id.reserve)).setOnClickListener(listener);
	}
}
