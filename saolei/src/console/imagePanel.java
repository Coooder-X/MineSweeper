package console;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

public class imagePanel extends JPanel{
	Image image = null;
	public void paint(Graphics g){
		try
		{
			image = ImageIO.read(new File("C:\\用户\\1104\\图片\\本机照片\\感动网友.jpg"));
			g.drawImage(image,0,0,550,400,this);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public static void main(String args[]) {
		JFrame j = new JFrame();
		JPanel i = new imagePanel();
		i.setBounds(0, 0, 80, 80);
		j.add(i);
		j.setVisible(true);
		j.pack();
		j.setDefaultCloseOperation(j.EXIT_ON_CLOSE);
	}
}
