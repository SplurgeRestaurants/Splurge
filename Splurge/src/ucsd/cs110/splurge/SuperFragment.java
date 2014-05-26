package ucsd.cs110.splurge;

import android.app.Fragment;

/**
 * Fragment superclass which details the requirements for all fragments used in
 * the application so as to conform to the implementation of the MVC framework.
 */
public abstract class SuperFragment extends Fragment {
	protected SuperListener mSuperListener;

	/**
	 * Sets the listener class for the Fragment.
	 * 
	 * This does not hook up the listener to any particular function, it merely
	 * registers the listener to the fragment so that the fragment can hook up
	 * the listener as need be. This hookup should occur in the
	 * <code>onStart</code> or <code>onResume</code> of the Fragment so as to
	 * avoid attempting to hook up the listener prior to there being an object
	 * to which to listen.
	 * 
	 * @param l
	 *            The Fragment's new listener.
	 */
	public void setSuperListener(SuperListener l) {
		mSuperListener = l;
	}
}
