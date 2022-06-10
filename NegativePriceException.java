package com.medical_store_management_system.Business_Logic.Custom_Exceptions;

public class NegativePriceException extends Exception {
    public NegativePriceException(String msg)
    {
        super(msg);
    }

}
