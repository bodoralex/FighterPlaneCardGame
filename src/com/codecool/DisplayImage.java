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
        BufferedImage resizedImage = new BufferedImage(512, 256, type);
        Graphics2D g = resizedImage.createGraphics();
        g.drawImage(originalImage, 0, 0, 512, 256, null);
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
            frame.setLayout(new FlowLayout());
            frame.setSize(512,256);
            lbl.setIcon(icon);
            frame.add(lbl);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Thread.sleep(3000);
            frame.setVisible(false);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}
