package ucsd.cs110.splurge;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class ReservationFragmentListener extends SuperListener implements
		OnClickListener {

	static final int PICK_DATE_REQUEST = 1;

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
		DatePicker dp = (DatePicker) mWrapper.findViewById(R.id.datePicker1);
		Intent intent = new Intent();
		intent.putExtra("date",
				dp.getYear() + "-" + dp.getMonth() + "-" + dp.getDayOfMonth());
		mWrapper.setIntent(intent);
		Log.e("whelp", "wut");
		mWrapper.changeFragment(new CalendarViewFragment(), null);
	}
}
