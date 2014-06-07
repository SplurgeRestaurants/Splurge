package ucsd.cs110.splurge.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.util.Log;

/**
 * Object containing all information specific to an individual restaurant.
 */
public class Restaurant {

	/**
	 * Menus available for the restaurant. These are labeled individually.
	 */
	private ArrayList<FoodMenu> mMenus;
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
	private Bitmap mImage;
	/**
	 * Identification number of the restaurant.
	 */
	private int mId;

	private PointF location;
	private String mPhoneNumber;
	private String mStreetAddress;
	private String mZipcode;
	private int numberOfReservationsPerHour = 4;

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
		mStreetAddress = "";
		mZipcode = "";
		mPhoneNumber = "";
		location = new PointF();

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

	private boolean isTimeDuringClosedHours(Calendar time) {
		return getHoursForDay(time.get(Calendar.DAY_OF_WEEK)).contains(time);
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
		return isTimeDuringClosedHours(time) || isTimeWithinReservation(time);
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
	 * Gets the list of unavailable times
	 * 
	 * @return The unavailable time slots
	 */
	public Collection<Timeslot> getUnavailableTimes() {
		return mUnavailableTimes;
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
	 * Retrieves an array of the labels for the menus available for this
	 * restaurant. This is not guaranteed to be in any particular order.
	 * 
	 * @return An array of labels fo rhte menus available for this restaurant.
	 */
	public String[] getMenuLabels() {
		String[] ret = new String[mMenus.size()];
		int i = 0;
		for (FoodMenu menu : mMenus) {
			ret[i] = menu.getMenuName();
			++i;
		}
		return ret;
	}

	/**
	 * Retrieves a menu by its index. There is no guarantee that a particular
	 * index will correspond to the same meal across multiple restaurants,
	 * although a given index will always return the same menu for the same
	 * restaurant.
	 * 
	 * @param index
	 *            The index number for the menu to retrieve.
	 * @return A menu.
	 */
	public FoodMenu getFoodMenuByIndex(int index) {
		return mMenus.get(index);
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
	public Timeslot getHoursForDay(int day) {
		switch (day) {
		case Calendar.SUNDAY:
			return mOpenHours[0];
		case Calendar.MONDAY:
			return mOpenHours[1];
		case Calendar.TUESDAY:
			return mOpenHours[2];
		case Calendar.WEDNESDAY:
			return mOpenHours[3];
		case Calendar.THURSDAY:
			return mOpenHours[4];
		case Calendar.FRIDAY:
			return mOpenHours[5];
		case Calendar.SATURDAY:
			return mOpenHours[6];
		default:
			throw new IllegalArgumentException("Illegal day of week: " + day);
		}
	}

	public void setHoursForDay(int day, Timeslot ts) {
		switch (day) {
		case Calendar.SUNDAY:
			mOpenHours[0] = ts;
			break;
		case Calendar.MONDAY:
			mOpenHours[1] = ts;
			break;
		case Calendar.TUESDAY:
			mOpenHours[2] = ts;
			break;
		case Calendar.WEDNESDAY:
			mOpenHours[3] = ts;
			break;
		case Calendar.THURSDAY:
			mOpenHours[4] = ts;
			break;
		case Calendar.FRIDAY:
			mOpenHours[5] = ts;
			break;
		case Calendar.SATURDAY:
			mOpenHours[6] = ts;
			break;
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

	/**
	 * Gets the logo for this Restaurant.
	 * 
	 * @return This restaurant's logo.
	 */
	public Bitmap getImage() {
		return mImage;
	}

	/**
	 * Sets the logo for this Restaurant.
	 * 
	 * @param image
	 *            This restaurant's new logo.
	 */
	public void setImage(Bitmap image) {
		mImage = image;
	}

	/**
	 * Sets the stored identification number for this restaurant. This will be
	 * used in the future to make restaurant-specific requests, and should
	 * correspond to the identification number of the restaurant in the
	 * database.
	 * 
	 * @param id
	 *            The new identification number.
	 */
	public void setId(int id) {
		mId = id;
	}

	/**
	 * Gets the identification number for this restaurant. This is to be used
	 * with calls to make reservations, and other restaurant-specific requests.
	 * 
	 * @return This restaurant's identification number.
	 */
	public int getId() {
		return mId;
	}

	/**
	 * Set the phone number of the restaurant
	 * 
	 * @param phoneNumber
	 *            The restaurant's phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		mPhoneNumber = phoneNumber;
	}

	/**
	 * Get the phone number of the restaurant
	 * 
	 * @return The restaurant's phone number
	 */
	public String getPhoneNumber() {
		return mPhoneNumber;
	}

	public String getStreetAddress() {
		return mStreetAddress;
	}

	public void setStreetAddress(String mStreetAddress) {
		this.mStreetAddress = mStreetAddress;
	}

	public String getZipcode() {
		return mZipcode;
	}

	public void setZipcode(String mZipcode) {
		this.mZipcode = mZipcode;
	}

	public ArrayList<String> getUnavailableDays(Calendar month) {
		int day, currMonth;
		ArrayList<String> unavailableDays = new ArrayList<String>();
		ArrayList<Timeslot> mTakenTimes = (ArrayList<Timeslot>) getUnavailableTimes();
		for (int i = 0; i < mTakenTimes.size(); i++) {
			day = mTakenTimes.get(i).getStartTime().get(Calendar.DATE);
			currMonth = mTakenTimes.get(i).getStartTime().get(Calendar.MONTH);
			Log.e("Splurge", currMonth + " " + day);
			String chosenDay = Integer.toString(day);
			if (month.get(Calendar.MONTH) == mTakenTimes.get(i).getStartTime()
					.get(Calendar.MONTH)
					&& isDayFull(mTakenTimes.get(i).getStartTime())) {
				unavailableDays.add(chosenDay);
			}
		}
		return unavailableDays;
	}

	public boolean isDayFull(Calendar time) {
		int currDay = time.get(Calendar.DAY_OF_YEAR);
		ArrayList<Timeslot> sameDay = new ArrayList<Timeslot>();
		ArrayList<Timeslot> mTakenTimes = (ArrayList<Timeslot>) getUnavailableTimes();
		for (int i = 0; i < mTakenTimes.size(); i++) {
			if (currDay == mTakenTimes.get(i).getStartTime()
					.get(Calendar.DAY_OF_YEAR)) {
				sameDay.add(mTakenTimes.get(i));
			}
		}
		int startHour = getHoursForDay(time.get(Calendar.DAY_OF_WEEK))
				.getStartTime().get(Calendar.HOUR_OF_DAY);
		int endHour = getHoursForDay(time.get(Calendar.DAY_OF_WEEK))
				.getEndTime().get(Calendar.HOUR_OF_DAY);
		int size = (endHour - startHour) * 4;
		if (size == sameDay.size()) {
			return true;
		}
		return false;
	}

	public String[] getAvailableHours(Calendar time) {
		int startHour = getHoursForDay(time.get(Calendar.DAY_OF_WEEK))
				.getStartTime().get(Calendar.HOUR_OF_DAY);
		int endHour = getHoursForDay(time.get(Calendar.DAY_OF_WEEK))
				.getEndTime().get(Calendar.HOUR_OF_DAY);
		ArrayList<String> hours = new ArrayList<String>();
		for (int i = startHour; i <= endHour; i++) {
			Calendar c = Calendar.getInstance();
			c.setTime(time.getTime());
			c.set(Calendar.SECOND, 0);
			c.set(Calendar.HOUR, i);
			boolean addable = false;
			for (int n : new int[] { 0, 15, 30, 45 }) {
				c.set(Calendar.MINUTE, n);
				if (!isTimeUnavailable(c)) {
					addable = true;
					break;
				}
			}
			if (addable) {
				hours.add(Integer.toString(i));
			}
		}
		return hours.toArray(new String[hours.size()]);
	}
}
