package com.nhsd.a2si.capacity.reportingservice.data.repository.waittime;


import com.nhsd.a2si.capacity.reportingservice.data.model.waittime.WaitTime;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

public class WaitTimeServiceRepositoryImpl implements WaitTimeServiceRepository {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<WaitTime> findAllByIdServiceId(List<String> ids) {
        TypedQuery<WaitTime> query = entityManager.createQuery("SELECT wt FROM WaitTime wt WHERE wt.service.id IN (:ids)", WaitTime.class);
        query.setParameter("ids", ids);
        return query.getResultList();
    }

    @Override
    public List<WaitTime> getAllByRegion(List<String> region) {
        TypedQuery<WaitTime> query = entityManager.createQuery("SELECT wt FROM WaitTime wt WHERE wt.service.region IN (:region)", WaitTime.class);
        query.setParameter("region", region);
        return query.getResultList();
    }
}
