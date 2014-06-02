package ucsd.cs110.splurge;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Listener for the InformationFragment
 * 
 */
public class InformationListener extends SuperListener implements
		OnClickListener {

	/**
	 * Create a new InformationListener, designed to listen to a
	 * InformationFragment.
	 * 
	 * @param context
	 *            Context used for spawning a new Activity
	 */
	public InformationListener(WrapperActivity context) {
		super(context);
	}

	/**
	 * Call the correct method for each button
	 */
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		case R.id.call_phone:
			// TODO make button call the phone number
			Log.e("InformationListener", "Call Phone Button Works");
			String phoneNumber = "tel:"
					+ Integer.toString(mWrapper.getModel()
							.getRestaurantPhoneNumber());
			phoneNumber = "tel:4089812708";
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
			mWrapper.startActivity(intent);
			break;
		case R.id.directions:
			String address = "672+E+11400+S+84020";
			// TODO address field in restaurant model
			address = String.format(
					"%s+%s",
					TextUtils.join("+", mWrapper.getModel()
							.getRestaurantStreetAddress().split("\\s+")),
					mWrapper.getModel().getRestaurantZipcode());
			intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="
					+ address));
			mWrapper.startActivity(intent);
			break;
		}
	}
}
