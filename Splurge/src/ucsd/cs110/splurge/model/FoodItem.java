package ucsd.cs110.splurge.model;

import android.graphics.Bitmap;

/**
 * Class for storing information about items of food available at a given
 * restaurant.
 */
public class FoodItem {

	/**
	 * Name of the food item in user-readable format.
	 */
	private String mName;
	/**
	 * Image representation of the food item.
	 */
	private Bitmap mImage;
	/**
	 * Tracker for determining whether the user has selected the item.
	 */
	private boolean mSelected;
	/**
	 * Cost of a single item.
	 */
	private double mPrice;
	/**
	 * Description of the item as presented by the manager.
	 */
	private String mDescription;

	/**
	 * Creates a FoodItem with the bare minimum requirements. However, it is not
	 * recommended that all fields are provided via the setter methods.
	 * 
	 * @param name
	 *            The name of the food item, as readable by the user.
	 */
	public FoodItem(String name) {
		this.mName = name;
	}

	/**
	 * Create a shallow copy of another food item.
	 * 
	 * @param item
	 *            The item to copy.
	 */
	public FoodItem(FoodItem item) {
		mName = item.mName;
		mImage = item.mImage;
		mSelected = item.mSelected;
		mPrice = item.mPrice;
		mDescription = item.mDescription;
	}

	/**
	 * Retrieves the name of the food item, as readable by the user.
	 * 
	 * @return A user-readable name for the food item.
	 */
	public String getName() {
		return mName;
	}

	@Override
	public String toString() {
		return getName();
	}

	/**
	 * Sets the image for the food item.
	 * 
	 * @param image
	 *            The new image.
	 */
	public void setImage(Bitmap image) {
		mImage = image;
	}

	/**
	 * Retrieves a picture of the food item.
	 * 
	 * @return The food item's picture.
	 */
	public Bitmap getImage() {
		return mImage;
	}

	/**
	 * Gets the selected status of the food item.
	 * 
	 * @return <code>true</code> if the food item is selected,
	 *         <code>false</code> otherwise.
	 */
	public boolean isSelected() {
		return mSelected;
	}

	/**
	 * Sets the selected status of the food item.
	 * 
	 * @param selected
	 *            The new status.
	 */
	public void setSelected(boolean selected) {
		this.mSelected = selected;
	}

	/**
	 * Gets the price of the food item. This is not guaranteed to be a valid
	 * denomination.
	 * 
	 * @return The cost of the food item.
	 */
	public double getPrice() {
		return mPrice;
	}

	/**
	 * Sets the cost of the food item.
	 * 
	 * @param d
	 *            The new cost.
	 */
	public void setPrice(double d) {
		this.mPrice = d;
	}

	/**
	 * Sets the description of the food item.
	 * 
	 * @param description
	 *            The new description.
	 */
	public void setDescription(String description) {
		mDescription = description;
	}

	/**
	 * Gets the user-readable description of the food item. This should not
	 * include any line breaks or other formatting information.
	 * 
	 * @return The food item's description.
	 */
	public String getDescription() {
		return mDescription;
	}
}
