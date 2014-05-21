package ucsd.cs110.splurge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

public class InformationFragment extends SuperFragment {
	/**
	 * Check if the restaurant is open or closed
	 */
	private TextView status;
	/**
	 * The current restaurant's hours
	 */
	private TextView currentHours;
	/**
	 * Weekly hours for the restaurant
	 */
	private TextView hours;
	/**
	 * Address of the restaurant
	 */
	private TextView address;
	/**
	 * Directions to the restaurant
	 */
	private TextView directions;

	/**
	 * Display the information for the restaurant
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater
				.inflate(R.layout.information_page, container, false);
		status = (TextView) ret.findViewById(R.id.status);
		// currentHours = (TextView) ret.findViewById(R.id.currentHours);
		// hours = (TextView) ret.findViewById(R.id.hours);
		address = (TextView) ret.findViewById(R.id.address);
		directions = (TextView) ret.findViewById(R.id.directions);
		status.setText(getRestaurantStatus());
		// currentHours.setText(getRestaurantCurrentHours());
		address.setText(getRestaurantAddress());
		return ret;
	}

	/**
	 * Get the current status of the restaurant
	 * 
	 * @return "Open" or "Closed"
	 */
	private String getRestaurantStatus() {
		return "Now: Open";
	}

	/**
	 * Get the current hours of the restaurant
	 * 
	 * @return String representing the hours
	 */
	private String getRestaurantCurrentHours() {
		return "Today: 11AM - 8PM";
	}

	/**
	 * Get the restaurant's address
	 * 
	 * @return String of the restaurant's address
	 */
	private String getRestaurantAddress() {
		return "Address: The Village";
	}

	/**
	 * set listener for directions and hours button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setButtonListener(OnClickListener listener) {
		// (getView().findViewById(R.id.hours)).setOnClickListener(listener);
		(getView().findViewById(R.id.directions)).setOnClickListener(listener);
	}

	/**
	 * Set mSuperListener for button listener
	 */
	@Override
	public void onStart() {
		setButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}
	// TODO (dqthai) add phone information
}
