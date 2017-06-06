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
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.junit.Ignore;
import org.junit.Test;

public class FTPTest {

	@Test
	@Ignore
	public void uploadFile() {
		//1、实例化ftp客户端
		FTPClient f = new FTPClient();
		try {
			//2、连接服务器
			f.connect("192.168.31.199");
			//3、登录验证
			f.login("root", "root");
			
			//4、判断是否登陆成功
			int reply = f.getReplyCode();
			if (FTPReply.isPositiveCompletion(reply)) {
				//切换到根路径
				f.changeWorkingDirectory("/");
				//判断是否存在
				//创建文件夹(返回true:服务器上没有，已被创建；返回false:服务器上有，创建失败)
				boolean d = f.makeDirectory("aa/bb/ff");
				//切换到刚刚创建完的路径下
				f.changeWorkingDirectory("/aa/bb/ff");
				InputStream is = new FileInputStream("e:/ss.txt");
				//f.setAutodetectUTF8(true);
				//在这个路径下保存文件
				f.storeFile(new String("黑名单.txt".getBytes("GBK"), "ISO-8859-1"), is);
				f.logout();
			}
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				f.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	@Test
	@Ignore
	public void downLoadFile() {
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
	            OutputStream os = new FileOutputStream("e:/ggghhhhh.mp4");
				//7、获取某文件
	            boolean boo = ftp.retrieveFile("/movies/2017/05/04/019d81e2-d2cf-4540-82d3-1e83493aeea9.mp4", os);
	            System.out.println(boo);
	            // 获取文件列表
//	            FTPFile[] fs = ftp.listFiles();
//	            for (FTPFile ff : fs) {
//	                System.out.println(ff.getName());
//	            }
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
	}
	
//	@Test
	public void test1() {
		System.out.println();
	}
	
	@Test
	public void zipDownLoad() {
		//生成的ZIP文件名为Demo.zip  
        String tmpFileName = "Demo.zip";  
        byte[] buffer = new byte[1024];  
        String strZipPath = "e:/" + tmpFileName;  
        try {  
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(  
                    strZipPath));  
            // 需要同时下载的两个文件result.txt ，source.txt  
            File[] file1 = { new File("e:/"+"测试1.txt"),  
                    new File("e:/"+"测试2.txt"),
                    new File("e:/"+"搞事情.mp4")};  
            for (int i = 0; i < file1.length; i++) {  
                FileInputStream fis = new FileInputStream(file1[i]);  
                out.putNextEntry(new ZipEntry(file1[i].getName()));  
                //设置压缩文件内的字符编码，不然会变成乱码  
                //out.setEncoding("GBK");  
                int len;  
                // 读入需要下载的文件的内容，打包到zip文件  
                while ((len = fis.read(buffer)) > 0) {  
                    out.write(buffer, 0, len);  
                }  
                out.closeEntry();  
                fis.close();  
            }  
            out.close();  
            //this.downFile(getResponse(), tmpFileName);  
            this.downFile(tmpFileName);  
        } catch (Exception e) {  
        }  
	}
	
	public void downFile(String str) {
		try {  
            String path = "e:/" + str;  
            File file = new File(path);  
            if (file.exists()) {  
                InputStream ins = new FileInputStream(path);  
                BufferedInputStream bins = new BufferedInputStream(ins);// 放到缓冲流里面  
                //OutputStream outs = response.getOutputStream();// 获取文件输出IO流  
                OutputStream outs = new FileOutputStream("e:/dddddd.zip");// 获取文件输出IO流  
                BufferedOutputStream bouts = new BufferedOutputStream(outs);  
//                response.setContentType("application/x-download");// 设置response内容的类型  
//                response.setHeader(  
//                        "Content-disposition",  
//                        "attachment;filename="  
//                                + URLEncoder.encode(str, "UTF-8"));// 设置头部信息  
                int bytesRead = 0;  
                byte[] buffer = new byte[8192];  
                // 开始向网络传输文件流  
                while ((bytesRead = bins.read(buffer, 0, 8192)) != -1) {  
                    bouts.write(buffer, 0, bytesRead);  
                }  
                bouts.flush();// 这里一定要调用flush()方法  
                ins.close();  
                bins.close();  
                outs.close();  
                bouts.close();  
            }
        } catch (IOException e) {  
        	
        }  
	}
	
	
}
