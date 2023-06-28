package dev.ddthanh.jobsgobe.service.iservice;

import dev.ddthanh.jobsgobe.payload.request.EmailRequest;

public interface EmailSenderIService {
    public void sendEmail(EmailRequest request);
}
