package ucsd.cs110.splurge;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class ReservationFragmentListener extends SuperListener implements
		OnClickListener, OnTimeChangedListener {

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
			NumberPicker hours = (NumberPicker) mWrapper
					.findViewById(R.id.hour);
			NumberPicker minutes = (NumberPicker) mWrapper
					.findViewById(R.id.minute);
			NumberPicker amPm = (NumberPicker) mWrapper.findViewById(R.id.amPm);
			Intent intent = new Intent();
			// intent.putExtra("date",
			// dp.getYear() + "-" + dp.getMonth() + "-" + dp.getDayOfMonth());
			String selectedHour = ReservationFragment.mHours[hours.getValue()];
			String selectedMinute = ReservationFragment.mMinutes[minutes
					.getValue()];
			String selectedAmPm = ReservationFragment.mAmPmString[amPm
					.getValue()];
			mWrapper.setIntent(intent);
			Log.e("Splurge", selectedHour + " " + selectedMinute + " "
					+ selectedAmPm);
			break;
		default:
			break;
		}
	}

	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {

	}
}
