package com.codecool.client;

import com.codecool.Card;
import com.codecool.Deck;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by lovi on 2017.03.16..
 */
public class DisplayLastWinner extends JFrame {
    private JLabel lbl;
    private JLabel lblSpeed;
    private JLabel lblHeight;
    private JLabel lblWeight;
    private JLabel lblRange;
    private JLabel lbl2;
    private JLabel lbl3;
    private JButton button1;
    private JPanel topLabel;
    private JPanel picturePanel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private Container contentPane;
    private Card cardGot;
    private Deck toSearch;

    public DisplayLastWinner(String c,String winnerName) throws IOException {
        toSearch = new Deck();
        toSearch.fillDeck(Card.values());
        for(Card d : Card.values()){
            if(d.getID() == c){
                cardGot = d;
            }
        }

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("src//" + cardGot.getID()));
        } catch(IOException e) {
            e.printStackTrace();
        }
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = DisplayImage.resizeImage(originalImage, type);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(resizedImage);
        lbl = new JLabel();
        lbl.setIcon(icon);
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl2 = new JLabel();
        lbl3 = new JLabel();
        lbl3.setText("Last turns winner: "+winnerName);
        lbl2.setText("Last turns winner card: " + cardGot.getName());
        lblSpeed = new JLabel();
        lblSpeed.setText("Speed: " + cardGot.getSpeed() + "km/h | ");
        lblHeight = new JLabel();
        lblHeight.setText("Max Height: " + cardGot.getMaxHeight() + " m | ");
        lblWeight = new JLabel();
        lblWeight.setText("Max Weight: " + cardGot.getMaxTakeoffWeight() + " kg |");
        lblRange = new JLabel();
        lblRange.setText("Range: " + cardGot.getRange() + " km");
        picturePanel = new JPanel();
        picturePanel.setLayout(new BoxLayout(picturePanel,BoxLayout.LINE_AXIS));
        picturePanel.add(lbl);
        lbl2.setAlignmentX(LEFT_ALIGNMENT);
        topLabel = new JPanel();
        topLabel.setLayout(new BoxLayout(topLabel,BoxLayout.LINE_AXIS));
        topLabel.add(lbl2);
        topLabel.add(lbl3);
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.LINE_AXIS));
        labelPanel.add(lblSpeed);
        labelPanel.add(lblHeight);
        labelPanel.add(lblWeight);
        labelPanel.add(lblRange);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
        button1 = new JButton("Close");
        buttonPanel.add(button1);
        contentPane = new Container();
        contentPane.setLayout(new BoxLayout(contentPane,BoxLayout.Y_AXIS));
        contentPane.add(topLabel);
        contentPane.add(picturePanel);
        contentPane.add(labelPanel);
        contentPane.add(buttonPanel);
        add(contentPane);
        setSize(650, 350);
        setMinimumSize(new Dimension(600,320));
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        try {
            Thread.sleep(5000);
            setVisible(false);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


    }



}
