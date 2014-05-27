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
	 * Chosen dining out option (Delivery or Take Out)
	 */
	final static String DINING_OUT_TYPE = "dining out";

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
		Intent intent = new Intent();
		intent.putExtra(DINING_OUT_TYPE, "Take Out");
		mWrapper.setIntent(intent);
		mWrapper.changeFragment(new DiningOutFormFragment(),
				new DiningOutFormListener(mWrapper));
		Log.e("DiningOutListener", "Take Out button works");
	}

	/**
	 * Place holder method. This should send a request for delivery?
	 */
	public void notifyDelivery() {
		Intent intent = new Intent();
		intent.putExtra(DINING_OUT_TYPE, "Delivery");
		mWrapper.setIntent(intent);
		mWrapper.changeFragment(new DiningOutFormFragment(),
				new DiningOutFormListener(mWrapper));
		Log.e("DiningOutListener", "Delivery button works");
	}

	/**
	 * Go to the correct Menu, if no menu was previously chosen open a dialog
	 */
	public void goToFoodMenu() {
		FoodMenuListFragment.clearChecked();
		if (++RestaurantMainMenuFragment.backCount > 1)
			mWrapper.getFragmentManager().popBackStack();
		openFoodMenuDialog();
	}

	/**
	 * Remove the selected items from the selectedFood array
	 */
	public void removeSelectedFoodItems() {
		DiningOutFragment frag = new DiningOutFragment();
		for (int i = 0; i < frag.getSelectedFood().size(); i++) {
			if (frag.getSelectedFood().get(i).isSelected()) {
				frag.getSelectedFood().remove(i);
				i = -1;
			}
		}
		frag.refresh();
	}

	/**
	 * Creates dialog for food menu options
	 */
	private void openFoodMenuDialog() {
		Builder dialog = new AlertDialog.Builder(mWrapper);
		final FoodMenuListFragment nextFrag = new FoodMenuListFragment();
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				nextFrag.setMealByIndex(i);
				mWrapper.changeFragment(new FoodMenuListFragment(),
						new FoodMenuListListener(mWrapper));
			}
		};
		dialog.setTitle(R.string.menu_title);
		dialog.setItems(mWrapper.getModel().getMenuLabels(), diaIn);
		dialog.show();
	}
}
