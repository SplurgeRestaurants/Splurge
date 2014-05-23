package ucsd.cs110.splurge;

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
		switch (v.getId()) {
		case R.id.directions:
			// TODO (dqthai) display google maps?
			Log.e("InformationListener", "Direction Button Works");
			break;
		}
	}
}
