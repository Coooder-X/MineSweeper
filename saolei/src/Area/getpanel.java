package Area;

import javax.swing.*;

public class getpanel extends JFrame{/////�����û�ã��ǲ��Դ���ģ����ÿ�
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
