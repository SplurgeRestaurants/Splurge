package ucsd.cs110.splurge;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class InformationActivity extends Activity {

	private InformationFragment fragment;
	public InformationListener listener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.activity_information);
		listener = new InformationListener(this);
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		fragment = new InformationFragment();
		ft.replace(R.id.information_fragment_placeholder, fragment);
		ft.commit();
		super.onCreate(savedInstanceState);
	}

	/*
	 * set listener for the fragment
	 */
	@Override
	protected void onStart() {
		fragment.setListListener(listener);
		super.onStart();
	}
}
