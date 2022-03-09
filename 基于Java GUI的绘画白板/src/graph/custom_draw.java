package graph;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class custom_draw {
	public int screenwidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
	public int screenheight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;
	protected static Graphics pen;
	public int x1,x2,y1,y2;
	public int flagall=-1;
	public int index=0;
	public static String color[]= {"红色","绿色","蓝色","黄色","橙色","白色"};
	public static Color colorenglish[]= {Color.red,Color.green,Color.blue,Color.yellow,Color.orange,Color.white};
	
	public custom_draw draw(JPanel panel) {
		JLabel label=new JLabel();
		label.setBounds(5,5,670,380);
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JButton button1=new JButton("三角形");
		JButton button2=new JButton("矩形");
		JButton button3=new JButton("立方体");
		JButton button4=new JButton("自定义画板");
		JButton button5=new JButton("清空画板");
		JButton buttoncolor=new JButton("更换颜色");
		button1.setBounds(20,387,100,25);
		button2.setBounds(130,387,100,25);
		button3.setBounds(250,387,100,25);
		button4.setBounds(370,387,100,25);
		button5.setBounds(480,387,100,25);
		buttoncolor.setBounds(480,355,100,25);
		
		panel.add(button1);
		panel.add(button2);
		panel.add(button3);
		panel.add(button4);
		panel.add(button5);
		panel.add(label);
		label.add(buttoncolor);
		add_listener(button1,button2,button3,button4,button5,buttoncolor,label);
		return this;
	}
	
	public void clearlabel(JLabel label) {
		label.removeAll();
		label.updateUI();
		JButton buttoncolor=new JButton("更换颜色");
		buttoncolor.setBounds(480,355,100,25);
		buttoncolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttoncolor.setText(color[index]);
				index++;
				if(index==5) {
					index=0;
				}
			}
		});
		label.add(buttoncolor);
	}
	
	public void add_listener(JButton button1,JButton button2,JButton button3,JButton button4,
			JButton button5,JButton buttoncolor,JLabel label) {
		buttoncolor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttoncolor.setText(color[index]);
				index++;
				if(index==5) {
					index=0;
				}
			}
		});
		
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearlabel(label);
				flagall=-1;
			}
		});
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearlabel(label);
				flagall=4;
				label.addMouseListener(new MouseAdapter() {
					public void mousePressed(MouseEvent e) {
						//鼠标按下的时候，分别获取起点的横纵坐标
							x1=e.getX();
							y1=e.getY();
					}
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						//鼠标松开的时候，分别获取终点的横纵坐标
						x2 = e.getX();
						y2 = e.getY();
						drawlabel ins=new drawlabel(x1,y1,x2,y2,0);
						ins.setBounds(5,5,670,380);
						label.add(ins);
						label.updateUI();
					}
				});
			}
		});
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearlabel(label);
				flagall=1;
				label.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//鼠标按下的时候，分别获取起点的横纵坐标
							x1=e.getX();
							y1=e.getY();
							drawlabel ins=new drawlabel(x1,y1,1);
							ins.setBounds(5,5,670,380);
							label.add(ins);
							label.updateUI();
					}
				});
			}
		});
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearlabel(label);
				flagall=2;
				label.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//鼠标按下的时候，分别获取起点的横纵坐标
							x1=e.getX();
							y1=e.getY();
							drawlabel ins=new drawlabel(x1,y1,2);
							ins.setBounds(5,5,670,380);
							label.add(ins);
							label.updateUI();
					}
				});
			}
		});
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearlabel(label);
				flagall=3;
				label.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						//鼠标按下的时候，分别获取起点的横纵坐标
							x1=e.getX();
							y1=e.getY();
							drawlabel ins=new drawlabel(x1,y1,3);
							ins.setBounds(5,5,670,380);
							label.add(ins);
							label.updateUI();
					}
				});
			}
		});
	}
	public class drawlabel extends JLabel{
		private static final long serialVersionUID = 1L;
		int xt,yt,xt2,yt2,flag;
		Color color;
		drawlabel(int x1,int y1,int x2,int y2,int flag1){
			xt=x1;
			yt=y1;
			xt2=x2;
			yt2=y2;
			flag=flag1;
		}
		drawlabel(int x1,int y1,int flag1){
			xt=x1;
			yt=y1;
			flag=flag1;
		}
		drawlabel(Color color){
			this.color=color;
		}
		public void paint(Graphics pen) {
			super.paint(pen);
			pen.setColor(colorenglish[index]);
			if(flag==0&&flagall==4) {
				pen.drawLine(xt, yt, xt2, yt2);//通过drawLine方法在两个点之间连一条直线(pen是画笔)
			}
			else if(flag==1&&flagall==1) {
				pen.drawLine(xt,yt,xt+80,yt+268);
				pen.drawLine(xt-80,yt+268,xt+80,yt+268);
				pen.drawLine(xt-80,yt+268,xt,yt);
			}
			else if(flag==2&&flagall==2) {
				pen.drawRect(xt, yt, 250, 200);
			}
			else if(flag==3&flagall==3) {
				pen.drawRect(xt, yt, 200, 200);
				pen.drawRect(xt-40, yt+60, 200, 200);
				pen.drawLine(xt, yt, xt-40, yt+60);
				pen.drawLine(200+xt, yt, 160+xt, yt+60);
				pen.drawLine(xt-40, 260+yt, xt, 200+yt);
				pen.drawLine(160+xt, 260+yt, xt+200, 200+yt);
			}
		}
	}
}
