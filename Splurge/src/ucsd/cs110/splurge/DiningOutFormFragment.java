package ucsd.cs110.splurge;

import java.util.LinkedList;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Class detailing a view component of the dining out sequence.
 */
public class DiningOutFormFragment extends SuperFragment {

	/**
	 * Input for the name field
	 */
	private TextView mFormName;
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
	 * Input for additional notes.
	 */
	private EditText mFormNotes;

	/**
	 * Hide the address views if "Take Out" was selected. Populated the order
	 * summary list. Set up layout.
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View ret = inflater.inflate(R.layout.delivery_form, container, false);
		mFormName = (TextView) ret.findViewById(R.id.form_name);
		mFormPhoneNumber = (TextView) ret.findViewById(R.id.form_phone_number);
		mFormStreetAddress = (TextView) ret
				.findViewById(R.id.form_street_address);
		mFormAptNumber = (TextView) ret.findViewById(R.id.form_apt_number);
		mFormCity = (TextView) ret.findViewById(R.id.form_city);
		mFormState = (TextView) ret.findViewById(R.id.form_state);
		mFormZipCode = (TextView) ret.findViewById(R.id.form_zip_code);
		mFormNotes = (EditText) ret.findViewById(R.id.form_order_notes);
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
		return (ret);
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
		((DiningOutFormListener) mSuperListener).setListened(this);
		super.onStart();
	}

	/**
	 * Retrieves the name provided by the user.
	 * 
	 * @return The contents of the user name field.
	 */
	public String getFilledName() {
		return mFormName.getText().toString();
	}

	/**
	 * Retrieves the contact phone number provided by the user.
	 * 
	 * @return The number provided by the user.
	 */
	public String getFilledPhoneNumber() {
		return mFormPhoneNumber.getText().toString();
	}

	/**
	 * Retrieves the address provided by the user. This combines all fields.
	 * 
	 * @return The contents of the address fields.
	 */
	public String getFilledAddress() {
		LinkedList<String> combination = new LinkedList<String>();
		if (mFormZipCode.getText().length() > 0) {
			combination.addFirst(mFormZipCode.getText().toString());
		}
		if (mFormState.getText().length() > 0) {
			combination.addFirst(mFormState.getText().toString());
		}
		if (mFormCity.getText().length() > 0) {
			combination.addFirst(mFormCity.getText().toString());
		}
		if (mFormAptNumber.getText().length() > 0) {
			combination.addFirst(mFormAptNumber.getText().toString());
		}
		if (mFormStreetAddress.getText().length() > 0) {
			combination.addFirst(mFormStreetAddress.getText().toString());
		}
		return TextUtils.join(", ", combination);
	}

	/**
	 * Retrieves the additional order notes provided by the user.
	 * 
	 * @return The user's additional notes.
	 */
	public String getAdditionalNotes() {
		return mFormNotes.getText().toString();
	}
}
