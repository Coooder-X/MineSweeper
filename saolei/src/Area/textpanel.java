package Area;

import javax.swing.*;
import javax.swing.JPanel;

public class textpanel extends JPanel{/////�����û�ã��ǲ��Դ���ģ����ÿ�
	private JButton b = new JButton();
	public textpanel()
	{
		b.setText("srg");
		this.add(b);
	}
	public JButton getb()
	{
		return b;
	}
}
