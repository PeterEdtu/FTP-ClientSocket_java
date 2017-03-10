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
			connect(clientSocket.username, clientSocket.password, true);
			
			print(write("RETR data.txt"));

			disconnect();

		}catch (Exception e){
			print("Connection failed " + e);

		}

	}

	public int getCommunication(){
		try{
			connect(clientSocket.username, clientSocket.password, false);

			String[] pasv = write("PASV").split(",");

			String[] ports = new String[2];
			ports[0] = pasv[4];
			ports[1] = pasv[5].replace(")", "").replace(".", "");

			int communicationPort = Integer.parseInt(ports[0]) * 256 + Integer.parseInt(ports[1]);

			print("Communication port is " + communicationPort);

			//disconnect();
			
			return communicationPort;

		}catch (Exception e){
			print("Connection failed " + e);

		}
		return -1;
	}


	public void connect(String username, String password, boolean welcome) throws Exception{
		print("Connection in progress");

		out = new DataOutputStream(clientSocket.getOutputStream()); //writer
		print("Output ready");

		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); //read buffer
		print("Input ready");

		if(welcome)
			showWelcomeMessage();

		write("USER " + username);
		write("PASS " + password);

		String verifyMessage = read();

		print(verifyMessage);

		if(Integer.parseInt(verifyMessage.split(" ")[0]) == 230)
			print("login success");

		else
			throw new Exception("username or/and password invalid");

		print("Connection etablished");

	}

	public void disconnect() throws IOException{
		print(write("QUIT"));
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
