package ucsd.cs110.splurge.connectivity;

import java.util.ArrayList;

import org.json.JSONArray;

/**
 * Worker class for composing a JSONArray containing pre-order information. The
 * array should be recompiled whenever new output is desired.
 */
public class PreOrderJSONArrayHandler {

	/**
	 * Record of the items currently to be pre-ordered. This is not sorted in
	 * any particular order.
	 */
	private ArrayList<PreOrderItem> mItemList;

	/**
	 * Initializes the handler with the given IDs. Duplicate IDs are condensed
	 * into a single listing with quantity specified by the number of times the
	 * ID repeats.
	 * 
	 * @param ids
	 *            List of identification numbers to insert.
	 */
	public PreOrderJSONArrayHandler(int... ids) {
		mItemList = new ArrayList<>();
		for (int id : ids) {
			addSingleItemByID(id);
		}
	}

	/**
	 * Adds an item listing for the given identification number, incrementing an
	 * existing item if the id already exists in the record.
	 * 
	 * @param id
	 *            The identification number of the item to increment.
	 * @return <code>true</code> if a new listing was added to the record,
	 *         <code>false</code> if the listing already existed and was
	 *         incremented.
	 */
	public boolean addSingleItemByID(int id) {
		for (PreOrderItem item : mItemList) {
			if (item.getID() == id) {
				item.incrementQuantity();
				return false;
			}
		}
		mItemList.add(new PreOrderItem(id));
		return true;
	}

	/**
	 * Removes a single item, decrementing the quantity and removing the listing
	 * from the record if applicable.
	 * 
	 * @param id
	 *            The identification number of the item to decrement.
	 * @return <code>true</code> if the item existed and was decremented,
	 *         <code>false</code> otherwise.
	 */
	public boolean removeSingleItemById(int id) {
		PreOrderItem decThis = null;
		for (PreOrderItem item : mItemList) {
			if (item.getID() == id) {
				decThis = item;
				break;
			}
		}
		if (decThis != null) {
			if (decThis.decrementQuantity() == 0) {
				mItemList.remove(decThis);
			}
			return true;
		}
		return false;
	}

	/**
	 * Compiles the contents of the handler into a usable JSONArray. This
	 * JSONArray is not updated after being compiled, and so this method must be
	 * called whenever a result is desired.
	 * 
	 * @return The compiled JSONArray containing the pre-order listings
	 *         contained in the Handler.
	 */
	public JSONArray compileArray() {
		JSONArray ret = new JSONArray();
		for (PreOrderItem item : mItemList) {
			ret.put(item.compileToObject());
		}
		return ret;
	}
}
