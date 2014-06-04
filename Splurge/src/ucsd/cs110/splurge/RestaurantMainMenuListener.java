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
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.menu:
			openFoodMenuDialog();
			break;
		case R.id.reserve:
			// (dqthai get rid of null, make reservation listener)
			mWrapper.changeFragment(new CalendarViewFragment(),
					new CalenderViewFragmentListener(mWrapper));
			;
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
		final FoodMenuListFragment nextFrag = new FoodMenuListFragment();
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				nextFrag.setMeal(mWrapper.getModel().getFoodMenuByIndex(i));
				mWrapper.changeFragment(nextFrag, new FoodMenuListListener(
						mWrapper));
			}
		};
		dialog.setTitle(R.string.menu_title);
		dialog.setItems(mWrapper.getModel().getMenuLabels(), diaIn);
		dialog.show();
	}
}
