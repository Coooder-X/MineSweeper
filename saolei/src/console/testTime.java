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

public class testTime extends JPanel{  /////�����û�ã���ѧϰ���ģ����ÿ�
    private JTextArea currentTimeLabel; //��ʾ��ǩ 
    private JButton startJButton;    //��ʼ��ť
    private JButton stopJButton;     //ֹͣ��ť
    private JButton resetJButton;    //��λ��ť
    private long countMis,countSec,countMin,countHour;//��ʱ����
    private DecimalFormat textFormat=new DecimalFormat("00");//��ʽ�����
    Timer timer=new Timer(10,new TestActionListener());//��ʱ��λ10ms

    public testTime() {  
        JPanel panel=new JPanel(new GridLayout(1,3,5,10)); //���񲼾�Ƕ�밴ť
        JPanel panel2=new JPanel(); 
        currentTimeLabel=new JTextArea(" "); 
        TestActionListener actionListener=new TestActionListener();
        currentTimeLabel.setForeground(Color.blue);
        currentTimeLabel.setFont(new Font("SAN_SERIF",Font.BOLD,50));  

        startJButton=new JButton("Start"); 
        stopJButton=new JButton("Stop"); 
        resetJButton=new JButton("Reset"); 
        //����JButton�������
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
    //��������¼�
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

            else{//��λ��λ
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
        JFrame frame=new JFrame("�����ʾ");  
        testTime stopwatch=new testTime();  
        frame.setSize(480,280);
        frame.getContentPane().add(stopwatch);  
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
        frame.setVisible(true);  
    }  
}  
//--------------------- 
//���ߣ�Jiafu_Liu 
//��Դ��CSDN 
//ԭ�ģ�https://blog.csdn.net/Jiafu_Liu/article/details/81390561 
//��Ȩ����������Ϊ����ԭ�����£�ת���븽�ϲ������ӣ�
