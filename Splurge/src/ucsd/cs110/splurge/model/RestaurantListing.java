package ucsd.cs110.splurge.model;

import ucsd.cs110.splurge.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

/**
 * Container class to hold restaurant name and identification number pairs. The
 * intended use for this is as a storage means permitting the app to display
 * available restaurants and select a restaurant to request from the server.
 */
public class RestaurantListing implements Comparable<RestaurantListing> {

	/**
	 * Displayable name of the restaurant.
	 */
	private String mRestaurantName;
	/**
	 * Identification number of the restaurant.
	 */
	private int mRestaurantId;
	/**
	 * Displayable logo for the restaurant.
	 */
	private Bitmap mRestaurantLogo;

	/**
	 * Creates a new restaurant listing with the given name, image, and
	 * identification number.
	 * 
	 * @param name
	 *            Displayable name of the restaurant.
	 * @param id
	 *            Identification number of the restaurant as it appears in the
	 *            database.
	 * @param image
	 *            Restaurant logo as it will appear to the patron.
	 */
	public RestaurantListing(String name, int id, Bitmap image) {
		setRestaurantName(name);
		setRestaurantId(id);
		setRestaurantImage(image);
	}

	/**
	 * Creates a new restaurant listing with the given name, identification
	 * number, and image given as a Base64-encoded string.
	 * 
	 * @param name
	 *            Displayable name of the restaurant.
	 * @param id
	 *            Identification number of the restaurant as it appears in the
	 *            database.
	 * @param base64
	 *            Base64-encoded restaurant logo as it will appear to the
	 *            patron.
	 */
	public RestaurantListing(String name, int id, String base64, Context awful) {
		this(name, id, BitmapFactory.decodeResource(awful.getResources(),
				R.drawable.restaurant));
		byte[] a = Base64.decode(base64, Base64.DEFAULT);
		if (a.length > 0)
			setRestaurantImage(BitmapFactory.decodeByteArray(a, 0, a.length));
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

	/**
	 * Sets the restaurant image to the given Bitmap object.
	 * 
	 * @param image
	 *            Restaurant logo as it is to be displayed to the user.
	 */
	private void setRestaurantImage(Bitmap image) {
		mRestaurantLogo = image;
	}

	/**
	 * Retrieves the logo as it is to be displayed to the user.
	 * 
	 * @return Displayable logo.
	 */
	public Bitmap getRestaurantImage() {
		return mRestaurantLogo;
	}

	@Override
	public boolean equals(Object o) {
		return o != null
				&& (o instanceof RestaurantListing)
				&& ((RestaurantListing) o).getRestaurantId() == getRestaurantId();
	}

	@Override
	public int compareTo(RestaurantListing another) {
		return this.getRestaurantName().compareToIgnoreCase(
				another.getRestaurantName());
	}
}
