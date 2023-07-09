package dev.ddthanh.jobsgobe.controller;

import dev.ddthanh.jobsgobe.model.entity.ApplyEntity;
import dev.ddthanh.jobsgobe.model.entity.AttachmentsEntity;
import dev.ddthanh.jobsgobe.payload.request.apply.BaseApply;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.service.impl.attachments.AttachmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AttachmentsController {
    private final AttachmentsService attachmentsService;
    @PostMapping("/attachments/candidate/{id}")
    @Secured("CANDIDATE")
    public Response<AttachmentsEntity> create(@PathVariable Long id, @RequestBody AttachmentsEntity request) {
        Response<AttachmentsEntity> attachmentsEntityResponse = attachmentsService.create(id,request);
        return attachmentsEntityResponse;
    }

    @PutMapping("/attachments/{id}")
    @Secured("CANDIDATE")
    public Response<AttachmentsEntity> update(@PathVariable Long id, @RequestBody AttachmentsEntity request) {
        Response<AttachmentsEntity> attachmentsEntityResponse = attachmentsService.update(id, request);
        return attachmentsEntityResponse;
    }

    @GetMapping("/attachments/candidate/{id}")
    @Secured("CANDIDATE")
    public Response<List<AttachmentsEntity>> getAttachmentsById(@PathVariable Long id) {
        Response<List<AttachmentsEntity>> response = attachmentsService.getAttachmentsByCandidateId(id);
        return response;
    }

    @DeleteMapping("/attachments/{id}")
    @Secured("CANDIDATE")
    public void deleteById(@PathVariable Long id){
        attachmentsService.deleteById(id);
    }
}
