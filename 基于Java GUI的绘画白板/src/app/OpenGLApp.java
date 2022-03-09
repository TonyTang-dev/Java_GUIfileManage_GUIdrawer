package app;

/**
 * 1������ Shape����ͼ����ĳ�����, ������һ�����󷽷�draw(),��������
	�����ж�ʵ����draw()����(ֻ��Ҫ����ʵ���������ƺ����������������׼
	�������)�����Ե����Ժ����Ե��޸ķ�����
	
	2��Graphic�������洢��ǰ���еĶ��󡢻������еĶ���ʵ���͸ı�ĳ��ʵ��
	������״������λ�����꣩�ȹ��ܣ����ڲ��д洢shape�༫������ʵ��������
	���ϣ�����shape���飩��add()���������shape�༫������ʵ������ڲ���
	�������У�draw()���ڵ����ڲ����������е�����ʵ����draw()������������
	��������������ı�ĳ��ʵ��������״�ķ�������
	
	3��OpenGLApp ������������Щ������ģ����� initGL()�����������ͳ�ʼ��
	Graphics��ʵ��������display()�������б�����Graphics��ʵ����ͼ�εģ�
	reshape()�������޸�Graphics��ʵ����ͼ�ε���������ֵ��Point ��������
	�� Shape�༯���е����Եģ�
	
	4������OpenGLApp����main��������main �����д���OpenGLApp��ʵ������
	����صĳ�Ա��������Graphics��shape �༰����������ڰ�graph�У� ����
	OpenGLApp�ŵ� app ���У�����OpenGLApp import ��graph�е��ࡣ
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
		setTitle("�����ͼ��-tony");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	//���ڴ�С���ɱ䣬�������
		this.add(panel0);
		panel0.setLayout(new BorderLayout());
		panel.setLayout(null);
		
		//������
//		JScrollPane jsp=new JScrollPane(panel);
//		jsp.getVerticalScrollBar().setUnitIncrement(15);
//		jsp.setLocation(0,0);
//		jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//�����Ƿ�һֱ�ɼ�
		panel0.add(panel,BorderLayout.CENTER);
		panel0.add(label2,BorderLayout.NORTH);
		panel0.add(label3,BorderLayout.SOUTH);
		layoutUI();
	}
	
	private void layoutUI() {
		label.setText("<html><body>---------------------<br>�������Ӧ��ͼ���ܱ�ţ�<br>1�����Ʋ�������������Ϣ<br>\t"
				+ "2�����Ʋ����������Ϣ<br>\t3�����Ʋ�������������Ϣ<br>\t4���Զ��廭��<br>5����ʾ�浵ͼ������<br>\t6���˳�����<br>---------------------<body></html>");
		//label.setVerticalAlignment(SwingConstants.CENTER);
		//label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(30,60,200,200);
		label.setFont(new Font("����",1,15));
		label2.setText("<html><body>��ͼ������ҳ<body></html>");
		label2.setVerticalAlignment(SwingConstants.CENTER);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		label3.setText("<html><body>�������������ȡ����<body></html>");
		label3.setVerticalAlignment(SwingConstants.CENTER);
		label3.setHorizontalAlignment(SwingConstants.CENTER);
		
		//�����ı���Ͱ�ť
		text.setText("��������������");
		text.setFont(new Font("����",1,13));
		text.addFocusListener(new myFocusListener());
		text.setBounds(150,260,100,25);
		
		JButton button_true=new JButton("ȷ��");//�տ�ʼ�Ұ������ó���ȫ�ֱ�������ô���ʱ��ÿ������layoutUI
											//�൱����Ϊ�������һ����������˻��ظ�������������һ�ε���������Ӧ
											//�����ÿ�ζ�����㶨���µİ�ť�ǾͲ��ᵼ�����������
		button_true.setBounds(250,260,60,25);
		JButton button_help=new JButton("����");
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
		
		//������ҳ��ʾ��
		JLabel homelabel=new JLabel();
		homelabel.setText("��ӭʹ��tony�������ͼ������������·��ı�����������ʹ�ñ�ϵͳ");
		homelabel.setFont(new Font("����",1,15));
		homelabel.setBounds(5,20,500,30);
		panel.add(homelabel);
		
		//����
		button_true.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				response();
			}
		});
	}
	
	//�̳�
	public void help() {
		JDialog dialogq=new JDialog();
		dialogq.setTitle("ʹ�ý̳�");
		dialogq.setAlwaysOnTop(true);
		dialogq.setBounds(screenwidth/2,screenheight/3,500,400);
		dialogq.setLayout(null);
		dialogq.setModal(true);
		JLabel labelfile1=new JLabel();
		labelfile1.setText("<html><body>�𾴵Ŀͻ����ã�<br><br>��ӭ��ʹ��tony���ͼ�װ壬�û�ͼ�װ���JavaΪ�ײ����Ա�д<br>����ʹ�õ�ʱ��"
				+ "����ͨ�������ťʵ�ֹ���ʹ�ã�<br>  1����ҳ���������ѡ��������������������������ȷ����ȡ����<br>  2��������ط����"
				+ "���Ƽ���ͼ����Ϣʱ�������������Ϣ��ȷ�����<br>  3�����½��з�����ҳ��ť��������������ҳ���ɷ�����ҳ"
				+ "<br>  4����������4�����Զ����ͼ���񣬿ɽ����Զ����ͼ������5��ʾ������Ϣ<br>  5��"
				+ "�Զ����ͼ�����Դ���ͼѡ���Լ��Զ����ͼ��һ��ֻ��ѡ��һ�֣�ѡ��������ģ��ͼ����ʧ<br>  6���Զ����ͼģ��ġ���ս��桱��ť�����ͼ�Σ��Զ����ͼ"
				+ "��ͨ���϶���겢�ͷ���ɻ�ͼ"
				+ "<br>7����ҳ�İ�����ť�ɻ�ȡ���̳�<br>  8����������δ���ƣ�Щ��ģ��δ���й�����䣬�����ڴ���������"
				+ "<br>  9����������ܴ���δ��������©����������ɼ�������٣�����֤��������Ϣ��ȡ��Ϊ<br>  10��������һ�н���Ȩ����������"
				);
		labelfile1.setBounds(5,5,480,380);
		labelfile1.setVerticalAlignment(SwingConstants.TOP);
		JButton btn=new JButton("�رս̳�");
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

	//�ı���ȫѡ
	public class myFocusListener extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			JTextField c=(JTextField)e.getSource();
			c.setSelectionStart(0);
			c.setSelectionEnd(c.getText().length());
		}
	}

	//��Ӧ����
	public void response() {
		if(text.getText().length()!=1) {
			showdialog("�������ָ������");
			return;
		}
		int command=Integer.parseInt(text.getText());
		if(command==6) {
			showdialog("��ȷ��Ҫ�˳�ϵͳ��",command);
			text.setText("��������������");
		}
		else {
			initGL(command);
			JButton btn=new JButton("������ҳ");
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
		dialog1.setTitle("����");
		dialog1.setAlwaysOnTop(true);
		dialog1.setBounds(screenwidth,screenheight,300,150);
		dialog1.setLayout(null);
		dialog1.setModal(true);
		labeltemp.setText(content);
		labeltemp.setBounds(80,15,120,25);
		JButton btn=new JButton("ȷ��");
		btn.setBounds(75,50,75,25);
		btn.setContentAreaFilled(false);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog1.dispose();
				System.exit(0);
			}
		});
		JButton btn1=new JButton("ȡ��");
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
	
	//���ط���
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
	
	protected void initGL(int a) {
		Graphic instance = new Graphic(panel,label2,label3,list1,list2,list3);
		instance.draw(a);
	}
	
	protected void display(ArrayList<Triangle> list1,ArrayList<Rectangle> list2,ArrayList<Cube> list3,JPanel panel) {
		/*
		 * �տ�ʼδ����panel����Ϊ��������ô˷���������ʹ�õ�panel�������panel,�����޷����������panel����������������������
		 */
		if (list1.size()==0&&list2.size()==0&&list3.size()==0) {
			showdialog("��δ������Ч��ͼ����");
			return;
		}
		else {
			panel.removeAll();
			panel.updateUI();
			
			JLabel label=new JLabel();
			label.setBounds(470,80,180,100);
			label.setFont(new Font("����",1,15));
			label.setBorder(BorderFactory.createLineBorder(Color.red));
			label.setText("<html><body>��ģ�����ı�����<br>����ʾ����ͼ����Ϣ<body></html>");
			
			JTextArea showall=new JTextArea();
			showall.setLineWrap(true);
			showall.setBounds(0,0,450,400);
			JScrollPane jsp=new JScrollPane(showall);
			jsp.getVerticalScrollBar().setUnitIncrement(15);
			jsp.setBounds(5,0,450,400);
			jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//�����Ƿ�һֱ�ɼ�
			showall.setText("------------------------------------------------------------\n");
			if (!(list1==null)) {
				int count=1;
				for(Triangle t:list1) {
					showall.append("��"+count+"��Triangle:\n��һ�������꣺("+t.a.x+" "+t.a.y+" "+t.a.z
							+")\n\t�ڶ��������꣺("+t.b.x+" "+t.b.y+" "+t.b.z
							+")\n\t�����������꣺("+t.c.x+" "+t.c.y+" "+t.c.z+")\n");
					count++;
				}
			}
			if (!(list2==null)) {
				int count=1;
				for(Rectangle rt:list2) {
					showall.append("��"+count+"��Rectangle:\n\tWidth="+rt.width+"\n\tHeight="
						+rt.height+"\n\t��һ�������꣺("+rt.a.x+" "+rt.a.y+" "+rt.a.z
						+")\n\t�ڶ��������꣺("+rt.b.x+" "+rt.b.y+" "+rt.b.z+")\n");
					count++;
				}
			}
			if (!(list3==null)) {
				int count=1;
				for(Cube cu:list3) {
					showall.append("��"+count+"��Cube:\n\tWidth="+cu.width+"\n\t��һ�������꣺("+cu.v1.x+" "+cu.v1.y+" "+cu.v1.z+")\n");
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
		ins1.setVisible(true);//��ʾ��ͼ���
	}
}
