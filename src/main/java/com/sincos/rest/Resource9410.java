package com.sincos.rest;


import com.sincos.app.GlobalData;
import com.sincos.imaje.lib.ComENet;
import com.sincos.imaje.lib.NetworkInterface9410;
import com.sincos.rest.pojo.Feedback;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Path("/mipapi/9410/")
public class Resource9410 {
    NetworkInterface9410 networkInterface9410 = new NetworkInterface9410(new ComENet(GlobalData.getPrinterIp(), Integer.parseInt(GlobalData.getPrinterPort()), 10,10));

    @GET
    @Path("/{printSize}/{printMsg}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response setPrinterByGET(@PathParam("printSize") String printSize, @PathParam("printMsg") String printMsg) {

        System.out.println(printSize);
        System.out.println(printMsg);

        Feedback feedback = new Feedback();
        feedback.setSize(printSize);
        feedback.setMsg(printMsg);


        try {
            networkInterface9410.connect();
            String stat = networkInterface9410.selectAJobByHisName(printSize);
            feedback.setSizeStatus(stat);
            Map<Integer,String> extData = new HashMap<>();
            extData.put(1, printMsg);
            Byte ack = networkInterface9410.sendExternalVariables(extData);
            feedback.setMsgStatus(ack.toString());
            networkInterface9410.disconnect();
        } catch (IOException e) {
            GlobalData.viewStatus.setText("Printer not available");
            e.printStackTrace();
        }

        return doResponse(feedback);
    }

    @POST
    @Path("/")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response setPrinterByPOST(@FormParam("printSize") String printSize, @FormParam("printMsg") String printMsg) {

        System.out.println(printSize);
        System.out.println(printMsg);

        Feedback feedback = new Feedback();
        feedback.setSize(printSize);
        feedback.setMsg(printMsg);


        try {
            networkInterface9410.connect();
            String stat = networkInterface9410.selectAJobByHisName(printSize);
            feedback.setSizeStatus(stat);
            Map<Integer,String> extData = new HashMap<>();
            extData.put(1, printMsg);
            Byte ack = networkInterface9410.sendExternalVariables(extData);
            feedback.setMsgStatus(ack.toString());
            networkInterface9410.disconnect();
        } catch (IOException e) {
            GlobalData.viewStatus.setText("Printer not available");
            e.printStackTrace();
        }

        return doResponse(feedback);
    }


    private Response doResponse(Object obj) {
        return Response.ok()
                .entity(obj)
                .header("Access-Control-Allow-Origin", "*")
                .build();
    }

}
