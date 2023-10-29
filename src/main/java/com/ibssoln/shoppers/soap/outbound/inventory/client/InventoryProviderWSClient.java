package com.ibssoln.shoppers.soap.outbound.inventory.client;

import com.ibssoln.shoppers.soap.outbound.inventory.dto.ProvideInventoryCANRequest;
import com.ibssoln.shoppers.soap.outbound.inventory.dto.ProvideInventoryCANResponse;
import jakarta.xml.bind.JAXBElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
public class InventoryProviderWSClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(InventoryProviderWSClient.class);
    public ProvideInventoryCANResponse callInventoryProvider(String arg0, String arg1) {

        ProvideInventoryCANRequest request = new ProvideInventoryCANRequest();
        request.setArg0(arg0);
        request.setArg1(arg1);
        JAXBElement<ProvideInventoryCANResponse> response = (JAXBElement<ProvideInventoryCANResponse>) getWebServiceTemplate()
                .marshalSendAndReceive(request);
        return response.getValue();
    }

}