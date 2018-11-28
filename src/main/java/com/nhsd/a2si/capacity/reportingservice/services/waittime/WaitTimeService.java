package com.nhsd.a2si.capacity.reportingservice.services.waittime;

import com.nhsd.a2si.capacity.reportingservice.data.repository.waittime.ServiceRepository;
import com.nhsd.a2si.capacity.reportingservice.data.repository.waittime.WaitTimeRepository;
import com.nhsd.a2si.capacity.reportingservice.data.model.waittime.Service;
import com.nhsd.a2si.capacity.reportingservice.data.model.waittime.WaitTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
    public WaitTime store(com.nhsd.a2si.capacity.reporting.service.dto.waittime.WaitTime swt){
        Service service = getOrCreateServiceAndCheckTheDetailsMatchesOrUpdateTheDetails(swt);
        WaitTime waitTime = new WaitTime();
        waitTime.setWaitTimeInMinutes(swt.getWaitTimeInMinutes());
        waitTime.setService(service);
        waitTime.setProvider(swt.getProvider().getName());
        waitTime.setLastUpdated(swt.getUpdated());
        return waitTimeRepository.save(waitTime);
    }

    private Service getOrCreateServiceAndCheckTheDetailsMatchesOrUpdateTheDetails(com.nhsd.a2si.capacity.reporting.service.dto.waittime.WaitTime swt) {
        return checkRegionNameMatchOrUpdate(checkServiceNameMatchOrUpdate(getOrCreateService(swt.getService().getId(), swt.getService().getName(), swt.getService().getRegion()), swt.getService().getName()), swt.getService().getRegion());
    }

    private Service getOrCreateService(String id, String name, String region) {
        Optional<Service> service = serviceRepository.findById(id);
        if (service.isPresent()) {
            return service.get();
        }
        Service newService = new Service();
        newService.setId(id);
        newService.setName(name);
        newService.setRegion(region);
        return serviceRepository.save(newService);
    }

    private Service checkRegionNameMatchOrUpdate(Service service, String region) {
        if(service.getRegion().equalsIgnoreCase(region)){
            return service;
        }
        service.setRegion(region);
        return serviceRepository.save(service);
    }

    private Service checkServiceNameMatchOrUpdate(Service service, String name) {
        if(service.getName().equalsIgnoreCase(name)){
            return service;
        }
        service.setName(name);
        return serviceRepository.save(service);
    }


    public Iterable<WaitTime> getAll(List<Long> ids){
        return waitTimeRepository.findAllById(ids);
    }

    public List<WaitTime> getAll(){
         return StreamSupport.stream(waitTimeRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<WaitTime> getAllByServiceIds(List<String> ids){
        return waitTimeRepository.findAllByIdServiceId(ids);
    }

    public List<WaitTime> getAllByRegions(List<String> regions) {
        return waitTimeRepository.getAllByRegion(regions);
    }

    public List<WaitTime> getAllByProviders(List<String> providers) {
        return waitTimeRepository.getAllByProvider(providers);
    }


}
