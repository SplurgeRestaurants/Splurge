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
	 * Enumerator indicating breakfast.
	 */
	private static final int BREAKFAST = 0;
	/**
	 * Enumerator indicating lunch.
	 */
	private static final int LUNCH = 1;
	/**
	 * Enumerator indicating dinner.
	 */
	private static final int DINNER = 2;

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
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu:
			openFoodMenuDialog();
			break;
	
		case R.id.reserve:
			// TODO (Yuri) go to reservation page
			// mWrapper.changeFragment(new ReservationFragment(), new
			// ReservationListener());
			break;
	
		case R.id.diningout:
			mWrapper.changeFragment(new DiningOutFragment(),
					new DiningOutListListener(mWrapper));
			break;
		case R.id.information:
			mWrapper.changeFragment(new InformationFragment(),
					new InformationListener(mWrapper));
			break;
		}
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
				case BREAKFAST:
					FoodMenuListFragment.setMEAL("Breakfast");
					break;
				case LUNCH:
					FoodMenuListFragment.setMEAL("Lunch");
					break;
				case DINNER:
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
