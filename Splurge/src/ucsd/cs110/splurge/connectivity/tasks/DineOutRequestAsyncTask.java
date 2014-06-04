package ucsd.cs110.splurge.connectivity.tasks;

import java.util.Calendar;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import android.os.AsyncTask;

/**
 * Class for asynchronously requesting a dine-out order from the server.
 * 
 * 
 * <p>
 * Its execute parameters are as follows:
 * <ul>
 * <li>JSONConnectionHandler for pushing along the request</li>
 * <li>Integer id of the restaurant at which the request is to be made</li>
 * <li>String name of the requester</li>
 * <li>String phone number at which the requester may be contacted</li>
 * <li>String address to which the delivery is to be made (if applicable)</li>
 * <li>String kind of request to make, as specified by
 * DineOutRequestMessage.KIND_*</li>
 * <li>Calendar time at which the request is to be fulfilled</li>
 * <li>String description of the order being made</li>
 * </ul>
 * </p>
 */
public class DineOutRequestAsyncTask extends
		AsyncTask<Object, Integer, Integer> {

	private DineOutRequestListener mListener;

	public DineOutRequestAsyncTask(DineOutRequestListener listener) {
		mListener = listener;
	}

	@Override
	protected Integer doInBackground(Object... params) {
		return ((JSONConnectionHandler) params[0]).requestDineOut(
				(Integer) params[1], (String) params[2], (String) params[3],
				(String) params[4], (String) params[5], (Calendar) params[6],
				(String) params[7]);
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		mListener.receiveDineOutId(result);
	}
}
