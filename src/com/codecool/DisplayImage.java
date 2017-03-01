package com.codecool;
import java.awt.FlowLayout;
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
public class DisplayImage {
	public DisplayImage(Card c) throws IOException
	{
		BufferedImage img=ImageIO.read(new File("src\\"+c.getID()));
		ImageIcon icon=new ImageIcon(img);
		JFrame frame=new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(icon.getIconWidth(),icon.getIconHeight());
		JLabel lbl=new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
