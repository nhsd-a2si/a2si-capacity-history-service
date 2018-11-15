package com.nhsd.a2si.capacity.reportingservice.data.repository.log;

import com.nhsd.a2si.capacity.reportingservice.data.model.log.Header;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HeaderRepository extends CrudRepository<Header, Long> {
	List<Header> findFirst100ByOrderByIdDesc();
}
