package ucsd.cs110.splurge.connectivity;

/**
 * Abstract class representing a message which may be passed to or from the
 * Server. This is a class instead of an interface as no object of this type
 * should bear a different superclass.
 */
public abstract class ServerMessage {

	/**
	 * String identifier for the message type field in the compiled JSON String.
	 */
	protected final static String MESSAGE_TYPE = "messageType";
	/**
	 * Number of spaces to use for each level of indentation in a human-readable
	 * JSON String.
	 */
	protected final static int INDENT_SPACES = 2;

	/**
	 * Retreive the message type String, which should identify the direction the
	 * message is going and why it is going that way.
	 * 
	 * @return A String identifying the message type.
	 */
	public abstract String getMessageType();

	/**
	 * Compiles the message to a JSON String which may be passed to or from the
	 * server. It is up to the extending class to maintain correct JSON
	 * formatting.
	 * 
	 * @return A JSON String containing the information of the message.
	 */
	public abstract String compileToJSON();

	/**
	 * Gets the suffix of the URL to which this message is to be sent. This is
	 * in order to compensate for the variety of pages receiving messages on the
	 * server side.
	 * 
	 * @return The specific page on the website receiving this message
	 */
	public abstract String getURLSuffix();
}
