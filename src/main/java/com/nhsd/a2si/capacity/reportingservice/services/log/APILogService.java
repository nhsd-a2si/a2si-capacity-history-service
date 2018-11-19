package com.nhsd.a2si.capacity.reportingservice.services.log;

import com.nhsd.a2si.capacity.reporting.service.dto.log.Header;
import com.nhsd.a2si.capacity.reporting.service.dto.log.Detail;
import com.nhsd.a2si.capacity.reportingservice.data.repository.log.DetailRepository;
import com.nhsd.a2si.capacity.reportingservice.data.repository.log.HeaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class APILogService {

    @Autowired
    private HeaderRepository headerRepository;

    @Autowired
    private DetailRepository detailLogRepository;

    public Header saveHeader(Header header){
        return ApiDataLogMapper.map(headerRepository.save(ApiDataLogMapper.map(header)));
    }

    public Detail saveDetails(Detail detail, long headerId){
        if(headerRepository.findById(headerId).isPresent()) {
            return ApiDataLogMapper.map(detailLogRepository.save(ApiDataLogMapper.map(detail, headerRepository.findById(headerId).get())));
        }
        return null;
    }

    public List<Header> getAllHeaderLogs() {
        return StreamSupport.stream(headerRepository.findAll().spliterator(), false).map(ApiDataLogMapper::map).collect(Collectors.toList());
    }

    public List<Header> getLatestHeaderLogs() {
        return headerRepository.findFirst100ByOrderByIdDesc().stream().map(ApiDataLogMapper::map).collect(Collectors.toList());
    }
}
