package graph;

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

public class Point {
	public double x;
	public double y;
	public double z;	//member variable
	protected Point(double x,double y,double z) {
		this.x=x;
		this.y=y;
		this.z=z;
	}
}
