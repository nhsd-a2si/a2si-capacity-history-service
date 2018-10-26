package com.nhsd.a2si.capacity.historyservice.data;

import com.nhsd.a2si.capacity.historyservice.data.model.WaitTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WaitTimeRepository extends CrudRepository<WaitTime, Long> {
}
