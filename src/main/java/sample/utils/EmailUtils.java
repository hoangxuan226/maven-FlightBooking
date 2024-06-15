/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author phamx
 */
public class EmailUtils {
    static Logger logger = Logger.getLogger(EmailUtils.class.getName());

    public EmailUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String fromEmail = "hpx0108@gmail.com";
    private static final String password = "fvqy ouya cvdi okid";
    private static final String fromDisplayName = "HVH Airways";

    public static boolean sendEmail(String toEmail, String subject, String body) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS

        //create Authenticator object to pass in Session.getInstance argument
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
        Session session = Session.getInstance(props, auth);

        boolean check = false;
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress(fromEmail, fromDisplayName));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            logger.info("Message is ready");
            Transport.send(msg);

            logger.info("EMail Sent Successfully!!");
            check = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return check;
    }

    public static boolean sendRegisterSuccessEmail(String lastname, String firstname, String email, String memberNumber) {
        String body;
        body = "Dear " + lastname + " " + firstname + ",\n";
        body += "Thank you for choosing and flying with HVH Airways!\n";
        body += "Your membership number is " + memberNumber + ".\n";
        body += "To accumulate points, please inform us of your membership number every time you make a reservation with HVH Airways.\n";
        return sendEmail(email, "Successfully registered - HVH Airways", body);
    }

    public static boolean sendBookingSuccessEmail(String lastname, String firstname, String email, String reservationCode) {
        String body;
        body = "Dear " + lastname + " " + firstname + ",\n";
        body += "Thank you for choosing and flying with HVH Airways!\n";
        body += "Your reservation code is " + reservationCode + ".\n";
        return sendEmail(email, "Successfully Booking - HVH Airways", body);
    }

}
