package console;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.*;
import javax.swing.event.DocumentListener;

public class SettingsFrame extends JFrame{
	private JButton easy = new JButton();
	private JButton mid = new JButton();
	private JButton hard = new JButton();
	private JButton trans = new JButton(); 
//	private JButton custom = new JButton();
	private JPanel panel = new JPanel();
	private JTextArea h = new JTextArea("10");
	private JTextArea w = new JTextArea("10");
	
	public SettingsFrame()
	{
		((JFrame)this).setTitle("Settings");
		((JFrame)this).setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-150, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-150,230,300);
		panel.setLayout(new GridLayout(11, 4, 4, 4));
		JPanel custom = new JPanel();
		JPanel customh = new JPanel();
		JPanel customw = new JPanel();
		custom.setLayout(new BorderLayout());
		JLabel l1 = new JLabel();
		JLabel l2 = new JLabel();
		JLabel l3 = new JLabel();
		JLabel l4 = new JLabel();
		JLabel l5 = new JLabel();
		JLabel l6 = new JLabel();
		JLabel l7 = new JLabel();
		l1.setText("     ");
		l2.setText("     ");
		l3.setText("     ");
		l7.setText("     ");
		l4.setText("          ");
		l5.setText("          ");
		l6.setText("自定义大小");
		easy.setText("简单——10X10");
		mid.setText("中等——15X15");
		hard.setText("困难——20X20");
		trans.setText("确定");
//		custom.setText("自定义大小");
		panel.add(l3);
		panel.add(easy);
		panel.add(l1);
		panel.add(mid);
		panel.add(l2);
		panel.add(hard);	
		panel.add(l7);
		panel.add(custom);
		panel.add(customh);
		panel.add(customw);
		panel.add(trans);
		
		JLabel lh = new JLabel("高度：");
		JLabel lw = new JLabel("宽度：");
		
		customh.setLayout(new BorderLayout());
		customw.setLayout(new BorderLayout());
		customh.add(h, BorderLayout.CENTER);
		customw.add(w, BorderLayout.CENTER);
		customh.add(lh, BorderLayout.WEST);
		customw.add(lw, BorderLayout.WEST);
		
		custom.add(l6, BorderLayout.CENTER);
//		h.getDocument().addDocumentListener((DocumentListener) this);
//		w.getDocument().addDocumentListener((DocumentListener) this);

//		n.add(h, BorderLayout.EAST);
//		s.add(w, BorderLayout.EAST);
		this.add(panel, BorderLayout.CENTER);
		this.add(l4, BorderLayout.WEST);
		this.add(l5, BorderLayout.EAST);
		((JFrame)this).setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		((JFrame)this).setVisible(true);
	}
	public JButton getEasy()
	{
		return easy;
	}
	public JButton getHard()
	{
		return hard;
	}
	public JButton getMid()
	{
		return mid;
	}
	public JButton getTrans()
	{
		return trans;
	}
	public int getCustomH()
	{
		String s = h.getText();
		int a = 10;
//		try {
		    a = Integer.parseInt(s);
//		} catch (NumberFormatException e) {
//		    e.printStackTrace();
//		}
		if( a == 0)
			a = 1;
		if( a < 0)
			a *= -1;
		return a;
	}
	public int getCustomW()
	{
		String s = w.getText();
		int a = 10;
//		try {
		    a = Integer.parseInt(s);
//		} catch (NumberFormatException e) {
//		    e.printStackTrace();
//		}
		if( a == 0)
			a = 1;
		if( a < 0)
			a *= -1;
		return a;
	}
	public JTextArea geth()
	{
		return h;
	}
	public JTextArea getw()
	{
		return w;
	}
	public static void main(String[] args)
	{
		SettingsFrame f = new SettingsFrame();
		((JFrame)f).setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		((JFrame)f).setVisible(true);
//		((JFrame)f).pack();
	}
}
