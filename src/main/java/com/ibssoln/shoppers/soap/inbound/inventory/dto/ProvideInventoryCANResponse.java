
package com.ibssoln.shoppers.soap.inbound.inventory.dto;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "provideInventoryCANResponse", namespace = "http://inventory.inbound.soap.shoppers.ibssoln.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "provideInventoryCANResponse", namespace = "http://inventory.inbound.soap.shoppers.ibssoln.com/")
public class ProvideInventoryCANResponse {

    @XmlElement(name = "return", namespace = "")
    private String _return;

    /**
     * 
     * @return
     *     returns String
     */
    public String getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(String _return) {
        this._return = _return;
    }

}
