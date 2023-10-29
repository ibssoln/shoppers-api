package com.ibssoln.shoppers.soap.inbound.inventory.endpoint;

import com.ibssoln.shoppers.soap.inbound.inventory.dto.ProvideInventoryCANRequest;
import com.ibssoln.shoppers.soap.inbound.inventory.dto.ProvideInventoryCANResponse;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class InventoryProviderEndpoint {
    private static final String NAMESPACE_URI = "http://inventory.inbound.soap.shoppers.ibssoln.com/";

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "provideInventoryCANRequest")
    @ResponsePayload
    public ProvideInventoryCANResponse getServiceName(@RequestPayload ProvideInventoryCANRequest request) {
        ProvideInventoryCANResponse response = new ProvideInventoryCANResponse();
        response.setReturn("SENT_CAN-"+request.getArg0());
        return response;
    }

}
