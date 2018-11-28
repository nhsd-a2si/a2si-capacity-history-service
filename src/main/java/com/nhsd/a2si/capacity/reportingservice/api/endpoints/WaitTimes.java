package com.nhsd.a2si.capacity.reportingservice.api.endpoints;

import com.nhsd.a2si.capacity.reportingservice.data.model.waittime.WaitTime;
import com.nhsd.a2si.capacity.reportingservice.services.waittime.WaitTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.nhsd.a2si.capacity.reportingservice.data.repository.waittime.WaitTimeServiceRepository.isDate;

@Component
@Path("/wait-times")
public class WaitTimes {

    @Autowired
    private WaitTimeService waitTimeService;

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addWaitTime(@Valid com.nhsd.a2si.capacity.reporting.service.dto.waittime.WaitTime waitTime) {
        WaitTime wt = waitTimeService.store(waitTime);
        return Response
                .created(URI.create("/wait-times/" + wt.getId()))
                .entity(wt)
                .build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getManyCapacityInformationByServiceIDs(
            @HeaderParam("serviceId") List<String> serviceIds,
            @HeaderParam("region") List<String> regions,
            @HeaderParam("provider") List<String> providers,
            @QueryParam("after") String after,
            @QueryParam("before") String before

    ) {
        // Do business logic
        List<WaitTime> results = null;

        if (notNullOrEmpty(serviceIds)) {
            results = new ArrayList<>();
            results.addAll(waitTimeService.getAllByServiceIds(serviceIds));
        }

        if (notNullOrEmpty(regions)) {
            if(results != null){
                results.retainAll(waitTimeService.getAllByRegions(regions));
            } else if(results == null) {
                results = new ArrayList<>();
                results.addAll(waitTimeService.getAllByRegions(regions));
            }
        }

        if (notNullOrEmpty(providers)) {
            if(results != null){
                results.retainAll(waitTimeService.getAllByProviders(providers));
            } else if(results == null) {
                results = new ArrayList<>();
                results.addAll(waitTimeService.getAllByProviders(providers));
            }
        }

        if (nullOrEmpty(serviceIds) && nullOrEmpty(regions) && nullOrEmpty(providers)) {
            results = new ArrayList<>();
            results.addAll(waitTimeService.getAll());
        }

        return Response.ok().entity(results.stream()
                .filter(r -> {
                    try {
                        return isDate(after) ? r.getLastUpdated().after(formatter.parse(after)) : true;
                    } catch (ParseException e) {
                        // This should never happen, see isDate;
                        return true;
                    }
                }).filter(r -> {
                    try {
                        return isDate(before) ? r.getLastUpdated().before(formatter.parse(before)) : true;
                    } catch (ParseException e) {
                        // This should never happen, see isDate;
                        return true;
                    }
                }).collect(Collectors.toList())).build();
    }

    private boolean notNullOrEmpty(List<String> list) {
        return !nullOrEmpty(list);
    }

    private boolean nullOrEmpty(List<String> list) {
        return list == null || list.size() == 0;
    }

    @GET
    @Path("/{serviceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getManyCapacityInformationByServiceIDs(@PathParam("serviceId") String serviceId) {
        List<WaitTime> allByServiceIds = waitTimeService.getAllByServiceIds(Arrays.asList(serviceId));
        if (allByServiceIds.size() == 0) {
            return Response.noContent().build();
        }
        return Response.ok().entity(allByServiceIds).build();
    }


}
