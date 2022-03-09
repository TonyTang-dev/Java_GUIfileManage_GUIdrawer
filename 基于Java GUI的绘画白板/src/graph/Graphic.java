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
		this.label2=label2;//���಻��ֱ���޸ĸ���ĳ�Ա��Ҫ����Ӳ���ֱ���޸ģ������޸ĵĶ�����һ��ֵ���������Լ��ĳ�Ա����
		this.label3=label3;
		this.list1=list1;
		this.list2=list2;
		this.list3=list3;
	}
	
	public void add1(Triangle a) {
		list1.add(a);
	}
	public void add1(Rectangle a) {//����
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
			label2.setText("������������Ϣģ��");
			label3.setText("������������Ϣȷ�����浵��������ڲ�ѯ");
			panel0.updateUI();
			ins=ins.draw(panel);
			add1(ins);
			break;
		case 2:
			ins2 = new Rectangle();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("���������Ϣģ��");
			label3.setText("���������Ϣȷ�����浵��������ڲ�ѯ");
			panel0.updateUI();
			ins2=ins2.draw(panel);
			add1(ins2);
			break;
		case 3:
			ins3 = new Cube();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("������������Ϣģ��");
			label3.setText("������������Ϣȷ�����浵��������ڲ�ѯ");
			panel0.updateUI();
			ins3=ins3.draw(panel);
			add1(ins3);
			break;
		case 4:
			ins4 = new custom_draw();
			panel.removeAll();
			panel.updateUI();
			panel0.removeAll();
			label2.setText("�Զ��廭��ģ��");
			label3.setText("�Զ��廭����ʱ����浵�����ڲ��ɲ�ѯ");
			panel0.updateUI();
			ins4=ins4.draw(panel);
			add1(ins3);
			break;
		case 5:
			label2.setText("��ʾ������Ϣģ��");
			label3.setText("��ʾ���Զ���滭�����������Ϣ");
			super.display(list1,list2,list3,panel);
			return;
		default:
			showdialog("�������ָ������");
			break;
		}
	}	
}
