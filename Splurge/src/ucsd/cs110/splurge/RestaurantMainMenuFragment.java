package ucsd.cs110.splurge;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

/**
 * Display the main menu of the chosen restaurant
 * 
 */
public class RestaurantMainMenuFragment extends SuperFragment {
	/**
	 * Keep tracks of the number of pushes to the backStack
	 */
	static int backCount;

	/**
	 * Set the restaurant name in TextView
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.main_menu, container, false);
		return (ret);
	}

	/**
	 * set listener for each button
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setMainMenuListener(OnClickListener listener) {
		(getView().findViewById(R.id.menu)).setOnClickListener(listener);
		(getView().findViewById(R.id.information)).setOnClickListener(listener);
		(getView().findViewById(R.id.diningout)).setOnClickListener(listener);
		(getView().findViewById(R.id.reserve)).setOnClickListener(listener);
	}

	/**
	 * Set mSuperListener to the buttons
	 */
	@Override
	public void onStart() {
		setMainMenuListener((OnClickListener) mSuperListener);
		backCount = 0;
		getActivity().setTitle(
				getWrapperActivity().getModel().getRestaurantName());
		DiningOutFragment frag = new DiningOutFragment();
		try {
			frag.getSelectedFood().clear();
		} catch (Exception e) {

		}
		super.onStart();
	}
}
