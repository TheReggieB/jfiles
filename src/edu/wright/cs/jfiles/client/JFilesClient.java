/*
 * Copyright (C) 2016 - WSU CEG3120 Students
 *
 * Roberto C. Sánchez <roberto.sanchez@wright.edu>
 *
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package edu.wright.cs.jfiles.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * The main class of the JFiles client application.
 *
 * @author Roberto C. Sánchez &lt;roberto.sanchez@wright.edu&gt;
 *
 */
public class JFilesClient implements Runnable {
	private Socket socket = null;
	private Thread thread = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private JFilesClientThread client = null;

	/**
	 * The main Class.
	 * @param serverName
	 *            The name of the server.
	 * @param serverPort
	 *            The port number of the server.
	 */
	public JFilesClient(String serverName, int serverPort) {
		System.out.println("Establishing connection. Please wait ...");
		try {
			socket = new Socket("localhost", 9786);
			System.out.println("Connected: " + socket);
			start();
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}

	/**
	 * .
	 */
	@SuppressWarnings("deprecation")
	public void run() {
		System.out.print(">");
		while (thread != null) {
			try {

				streamOut.writeUTF(console.readLine());

				streamOut.flush();

			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
				stop();
			}

		}

	}

	/**
	 * This method handles the message.
	 * @param msg
	 *            The message to handle.
	 */
	public void handle(String msg) {
		if (msg.equals(".exit")) {
			System.out.println("Good bye. Press RETURN to exit ...");
			stop();
		} else {
			System.out.print(msg);
		}
	}

	/**
	 * Start the connection.
	 */
	public void start() throws IOException {
		console = new DataInputStream(System.in);
		streamOut = new DataOutputStream(socket.getOutputStream());
		if (thread == null) {
			client = new JFilesClientThread(this, socket);
			thread = new Thread(this);
			thread.start();
		}
	}

	/**
	 * Stops the thread.
	 */
	@SuppressWarnings("deprecation")
	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
		try {
			if (console != null) {
				console.close();
			} else if (streamOut != null) {
				streamOut.close();
			} else if (socket != null) {
				socket.close();
			}
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
		client.close();
		client.stop();
	}

	/**
	 * The main method.
	 */
	public static void main(String[] args) {
		JFilesClient client = new JFilesClient("localhost", 9786);
	}
}