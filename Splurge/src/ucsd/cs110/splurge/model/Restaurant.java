package ucsd.cs110.splurge.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import android.graphics.PointF;

/**
 * Object containing all information specific to an individual restaurant.
 */
public class Restaurant {

	/**
	 * Menus available for the restaurant. These are labeled individually.
	 */
	private Collection<FoodMenu> mMenus;
	/**
	 * Times which are currently unavailable for reservation.
	 * 
	 * It is expected that the default iteration through this Collection
	 * provides unavailable times in sorted order.
	 */
	private Collection<Timeslot> mUnavailableTimes;
	/**
	 * Hours during which this restaurant is open for business, indexed by day
	 * starting at Monday.
	 */
	private Timeslot[] mOpenHours;
	/**
	 * Name of the restaurant.
	 */
	private String mRestaurantName;
	/**
	 * Logo of the restaurant
	 */
	private int image;
	private int mId;

	private PointF location;

	/**
	 * Creates a new restaurant with nothing but a name.
	 * 
	 * Container objects are initialized, but not populated. Population is to
	 * occur elsewhere, and it is the responsibility of the owning object to
	 * undertake.
	 * 
	 * @param name
	 *            The name of the Restaurant.
	 */
	public Restaurant(String name) {
		mRestaurantName = name;
		mMenus = new ArrayList<FoodMenu>();
		mUnavailableTimes = new ArrayList<Timeslot>();

		mOpenHours = new Timeslot[7];
	}

	/**
	 * Retrieves the name of the restaurant in user-friendly form.
	 * 
	 * @return A printable name of the restaurant.
	 */
	public String getName() {
		return mRestaurantName;
	}

	/**
	 * Checks through the unavailable timeslots to determine if the given
	 * instant of time is unavailable.
	 * 
	 * @param time
	 *            The instant in time to check for unavailability.
	 * @return <code>true</code> if the time is within reserved space.
	 * @see isTimeUnavailalble(Calendar)
	 */
	private boolean isTimeWithinReservation(Calendar time) {
		for (Timeslot t : mUnavailableTimes) {
			if (t.withinTimeslot(time))
				return true;
		}
		return false;
	}

	/**
	 * Determines whether the given time is taken.
	 * 
	 * @param time
	 *            The point in time to check.
	 * @return <code>true</code> if the time is unavailable, <code>false</code>
	 *         otherwise.
	 */
	public boolean isTimeUnavailable(Calendar time) {
		return isTimeWithinReservation(time);
	}

	/**
	 * Informs the system that the given timeslot is unavailable.
	 * 
	 * @param time
	 *            The unavailable timeslot.
	 */
	public void addUnavailableTime(Timeslot time) {
		mUnavailableTimes.add(time);
	}

	/**
	 * Retrieves a menu by its title. If no such menu exist, this function will
	 * return <code>null</code>.
	 * 
	 * @param label
	 *            The title of the menu.
	 * @return The menu, or <code>null</code> if no such menu exists.
	 */
	public FoodMenu getMenuByLabel(String label) {
		for (FoodMenu m : mMenus) {
			if (m.getMenuName().equals(label))
				return m;
		}
		return null;
	}

	/**
	 * Gets the hours available for a given day. If hours are unavailable on
	 * that day, then the Timeslot value will be <code>null</code>.
	 * 
	 * @param day
	 *            Enum value depicting which day to check.
	 * @return The Timeslot depicting hours for the given day, or
	 *         <code>null</code> if closed all day.
	 */
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

	/**
	 * Sets the longitude of the restaurant's physical location.
	 * 
	 * @param x
	 *            The longitude of the restaurant's location.
	 */
	public void setLocX(float x) {
		location.x = x;
	}

	/**
	 * Sets the latitude of the restaurant's physical location.
	 * 
	 * @param y
	 *            The latitude of the restaurant's location.
	 */
	public void setLocY(float y) {
		location.y = y;
	}

	/**
	 * Sets the location to the given latitude and longitude pair.
	 * 
	 * @param point
	 *            Point representation of the restaurant's location.
	 */
	public void setLocation(PointF point) {
		location = point;
	}

	/**
	 * Adds a FoodMenu to the restaurant listing.
	 * 
	 * @param menu
	 *            The Menu to add.
	 * @return <code>true</code> on successful add.
	 * @throws IllegalArgumentException
	 *             if a menu exists sharing the same name.
	 */
	public boolean addFoodMenu(FoodMenu menu) {
		for (FoodMenu m : mMenus) {
			if (m.getMenuName().equals(menu.getMenuName())) {
				throw new IllegalArgumentException("Menus cannot share names.");
			}
		}
		return mMenus.add(menu);
	}

	public int getImage() {
		return image;
	}

	public void setImage(int imageId) {
		image = imageId;
	}

	public void setId(int id) {
		mId = id;
	}

	public int getId() {
		return mId;
	}
}
