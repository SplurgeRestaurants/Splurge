package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.NumberPicker;

public class ReservationFragment extends SuperFragment {

	private NumberPicker mMinuteSpinner;
	private NumberPicker mHourSpinner;
	private NumberPicker mAmPmSpinner;
	private NumberPicker mPartySize;
	private final int MAX_PARTY_SIZE = 10;
	static final String[] mAmPmString = { "AM", "PM" };
	static String[] mHours = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12" };
	static final String[] mMinutes = { "00", "15", "30", "45" };
	static final String[] mPSize = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.reservation, container, false);
		mHourSpinner = (NumberPicker) ret.findViewById(R.id.hour);
		mHourSpinner.setMinValue(0);
		mHourSpinner.setMaxValue(0);
		mHours = getWrapperActivity().getModel().getRestaurant()
				.getAvailableHours(CalendarViewFragment.month);
		mHours = convertHours(mHours);
		mHourSpinner.setDisplayedValues(mHours);
		mHourSpinner.setMaxValue(mHours.length - 1);
		mHourSpinner.setOnLongPressUpdateInterval(100);
		mMinuteSpinner = (NumberPicker) ret.findViewById(R.id.minute);
		mMinuteSpinner.setMinValue(0);
		mMinuteSpinner.setMaxValue(3);
		mMinuteSpinner.setDisplayedValues(mMinutes);
		mMinuteSpinner.setOnLongPressUpdateInterval(100);
		mAmPmSpinner = (NumberPicker) ret.findViewById(R.id.amPm);
		mAmPmSpinner.setMinValue(0);
		mAmPmSpinner.setMaxValue(1);
		mAmPmSpinner.setDisplayedValues(mAmPmString);
		mPartySize = (NumberPicker) ret.findViewById(R.id.party_size);
		mPartySize.setMinValue(0);
		mPartySize.setMaxValue(MAX_PARTY_SIZE - 1);
		mPartySize.setDisplayedValues(mPSize);
		mPartySize.setOnLongPressUpdateInterval(100);
		return ret;
	}

	private String[] convertHours(String[] hours) {
		for (int i = 0; i < hours.length; i++) {
			if (Integer.parseInt(hours[i]) > 12) {
				hours[i] = Integer.toString(Integer.parseInt(hours[i]) - 12);
			}
		}
		Set<String> removeDups = new HashSet<String>();
		for (int i = 0; i < hours.length; i++) {
			removeDups.add(hours[i]);
		}
		ArrayList<String> copy = new ArrayList<String>(removeDups);
		ArrayList<Integer> sort = new ArrayList<Integer>();
		for (int i = 0; i < copy.size(); i++) {
			sort.add(Integer.parseInt(copy.get(i)));
		}
		Collections.sort(sort);
		hours = new String[sort.size()];
		for (int i = 0; i < hours.length; i++) {
			hours[i] = Integer.toString(sort.get(i));
		}
		return hours;
	}

	/**
	 * set listener for each button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setReservationListener(OnClickListener listener) {
		(getView().findViewById(R.id.openButton)).setOnClickListener(listener);
	}

	/**
	 * Set mSuperListener to the buttons
	 */
	@Override
	public void onStart() {
		setReservationListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}