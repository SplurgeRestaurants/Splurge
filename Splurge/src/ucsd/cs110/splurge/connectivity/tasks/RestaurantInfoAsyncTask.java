package ucsd.cs110.splurge.connectivity.tasks;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import ucsd.cs110.splurge.model.Restaurant;
import android.os.AsyncTask;

/**
 * Class for asynchronously requesting information on a given restaurant. When
 * the restaurant has been received, the class will notify its listener and pass
 * along the information.
 */
public class RestaurantInfoAsyncTask extends
		AsyncTask<Object, Integer, Restaurant> {

	/**
	 * Listener which will receive the restaurant when the information is
	 * received.
	 */
	private RestaurantInfoRequestListener mListener;

	/**
	 * Creates the task with the given listener. Don't forget to call
	 * <code>execute</code>!
	 * 
	 * @param listener
	 *            Object to notify when the restaurant is received.
	 */
	public RestaurantInfoAsyncTask(RestaurantInfoRequestListener listener) {
		mListener = listener;
	}

	@Override
	protected Restaurant doInBackground(Object... params) {
		return ((JSONConnectionHandler) params[0])
				.requestRestaurantInfo(((Integer) params[1]).intValue());
	}

	@Override
	protected void onPostExecute(Restaurant result) {
		mListener.receiveRestaurantInfo(result);
		super.onPostExecute(result);
	}
}
