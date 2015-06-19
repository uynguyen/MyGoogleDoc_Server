/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bus;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import util.CommonConstants;

/**
 *
 * @author UyNguyen.ITUS
 */
public class MySendingEmail {



    public boolean sendEmailForgotPass(String emailAddress, String newPass) {
        try {

            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");

            // creates a new session with an authenticator
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(CommonConstants._fromUser, CommonConstants._passFromUser);
                }
            };

            Session session = Session.getInstance(properties, auth);

            // creates a new e-mail message
            Message msg = new MimeMessage(session);

            msg.setFrom(new InternetAddress(CommonConstants._fromUser));
            InternetAddress[] toAddresses = {new InternetAddress(emailAddress)};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject(CommonConstants._SubjectEmail);
            msg.setSentDate(new Date());

            String htmlContent = "<h3>Bạn vừa thực hiện thao tác khôi phục mật khẩu từ ứng dụng MY_GOOGLE_DOC</h3>\n"
                    + "<h3>Đây là mật khẩu mới của bạn: </h3>\n"
                    + "<h3 style='color: #fe3636' >"+ newPass +" </h3>\n"
                    + "<h3>Bạn hãy dùng mật khẩu này để đăng nhập và cập nhật lại mật khẩu để đảm bảo sự an toàn cho tài khoản. </h3>\n"
                    + "<h3>Trân trọng</h3 >\n";
            

            msg.setContent(htmlContent,
                    "text/html; charset=UTF-8");

            // sends the e-mail
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
