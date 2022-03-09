package guifilemanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.swing.JDialog;

public class FileOperation extends manager{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int  BUFFER_SIZE = 2 * 1024;
	private static final int numOfEncAndDec = 0x99; //���ܽ�����Կ
	private static int dataOfFile = 0; //�ļ��ֽ�����

	public boolean DeleteFile(File files) {
		try {
			if(files.isDirectory()) {
				File list[]=files.listFiles();
				for (int i=0;i<list.length;i++) {
					if (list[i].isDirectory()) {
						DeleteFile(list[i]);//�ݹ�ɾ���������Ŀ¼��Ҫ������ܳ���ɾ����
					}
					else if(list[i].isFile()){
						list[i].delete();
					}
				}
				files.delete();
			}
			else {
				files.delete();
			}
			
			return true;
		}
		catch(Exception e) {
			showdialog("Ŀ¼���ļ�ɾ��ʧ��");
			return false;
		}
	}
	
	//��ʾ�Ի���
	public void showdialog(String content) {
		JDialog dialog1=new JDialog();
		dialog1.setTitle("����");
		dialog1.setAlwaysOnTop(true);
		dialog1.setBounds(screenwidth,screenheight,300,100);
		dialog1.setLayout(null);
		dialog1.setModal(true);
		labelfile.setText(content);
		labelfile.setBounds(80,15,140,25);
		dialog1.add(labelfile);
		dialog1.setVisible(true);
	}
	
	public boolean copyFile(File file,File file3,int flag,int flag2,String oldpath,String newpath) {
		if(flag==0) {
			try{       
				int byteread=0;    
				File outfile=new File(file.getParent()+File.separator+file.getName().split("\\.")[0]+"����."+file.getName().split("\\.")[1]);
				outfile.createNewFile();
				InputStream  inStream=new FileInputStream(file);  //����ԭ�ļ�   
				FileOutputStream fcopy=new FileOutputStream(outfile);    
				byte[] buffer=new byte[1444];       
				while((byteread=inStream.read(buffer))!=-1){      
					fcopy.write(buffer,0,byteread);    
				}
				fcopy.flush();
				fcopy.close();
				inStream.close();
				return true;
	         }
			catch(Exception e){
				showdialog("�ļ�����ʧ��");
				return false;
			}    
		}
		else {
			try{
				if(flag2==1) {
					String path2=file.getParent()+File.separator+file.getName()+"_����";
					File filet=new File(path2);
					int flag1=0;
					while(filet.exists()&&flag1<20) {
						filet=new File(path2+"_����");
						flag1++;
					}
					filet.mkdir();
					flag2=0;
					newpath=filet.getAbsolutePath()+File.separator;
				}
				String[] list=file.list();    
				File  temp=null;    
				for (int i=0;i<list.length;i++){    
					temp=new  File(oldpath+list[i]+File.separator);
					if(temp.isFile()){
						File outfile=new File(newpath+list[i]);
						FileInputStream input=new FileInputStream(temp);    
						FileOutputStream output=new FileOutputStream(outfile);    
						byte[] b=new byte[1024*5];    
						int len;    
						while((len=input.read(b))!=-1){    
							output.write(b,0,len);    
						}    
						output.flush();    
						output.close();    
						input.close();
						outfile.createNewFile();					
					}    
					if(temp.isDirectory()){//��������ļ���   
						File file2=new File(newpath+list[i]+File.separator);
						file2.mkdir();
						file=temp;
						oldpath=oldpath+list[i]+File.separator;
						newpath=newpath+list[i]+File.separator;
						copyFile(file,file2,1,0,oldpath,newpath);    
					}
				}
				return true;
			}    
			catch  (Exception  e)  {
				showdialog("Ŀ¼���ļ�����ʧ��");
				return false;   
			}    
		}
	}
	
	//ѹ���ļ����ļ���
	public boolean Zipfile(File file, boolean KeepDirStructure) throws FileNotFoundException{
		String path2=file.getAbsolutePath()+".zip";
		String path3=file.getAbsolutePath();
		File fil=new File(path2);
		int flag1=0;
		while(fil.exists()&&flag1<20) {
			path3=path3+"_����";
			fil=new File(path3+".zip");
			flag1++;
		}
		OutputStream out=new FileOutputStream(path3+".zip");
		ZipOutputStream zos = null ;
		try {
			zos = new ZipOutputStream(out);
			File sourceFile =file;
			compress(sourceFile,zos,sourceFile.getName(),KeepDirStructure);
			zos.close();
			return true;
		} 
		catch (Exception e) {
			showdialog("ѹ���ļ����ļ���ʧ��");
			return false;
		}
	}
	
	public void compress(File sourceFile, ZipOutputStream zos, String name,boolean KeepDirStructure) throws Exception{
		byte[] buf = new byte[BUFFER_SIZE];
		if(sourceFile.isFile()){
			zos.putNextEntry(new ZipEntry(name+File.separator));
			int len;
			FileInputStream in = new FileInputStream(sourceFile);
			while ((len = in.read(buf)) != -1){
				zos.write(buf, 0, len);
			}
			zos.closeEntry();
			in.close();
		} 
		else{
			File[] listFiles=sourceFile.listFiles();
			if(listFiles==null||listFiles.length==0){
				if(KeepDirStructure){
					zos.putNextEntry(new ZipEntry(name+File.separator));
					zos.closeEntry();
				}
			}
			else{
				for (File file : listFiles){
					if (KeepDirStructure) {
						compress(file,zos,name+File.separator+file.getName(),KeepDirStructure);
					} 
					else {
						compress(file,zos,file.getName(),KeepDirStructure);
					}
				}
			}
		}
	}
	
	
	//�ļ�����
	public boolean EncryptFile(File srcFile, File encFile) throws Exception {
		if(!encFile.exists()){
			encFile.createNewFile();
		}
		else {
			showdialog("�ü����ļ��Ѵ���");
			return false;
		}
		InputStream fis  = new FileInputStream(srcFile);
		OutputStream fos = new FileOutputStream(encFile);
	         
		while ((dataOfFile = fis.read()) > -1) {
			fos.write(dataOfFile^numOfEncAndDec);
		}
		
		fis.close();
		fos.flush();
		fos.close();
		return true;
	}
	
	//�ļ����ܺͽ�ѹ��
	public boolean Decrypt_Unzip(File file) throws IOException {//�����������׳��쳣������
		File finalFile=null;
		String descDir=null;
		//��ѹ��
		if (file.getName().split("\\.")[1].equals("zip")) {
			descDir=file.getParent()+file.getName().split("\\.")[0]+"_��ѹ��"+File.separator;
	        ZipFile zipFile = new ZipFile(file);
	        ZipEntry zipEntry;
	        Enumeration<? extends ZipEntry> entry = zipFile.entries();
	        while (entry.hasMoreElements()) {
	            zipEntry = entry.nextElement();
	            String fileName = zipEntry.getName();
	            File outputFile = new File(descDir + fileName);
	            if (zipEntry.isDirectory()) {
	                forceMkdirs(outputFile);
	                continue;
	            } else if (!outputFile.getParentFile().exists()) {
	                forceMkdirs(outputFile.getParent());
	            }
	            OutputStream outputStream = new FileOutputStream(outputFile);
	            InputStream inputStream = zipFile.getInputStream(zipEntry);
	            int len;
	            byte[] buffer = new byte[8192];
	            while (-1 != (len = inputStream.read(buffer))) {
	                outputStream.write(buffer, 0, len);
	            }
	            outputStream.close();
	            inputStream.close();
	        }
	        zipFile.close();
	        return true;
	    }
		
		//����
		else {
			finalFile=new File(file.getParent()+File.separator+file.getName().split("\\.")[0]+"_����"+file.getName().split("\\.")[1]);
			if(!finalFile.exists()){
				finalFile.createNewFile();
			}
			else {
				showdialog("�����ļ��Ѵ���");
				return false;
			}
			InputStream fis  = new FileInputStream(file);
			OutputStream fos = new FileOutputStream(finalFile);
			while ((dataOfFile = fis.read()) > -1) {
				fos.write(dataOfFile^numOfEncAndDec);
			}
		
			fis.close();
			fos.flush();
			fos.close();
		}
		return true;
	}
	
	public static File forceMkdirs(File file) {
        if (!file.exists()) {
            file.mkdirs();
        } else if (!file.isDirectory()) {
            file.delete();
            file.mkdirs();
        }
        return file;
    }
	public static File forceMkdirs(String pathName) {
        return forceMkdirs(new File(pathName));
    }
}
