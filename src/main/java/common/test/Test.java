package common.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Test {

	//获取文件指纹
	public static void main(String[] args) {
		InputStream is = null;
		try {
			is = new FileInputStream("e:/zip/k2.zip");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String md5 = getMD5(is, "md5");
		System.out.println(md5);
	}
	
	
	private static String getMD5(InputStream is, String type) {
		String result = null;
		 try {
			 MessageDigest md = MessageDigest.getInstance(type);
			 byte[] buffer = new byte[8192];
			 int length = -1;
			 System.out.println("开始算");
			 while ((length = is.read(buffer)) != -1) {
			     md.update(buffer, 0, length);
			 }
			 System.out.println("算完了");
			 
			 
			 byte[] digest = md.digest();
			 
			 StringBuffer strBuffer = new StringBuffer();
			  for (int i = 0; i < digest.length; i++) {
			   strBuffer.append(Integer.toHexString(0xff & digest[i]));
			  }
			  result = strBuffer.toString();
			 
		} catch (IOException ex) {
			
		} catch (NoSuchAlgorithmException ex) {
			
		} finally {
		    try {
		    	is.close();
		    } catch (IOException ex) {
		    	
		    }
		}
		
		return result;
	}
	
}
