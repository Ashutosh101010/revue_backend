package org.aurd.user.controllers;


import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.aurd.user.constant.Constants;
import org.aurd.user.modal.request.SendOtpRequest;
import org.aurd.user.modal.response.SendOtpResponse;
import org.bson.Document;

import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import static org.aurd.MongoService.authCollection;


@Path("/sendOtp")
public class SendOtpController {

    @Inject
    Mailer mailer;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public SendOtpResponse sendOtp(SendOtpRequest request)
    {
        String otp= new DecimalFormat("000000").format(new Random().nextInt(999999));
        Document document=new Document();
        document.append("createdAt",new Date());
        document.append("otp",otp);
        document.append("email",request.getEmail());

        String messageBody="Your Otp is "+otp;
        String[] mail={request.getEmail()};
        mailer.send(Mail.withText(request.getEmail(),Constants.GMAIL_SUBJECT,messageBody));
//
//        sendFromGMail(Constants.GMAIL_USER_NAME,Constants.GMAIL_PASSWORD, mail,Constants.GMAIL_SUBJECT,messageBody);
        authCollection.insertOne(document);

        SendOtpResponse response=new SendOtpResponse();
        response.setStatus(Constants.STATUS_SUCCESS);
        response.setErrorCode(0);
        response.setMessage("Otp Sent SuccessFully");

        return response;

    }

    private static void sendFromGMail(String from, String pass, String[] to, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

