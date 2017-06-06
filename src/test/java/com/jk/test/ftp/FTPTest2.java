package com.jk.test.ftp;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Ignore;
import org.junit.Test;

public class FTPTest2 {
	
	public boolean getFileISList(String tempPath, List<String> pathList, List<String> fileNameList) {
		boolean boo = false;
		//1、创建ftpclient
		FTPClient ftp = new FTPClient();
		//2、连接服务器
		try {
			ftp.connect("192.168.31.200", 21);
			//3、登录
			ftp.login("root", "root");
			//4、判断是否登陆成功
			int reply = ftp.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//5、设置编码
				ftp.setControlEncoding(System.getProperty("file.encoding"));
	            //6、切换路径
				ftp.changeWorkingDirectory("/");
				// 设置文件传输类型为二进制
	            ftp.setFileType(FTPClient.BINARY_FILE_TYPE);
	            
	            OutputStream os = null;
	            //循环文件路径集合
	            for (int i = 0; i < pathList.size(); i++) {
					InputStream is = ftp.retrieveFileStream(pathList.get(i));
					//判断文件是否存在
		            if (null == is || ftp.getReplyCode() == 550) {
		            	is = null;
		            	continue;
	                }
	            	os = new FileOutputStream(tempPath + fileNameList.get(i));
	            	boo = ftp.retrieveFile(pathList.get(i), os);
	            	is.close();
	            	os.close();
				}
	            ftp.logout();
			}
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				ftp.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return boo;
	}
	
	public void downLoadZipFile(String tempPath, String tempZipName, List<String> pathList, List<String> fileNameList) {
        File f = new File(tempPath);
        if (!f.exists()) {
        	//创建临时路径
        	f.mkdirs();
        }
        
        byte[] buffer = new byte[1024];
        String strZipPath = tempPath + tempZipName;
        try {
        	//实例化zip输出流
        	ZipOutputStream out = new ZipOutputStream(new FileOutputStream(strZipPath));
        	//调用ftp的方法，获取文件输入流的集合
        	boolean boo = getFileISList(tempPath, pathList, fileNameList);
        	if (boo) {
        		//读取临时路径下所有文件
        		File[] listFiles = f.listFiles();
        		if (null != listFiles && 0 < listFiles.length) {
        			for (int j = 0; j < listFiles.length; j++) {
        				File file = listFiles[j];
        				if (file.isFile() && !file.getName().endsWith(".zip")) {
        					InputStream ins = new FileInputStream(file);
        					out.putNextEntry(new ZipEntry(file.getName()));
        					int len;
        					// 读入需要下载的文件的内容，打包到zip文件
        					while ((len = ins.read(buffer)) > 0) {
        						out.write(buffer, 0, len);
        					}
        					//文件放置完毕，关闭当前文件
        					out.closeEntry();
        					ins.close();
        				}
        			}
        		}
        	}
        	out.close();
        	//downLoadZipFileTOClient(response, strZipPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void test111() {
		String tempPath = "e:/test23/";
		String tempZipName = "aaa.zip";
		List<String> pathList = new ArrayList<String>();
		pathList.add("/movies/2017/05/04/72ce9dbd-401d-4502-bca7-e989415f5b3baa.mp4");
		pathList.add("/movies/2017/05/04/72ce9dbd-401d-4502-bca7-e989415f5b3b.mp4");
		List<String> fileNameList = new ArrayList<String>();
		fileNameList.add("视频9.mp4");
		fileNameList.add("视频8.mp4");
		downLoadZipFile(tempPath, tempZipName, pathList, fileNameList);
	}
	
	@Test
	@Ignore
	public void test11111() {
		File f = new File("e:/test23/ddbb");
//		if (f.isDirectory()) {
//			File[] listFiles = f.listFiles();
//			for (File file : listFiles) {
//				file.delete();
//			}
//		}
//		f.delete();
		test1111122(f);
	}
	
	public void test1111122(File f) {
		if (f.isDirectory()) {
			File[] listFiles = f.listFiles();
			for (File file : listFiles) {
				if (f.isDirectory()) {
					test1111122(file);
				} else {
					file.delete();
				}
			}
		}
		f.delete();
	}
	
	@Test
	public void testddd() {
		InputStream is = null;
		BufferedInputStream bis = null;
		OutputStream os = null;
		BufferedOutputStream bos = null;
		try {
			//判断服务器上是否有要下载的zip文件
			File file = new File("E:/workspase/1609B_6_3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ssi-03/temp/c636b6f8-5b89-4b05-b2cf-0ba037a84b20/f642308d-1998-4c50-a3ec-e51115a33aa8.zip");
			if (file.exists()) {
				is = new FileInputStream("E:/workspase/1609B_6_3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/ssi-03/temp/c636b6f8-5b89-4b05-b2cf-0ba037a84b20/f642308d-1998-4c50-a3ec-e51115a33aa8.zip");
				// 放到缓冲流里面
				bis = new BufferedInputStream(is);
				
//				response.setCharacterEncoding("utf-8");
//				// 设置response内容的类型
////				response.setContentType("application/x-download");
////				response.setContentType("multipart/form-data");
//				// 设置头部信息
//				response.setHeader("Content-Type", "application/zip");
//				response.setHeader("Content-disposition", "attachment;filename="
//                                + UUID.randomUUID().toString() + ".zip");
				// 获取文件输出IO流
				os = new FileOutputStream("e:/ceshiaa.zip");
				bos = new BufferedOutputStream(os);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				// 开始向网络传输文件流
				while ((bytesRead = is.read(buffer)) != -1) {
					os.write(buffer);
					// 这里一定要调用flush()方法
					os.flush();
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				if(null != bos) {
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if(null != os) {
					os.flush();
					os.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (null != bis) {
					bis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				if (null != is) {
					is.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
