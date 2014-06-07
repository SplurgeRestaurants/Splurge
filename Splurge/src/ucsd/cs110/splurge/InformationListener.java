package ucsd.cs110.splurge;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

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
			TelephonyManager tm = (TelephonyManager) mWrapper
					.getSystemService(Activity.TELEPHONY_SERVICE);
			if (tm != null
					&& tm.getSimState() == TelephonyManager.SIM_STATE_READY) {
				String phoneNumber = "tel:"
						+ mWrapper.getModel().getRestaurantPhoneNumber();
				intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
				mWrapper.startActivity(intent);
			} else {
				Toast.makeText(mWrapper, R.string.phone_unavailable,
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.directions:
			String address = String.format(
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
