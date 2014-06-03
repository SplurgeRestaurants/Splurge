package ucsd.cs110.splurge.connectivity.tasks;

import java.util.Calendar;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import android.os.AsyncTask;

/**
 * Class for asynchronously requesting a reservation from the server.
 * 
 * <p>
 * Its execute parameters are as follows:
 * <ul>
 * <li>JSONConnectionHandler which will be used to make the request</li>
 * <li>int identification number of the restaurant</li>
 * <li>int party size</li>
 * <li>String party name</li>
 * <li>Calendar starting time of reservation</li>
 * </ul>
 * </p>
 */
public class ReservationRequestAsyncTask extends
		AsyncTask<Object, Integer, Integer> {

	/**
	 * Listener to notify when the reservation response is received from the
	 * server.
	 */
	private ReservationRequestListener mListener;

	/**
	 * Creates a new task handler which will notify the given listener upon
	 * completion.
	 * 
	 * @param listener
	 *            Object to notify when the response is received from the
	 *            server.
	 */
	public ReservationRequestAsyncTask(ReservationRequestListener listener) {
		mListener = listener;
	}

	@Override
	protected Integer doInBackground(Object... params) {
		return ((JSONConnectionHandler) params[0]).requestReservation(
				(Integer) params[1], (Integer) params[2], (String) params[3],
				(Calendar) params[4]);
	}

	@Override
	protected void onPostExecute(Integer result) {
		super.onPostExecute(result);
		mListener.receiveReservationId(result);
	}
}
