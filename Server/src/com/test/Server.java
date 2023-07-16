package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void start(int port) throws IOException {
		serverSocket = new ServerSocket(port);
		System.out.println("started");
		
		clientSocket = serverSocket.accept();

		out = new PrintWriter(clientSocket.getOutputStream(), true);
		in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		String greeting = in.readLine();
		System.out.println("first line read");

		if ("AAPL".equals(greeting)) {
			out.println("100");
			
		} else if ("AMZN".equals(greeting)) {
			out.println("200");
			
		} else {
			out.println("-1");
		}
		
		System.out.println("closing...");
	}

	public void stop() throws IOException {
		in.close();
		out.close();
		clientSocket.close();
		serverSocket.close();
	}

	public static void main(String[] args) throws IOException {
		Server server = new Server();
		server.start(2021);
	}

}
