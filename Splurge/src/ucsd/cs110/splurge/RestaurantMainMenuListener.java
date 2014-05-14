package ucsd.cs110.splurge;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;

public class RestaurantMainMenuListener extends SuperListener implements
		OnClickListener {

	// private static final int DELIVERY = 1;
	// private static final int TAKE_OUT = 0;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View) Makes
	 * sure the button opens the correct dialog
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

	// Creates dialog for reservation options
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

	// Creates dialog for food menu options
	private void openFoodMenuDialog() {
		Builder dialog = new AlertDialog.Builder(mWrapper);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				mWrapper.changeFragment(new FoodMenuListFragment(),
						new FoodMenuListListener(mWrapper));
			}
		};
		dialog.setTitle(R.string.menu_title);
		dialog.setItems(R.array.menus, diaIn);
		dialog.show();
	}

	// Creates dialog for dining out options
	// private void openDiningOutDialog() {
	// Builder dialog = new AlertDialog.Builder(mContext);
	// DialogInterface.OnClickListener diaIn = new
	// DialogInterface.OnClickListener() {
	// @Override
	// public void onClick(DialogInterface dialoginterface, int i) {
	// Intent intent = null;
	// switch (i) {
	// case TAKE_OUT:
	// intent = new Intent(mContext, InformationActivity.class);
	// // TODO save restaurant id?
	// break;
	// case DELIVERY:
	// intent = new Intent(mContext, InformationActivity.class);
	// // TODO save restaurant id?
	// break;
	// default:
	// break;
	// }
	// mContext.startActivity(intent);
	// }
	// };
	// dialog.setTitle(R.string.dining_out_title);
	// dialog.setItems(R.array.dining_out, diaIn);
	// dialog.show();
	// }
}
