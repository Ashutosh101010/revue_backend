//package org.aurd.user.utils;
//
//import io.quarkus.mailer.reactive.ReactiveMailer;
//import org.aurd.user.constant.Constants;
//
//import javax.inject.Inject;
//import javax.mail.Message;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//import java.util.Properties;
//
//public class Mailer{
//
//    @Inject
//    ReactiveMailer reactiveMailer;
//    String email;
//    String userId;
//    String token;
//
//    public Mailer(String email, String userId, String token) {
//        this.email = email;
//        this.userId = userId;
//        this.token = token;
//    }
//
//    void sendEmail(String email,String userId,String token)
//    {
//        try {
////            String from= Constants.GMAIL_USER_NAME;
////            String pass=Constants.GMAIL_PASSWORD;
////
////            Properties props = System.getProperties();
////            String host = "smtp.gmail.com";
////            props.put("mail.smtp.starttls.enable", "true");
////            props.put("mail.smtp.host", host);
////            props.put("mail.smtp.user", from);
////            props.put("mail.smtp.password", pass);
////            props.put("mail.smtp.port", "587");
////            props.put("mail.smtp.auth", "true");
////
//
//
////            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
////                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
////                    return new javax.mail.PasswordAuthentication(from, pass);
////                }
////            });
//
//            Message msg = new MimeMessage(session);
//            msg.setFrom(new InternetAddress(from));
//            msg.addRecipient(Message.RecipientType.TO,
//                    new InternetAddress(email, ""));
//            msg.setSubject("Verify Email");
//            msg.setText("Please click below to verify your email \n https://revue-app.com/#/verifyEmail?token="+token+"&userId="+userId);
//            Transport.send(msg);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//}
