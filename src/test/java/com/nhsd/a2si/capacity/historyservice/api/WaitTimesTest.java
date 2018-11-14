package com.nhsd.a2si.capacity.historyservice.api;

import com.nhsd.a2si.capacity.historyservice.api.model.waittime.Provider;
import com.nhsd.a2si.capacity.historyservice.api.model.waittime.Service;
import com.nhsd.a2si.capacity.historyservice.api.model.waittime.WaitTime;
import com.nhsd.a2si.capacity.historyservice.data.ServiceRepository;
import com.nhsd.a2si.capacity.historyservice.data.repository.waittime.WaitTimeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.ws.rs.core.Response;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WaitTimesTest {

    @Autowired
    private WaitTimeRepository waitTimeRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private WaitTimes waitTimes;

    @Test
    public void create(){

        WaitTime waitTime = new WaitTime();
        waitTime.setUpdated(new Date());
        waitTime.setWaitTimeInMinutes(30);
        Provider provider = new Provider();
        provider.setName("Henbary");
        provider.setRegion("Devon");
        waitTime.setProvider(provider);
        Service service = new Service();
        service.setId("ABC123");
        service.setName("Tea Service");
        waitTime.setProvider(provider);

        Response response = waitTimes.addWaitTime(waitTime);
        assertThat(response.getStatus(), is(201));
        assertThat(response.getLocation(), is("boo"));

    }

}