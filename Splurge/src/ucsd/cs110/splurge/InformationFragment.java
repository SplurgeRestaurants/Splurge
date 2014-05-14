package ucsd.cs110.splurge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformationFragment extends SuperFragment {
	TextView status;
	TextView currentHours;
	TextView hours;
	TextView address;
	TextView directions;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// get layout
		View ret = inflater
				.inflate(R.layout.information_page, container, false);
		// Get ids
		status = (TextView) ret.findViewById(R.id.status);
		currentHours = (TextView) ret.findViewById(R.id.currentHours);
		hours = (TextView) ret.findViewById(R.id.hours);
		address = (TextView) ret.findViewById(R.id.address);
		directions = (TextView) ret.findViewById(R.id.directions);
		// set values for views
		status.setText(getRestaurantStatus());
		currentHours.setText(getRestaurantCurrentHours());
		address.setText(getRestaurantAddress());
		return ret;
	}

	private String getRestaurantStatus() {
		return "Now: Open Forever";
	}

	private String getRestaurantCurrentHours() {
		return "Hours: 24/7 Baby!";
	}

	private String getRestaurantAddress() {
		return "The Village";
	}

	// set listener for directions and hours button
	public void setListListener(OnClickListener listener) {
		(getView().findViewById(R.id.hours)).setOnClickListener(listener);
		(getView().findViewById(R.id.directions)).setOnClickListener(listener);
	}

	@Override
	public void setSuperListener(SuperListener l) {
		setListListener((OnClickListener) l);
	}
}
