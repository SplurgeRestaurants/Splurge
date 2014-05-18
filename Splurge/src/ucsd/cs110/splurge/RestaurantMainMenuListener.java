package ucsd.cs110.splurge;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Listener for the RestaurantMainMenuFragment
 * 
 */
public class RestaurantMainMenuListener extends SuperListener implements
		OnClickListener {

	/**
	 * Create a new RestaurantMainMenuListener, designed to listen to a
	 * RestaurantMainMenuFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public RestaurantMainMenuListener(WrapperActivity context) {
		super(context);
	}

	/**
	 * Buttons go to the correct page
	 * 
	 * @param v
	 *            View being clicked on
	 * 
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.information:
			mWrapper.changeFragment(new InformationFragment(),
					new InformationListener(mWrapper));
			break;
		case R.id.reserve:
			openReserveDialog();
			break;
		case R.id.menu:
			openFoodMenuDialog();
			break;
		case R.id.diningout:
			mWrapper.changeFragment(new DiningOutFragment(),
					new DiningOutListListener(mWrapper));
			break;
		}
	}

	/**
	 * Creates dialog for reservation options
	 */
	private void openReserveDialog() {
		Builder dialog = new AlertDialog.Builder(mWrapper);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				// TODO open reservation activity
			}
		};
		dialog.setTitle(R.string.new_reserv_title);
		dialog.setItems(R.array.reservations, diaIn);
		dialog.show();
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
