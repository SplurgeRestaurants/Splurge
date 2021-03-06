package ucsd.cs110.splurge;

import java.util.Calendar;

import ucsd.cs110.splurge.connectivity.tasks.DineOutRequestListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Listener for the DiningOutFormFragment. This handles all user interactions
 * for the fragment.
 */
public class DiningOutFormListener extends SuperListener implements
		OnClickListener, DineOutRequestListener {

	private DiningOutFormFragment mListenedTo;

	/**
	 * Create a new DeliveryFormListener, designed to listen to a
	 * DeliveryFormFragment.
	 * 
	 * @param wrapper
	 *            Context used for spawning a new Activity
	 */
	public DiningOutFormListener(WrapperActivity wrapper) {
		super(wrapper);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.place_order_button:
			String meal = mWrapper.getIntent().getStringExtra(
					DiningOutListListener.OUTPUT_STRING);
			String kind = "";
			String addy = "none";
			meal += mListenedTo.getAdditionalNotes();
			switch (mWrapper.getIntent().getStringExtra(
					DiningOutListListener.DINING_OUT_TYPE)) {
			case "Delivery":
				kind = "d";
				addy = mListenedTo.getFilledAddress();
				TextView address = (TextView) mWrapper
						.findViewById(R.id.form_street_address);
				if (addy.length() < 1
						|| address.getText().toString().length() < 1) {
					Toast.makeText(mWrapper, "Missing Fields",
							Toast.LENGTH_SHORT).show();
					return;
				}
				break;
			case "Take Out":
				kind = "co";
				break;
			default:
				kind = "error";
				break;
			}
			if (mListenedTo.getFilledName().length() < 1
					|| mListenedTo.getFilledPhoneNumber().length() < 1) {
				Toast.makeText(mWrapper, "Missing Fields", Toast.LENGTH_SHORT)
						.show();
				return;
			}
			int response = mWrapper.getModel().requestDineOut(
					mListenedTo.getFilledName(),
					mListenedTo.getFilledPhoneNumber(), addy, kind,
					Calendar.getInstance(), meal, this);
			if (response >= 0) {
				Toast.makeText(mWrapper, "Order Successful!", Toast.LENGTH_LONG)
						.show();
				mWrapper.getFragmentManager().popBackStack();
				mWrapper.getFragmentManager().popBackStack();
				mWrapper.getFragmentManager().popBackStack();
				DiningOutFragment frag = new DiningOutFragment();
				frag.getSelectedFood().clear();
				mWrapper.changeFragment(new RestaurantMainMenuFragment(),
						new RestaurantMainMenuListener(mWrapper));
			} else if (response == -1) {
				Toast.makeText(mWrapper, "Order Unsuccessful. Our apologies.",
						Toast.LENGTH_LONG).show();
			} else if (response == -2) {
				Log.i("Splurge", "Dine-out response deferred to later time.");
			}
			break;
		default:
			break;
		}
	}

	/**
	 * Sets the fragment to which this listener is listening. This is necessary
	 * in order to pull user-provided information from the fragment, such as the
	 * user's name and phone number.
	 * 
	 * @param listened
	 *            The fragment to which this listener listens.
	 */
	public void setListened(DiningOutFormFragment listened) {
		mListenedTo = listened;
	}

	@Override
	public void receiveDineOutId(int id) {
		if (id >= 0) {
			Toast.makeText(mWrapper, "Order Successful!", Toast.LENGTH_LONG)
					.show();
			mWrapper.changeFragment(new RestaurantMainMenuFragment(),
					new RestaurantMainMenuListener(mWrapper));
		} else if (id == -1) {
			Toast.makeText(mWrapper, "Order Unsuccessful. Our apologies.",
					Toast.LENGTH_LONG).show();
		}
	}
}
