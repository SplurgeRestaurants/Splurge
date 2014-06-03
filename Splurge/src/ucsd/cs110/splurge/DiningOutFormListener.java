package ucsd.cs110.splurge;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class DiningOutFormListener extends SuperListener implements
		OnClickListener {
	private boolean diningOutSuccessful = false;

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
			if (diningOutSuccessful) {
				Toast.makeText(mWrapper, "Order Successful", Toast.LENGTH_LONG)
						.show();
			} else {
				Toast.makeText(mWrapper, "Order Unsuccessful",
						Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}
}
