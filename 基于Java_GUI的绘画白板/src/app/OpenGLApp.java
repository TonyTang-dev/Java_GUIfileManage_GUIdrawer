package app;

/**
 * 1、其中 Shape类是图形类的抽象父类, 它包含一个抽象方法draw(),在他的派
	生类中都实现了draw()方法(只需要将本实例的类名称和坐标属性输出到标准
	输出即可)、各自的属性和属性的修改方法；
	
	2、Graphic是用来存储当前已有的对象、绘制已有的对象实例和改变某个实例
	对象形状（例如位置坐标）等功能，其内部有存储shape类极其子类实例的容器
	集合（例如shape数组），add()方法负责把shape类极其子类实例添加内部容
	器集合中，draw()用于调用内部容器集合中的所有实例的draw()方法，还可以
	添加其它方法（改变某个实例对象形状的方法）；
	
	3、OpenGLApp 是用来测试这些集合类的，方法 initGL()是用来创建和初始化
	Graphics类实例，方法display()绘制所有保存在Graphics类实例中图形的，
	reshape()是用来修改Graphics类实例中图形的区域属性值；Point 类用来存
	储 Shape类集合中点属性的；
	
	4、在类OpenGLApp定义main函数，在main 函数中创建OpenGLApp的实例并调
	用相关的成员方法；把Graphics和shape 类及其派生类放在包graph中， 把类
	OpenGLApp放到 app 包中，并在OpenGLApp import 包graph中的类。
 */
import graph.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class OpenGLApp extends JFrame{
	private static final long serialVersionUID = 1L;
	protected JPanel panel0=new JPanel();
	public JPanel panel=new JPanel();
	public JLabel label=new JLabel();
	public JLabel label2=new JLabel();
	public JLabel label3=new JLabel();
	JTextField text=new JTextField();
	
	protected ArrayList<Triangle> list1 = new ArrayList<Triangle>();
	protected ArrayList<Rectangle> list2 = new ArrayList<Rectangle>();
	protected ArrayList<Cube> list3 = new ArrayList<Cube>();
	
	public int screenwidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
	public int screenheight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;
	
	public OpenGLApp() {
		this.setSize(700,500);
		setLocation(200,100);
		setTitle("网络绘图板-tony");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	//窗口大小不可变，不可最大化
		this.add(panel0);
		panel0.setLayout(new BorderLayout());
		panel.setLayout(null);
		
		//滚动条
//		JScrollPane jsp=new JScrollPane(panel);
//		jsp.getVerticalScrollBar().setUnitIncrement(15);
//		jsp.setLocation(0,0);
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置是否一直可见
		panel0.add(panel,BorderLayout.CENTER);
		panel0.add(label2,BorderLayout.NORTH);
		panel0.add(label3,BorderLayout.SOUTH);
		layoutUI();
	}
	
	private void layoutUI() {
		label.setText("<html><body>---------------------<br>请输入对应画图功能编号：<br>1、绘制并加入三角形信息<br>\t"
				+ "2、绘制并加入矩形信息<br>\t3、绘制并加入立方体信息<br>\t4、自定义画板<br>5、显示存档图形数据<br>\t6、退出程序<br>---------------------<body></html>");
		//label.setVerticalAlignment(SwingConstants.CENTER);
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(30,60,200,200);
		label.setFont(new Font("宋体",1,15));
		label2.setText("<html><body>绘图程序首页<body></html>");
		label2.setVerticalAlignment(SwingConstants.CENTER);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setText("<html><body>输入您的命令获取服务<body></html>");
		label3.setVerticalAlignment(SwingConstants.CENTER);
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		
		//设置文本框和按钮
		text.setText("请输入您的命令");
		text.setFont(new Font("宋体",1,13));
		text.addFocusListener(new myFocusListener());
		text.setBounds(150,260,100,25);
		
		JButton button_true=new JButton("确定");//刚开始我把它设置成了全局变量，那么这个时候每次运行layoutUI
											//相当于又为它添加了一个监听，因此会重复监听它，导致一次点击，多次响应
											//而如果每次都冲刺你定义新的按钮那就不会导致这样的情况
		button_true.setBounds(250,260,60,25);
		JButton button_help=new JButton("帮助");
		button_help.setBounds(330,260,60,25);
		button_help.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				help();
			}
		});
		
		panel.add(label);
		panel.add(text);
		panel.add(button_true);
		panel.add(button_help);
		
		//设置首页提示语
		JLabel homelabel=new JLabel();
		homelabel.setText("欢迎使用tony版网络绘图软件，您可在下方文本框输入命令使用本系统");
		homelabel.setFont(new Font("宋体",1,15));
		homelabel.setBounds(5,20,500,30);
		panel.add(homelabel);
		
		//监听
		button_true.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response();
			}
		});
	}
	
	//教程
	public void help() {
		JDialog dialogq=new JDialog();
		dialogq.setTitle("使用教程");
		dialogq.setAlwaysOnTop(true);
		dialogq.setBounds(screenwidth/2,screenheight/3,500,400);
		dialogq.setLayout(null);
		dialogq.setModal(true);
		JLabel labelfile1=new JLabel();
		labelfile1.setText("<html><body>尊敬的客户您好：<br><br>欢迎您使用tony版绘图白板，该绘图白板以Java为底层语言编写<br>在您使用的时候"
				+ "，可通过点击按钮实现功能使用：<br>  1、首页有相关命令选项和命令输入框，您可输入命令点击确定获取服务<br>  2、进入相关服务后"
				+ "绘制加入图形信息时可以输入相关信息并确定添加<br>  3、右下角有返回首页按钮，单击“返回首页”可返回首页"
				+ "<br>  4、输入命令4进入自定义绘图服务，可进行自定义绘图，命令5显示所有信息<br>  5、"
				+ "自定义绘图里有自带绘图选项以及自定义绘图，一次只能选择一种，选定后其他模块图形消失<br>  6、自定义绘图模块的“清空界面”按钮可清空图形，自定义绘图"
				+ "是通过拖动鼠标并释放完成绘图"
				+ "<br>7、首页的帮助按钮可获取本教程<br>  8、本程序尚未完善，些许模块未进行功能填充，敬请期待后续更新"
				+ "<br>  9、本程序可能存在未处理的软件漏洞，可能造成计算机卡顿，但保证不存在信息盗取行为<br>  10、本程序一切解释权归作者所有"
				);
		labelfile1.setBounds(5,5,480,380);
		labelfile1.setVerticalAlignment(SwingConstants.TOP);
		JButton btn=new JButton("关闭教程");
		btn.setBounds(300,300,100,25);
		btn.setContentAreaFilled(true);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialogq.dispose();
			}
		});
		dialogq.add(btn);
		dialogq.add(labelfile1);
		dialogq.setVisible(true);
	}

	//文本框全选
	public class myFocusListener extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			JTextField c=(JTextField)e.getSource();
			c.setSelectionStart(0);
			c.setSelectionEnd(c.getText().length());
		}
	}

	//相应命令
	public void response() {
		if(text.getText().length()!=1) {
			showdialog("您输入的指令有误");
			return;
		}
		int command=Integer.parseInt(text.getText());
		if(command==6) {
			showdialog("您确定要退出系统吗？",command);
			text.setText("请输入您的命令");
		}
		else {
			initGL(command);
			JButton btn=new JButton("返回首页");
			btn.setBounds(585,387,100,25);
			panel.add(btn);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					panel.removeAll();
					panel.updateUI();
					layoutUI();
				}
			});
		}
	}
	
	public void showdialog(String content,int command) {
		JDialog dialog1=new JDialog();
		JLabel labeltemp=new JLabel();
		dialog1.setTitle("详情");
		dialog1.setAlwaysOnTop(true);
		dialog1.setBounds(screenwidth,screenheight,300,150);
		dialog1.setLayout(null);
		dialog1.setModal(true);
		labeltemp.setText(content);
		labeltemp.setBounds(80,15,120,25);
		JButton btn=new JButton("确定");
		btn.setBounds(75,50,75,25);
		btn.setContentAreaFilled(false);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog1.dispose();
				System.exit(0);
			}
		});
		JButton btn1=new JButton("取消");
		btn1.setBounds(160,50,75,25);
		btn1.setContentAreaFilled(false);
		btn1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog1.dispose();
			}
		});
		dialog1.add(btn);
		dialog1.add(btn1);
		dialog1.add(labeltemp);
		dialog1.setVisible(true);
	}
	
	//重载方法
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
	
	protected void initGL(int a) {
		Graphic instance = new Graphic(panel,label2,label3,list1,list2,list3);
		instance.draw(a);
	}
	
	protected void display(ArrayList<Triangle> list1,ArrayList<Rectangle> list2,ArrayList<Cube> list3,JPanel panel) {
		/*
		 * 刚开始未传入panel，因为是子类调用此方法，所以使用的panel是子类的panel,导致无法更改自身的panel，传入参数后可以正常更改
		 */
		if (list1.size()==0&&list2.size()==0&&list3.size()==0) {
			showdialog("您未进行有效绘图操作");
			return;
		}
		else {
			panel.removeAll();
			panel.updateUI();
			
			JLabel label=new JLabel();
			label.setBounds(470,80,180,100);
			label.setFont(new Font("宋体",1,15));
			label.setBorder(BorderFactory.createLineBorder(Color.red));
			label.setText("<html><body>本模块左方文本框内<br>可显示所有图形信息<body></html>");
			
			JTextArea showall=new JTextArea();
			showall.setLineWrap(true);
			showall.setBounds(0,0,450,400);
			JScrollPane jsp=new JScrollPane(showall);
			jsp.getVerticalScrollBar().setUnitIncrement(15);
			jsp.setBounds(5,0,450,400);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置是否一直可见
			showall.setText("------------------------------------------------------------\n");
			if (!(list1==null)) {
				int count=1;
				for(Triangle t:list1) {
					showall.append("第"+count+"个Triangle:\n第一个点坐标：("+t.a.x+" "+t.a.y+" "+t.a.z
							+")\n\t第二个我坐标：("+t.b.x+" "+t.b.y+" "+t.b.z
							+")\n\t第三个点坐标：("+t.c.x+" "+t.c.y+" "+t.c.z+")\n");
					count++;
				}
			}
			if (!(list2==null)) {
				int count=1;
				for(Rectangle rt:list2) {
					showall.append("第"+count+"个Rectangle:\n\tWidth="+rt.width+"\n\tHeight="
						+rt.height+"\n\t第一个点坐标：("+rt.a.x+" "+rt.a.y+" "+rt.a.z
						+")\n\t第二个我坐标：("+rt.b.x+" "+rt.b.y+" "+rt.b.z+")\n");
					count++;
				}
			}
			if (!(list3==null)) {
				int count=1;
				for(Cube cu:list3) {
					showall.append("第"+count+"个Cube:\n\tWidth="+cu.width+"\n\t第一个点坐标：("+cu.v1.x+" "+cu.v1.y+" "+cu.v1.z+")\n");
					count++;
				}
			}
			showall.append("-------------------------------------------------------------");
			panel.add(label);
			panel.add(jsp);
			panel.updateUI();
		}
	}
	
	protected void reshape() {
		
	}
	
	public static void main(String args[]) {
		OpenGLApp ins1 = new OpenGLApp();
		ins1.setVisible(true);//显示绘图面板
	}
}
