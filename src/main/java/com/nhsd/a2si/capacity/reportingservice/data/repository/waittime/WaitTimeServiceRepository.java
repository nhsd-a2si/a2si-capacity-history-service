package com.nhsd.a2si.capacity.reportingservice.data.repository.waittime;

import com.nhsd.a2si.capacity.reportingservice.data.model.waittime.WaitTime;

import java.util.List;

public interface WaitTimeServiceRepository {

    static boolean isDate(String d){
        return d != null && d.matches("(?im)^\\d{4}(?:-\\d{2}){2}\\s(\\d{2}:){2}\\d{2}$");
    }

    List<WaitTime> findAllByIdServiceId(List<String> ids);
}
