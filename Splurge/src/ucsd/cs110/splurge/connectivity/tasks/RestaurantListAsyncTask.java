package ucsd.cs110.splurge.connectivity.tasks;

import java.util.Collection;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import ucsd.cs110.splurge.model.RestaurantListing;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Class for asynchonously requesting a list of available restaurants. When the
 * request has been received, it will notify the listener and pass the
 * information along.
 * <p>
 * When calling <code>execute()</code>, the following parameters should be used:
 * <ul>
 * <li>JSONConnectionHandler which will handle the server connection</li>
 * <li>Context which will be used to access device resources</li>
 * </ul>
 * </p>
 */
public class RestaurantListAsyncTask extends
		AsyncTask<Object, Integer, Collection<RestaurantListing>> {

	/**
	 * Listener to which the response should be forwarded.
	 */
	private RestaurantListRequestListener mListener;

	/**
	 * Creates a new task handler which will report to the given listener.
	 * 
	 * @param listener
	 *            The listener which should receive the response.
	 */
	public RestaurantListAsyncTask(RestaurantListRequestListener listener) {
		mListener = listener;
	}

	@Override
	protected Collection<RestaurantListing> doInBackground(Object... params) {
		return ((JSONConnectionHandler) params[0])
				.requestRestaurantList((Context) params[1]);
	}

	@Override
	protected void onPostExecute(Collection<RestaurantListing> result) {
		mListener.receiveRetaurantList(result);
		super.onPostExecute(result);
	}
}
