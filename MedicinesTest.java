package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativePriceException;
import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeQuantityException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.InputMismatchException;

import static org.junit.Assert.*;

public class MedicinesTest {
    Medicines testObject;

    @Before
    public void setup() throws Exception {
        testObject = new Medicines();
        testObject.setMedicineDrugCode("ABC");
        testObject.setMedicineName("Pandol");
        testObject.setMedicineBatchNo("1234");
        testObject.setMedicineGenericName("Paracetamol");
        testObject.setMedicineRetailPrice(10);
        testObject.setMedicineDealerPrice(5);
        testObject.setMedicineManufacturerName("GSK");
        testObject.setMedicineDosage("10mg");
        testObject.setMedicineDateOfManufacture(LocalDate.now());
        testObject.setMedicineDateOfExpiry(LocalDate.parse("2022-01-08"));
        testObject.setMedicineDescription("Headache");
        testObject.setMedicineType("Tablet");
        testObject.setMedicineQuantity(50);

    }

    @Test
    public void getMedicineDrugCode() {
        Assert.assertEquals("ABC", testObject.getMedicineDrugCode());
    }

    @Test
    public void setMedicineDrugCode() {
        testObject.setMedicineDrugCode("QWER123");
        Assert.assertEquals("QWER123", testObject.getMedicineDrugCode());
    }

    @Test
    public void setMedicineDrugCode_NegativeCase() {
        testObject.setMedicineDrugCode(" ");
        Assert.assertEquals("NO DRUG CODE INVALID", testObject.getMedicineDrugCode());
    }


    @Test
    public void getMedicineName() {
        Assert.assertEquals("Pandol", testObject.getMedicineName());
    }

    @Test
    public void setMedicineName() {
        testObject.setMedicineName("Pandol Extra");
        Assert.assertEquals("Pandol Extra", testObject.getMedicineName());

    }

    @Test
    public void setMedicineName_Negative() {
        testObject.setMedicineName(null);
        Assert.assertEquals("INVALID NAME", testObject.getMedicineName());

    }

    @Test
    public void getMedicineBatchNo() {
        Assert.assertEquals("1234", testObject.getMedicineBatchNo());

    }

    @Test
    public void setMedicineBatchNo() {
        testObject.setMedicineBatchNo("OP_21Z");
        Assert.assertEquals("OP_21Z", testObject.getMedicineBatchNo());
    }

    @Test
    public void setMedicineBatchNo_Negative() {
        testObject.setMedicineBatchNo("");
        Assert.assertEquals("INVALID BATCH NUMBER", testObject.getMedicineBatchNo());
    }

    @Test
    public void getMedicineGenericName() {
        Assert.assertEquals("Paracetamol", testObject.getMedicineGenericName());
    }

    @Test
    public void setMedicineGenericName() {
        testObject.setMedicineGenericName("Hello");
        Assert.assertEquals("Hello", testObject.getMedicineGenericName());
    }

    @Test
    public void setMedicineGenericName_Negative() {
        testObject.setMedicineGenericName(null);
        Assert.assertEquals("INVALID GENERIC NAME", testObject.getMedicineGenericName());
    }

    @Test
    public void getMedicineRetailPrice() {
        Assert.assertEquals(10, testObject.getMedicineRetailPrice());
    }

    @Test
    public void setMedicineRetailPrice() throws NegativePriceException {
        testObject.setMedicineRetailPrice(20);
        Assert.assertEquals(20, testObject.getMedicineRetailPrice());
    }

    @Test(expected = NegativePriceException.class)
    public void setMedicineRetailPrice_Negative() throws NegativePriceException {
        testObject.setMedicineRetailPrice(-20);
//        Assert.assertEquals(20, testObject.getMedicineRetailPrice());
    }

    @Test
    public void getMedicineDealerPrice() {
        Assert.assertEquals(5, testObject.getMedicineDealerPrice());
    }

    @Test
    public void setMedicineDealerPrice() throws NegativePriceException {
        testObject.setMedicineDealerPrice(20);
        Assert.assertEquals(20, testObject.getMedicineDealerPrice());
    }

    @Test(expected = NegativePriceException.class)
    public void setMedicineDealerPrice_NegativeCase() throws NegativePriceException {
        testObject.setMedicineDealerPrice(-20);
//        Assert.assertEquals(20, testObject.getMedicineDealerPrice());
    }

    @Test
    public void getMedicineManufacturerName() {
        Assert.assertEquals("GSK", testObject.getMedicineManufacturerName());
    }

    @Test
    public void setMedicineManufacturerName() {
        testObject.setMedicineManufacturerName("Seral");
        Assert.assertEquals("Seral", testObject.getMedicineManufacturerName());
    }

    @Test
    public void setMedicineManufacturerName_Negative() {
        testObject.setMedicineManufacturerName(" ");
        Assert.assertEquals("Invalid Manufacture Name", testObject.getMedicineManufacturerName());
    }

    @Test
    public void getMedicineDosage() {
        Assert.assertEquals("10mg", testObject.getMedicineDosage());
    }

    @Test
    public void setMedicineDosage() {
        testObject.setMedicineDosage("25mg");
        Assert.assertEquals("25mg", testObject.getMedicineDosage());
    }

    @Test
    public void setMedicineDosage_Negative() {
        testObject.setMedicineDosage("25mg");
        Assert.assertEquals("25mg", testObject.getMedicineDosage());
    }

    @Test
    public void getMedicineDateOfManufacture() {
        Assert.assertEquals(LocalDate.now(), testObject.getMedicineDateOfManufacture());
    }

    @Test
    public void setMedicineDateOfManufacture() throws Exception {
        testObject.setMedicineDateOfManufacture(LocalDate.now());
        Assert.assertEquals(LocalDate.now(), testObject.getMedicineDateOfManufacture());
    }

    @Test(expected = Exception.class)
    public void setMedicineDateOfManufacture_Greater() throws Exception {
        testObject.setMedicineDateOfManufacture(LocalDate.parse("2024,12,08"));
        Assert.assertEquals(LocalDate.now(), testObject.getMedicineDateOfManufacture());
    }

    @Test
    public void getMedicineDateOfExpiry() {
        Assert.assertEquals(LocalDate.parse("2022-01-08"), testObject.getMedicineDateOfExpiry());
    }

    @Test
    public void setMedicineDateOfExpiry() throws Exception {
        testObject.setMedicineDateOfExpiry(LocalDate.parse("2024-02-08"));
        Assert.assertEquals(LocalDate.parse("2024-02-08"), testObject.getMedicineDateOfExpiry());

    }

    @Test(expected = Exception.class)
    public void setMedicineDateOfExpiry_Negative() throws Exception {
        testObject.setMedicineDateOfExpiry(null);
        Assert.assertEquals(LocalDate.parse("2024-02-08"), testObject.getMedicineDateOfExpiry());
    }

    @Test
    public void getMedicineDescription() {
        Assert.assertEquals("Headache",testObject.getMedicineDescription());
    }

    @Test
    public void setMedicineDescription() {
        testObject.setMedicineDescription("Headache, Pain, Fever");
        Assert.assertEquals("Headache, Pain, Fever", testObject.getMedicineDescription());
    }


    @Test
    public void setMedicineDescription_Negative() {
        testObject.setMedicineDescription(" ");
        Assert.assertEquals("INVALID DESCRIPTION",testObject.getMedicineDescription());
    }

    @Test
    public void getMedicineType() {
        Assert.assertEquals("Tablet",testObject.getMedicineType());
    }

    @Test
    public void setMedicineType() {
        testObject.setMedicineType("Injection");
        Assert.assertEquals("Injection",testObject.getMedicineType());
    }

    @Test
    public void setMedicineType_Negative() {
        testObject.setMedicineType(null);
        Assert.assertEquals("Invalid Type",testObject.getMedicineType());
    }

    @Test
    public void getMedicineQuantity() {
        Assert.assertEquals(50,testObject.getMedicineQuantity());
    }

    @Test
    public void setMedicineQuantity() throws NegativeQuantityException {
        testObject.setMedicineQuantity(100);
    }

    @Test(expected = NegativeQuantityException.class)
    public void setMedicineQuantity_Negative() throws NegativeQuantityException {
        testObject.setMedicineQuantity(-100);
    }
}