package Area;

import javax.swing.*;

public class getpanel extends JFrame{/////这个类没用，是测试代码的，不用看
	static JFrame frame;// = new JFrame();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame = new JFrame();
		textpanel p = new textpanel();
		frame.add(p);
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

}
