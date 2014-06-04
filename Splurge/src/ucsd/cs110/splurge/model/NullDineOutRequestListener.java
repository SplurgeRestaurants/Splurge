package ucsd.cs110.splurge.model;

import ucsd.cs110.splurge.connectivity.tasks.DineOutRequestListener;

/**
 * Null implementation of the DineOutRequestListener interface.
 */
public class NullDineOutRequestListener implements DineOutRequestListener {

	@Override
	public void receiveDineOutId(int id) {
		// Do nothing.
	}
}
