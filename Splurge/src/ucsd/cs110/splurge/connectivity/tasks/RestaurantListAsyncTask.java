package ucsd.cs110.splurge.connectivity.tasks;

import java.util.Collection;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import ucsd.cs110.splurge.model.RestaurantListing;
import android.os.AsyncTask;

public class RestaurantListAsyncTask
		extends
		AsyncTask<JSONConnectionHandler, Integer, Collection<RestaurantListing>> {

	private RestaurantListRequestListener mListener;

	public RestaurantListAsyncTask(RestaurantListRequestListener listener) {
		listener = mListener;
	}

	@Override
	protected Collection<RestaurantListing> doInBackground(
			JSONConnectionHandler... params) {
		return params[0].requestRestaurantList();
	}

	@Override
	protected void onPostExecute(Collection<RestaurantListing> result) {
		mListener.receiveRetaurantList(result);
		super.onPostExecute(result);
	}
}
