package com.nhsd.a2si.capacity.historyservice.data.repository.waittime;

import com.nhsd.a2si.capacity.historyservice.data.model.waittime.Service;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends CrudRepository<Service, String> {

}
