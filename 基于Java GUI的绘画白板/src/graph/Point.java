package graph;

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
