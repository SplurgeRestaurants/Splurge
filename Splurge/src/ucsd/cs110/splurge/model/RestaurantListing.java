package ucsd.cs110.splurge.model;

/**
 * Container class to hold restaurant name and identification number pairs. The
 * intended use for this is as a storage means permitting the app to display
 * available restaurants and select a restaurant to request from the server.
 */
public class RestaurantListing {

	/**
	 * Displayable name of the restaurant.
	 */
	private String mRestaurantName;
	/**
	 * Identification number of the restaurant.
	 */
	private int mRestaurantId;

	/**
	 * Creates a new restaurant listing with the given name and identification
	 * number.
	 * 
	 * @param name
	 *            Displayable name of the restaurant.
	 * @param id
	 *            Identification number of the restauarant as it appears in the
	 *            database.
	 */
	public RestaurantListing(String name, int id) {
		setRestaurantName(name);
		setRestaurantId(id);
	}

	/**
	 * Gets the printable name of the restaurant.
	 * 
	 * @return Printable restaurant name.
	 */
	public String getRestaurantName() {
		return mRestaurantName;
	}

	/**
	 * Gets the identification number of the restaurant, as it appears on the
	 * server.
	 * 
	 * @return Restaurant's identification number.
	 */
	public int getRestaurantId() {
		return mRestaurantId;
	}

	/**
	 * Sets the restaurant name. Please input a nicely formatted name suitable
	 * for printing.
	 * 
	 * @param name
	 *            A user-friendly name.
	 */
	private void setRestaurantName(String name) {
		mRestaurantName = name;
	}

	/**
	 * Sets the restaurant identification number. This should correspond to a
	 * valid identification number in the database.
	 * 
	 * @param id
	 *            The restaurant's identification number.
	 */
	private void setRestaurantId(int id) {
		mRestaurantId = id;
	}

	@Override
	public boolean equals(Object o) {
		return o != null
				&& (o instanceof RestaurantListing)
				&& ((RestaurantListing) o).getRestaurantId() == getRestaurantId();
	}
}
