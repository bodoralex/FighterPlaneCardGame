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
public class DisplayImage extends Thread{
            JFrame frame = new JFrame();
            JLabel lbl = new JLabel();
            JLabel lbl2 = new JLabel("Your plane: ");
            JButton button1 = new JButton("Speed");
            JButton button2 = new JButton("Height");
            JButton button3 = new JButton("Weight");
            JButton button4 = new JButton("Range");
            Integer numToReturn = 0;
    Card cardGot;

    public DisplayImage(Card c) throws IOException {
        cardGot = c;
    }

    public void run(){
        BufferedImage originalImage = null;
        try {
            originalImage = ImageIO.read(new File("src//" + cardGot.getID()));
        } catch(IOException e) {
            e.printStackTrace();
        }
        int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = resizeImage(originalImage,type);

            frame.setLayout(new FlowLayout());
            frame.setSize(600,320);
            ImageIcon icon = new ImageIcon(resizedImage);
            lbl.setIcon(icon);
            frame.add(lbl2);
            frame.add(lbl);
            frame.add(button1);
            frame.add(button2);
            frame.add(button3);
            frame.add(button4);
            button1.addActionListener(new SpeedActionListener(this.numToReturn));
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }


    public static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(512, 256, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 512, 256, null);
        g.dispose();

        return resizedImage;
    }

    public class SpeedActionListener implements ActionListener {
        Integer numToReturn = 0;
        SpeedActionListener(Integer numToReturn){
        this.numToReturn = numToReturn;
        }
        public void actionPerformed(ActionEvent e) {
            button1.setText("1");
            frame.setVisible(false);
            Integer num =Integer.parseInt(e.getSource().toString());
            getChoosenNumber(num);
        }
    }
    public Integer getChoosenNumber(Integer num){
        return num;
    }
}