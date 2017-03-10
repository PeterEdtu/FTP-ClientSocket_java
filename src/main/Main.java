/*
 * FTP CLIENT USING SOCKET
 * 
 * @author Peter E.
 * @copyright GNU GPL 3
 */

package main;

import java.util.Scanner;

import controller.*;

public class Main {
	private static final String host = "130.226.195.126";
	private static final int port = 21;
	private static String username = "anonymous";
	private static String password = "nothing";
	
	public static void main(String[] args) {
		
		try{
			
			Client socket1 = new Client(host, port, username, password);
			
			SocketController controller = new SocketController (socket1);
			
			int newPort = controller.getCommunication();
			
			Client socket2 = new Client("130.226.195.126", newPort, "anonymous", "nothing");
			
			SocketController controller2 = new SocketController (socket2);
			
			controller2.startFileTranfer();
			
			
		
		}catch (Exception e){
			System.out.println("Bad host and port number !");
		
		}finally{
		
		System.out.println("FTP client closed");

		}
	}

}
