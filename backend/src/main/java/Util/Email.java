package Util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class Email
{
    private String destEmail = "";
    private String subject = "";
    private String type = "";
    private String token = "";

    public Email(String destEmail, String subject, String type, String token){
        this.destEmail = destEmail;
        this.subject = subject;
        this.type = type;
        this.token = token;
    }

    public void send()
    {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                try {
                    throw new Exception();
                } catch (Exception e) {
                    System.out.println(EmailConfig.EMAIL.value + "  " + EmailConfig.PASSWORD.value);
                }

                return new PasswordAuthentication(EmailConfig.EMAIL.value, EmailConfig.PASSWORD.value);
            }
        });

        Message message = new MimeMessage(session);
        try
        {
            message.setFrom(new InternetAddress(EmailConfig.EMAIL.value));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destEmail));
            message.setSubject(subject);

            String msg = getMessage();

            MimeBodyPart mimeBodyPart = new MimeBodyPart();
            mimeBodyPart.setContent(msg, "text/html; charset=utf-8");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPart);

            message.setContent(multipart);

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private String getMessage()
    {
        if (type.equals("Verification"))
            return "Hello, \n Please click on the following link to verify your email! " +
                    "\n\n http://localhost:8080/Assignment1_war/verification/" + token;
        else
            return "Hello, \n Please click on the following link to change your password!";
    }
}
