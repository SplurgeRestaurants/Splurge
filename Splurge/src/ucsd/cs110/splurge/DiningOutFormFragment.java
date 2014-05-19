package ucsd.cs110.splurge;

import java.util.ArrayList;

import ucsd.cs110.splurge.model.FoodItem;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DiningOutFormFragment extends SuperFragment {
	private ListView mListView;
	private TextView mFormName;
	private TextView mFormEmail;
	private TextView mFormPhoneNumber;
	private TextView mFormStreetAddress;
	private TextView mFormAptNumber;
	private TextView mFormCity;
	private TextView mFormState;
	private TextView mFormZipCode;
	private Button mFormPlaceOrderButton;
	private FoodMenuAdapter adapter;
	private ArrayList<FoodItem> mOrder;

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
		mFormPlaceOrderButton = (Button) ret
				.findViewById(R.id.place_order_button);
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

	ArrayList<FoodItem> getOrder() {
		return DiningOutFragment.getFoodItemsSelected();
	}

	public void setDiningOutFormButtonListener(OnClickListener listener) {
		getView().findViewById(R.id.place_order_button).setOnClickListener(
				listener);
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
	 * set the mSuperListener to the buttons
	 */
	@Override
	public void onStart() {
		setDiningOutFormButtonListener((OnClickListener) mSuperListener);
		super.onStart();
	}
}
