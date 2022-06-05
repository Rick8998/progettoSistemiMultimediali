package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Main {
	public static void main(String[] args) {
		BufferedImage original = null;
		try {
			original = ImageIO.read(new File("img/SampleCol.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage[] doubleBuffer = {
				new BufferedImage(original.getWidth(), original.getHeight(), original.getType()),
				new BufferedImage(original.getWidth(), original.getHeight(), original.getType()),
		};
		
		int currentBuffer = 0;
		
		Rotate imgRotation = new Rotate();
		imgRotation.setSourceData(original);
		imgRotation.setRotationAngle(0);
		imgRotation.setCenterPoint((int)(original.getWidth()/2), (int)(original.getHeight()/2));
		
		JFrame frame = new JFrame("Image rotation");
		JLabel label = new JLabel(new ImageIcon(original));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(label);
		frame.pack();
		frame.setLocation(20, 20);
		frame.setVisible(true);
		
		for(int i = 0; i < 100; i++) {
			imgRotation.setRotationAngle(45*i);
			imgRotation.calculate();
			doubleBuffer[currentBuffer] = (BufferedImage) imgRotation.getResult();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			label.setIcon(new ImageIcon(doubleBuffer[currentBuffer]));
			if(currentBuffer == 0) {
				doubleBuffer[currentBuffer] = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
				currentBuffer = 1;
			}
			else {
				doubleBuffer[currentBuffer] = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());
				currentBuffer = 0;
			}
		}
	}
}
