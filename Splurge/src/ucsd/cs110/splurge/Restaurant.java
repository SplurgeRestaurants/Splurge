package ucsd.cs110.splurge;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

public class Restaurant {

	private Collection<FoodMenu> mMenus;
	private Collection<Timeslot> mUnavailableTimes;
	private Timeslot[] mOpenHours;
	private String mRestaurantName;

	public Restaurant(String name) {
		mRestaurantName = name;
		mMenus = new ArrayList<FoodMenu>();
		mUnavailableTimes = new ArrayList<Timeslot>();

		// Temporary values to avoid NullPointerExceptions
		mOpenHours = new Timeslot[7];
		for (int i = 0; i < mOpenHours.length; i++) {
			mOpenHours[i] = new Timeslot(Calendar.getInstance(),
					Calendar.getInstance());
		}
	}
	
	public String getName() {
		return mRestaurantName;
	}

	private boolean isTimeWithinReservation(Calendar time) {
		for (Timeslot t : mUnavailableTimes) {
			if (t.withinTimeslot(time))
				return true;
		}
		return false;
	}

	public boolean isTimeUnavailable(Calendar time) {
		return isTimeWithinReservation(time);
	}

	public FoodMenu getMenuByLabel(String label) {
		for (FoodMenu m : mMenus) {
			if (m.getMenuName().equals(label))
				return m;
		}
		return null;
	}

	public Timeslot getHoursForDay(DayOfWeek day) {
		switch (day) {
		case MONDAY:
			return mOpenHours[0];
		case TUESDAY:
			return mOpenHours[1];
		case WEDNESDAY:
			return mOpenHours[2];
		case THURSDAY:
			return mOpenHours[3];
		case FRIDAY:
			return mOpenHours[4];
		case SATURDAY:
			return mOpenHours[5];
		case SUNDAY:
			return mOpenHours[6];
		default:
			throw new IllegalArgumentException("Illegal day of week: " + day);
		}
	}
}
