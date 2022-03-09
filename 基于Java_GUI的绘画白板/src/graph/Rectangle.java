package graph;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Rectangle extends Shape {
	public double width;
	public double height;
	public Point a = new Point(0,0,0);
	public Point b = new Point(0,0,0);
	public int screenwidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
	public int screenheight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;
	
	public Graphics pen;

	public Rectangle draw(JPanel panel) {
		JLabel label=new JLabel();
		label.setBounds(350,60,280,200);
		label.setFont(new Font("宋体",1,15));
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		JTextField textl=new JTextField("1");
		textl.setBounds(420,152,50,15);
		JTextField textx=new JTextField("1");
		textl.addFocusListener(new myFocusListener());
		textx.setBounds(420,171,50,15);
		JTextField texty=new JTextField("1,1,1");
		textx.addFocusListener(new myFocusListener());
		texty.setBounds(420,190,50,15);
		JTextField textz=new JTextField("1,1,1");
		texty.addFocusListener(new myFocusListener());
		textz.setBounds(420,210,50,15);
		textz.addFocusListener(new myFocusListener());
		label.setText("<html><body>请输入长宽和其中两点坐标<br>坐标值以逗号分隔默认为1<br><br>长length：<br>宽width:<br>坐标点1:<br>坐标点2:<body></html>");
		
		//布局画板
		JLabel labelline=new JLabel();
		JLabel labelline2=new JLabel();
		labelline2.setText("样图");
		labelline2.setBounds(150,10,75,20);
		labelline.setBounds(5,5,320,400);
		labelline.setBorder(BorderFactory.createLineBorder(Color.red));
		
		JButton button=new JButton("确定");
		button.setBounds(500,230,75,25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				height=Double.parseDouble(textl.getText());
				width=Double.parseDouble(textx.getText());
				a.x=Double.parseDouble(texty.getText().split(",")[0]);
				a.y=Double.parseDouble(texty.getText().split(",")[1]);
				a.z=Double.parseDouble(texty.getText().split(",")[2]);
				b.x=Double.parseDouble(texty.getText().split(",")[0]);
				b.y=Double.parseDouble(texty.getText().split(",")[1]);
				b.z=Double.parseDouble(texty.getText().split(",")[2]);
				showdialog("加入信息成功");
			}
		});
		
		panel.add(labelline2);
		panel.add(labelline);
		panel.add(label);
		panel.add(textl);
		panel.add(textx);
		panel.add(texty);
		panel.add(textz);
		panel.add(button);
		panel.updateUI();
		drawpanel mydraw=new drawpanel();
		mydraw.setBounds(10,10,320,400);
		panel.add(mydraw);
		return this;
	}
	
	public class myFocusListener extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			JTextField c=(JTextField)e.getSource();
			c.setSelectionStart(0);
			c.setSelectionEnd(c.getText().length());
		}
	}
	//显示操作详情
	public void showdialog(String content) {
		JDialog dialog2=new JDialog();
		JLabel labeltemp=new JLabel();
		dialog2.setTitle("详情");
		dialog2.setAlwaysOnTop(true);
		dialog2.setBounds(screenwidth,screenheight,300,100);
		dialog2.setLayout(null);
		dialog2.setModal(true);
		labeltemp.setText(content);
		labeltemp.setBounds(50,15,120,25);
		JButton btn=new JButton("确定");
		btn.setBounds(175,15,75,25);
		btn.setContentAreaFilled(false);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog2.dispose();
			}
		});
		dialog2.add(btn);
		dialog2.add(labeltemp);
		dialog2.setVisible(true);
	}
	public class drawpanel extends JPanel{
		private static final long serialVersionUID = 1L;

		public void paint(Graphics pen) {
			super.paint(pen);pen.setColor(Color.red);
			pen.drawRect(30, 80, 250, 200);
		}
	}
}
