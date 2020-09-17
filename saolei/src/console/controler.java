package console;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import Area.ACell;
import Area.Area;
import console.testTime.TestActionListener;

public class controler extends JFrame{
	
	static int showMineNum = 0;//
	static int MineNum = 0;//该不该写在这？
	static int areaHeight = 10;
	static int areaWidth = 10;
	static String k = "       ";//"       "
	JFrame frame;// = new JFrame();
	Area area;
	JPanel panel, panelTask;
	JLabel face = new JLabel();
	JTextArea remain;
	JTextArea time;
	public long countMis,countSec,countMin,countHour;//计时变量
    public DecimalFormat textFormat=new DecimalFormat("00");//格式化输出
    Timer timer = new Timer(1000,new TimeActionListener());//计时单位10ms
	
	public static void main(String[] args) {
		
		showMineNum = 0;
		MineNum = 0;
		controler con = new controler();
		con.go();
	}
	public void go()
	{
		showMineNum = 0;
		MineNum = 0;
		countHour=0;
        countMin=0;
        countSec=0;
        timer.stop();//否则在Restart后，计时器将马上从0开始计时，并不会等任意键按下（Restart通过调用go()实现）
		frame = new JFrame();
		//使窗体出现在屏幕中间
		frame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-300,600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
//		frame.setTitle("扫雷 3.0");
		if( areaHeight == 10 && areaWidth == 10)//关于任意模式restart后标题变回简单模式的bug修正
			frame.setTitle("扫雷 3.0  —— 简单模式");
		else if( areaHeight == 15 && areaWidth == 15)
			frame.setTitle("扫雷 3.0  —— 中级模式");
		else if( areaHeight == 20 && areaWidth == 20)
			frame.setTitle("扫雷 3.0  —— 困难模式");
		else
			frame.setTitle("扫雷 3.0  —— 自定义模式");

		panel = new JPanel();//放置button矩阵的panel
		area = new Area(areaHeight, areaWidth);
		panel.setLayout(new GridLayout(area.getH(), area.getW(), 4, 4));//设置panel布局
		
		for( int i = 0; i < area.getH(); ++i)
		{
			for( int j = 0; j < area.getW(); ++j)
			{
				area.place(i, j, new ACell());//逐个创建对象
				panel.add(area.getCell(i, j).getButton());//将cell中的button加入panel 
				area.getCell(i, j).getButton().setBorder(BorderFactory.createRaisedBevelBorder());//设置凸起来的按钮
//				area.getCell(i, j).getButton().setBorderPainted(false);//设置按钮没有边框
				area.getCell(i, j).getButton().addMouseListener(new monitor(i, j, area));
				if( Math.random() < 0.15)//概率放雷
				{
					ACell tmp = area.getCell(i, j);
					tmp.setMine(true);
					MineNum++;
					showMineNum++;
				}
			}
		}	
		frame.add(panel,BorderLayout.CENTER);// 加入button矩阵
//		JPanel panelTask = taskbar(frame);// 新建放置选项栏的panel
		panelTask = new TaskBar();
//		((TaskBar) panelTask).getB2().addActionListener(new TimeActionListener());
		((TaskBar) panelTask).getBA().addActionListener(new showAbout());
		((TaskBar) panelTask).getBS().addActionListener(new Set());
		((TaskBar) panelTask).getB3().addActionListener(new showHelp());
		((TaskBar) panelTask).getB5().addActionListener(new Restart());
		
		JPanel totalPanel = new JPanel();// 新建放置选项栏、计数器计时器的 总panel
		totalPanel.setLayout(new BorderLayout());// 总panel的布局设置
		totalPanel.add(panelTask, BorderLayout.NORTH);// 先将选项栏放在总panel北方
//		JTextArea showNum = new JTextArea();
		JPanel ShowNum = new JPanel();
		ShowNum.setLayout(new BorderLayout());// 设置计数器计时器栏的布局
		face.setText("(^W^)");
		JPanel left = new JPanel();// 左panel放计数器
		JPanel right = new JPanel();//  中panel放表情
		JPanel center = new JPanel();// 右panel放计时器
		
		remain = new JTextArea();// 计时器与计数器用JTextArea实现
		time = new JTextArea();
		remain.setText("    " + showMineNum + "    ");
		remain.setFont(new Font("微软雅黑", Font.BOLD, 14));
		remain.setForeground(Color.WHITE);
		remain.setBackground(new Color(80,80,80));
		remain.setEditable(false);
		time.setText(textFormat.format(countHour)+":"+textFormat.format(countMin)+
		        ":"+textFormat.format(countSec));
		time.setFont(new Font("微软雅黑", Font.BOLD, 14));
		time.setForeground(Color.WHITE);
		time.setBackground(new Color(80,80,80));
		time.setEditable(false);
		
		left.add(remain);
		right.add(time);
		center.add(face);
		ShowNum.add(left, BorderLayout.WEST);
		ShowNum.add(right, BorderLayout.EAST);
		ShowNum.add(center, BorderLayout.CENTER);
		
		totalPanel.add(ShowNum, BorderLayout.SOUTH);
		frame.add( totalPanel, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);
//		System.out.println(frame.getComponentAt(panelTask.getX(), panelTask.getY()).getComponentAt(10, 10));
	}

	public void Click(int r, int c, Area obj) {
		ACell tmp = obj.getCell(r, c);
		int num = obj.CountAndSet(r, c);//计算周边地雷个数
		if( tmp.getMine() == true)
		{
			if( tmp.getClicked() == true)
				return ;// 	游戏结束后不能点X，否则将再次弹出游戏失败的窗体
			face.setText("(QAQ)");// 加表情
			tmp.getButton().setText("X");//全部设为无法点击
			for( int i = 0; i < obj.getH(); ++i)// 游戏结束时，显示屏幕上所有雷的布局
			{
				for( int j = 0; j < obj.getW(); ++j)
				{
					obj.getCell(i, j).setClicked(true);//将所有格子按下 否则游戏结束后还能挖雷
					if( obj.getCell(i, j).getMine() == true)
					{
						obj.getCell(i, j).getButton().setForeground(Color.BLACK);
						obj.getCell(i, j).getButton().setText("X");
						obj.getCell(i, j).getButton().setBackground(Color.red);
						obj.getCell(i, j).setClicked(true);
					}
					else if( obj.getCell(i, j).getMine() == false && obj.getCell(i, j).getButton().getText().equals("!"))
						obj.getCell(i, j).getButton().setText("X");
					//插错旗的情况，将旗置为红叉
					else if(obj.getCell(i, j).getButTxt().equals(k))
						obj.getCell(i, j).getButton().setEnabled(false);//将格子置为不可点击
				}
			}
			Lost();
			return;
		}
		else if( num == 0)
		{
			if( tmp.getClicked() == true)
				return ;//		判断重复点击，否则游戏结束后还能挖雷
			tmp.getButton().setEnabled(false);
			tmp.setClicked(true);
			System.out.println("click: " + r +", " + c);
			int dx[] = { -1, -1, -1, 0, 1, 1, 1, 0};
			int dy[] = { -1, 0, 1, 1, 1, 0, -1, -1};
			for( int i = 0; i < 8; ++i)
			{
				int x = r + dx[i], y = c + dy[i];
				if( x < 0 || x >= obj.getH() || y < 0 || y >= obj.getW())
					continue;
				if( obj.getCell(x, y).getButTxt().equals(k) && obj.getCell(x, y).getMine()==false && obj.getCell(x, y).getClicked()==false)
				{
					Click( x, y, obj); 
					obj.getCell(x, y).setClicked(true);// 标记已点击
				}
			}
			return ;
		}
		else if( num > 0)
		{
			if( tmp.getClicked() == true)
				return ;//		判断重复 点击否则游戏结束后还能挖雷
			tmp.setButton(""+ num);
			tmp.getButton().setFont(new Font("黑体", Font.BOLD, 16));
			return ;
		}
	}
	public void mouseClick(MouseEvent e, int r, int c, Area obj)
	{
		ACell tmp = obj.getCell(r, c);
		if( e.getButton() == MouseEvent.BUTTON3)//右键点击
		{
			if( tmp.getButTxt().equals("!"))// 若插了旗，则执行撤回操作
			{
				tmp.setButton(" ");
				tmp.getButton().setForeground(Color.BLACK);// 此时颜色为红 也要还原成黑
				showMineNum++;
				remain.setText("    " + showMineNum + "    ");
				if( tmp.getMine() == true)
					MineNum++;
			}
			else
			{
				if( tmp.getClicked() == true)
					return;
				tmp.setButton("!");
				tmp.getButton().setFont(new Font("微软雅黑", Font.BOLD, 16));
				tmp.getButton().setForeground(Color.RED);
				showMineNum--;
				remain.setText("    " + showMineNum + "    ");
				if( tmp.getMine() == true)
					MineNum--;
				if( MineNum == 0 && showMineNum == 0)
				{//	若不满足showMineNum == 0，则在1X1地图且没有雷的时候，出现插旗也判赢的情况，此时
					//showMineNum就小于0，因此，既然赢，则showMineNum也一定要是0
					Win();							
					face.setText("\\(>w<)/");// 加表情
				}
			}
			
		}
		else if( e.getButton() == MouseEvent.BUTTON1)//左键点击
		{
			if(countSec == 0 && countMin == 0 && countHour == 0)
			{
				timer.start();
			}
			if(MineNum == 0 && tmp.getClicked()==false)//存在刚好初始时就没有雷的情况，在
			{//自定义成1X1地图的时候常见，加上Clicked的判断，则是保证不在win之后再次win，多次弹窗
				if( !tmp.getButTxt().equals("!"))
				{
					Click(r, c, obj);// 否则在1X1时，插了旗后点击左键还能win
					Win();
				}	
			}
			if( !tmp.getButTxt().equals("!"))
			{
				Click(r, c, obj);
			}
		}
	}

	class monitor implements MouseListener {
		private int r, c;
		private Area tmp;
		public monitor(int r, int c, Area o)
		{
			this.r = r;
			this.c = c;
			this.tmp = o;
		}
        public void Performed(MouseEvent e)
        {
        	mouseClick(e, r, c, tmp);
        }
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
        	mouseClick(e, r, c, tmp);
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
     }
	public void Win()
	{
		timer.stop();
		JFrame WinJ = new JFrame(" ");
		JButton b = new JButton("确定");
		b.addActionListener(new close(WinJ));
		JPanel P = new JPanel();
		JLabel P2 = new JLabel("   You Win!  ", JLabel.CENTER);
		P2.setFont(new Font("微软雅黑", Font.BOLD, 20));
		P.setLayout(new BorderLayout());
		JLabel TheTime = new JLabel("    You spent:  "+textFormat.format(countHour)+":"+textFormat.format(countMin)+
		        ":"+textFormat.format(countSec) + "    ");
		TheTime.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		P.add(P2, BorderLayout.NORTH);
		P.add(TheTime, BorderLayout.CENTER);
		P.add(b, BorderLayout.SOUTH);
		WinJ.add(P);

		WinJ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		WinJ.setLocationRelativeTo(null);// 出现在中间
		WinJ.setResizable(false);
		WinJ.pack();
		WinJ.setVisible(true);
		for( int i = 0; i < area.getH(); ++i)// 游戏结束时，显示屏幕上所有雷的布局
		{
			for( int j = 0; j < area.getW(); ++j)
			{
				area.getCell(i, j).setClicked(true);// 否则，win之后再点击方格，将多次弹窗
			}
		}
	}
	public void Lost()
	{
		timer.stop();
		JFrame LostJ = new JFrame(" ");
		JButton b = new JButton("确定");
		b.addActionListener(new close(LostJ));
		JPanel P = new JPanel();
		P.setLayout(new BorderLayout());
		JLabel P2 = new JLabel("Boom Boom Boom!", JLabel.CENTER);
		P2.setFont(new Font("微软雅黑", Font.BOLD, 16));
		P.add(P2, BorderLayout.NORTH);
		P.add(b);
		LostJ.add(P);

		LostJ.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		LostJ.setLocationRelativeTo(null); //出现在中间
		LostJ.setResizable(false);
		LostJ.pack();
		LostJ.setVisible(true);
	}
	class close implements ActionListener{
		JFrame obj = new JFrame();
		public close(JFrame o)
		{
			obj = o;
		}
		public void actionPerformed(ActionEvent e) {
//			System.exit(0);
			obj.dispose();
		}
	}
	
	public JPanel taskbar( JFrame frame)
	{
		JPanel panel1 = new JPanel();
		
		JButton button1 = new JButton();
		JButton button2 = new JButton();
		JButton button3 = new JButton();
//		JButton button4 = new JButton();
		JButton button5 = new JButton();
		
		button1.setText("Settings");
		button2.setText("Play");
		button3.setText("Help");
//		button4.setText("About");
		button5.setText("Restart");
		button3.addActionListener(new showHelp());
		button5.addActionListener(new Restart());
		
		panel1.add(button2);
		panel1.add(button1);
		panel1.add(button3);
//		panel1.add(button4);
		panel1.add(button5);
//		JLabel label1 = new JLabel();
//		label1.setText("(^w^)");
		return panel1;
	}
	public void countTime()
	{
		time.setText(textFormat.format(countHour)+":"+textFormat.format(countMin)+
		        ":"+textFormat.format(countSec));
		repaint();
	}
	
	class TimeActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
	        countSec++;
	        if(countSec>=59)
	        {
		        countMin++;
		        countSec=0;
		        if(countMin>=59)
		        {
		             countHour++;
		             countMin=0;
		        }
	         }
	         countTime();                              
		}
	}
	class showHelp implements ActionListener
	{
		JFrame tmpFrame;
		public void actionPerformed(ActionEvent e) {
			if(tmpFrame != null)
				tmpFrame.dispose();
			tmpFrame = new JFrame();
			JLabel label = new JLabel();
			label.setText("规则如下：");
			JTextArea jta = new JTextArea();
	        jta.setLineWrap(true);//自动换行
	        jta.setEditable(false);//模仿JLabel 禁止编辑文字
	        jta.setBackground(new Color(238,238,238));//设置背景色和 窗体的背景色一样
	        //win 10 下, JFrame窗体背景RGB,测来是238,238,238
	        String s = "\n一、界面 \n          左上角显示地雷数目，右上角显示所花的时间。点击“Settings”"
	        		+ "可设置难度等级"
	        		+ "，以及自定义游戏区域大小。\n\n二、规则\n          在方格中点击左键挖雷，若显示数字，"
	        		+ "则数字表示该方格周围的一圈"
	        		+ "格子中有几个地雷；若挖到地雷，则游戏失败，可点击“Restart”重玩。若你认为某方格藏有地雷，"
	        		+ "可右键点击该方格插旗；若想撤回旗子，则再次点击右键即可。";
	        jta.setText(s);
	        tmpFrame.add(jta, BorderLayout.CENTER);
			tmpFrame.setTitle("Help");
			tmpFrame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-200,300,300);
//			tmpFrame.pack();
			tmpFrame.setVisible(true);
			tmpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	class showAbout implements ActionListener
	{
		JFrame tmpFrame;
		public void actionPerformed(ActionEvent e) {
			if(tmpFrame != null)
				tmpFrame.dispose();
			tmpFrame = new JFrame();
			JLabel label = new JLabel();
			label.setText("关于：");
			JTextArea jta = new JTextArea();
	        jta.setEditable(false);//模仿JLabel 禁止编辑文字
	        jta.setBackground(new Color(238,238,238));//设置背景色和 窗体的背景色一样
	        //win 10 下, JFrame窗体背景RGB,测来是238,238,238
	        String s = "由  木木正车干   于2019.1.8倾情制作";
	        jta.setText(s);
	        JPanel aboutPanel = new JPanel();
	        aboutPanel.setLayout(new BorderLayout());
	        aboutPanel.add(jta);
	        JLabel kong = new JLabel("	");// 防止文字顶格不好看。。。
	        tmpFrame.add(kong, BorderLayout.NORTH); 
	        tmpFrame.add(aboutPanel, BorderLayout.CENTER);
//			tmpFrame.add(label2, BorderLayout.CENTER);
			tmpFrame.setTitle("About");
			tmpFrame.setBounds(((Toolkit.getDefaultToolkit().getScreenSize().width)/2)-300, ((Toolkit.getDefaultToolkit().getScreenSize().height)/2)-200,300,300);
//			tmpFrame.pack();
			tmpFrame.setVisible(true);
			tmpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	}
	class Restart implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			frame.dispose();// 先关掉旧的frame再go，否则将开出多个frame
			go();
		}	
	}
	class Set implements ActionListener
	{
		JFrame setting;
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if( setting != null)
				setting.dispose();
			setting = new SettingsFrame();
			((SettingsFrame)setting).getEasy().addActionListener(new Set2(setting));
			((SettingsFrame)setting).getHard().addActionListener(new Set2(setting));
			((SettingsFrame)setting).getMid().addActionListener(new Set2(setting));
			((SettingsFrame)setting).getTrans().addActionListener(new Set2(setting));
		}
	}
	class Set2 implements ActionListener
	{
		JFrame setting;
		public Set2( JFrame obj)
		{
			setting = obj;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if( e.getSource() == ((SettingsFrame)setting).getEasy())
			{
				areaHeight = 10;
				areaWidth = 10;
				setting.dispose();
				frame.dispose();// 先关掉旧的frame再go，否则将开出多个frame
				go();
				frame.setTitle("扫雷 3.0  —— 简单模式");
			}
			else if( e.getSource() == ((SettingsFrame)setting).getMid())
			{
				areaHeight = 15;
				areaWidth = 15;
				setting.dispose();
				frame.dispose();// 先关掉旧的frame再go，否则将开出多个frame
				go();
				frame.setTitle("扫雷 3.0  —— 中级模式");
			}
			else if( e.getSource() == ((SettingsFrame)setting).getHard())
			{
				areaHeight = 20;
				areaWidth = 20;
				setting.dispose();
				frame.dispose();// 先关掉旧的frame再go，否则将开出多个frame
				go();
				frame.setTitle("扫雷 3.0  —— 困难模式");
			}
			else if( e.getSource() == ((SettingsFrame)setting).getTrans())
			{
				areaHeight = ((SettingsFrame)setting).getCustomH();
				areaWidth = ((SettingsFrame)setting).getCustomW();
				setting.dispose();
				frame.dispose();// 先关掉旧的frame再go，否则将开出多个frame
				go();
				frame.setTitle("扫雷 3.0  —— 自定义模式");
			}
		}	
	}
}
