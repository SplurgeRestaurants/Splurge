package ucsd.cs110.splurge;

import android.app.Fragment;

public abstract class SuperFragment extends Fragment {
	protected SuperListener mSuperListener;

	public void setSuperListener(SuperListener l) {
		mSuperListener = l;
	}
}
