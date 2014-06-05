package ucsd.cs110.splurge;

import java.util.Calendar;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

/**
 * Listener for the CalendarViewFragment. This handles all user interactions
 * with the fragment.
 */
public class CalenderViewFragmentListener extends SuperListener implements
		OnClickListener, OnItemClickListener {

	/**
	 * Creates a new listener.
	 * 
	 * @param context
	 *            WrapperActivity for use in accessing the model and a Context
	 *            updating the view.
	 */
	public CalenderViewFragmentListener(WrapperActivity context) {
		super(context);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.previous:
			if (CalendarViewFragment.month.get(Calendar.MONTH) - 1 < Calendar
					.getInstance().get(Calendar.MONTH)
					&& CalendarViewFragment.month.get(Calendar.YEAR) == Calendar
							.getInstance().get(Calendar.YEAR)) {
				return;
			}
			if (CalendarViewFragment.month.get(Calendar.MONTH) == CalendarViewFragment.month
					.getActualMinimum(Calendar.MONTH)) {
				CalendarViewFragment.month.set((CalendarViewFragment.month
						.get(Calendar.YEAR) - 1), CalendarViewFragment.month
						.getActualMaximum(Calendar.MONTH), 1);
			} else {
				CalendarViewFragment.month.set(Calendar.MONTH,
						CalendarViewFragment.month.get(Calendar.MONTH) - 1);
			}
			CalendarViewFragment.refreshCalendar(mWrapper);
			break;
		case R.id.next:
			if (CalendarViewFragment.month.get(Calendar.MONTH) == CalendarViewFragment.month
					.getActualMaximum(Calendar.MONTH)) {
				CalendarViewFragment.month.set((CalendarViewFragment.month
						.get(Calendar.YEAR) + 1), CalendarViewFragment.month
						.getActualMinimum(Calendar.MONTH), 1);
			} else {
				CalendarViewFragment.month.set(Calendar.MONTH,
						CalendarViewFragment.month.get(Calendar.MONTH) + 1);
			}
			CalendarViewFragment.refreshCalendar(mWrapper);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		if (position + 1 < Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
				&& CalendarViewFragment.month.get(Calendar.MONTH) == Calendar
						.getInstance().get(Calendar.MONTH)
				&& CalendarViewFragment.month.get(Calendar.YEAR) == Calendar
						.getInstance().get(Calendar.YEAR)) {
			view.setClickable(false);
			return;
		}
		TextView date = (TextView) view.findViewById(R.id.date);
		if (date instanceof TextView && !date.getText().equals("")) {

			Intent intent = new Intent();
			String day = date.getText().toString();
			if (day.length() == 1) {
				day = "0" + day;
			}
			intent.putExtra(
					CalendarViewFragment.DATE,
					android.text.format.DateFormat.format("yyyy-MM",
							CalendarViewFragment.month) + "-" + day);
			mWrapper.setIntent(intent);
			mWrapper.changeFragment(new ReservationFragment(),
					new ReservationFragmentListener(mWrapper));
		}

	}
}