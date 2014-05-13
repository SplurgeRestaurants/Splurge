package ucsd.cs110.splurge.connectivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Helper class for composing JSONObjects for pre-order listings. This contains
 * item id and quantity information for the given item.
 */
public class PreOrderItem implements Comparable<PreOrderItem> {

	/**
	 * String which is reported as the message when quantity enters an illegal
	 * state, i.e. falls below zero.
	 */
	private static final String ILLEGAL_QUANTITY = "Cannot have a quantity which is less than zero.";
	/**
	 * JSON key for the item identification number.
	 */
	private static final String ITEM_ID = "itemID";
	/**
	 * JSON key for the quantity of items to be ordered.
	 */
	private static final String QUANTITY = "quantity";

	/**
	 * Identification number of the corresponding food item. This should
	 * correspond to an item id as registered by the server / database.
	 */
	private int mID;
	/**
	 * The quantity of this item to be ordered. This should be a counting
	 * number, and an exception will be thrown if this rule is ever broken.
	 */
	private int mQuantity;

	/**
	 * Creates a new PreOrderItem with the given identification number and a
	 * quantity of one.
	 * 
	 * @param id
	 *            The identification number of the item.
	 */
	public PreOrderItem(int id) {
		this(id, 1);
	}

	/**
	 * Creates a new PreOrderItem with the given identification number and
	 * quantity. If the quantity
	 * 
	 * @param id
	 * @param quantity
	 */
	public PreOrderItem(int id, int quantity) {
		mID = id;
		mQuantity = quantity;
		checkQuantity();
	}

	/**
	 * Retrieves the identification number of the food item which is requested
	 * in this item listing.
	 * 
	 * @return This listing's food identification number.
	 */
	public int getID() {
		return mID;
	}

	/**
	 * Retrieves the quantity of the specified food item which is requested in
	 * this pre-order listing.
	 * 
	 * @return The quantity of the listing.
	 */
	public int getQuantity() {
		return mQuantity;
	}

	/**
	 * Increments the quantity of this PreOrderItem and returns the held
	 * quantity.
	 * 
	 * @return The quantity of the item after the increment.
	 */
	public int incrementQuantity() {
		return ++mQuantity;
	}

	/**
	 * Decrements the quantity of this PreOrderItem and returns the held
	 * quantity. If the quantity falls below zero, then an exception will be
	 * thrown.
	 * 
	 * @return The quantity of the item after the decrement.
	 */
	public int decrementQuantity() {
		--mQuantity;
		checkQuantity();
		return mQuantity;
	}

	/**
	 * Checks the quantity reported for this PreOrderItem and throws an
	 * IllegalArgumentException if it is below zero. This is perhaps not the
	 * most appropriate exception, and so it may be suitable for someone to
	 * modify this.
	 */
	private void checkQuantity() {
		if (mQuantity < 0)
			throw new IllegalArgumentException(ILLEGAL_QUANTITY);
	}

	/**
	 * Compiles this PreOrderItem into a useful JSONObject. This object is not
	 * updated after compilation, and so if this PreOrderItem is modified, a new
	 * JSONObject will need to be compiled in order for the changes to be
	 * reflected.
	 * 
	 * @return A useful JSONObject based on this PreOrderItem.
	 */
	public JSONObject compileToObject() {
		JSONObject ret = new JSONObject();
		try {
			ret.put(ITEM_ID, mID);
			ret.put(QUANTITY, mQuantity);
		} catch (JSONException e) {
			// DO nothing
		}
		return ret;
	}

	/**
	 * Compares this PreOrderItem to another by identification number.
	 */
	@Override
	public boolean equals(Object o) {
		return o != null && (o instanceof PreOrderItem)
				&& ((PreOrderItem) o).getID() == getID();
	}

	/**
	 * Compares this PreOrderItem to another by identification number.
	 */
	@Override
	public int compareTo(PreOrderItem another) {
		return getID() - another.getID();
	}
}
