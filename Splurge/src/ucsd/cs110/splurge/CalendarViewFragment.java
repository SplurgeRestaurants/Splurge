package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarViewFragment extends SuperFragment {

	static Calendar month;
	static CalendarAdapter adapter;
	static Handler handler;
	static ArrayList<String> items; // container to store some random
									// calendar items

	/*
	 * TODO (dqthai) shouldnt be able to click on calendar items before today
	 * calendar items will have a way to indicate that there are no available
	 * times that day make it unclickable calendar items go to a time picker
	 * time picker should on show available times page should also have a form
	 * for party size preorder? submit form to website
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.calendar, container, false);
		month = Calendar.getInstance();
		// onNewIntent(getActivity().getIntent());

		items = new ArrayList<String>();
		adapter = new CalendarAdapter(getActivity(), month);

		GridView gridview = (GridView) ret.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);

		handler = new Handler();
		handler.post(calendarUpdater);

		TextView title = (TextView) ret.findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		return ret;
	}

	public static void refreshCalendar(Context context) {
		TextView title = (TextView) ((Activity) context)
				.findViewById(R.id.title);

		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater); // generate some random calendar items

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	public void onNewIntent(Intent intent) {
		String date = intent.getStringExtra("date");
		String[] dateArr = date.split("-"); // date format is yyyy-mm-dd
		month.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]),
				Integer.parseInt(dateArr[2]));
	}

	public static Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			items.clear();

			adapter.setItems(items);
			adapter.notifyDataSetChanged();
		}
	};

	/**
	 * set listener for each button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setCalendarListener(OnClickListener listener) {
		(getView().findViewById(R.id.previous)).setOnClickListener(listener);
		(getView().findViewById(R.id.next)).setOnClickListener(listener);
	}

	public void setCalendarGridListener(OnItemClickListener listener) {
		((GridView) getView().findViewById(R.id.gridview))
				.setOnItemClickListener(listener);
	}

	/**
	 * Set mSuperListener to the buttons and grid
	 */
	@Override
	public void onStart() {
		setCalendarGridListener((OnItemClickListener) mSuperListener);
		setCalendarListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}