package UserManagement;

import Storage.Config;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;

public class LocalGmail
{
    private String destEmail = "";
    private String subject = "";
    private String type = "";
    private String token = "";

    public LocalGmail(String destEmail, String subject, String type){
        this.destEmail = destEmail;
        this.subject = subject;
        this.type = type;
        this.token = generateToken();
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
            return "Hello, <br> Please click on the following link to verify your email! " +
                    "<br><br> http://localhost:3000/verification/" + token;
        else
            return "Hello, <br> Please click on the following link to change your password!" +
                    "<br><br> http://localhost:3000/changepassword/" + token;
    }

    private String generateToken()
    {
        StringBuilder str = new StringBuilder();
        String allowedChars = Config.ID_ALLOWED_CHARACTERS.value.toString();
        for(int i = 0 ; i < 16; i++){
            int index = ThreadLocalRandom.current().nextInt(allowedChars.length());
            str.append(allowedChars.charAt(index));
        }

        return str.toString();
    }

    public String getToken() {
        return this.token;
    }
}
