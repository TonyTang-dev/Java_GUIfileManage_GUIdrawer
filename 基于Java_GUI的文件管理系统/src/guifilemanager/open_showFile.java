package guifilemanager;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class open_showFile extends manager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/*
	 * 该模块有待改进，无法正常阅读
	 */
	public open_showFile(JPanel panel,String str) throws IOException {
		JTextArea textfield=new JTextArea();
		textfield.setBounds(0,0,545,400);
		textfield.setLineWrap(true);
		
		int temp=0,index=0;
		File source=new File(str);
		char text[]=new char[(int)source.length()];
		String text2="";
		Reader reader=new FileReader(source);
		while((temp=reader.read())!=-1) {
			text[index++]=(char)temp;
		}
		reader.close();
		textfield.setText("");
		if(text.length==0) {
			JDialog dialog1=new JDialog();
			dialog1.setTitle("详情");
			dialog1.setAlwaysOnTop(true);
			dialog1.setBounds(screenwidth,screenheight,300,100);
			dialog1.setLayout(null);
			dialog1.setModal(true);
			labelfile.setText("这是一个空文件");
			labelfile.setBounds(80,15,140,25);
			dialog1.add(labelfile);
			Enter_Dir(panel,source.getParent());
			dialog1.setVisible(true);
		}
		else {
			panel.removeAll();//移除所有组件
			panel.updateUI();//刷新界面
			for (int i=0;i<index;i++) {
				if(i%50==0) {
					
				}
				text2+=Character.toString(text[i]);
			}
			textfield.setText(text2);
			textfield.add(labelfile);
			panel.add(textfield);
		}
	}
}
