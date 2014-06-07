package ucsd.cs110.splurge;

import java.util.Calendar;
import java.util.GregorianCalendar;

import ucsd.cs110.splurge.connectivity.tasks.ReservationRequestListener;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class ReservationFragmentListener extends SuperListener implements
		OnClickListener, ReservationRequestListener {

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
			EditText partyName = (EditText) mWrapper
					.findViewById(R.id.form_party_name);
			String pName = partyName.getText().toString();
			if (pName.length() < 1) {
				Toast.makeText(mWrapper, "Missing Field(s)", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			NumberPicker mPartySize = (NumberPicker) mWrapper
					.findViewById(R.id.party_size);
			NumberPicker hours = (NumberPicker) mWrapper
					.findViewById(R.id.hour);
			NumberPicker minutes = (NumberPicker) mWrapper
					.findViewById(R.id.minute);
			NumberPicker amPm = (NumberPicker) mWrapper.findViewById(R.id.amPm);
			String selectedAmPm = ReservationFragment.mAmPmString[amPm
					.getValue()];
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
			int getPartySize = mPartySize.getValue() + 1;
			Intent intent = mWrapper.getIntent();
			String date = intent.getStringExtra(CalendarViewFragment.DATE);
			Log.e("Splurge", "Party Name is " + pName);
			Log.e("Splurge", date);
			Log.e("Splurge", selectedHour + " " + selectedMinute + " "
					+ selectedAmPm);
			Log.e("Splurge", "Party size is " + getPartySize);

			// Populate the starting time with given values
			GregorianCalendar startTime = new GregorianCalendar();
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			int day = Integer.parseInt(date.substring(8, 10));
			startTime.set(Calendar.HOUR_OF_DAY, selectedHour);
			startTime.set(Calendar.MINUTE, selectedMinute);
			startTime.set(Calendar.MONTH, month - 1);
			startTime.set(Calendar.YEAR, year);
			startTime.set(Calendar.DAY_OF_MONTH, day);
			Log.e("Splurge", "The set date " + startTime.getTime().toString());
			int result = mWrapper.getModel().requestReservation(getPartySize,
					pName, startTime, this);
			startTime.set(Calendar.SECOND, 0);
			if (result >= 0) {
				Toast.makeText(
						mWrapper,
						String.format("Reservation Successful. Your id is %d.",
								result), Toast.LENGTH_LONG).show();
				mWrapper.getFragmentManager().popBackStack();
				mWrapper.getFragmentManager().popBackStack();
				mWrapper.changeFragment(new RestaurantMainMenuFragment(),
						new RestaurantMainMenuListener(mWrapper));
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
	public void receiveReservationId(int id) {
		if (id >= 0) {
			Toast.makeText(
					mWrapper,
					String.format("Reservation Successful. Your id is %d.", id),
					Toast.LENGTH_LONG).show();
			mWrapper.changeFragment(new RestaurantMainMenuFragment(),
					new RestaurantMainMenuListener(mWrapper));
		}
		if (id == -1) {
			Toast.makeText(mWrapper,
					"Reservation Unsuccessful, pick another time",
					Toast.LENGTH_LONG).show();
		}
	}
}
