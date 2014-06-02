package ucsd.cs110.splurge.connectivity.tasks;

import java.util.Collection;

import ucsd.cs110.splurge.connectivity.JSONConnectionHandler;
import ucsd.cs110.splurge.model.RestaurantListing;
import android.content.Context;
import android.os.AsyncTask;

public class RestaurantListAsyncTask extends
		AsyncTask<Object, Integer, Collection<RestaurantListing>> {

	private RestaurantListRequestListener mListener;

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
