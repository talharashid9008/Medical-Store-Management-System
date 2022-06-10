package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StoreAdminTest {


    StoreAdmin testObject;

    //----------------------------- Setting the test object -----------------------------
    @Before
    public void setUp() throws NegativeCINC_Number_Exception {
        testObject = new StoreAdmin();
        testObject.setAdminName("Amir");
        testObject.setAdminCnic(123456);
        testObject.setAdminAddress("Rawalpindi");
        testObject.setAdminLoginUserName("Amir");
        testObject.setAdminLoginPassword("Amir");
    }


    @Test
    public void getAdminCnic() {
        System.out.println("Testing The Store Admin CNIC");
        Assert.assertEquals(123456, testObject.getAdminCnic());
    }

    @Test
    public void setAdminCnic_PostiveCase() throws NegativeCINC_Number_Exception {
        System.out.println("Testing CNIC Method");
        testObject.setAdminCnic(1234);
        Assert.assertEquals(1234, testObject.getAdminCnic());
    }

    @Test(expected = NegativeCINC_Number_Exception.class)
    public void setAdminCnic_NegativeCase() throws NegativeCINC_Number_Exception {
        System.out.println("Testing CNIC Method Negative Case : Negative Number Exception Will Be Thrown");
        testObject.setAdminCnic(-1234);
//        Assert.assertEquals(1234,testObject.getAdminCnic());
    }

    @Test
    public void getAdminName() {
        System.out.println("Testing the Admin Name");
        Assert.assertEquals("Amir", testObject.getAdminName());
    }

    @Test
    public void setAdminName() {
        System.out.println("Testing the Admin SetName Method");
        testObject.setAdminName("Khan");
        Assert.assertEquals("Khan", testObject.getAdminName());
    }

    @Test
    public void getAdminAddress() {
        System.out.println("Testing The Get Address");
        Assert.assertEquals("Rawalpindi", testObject.getAdminAddress());
    }

    @Test
    public void setAdminAddress() {
        System.out.println("Testing Set Address");
        testObject.setAdminAddress("Lahore");
        Assert.assertEquals("Lahore", testObject.getAdminAddress());
    }

    @Test
    public void getAdminLoginUserName() {
        System.out.println("Testing Get Admin UserName");
        Assert.assertEquals("Amir", testObject.getAdminLoginUserName());
    }

    @Test
    public void setAdminLoginUserName() {
        System.out.println("Testing Set Admin Login USername");
        testObject.setAdminLoginUserName("123");
        Assert.assertEquals("123", testObject.getAdminLoginUserName());
    }

    @Test
    public void getAdminLoginPassword() {
        System.out.println("Testing Get Admin UserName");
        Assert.assertEquals("Amir", testObject.getAdminLoginPassword());
    }

    @Test
    public void setAdminLoginPassword() {
        System.out.println("Testing Set Admin Login USername");
        testObject.setAdminLoginPassword("123");
        Assert.assertEquals("123", testObject.getAdminLoginPassword());
    }

}