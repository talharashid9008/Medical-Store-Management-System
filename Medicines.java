package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativePriceException;
import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeQuantityException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;

@Entity
@Table(name = "medicines", schema = "medical_store_management_system", catalog = "")
public class Medicines {
    @Id
    private String medicineDrugCode;
    private String medicineName;
    private String medicineBatchNo;
    private String medicineGenericName;
    private int medicineRetailPrice;
    private int medicineDealerPrice;
    private String medicineManufacturerName;
    private String medicineDosage;
    private LocalDate medicineDateOfManufacture;
    private LocalDate medicineDateOfExpiry;
    private String medicineDescription;
    private String medicineType;
    private int medicineQuantity;

    @Id
    @Column(name = "medicine_DrugCode", insertable = true, updatable = true)
    public String getMedicineDrugCode() {
        return medicineDrugCode;
    }

    public void setMedicineDrugCode(String medicineDrugCode) {
        if (medicineDrugCode == "" || medicineDrugCode == " " || medicineDrugCode == null)
            this.medicineDrugCode = "NO DRUG CODE INVALID";
        else
            this.medicineDrugCode = medicineDrugCode;
    }

    @Basic
    @Column(name = "medicine_Name")
    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        if (medicineName == "" || medicineName == " " || medicineName == null)
            this.medicineName = "INVALID NAME";
        else
            this.medicineName = medicineName;
    }

    @Basic
    @Column(name = "medicine_BatchNo")
    public String getMedicineBatchNo() {
        return medicineBatchNo;
    }

    public void setMedicineBatchNo(String medicineBatchNo) {
        if (medicineBatchNo == "" || medicineBatchNo == " " || medicineBatchNo == null)
            this.medicineBatchNo = "INVALID BATCH NUMBER";
        else
            this.medicineBatchNo = medicineBatchNo;
    }

    @Basic
    @Column(name = "medicine_Generic_Name")
    public String getMedicineGenericName() {
        return medicineGenericName;
    }

    public void setMedicineGenericName(String medicineGenericName) {
        if (medicineGenericName == "" || medicineGenericName == " " || medicineGenericName == null)
            this.medicineGenericName = "INVALID GENERIC NAME";
        else
            this.medicineGenericName = medicineGenericName;
    }

    @Basic
    @Column(name = "medicine_RetailPrice")
    public int getMedicineRetailPrice() {
        return medicineRetailPrice;
    }

    public void setMedicineRetailPrice(int medicineRetailPrice) throws NegativePriceException {
        try {
            if (medicineRetailPrice <= 0) {
                this.medicineRetailPrice = 0;
                throw new NegativePriceException("You Have Entered A Negative Price Value Not Accepted By System");
            } else
                this.medicineRetailPrice = medicineRetailPrice;
        } catch (NumberFormatException E) {
            System.out.println(E);
            this.medicineRetailPrice = 0;
        }
    }

    @Basic
    @Column(name = "medicine_DealerPrice")
    public int getMedicineDealerPrice() {
        return medicineDealerPrice;
    }

    public void setMedicineDealerPrice(int medicineDealerPrice) throws NegativePriceException {
        try {
            if (medicineDealerPrice <= 0) {
                this.medicineDealerPrice = 0;
                throw new NegativePriceException("You Have Entered A Negative Price Value Not Accepted By System");
            } else
                this.medicineDealerPrice = medicineDealerPrice;
        } catch (NumberFormatException E) {
            System.out.println(E);
            this.medicineDealerPrice = 0;
        }
    }

    @Basic
    @Column(name = "medicine_ManufacturerName")
    public String getMedicineManufacturerName() {
        return medicineManufacturerName;
    }

    public void setMedicineManufacturerName(String medicineManufacturerName) {
        if (medicineManufacturerName == "" || medicineManufacturerName == " " || medicineManufacturerName == null)
            this.medicineManufacturerName = "Invalid Manufacture Name";
        else
            this.medicineManufacturerName = medicineManufacturerName;
    }

    @Basic
    @Column(name = "medicine_Dosage")
    public String getMedicineDosage() {
        return medicineDosage;
    }

    public void setMedicineDosage(String medicineDosage) {
        if (medicineDosage == "" || medicineDosage == " " || medicineDosage == null)
            this.medicineDosage = "Invalid Dossage Entered";
        else
            this.medicineDosage = medicineDosage;
    }

    @Basic
    @Column(name = "medicine_DateOf_Manufacture")
    public LocalDate getMedicineDateOfManufacture() {
        return medicineDateOfManufacture;
    }

    public void setMedicineDateOfManufacture(LocalDate medicineDateOfManufacture) throws Exception {
        LocalDate currentDate = LocalDate.now();
        if (medicineDateOfManufacture == null || currentDate.isBefore(medicineDateOfManufacture))
            throw new InputMismatchException("Cannot Set this value");
        else
            this.medicineDateOfManufacture = medicineDateOfManufacture;
    }

    @Basic
    @Column(name = "medicine_DateOf_Expiry")
    public LocalDate getMedicineDateOfExpiry() {
        return medicineDateOfExpiry;
    }

    public void setMedicineDateOfExpiry(LocalDate medicineDateOfExpiry) throws Exception {
        if (medicineDateOfExpiry == null)
            throw new InputMismatchException("Cannot Set this value");

        this.medicineDateOfExpiry = medicineDateOfExpiry;
    }

    @Basic
    @Column(name = "medicine_Description")
    public String getMedicineDescription() {
        return medicineDescription;
    }

    public void setMedicineDescription(String medicineDescription) {
        if (medicineDescription.isEmpty() || medicineDescription.isEmpty() || medicineDescription == null || medicineDescription == "" || medicineDescription == " ")
            this.medicineDescription = "INVALID DESCRIPTION";
        else
            this.medicineDescription = medicineDescription;
    }

    @Basic
    @Column(name = "medicine_Type")
    public String getMedicineType() {
        return medicineType;
    }

    public void setMedicineType(String medicineType) {
        if (medicineType == "" || medicineType == " " || medicineType == null)
            this.medicineType = "Invalid Type";
        else
            this.medicineType = medicineType;
    }

    @Basic
    @Column(name = "medicine_Quantity")
    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(int medicineQuantity) throws NegativeQuantityException {
        try {
            if (medicineQuantity < 0) {
                this.medicineQuantity = 0;
                throw new NegativeQuantityException("You Have Entered A Negative Price Value Not Accepted By System");
            } else
                this.medicineQuantity = medicineQuantity;
        } catch (NumberFormatException E) {
            System.out.println(E);
            this.medicineQuantity = 999999;
        }
        this.medicineQuantity = medicineQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicines medicines = (Medicines) o;
        return medicineRetailPrice == medicines.medicineRetailPrice && medicineDealerPrice == medicines.medicineDealerPrice && medicineQuantity == medicines.medicineQuantity && Objects.equals(medicineDrugCode, medicines.medicineDrugCode) && Objects.equals(medicineName, medicines.medicineName) && Objects.equals(medicineBatchNo, medicines.medicineBatchNo) && Objects.equals(medicineGenericName, medicines.medicineGenericName) && Objects.equals(medicineManufacturerName, medicines.medicineManufacturerName) && Objects.equals(medicineDosage, medicines.medicineDosage) && Objects.equals(medicineDateOfManufacture, medicines.medicineDateOfManufacture) && Objects.equals(medicineDateOfExpiry, medicines.medicineDateOfExpiry) && Objects.equals(medicineDescription, medicines.medicineDescription) && Objects.equals(medicineType, medicines.medicineType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicineDrugCode, medicineName, medicineBatchNo, medicineGenericName, medicineRetailPrice, medicineDealerPrice, medicineManufacturerName, medicineDosage, medicineDateOfManufacture, medicineDateOfExpiry, medicineDescription, medicineType, medicineQuantity);
    }

    //------------------- To String Method -----------------------------------------------------------------------------
    @Override
    public String toString() {
        return "Medicines{" +
                "medicineDrugCode='" + medicineDrugCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", medicineBatchNo='" + medicineBatchNo + '\'' +
                ", medicineGenericName='" + medicineGenericName + '\'' +
                ", medicineRetailPrice=" + medicineRetailPrice +
                ", medicineDealerPrice=" + medicineDealerPrice +
                ", medicineManufacturerName='" + medicineManufacturerName + '\'' +
                ", medicineDosage='" + medicineDosage + '\'' +
                ", medicineDateOfManufacture=" + medicineDateOfManufacture +
                ", medicineDateOfExpiry=" + medicineDateOfExpiry +
                ", medicineDescription='" + medicineDescription + '\'' +
                ", medicineType='" + medicineType + '\'' +
                ", medicineQuantity=" + medicineQuantity +
                '}';
    }
}
