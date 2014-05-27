package ucsd.cs110.splurge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

public class ReservationFragment extends SuperFragment {
	/** Called when the activity is first created. */

	static final int DATE_DIALOG_ID = 0;
	static final int PICK_DATE_REQUEST = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.main, container, false);
		return ret;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == PICK_DATE_REQUEST) {
			if (resultCode == Activity.RESULT_OK) {
				Toast.makeText(getActivity().getApplicationContext(),
						data.getStringExtra("date"), Toast.LENGTH_SHORT).show();
				String[] dateArr = data.getStringExtra("date").split("-");
				DatePicker dp = (DatePicker) getActivity().findViewById(
						R.id.datePicker1);
				dp.updateDate(Integer.parseInt(dateArr[0]),
						Integer.parseInt(dateArr[1]),
						Integer.parseInt(dateArr[2]));
			}
		}
	}

	/**
	 * set listener for each button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setReservationListener(OnClickListener listener) {
		(getView().findViewById(R.id.openButton)).setOnClickListener(listener);
	}

	/**
	 * Set mSuperListener to the buttons
	 */
	@Override
	public void onStart() {
		setReservationListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}