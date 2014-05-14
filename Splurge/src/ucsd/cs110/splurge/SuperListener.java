package ucsd.cs110.splurge;

public abstract class SuperListener {

	/**
	 * Reference to the wrapper activity for the sake of fragment change
	 * callbacks.
	 */
	protected WrapperActivity mWrapper;

	/**
	 * Creates a new SuperListener with access to the given WrapperActivity.
	 * This constructor should always be called by subclasses.
	 * 
	 * @param act
	 *            The WrapperActivity for callbacks.
	 */
	public SuperListener(WrapperActivity act) {
		mWrapper = act;
	}
}
