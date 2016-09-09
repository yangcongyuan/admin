package com.etnlgravtnl.common.channel.email;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {

	private final static String host = "smtp.ym.163.com";
	private final static String from = "guozheng@fundview.cn";
	private final static String user = "guozheng@fundview.cn";
	private final static String password = "guozheng123";
	private static  String nick = "360fengyu";//发送者别名

	/**
	 * 发送
	 * @param email     邮箱地址
	 * @param subject   标题
	 * @param content   内容
     */
	public static void send(String email, String subject, String content) {
		Properties props = new Properties();
		props.put("mail.smtp.host", host); // 指定SMTP服务器
		props.put("mail.smtp.auth", "true"); // 指定是否需要SMTP验证
		try {
			Session mailSession = Session.getDefaultInstance(props);
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(nick+" <"+from+">")); // 发件人
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(email)); // 收件人
			message.setSubject(subject); // 邮件主题
			message.setText(content); // 邮件内容
			message.saveChanges();
			Transport transport = mailSession.getTransport("smtp");
			transport.connect(host, user, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) {
		new SendMail().send( "296734078@qq.com", "标题", "HelloWold!");
		System.out.println("发送成功！");
	}
}