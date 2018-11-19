package com.nhsd.a2si.capacity.reportingservice.api.endpoints;

import com.nhsd.a2si.capacity.reporting.service.dto.log.Detail;
import com.nhsd.a2si.capacity.reporting.service.dto.log.Header;
import com.nhsd.a2si.capacity.reportingservice.services.log.APILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
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
    public Response postLogHeader(@Valid Header header) {
        Header h = logOutputService.saveHeader(header);
        return Response
                .created(URI.create("/log/" + h.getId()))
                .entity(h)
                .build();
    }

    @POST
    @Path("/{header-id}/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postLogDetail(@Valid Detail detail, @PathParam("header-id") long header) {
        Detail d = logOutputService.saveDetails(detail, header);
        if(d != null) {
            return Response
                    .created(URI.create("/log/" + header + "/" + d.getId()))
                    .entity(d)
                    .build();
        }
        // Error response
        return Response.status(Response.Status.NOT_FOUND.getStatusCode(), "Resource not found: /log/" + header + "/").build();
    }



}
