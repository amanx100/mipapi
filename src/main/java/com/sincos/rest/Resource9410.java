package com.sincos.rest;


import com.sincos.rest.pojo.Feedback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/mipapi/9410/")
public class Resource9410 {

    @GET
    @Path("/{printSize}/{printMsg}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response setPrinterByGET(@PathParam("printSize") int printSize, @PathParam("printMsg") String printMsg) {

        System.out.println(printSize);
        System.out.println(printMsg);

        Feedback feedback = new Feedback();
        feedback.setMsgsize(printSize);
        feedback.setMsg(printMsg);
        feedback.setStatus("yes");

        return doResponse(feedback);
    }

    @POST
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response setPrinterByPOST(@FormParam("printSize") int printSize, @FormParam("printMsg") String printMsg) {

        System.out.println(printSize);
        System.out.println(printMsg);

        Feedback feedback = new Feedback();
        feedback.setMsgsize(printSize);
        feedback.setMsg(printMsg);
        feedback.setStatus("hei");

        return doResponse(feedback);
    }


    private Response doResponse(Object obj) {
        return Response.ok()
                .entity(obj)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

}
