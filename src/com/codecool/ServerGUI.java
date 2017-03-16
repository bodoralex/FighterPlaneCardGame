package com.codecool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by pata on 2017.03.16..
 */
public class ServerGUI extends JFrame {

    JMenuBar serverMenuBar;
    JMenu serverMenu;
    JMenuItem serverMenuItem;
    JMenuItem serverRestartMenuItem;
    JMenuItem serverExitMenuItem;
    JButton serverButton;
    JTextField playerTextField;
    JTextField robotTextField;
    JLabel serverLabel;
    JLabel playerLabel;
    Integer playerNumber;
    JLabel robotLabel;
    Integer robotNumber;
    JPanel panel = new JPanel();
    JPanel headerPanel;
    JLabel header;
    int MAX_PLAYERS = 8;
    Container container;
    JLabel sockets;
    JTextArea socketConnections;
    JScrollPane scrollPane;

    public ServerGUI() {

        container = new Container();
        container.setLayout(new BorderLayout());

        headerPanel = new JPanel();
        header = new JLabel("Fighter Plane Card Game");
        headerPanel.add(header);

        panel.setLayout(new GridLayout(4,4));
        panel.setVisible(false);

        serverMenuBar = new JMenuBar();
        setJMenuBar(serverMenuBar);

        serverMenu = new JMenu("File");
        serverMenuBar.add(serverMenu);

        serverMenuItem = new JMenuItem("New Server");
        serverMenu.add(serverMenuItem);

        serverRestartMenuItem = new JMenuItem("Restart Server");
        serverMenu.add(serverRestartMenuItem);

        serverExitMenuItem = new JMenuItem("Exit Server");
        serverMenu.add(serverExitMenuItem);

        SetServerEvent setServerEvent = new SetServerEvent();
        RestartServerEvent restartServerEvent = new RestartServerEvent();
        ExitServerEvent exitServerEvent = new ExitServerEvent();
        serverMenuItem.addActionListener(setServerEvent);
        serverRestartMenuItem.addActionListener(restartServerEvent);
        serverExitMenuItem.addActionListener(exitServerEvent);

        playerLabel = new JLabel("How many players? (max. 8)");
        panel.add(playerLabel);

        playerTextField = new JTextField(20);
        panel.add(playerTextField);

        robotLabel = new JLabel("How many robots? (max. 8)");
        panel.add(robotLabel);

        robotTextField = new JTextField(20);
        panel.add(robotTextField);

        serverLabel = new JLabel("Run Server?");
        panel.add(serverLabel);

        serverButton = new JButton("Run Server!");
        RunServerEvent runServerEvent = new RunServerEvent();
        serverButton.addActionListener(runServerEvent);
        panel.add(serverButton);

        sockets = new JLabel("Sockets:");
        panel.add(sockets);

        socketConnections = new JTextArea();
        socketConnections.setEditable(false);
        panel.add(socketConnections);

        scrollPane = new JScrollPane(socketConnections);
        panel.add(scrollPane);

        container.add(headerPanel, BorderLayout.NORTH);
        container.add(panel, BorderLayout.CENTER);

        add(container);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400,400);
        this.setVisible(true);
        this.setTitle("Server");

    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public Integer getRobotNumber() {
        return robotNumber;
    }

    public void sleepServer() {
        while(getPlayerNumber() == null
                || getRobotNumber() == null
                || playerNumber > MAX_PLAYERS
                || playerNumber < 0
                || robotNumber < 0
                || robotNumber > (MAX_PLAYERS-playerNumber)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void printConnection(Object o) {

        socketConnections.append(o.toString() + "\n");

    }

    //Nem működik ez a fos!!!!
    public class RestartServerEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            header.setText("Under development....");
            panel.setVisible(false);
            /*try {
                Runtime.getRuntime().exec("java -jar FighterPlaneCardGame.jar");
                //Nem működik ez a fos!!!!
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.exit(0);*/
        }
    }

    public class SetServerEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            header.setText("Fighter Plane Card Game");
            panel.setVisible(true);

        }
    }

    public class ExitServerEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            System.exit(0);

        }
    }

    public class RunServerEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                playerNumber = Integer.parseInt(playerTextField.getText());
            } catch (Exception exception) {
            }

            try {
                robotNumber = Integer.parseInt(robotTextField.getText());
            } catch (Exception exception) {
            }

            if(playerNumber instanceof Integer && robotNumber instanceof Integer) {
                if(playerNumber > MAX_PLAYERS) {
                    serverButton.setText("Too much player! Try again!");
                } else if(playerNumber < 0) {
                    serverButton.setText("Negative player number! Try again!");
                } else if(robotNumber < 0) {
                    serverButton.setText("Negative robot number! Try again!");
                } else if(robotNumber > (MAX_PLAYERS-playerNumber)) {
                    serverButton.setText("Too much robot! Try again!");
                } else {
                    serverButton.setText("Server Run!");
                    serverButton.setEnabled(false);
                }
            } else {
                serverButton.setText("Type in Integer values! Try again!");
            }

        }
    }

}
