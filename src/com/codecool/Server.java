package com.codecool;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	protected final int maxPlayersNumber;
	protected final int portNumber;
	protected final Printer printer = new Printer();
	protected ServerSocket serverSocket;

	public Server(int maxPlayers, int portNumber) {
		this.maxPlayersNumber = maxPlayers;
		this.portNumber = portNumber;

	}

	public void createServerSocket() {
		try {
			this.serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			printer.printError("The port is busy");
			printer.printError(e.getStackTrace());
		}
	}

	public ArrayList<Player> gatherPlayers() {

		ArrayList<Player> players = new ArrayList<Player>();

		for (int i = 0; i < maxPlayersNumber; i++) {
			try {
				Socket socket = serverSocket.accept();
				Player player = new Player();
				InputStream inputStream = socket.getInputStream();
				player.setInputStream(new ObjectInputStream(inputStream));
				OutputStream outputStream = socket.getOutputStream();
				player.setOutputStream(new ObjectOutputStream(outputStream));
				players.add(player);

			} catch (IOException e) {
				printer.printError("The port is busy");
				printer.printError(e.getStackTrace());
			}

		}
		printer.print("Players connected");
		return players;
	}

	private boolean everybodyHasName(ArrayList<Player> players) {
		for (Player player : players) {
			if (player.getName() == null)
				return false;
		}
		return true;
	}

	public ArrayList<Player> setNames(ArrayList<Player> players) {

		nameRequest(players);

		while (!everybodyHasName(players)) {
			for (Player player : players) {
				try {
					boolean availableName = true;
					String name = (String) player.getInputStream().readObject();
					for (Player player2 : players) {
						if (player.getName().equals(player2.getName())) {
							player.getOutputStream().writeObject("Name is already taken");
							availableName = false;
						}
					}
					if (availableName)
						player.setName(name);
				} catch (ClassNotFoundException e) {
					printer.printError(e.getStackTrace());
				} catch (IOException e) {
					printer.printError("The port is busy");
					printer.printError(e.getStackTrace());
				}
			}

		}

		return players;
	}

	private void nameRequest(ArrayList<Player> players) {
		for (Player player : players) {
			try {
				player.getOutputStream().writeObject("Please enter your name");
			} catch (IOException e) {
				printer.printError("The port is busy");
				printer.printError(e.getStackTrace());
			}
		}
	}

}
