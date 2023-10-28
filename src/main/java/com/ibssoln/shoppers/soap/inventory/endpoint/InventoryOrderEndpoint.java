package com.ibssoln.shoppers.soap.inventory.endpoint;

import com.ibssoln.shoppers.soap.inventory.view.FillItemInventoryByFactoryCANRequest;
import com.ibssoln.shoppers.soap.inventory.view.FillItemInventoryByFactoryCANResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
@Endpoint
public class InventoryOrderEndpoint {

    private static final String NAMESPACE_URI = "http://inventory.soap.inbound.shoppers.ibssoln.com/";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "fillItemInventoryByFactoryCANRequest")
    @ResponsePayload
    public FillItemInventoryByFactoryCANResponse getServiceName(@RequestPayload FillItemInventoryByFactoryCANRequest request) {
        FillItemInventoryByFactoryCANResponse response = new FillItemInventoryByFactoryCANResponse();
        response.setReturn("SENT_CAN_"+request.getArg0());
        return response;
    }

}