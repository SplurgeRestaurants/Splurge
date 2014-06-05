package ucsd.cs110.splurge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

public class ReservationFragment extends SuperFragment {

	private NumberPicker mMinuteSpinner;
	private NumberPicker mHourSpinner;
	private NumberPicker mAmPmSpinner;
	private NumberPicker mPartySize;
	private final int MAX_PARTY_SIZE = 10;
	static final String[] mAmPmString = { "AM", "PM" };
	static String[] mHours = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10", "11", "12" };
	static String[] mMinutes = { "00", "15", "30", "45" };
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
		mHourSpinner.setDisplayedValues(mHours);
		mHourSpinner.setMaxValue(mHours.length - 1);
		mHourSpinner.setOnLongPressUpdateInterval(100);
		mMinuteSpinner = (NumberPicker) ret.findViewById(R.id.minute);
		mMinuteSpinner.setMinValue(0);
		mMinuteSpinner.setMaxValue(3);
		mMinutes = getWrapperActivity()
				.getModel()
				.getRestaurant()
				.getAvailableMinutes(CalendarViewFragment.month,
						Integer.parseInt(mHours[0]));
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

	/**
	 * set listener for each button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setReservationListener(OnClickListener listener) {
		(getView().findViewById(R.id.openButton)).setOnClickListener(listener);
	}

	public void setValueChangeListener(OnValueChangeListener listener) {
		((NumberPicker) getView().findViewById(R.id.hour))
				.setOnValueChangedListener(listener);
		((NumberPicker) getView().findViewById(R.id.minute))
				.setOnValueChangedListener(listener);
		((NumberPicker) getView().findViewById(R.id.amPm))
				.setOnValueChangedListener(listener);
	}

	/**
	 * Set mSuperListener to the buttons
	 */
	@Override
	public void onStart() {
		setReservationListener((OnClickListener) mSuperListener);
		setValueChangeListener((OnValueChangeListener) mSuperListener);
		super.onStart();
	}
}