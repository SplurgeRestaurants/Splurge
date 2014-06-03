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
	 * Phone number of the restaurant
	 */
	private TextView phoneNumber;
	/**
	 * Address of the restaurant
	 */
	private TextView address;

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
		phoneNumber = (TextView) ret.findViewById(R.id.phone_number);
		address = (TextView) ret.findViewById(R.id.address);
		status.setText(getRestaurantStatus());
		address.setText(getRestaurantAddress());
		phoneNumber.setText(getPhoneNumber());
		return ret;
	}

	/**
	 * Get phone number of the restaurant
	 * 
	 * @return String representing phone number
	 */
	private String getPhoneNumber() {
		return getWrapperActivity().getModel().getRestaurantPhoneNumber();
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
	 * Get the restaurant's address
	 * 
	 * @return String of the restaurant's address
	 */
	private String getRestaurantAddress() {
		return getWrapperActivity().getModel().getRestaurantStreetAddress();
	}

	/**
	 * set listener for directions and hours button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setButtonListener(OnClickListener listener) {
		(getView().findViewById(R.id.directions)).setOnClickListener(listener);
		(getView().findViewById(R.id.call_phone)).setOnClickListener(listener);
	}

	/**
	 * Set mSuperListener for button listener
	 */
	@Override
	public void onStart() {
		setButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}
