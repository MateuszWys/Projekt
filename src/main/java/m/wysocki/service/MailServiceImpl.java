package m.wysocki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service("emailService")
public class MailServiceImpl implements MailService {

    private JavaMailSender mailSender;

    @Autowired
    public MailServiceImpl(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(String sender, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("testkapj1@gmail.com");
        message.setTo(sender);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
    }
}
