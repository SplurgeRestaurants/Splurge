package ucsd.cs110.splurge;

import java.util.Calendar;

import ucsd.cs110.splurge.connectivity.tasks.DineOutRequestListener;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
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
			// TODO send order request to website
			String meal = mWrapper.getIntent().getStringExtra(
					DiningOutListListener.OUTPUT_STRING);
			String kind = "";
			String addy = "none";
			switch (mWrapper.getIntent().getStringExtra(
					DiningOutListListener.DINING_OUT_TYPE)) {
			case "Delivery":
				kind = "d";
				break;
			case "Take Out":
				kind = "co";
				addy = mListenedTo.getFilledAddress();
				break;
			default:
				kind = "error";
				break;
			}
			int response = mWrapper.getModel().requestDineOut(
					mListenedTo.getFilledName(),
					mListenedTo.getFilledPhoneNumber(), addy, kind,
					Calendar.getInstance(), meal, this);
			if (response >= 0) {
				Toast.makeText(mWrapper, "Order Successful!", Toast.LENGTH_LONG)
						.show();
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
		} else if (id == -1) {
			Toast.makeText(mWrapper, "Order Unsuccessful. Our apologies.",
					Toast.LENGTH_LONG).show();
		}
	}
}
