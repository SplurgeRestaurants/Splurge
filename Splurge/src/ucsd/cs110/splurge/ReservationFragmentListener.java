package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.Toast;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class ReservationFragmentListener extends SuperListener implements
		OnClickListener, OnScrollListener {

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
			String selectedHour = ReservationFragment.mHours[hours.getValue()];
			String selectedMinute = ReservationFragment.mMinutes[minutes
					.getValue()];
			String selectedAmPm = ReservationFragment.mAmPmString[amPm
					.getValue()];
			int getPartySize = mPartySize.getValue();
			Log.e("Splurge", selectedHour + " " + selectedMinute + " "
					+ selectedAmPm);
			Log.e("Splurge", "Party size is " + getPartySize);
			if (reservationSuccessful) {
				Toast.makeText(mWrapper, "Reservation Successful",
						Toast.LENGTH_LONG).show();
			} else {
				Toast.makeText(mWrapper,
						"Reservation Unsuccessful, pick another time",
						Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onScrollStateChange(NumberPicker view, int scrollState) {
		if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
			switch (view.getId()) {
			case R.id.hour:
				break;
			case R.id.minute:
				break;
			}
		}
	}
}