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

	protected final int playersNumber;
	protected final int portNumber;
	protected final Printer printer = new Printer();
	protected ServerSocket serverSocket;
	protected final int robotsNumber;
	protected ServerGUI serverGUI;

	public Server(int players, int robotsNumber, int portNumber) {
		this.playersNumber = players;
		this.portNumber = portNumber;
		this.robotsNumber = robotsNumber;

	}

	public void createServerSocket() {
		try {
			this.serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			serverGUI.printToTextArea("3");
			serverGUI.printToTextArea(e.getStackTrace());
		}
	}
	
	

	public void setServerGUI(ServerGUI serverGUI) {
		this.serverGUI = serverGUI;
	}

	public ServerGUI getServerGUI() {
		return serverGUI;
	}

	public int getPlayersNumber() {
		return playersNumber;
	}

	public int getRobotsNumber() {
		return robotsNumber;
	}

	public ArrayList<Player> gatherPlayers() {

		ArrayList<Player> players = new ArrayList<Player>();

		for (int i = 0; i < playersNumber; i++) {
			try {
				Socket socket = serverSocket.accept();
				Player player = new Player();
				InputStream inputStream = socket.getInputStream();
				player.setInputStream(new ObjectInputStream(inputStream));
				OutputStream outputStream = socket.getOutputStream();
				player.setOutputStream(new ObjectOutputStream(outputStream));
				player.setServer(this);
				players.add(player);

			} catch (IOException e) {
				serverGUI.printToTextArea("4");
				serverGUI.printToTextArea(e.getStackTrace());
			}
		}
		serverGUI.printToTextArea("Players connected");
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
					String name = (String) player.getInputStream().readObject();
					// TODO veszélyforrás
					// mivanhanemírbesemmi
					if (isANewName(players, name)) {
						player.setName(name);
					}
				} catch (ClassNotFoundException e) {
					serverGUI.printToTextArea(e.getStackTrace());
				} catch (IOException e) {
					serverGUI.printToTextArea("5");
					serverGUI.printToTextArea(e.getStackTrace());
				}
			}
		}
		return players;
	}

	private boolean isANewName(ArrayList<Player> players, String name) throws IOException {
		if (name.length() < 4 || name == null)
			return false;
		for (Player player : players) {
			if (name.equals(player.getName())) {
				player.getOutputStream().writeObject("Name is already taken");
				return false;
			}
		}
		return true;
	}

	private void nameRequest(ArrayList<Player> players) {
		for (Player player : players) {
			try {
				player.getOutputStream().writeObject("Please enter your name");
			} catch (IOException e) {
				serverGUI.printToTextArea("0");
				serverGUI.printToTextArea(e.getStackTrace());
			}
		}
	}

	public void send(ObjectOutputStream outStream, Object object) {

		try {
			outStream.writeObject(object);
		} catch (IOException e) {
			serverGUI.printToTextArea("1");
			serverGUI.printToTextArea(e.getStackTrace());
		}

	}

	public Object receive(ObjectInputStream inputStream) {
		Object object = null;
		try {
			object = inputStream.readObject();
		} catch (ClassNotFoundException e) {
		} catch (IOException e) {
			serverGUI.printToTextArea("2");
			serverGUI.printToTextArea(e.getStackTrace());
		}
		return object;
	}
}
