package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.payload.request.EmailRequest;
import dev.ddthanh.jobsgobe.service.iservice.EmailSenderIService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    }
}
