package Area;

import javax.swing.*;

public class ACell extends JFrame{
	private JButton button = new JButton();
//	private int r;
//	private int c;//��û����Cell�ڲ���������ı�Ҫ��
	private int value;
	private  boolean Mine;
	private boolean Clicked;
	
	public ACell()
	{
		value = 0;
		Mine = false;
		Clicked = false;
		button.setText("       ");
//		button.addMouseListener(this);
	}
	public void setValue( int v)
	{
		value = v;
	}
	public void setMine( boolean f)
	{
		Mine = f;
	}
	public int getValue()
	{
		return value;
	}
//	public int getR() {
//		return r;
//	}
//	public int getC() {
//		return c;
//	}
	public boolean getMine()
	{
		return Mine;
	}
	public JButton getButton()
	{
		return button;
	}
	public void setButton( String s)
	{
		button.setText(s);
	}
	@SuppressWarnings("deprecation")//////
	public String getButTxt()
	{
		return button.getLabel();///
	}
	public boolean getClicked()
	{
		return Clicked;
	}
	public void setClicked( boolean f)
	{
		Clicked = f;
	}
}
