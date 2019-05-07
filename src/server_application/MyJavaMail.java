package server_application;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public abstract class MyJavaMail {

    public static void sendMail(Email email, String password){

        final String userMail = "philipelunacc@gmail.com";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smpt.port", "465");

        Session session = Session.getDefaultInstance(properties,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userMail, password);
                    }
                });
        session.setDebug(true);

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(userMail));

            Address[] addresses = InternetAddress
                    .parse(email.getAdress());
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSubject("Teste Automaçao de Emails 9");
            message.setText("Email eviado usando API JavaMail.");

            Transport.send(message);

            System.out.println("Mensagem com o Titulo" + message.getSubject() + " foi enviada");

        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
}
