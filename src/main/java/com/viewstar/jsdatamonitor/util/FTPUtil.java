package com.viewstar.jsdatamonitor.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class FTPUtil {
	 /**   
     * Description: ��FTP�����������ļ�   
     *   
     * @param url FTP������hostname   
     * @param port FTP�������˿�   
     * @param username FTP��¼�˺�   
     * @param password FTP��¼����   
     * @param remotePath FTP�������ϵ����·��?   
     * @param fileName Ҫ���ص��ļ���   
     * @param localPath ���غ󱣴浽���ص�·��   
     * @return   
     */    
    public boolean downFile(String url, int port, String username, String password, String remotePath, String fileName, String localPath) {    
        boolean success = false;    
        FTPClient ftp = new FTPClient();    
        try {    
            int reply;
            ftp.setConnectTimeout(30000);
            ftp.connect(url, port);
            //�������Ĭ�϶˿ڣ�����ʹ��ftp.connect(url)�ķ�ʽֱ������FTP������     
            ftp.login(username, password);//��¼     
            reply = ftp.getReplyCode();    
            if (!FTPReply.isPositiveCompletion(reply)) {    
                ftp.disconnect();    
                return success;    
            }
            System.out.println("����FTP�������ɹ�������");
            ftp.changeWorkingDirectory(remotePath);//ת�Ƶ�FTP������Ŀ¼     
            FTPFile[] fs = ftp.listFiles();    
            for(FTPFile ff:fs){    
            	System.out.println("�ļ����ƣ�"+ff.getName());
                if(ff.getName().equals(fileName)){   
                    File localFile = new File(localPath+"/"+ff.getName());    
                    OutputStream is = new FileOutputStream(localFile);     
                    ftp.retrieveFile(ff.getName(), is);    
                    is.close();    
                    System.out.println("���سɹ�������");
                }    
            }    
                
            ftp.logout();    
            success = true;    
        } catch (IOException e) {    
            e.printStackTrace();    
        } finally {    
            if (ftp.isConnected()) {    
                try {    
                    ftp.disconnect();    
                } catch (IOException ioe) {    
                }    
            }    
        }    
        return success;    
    } 
}
