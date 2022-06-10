package com.medical_store_management_system.Business_Logic.Custom_Exceptions;

public class NegativeQuantityException extends Exception{
    public NegativeQuantityException(String msg)
    {
        super(msg);
    }
}
