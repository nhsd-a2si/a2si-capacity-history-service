package com.nhsd.a2si.capacity.reportingservice.services.log;



import com.nhsd.a2si.capacity.reporting.service.dto.log.Detail;
import com.nhsd.a2si.capacity.reporting.service.dto.log.Header;

import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;


public class ApiDataLogMapper {

    // Convert DB model to API model.
    public static Header map(com.nhsd.a2si.capacity.reportingservice.data.model.log.Header header) {
        Header h = new Header();
        h.setId(header.getId());
        h.setAction(header.getAction());
        h.setComponent(header.getComponent());
        h.setDetails(header.getDetails().stream().map(ApiDataLogMapper::map).collect(toList()));
        h.setEndpoint(header.getEndpoint());
        h.setHashcode(header.getHashcode());
        h.setId(header.getId());
        h.setTimestamp(header.getTimestamp());
        h.setUserId(header.getUserId());
        return h;
    }

    // Convert DB model to API model.
    public static Detail map(com.nhsd.a2si.capacity.reportingservice.data.model.log.Detail detail) {
        Detail d = new Detail();
        d.setAgeInMinutes(detail.getAgeInMinutes());
        d.setServiceId(detail.getServiceId());
        d.setTimestamp(detail.getTimestamp());
        d.setWaitTimeInMinutes(detail.getWaitTimeInMinutes());
        return d;
    }

    // Convert API model to DB model.
    public static com.nhsd.a2si.capacity.reportingservice.data.model.log.Header map(com.nhsd.a2si.capacity.reporting.service.dto.log.Header header) {
        com.nhsd.a2si.capacity.reportingservice.data.model.log.Header h = new com.nhsd.a2si.capacity.reportingservice.data.model.log.Header();
        h.setAction(header.getAction());
        h.setComponent(header.getComponent());
        h.setEndpoint(header.getEndpoint());
        h.setDetails(header.getDetails().stream().map(i -> ApiDataLogMapper.map(i, h)).collect(Collectors.toList()));
        h.setHashcode(header.getHashcode());
        h.setTimestamp(header.getTimestamp());
        h.setUserId(header.getUserId());
        return h;
    }

    // Convert API model to DB model.
    public static com.nhsd.a2si.capacity.reportingservice.data.model.log.Detail map(com.nhsd.a2si.capacity.reporting.service.dto.log.Detail detail, com.nhsd.a2si.capacity.reportingservice.data.model.log.Header header) {
        com.nhsd.a2si.capacity.reportingservice.data.model.log.Detail d = new com.nhsd.a2si.capacity.reportingservice.data.model.log.Detail();
        d.setAgeInMinutes(detail.getAgeInMinutes());
        d.setServiceId(detail.getServiceId());
        d.setTimestamp(detail.getTimestamp());
        d.setWaitTimeInMinutes(detail.getWaitTimeInMinutes());
        d.setHeader(header);
        return d;
    }

}
