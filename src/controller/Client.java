/*
 * FTP CLIENT USING SOCKET
 * 
 * @author Peter E.
 * @copyright GNU GPL 3
 */

package controller;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Socket {
		public String host;
		public int port;

		public String username;
		public String password;

		public Client(String host, int port, String username, String password) throws UnknownHostException, IOException{
			super(host,port);
			
			this.host = host;
			this.port = port;
			this.username = username;
			this.password = password;
		}
	}
