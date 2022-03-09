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
	 * 这是针对整体的文件管理器入口主类
	 */
	private static final long serialVersionUID = 2642996604805081618L;
	/*这是序列化ID，等同于身份验证，用于版本控制维护不同版本的兼容性以及避免程序升级时报错
	*通过序列化机制确定类的serialVersionUID来验证版本的一致性
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
	private String menulist[]= {"删除该文件或文件夹","压缩该文件或文件夹","复制该文件或文件夹","加密该目录或文件夹","解压或解密该文件"};
	private JDialog dialog;
	public int screenwidth=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/3;
	public int screenheight=(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/3;

	public manager() {
		setSize(700,500);
		setLocation(200,100);
		setTitle("文件管理器-tony");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);	//窗口大小不可变，不可最大化
		
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
		
		//布局嵌板
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
		JButton button3=new JButton("进入");
		text2=new JTextArea(1,50);
		text2.setText("请输入您要搜索的文件或目录");
		JButton button4=new JButton("搜索");
		
		//添加文本框点击全选事件
		text1.addFocusListener(new myFocusListener());
		text2.addFocusListener(new myFocusListener());
		textcreatefile.addFocusListener(new myFocusListener());
		
		//为搜索按钮添加事件
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog=new JDialog();
				dialog.setTitle("详情");
				dialog.setAlwaysOnTop(true);
				dialog.setBounds(screenwidth,screenheight,300,200);
				dialog.setLayout(null);
				dialog.setModal(true);
				//dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);//这个方法不能正确关闭dialog对话框
				labelfile.setText("暂未写入该模块");
				labelfile.setBounds(75,50,150,60);
				dialog.add(labelfile);
				dialog.setVisible(true);
			}
		});
		//添加返回上一级目录键
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text1.getText().split("\\"+File.separator).length!=1
						&&!text1.getText().equals("首页")) {
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
						&&!text1.getText().equals("首页")) {
					templast=text1.getText();
					text1.setText("首页");
					removecomponent(temppanel);
					Showhomepage(temppanel,temppanel,50,30,190,30,1,1);
				}
			}
		});
		
		//返回下一页目录
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
	
	//文本框全选
	private class myFocusListener extends FocusAdapter {
		public void focusGained(FocusEvent e) {
			JTextArea c=(JTextArea)e.getSource();
			c.setSelectionStart(0);
			c.setSelectionEnd(c.getText().length());
		}
	}
	
	//本程序教程
	public void help() {
		if(flagfinal==1) {
			JDialog dialogq=new JDialog();
			dialogq.setTitle("使用教程");
			dialogq.setAlwaysOnTop(true);
			dialogq.setBounds(screenwidth/2,screenheight/3,500,400);
			dialogq.setLayout(null);
			dialogq.setModal(true);
			JLabel labelfile1=new JLabel();
			labelfile1.setText("<html><body>尊敬的客户您好：<br><br>欢迎您使用tony版文件管理器，该文件管理器以Java为底层语言编写<br>在您使用的时候"
					+ "，可通过点击按钮实现功能使用：<br>  1、顶端有新建文件和文件夹按钮，单击输入文件（夹）名可新建（首页不可新建)<br>  2、左上角"
					+ "有返回上一层按钮和进入下一层按钮，可做返回动作，文本框显示当前路径<br>  3、管理器主功能区：左侧导航栏中，单击“计算机首页”可返回首页"
					+ "双击盘符可进入磁盘<br>  4、主功能区双击盘符可进入磁盘查看内容，双击可进入文件夹，双击文件可打开文件查看<br>  5、"
					+ "文件目录可右击鼠标弹出菜单栏单击可实现复制、删除、加密、压缩、解密、解压<br>  6、拖动右侧滚动条可滚动内容，单击面板可关闭菜单"
					+ "选项，单击“帮助”可打开教程"
					+ "<br>  用户须知：<br>  7、本程序需访问您的计算机内存，敬请悉知<br>  8、本程序尚未完善，些许模块未进行功能填充，敬请期待后续更新"
					+ "<br>  9、本程序可能存在未处理的软件漏洞，可能造成计算机卡顿，但保证不存在信息盗取行为<br>  10、本程序一切解释权归作者所有"
					+ "<br><br>==>点击右方按钮或关闭教程可进入文件管理器<body></html>");
			labelfile1.setBounds(5,5,480,380);
			labelfile1.setVerticalAlignment(SwingConstants.TOP);
			JButton btn=new JButton("进入");
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
		JButton button1=new JButton("文件(T)");
		JButton button2=new JButton("编辑(E)");
		JButton button3=new JButton("查看(V)");
		JButton button4=new JButton("工具(T)");
		JButton button5=new JButton("帮助(H)");
		
		JButton listt[]= {button1,button2,button3,button4};
		for (JButton d:listt) {
			d.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showdialog("该模块暂未写入");
				}
			});
		}
		//添加帮助按钮进入教程页面
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
		//按钮边框
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
		JButton button1=new JButton("组织");
		JButton button2=new JButton("包含到库中");
		JButton button3=new JButton("共享");
		createfilebutton[0]=new JButton("新建文件");
		createfilebutton[1]=new JButton("新建文件夹");
		
		JButton listb[]= {button1,button2,button3};
		for (JButton j:listb) {
			j.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e) {
					showdialog("该模块未写入");
				}
			});
		}
		//添加新建文件和文件夹按钮事件
		for (JButton i:createfilebutton) {
			i.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(!text1.getText().equals("首页")) {
						dialog=new JDialog();
						dialog.setTitle("详情");
						dialog.setAlwaysOnTop(true);
						dialog.setBounds(screenwidth,screenheight,300,100);
						dialog.setLayout(null);
						dialog.setModal(true);
						//dialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);//这个方法不能正确关闭dialog对话框
						//textcreatefile=new JTextArea(1,50);//这样无法让焦点检测正确执行，应当让声明在全局而不是局部
						textcreatefile.setRows(1);
						textcreatefile.setColumns(50);
						//设置创建提示对话
						if(i.getText().equals("新建文件夹")) {
							textcreatefile.setText("请输入文件夹名");
						}
						else if(i.getText().equals("新建文件")) {
							textcreatefile.setText("请输入文件名+后缀名");
						}
						textcreatefile.setBounds(50,15,140,25);
						textcreatefile.setBackground(Color.white);
						buttoncreatefile=new JButton("创建");
						buttoncreatefile.setBounds(190,15,60,25);
						dialog.add(buttoncreatefile);
						dialog.add(textcreatefile);
						
						buttoncreatefile.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								createDir=text1.getText()+textcreatefile.getText();
								//System.out.println(createDir);
								File file=new File(createDir);
								if (!file.exists()) {
									if(i.getText().equals("新建文件夹")) {
										file.mkdir();
									}
									else if(i.getText().equals("新建文件")) {
										try {
											file.createNewFile();
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											dialog.dispose();
											JDialog dialog1=new JDialog();
											dialog1.setTitle("详情");
											dialog1.setAlwaysOnTop(true);
											dialog1.setBounds(screenwidth,screenheight,300,100);
											dialog1.setLayout(null);
											dialog1.setModal(true);
											labelfile.setText("文件创建失败");
											labelfile.setBounds(80,15,140,25);
											dialog1.add(labelfile);
											dialog1.setVisible(true);
										}
									}
									dialog.dispose();
									JDialog dialog1=new JDialog();
									dialog1.setTitle("详情");
									dialog1.setAlwaysOnTop(true);
									dialog1.setBounds(screenwidth,screenheight,300,100);
									dialog1.setLayout(null);
									dialog1.setModal(true);
									if(i.getText().equals("新建文件夹")) {
										labelfile.setText("文件夹创建成功");
										labelfile.setBounds(90,15,100,25);
									}
									else if(i.getText().equals("新建文件")) {
										labelfile.setText("文件创建成功");
										labelfile.setBounds(90,15,100,25);
									}
									JButton btn=new JButton("确定");
									btn.setBounds(200,15,75,25);
									btn.setContentAreaFilled(false);
									btn.addActionListener(new ActionListener() {
										public void actionPerformed(ActionEvent e) {
											dialog1.dispose();
										}
									});
									dialog1.add(btn);
									dialog1.add(labelfile);
									
									//刷新当前目录
									temppanel.removeAll();
									temppanel.updateUI();
									Enter_Dir(temppanel,text1.getText());
										
									dialog1.setVisible(true);
								}
								else {
									dialog.dispose();
									JDialog dialog1=new JDialog();
									dialog1.setTitle("详情");
									dialog1.setAlwaysOnTop(true);
									dialog1.setBounds(screenwidth,screenheight,300,100);
									dialog1.setLayout(null);
									dialog1.setModal(true);
									if(i.getText().equals("新建文件夹")) {
										labelfile.setText("该文件夹已存在");
										labelfile.setBounds(80,15,140,25);
									}
									else if(i.getText().equals("新建文件")) {
										labelfile.setText("该文件已存在");
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
						showdialog("首页不可新建文件");
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
		//按钮边框
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
		
		//布局panel11
//		panel11.setBackground(Color.LIGHT_GRAY);
//		//panel22.setBackground(Color.getHSBColor(113, 191, 234));
//		
		this.panel4.setLayout(new BorderLayout());
		this.panel4.add(panel11,BorderLayout.WEST);
		this.panel4.add(panel22,BorderLayout.CENTER);
		
		panel11.setLayout(null);
		panel11.setPreferredSize(new Dimension(120,150));
		panel22.setPreferredSize(new Dimension(565,380));	//底层嵌板
		panel22.setLayout(null);

		//布局panel22
		text1.setText("首页");
		temppanel=layoutpanellength(panel22,565,380,545);
		Showhomepage(temppanel,temppanel,50,30,190,30,1,1);
		
		JPanel temppanel2=layoutpanellength(panel11,122,380,103);
		JButton buttonhome=Showhomepage(temppanel2,temppanel,13,25,90,25,1,0);	//布局菜单按钮
		
		//给首页键添加的活动
		buttonhome.setText("计算机首页");
		buttonhome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text1.setText("首页");
				removecomponent(temppanel);
				Showhomepage(temppanel,temppanel,50,30,190,30,1,1);
			}
		});
	}
	
	//布局视窗滚动条
	public JPanel layoutpanellength(JPanel panel,int width,int height,int widthtop) {
		JPanel panel0=new JPanel();
		//String list[]=(new File(path)).list();
		panel0.setPreferredSize(new Dimension(widthtop,375));	//panel0的窗口大小待变化
		panel0.setLayout(null);
		JScrollPane jsp=new JScrollPane(panel0);
		jsp.getVerticalScrollBar().setUnitIncrement(15);
		jsp.setBounds(0,0,width,height);
		//jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//设置是否一直可见
		panel.add(jsp,BorderLayout.CENTER);
		return panel0;
	}
	
	private JButton Showhomepage(JPanel panel,JPanel panel2,int x,int y,int width,int height,int flag,int flag2) {
		JButton home=new JButton("计算机");	//主页
		home.setContentAreaFilled(true);
		home.setBounds(0,0,width+x,50);
		panel.add(home);
		
		if(flag2==1) {
			JLabel label=new JLabel();
			label.setBounds(300,150,200,200);
			label.setText("<html><body>欢迎使用唐英福文件管理器<br><br>用户须知：本程序需访问计算机内存<body></html>");
			panel.add(label);
		}
		
		File list[]=File.listRoots();		//分区盘符信息
		for (int i=0;i<list.length;i++) {
			ImageIcon image=new ImageIcon("文件夹图标.png");
			JButton button=new JButton(list[i].getPath()+"盘",image);
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
		//设置点击按钮事件和右键单击按钮出现菜单栏事件
		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(flag==1&&e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {
					/*鼠标双击进入事件
					 * 鼠标左键双击两次才可进入
					 */
					panel.removeAll();//移除所有组件
//					panel.repaint();
					panel.updateUI();//刷新界面
					Enter_Dir(panel,str);
				}
				//右键菜单
				else if(e.getButton()==MouseEvent.BUTTON3) {
					if(tempbutton[0].getName()!="temp") {
						for (int i=0;i<5;i++) {
							if(tempbutton[i]!=null) {
								panel.remove(tempbutton[i]);
							}
						}
						panel.updateUI();
					}
					
					//添加右键单击事件下弹出的菜单栏+按钮点击事件
					for (int i=0;i<5;i++) {
						 ImageIcon image=new ImageIcon("文件图标.png");
						tempbutton[i]=new JButton(menulist[i],image);
						tempbutton[i].setBounds(x,y+25*i,200,25);
						//添加按钮监听器事件
						String buttonmenu=menulist[i];//使得每个对象不一样
						//添加菜单按钮事件
						tempbutton[i].addActionListener(new ActionListener(){
							public void actionPerformed(ActionEvent e) {
								File file=new File(text1.getText()+button.getText()+File.separator);
								try {
									menufunc(buttonmenu,file,text1.getText(),button.getText());
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									showdialog("无法执行按钮选项");
								}
							}
						});
						panel.add(tempbutton[i]);
					}
					//tempbutton[0]=new JButton("temp");//这是错误的一步
					panel.updateUI();//刷新界面后添加的按钮才会出现
				}
				else if(flag==0&&e.getButton()==MouseEvent.BUTTON1&&e.getClickCount()==2) {
					try {
						new open_showFile(panel,str);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						showdialog("打开文件出错");
					}
				}
			}
		});
		//点击面板-菜单栏消失
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
	
	//显示处理结果对话框
	public void showdialog(String content) {
		JDialog dialog1=new JDialog();
		dialog1.setTitle("详情");
		dialog1.setAlwaysOnTop(true);
		dialog1.setBounds(screenwidth,screenheight,300,100);
		dialog1.setLayout(null);
		dialog1.setModal(true);
		labelfile.setText(content);
		labelfile.setBounds(80,15,120,25);
		JButton btn=new JButton("确定");
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
	
	//右键菜单事件处理选择
	public void menufunc(String buttonname,File file,String path,String filename) throws Exception {
		FileOperation ins=new FileOperation();
		switch(buttonname.toCharArray()[0]) {
			case '删':{
				boolean temp=ins.DeleteFile(file);
				
				if(temp) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("文件删除成功");
				}
				break;
			}
			case '复':{
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
					showdialog("文件或文件夹复制成功");
				}
				break;
			}
			case '压':{
				boolean consequence=ins.Zipfile(file,true);
				if(consequence) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("文件或文件夹压缩成功");
				}
				break;
			}
			case '加':{
				if(file.isDirectory()) {
					showdialog("暂不支持文件夹加密");
				}
				else {
					File encFile=new File(file.getParent()+File.separator+file.getName().split("\\.")[0]+"_加密."+file.getName().split("\\.")[1]);
					System.out.println(encFile.getAbsolutePath());
					boolean temp11=ins.EncryptFile(file,encFile);
					if (temp11) {
						temppanel.removeAll();
						temppanel.updateUI();
						Enter_Dir(temppanel,path);
						showdialog("文件加密成功");
					}
				}
				
				break;
			}
			case '解':{
				boolean temp22=ins.Decrypt_Unzip(file);
				if (temp22) {
					temppanel.removeAll();
					temppanel.updateUI();
					Enter_Dir(temppanel,path);
					showdialog("文件解密或解压缩成功");
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
				label=new JLabel("空文件夹");
				label.setBounds(230,150,50,25);
				panel.add(label);
			}
			
			//布局滚动条，随变化变化滚动条
			if(list.length*25+100>380) {		//使得滚动条随着数据大小滚动
				//panel.setSize(widthpanel, 1000);		setSize对panel不起作用，要用下面这个
				panel.setPreferredSize(new Dimension(545,list.length*25+100));
			}
			else {
				panel.setPreferredSize(new Dimension(545,375));
			}
			
			//显示文件目录
			for (int i=0;i<list.length;i++) {
				ImageIcon image=null;
				if (list[i].isFile()) {
					image=new ImageIcon("文件图标.png");
				}
				else {
					image=new ImageIcon("文件夹图标.png");
				}
				
				JButton button=new JButton(list[i].getName(),image);
				button.setBounds(5,i*25,200,25);
				button.setHorizontalAlignment(10);;
				if(list[i].isDirectory()) {//已改动
					Removeall_Enter(panel,button,list[i].getPath()+File.separator,205,i*25,1);
				}
				else if(list[i].isFile()){
					Removeall_Enter(panel,button,list[i].getPath()+File.separator,205,i*25,0);
				}
				panel.add(button);
			}
		}
		
		//其他文件格式
		else{
			showdialog("暂不支持该文件查看");
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
