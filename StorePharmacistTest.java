package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class StorePharmacistTest {

    StorePharmacist testObject;

    @Before
    public void setUp() throws Exception {
        testObject = new StorePharmacist();
        testObject.setPharmacistCnic(1234569);
        testObject.setPharmacistName("Ahmed");
        testObject.setPharmacistAddress("Rawalpindi");
        testObject.setPharmacistDob(LocalDate.now());
        testObject.setPharmacistPhoneNo("+921234");
        testObject.setPharmacistLoginUserName("Ahmed");
        testObject.setPharmacistLoginPassword("Ahmed");
        testObject.setPharmacistLicenseNo("ANC-123");
    }

    @Test
    public void getPharmacistCnic() {
        Assert.assertEquals(1234569, testObject.getPharmacistCnic());
    }

    @Test
    public void setPharmacistCnic_Positvie() throws NegativeCINC_Number_Exception {
        testObject.setPharmacistCnic(4567);
        Assert.assertEquals(4567, testObject.getPharmacistCnic());
    }

    @Test(expected = NegativeCINC_Number_Exception.class)
    public void setPharmacistCnic_Negative() throws NegativeCINC_Number_Exception {
        testObject.setPharmacistCnic(-4567);

    }


    @Test
    public void getPharmacistName() {
        Assert.assertEquals("Ahmed", testObject.getPharmacistName());
    }

    @Test
    public void setPharmacistName() {
        testObject.setPharmacistName("Ali");
        Assert.assertEquals("Ali", testObject.getPharmacistName());
    }

    @Test
    public void getPharmacistAddress() {
        Assert.assertEquals("Rawalpindi", testObject.getPharmacistAddress());
    }

    @Test
    public void setPharmacistAddress() {
        testObject.setPharmacistAddress("Karachi");
        Assert.assertEquals("Karachi", testObject.getPharmacistAddress());

    }

    @Test
    public void getPharmacistDob() {
        Assert.assertEquals(LocalDate.now(), testObject.getPharmacistDob());

    }

    @Test
    public void setPharmacistDob_Positive() throws Exception {
        testObject.setPharmacistDob(LocalDate.now());
        Assert.assertEquals(LocalDate.now(),testObject.getPharmacistDob());
    }

    @Test(expected = Exception.class)
    public void setPharmacistDob_Negative() throws Exception {
        testObject.setPharmacistDob(null);

    }

    @Test(expected = Exception.class)
    public void setPharmacistDob_Negative_Date_GreaterThenCurrentDate() throws Exception {
        System.out.println("This Also Will Throws An Exception");
        testObject.setPharmacistDob(LocalDate.parse("2024-12-18"));

    }

    @Test
    public void getPharmacistPhoneNo() {
        Assert.assertEquals("+921234",testObject.getPharmacistPhoneNo());
    }

    @Test
    public void setPharmacistPhoneNo() {
        testObject.setPharmacistPhoneNo("+921542");
        Assert.assertEquals("+921542",testObject.getPharmacistPhoneNo());
    }

    @Test
    public void getPharmacistLicenseNo() {
        Assert.assertEquals("ANC-123",testObject.getPharmacistLicenseNo());
    }

    @Test
    public void setPharmacistLicenseNo() {
        testObject.setPharmacistLicenseNo("XYZ");
        Assert.assertEquals("XYZ",testObject.getPharmacistLicenseNo());
    }

    @Test
    public void getPharmacistLoginUserName() {
        Assert.assertEquals("Ahmed",testObject.getPharmacistLoginUserName());
    }

    @Test
    public void setPharmacistLoginUserName() {
        testObject.setPharmacistLoginUserName("124aa");
        Assert.assertEquals("124aa",testObject.getPharmacistLoginUserName());
    }

    @Test
    public void getPharmacistLoginPassword() {
        Assert.assertEquals("Ahmed",testObject.getPharmacistLoginPassword());
    }

    @Test
    public void setPharmacistLoginPassword() {
        testObject.setPharmacistLoginPassword("abc");
        Assert.assertEquals("abc",testObject.getPharmacistLoginPassword());
    }
}