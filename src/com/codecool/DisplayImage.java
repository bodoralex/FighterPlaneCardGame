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
public class DisplayImage {
    private JFrame frame = new JFrame();
    private JLabel lbl = new JLabel();
    private JLabel lblSpeed = new JLabel();
    private JLabel lblHeight = new JLabel();
    private JLabel lblWeight = new JLabel();
    private JLabel lblRange = new JLabel();
    private JLabel lbl2 = new JLabel("Your plane: ");
    private JButton button1 = new JButton("Speed");
    private JButton button2 = new JButton("Height");
    private JButton button3 = new JButton("Weight");
    private JButton button4 = new JButton("Range");
    private Integer numToReturn = 0;
    private Card cardGot;

    public DisplayImage(Card c) throws IOException {
        cardGot = c;
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("src//" + cardGot.getID()));
        } catch(IOException e) {
            e.printStackTrace();
        }
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
        BufferedImage resizedImage = resizeImage(originalImage,type);

        frame.setLayout(new FlowLayout());
        frame.setSize(1200,640);
        ImageIcon icon = new ImageIcon(resizedImage);
        lbl.setIcon(icon);
        lbl2.setText("Players card: "+cardGot.getName());
        lbl2.setVerticalAlignment(SwingConstants.TOP);
        lblSpeed.setText(""+cardGot.getSpeed());
        lblHeight.setText(""+cardGot.getMaxHeight());
        lblWeight.setText(""+cardGot.getMaxTakeoffWeight());
        lblRange.setText(""+cardGot.getRange());
        frame.add(lbl2);
        frame.add(lbl);
        frame.add(lblSpeed);
        frame.add(lblHeight);
        frame.add(lblWeight);
        frame.add(lblRange);
        button1.setVerticalAlignment(SwingConstants.BOTTOM);
        button2.setVerticalAlignment(SwingConstants.BOTTOM);
        button3.setVerticalAlignment(SwingConstants.BOTTOM);
        button4.setVerticalAlignment(SwingConstants.BOTTOM);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 1;
                frame.setVisible(false);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 2;
                frame.setVisible(false);
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 3;
                frame.setVisible(false);
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                numToReturn = 4;
                frame.setVisible(false);
            }
        });
            
    }
    
    public Integer getNumToReturn() {
    	int temp = numToReturn;
    	numToReturn = 0;
		return temp;
	}

	public static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(1024, 512, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1024, 512, null);
        g.dispose();
        return resizedImage;
    }

}