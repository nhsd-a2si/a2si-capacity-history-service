package com.nhsd.a2si.capacity.reportingservice.data.repository.waittime;

import com.nhsd.a2si.capacity.reportingservice.data.model.waittime.WaitTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitTimeRepository extends CrudRepository<WaitTime, Long> {
}
