package Area;

import javax.swing.*;
import javax.swing.JPanel;

public class textpanel extends JPanel{/////这个类没用，是测试代码的，不用看
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
