package com.medical_store_management_system.Business_Logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class PurchasedMedicinesTest {
    PurchasedMedicines testObject;

    @Before
    public void setUp() {
        testObject = new PurchasedMedicines();
        testObject.setDrugCode("ABC");
        testObject.setMedicineName("Panadol");
        testObject.setBatchNo("XAZ");
        testObject.setMedicineExpiryDate(LocalDate.now());
        testObject.setAmount(100);
        testObject.setQuantity(1);
        testObject.setUnitPrice(2);
        testObject.setBillNo("ABC123");
    }

    @Test
    public void getDrugCode() {
        Assert.assertEquals("ABC", testObject.getDrugCode());
    }

    @Test
    public void setDrugCode() {
        testObject.setDrugCode("AYZ");
        Assert.assertEquals("AYZ", testObject.getDrugCode());
    }

    @Test
    public void setDrugCode_Negative() {
        testObject.setDrugCode("");
        Assert.assertEquals("INVALID DRUG CODE", testObject.getDrugCode());
    }

    @Test
    public void getMedicineName() {
        Assert.assertEquals("Panadol", testObject.getMedicineName());
    }

    @Test
    public void setMedicineName() {
        testObject.setMedicineName("Disprin");
        Assert.assertEquals("Disprin", testObject.getMedicineName());
    }

    @Test
    public void setMedicineName_Negative() {
        testObject.setMedicineName(null);
        Assert.assertEquals("INVALID NAME", testObject.getMedicineName());
    }


    @Test
    public void getBatchNo() {
        Assert.assertEquals("XAZ", testObject.getBatchNo());
    }

    @Test
    public void setBatchNo() {
        testObject.setBatchNo("AXHYZ");
        Assert.assertEquals("AXHYZ", testObject.getBatchNo());
    }

    @Test
    public void setBatchNo_NegativeCase() {
        testObject.setBatchNo("");
        Assert.assertEquals("INVALID BATCH NUMBER", testObject.getBatchNo());
    }

    @Test
    public void getMedicineExpiryDate() {
        Assert.assertEquals(LocalDate.now(), testObject.getMedicineExpiryDate());
    }

    @Test
    public void getAmount() {
        Integer val = 100;
        assertSame(val, testObject.getAmount());
    }

    @Test
    public void setAmount() {
        testObject.setAmount(1);
        Integer val = 1;
        assertSame(val, testObject.getAmount());
    }

    @Test
    public void setAmount_NegativeCase() {
        testObject.setAmount(-1);
        Integer val = 0;
        assertSame(val, testObject.getAmount());
    }


    @Test
    public void getQuantity() {
        assertSame(1, testObject.getQuantity());
    }

    @Test
    public void setQuantity() {
        testObject.setQuantity(10);
        assertSame(10, testObject.getQuantity());
    }

    @Test
    public void setQuantity_Negative() {
        testObject.setQuantity(-10);
        assertSame(0, testObject.getQuantity());
    }


    @Test
    public void getUnitPrice() {
        assertSame(2, testObject.getUnitPrice());
    }

    @Test
    public void setUnitPrice() {
        Integer val = 2;
        testObject.setUnitPrice(2);
        assertSame(val, testObject.getUnitPrice());

    }

    @Test
    public void setUnitPrice_Negative() {
        Integer val = 2;
        testObject.setUnitPrice(2);
        assertSame(val, testObject.getUnitPrice());

    }

    @Test
    public void getBillNo() {
        Assert.assertEquals("ABC123",testObject.getBillNo());
    }

    @Test
    public void setBillNo() {
        testObject.setBillNo("AAA");
        Assert.assertEquals("AAA",testObject.getBillNo());
    }

    @Test
    public void setBillNo_Negative() {
        testObject.setBillNo(" ");
        Assert.assertEquals("NO INVALID BILL NO",testObject.getBillNo());
    }
}