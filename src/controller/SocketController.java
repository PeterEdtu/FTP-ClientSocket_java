/*
 * FTP CLIENT USING SOCKET
 * 
 * @author Peter E.
 * @copyright GNU GPL 3
 */

package controller;

import java.io.*;


public class SocketController {


	Client clientSocket;
	private DataOutputStream out;
	private BufferedReader in;

	public SocketController(Client clientSocket){
		try{
			this.clientSocket = clientSocket;
		}catch (Exception e){

		}
	}

	public void startFileTranfer(){
		try{
			connect(clientSocket.username, clientSocket.password);

			disconnect();

		}catch (Exception e){
			System.out.println("Connection failed " + e);

		}

	}


	public void connect(String username, String password) throws Exception{
		print("Connection in progress");

		out = new DataOutputStream(clientSocket.getOutputStream()); //writer
		print("Output ready");

		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //read buffer
		print("Input ready");

		showWelcomeMessage();

		write("USER " + username);
		write("PASS " + password);

		String verifyMessage = read();

		print(verifyMessage);

		if(Integer.parseInt(verifyMessage.split(" ")[0]) == 230)
			print("Connection success");
		
		else
			throw new Exception("username or/and password invalid");


	}

	public void disconnect() throws IOException{
		clientSocket.close();
	}

	private String write(String message) throws IOException{
		out.writeBytes(message + "\n");
		return read();
	}

	private String read() throws IOException{
		return in.readLine();
	}

	private void print(String message){
		System.out.println(message);
	}
	
	private void showWelcomeMessage() throws IOException{
		print("------------------Welcome message-------------------");

		print(read());
		print(read());
		print(read());
		print(read());
		print("---------------------------------------------------");
	}

}
