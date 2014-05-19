package ucsd.cs110.splurge;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/**
 * Listener class for DiningOutFragment
 */
public class DiningOutListListener extends SuperListener implements
		OnItemClickListener, OnClickListener {

	/**
	 * Create a new DiningOutListListener, designed to listen to a
	 * DiningOutListFragment.
	 * 
	 * @param wrapper
	 *            Context used for spawning a new Activity
	 */
	public DiningOutListListener(WrapperActivity wrapper) {
		super(wrapper);
	}

	/**
	 * When a list entry is clicked on display the correct food item
	 */
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Intent intent = new Intent();
		intent.putExtra(FoodMenuListFragment.FOOD_ITEM_POSITION, position);
		mWrapper.setIntent(intent);
		mWrapper.changeFragment(new FoodItemFragment(), null);
	}

	/**
	 * Call the appropriate method for the button clicked
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.delivery:
			notifyDelivery();
			break;
		case R.id.add_to_order:
			goToFoodMenu();
			break;
		case R.id.take_out:
			notifyTakeOut();
			break;
		case R.id.remove_items:
			removeSelectedFoodItems();
			break;
		default:
			break;
		}

	}

	/**
	 * Place holder method. This should send a request for take out?
	 */
	public void notifyTakeOut() {
		// TODO
		Log.e("DiningOutListener", "Take Out button works");
	}

	/**
	 * Place holder method. This should send a request for delivery?
	 */
	public void notifyDelivery() {
		mWrapper.changeFragment(new DeliveryFormFragment(), null);
		Log.e("DiningOutListener", "Delivery button works");
	}

	/**
	 * Go to the correct Menu, if no menu was previously chosen open a dialog
	 */
	public void goToFoodMenu() {
		if (FoodMenuListFragment.getMEAL() == null) {
			openFoodMenuDialog();
		} else {
			mWrapper.changeFragment(new FoodMenuListFragment(),
					new FoodMenuListListener(mWrapper));
		}
	}

	/**
	 * Remove the selected items from the selectedFood array
	 */
	public void removeSelectedFoodItems() {
		for (int i = 0; i < FoodMenuListFragment.selectedFood.size(); i++) {
			if (FoodMenuListFragment.selectedFood.get(i).isSelected()) {
				FoodMenuListFragment.selectedFood.remove(i);
				i = -1;
			}
		}
		DiningOutFragment.refresh();
	}

	/**
	 * Creates dialog for food menu options
	 */
	private void openFoodMenuDialog() {
		Builder dialog = new AlertDialog.Builder(mWrapper);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				switch (i) {
				case 0:
					FoodMenuListFragment.setMEAL("Breakfast");
					break;
				case 1:
					FoodMenuListFragment.setMEAL("Lunch");
					break;
				case 2:
					FoodMenuListFragment.setMEAL("Dinner");
					break;
				default:
					break;
				}
				mWrapper.changeFragment(new FoodMenuListFragment(),
						new FoodMenuListListener(mWrapper));
			}
		};
		dialog.setTitle(R.string.menu_title);
		dialog.setItems(R.array.menus, diaIn);
		dialog.show();
	}
}
