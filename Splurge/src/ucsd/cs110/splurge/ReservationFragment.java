package ucsd.cs110.splurge;

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
	private final int MAX_PARTY_SIZE = 20;
	static final String[] mAmPmString = { "AM", "PM" };
	static final String[] mHours = { "1", "2", "3", "4", "5", "6", "7", "8",
			"9", "10", "11", "12" };
	static final String[] mMinutes = { "00", "15", "30", "45" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View ret = inflater.inflate(R.layout.reservation, container, false);
		mHourSpinner = (NumberPicker) ret.findViewById(R.id.hour);
		mHourSpinner.setMinValue(0);
		mHourSpinner.setMaxValue(11);
		mHourSpinner.setDisplayedValues(mHours);
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
		mPartySize.setMaxValue(MAX_PARTY_SIZE);
		mPartySize.setOnLongPressUpdateInterval(100);
		return ret;
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