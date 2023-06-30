package dev.ddthanh.jobsgobe.service.impl.schedule;

import dev.ddthanh.jobsgobe.common.constants.TypeJob;
import dev.ddthanh.jobsgobe.model.entity.JobEntity;
import dev.ddthanh.jobsgobe.payload.response.job.JobResponse;
import dev.ddthanh.jobsgobe.repository.job.JobRepository;
import dev.ddthanh.jobsgobe.service.impl.job.JobService;
import dev.ddthanh.jobsgobe.service.iservice.ScheduleIService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService implements ScheduleIService {
    private final JobRepository jobRepository;

    @Override
    @Scheduled(cron = "0 0 */6 * * *")
    public void cronJob() {
        Date now = new Date();
        List<JobEntity> listJob = jobRepository.findAll();
        for (JobEntity job : listJob) {
            if(now.after(job.getExpiredAt()) && !job.getStatus().equals(TypeJob.EXPIRED)){
                job.setStatus(TypeJob.EXPIRED);
                jobRepository.save(job);
            }
        }
    }
}
