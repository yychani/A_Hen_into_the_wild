package inSeongJo.aHenIntoTheWild.model.dao;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import inSeongJo.aHenIntoTheWild.controller.UserManager;

public class PasswordEmail {
	public void repasswordEmail(String email, String randomPassword) {

		//보내는 사람 정보
		String host = "smtp.naver.com";
		final String user = "ajfodc";
		final String password = "duswnkyj99~!";
		
		//이메일 받는 유저 정보
		String userEmail = email;
		//String userNickName = nickName;

		// SMTP 서버 정보를 설정
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 587);
		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		
		
		// Compose the message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(userEmail));

			// 메일 제목
			message.setSubject(" <마당을 나온 암탉> 임시비밀번호 안내입니다.");

			// 메일 내용
			message.setText("안녕하세요. \r\n" + "\r\n" + "<마당을 나온 암탉> 임시 비밀번호는 다음과 같습니다. " + randomPassword);

			// send the message
			Transport.send(message);
			System.out.println("임시 비밀번호 변경 이메일 보내기 완료");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	

}
