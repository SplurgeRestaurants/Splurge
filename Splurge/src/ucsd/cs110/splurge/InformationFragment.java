package ucsd.cs110.splurge;

import java.util.Calendar;

import ucsd.cs110.splurge.model.Timeslot;
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
	 * Current hours of the restaurant
	 */
	private TextView hours;

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
		hours = (TextView) ret.findViewById(R.id.hours);
		hours.setText(getHours());
		status.setText(getRestaurantStatus());
		address.setText(getRestaurantAddress());
		phoneNumber.setText(getPhoneNumber());
		return ret;
	}

	/**
	 * Get the current hours of the restaurant
	 * 
	 * @return A formatted String of the open hours of the restaurant
	 */
	private String getHours() {
		String hour = "Hours: ";
		Timeslot time = getWrapperActivity().getModel().getHoursForDay(
				Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		hour += time.getStartTime().get(Calendar.HOUR);
		if (time.getStartTime().get(Calendar.AM_PM) == 0) {
			hour += "AM";
		} else {
			hour += "PM";
		}
		hour += " - ";
		hour += time.getEndTime().get(Calendar.HOUR);
		if (time.getEndTime().get(Calendar.AM_PM) == 0) {
			hour += "AM";
		} else {
			hour += "PM";
		}
		return hour;
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
		Timeslot openHours = getWrapperActivity().getModel().getHoursForDay(
				Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
		int endTime = openHours.getEndTime().get(Calendar.HOUR_OF_DAY);
		int startTime = openHours.getStartTime().get(Calendar.HOUR_OF_DAY);
		int currHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if (currHour > startTime && currHour < endTime) {
			return "Now: Open";
		} else {
			return "Now: Closed";
		}
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
	 * set listener for the get directions and call phone buttons
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
