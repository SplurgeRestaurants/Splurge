package ucsd.cs110.splurge;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class RestaurantMainMenuListener implements OnClickListener {
	private Context mContext;

	// private static final int DELIVERY = 1;
	// private static final int TAKE_OUT = 0;

	/**
	 * Create a new RestaurantMainMenuListener, designed to listen to a
	 * RestaurantMainMenuFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public RestaurantMainMenuListener(Context context) {
		mContext = context;
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
			Intent intent = new Intent(mContext, InformationActivity.class);
			mContext.startActivity(intent);
			break;
		case R.id.reserve:
			openReserveDialog();
			break;
		case R.id.menu:
			openFoodMenuDialog();
			break;
		case R.id.diningout:
			// TODO just go to DiningOutActivity instead of opening dialog
			Intent intent1 = new Intent(mContext, DiningOutActivity.class);
			mContext.startActivity(intent1);
			// openDiningOutDialog();
			break;
		}
	}

	// Creates dialog for reservation options
	private void openReserveDialog() {
		Builder dialog = new AlertDialog.Builder(mContext);
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
		Builder dialog = new AlertDialog.Builder(mContext);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialoginterface, int i) {
				Intent intent = new Intent(mContext, FoodMenuActivity.class);
				intent.putExtra(RestaurantMainMenuActivity.MEAL, i);
				mContext.startActivity(intent);
			}
		};
		dialog.setTitle(R.string.menu_title);
		dialog.setItems(R.array.menus, diaIn);
		dialog.show();
	}
}
