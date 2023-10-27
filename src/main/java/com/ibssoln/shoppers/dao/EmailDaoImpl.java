package com.ibssoln.shoppers.dao;

import com.ibssoln.shoppers.domain.exception.ShoppersException;
import com.ibssoln.shoppers.entity.Item;

public class EmailDaoImpl {

    public void sendEmailNotification(Item item) throws ShoppersException {
        try{
            //TODO

        } catch (Exception e){
            throw new ShoppersException("Error occurred while sending email notification");
        }
    }

}
