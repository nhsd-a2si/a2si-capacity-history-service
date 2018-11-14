package com.nhsd.a2si.capacity.historyservice.data.repository.log;


import com.nhsd.a2si.capacity.historyservice.data.model.log.Detail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailRepository extends CrudRepository<Detail, Long> {
}

