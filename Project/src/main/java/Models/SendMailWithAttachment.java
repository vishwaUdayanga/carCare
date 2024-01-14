package Models;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.util.Properties;

public class SendMailWithAttachment {

    public void send(String recepient,String adminSubject, String adminMessage) throws IOException {
        String to = recepient; // to address. It can be any like gmail, hotmail etc.
        final String from = "vishwaudayanga310@gmail.com"; // from address. As this is using Gmail SMTP.
        final String password = "vjryrhgnkblycqih"; // password for from mail address.

        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

//        prop.put("mail.smtp.socketFactory.port", "587");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(adminSubject);

            String msg = adminMessage;

            message.setText(msg);

            Transport.send(message);

            System.out.println("Mail successfully sent..");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}