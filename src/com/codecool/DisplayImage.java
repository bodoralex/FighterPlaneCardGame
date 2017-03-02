package com.codecool;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Created by Lovi on 2017. 03. 02. @ 0:43.
 */
public class DisplayImage extends Thread {
    Card cardGot;

    public DisplayImage(Card c) throws IOException {
        cardGot = c;
    }
    public static BufferedImage resizeImage(BufferedImage originalImage, int type){
        BufferedImage resizedImage = new BufferedImage(1024, 512, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 1024, 512, null);
        g.dispose();

        return resizedImage;
    }

    @Override
    public void run() {
        try {
            BufferedImage originalImage = ImageIO.read(new File("src//" + cardGot.getID()));
            int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            BufferedImage resizedImage = resizeImage(originalImage,type);
            ImageIcon icon = new ImageIcon(resizedImage);
            JFrame frame = new JFrame();
            JLabel lbl = new JLabel();
            JLabel lbl2 = new JLabel();
            JLabel lblSpeed = new JLabel("Speed: "+cardGot.getSpeed());
            JLabel lblHeight = new JLabel("Max Height: "+cardGot.getMaxHeight());
            JLabel lblWeight = new JLabel("Max Takeoff Weight: "+cardGot.getMaxTakeoffWeight());
            JLabel lblRange = new JLabel("Range: "+cardGot.getRange());
            frame.setLayout(new FlowLayout());
            frame.setSize(1024,600);
            lbl.setIcon(icon);
            lbl2.setText("Attacker players card: "+cardGot.getName());
            frame.add(lbl2);
            frame.add(lbl);
            frame.add(lblSpeed);
            frame.add(lblHeight);
            frame.add(lblWeight);
            frame.add(lblRange);

            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Thread.sleep(5000);
            frame.setVisible(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
