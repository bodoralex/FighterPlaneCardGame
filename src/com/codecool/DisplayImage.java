package com.codecool;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;


/**
 * Created by Lovi on 2017. 03. 02. @ 0:43.
 */
public class DisplayImage extends JFrame{
    private JLabel lbl;
    private JLabel lblSpeed;
    private JLabel lblHeight;
    private JLabel lblWeight;
    private JLabel lblRange;
    private JLabel lbl2;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel topLabel;
    private JPanel picturePanel;
    private JPanel labelPanel;
    private JPanel buttonPanel;
    private Container contentPane;
    private Integer numToReturn;
    private Card cardGot;

    public DisplayImage(Card c) throws IOException {
        cardGot = c;

        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("src//" + cardGot.getID()));
        } catch(IOException e) {
            e.printStackTrace();
        }
        int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = resizeImage(originalImage, type);
        setAlwaysOnTop(true);
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon(resizedImage);
        lbl = new JLabel();
        lbl.setIcon(icon);
        lbl.setHorizontalAlignment(SwingConstants.LEFT);
        lbl2 = new JLabel();
        lbl2.setText("Players card: " + cardGot.getName());
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
        labelPanel = new JPanel();
        labelPanel.setLayout(new BoxLayout(labelPanel,BoxLayout.LINE_AXIS));
        labelPanel.add(lblSpeed);
        labelPanel.add(lblHeight);
        labelPanel.add(lblWeight);
        labelPanel.add(lblRange);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.LINE_AXIS));
        button1 = new JButton("Speed");
        buttonPanel.add(button1);
        buttonPanel.add(Box.createRigidArea(new Dimension(45,0)));
        button2 = new JButton("Height");
        buttonPanel.add(button2);
        buttonPanel.add(Box.createRigidArea(new Dimension(75,0)));
        button3 = new JButton("Weight");
        buttonPanel.add(button3);
        buttonPanel.add(Box.createRigidArea(new Dimension(60,0)));
        button4 = new JButton("Range");
        buttonPanel.add(button4);
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

        numToReturn = 0;
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 1;
                setVisible(false);

            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 2;
                setVisible(false);

            }
        });
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 3;
                setVisible(false);

            }
        });
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 4;
                setVisible(false);

            }
        });

    }

    public static BufferedImage resizeImage(BufferedImage originalImage, int type) {
        BufferedImage resizedImage = new BufferedImage(512, 256, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 512, 256, null);
        g.dispose();

        return resizedImage;
    }

    public Integer getNumToReturn() {
        int temp = numToReturn;
        numToReturn = 0;
        return temp;
    }

    public Integer getChoosenNumber() {
        return this.numToReturn;
    }

    public class SpeedActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            numToReturn = 1;
            System.out.println(numToReturn);
        }
    }
}