package ucsd.cs110.splurge;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

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

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.hours:
			// TODO get hours from data base and show new page or dialog
			Log.e("InformationListener", "Hours Button Works");
			break;
		case R.id.directions:
			// TODO display google maps?
			Log.e("InformationListener", "Direction Button Works");
			break;
		}
	}
}
