package Area;
import java.awt.GridLayout;

import javax.swing.*;

//import console.controler.monitor;

public class Area extends JFrame{
//	JFrame area;
	private int width;
	private int height;
	private ACell cellField[][];
	public Area(int h, int w)
	{
		width = w;
		height = h;
		cellField = new ACell[height][width];
	}
	public int getW() {
		return width;
	}
	public int getH() {
		return height;
	}
	public void setW( int w ) {
		height = w;
	}
	public void setH( int h ) {
		height = h;
	}
	public ACell place( int r, int c, ACell obj)
	{
		ACell tmp = cellField[r][c];
		cellField[r][c] = obj;//////////////???????
		return tmp;
	}
	public ACell getCell( int r, int c)
	{
		return cellField[r][c];
	}
	public int CountAndSet( int r, int c )
	{
		int count = 0;
		int dx[] = { -1, -1, -1, 0, 1, 1, 1, 0};
		int dy[] = { -1, 0, 1, 1, 1, 0, -1, -1};
		for( int i = 0; i < 8; ++i)
		{
			int x = r + dx[i], y = c + dy[i];
			if( x < 0 || x >= height || y < 0 || y >= width)
				continue;
			if(cellField[x][y].getMine() == true)
				count++;
		}
		return count;
	}
//	public 
	public static void main(String[] args) {
//		area = new JFrame("The Field");
		JFrame a = new Area( 3, 6);
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(((Area)a).getH(), ((Area)a).getW(), 4, 4));
		System.out.println(((Area)a).getH());
		System.out.println(((Area)a).getW());
		for( int i = 0; i < ((Area)a).getH(); ++i)
		{
			for( int j = 0; j < ((Area)a).getW(); ++j)
			{
				((Area)a).place(i, j, new ACell());//逐个创建对象
				panel.add(((Area)a).getCell(i, j).getButton());//将cell中的button加入panel 
				((Area)a).getCell(i, j).getButton().setBorder(BorderFactory.createRaisedBevelBorder());//设置凸起来的按钮
//				
			}
		}	
		a.add(panel);
		a.setVisible(true);
		a.pack();
		a.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
	
}
