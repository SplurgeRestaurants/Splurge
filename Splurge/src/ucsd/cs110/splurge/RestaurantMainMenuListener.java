package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class RestaurantMainMenuListener implements OnClickListener {
	private Context mContext;
	private static final int DELIVERY = 1;
	private static final int TAKE_OUT = 0;
	
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
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 * Makes sure the button opens the correct dialog
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.information:
			((Activity) mContext).getFragmentManager().beginTransaction()
					.add(R.id.container, new LocationFragment()).commit();
			break;
		case R.id.reserve:
			openReserveDialog();
			break;
		case R.id.menu:
			openFoodMenuDialog();
			break;
		case R.id.diningout:
			openDiningOutDialog();
			break;
		}
	}

	// Creates dialog for reservation options
	private void openReserveDialog() {
		Builder dialog = new AlertDialog.Builder(mContext);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
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

	// Creates dialog for dining out options
	private void openDiningOutDialog() {
		Builder dialog = new AlertDialog.Builder(mContext);
		DialogInterface.OnClickListener diaIn = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialoginterface, int i) {
				FragmentTransaction ft = ((Activity) mContext)
						.getFragmentManager().beginTransaction();
				switch (i) {
				case TAKE_OUT:
					ft.add(R.id.container, new LocationFragment());
					ft.commit();
					break;
				case DELIVERY:
					ft.add(R.id.container, new LocationFragment());
					ft.commit();
					break;
				default:
					break;
				}
			}
		};
		dialog.setTitle(R.string.dining_out_title);
		dialog.setItems(R.array.dining_out, diaIn);
		dialog.show();
	}

	public static class LocationFragment extends Fragment {
		public LocationFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.map, container, false);
			return rootView;
		}
	}

}
