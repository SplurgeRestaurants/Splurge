package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.NumberPicker;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class ReservationFragmentListener extends SuperListener implements
		OnClickListener {

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
			EditText mPartySize = (EditText) mWrapper
					.findViewById(R.id.form_party_size);
			NumberPicker hours = (NumberPicker) mWrapper
					.findViewById(R.id.hour);
			NumberPicker minutes = (NumberPicker) mWrapper
					.findViewById(R.id.minute);
			NumberPicker amPm = (NumberPicker) mWrapper.findViewById(R.id.amPm);
			String selectedHour = ReservationFragment.mHours[hours.getValue()];
			String selectedMinute = ReservationFragment.mMinutes[minutes
					.getValue()];
			String selectedAmPm = ReservationFragment.mAmPmString[amPm
					.getValue()];
			String getPartySize = mPartySize.getText().toString();
			Log.e("Splurge", selectedHour + " " + selectedMinute + " "
					+ selectedAmPm);
			Log.e("Splurge", "Party size is " + getPartySize);
			break;
		default:
			break;
		}
	}
}
