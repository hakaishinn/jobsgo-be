package dev.ddthanh.jobsgobe.service.impl;

import dev.ddthanh.jobsgobe.model.entity.CareerEntity;
import dev.ddthanh.jobsgobe.payload.response.Response;
import dev.ddthanh.jobsgobe.repository.career.CareerRepository;
import dev.ddthanh.jobsgobe.service.iservice.CareerIService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CareerService implements CareerIService {
    private final CareerRepository careerRepository;
    @Override
    public Response<List<CareerEntity>> getAll(int size) {
        List<CareerEntity> listCareer = new ArrayList<CareerEntity>();
        if(size != 0){
            Pageable pageable = PageRequest.of(0, size);
            listCareer = careerRepository.findTop(pageable);
        } else{
            listCareer = careerRepository.findAll();
        }
        return Response.<List<CareerEntity>>builder()
                .setMessage("Success")
                .setData(listCareer)
                .build();
    }
}
