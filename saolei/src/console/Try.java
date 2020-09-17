package console;

import java.awt.BorderLayout;

import javax.swing.*;

public class Try {/////这个类没用，是测试代码的，不用看

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame();
		
		JPanel all = new JPanel();
		JPanel up = new JPanel();
		JPanel down = new JPanel();
		JPanel u1 = new JPanel();
		JPanel u2 = new JPanel();
		JPanel d1 = new JPanel();
		JPanel d2 = new JPanel();
		
		up.setLayout(new BorderLayout());
		down.setLayout(new BorderLayout());
		all.setLayout(new BorderLayout());
		
		
		JButton ub1 = new JButton("ub1");
		JButton ub2 = new JButton("ub2");
		JButton db1 = new JButton("db1");
		JButton db2 = new JButton("db2");
		
		u1.add(ub1);
		u2.add(ub2);
		d1.add(db1);
		d2.add(db2);
		
		up.add(u1, BorderLayout.WEST);
		up.add(u2, BorderLayout.EAST);
		down.add(d1, BorderLayout.NORTH);
		down.add(d2, BorderLayout.SOUTH);
		
		all.add(up, BorderLayout.NORTH);
		all.add(down, BorderLayout.SOUTH);
		
		frame.add(all);
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
