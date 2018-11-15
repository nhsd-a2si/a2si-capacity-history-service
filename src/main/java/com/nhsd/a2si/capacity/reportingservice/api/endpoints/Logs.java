package com.nhsd.a2si.capacity.reportingservice.api.endpoints;

import com.nhsd.a2si.capacity.reportingservice.api.model.log.Detail;
import com.nhsd.a2si.capacity.reportingservice.api.model.log.Header;
import com.nhsd.a2si.capacity.reportingservice.services.log.APILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("/log")
public class Logs {

    @Autowired
    private APILogService logOutputService;

    @GET
    @Path("/get-all")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Header> getAll(){
        return logOutputService.getAllHeaderLogs();
    }

    @GET
    @Path("/get-latest")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Header> getLatest(){
        return logOutputService.getLatestHeaderLogs();
    }

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Header postLogHeader(String action, String endpoint, String username) {
       /* HeaderLog headerLog = new HeaderLog();
        headerLog.setAction(action);
        headerLog.setComponent("capacity-service");
        headerLog.setEndpoint(endpoint);
        headerLog.setHashcode(null);
        headerLog.setTimestamp(new Date());
        headerLog.setUserId(username);
        logService.saveHeader(headerLog);
        return headerLog;*/
       return null;
    }

    @POST
    @Path("/{header-id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Header postLogDetail(@Valid Detail detail, @PathParam("header-id") String header) {
       /* HeaderLog headerLog = new HeaderLog();
        headerLog.setAction(action);
        headerLog.setComponent("capacity-service");
        headerLog.setEndpoint(endpoint);
        headerLog.setHashcode(null);
        headerLog.setTimestamp(new Date());
        headerLog.setUserId(username);
        logService.saveHeader(headerLog);
        return headerLog;*/
        return null;
    }



}
