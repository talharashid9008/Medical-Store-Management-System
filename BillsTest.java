package com.medical_store_management_system.Business_Logic;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BillsTest {

    Bills testObject;

    @Before
    public void setup() {
        testObject = new Bills();
        testObject.setSalesmanName("Ali");
        testObject.setCustomerName("Ahmed");
        testObject.setCustomerPhoneNo("+9214567896");
        testObject.setPaymentMethod("Cash");
        testObject.setTotalAmountOfBill(123);
        testObject.setDiscountPercentage(12);
        testObject.setTotalSavingsOnBill(55);
        testObject.setTotalPayableBill(55);
        testObject.setCashGivenByCustomer(100);
        testObject.setChangeOfCashGivenBackToCustomer(55);
        testObject.setSystemTimeDate();
        testObject.setBillNo("12345");
    }

    @Test
    public void getBillNo() {
        Assert.assertEquals("12345", testObject.getBillNo());
    }

    @Test
    public void setBillNo_NegativeCase_NullValues() {
        testObject.setBillNo(null);
        Assert.assertEquals("INVALID BILL", testObject.getBillNo());
    }

    @Test
    public void setBillNo() {
        testObject.setBillNo("1256A");
        Assert.assertEquals("1256A", testObject.getBillNo());
    }

    @Test
    public void getCustomerName() {
        Assert.assertEquals("Ahmed", testObject.getCustomerName());
    }

    @Test
    public void setCustomerName() {
        testObject.setCustomerName("Ahmed");
        Assert.assertEquals("Ahmed", testObject.getCustomerName());
    }

    @Test
    public void setCustomerName_NegativeCase() {
        testObject.setCustomerName("");
        Assert.assertEquals("NO NAME", testObject.getCustomerName());
    }

    @Test
    public void getCustomerPhoneNo() {
        Assert.assertEquals("+9214567896", testObject.getCustomerPhoneNo());
    }

    @Test
    public void setCustomerPhoneNo() {
        testObject.setCustomerPhoneNo("");
        Assert.assertEquals("INVALID MOBILE NUMBER", testObject.getCustomerPhoneNo());
    }

    @Test
    public void getPaymentMethod() {
        Assert.assertEquals("Cash", testObject.getPaymentMethod());
    }

    @Test
    public void setPaymentMethod() {
        testObject.setPaymentMethod("JazzCash");
        Assert.assertEquals("JazzCash", testObject.getPaymentMethod());
    }

    @Test
    public void setPaymentMethod_Negative() {
        testObject.setPaymentMethod(" ");
        Assert.assertEquals("INVALID METHOD", testObject.getPaymentMethod());
    }


    @Test
    public void getTotalAmountOfBill() {
        Assert.assertEquals("12345", testObject.getBillNo());
    }

    @Test
    public void setTotalAmountOfBill() {
        testObject.setBillNo("12");
        Assert.assertEquals("12", testObject.getBillNo());
    }

    @Test
    public void getDiscountPercentage() {
        Assert.assertEquals(12, testObject.getDiscountPercentage());
    }

    @Test
    public void setDiscountPercentage_Positve() {
        testObject.setDiscountPercentage(55);
        Assert.assertEquals(55, testObject.getDiscountPercentage());

    }

    @Test
    public void setDiscountPercentage_Negative_Value() {
        testObject.setDiscountPercentage(-2);
        Assert.assertEquals(0, testObject.getDiscountPercentage());
    }


    @Test
    public void getTotalSavingsOnBill() {
        Assert.assertEquals(55, testObject.getTotalSavingsOnBill(),0);
    }

    @Test
    public void setTotalSavingsOnBill() {
        testObject.setTotalSavingsOnBill(100);
        Assert.assertEquals(100,testObject.getTotalSavingsOnBill(),0);
    }

    @Test
    public void setTotalSavingsOnBill_Negative() {
        testObject.setTotalSavingsOnBill(-100);
        Assert.assertEquals(0,testObject.getTotalSavingsOnBill(),0);
    }

    @Test
    public void getTotalPayableBill() {
        Assert.assertEquals(55,testObject.getTotalSavingsOnBill(),0);
    }

    @Test
    public void setTotalPayableBill() {
        testObject.setTotalPayableBill(100);
        Assert.assertEquals(100,testObject.getTotalPayableBill(),0);
    }

    @Test
    public void setTotalPayableBill_Negative() {
        testObject.setTotalPayableBill(-100);
        Assert.assertEquals(0,testObject.getTotalPayableBill(),0);
    }

    @Test
    public void getCashGivenByCustomer() {
        Assert.assertEquals(100,testObject.getCashGivenByCustomer());
    }

    @Test
    public void setCashGivenByCustomer() {
        testObject.setCashGivenByCustomer(100);
        Assert.assertEquals(100,testObject.getCashGivenByCustomer());
    }

    @Test
    public void setCashGivenByCustomer_Negative() {
        testObject.setCashGivenByCustomer(-100);
        Assert.assertEquals(0,testObject.getCashGivenByCustomer());
    }
    @Test
    public void getChangeOfCashGivenBackToCustomer() {
        Assert.assertEquals(55,testObject.getChangeOfCashGivenBackToCustomer());
    }

    @Test
    public void setChangeOfCashGivenBackToCustomer() {
        testObject.setChangeOfCashGivenBackToCustomer(12);
        Assert.assertEquals(12,testObject.getChangeOfCashGivenBackToCustomer());
    }

    @Test
    public void setChangeOfCashGivenBackToCustomer_Negative() {
        testObject.setChangeOfCashGivenBackToCustomer(-12);
        Assert.assertEquals(0,testObject.getChangeOfCashGivenBackToCustomer());
    }

    @Test
    public void getDateTimeOfBill() {
        testObject.setSystemTimeDate();
        Assert.assertEquals(LocalDateTime.now() ,testObject.getDateTimeOfBill());

    }

    @Test
    public void setDateTimeOfBill() {
        testObject.setSystemTimeDate();
        Assert.assertEquals(LocalDateTime.now() ,testObject.getDateTimeOfBill());
    }

    @Test
    public void getSalesmanName() {
        Assert.assertEquals("Ali",testObject.getSalesmanName());
    }

    @Test
    public void setSalesmanName() {
        testObject.setSalesmanName("Ahmed");
        Assert.assertEquals("Ahmed",testObject.getSalesmanName());
    }

    @Test
    public void setSalesmanName_Negative() {
        testObject.setSalesmanName("");
        Assert.assertEquals("INVALID NAME",testObject.getSalesmanName());
    }


}