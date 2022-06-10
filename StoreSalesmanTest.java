package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class StoreSalesmanTest {

    StoreSalesman testObject;

    @Before
    public void setUp() throws Exception ,NegativeCINC_Number_Exception {
        testObject = new StoreSalesman();
        testObject.setSalesmanName("Amir");
        testObject.setSalesmanDob(LocalDate.now());
        testObject.setSalesmanAddress("Rawalpindi");
        testObject.setSalesmanCnic(1234);
        testObject.setSalesmanLoginUserName("ABC");
        testObject.setSalesmanLoginPassword("ABC");
        testObject.setSalesmanPhoneNo("+921235");
    }

    @Test
    public void getSalesmanCnic() {
        System.out.println("Testing The CNIC Salesman");
        Assert.assertEquals(1234, testObject.getSalesmanCnic());
    }

    @Test
    public void setSalesmanCnic_PositiveCase() throws NegativeCINC_Number_Exception {
        System.out.println("Testing Set Salesman CNIC ");
        testObject.setSalesmanCnic(123);
        Assert.assertEquals(123, testObject.getSalesmanCnic());
    }

    @Test(expected = NegativeCINC_Number_Exception.class)
    public void setSalesmanCnic_NegativeCase() throws NegativeCINC_Number_Exception {
        System.out.println("Testing Set Salesman CNIC ");
        testObject.setSalesmanCnic(-123);
    }

    @Test
    public void getSalesmanName() {
        System.out.println("Testing Get Salesman");
        Assert.assertEquals("Amir", testObject.getSalesmanName());
    }

    @Test
    public void setSalesmanName() {
        testObject.setSalesmanName("Ahmed");
        Assert.assertEquals("Ahmed", testObject.getSalesmanName());
    }

    @Test
    public void getSalesmanAddress() {
        Assert.assertEquals("Rawalpindi", testObject.getSalesmanAddress());
    }

    @Test
    public void setSalesmanAddress() {
        testObject.setSalesmanAddress("Lahore");
        Assert.assertEquals("Lahore", testObject.getSalesmanAddress());
    }

    @Test
    public void getSalesmanDob() {
        Assert.assertEquals(LocalDate.now(),testObject.getSalesmanDob());
    }

    @Test
    public void setSalesmanDob_PositiveCase() throws Exception {
        testObject.setSalesmanDob(LocalDate.now());
        Assert.assertEquals(LocalDate.now(),testObject.getSalesmanDob());
    }

    @Test(expected = Exception.class)
    public void setSalesmanDob_NegativeCase() throws Exception {
        testObject.setSalesmanDob(null);
    }

    @Test(expected = Exception.class)
    public void setSalesmanDob_NegativeCaseGreaterThenCurrentDate() throws Exception {
        System.out.println("This Also Will Throws An Exception");
        testObject.setSalesmanDob(LocalDate.parse("2024-12-18"));
    }

    @Test
    public void getSalesmanPhoneNo() {
        System.out.println("Testing the Get Salesman Phone Number");
        Assert.assertEquals("+921235",testObject.getSalesmanPhoneNo());
    }

    @Test
    public void setSalesmanPhoneNo() {
        testObject.setSalesmanPhoneNo("+92145632");
        Assert.assertEquals("+92145632",testObject.getSalesmanPhoneNo());
    }

    @Test
    public void getSalesmanLoginUserName() {
        Assert.assertEquals("ABC",testObject.getSalesmanLoginUserName());
    }

    @Test
    public void setSalesmanLoginUserName() {
        testObject.setSalesmanLoginUserName("123");
        Assert.assertEquals("123",testObject.getSalesmanLoginUserName());
    }

    @Test
    public void getSalesmanLoginPassword() {
        Assert.assertEquals("ABC",testObject.getSalesmanLoginPassword());

    }

    @Test
    public void setSalesmanLoginPassword() {
        testObject.setSalesmanLoginPassword("123");
        Assert.assertEquals("123",testObject.getSalesmanLoginPassword());
    }
}