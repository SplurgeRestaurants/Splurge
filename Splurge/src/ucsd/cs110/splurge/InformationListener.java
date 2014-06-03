package ucsd.cs110.splurge;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
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
			String phoneNumber = "tel:"
					+ mWrapper.getModel().getRestaurantPhoneNumber();
			intent = new Intent(Intent.ACTION_DIAL, Uri.parse(phoneNumber));
			mWrapper.startActivity(intent);
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
