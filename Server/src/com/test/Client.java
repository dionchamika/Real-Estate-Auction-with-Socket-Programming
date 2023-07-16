package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public void startConnection(String ip, int port) throws UnknownHostException, IOException {
        clientSocket = new Socket(ip, port);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        
        System.out.println("connected");
    }

    public String sendMessage(String msg) throws IOException {
        out.println(msg);
        
        System.out.println("request sent");
        String resp = in.readLine();
        
        return resp;
    }

    public void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }

	public static void main(String[] args) throws UnknownHostException, IOException {
		Client client = new Client();
		client.startConnection("localhost", 2021);
		String reply = client.sendMessage("AAPL");
		System.out.println(reply);
	}

}
