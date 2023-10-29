package com.ibssoln.shoppers.soap.outbound.inventory.config;

import com.ibssoln.shoppers.soap.outbound.inventory.client.InventoryProviderWSClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
@Configuration
public class InventoryProviderWSClientConfig {
    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.ibssoln.shoppers.soap.outbound.inventory.dto");
        return marshaller;
    }

    @Bean
    public InventoryProviderWSClient countryClient(Jaxb2Marshaller marshaller) {
        InventoryProviderWSClient client = new InventoryProviderWSClient();
        client.setDefaultUri("http://localhost:8080/ws/inventoryProviderService");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}