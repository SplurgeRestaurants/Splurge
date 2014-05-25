package ucsd.cs110.splurge;

import java.util.Calendar;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class CalenderViewFragmentListener extends SuperListener implements
		OnClickListener, OnItemClickListener {

	public CalenderViewFragmentListener(WrapperActivity context) {
		super(context);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.previous:
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
		TextView date = (TextView) view.findViewById(R.id.date);
		if (date instanceof TextView && !date.getText().equals("")) {

			Intent intent = new Intent();
			String day = date.getText().toString();
			if (day.length() == 1) {
				day = "0" + day;
			}
			// return chosen date as string format
			intent.putExtra(
					"date",
					android.text.format.DateFormat.format("yyyy-MM",
							CalendarViewFragment.month) + "-" + day);
			mWrapper.setResult(Activity.RESULT_OK, intent);
			mWrapper.finish();
		}

	}
}
