package com.codecool.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by lovi on 2017.03.16..
 */
public class StartGUI extends JFrame {
    JLabel label1;
    JPanel panel1;
    JButton buttonOK;
    JTextField ipArea;
    JTextField nameArea;
    String actualUserName;
    String ipToConnect;
    Socket connection;
    ObjectInputStream input;
    ObjectOutputStream output;
    Container contentPane;

    public StartGUI() {
        label1 = new JLabel("Username: ");
        buttonOK = new JButton("OK");
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setUserName(nameArea.getText());
                setIpToConnect(ipArea.getText());
                setupConnection();
                setupStreams();
                while(true){
                    try {
                            Thread.sleep(250);
                        if(((String)input.readObject()).equals("SEND")) {
                            sendName();
                            break;
                        }
                    } catch(IOException e1) {
                        e1.printStackTrace();
                    } catch(ClassNotFoundException e1) {
                        e1.printStackTrace();
                    } catch(InterruptedException e1) {
                        e1.printStackTrace();
                    }
                }
                setVisible(false);
                startRunning();
            }
        });
        ipArea = new JTextField("127.0.0.1");
        nameArea = new JTextField("Enter username");
        panel1 = new JPanel();
        panel1.setLayout(new BoxLayout(panel1, BoxLayout.LINE_AXIS));
        panel1.add(label1);
        panel1.add(nameArea);
        panel1.add(ipArea);
        panel1.add(buttonOK);
        contentPane = getContentPane();
        contentPane.add(panel1);
        setSize(600, 30);
        setVisible(true);
    }

    private void setIpToConnect(final String actionCommand) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ipToConnect = actionCommand;
            }
        });
    }

    private void setUserName(final String actionCommand) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                actualUserName = actionCommand;
            }
        });
    }

    public void setupConnection() {
        try {
            connection = new Socket(InetAddress.getByName(ipToConnect), 6969);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void setupStreams() {
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            output.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
        try {
            input = new ObjectInputStream(connection.getInputStream());
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void sendName() {
        try {
            int counter = 0;
            output.writeObject(actualUserName);

            if(input.readObject().equals("Name already taken")) {
                counter++;
                output.writeObject(actualUserName + "(" + counter + ")");
            }
        } catch(IOException e) {
            e.printStackTrace();
        }catch(ClassNotFoundException e) {
            e.printStackTrace();

        }
    }

    private void startRunning() {
        while(true) {
            try {
                Object incomingTransmission = input.readObject();
                if(incomingTransmission instanceof ArrayList) {
                    for(Object a : (ArrayList) incomingTransmission) { //nagy arraylist
                        for(Object b : (ArrayList) a) { //arraylistbeli arraylist
                            if(((ArrayList) a).get(2).equals("TRUE")) { //kis arraylistek beli stringek
                                new DisplayLastWinner("" + ((ArrayList) a).get(0), "" + ((ArrayList) a).get(1));
                            }
                        }
                    }
                }
                else if(incomingTransmission instanceof String){
                    int numberToSend = new DisplayImage((String)incomingTransmission).getNumToReturn();
                    output.writeObject(numberToSend);
                }
            } catch(IOException e) {
                e.printStackTrace();
            } catch(ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }
}
