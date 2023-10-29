package com.ibssoln.shoppers.soap.outbound.inventory.dto;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ibssoln.shoppers.soap.outbound.inventory.dto package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ProvideInventoryCANRequest_QNAME = new QName("http://inventory.inbound.soap.shoppers.ibssoln.com/", "provideInventoryCANRequest");
    private final static QName _ProvideInventoryCANResponse_QNAME = new QName("http://inventory.inbound.soap.shoppers.ibssoln.com/", "provideInventoryCANResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ibssoln.shoppers.soap.outbound.inventory.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ProvideInventoryCANRequest }
     * 
     */
    public ProvideInventoryCANRequest createProvideInventoryCANRequest() {
        return new ProvideInventoryCANRequest();
    }

    /**
     * Create an instance of {@link ProvideInventoryCANResponse }
     * 
     */
    public ProvideInventoryCANResponse createProvideInventoryCANResponse() {
        return new ProvideInventoryCANResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvideInventoryCANRequest }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProvideInventoryCANRequest }{@code >}
     */
    @XmlElementDecl(namespace = "http://inventory.inbound.soap.shoppers.ibssoln.com/", name = "provideInventoryCANRequest")
    public JAXBElement<ProvideInventoryCANRequest> createProvideInventoryCANRequest(ProvideInventoryCANRequest value) {
        return new JAXBElement<ProvideInventoryCANRequest>(_ProvideInventoryCANRequest_QNAME, ProvideInventoryCANRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ProvideInventoryCANResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link ProvideInventoryCANResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://inventory.inbound.soap.shoppers.ibssoln.com/", name = "provideInventoryCANResponse")
    public JAXBElement<ProvideInventoryCANResponse> createProvideInventoryCANResponse(ProvideInventoryCANResponse value) {
        return new JAXBElement<ProvideInventoryCANResponse>(_ProvideInventoryCANResponse_QNAME, ProvideInventoryCANResponse.class, null, value);
    }

}
