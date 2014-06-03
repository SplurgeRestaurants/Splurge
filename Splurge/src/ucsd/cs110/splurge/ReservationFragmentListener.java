package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import ucsd.cs110.splurge.connectivity.tasks.ReservationRequestListener;
import ucsd.cs110.splurge.model.Timeslot;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;
import android.widget.Toast;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class ReservationFragmentListener extends SuperListener implements
		OnClickListener, OnValueChangeListener, ReservationRequestListener {

	private boolean reservationSuccessful = false;

	/**
	 * Create a new ReservationFragmentListener, designed to listen to a
	 * ResevationFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public ReservationFragmentListener(WrapperActivity context) {
		super(context);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.openButton:
			NumberPicker mPartySize = (NumberPicker) mWrapper
					.findViewById(R.id.party_size);
			NumberPicker hours = (NumberPicker) mWrapper
					.findViewById(R.id.hour);
			NumberPicker minutes = (NumberPicker) mWrapper
					.findViewById(R.id.minute);
			NumberPicker amPm = (NumberPicker) mWrapper.findViewById(R.id.amPm);
			String selectedAmPm = ReservationFragment.mAmPmString[amPm
					.getValue()];
			EditText partyName = (EditText) mWrapper
					.findViewById(R.id.form_party_name);
			String pName = partyName.getText().toString();
			int selectedHour;
			if (selectedAmPm.compareTo("AM") == 0) {
				selectedHour = Integer
						.parseInt(ReservationFragment.mHours[hours.getValue()]);
			} else {
				selectedHour = Integer
						.parseInt(ReservationFragment.mHours[hours.getValue()]);
				selectedHour += 12;
			}
			int selectedMinute = Integer
					.parseInt(ReservationFragment.mMinutes[minutes.getValue()]);
			int getPartySize = mPartySize.getValue();
			Log.e("Splurge", "Party Name is " + pName);

			Log.e("Splurge", selectedHour + " " + selectedMinute + " "
					+ selectedAmPm);
			Log.e("Splurge", "Party size is " + getPartySize);

			// Populate the starting time with given values
			GregorianCalendar startTime = new GregorianCalendar();
			startTime.set(Calendar.HOUR_OF_DAY, selectedHour - 1);
			startTime.set(Calendar.MINUTE, selectedMinute);
			startTime.set(Calendar.MONTH,
					CalendarViewFragment.month.get(Calendar.MONTH));
			startTime.set(Calendar.YEAR,
					CalendarViewFragment.month.get(Calendar.YEAR));
			startTime.set(Calendar.DAY_OF_MONTH,
					CalendarViewFragment.month.get(Calendar.DAY_OF_MONTH));

			int result = mWrapper.getModel().requestReservation(getPartySize,
					"no name", startTime, this);
			if (reservationSuccessful) {
				Toast.makeText(mWrapper,
						"Reservation Successful. Your id is " + result,
						Toast.LENGTH_LONG).show();
			} else if (result == -1) {
				Toast.makeText(mWrapper,
						"Reservation Unsuccessful, pick another time",
						Toast.LENGTH_LONG).show();
			} else if (result == -2) {
				Log.i("Splurge", "Reservation information available later.");
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
		switch (picker.getId()) {
		case R.id.hour:
			int hour = picker.getValue();
			getAvailableMinutes(hour);
			break;
		}
	}

	private void getAvailableMinutes(int hour) {
		NumberPicker mMinuteSpinner = (NumberPicker) mWrapper
				.findViewById(R.id.minute);
		ArrayList<String> minutes = new ArrayList<String>();
		minutes.add("00");
		minutes.add("15");
		minutes.add("30");
		minutes.add("45");
		try {
			ArrayList<Timeslot> times = (ArrayList<Timeslot>) mWrapper
					.getModel().getRestaurant().getUnavailableTimes();

			for (int i = 0; i < times.size(); i++) {
				Calendar time = times.get(i).getStartTime();
				Calendar currTime = CalendarViewFragment.month;
				if (time.get(Calendar.MONTH) == currTime.get(Calendar.MONTH)) {
					if (time.get(Calendar.DAY_OF_MONTH) == currTime
							.get(Calendar.DAY_OF_MONTH)) {
						if (time.get(Calendar.HOUR_OF_DAY) == currTime
								.get(Calendar.HOUR_OF_DAY)) {
							minutes.remove(Integer.toString(time
									.get(Calendar.MINUTE)));
						}
					}
				}
			}
		} catch (Exception e) {

		}
		String[] displayMinutes = new String[minutes.size()];
		for (int i = 0; i < displayMinutes.length; i++) {
			displayMinutes[i] = minutes.get(i);
		}
		mMinuteSpinner.setMinValue(0);
		mMinuteSpinner.setMaxValue(0);
		mMinuteSpinner.setDisplayedValues(displayMinutes);
		mMinuteSpinner.setMaxValue(displayMinutes.length - 1);
	}

	@Override
	public void receiveReservationId(int id) {
		if (id >= 0) {
			Toast.makeText(mWrapper,
					"Reservation Successful. Your id is " + id,
					Toast.LENGTH_LONG).show();
		}
		if (id == -1) {
			Toast.makeText(mWrapper,
					"Reservation Unsuccessful, pick another time",
					Toast.LENGTH_LONG).show();
		}
	}
}
