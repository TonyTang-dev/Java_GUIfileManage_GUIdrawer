package guifilemanager;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class manager extends JFrame{
	/**
	 * �������������ļ��������������
	 */
	private static final long serialVersionUID = 2642996604805081618L;
	/*�������л�ID����ͬ�������֤�����ڰ汾����ά����ͬ�汾�ļ������Լ������������ʱ����
	*ͨ�����л�����ȷ�����serialVersionUID����֤�汾��һ����
	*/
	private static int flagfinal=1;
	
	private JPanel panel1=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JPanel panel4=new JPanel();
	
	protected JPanel temppanel;
	private JTextArea text1;
	private JTextArea text2;
	private JTextArea textcreatefile=new JTextArea();
	private JLabel label;
	public JLabel labelfile=new JLabel();
	private String lastpath="";
	private String lastpage[];
	private JButton tempbutton[]= {new JButton("temp"),null,null,null,null};
	private JButton createfilebutton[]= {null,null};
	private JButton buttoncreatefile;
	private String templast="";
	private String createDir="";
	private String menulist[]= {"ɾ�����ļ����ļ���","ѹ�����ļ����ļ���","���Ƹ��ļ����ļ���","���ܸ�Ŀ¼���ļ���","��ѹ����ܸ��ļ�"};
	private JDialog dialog;
	public int screenwidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
	public int screenheight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;

	public manager() {
		setSize(700,500);
		setLocation(200,100);
		setTitle("�ļ�������-tony");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	//���ڴ�С���ɱ䣬�������
		
		this.panel1.setBackground(Color.orange);
		//this.panel1.setOpaque(false);
		this.panel2.setBackground(Color.white);
		this.panel4.setBackground(Color.white);
		
		this.setLayout(null);
		
		this.panel1.setLocation(0,0);
		this.panel2.setLocation(0,30);
		this.panel3.setLocation(0,55);
		this.panel4.setLocation(0,82);
		//this.panel5.setLocation(170,95);
		
		this.panel1.setSize(700,30);
		this.panel2.setSize(700,25);
		this.panel3.setSize(700,27);
		this.panel4.setSize(700,418);
		
		//����Ƕ��
		this.layoutpanel1();
		this.layoutpanel2();
		this.layoutpanel3();
		this.layoutpanel4();
		
		this.add(panel1);
		this.add(panel2);
		this.add(panel3);
		this.add(panel4);
	}
	
	private void layoutpanel1() {
		JButton button1=new JButton("<=");
		JButton button2=new JButton("=>");
		text1=new JTextArea(1,100);
		JButton button3=new JButton("����");
		text2=new JTextArea(1,50);
		text2.setText("��������Ҫ�������ļ���Ŀ¼");
		JButton button4=new JButton("����");
		
		//����ı�����ȫѡ�¼�
		text1.addFocusListener(new myFocusListener());
		text2.addFocusListener(new myFocusListener());
		textcreatefile.addFocusListener(new myFocusListener());
		
		//Ϊ������ť����¼�
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog=new JDialog();
				dialog.setTitle("����");
				dialog.setAlwaysOnTop(true);
				dialog.setBounds(screenwidth,screenheight,300,200);
				dialog.setLayout(null);
				dialog.setModal(true);
				//dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);//�������������ȷ�ر�dialog�Ի���
				labelfile.setText("��δд���ģ��");
				labelfile.setBounds(75,50,150,60);
				dialog.add(labelfile);
				dialog.setVisible(true);
			}
		});
		//��ӷ�����һ��Ŀ¼��
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text1.getText().split("\\"+File.separator).length!=1
						&&!text1.getText().equals("��ҳ")) {
					lastpage=text1.getText().split("\\"+File.separator);
					for (int i=0;i<lastpage.length-1;i++) {
						lastpath=lastpath+lastpage[i]+File.separator;
					}
					temppanel.removeAll();
					temppanel.updateUI();
					templast=text1.getText();
					Enter_Dir(temppanel,lastpath);
					lastpath="";
				}
				else if(text1.getText().split("\\"+File.separator).length==1
						&&!text1.getText().equals("��ҳ")) {
					templast=text1.getText();
					text1.setText("��ҳ");
					removecomponent(temppanel);
					Showhomepage(temppanel,temppanel,50,30,190,30,1,1);
				}
			}
		});
		
		//������һҳĿ¼
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(templast!="") {
					text1.setText(templast);
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,templast);
					templast="";
				}
			}
		});
		
		this.panel1.setLayout(null);
		button1.setLocation(1,0);
		button1.setSize(50,30);
		button2.setLocation(52,0);
		button2.setSize(50,30);
		text1.setLocation(110,3);
		text1.setSize(200,25);
		button3.setSize(60,30);
		button3.setLocation(310,0);
		text2.setSize(200,25);
		text2.setLocation(380,3);
		button4.setSize(60,30);
		button4.setLocation(580,0);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		this.panel1.add(button1);
		this.panel1.add(button2);
		this.panel1.add(text1);
		this.panel1.add(button3);
		this.panel1.add(text2);
		this.panel1.add(button4);
	}
	
	//�ı���ȫѡ
	private class myFocusListener extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			JTextArea c=(JTextArea)e.getSource();
			c.setSelectionStart(0);
			c.setSelectionEnd(c.getText().length());
		}
	}
	
	//������̳�
	public void help() {
		if(flagfinal==1) {
			JDialog dialogq=new JDialog();
			dialogq.setTitle("ʹ�ý̳�");
			dialogq.setAlwaysOnTop(true);
			dialogq.setBounds(screenwidth/2,screenheight/3,500,400);
			dialogq.setLayout(null);
			dialogq.setModal(true);
			JLabel labelfile1=new JLabel();
			labelfile1.setText("<html><body>�𾴵Ŀͻ����ã�<br><br>��ӭ��ʹ��tony���ļ������������ļ���������JavaΪ�ײ����Ա�д<br>����ʹ�õ�ʱ��"
					+ "����ͨ�������ťʵ�ֹ���ʹ�ã�<br>  1���������½��ļ����ļ��а�ť�����������ļ����У������½�����ҳ�����½�)<br>  2�����Ͻ�"
					+ "�з�����һ�㰴ť�ͽ�����һ�㰴ť���������ض������ı�����ʾ��ǰ·��<br>  3��������������������ർ�����У��������������ҳ���ɷ�����ҳ"
					+ "˫���̷��ɽ������<br>  4����������˫���̷��ɽ�����̲鿴���ݣ�˫���ɽ����ļ��У�˫���ļ��ɴ��ļ��鿴<br>  5��"
					+ "�ļ�Ŀ¼���һ���굯���˵���������ʵ�ָ��ơ�ɾ�������ܡ�ѹ�������ܡ���ѹ<br>  6���϶��Ҳ�������ɹ������ݣ��������ɹرղ˵�"
					+ "ѡ��������������ɴ򿪽̳�"
					+ "<br>  �û���֪��<br>  7����������������ļ�����ڴ棬����Ϥ֪<br>  8����������δ���ƣ�Щ��ģ��δ���й�����䣬�����ڴ���������"
					+ "<br>  9����������ܴ���δ��������©����������ɼ�������٣�����֤��������Ϣ��ȡ��Ϊ<br>  10��������һ�н���Ȩ����������"
					+ "<br><br>==>����ҷ���ť��رս̳̿ɽ����ļ�������<body></html>");
			labelfile1.setBounds(5,5,480,380);
			labelfile1.setVerticalAlignment(SwingConstants.TOP);
			JButton btn=new JButton("����");
			btn.setBounds(300,300,75,25);
			btn.setContentAreaFilled(true);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dialogq.dispose();
				}
			});
			dialogq.add(btn);
			dialogq.add(labelfile1);
			dialogq.setVisible(true);
			flagfinal=0;
		}
	}
	
	private void layoutpanel2() {
		JButton button1=new JButton("�ļ�(T)");
		JButton button2=new JButton("�༭(E)");
		JButton button3=new JButton("�鿴(V)");
		JButton button4=new JButton("����(T)");
		JButton button5=new JButton("����(H)");
		
		JButton listt[]= {button1,button2,button3,button4};
		for (JButton d:listt) {
			d.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showdialog("��ģ����δд��");
				}
			});
		}
		//��Ӱ�����ť����̳�ҳ��
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				flagfinal=1;
				help();
			}
		});
		
		this.panel2.setLayout(null);
		button1.setLocation(1,0);
		button1.setSize(80,25);
		button2.setLocation(81,0);
		button2.setSize(80,25);
		button3.setSize(80,25);
		button3.setLocation(161,0);
		button4.setSize(80,25);
		button4.setLocation(241,0);
		button5.setSize(80,25);
		button5.setLocation(321,0);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		//��ť�߿�
		button2.setBorderPainted(false);
		
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		button4.setContentAreaFilled(false);
		button4.setBorderPainted(false);
		button5.setContentAreaFilled(false);
		button5.setBorderPainted(false);
		this.panel2.add(button1);
		this.panel2.add(button2);
		this.panel2.add(button3);
		this.panel2.add(button4);
		this.panel2.add(button5);
	}
	
	private void layoutpanel3() {
		JButton button1=new JButton("��֯");
		JButton button2=new JButton("����������");
		JButton button3=new JButton("����");
		createfilebutton[0]=new JButton("�½��ļ�");
		createfilebutton[1]=new JButton("�½��ļ���");
		
		JButton listb[]= {button1,button2,button3};
		for (JButton j:listb) {
			j.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					showdialog("��ģ��δд��");
				}
			});
		}
		//����½��ļ����ļ��а�ť�¼�
		for (JButton i:createfilebutton) {
			i.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!text1.getText().equals("��ҳ")) {
						dialog=new JDialog();
						dialog.setTitle("����");
						dialog.setAlwaysOnTop(true);
						dialog.setBounds(screenwidth,screenheight,300,100);
						dialog.setLayout(null);
						dialog.setModal(true);
						//dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);//�������������ȷ�ر�dialog�Ի���
						//textcreatefile=new JTextArea(1,50);//�����޷��ý�������ȷִ�У�Ӧ����������ȫ�ֶ����Ǿֲ�
						textcreatefile.setRows(1);
						textcreatefile.setColumns(50);
						//���ô�����ʾ�Ի�
						if(i.getText().equals("�½��ļ���")) {
							textcreatefile.setText("�������ļ�����");
						}
						else if(i.getText().equals("�½��ļ�")) {
							textcreatefile.setText("�������ļ���+��׺��");
						}
						textcreatefile.setBounds(50,15,140,25);
						textcreatefile.setBackground(Color.white);
						buttoncreatefile=new JButton("����");
						buttoncreatefile.setBounds(190,15,60,25);
						dialog.add(buttoncreatefile);
						dialog.add(textcreatefile);
						
						buttoncreatefile.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								createDir=text1.getText()+textcreatefile.getText();
								//System.out.println(createDir);
								File file=new File(createDir);
								if (!file.exists()) {
									if(i.getText().equals("�½��ļ���")) {
										file.mkdir();
									}
									else if(i.getText().equals("�½��ļ�")) {
										try {
											file.createNewFile();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											dialog.dispose();
											JDialog dialog1=new JDialog();
											dialog1.setTitle("����");
											dialog1.setAlwaysOnTop(true);
											dialog1.setBounds(screenwidth,screenheight,300,100);
											dialog1.setLayout(null);
											dialog1.setModal(true);
											labelfile.setText("�ļ�����ʧ��");
											labelfile.setBounds(80,15,140,25);
											dialog1.add(labelfile);
											dialog1.setVisible(true);
										}
									}
									dialog.dispose();
									JDialog dialog1=new JDialog();
									dialog1.setTitle("����");
									dialog1.setAlwaysOnTop(true);
									dialog1.setBounds(screenwidth,screenheight,300,100);
									dialog1.setLayout(null);
									dialog1.setModal(true);
									if(i.getText().equals("�½��ļ���")) {
										labelfile.setText("�ļ��д����ɹ�");
										labelfile.setBounds(90,15,100,25);
									}
									else if(i.getText().equals("�½��ļ�")) {
										labelfile.setText("�ļ������ɹ�");
										labelfile.setBounds(90,15,100,25);
									}
									JButton btn=new JButton("ȷ��");
									btn.setBounds(200,15,75,25);
									btn.setContentAreaFilled(false);
									btn.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											dialog1.dispose();
										}
									});
									dialog1.add(btn);
									dialog1.add(labelfile);
									
									//ˢ�µ�ǰĿ¼
									temppanel.removeAll();
									temppanel.updateUI();
									Enter_Dir(temppanel,text1.getText());
										
									dialog1.setVisible(true);
								}
								else {
									dialog.dispose();
									JDialog dialog1=new JDialog();
									dialog1.setTitle("����");
									dialog1.setAlwaysOnTop(true);
									dialog1.setBounds(screenwidth,screenheight,300,100);
									dialog1.setLayout(null);
									dialog1.setModal(true);
									if(i.getText().equals("�½��ļ���")) {
										labelfile.setText("���ļ����Ѵ���");
										labelfile.setBounds(80,15,140,25);
									}
									else if(i.getText().equals("�½��ļ�")) {
										labelfile.setText("���ļ��Ѵ���");
										labelfile.setBounds(80,15,140,25);
									}
									dialog1.add(labelfile);
									dialog1.setVisible(true);
								}
							}
						});
						dialog.setVisible(true);
					}
					else {
						showdialog("��ҳ�����½��ļ�");
					}
				}
			});
		}
		this.panel3.setLayout(null);
		button1.setLocation(0,0);
		button1.setSize(75,25);
		button2.setLocation(75,0);
		button2.setSize(95,25);
		button3.setSize(75,25);
		button3.setLocation(175,0);
		createfilebutton[0].setSize(85,25);
		createfilebutton[0].setLocation(255,0);
		createfilebutton[1].setSize(95,25);
		createfilebutton[1].setLocation(335,0);
		button1.setContentAreaFilled(false);
		button1.setBorderPainted(false);
		button2.setContentAreaFilled(false);
		//��ť�߿�
		button2.setBorderPainted(false);
		
		button3.setContentAreaFilled(false);
		button3.setBorderPainted(false);
		createfilebutton[0].setContentAreaFilled(false);
		createfilebutton[0].setBorderPainted(false);
		createfilebutton[1].setContentAreaFilled(false);
		createfilebutton[1].setBorderPainted(false);
		this.panel3.add(button1);
		this.panel3.add(button2);
		this.panel3.add(button3);
		this.panel3.add(createfilebutton[0]);
		this.panel3.add(createfilebutton[1]);
	}
	
	public void layoutpanel4() {
		JPanel panel11=new JPanel();
		JPanel panel22=new JPanel();
		
		panel11.setBackground(Color.LIGHT_GRAY);
		
		//����panel11
//		panel11.setBackground(Color.LIGHT_GRAY);
//		//panel22.setBackground(Color.getHSBColor(113, 191, 234));
//		
		this.panel4.setLayout(new BorderLayout());
		this.panel4.add(panel11,BorderLayout.WEST);
		this.panel4.add(panel22,BorderLayout.CENTER);
		
		panel11.setLayout(null);
		panel11.setPreferredSize(new Dimension(120,150));
		panel22.setPreferredSize(new Dimension(565,380));	//�ײ�Ƕ��
		panel22.setLayout(null);

		//����panel22
		text1.setText("��ҳ");
		temppanel=layoutpanellength(panel22,565,380,545);
		Showhomepage(temppanel,temppanel,50,30,190,30,1,1);
		
		JPanel temppanel2=layoutpanellength(panel11,122,380,103);
		JButton buttonhome=Showhomepage(temppanel2,temppanel,13,25,90,25,1,0);	//���ֲ˵���ť
		
		//����ҳ����ӵĻ
		buttonhome.setText("�������ҳ");
		buttonhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText("��ҳ");
				removecomponent(temppanel);
				Showhomepage(temppanel,temppanel,50,30,190,30,1,1);
			}
		});
	}
	
	//�����Ӵ�������
	public JPanel layoutpanellength(JPanel panel,int width,int height,int widthtop) {
		JPanel panel0=new JPanel();
		//String list[]=(new File(path)).list();
		panel0.setPreferredSize(new Dimension(widthtop,375));	//panel0�Ĵ��ڴ�С���仯
		panel0.setLayout(null);
		JScrollPane jsp=new JScrollPane(panel0);
		jsp.getVerticalScrollBar().setUnitIncrement(15);
		jsp.setBounds(0,0,width,height);
		//jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//�����Ƿ�һֱ�ɼ�
		panel.add(jsp,BorderLayout.CENTER);
		return panel0;
	}
	
	private JButton Showhomepage(JPanel panel,JPanel panel2,int x,int y,int width,int height,int flag,int flag2) {
		JButton home=new JButton("�����");	//��ҳ
		home.setContentAreaFilled(true);
		home.setBounds(0,0,width+x,50);
		panel.add(home);
		
		if(flag2==1) {
			JLabel label=new JLabel();
			label.setBounds(300,150,200,200);
			label.setText("<html><body>��ӭʹ����Ӣ���ļ�������<br><br>�û���֪������������ʼ�����ڴ�<body></html>");
			panel.add(label);
		}
		
		File list[]=File.listRoots();		//�����̷���Ϣ
		for (int i=0;i<list.length;i++) {
			ImageIcon image=new ImageIcon("�ļ���ͼ��.png");
			JButton button=new JButton(list[i].getPath()+"��",image);
			button.setBounds(x,i*y+50,width,height);
			//button.setBorderPainted(false);
			button.setContentAreaFilled(false);
			this.Removeall_Enter(panel2,button,list[i].getPath(),240,i*30+50,flag);
			panel.add(button);
		}
		
		return home;
	}
	
	public void Removeall_Enter(JPanel panel,JButton button,String str,int x,int y,int flag) {
		//buttonstr=button.getText();
		//���õ����ť�¼����Ҽ�������ť���ֲ˵����¼�
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(flag==1&&e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {
					/*���˫�������¼�
					 * ������˫�����βſɽ���
					 */
					panel.removeAll();//�Ƴ��������
//					panel.repaint();
					panel.updateUI();//ˢ�½���
					Enter_Dir(panel,str);
				}
				//�Ҽ��˵�
				else if(e.getButton()==MouseEvent.BUTTON3) {
					if(tempbutton[0].getName()!="temp") {
						for (int i=0;i<5;i++) {
							if(tempbutton[i]!=null) {
								panel.remove(tempbutton[i]);
							}
						}
						panel.updateUI();
					}
					
					//����Ҽ������¼��µ����Ĳ˵���+��ť����¼�
					for (int i=0;i<5;i++) {
						 ImageIcon image=new ImageIcon("�ļ�ͼ��.png");
						tempbutton[i]=new JButton(menulist[i],image);
						tempbutton[i].setBounds(x,y+25*i,200,25);
						//��Ӱ�ť�������¼�
						String buttonmenu=menulist[i];//ʹ��ÿ������һ��
						//��Ӳ˵���ť�¼�
						tempbutton[i].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								File file=new File(text1.getText()+button.getText()+File.separator);
								try {
									menufunc(buttonmenu,file,text1.getText(),button.getText());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									showdialog("�޷�ִ�а�ťѡ��");
								}
							}
						});
						panel.add(tempbutton[i]);
					}
					//tempbutton[0]=new JButton("temp");//���Ǵ����һ��
					panel.updateUI();//ˢ�½������ӵİ�ť�Ż����
				}
				else if(flag==0&&e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {
					try {
						new open_showFile(panel,str);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						showdialog("���ļ�����");
					}
				}
			}
		});
		//������-�˵�����ʧ
		panel.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent a) {
				if(a.getButton()==MouseEvent.BUTTON1) {
					if(tempbutton[0].getName()!="temp") {
						for (int i=0;i<5;i++) {
							if(tempbutton[i]!=null) {
								panel.remove(tempbutton[i]);
							}
						}
						panel.updateUI();
					}
					tempbutton[0]=new JButton("temp");
					for (int i=1;i<5;i++) {
						tempbutton[i]=null;
					}
				}
			}
		});
	}
	
	//��ʾ�������Ի���
	public void showdialog(String content) {
		JDialog dialog1=new JDialog();
		dialog1.setTitle("����");
		dialog1.setAlwaysOnTop(true);
		dialog1.setBounds(screenwidth,screenheight,300,100);
		dialog1.setLayout(null);
		dialog1.setModal(true);
		labelfile.setText(content);
		labelfile.setBounds(80,15,120,25);
		JButton btn=new JButton("ȷ��");
		btn.setBounds(200,15,75,25);
		btn.setContentAreaFilled(false);
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog1.dispose();
			}
		});
		dialog1.add(btn);
		dialog1.add(labelfile);
		dialog1.setVisible(true);
	}
	
	//�Ҽ��˵��¼�����ѡ��
	public void menufunc(String buttonname,File file,String path,String filename) throws Exception {
		FileOperation ins=new FileOperation();
		switch(buttonname.toCharArray()[0]) {
			case 'ɾ':{
				boolean temp=ins.DeleteFile(file);
				
				if(temp) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("�ļ�ɾ���ɹ�");
				}
				break;
			}
			case '��':{
				boolean result;
				if(file.isFile()) {
					result=ins.copyFile(file,new File(""),0,0,file.getAbsolutePath()+File.separator,"");
				}
				else {
					result=ins.copyFile(file,new File(""),1,1,file.getAbsolutePath()+File.separator,"");
					}
				
				if(result) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("�ļ����ļ��и��Ƴɹ�");
				}
				break;
			}
			case 'ѹ':{
				boolean consequence=ins.Zipfile(file,true);
				if(consequence) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("�ļ����ļ���ѹ���ɹ�");
				}
				break;
			}
			case '��':{
				if(file.isDirectory()) {
					showdialog("�ݲ�֧���ļ��м���");
				}
				else {
					File encFile=new File(file.getParent()+File.separator+file.getName().split("\\.")[0]+"_����."+file.getName().split("\\.")[1]);
					System.out.println(encFile.getAbsolutePath());
					boolean temp11=ins.EncryptFile(file,encFile);
					if (temp11) {
						temppanel.removeAll();
						temppanel.updateUI();
						Enter_Dir(temppanel,path);
						showdialog("�ļ����ܳɹ�");
					}
				}
				
				break;
			}
			case '��':{
				boolean temp22=ins.Decrypt_Unzip(file);
				if (temp22) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("�ļ����ܻ��ѹ���ɹ�");
				}
				break;
			}
		}
	}
	
	public void Enter_Dir(JPanel panel,String a){
		text1.setText(a);
		File file=new File(a);
		if (file.exists()&&(file.isFile()||file.isDirectory())) {
			File list[]=file.listFiles();
			if(list.length==0) {
				label=new JLabel("���ļ���");
				label.setBounds(230,150,50,25);
				panel.add(label);
			}
			
			//���ֹ���������仯�仯������
			if(list.length*25+100>380) {		//ʹ�ù������������ݴ�С����
				//panel.setSize(widthpanel, 1000);		setSize��panel�������ã�Ҫ���������
				panel.setPreferredSize(new Dimension(545,list.length*25+100));
			}
			else {
				panel.setPreferredSize(new Dimension(545,375));
			}
			
			//��ʾ�ļ�Ŀ¼
			for (int i=0;i<list.length;i++) {
				ImageIcon image=null;
				if (list[i].isFile()) {
					image=new ImageIcon("�ļ�ͼ��.png");
				}
				else {
					image=new ImageIcon("�ļ���ͼ��.png");
				}
				
				JButton button=new JButton(list[i].getName(),image);
				button.setBounds(5,i*25,200,25);
				button.setHorizontalAlignment(10);;
				if(list[i].isDirectory()) {//�ѸĶ�
					Removeall_Enter(panel,button,list[i].getPath()+File.separator,205,i*25,1);
				}
				else if(list[i].isFile()){
					Removeall_Enter(panel,button,list[i].getPath()+File.separator,205,i*25,0);
				}
				panel.add(button);
			}
		}
		
		//�����ļ���ʽ
		else{
			showdialog("�ݲ�֧�ָ��ļ��鿴");
		}
	}
	
	public void removecomponent(JPanel panel) {
		panel.removeAll();
		tempbutton[0]=new JButton("temp");
		for (int i=1;i<5;i++) {
			tempbutton[i]=null;
		}
		panel.setPreferredSize(new Dimension(545,375));
		panel.updateUI();
	}
	
	public static void main(String args[]) {
		manager ins = new manager();
		ins.help();
		ins.setVisible(true);
	}
}
