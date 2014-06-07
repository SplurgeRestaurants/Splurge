package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Calendar;

import ucsd.cs110.splurge.model.Restaurant;
import ucsd.cs110.splurge.model.Timeslot;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Fragment displaying a calendar for selecting a date on which to make a
 * reservation.
 */
public class CalendarViewFragment extends SuperFragment {
	/**
	 * Reference to the current restaurant
	 */
	private Restaurant mRestaurant;
	/**
	 * Reference to the calendar
	 */
	static Calendar month;
	/**
	 * Reference to the adapter, to populate the grid view
	 */
	static CalendarAdapter adapter;
	/**
	 * Add items to calendar
	 */
	static Handler handler;
	/**
	 * Array of unavailable times
	 */
	static ArrayList<String> mUnavailableTimes;
	/**
	 * Array of timeslots of the unavailable times
	 */
	static ArrayList<Timeslot> mTakenTimes;
	/**
	 * String for the intent message
	 */
	static final String DATE = "date";

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
		mUnavailableTimes = mRestaurant.getUnavailableDays(month);
		adapter = new CalendarAdapter(getActivity(), month);
		GridView gridview = (GridView) ret.findViewById(R.id.gridview);
		gridview.setAdapter(adapter);
		handler = new Handler();
		handler.post(calendarUpdater);
		TextView title = (TextView) ret.findViewById(R.id.title);
		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
		return ret;
	}

	/**
	 * Refresh the calendar when the array of items has been changed
	 * 
	 * @param context
	 *            Current context
	 */
	public static void refreshCalendar(Context context) {
		TextView title = (TextView) ((Activity) context)
				.findViewById(R.id.title);
		adapter.refreshDays();
		adapter.notifyDataSetChanged();
		handler.post(calendarUpdater);

		title.setText(android.text.format.DateFormat.format("MMMM yyyy", month));
	}

	/**
	 * Class to update the calendar dynamically
	 */
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

	/**
	 * Hooks up a listener to the calendar grid.
	 * 
	 * @param listener
	 *            Listener to respond to click events on the calendar grid.
	 */
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