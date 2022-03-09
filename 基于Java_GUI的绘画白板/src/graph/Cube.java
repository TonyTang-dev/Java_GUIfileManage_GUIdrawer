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

public class Cube extends Shape{
	public int screenwidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
	public int screenheight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;
	
	public double width;
	public Point v1 = new Point(0,0,0);
	
	public Graphics pen;
	
	public Cube draw(JPanel panel) {
		JLabel label=new JLabel();
		label.setBounds(350,60,280,200);
		label.setFont(new Font("����",1,15));
		label.setBorder(BorderFactory.createLineBorder(Color.black));
		JTextField textl=new JTextField("4");
		textl.setBounds(410,140,50,20);
		JTextField textx=new JTextField("1");
		textl.addFocusListener(new myFocusListener());
		textx.setBounds(410,165,50,20);
		JTextField texty=new JTextField("1");
		textx.addFocusListener(new myFocusListener());
		texty.setBounds(410,190,50,20);
		JTextField textz=new JTextField("1");
		texty.addFocusListener(new myFocusListener());
		textz.setBounds(410,215,50,20);
		textz.addFocusListener(new myFocusListener());
		label.setText("<html><body>�������ⳤ������һ������ Ĭ��ֵΪ1<br><br>�ⳤ��<br><br>����x:<br>����y:<br>����z:<body></html>");
		
		//���ֻ���
		JLabel labelline=new JLabel();
		JLabel labelline2=new JLabel();
		labelline2.setText("��ͼ");
		labelline2.setBounds(150,10,75,20);
		labelline.setBounds(5,5,320,400);
		labelline.setBorder(BorderFactory.createLineBorder(Color.red));
		
		JButton button=new JButton("ȷ��");
		button.setBounds(500,230,75,25);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				width=Double.parseDouble(textl.getText());
				v1.x=Double.parseDouble(textx.getText());
				v1.y=Double.parseDouble(texty.getText());
				v1.z=Double.parseDouble(textz.getText());
				showdialog("������Ϣ�ɹ�");
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
	//��ʾ��������
	public void showdialog(String content) {
		JDialog dialog2=new JDialog();
		JLabel labeltemp=new JLabel();
		dialog2.setTitle("����");
		dialog2.setAlwaysOnTop(true);
		dialog2.setBounds(screenwidth,screenheight,300,100);
		dialog2.setLayout(null);
		dialog2.setModal(true);
		labeltemp.setText(content);
		labeltemp.setBounds(50,15,120,25);
		JButton btn=new JButton("ȷ��");
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
			pen.drawRect(80, 80, 200, 200);
			pen.drawRect(40, 140, 200, 200);
			pen.drawLine(80, 80, 40, 140);
			pen.drawLine(280, 80, 240, 140);
			pen.drawLine(40, 340, 80, 280);
			pen.drawLine(240, 340, 280, 280);
		}
	}
}
