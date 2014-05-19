package ucsd.cs110.splurge;

import android.view.View;
import android.view.View.OnClickListener;

public class DeliveryFormListener extends SuperListener implements
		OnClickListener {
	/**
	 * Create a new DeliveryFormListener, designed to listen to a
	 * DeliveryFormFragment.
	 * 
	 * @param wrapper
	 *            Context used for spawning a new Activity
	 */
	public DeliveryFormListener(WrapperActivity wrapper) {
		super(wrapper);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.place_order_button:
			// TODO (dqthai) submit the form to the web site
			break;
		default:
			break;
		}
	}
}