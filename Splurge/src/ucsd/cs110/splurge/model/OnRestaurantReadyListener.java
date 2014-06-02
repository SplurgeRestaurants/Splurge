package ucsd.cs110.splurge.model;

/**
 * Listener class which accepts notifications for when the Model is ready to
 * provide information on a restaurant. This is intended to primarily be used
 * for notifying the View when to move on to restaurant menu page after a
 * restaurant is selected, as the restaurant may take some time to be requested
 * from the server.
 */
public interface OnRestaurantReadyListener {

	/**
	 * Method which is called when the model becomes ready to provide
	 * information on a restaurant.
	 */
	public void onRestaurantReady();
}
