package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Calendar;

import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.Timeslot;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

public class CalendarViewFragment extends SuperFragment {
	private Restaurant mRestaurant;
	static Calendar month;
	static CalendarAdapter adapter;
	static Handler handler;
	static ArrayList<String> mUnavailableTimes;
	static ArrayList<Timeslot> mTakenTimes;

	/*
	 * TODO (dqthai) calendar items will have a way to indicate that there are
	 * no available times that day make it unclickable time picker should on
	 * show available times page preorder? submit form to website
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.calendar, container, false);
		month = Calendar.getInstance();
		mRestaurant = getWrapperActivity().getModel().getRestaurant();
		mUnavailableTimes = new ArrayList<String>();
		getUnavailableDays();
		adapter = new CalendarAdapter(getActivity(), month);
		GridView gridview = (GridView) ret.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		handler = new Handler();
		handler.post(calendarUpdater);
		TextView title = (TextView) ret.findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));

		return ret;
	}

	public void getUnavailableDays() {
		int day, currMonth;
		mTakenTimes = (ArrayList<Timeslot>) mRestaurant.getUnavailableTimes();
		for (int i = 0; i < mTakenTimes.size(); i++) {
			day = mTakenTimes.get(i).getStartTime().get(Calendar.DATE);
			currMonth = mTakenTimes.get(i).getStartTime().get(Calendar.MONTH);
			Log.e("Splurge", currMonth + " " + day);
			String chosenDay = Integer.toString(day);
			if (month.get(Calendar.MONTH) == mTakenTimes.get(i).getStartTime()
					.get(Calendar.MONTH)
					&& isDayFull(mTakenTimes.get(i).getStartTime())) {
				mUnavailableTimes.add(chosenDay);
			}
		}
	}

	public boolean isDayFull(Calendar time) {
		int currDay = time.get(Calendar.DATE);
		ArrayList<Timeslot> sameDay = new ArrayList<Timeslot>();
		mTakenTimes = (ArrayList<Timeslot>) mRestaurant.getUnavailableTimes();
		for (int i = 0; i < mTakenTimes.size(); i++) {
			if (currDay == mTakenTimes.get(i).getStartTime().get(Calendar.DATE)) {
				sameDay.add(mTakenTimes.get(i));
			}
		}
		int startHour = mRestaurant
				.getHoursForDay(time.get(Calendar.DAY_OF_WEEK)).getStartTime()
				.get(Calendar.HOUR_OF_DAY);
		int endHour = mRestaurant.getHoursForDay(Calendar.DAY_OF_WEEK)
				.getEndTime().get(Calendar.HOUR_OF_DAY);
		int size = (endHour - startHour) * 4;
		if (size == sameDay.size()) {
			return true;
		}
		return false;
	}

	public static void refreshCalendar(Context context) {
		TextView title = (TextView) ((Activity) context)
				.findViewById(R.id.title);
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater);

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	public static Runnable calendarUpdater = new Runnable() {

		@Override
		public void run() {
			adapter.setItems(mUnavailableTimes);
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