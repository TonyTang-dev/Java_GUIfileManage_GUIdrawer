package graph;

import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import app.OpenGLApp;

public class Graphic extends OpenGLApp{
	private static final long serialVersionUID = 1L;
	protected JPanel panel=null;
	protected JLabel label2=null;
	//protected JLabel label3=null;
	
	
	public Graphic(JPanel panel,JLabel label2,JLabel label3,
			ArrayList<Triangle> list1,ArrayList<Rectangle> list2,ArrayList<Cube> list3) {
		this.panel=panel;
		this.label2=label2;//子类不能直接修改父类的成员域，要搭建链接才能直接修改，否则修改的对象是一个值，即子类自己的成员变量
		this.label3=label3;
		this.list1=list1;
		this.list2=list2;
		this.list3=list3;
	}
	
	public void add1(Triangle a) {
		list1.add(a);
	}
	public void add1(Rectangle a) {//重载
		list2.add(a);
	}
	public void add1(Cube a) {
		list3.add(a);
	}
	
	public void draw(int temp3) {
		Triangle ins=null;
		Rectangle ins2=null;
		Cube ins3=null;
		custom_draw ins4=null;
		switch(temp3) {
		case 1:
			ins = new Triangle();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("加入三角形信息模块");
			label3.setText("输入三角形信息确定后会存档，方便后期查询");
			panel0.updateUI();
			ins=ins.draw(panel);
			add1(ins);
			break;
		case 2:
			ins2 = new Rectangle();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("加入矩形信息模块");
			label3.setText("输入矩形信息确定后会存档，方便后期查询");
			panel0.updateUI();
			ins2=ins2.draw(panel);
			add1(ins2);
			break;
		case 3:
			ins3 = new Cube();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("加入立方体信息模块");
			label3.setText("输入立方体信息确定后会存档，方便后期查询");
			panel0.updateUI();
			ins3=ins3.draw(panel);
			add1(ins3);
			break;
		case 4:
			ins4 = new custom_draw();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("自定义画板模块");
			label3.setText("自定义画板暂时不会存档，后期不可查询");
			panel0.updateUI();
			ins4=ins4.draw(panel);
			add1(ins3);
			break;
		case 5:
			label2.setText("显示所有信息模块");
			label3.setText("显示除自定义绘画版外的所有信息");
			super.display(list1,list2,list3,panel);
			return;
		default:
			showdialog("您输入的指令有误");
			break;
		}
	}	
}
