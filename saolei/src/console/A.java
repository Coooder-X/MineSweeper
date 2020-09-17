package console;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
public class A extends JFrame implements MouseListener {/////这个类没用，是测试代码的，不用看
private JButton btn = new JButton("右键点我");
public A() {
	this.setTitle("test");
	this.setSize(300, 200);
	this.add(btn);
	btn.addMouseListener(this);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	this.setVisible(true);
}
	public void actionPerformed(ActionEvent e) {
		
	}
	public static void main(String[] args) {
		new A();
	}
	
	public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {//右键点击
			btn.setText("H");
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
}
