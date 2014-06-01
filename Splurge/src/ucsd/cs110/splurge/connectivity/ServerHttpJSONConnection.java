package ucsd.cs110.splurge.connectivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

/**
 * Class for handling connections with the server.
 */
public class ServerHttpJSONConnection {

	/**
	 * Constant field holding the host address of the Splurge server.
	 */
	private static final String HOST_ADDR = "http://splurge.herokuapp.com/";

	/**
	 * Object handling the actual connection.
	 */
	private HttpURLConnection mServerConnection;
	/**
	 * Writer class for pushing messages through the connection.
	 */
	private BufferedWriter mWriter;

	/**
	 * Opens a connection with the server.
	 * 
	 * @throws IOException
	 */
	private void openConnection(String suffix) throws IOException {
		mServerConnection = (HttpURLConnection) (new URL(HOST_ADDR + suffix))
				.openConnection();
		mServerConnection.setDoInput(true);
		mServerConnection.setDoOutput(true);
		// That's the Decorator pattern if I ever saw it.
		mWriter = new BufferedWriter(new OutputStreamWriter(
				mServerConnection.getOutputStream()));
	}

	/**
	 * Closes all connections with the server.
	 * 
	 * @throws IOException
	 */
	private void closeConnection() throws IOException {
		mWriter.close();
		mServerConnection.disconnect();
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
	 * message along to the server. It then wait for a response, which is
	 * subsequently returned as a String. If an error occurs, then the response
	 * will be empty.
	 * 
	 * @param m
	 *            A ServerMessage ready for transit to the server.
	 * @return The Server's response message.
	 */
	public String pushServerMessage(ServerMessage m) {
		try {
			openConnection(m.getMessageType());
			pushJSONString(m.compileToJSON());
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					mServerConnection.getInputStream()));
			String add;
			String ret = "";
			while ((add = reader.readLine()) != null)
				ret += add;
			Log.i("Splurge", "Response message: " + ret);
			closeConnection();
		} catch (IOException e) {
			Log.e("Splurge", "Exception occurred while sending data.");
			Log.e("Splurge", e.getMessage());
			e.printStackTrace();
		} finally {
			mServerConnection.disconnect();
		}
		return "";
	}
}
