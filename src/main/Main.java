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
	private static final String host = "162.210.102.210";
	private static final int port = 21;
	private static String username;
	private static String password;
	private static Scanner input;
	
	public static void main(String[] args) {
		input = new Scanner(System.in);
		
		System.out.println(host + "\nUsername :");
		username = input.nextLine();
		
		System.out.println("Password :");
		password = input.nextLine();
		
		
		try{
		
		new SocketController (new Client(host, port, username, password)).startFileTranfer();
		
		}catch (Exception e){
			System.out.println("Bad host and port number !");
		
		}finally{
		
		System.out.println("FTP client closed");

		}
	}

}
