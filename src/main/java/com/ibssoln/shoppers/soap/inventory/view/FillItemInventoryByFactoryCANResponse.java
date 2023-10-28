
package com.ibssoln.shoppers.soap.inventory.view;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "fillItemInventoryByFactoryCANResponse", namespace = "http://inventory.soap.inbound.shoppers.ibssoln.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fillItemInventoryByFactoryCANResponse", namespace = "http://inventory.soap.inbound.shoppers.ibssoln.com/")
public class FillItemInventoryByFactoryCANResponse {

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
