package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.payload.request.EmailRequest;
import dev.ddthanh.jobsgobe.service.iservice.EmailSenderIService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService implements EmailSenderIService {
    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(EmailRequest request) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("thanhteo2903@gmai.com");
        message.setTo(request.getToEmail());
        message.setText(request.getBody());
        message.setSubject(request.getSubject());

        mailSender.send(message);

//        MimeMessage mimeMessage = mailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
//        String htmlMsg = "<p>Chào bạn,</p><p><br></p><p>Bạn đã ứng tuyển vị trí Fresher của công ty ABC.</p><p><br></p><p>Trân trọng.</p>";
//        try {
//            helper.setText(htmlMsg, true); // Use this or above line.
//            helper.setTo(request.getToEmail());
//            helper.setSubject(request.getSubject());
//            helper.setFrom("thanhteo2903@gmail.com");
//
//            mailSender.send(mimeMessage);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
    }
}
