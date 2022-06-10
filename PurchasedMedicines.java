package com.medical_store_management_system.Business_Logic;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "purchased_medicines", schema = "medical_store_management_system", catalog = "")
public class PurchasedMedicines {
    private int srNo;
    private String drugCode;
    private String medicineName;
    private String batchNo;
    private LocalDate medicineExpiryDate;
    private Integer amount;
    private Integer quantity;
    private Integer unitPrice;
    private String billNo;
    private static int srNo_Table = 0;


    @Id
    @Column(name = "srNo")
    public int getSrNo() {
        return srNo;
    }

    public void setSrNo(int srNo) {
        this.srNo = srNo;
    }

    @Basic
    @Column(name = "drugCode")
    public String getDrugCode() {
        return drugCode;
    }

    public void setDrugCode(String drugCode) {
        if (drugCode == "" || drugCode == " " || drugCode == null)
            this.drugCode = "INVALID DRUG CODE";
        else
            this.drugCode = drugCode;
    }

    @Basic
    @Column(name = "medicineName")
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
    @Column(name = "batchNo")
    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        if (batchNo == "" || batchNo == " " || batchNo == null)
            this.batchNo = "INVALID BATCH NUMBER";
        else
            this.batchNo = batchNo;
    }

    @Basic
    @Column(name = "medicineExpiryDate")
    public LocalDate getMedicineExpiryDate() {
        return medicineExpiryDate;
    }

    public void setMedicineExpiryDate(LocalDate medicineExpiryDate) {
        this.medicineExpiryDate = medicineExpiryDate;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        if (amount < 0)
            this.amount = 0;
        else
            this.amount = amount;
    }

    @Basic
    @Column(name = "quantity")
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        if(quantity ==null || quantity <0)
            this.quantity =0;
        else
            this.quantity = quantity;
    }

    @Basic
    @Column(name = "unitPrice")
    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        if(unitPrice <0)
            this.unitPrice=0;
        else
            this.unitPrice = unitPrice;
    }

    @Basic
    @Column(name = "bill_no")
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        if(billNo ==""|| billNo ==" " || billNo==null)
            this.billNo="NO INVALID BILL NO";
        else
            this.billNo = billNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchasedMedicines that = (PurchasedMedicines) o;
        return srNo == that.srNo && Objects.equals(drugCode, that.drugCode) && Objects.equals(medicineName, that.medicineName) && Objects.equals(batchNo, that.batchNo) && Objects.equals(medicineExpiryDate, that.medicineExpiryDate) && Objects.equals(amount, that.amount) && Objects.equals(quantity, that.quantity) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(billNo, that.billNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(srNo, drugCode, medicineName, batchNo, medicineExpiryDate, amount, quantity, unitPrice, billNo);
    }


    public static int getSrNo_Table() {
        return srNo_Table;
    }

    public static void restSrNo() {
        srNo_Table = 0;
    }

    //-------------------------- To String For Medicines In The Cart ------------------------------------------------------------------------
    @Override
    public String toString() {
        return medicineName + "\t\t\t" +
                medicineExpiryDate + "\t\t\t" +
                unitPrice + "\t\t\t" +
                quantity + "\t\t\t" +
                amount + "\t\t";
    }

    //-------------------------------------------------- Calculate The Amount of Total Items Purchased-----------------------------------------------
    public void calculateAmount() {
        this.amount = this.quantity * this.unitPrice;
    }

    //------------------------------------- set medicine in cart details -----------------------------------------------
    public void setMedicineInCartDetails(Medicines selectedMedicine) {

        this.unitPrice = selectedMedicine.getMedicineRetailPrice();
        this.medicineName = selectedMedicine.getMedicineName();
        this.medicineExpiryDate = selectedMedicine.getMedicineDateOfExpiry();
        this.drugCode = selectedMedicine.getMedicineDrugCode();
        this.batchNo = selectedMedicine.getMedicineBatchNo();
        ++srNo_Table;
    }

    //---------------------------------------------- Get String of The Entire object --------------------------------------------------
    @Transient
    public String getStringMethod() {
        return "PurchasedMedicines{" +
                "srNo=" + srNo +
                ", drugCode='" + drugCode + '\'' +
                ", medicineName='" + medicineName + '\'' +
                ", batchNo='" + batchNo + '\'' +
                ", medicineExpiryDate=" + medicineExpiryDate +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", billNo='" + billNo + '\'' +
                '}';
    }
}
