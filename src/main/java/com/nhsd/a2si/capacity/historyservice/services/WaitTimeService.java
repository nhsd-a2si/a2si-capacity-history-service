package com.nhsd.a2si.capacity.historyservice.services;

import com.nhsd.a2si.capacity.historyservice.data.ServiceRepository;
import com.nhsd.a2si.capacity.historyservice.data.WaitTimeRepository;
import com.nhsd.a2si.capacity.historyservice.data.model.Service;
import com.nhsd.a2si.capacity.historyservice.data.model.WaitTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@org.springframework.stereotype.Service
public class WaitTimeService {

    @Autowired
    private WaitTimeRepository waitTimeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    /**
     * Transforms an API WaitTime into a ORM WaitTime with its corresponding service.
     *
     * @param swt The Service Wait time Object
     * @return The newly created wait time record.
     */
    public WaitTime store(com.nhsd.a2si.capacity.historyservice.api.model.WaitTime swt){
        Service service = getOrCreateServiceAndCheckTheNameMatchesOrUpdateTheName(swt);
        WaitTime waitTime = new WaitTime();
        waitTime.setWaitTimeInMinutes(swt.getWaitTimeInMinutes());
        waitTime.setService(service);
        waitTime.setProvider(swt.getProvider().getName());
        waitTime.setRegion(swt.getProvider().getName());
        waitTime.setLastUpdated(swt.getUpdated());
        return waitTimeRepository.save(waitTime);
    }

    private Service getOrCreateServiceAndCheckTheNameMatchesOrUpdateTheName(com.nhsd.a2si.capacity.historyservice.api.model.WaitTime swt) {
        return checkServiceNameMatchOrUpdate(getOrCreateService(swt.getService().getId(), swt.getService().getName()), swt.getService().getName());
    }

    private Service getOrCreateService(String id, String name) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            return service.get();
        }
        Service newService = new Service();
        newService.setId(id);
        newService.setName(name);
        return serviceRepository.save(newService);
    }

    private Service checkServiceNameMatchOrUpdate(Service service, String name) {
        if(service.getName().equalsIgnoreCase(name)){
            return service;
        }
        service.setName(name);
        return serviceRepository.save(service);
    }
}
