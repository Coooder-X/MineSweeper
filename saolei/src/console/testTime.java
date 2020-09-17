package console;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout; 
import java.util.TimerTask;  
import java.text.DecimalFormat;
import java.awt.Color; 
import java.awt.GridLayout; 
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;

public class testTime extends JPanel{  /////这个类没用，是学习秒表的，不用看
    private JTextArea currentTimeLabel; //显示标签 
    private JButton startJButton;    //开始按钮
    private JButton stopJButton;     //停止按钮
    private JButton resetJButton;    //复位按钮
    private long countMis,countSec,countMin,countHour;//计时变量
    private DecimalFormat textFormat=new DecimalFormat("00");//格式化输出
    Timer timer=new Timer(10,new TestActionListener());//计时单位10ms

    public testTime() {  
        JPanel panel=new JPanel(new GridLayout(1,3,5,10)); //网格布局嵌入按钮
        JPanel panel2=new JPanel(); 
        currentTimeLabel=new JTextArea(" "); 
        TestActionListener actionListener=new TestActionListener();
        currentTimeLabel.setForeground(Color.blue);
        currentTimeLabel.setFont(new Font("SAN_SERIF",Font.BOLD,50));  

        startJButton=new JButton("Start"); 
        stopJButton=new JButton("Stop"); 
        resetJButton=new JButton("Reset"); 
        //设置JButton相关属性
        startJButton.setBorder(BorderFactory.createRaisedBevelBorder());
        stopJButton.setBorder(BorderFactory.createRaisedBevelBorder());
        resetJButton.setBorder(BorderFactory.createRaisedBevelBorder());

        startJButton.setFont(new java.awt.Font("Times New Roman", 1, 30));
        stopJButton.setFont(new java.awt.Font("Times New Roman", 1, 30));
        resetJButton.setFont(new java.awt.Font("Times New Roman", 1, 30));

        stopJButton.setBackground(Color.cyan); 
        startJButton.setBackground(Color.red);
        resetJButton.setBackground(Color.orange);

        stopJButton.addActionListener(actionListener);  
        startJButton.addActionListener(actionListener);  
        resetJButton.addActionListener(actionListener);  

        this.setLayout(new BorderLayout());  

        panel2.setBackground(Color.gray);
        panel2.setBorder(BorderFactory.createLoweredBevelBorder());  
        panel2.add(currentTimeLabel); 
        panel.add(stopJButton);  
        panel.add(startJButton);  
        panel.add(resetJButton); 

        this.add(panel2,BorderLayout.NORTH); 
        this.add(panel,BorderLayout.CENTER);

    }  
    //处理相关事件
    class TestActionListener implements ActionListener{   
        public void actionPerformed(ActionEvent e){ 
            if(e.getSource()==startJButton){
                timer.start();
                startJButton.setEnabled(false);
            }  

            else if(e.getSource()==stopJButton){
                timer.stop();
                startJButton.setEnabled(true);
            }

            else if(e.getSource()==resetJButton){ 
                countHour=0;
                countMin=0;
                countSec=0;
                countMis=0;
            }

            else{//满位后复位
                countMis++;
                if(countMis>=99){
                    countSec++;
                    countMis=0;
                    if(countSec>=59){
                        countMin++;
                        countSec=0;
                        if(countMin>=59){
                            countHour++;
                            countMin=0;
                        }
                    }
                }

            }

        }
    }


    public void paintComponent(Graphics g){
        super.paintComponent(g); 
        currentTimeLabel.setText(textFormat.format(countHour)+":"+textFormat.format(countMin)+
        ":"+textFormat.format(countSec)+":"+textFormat.format(countMis));
        repaint();  
    }


    public static void main(String args[]){  
        JFrame frame=new JFrame("秒表演示");  
        testTime stopwatch=new testTime();  
        frame.setSize(480,280);
        frame.getContentPane().add(stopwatch);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
    }  
}  
//--------------------- 
//作者：Jiafu_Liu 
//来源：CSDN 
//原文：https://blog.csdn.net/Jiafu_Liu/article/details/81390561 
//版权声明：本文为博主原创文章，转载请附上博文链接！
