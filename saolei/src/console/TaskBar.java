package console;

import javax.swing.JButton;
import javax.swing.*;
//import java.awt.event.*;

import console.controler.Restart;
import console.controler.showHelp;

public class TaskBar extends JPanel{
	private JButton button1 = new JButton();
	private JButton buttonSet = new JButton();
	private JButton button3 = new JButton();
//	JButton button4 = new JButton();
	private JButton button5 = new JButton();
	
	public TaskBar()
	{
		button1.setText("About");
		buttonSet.setText("Settings");
		button3.setText("Help");
	//	button4.setText("About");
		button5.setText("Restart");
//		button3.addActionListener(new showHelp());
//		button5.addActionListener(new Restart());
		this.add(buttonSet);
		this.add(button1);
		this.add(button3);
//		panel1.add(button4);
		this.add(button5);
	}
	public JButton getBS()
	{
		return buttonSet;
	}
	public JButton getBA()
	{
		return button1;
	}
	public JButton getB3()
	{
		return button3;
	}
	public JButton getB5()
	{
		return button5;
	}
}
