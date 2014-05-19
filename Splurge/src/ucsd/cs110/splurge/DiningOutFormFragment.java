package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class DiningOutFormFragment extends SuperFragment {
	/**
	 * List of food items to be ordered
	 */
	private ListView mListView;
	/**
	 * Input for the name field
	 */
	private TextView mFormName;
	/**
	 * Input for email field
	 */
	private TextView mFormEmail;
	/**
	 * Input for phone number field
	 */
	private TextView mFormPhoneNumber;
	/**
	 * Input for street address field
	 */
	private TextView mFormStreetAddress;
	/**
	 * Input for apartment number field
	 */
	private TextView mFormAptNumber;
	/**
	 * Input for city field
	 */
	private TextView mFormCity;
	/**
	 * Input for state field
	 */
	private TextView mFormState;
	/**
	 * Input for zip code field
	 */
	private TextView mFormZipCode;
	/**
	 * Format the list entries
	 */
	private FoodMenuAdapter adapter;
	/**
	 * Populated with food items to be ordered
	 */
	private ArrayList<FoodItem> mOrder;

	/**
	 * Hide the address views if "Take Out" was selected. Populated the order
	 * summary list. Set up layout.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.delivery_form, container, false);
		mListView = (ListView) ret.findViewById(R.id.order_summary_list);
		mFormName = (TextView) ret.findViewById(R.id.form_name);
		mFormEmail = (TextView) ret.findViewById(R.id.form_email);
		mFormPhoneNumber = (TextView) ret.findViewById(R.id.form_phone_number);
		mFormStreetAddress = (TextView) ret
				.findViewById(R.id.form_street_address);
		mFormAptNumber = (TextView) ret.findViewById(R.id.form_apt_number);
		mFormCity = (TextView) ret.findViewById(R.id.form_city);
		mFormState = (TextView) ret.findViewById(R.id.form_state);
		mFormZipCode = (TextView) ret.findViewById(R.id.form_zip_code);
		String diningOutType = getActivity().getIntent()
				.getCharSequenceExtra(DiningOutListListener.DINING_OUT_TYPE)
				.toString();
		if (diningOutType.compareTo("Take Out") == 0) {
			((View) mFormStreetAddress.getParent()).setVisibility(View.GONE);
			((View) mFormAptNumber.getParent()).setVisibility(View.GONE);
			((View) mFormCity.getParent()).setVisibility(View.GONE);
			((View) mFormState.getParent()).setVisibility(View.GONE);
			((View) mFormZipCode.getParent()).setVisibility(View.GONE);
		}
		mOrder = getOrder();
		adapter = new FoodMenuAdapter(getActivity(),
				R.layout.order_summary_item, mOrder);
		mListView.setAdapter(adapter);
		return (ret);
	}

	/**
	 * Get the selected food items
	 * 
	 * @return Items that are going to be ordered
	 */
	ArrayList<FoodItem> getOrder() {
		return DiningOutFragment.getFoodItemsSelected();
	}

	/**
	 * Get form information
	 * 
	 * @return Compiled form information
	 */
	String getForm() {
		return null;
	}

	/**
	 * Set a listener for the buttons
	 * 
	 * @param listener
	 *            Listener to be set
	 */
	public void setDiningOutFormButtonListener(OnClickListener listener) {
		getView().findViewById(R.id.place_order_button).setOnClickListener(
				listener);
	}

	/**
	 * set the mSuperListener to the buttons
	 */
	@Override
	public void onStart() {
		setDiningOutFormButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}
