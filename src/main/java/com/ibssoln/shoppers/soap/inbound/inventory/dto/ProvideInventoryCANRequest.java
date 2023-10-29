
package com.ibssoln.shoppers.soap.inbound.inventory.dto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "provideInventoryCANRequest", namespace = "http://inventory.inbound.soap.shoppers.ibssoln.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "provideInventoryCANRequest", namespace = "http://inventory.inbound.soap.shoppers.ibssoln.com/", propOrder = {
    "arg0",
    "arg1"
})
public class ProvideInventoryCANRequest {

    @XmlElement(name = "arg0", namespace = "")
    private String arg0;
    @XmlElement(name = "arg1", namespace = "")
    private String arg1;

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(String arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns String
     */
    public String getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

}