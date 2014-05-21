package ucsd.cs110.splurge.connectivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Class for handling connections with the server.
 */
public class ServerJSONConnection {

	private static final String HOST_ADDR = "www.splurge.com";
	private static final int HOST_PORT = 8081;

	private Socket mSocket;
	private BufferedWriter mWriter;
	private ServerListener mServerListener;

	/**
	 * Opens a connection with the server.
	 * 
	 * @throws IOException
	 */
	private void openConnection() throws IOException {
		mSocket = new Socket(HOST_ADDR, HOST_PORT);
		// That's the Decorator pattern if I ever saw it.
		mWriter = new BufferedWriter(new OutputStreamWriter(
				mSocket.getOutputStream()));
	}

	/**
	 * Closes all connections with the server.
	 * 
	 * @throws IOException
	 */
	private void closeConnection() throws IOException {
		mWriter.close();
		mSocket.close();
	}

	/**
	 * Sends a JSON string to the server.
	 * 
	 * @param json
	 *            JSON-formatted string for the server containing arbitrary
	 *            information.
	 */
	private void pushJSONString(String json) {
		try {
			mWriter.write(json);
		} catch (IOException e) {
		}
	}

	/**
	 * Takes a ServerMessage object, extracts JSON from it, and pushes the
	 * message along to the server.
	 * 
	 * @param m
	 *            A ServerMessage ready for transit to the server.
	 */
	public void pushServerMessage(ServerMessage m) {
		pushJSONString(m.compileToJSON());
	}
}
