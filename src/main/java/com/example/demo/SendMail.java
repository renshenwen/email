package com.example.demo;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.FileOutputStream;
import java.util.Properties;

//javax mail 未

public class SendMail {
	
	 public static void main(String[] args) throws Exception {

		 Properties props = new Properties();
		 
		    // 开启debug调试
		    props.setProperty("mail.debug", "true");
		    // 发送服务器需要身份验证
		    props.setProperty("mail.smtp.auth", "true");
		    // 设置邮件服务器主机名
		    props.setProperty("mail.host", "smtp.qq.com");
		    // 发送邮件协议名称
		    props.setProperty("mail.transport.protocol", "smtp");
		 
		    //ssl安全
		    MailSSLSocketFactory sf = new MailSSLSocketFactory();
		    sf.setTrustAllHosts(true);
		    props.put("mail.smtp.ssl.enable", "true");
		    props.put("mail.smtp.ssl.socketFactory", sf);
		 
		    Session session = Session.getInstance(props);
		 
		    Message msg = new MimeMessage(session);
	
		    Transport transport = session.getTransport();
		    //授权码
		    transport.connect("smtp.qq.com", "348960743@qq.com", "ezkubigpmfhmcajb");
		   
			Message message = createImageMail(session);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
}





public static MimeMessage createImageMail(Session session) throws Exception {
    //创建邮件
    MimeMessage message = new MimeMessage(session);
    // 设置邮件的基本信息
    //发件人
    message.setFrom(new InternetAddress("348960743@qq.com"));
    //收件人
    message.setRecipient(Message.RecipientType.TO, new InternetAddress("3343068522@qq.com"));
    //邮件标题
    message.setSubject("带图片的邮件");

    // 准备邮件数据
    // 准备邮件正文数据
    MimeBodyPart text = new MimeBodyPart();
    text.setContent("这是一封邮件正文带图片<img src='cid:xxx.jpg'>的邮件", "text/html;charset=UTF-8");
    // 准备图片数据
/*    MimeBodyPart image = new MimeBodyPart();
    DataHandler dh = new DataHandler(new FileDataSource("src\\1.jpg"));
    image.setDataHandler(dh);
    image.setContentID("xxx.jpg");*/
    // 描述数据关系
    MimeMultipart mm = new MimeMultipart();
    mm.addBodyPart(text);
  //  mm.addBodyPart(image);
    mm.setSubType("related");

    message.setContent(mm);
    message.saveChanges();
    //将创建好的邮件写入到E盘以文件的形式进行保存
    message.writeTo(new FileOutputStream("D:\\ImageMail.eml"));
    //返回创建好的邮件
    return message;
}
	 }