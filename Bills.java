package com.medical_store_management_system.Business_Logic;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Random;
import java.util.Vector;

@Entity
public class Bills {
    private String billNo;
    private String customerName;
    private String customerPhoneNo;
    private String paymentMethod;
    private int totalAmountOfBill;
    private int discountPercentage;
    private double totalSavingsOnBill;
    private double totalPayableBill;
    private int cashGivenByCustomer;
    private int changeOfCashGivenBackToCustomer;
    private LocalDateTime dateTimeOfBill;
    private String salesmanName;


    //----------------------------------------------  List Of All Medicines --------------------------------------------
    @Transient
    private Vector<PurchasedMedicines> listofAllMedicinesPurchased = new Vector<>();

    public Bills() {
        Random rand = new Random(System.currentTimeMillis());
        int number = rand.nextInt();
        this.billNo = "ABC_STR" + number;
    }

    @Id
    @Column(name = "billNo")
    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        if (billNo == null)
            this.billNo = "INVALID BILL";
        else
            this.billNo = billNo;
    }

    @Basic
    @Column(name = "customerName")
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        if (customerName == null || customerName == " " || customerName == "")
            this.customerName = "NO NAME";
        else
            this.customerName = customerName;
    }

    @Basic
    @Column(name = "customerPhoneNo")
    public String getCustomerPhoneNo() {
        return customerPhoneNo;
    }

    public void setCustomerPhoneNo(String customerPhoneNo) {
        if (customerPhoneNo == "" || customerPhoneNo == " " || customerPhoneNo == null)
            this.customerPhoneNo = "INVALID MOBILE NUMBER";
        else
            this.customerPhoneNo = customerPhoneNo;
    }

    @Basic
    @Column(name = "paymentMethod")
    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        if (paymentMethod == null || paymentMethod == " " || paymentMethod == "")
            this.paymentMethod = "INVALID METHOD";
        else
            this.paymentMethod = paymentMethod;
    }

    @Basic
    @Column(name = "totalAmountOfBill")
    public int getTotalAmountOfBill() {
        return totalAmountOfBill;
    }

    public void setTotalAmountOfBill(int totalAmountOfBill) {
        this.totalAmountOfBill = totalAmountOfBill;
    }

    @Basic
    @Column(name = "discountPercentage")
    public int getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(int discountPercentage) {
        if (discountPercentage < 0)
            this.discountPercentage = 0;
        else
            this.discountPercentage = discountPercentage;
    }

    @Basic
    @Column(name = "totalSavingsOnBill")
    public double getTotalSavingsOnBill() {
        return totalSavingsOnBill;
    }

    public void setTotalSavingsOnBill(double totalSavingsOnBill) {
        if(totalSavingsOnBill <0)
            this.totalSavingsOnBill=0;
        else
            this.totalSavingsOnBill = totalSavingsOnBill;
    }

    @Basic
    @Column(name = "totalPayableBill")
    public double getTotalPayableBill() {
        return totalPayableBill;
    }

    public void setTotalPayableBill(double totalPayableBill) {
        if(totalPayableBill <0)
            this.totalPayableBill=0;
        else
            this.totalPayableBill = totalPayableBill;
    }

    @Basic
    @Column(name = "cashGivenByCustomer")
    public int getCashGivenByCustomer() {
        return cashGivenByCustomer;
    }

    public void setCashGivenByCustomer(int cashGivenByCustomer) {
        if(cashGivenByCustomer <0)
            this.cashGivenByCustomer =0;
        else
            this.cashGivenByCustomer = cashGivenByCustomer;
    }

    @Basic
    @Column(name = "changeOfCashGivenBackToCustomer")
    public int getChangeOfCashGivenBackToCustomer() {
        return changeOfCashGivenBackToCustomer;
    }

    public void setChangeOfCashGivenBackToCustomer(int changeOfCashGivenBackToCustomer) {
        if (changeOfCashGivenBackToCustomer < 0)
            this.changeOfCashGivenBackToCustomer = 0;
        else
            this.changeOfCashGivenBackToCustomer = changeOfCashGivenBackToCustomer;
    }

    @Basic
    @Column(name = "dateTimeOfBill")
    public LocalDateTime getDateTimeOfBill() {
        return dateTimeOfBill;
    }

    public void setDateTimeOfBill(LocalDateTime dateTimeOfBill) {
        this.dateTimeOfBill = dateTimeOfBill;
    }

    @Basic
    @Column(name = "salesmanName")
    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        if (salesmanName == "" || salesmanName == " " || salesmanName == null)
            this.salesmanName = "INVALID NAME";
        else
            this.salesmanName = salesmanName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bills bills = (Bills) o;
        return totalAmountOfBill == bills.totalAmountOfBill && discountPercentage == bills.discountPercentage && Double.compare(bills.totalSavingsOnBill, totalSavingsOnBill) == 0 && Double.compare(bills.totalPayableBill, totalPayableBill) == 0 && cashGivenByCustomer == bills.cashGivenByCustomer && changeOfCashGivenBackToCustomer == bills.changeOfCashGivenBackToCustomer && Objects.equals(billNo, bills.billNo) && Objects.equals(customerName, bills.customerName) && Objects.equals(customerPhoneNo, bills.customerPhoneNo) && Objects.equals(paymentMethod, bills.paymentMethod) && Objects.equals(dateTimeOfBill, bills.dateTimeOfBill) && Objects.equals(salesmanName, bills.salesmanName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(billNo, customerName, customerPhoneNo, paymentMethod, totalAmountOfBill, discountPercentage, totalSavingsOnBill, totalPayableBill, cashGivenByCustomer, changeOfCashGivenBackToCustomer, dateTimeOfBill, salesmanName);
    }

    public void setListofAllMedicinesInCart(Vector<PurchasedMedicines> listofAllMedicinesInCart) {
        if (listofAllMedicinesInCart != null)
            this.listofAllMedicinesPurchased = listofAllMedicinesInCart;
        else
            this.listofAllMedicinesPurchased = null;
    }


    //---------------------------------------- getSystemDateTime -------------------------------------------------------
    public void setSystemTimeDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime dateTimeOfBill = LocalDateTime.now();
        this.setDateTimeOfBill(dateTimeOfBill);
    }

    public void setTotalMedicinesPurchased(Vector<PurchasedMedicines> totalMedicinesPurchased) {
        if (totalMedicinesPurchased != null)
            this.listofAllMedicinesPurchased = totalMedicinesPurchased;
        else
            this.listofAllMedicinesPurchased = null;
    }

    //------------------------------------ set change amount -----------------------------------------------------------
    public void setChangeAmount() {
        this.changeOfCashGivenBackToCustomer = (int) (this.cashGivenByCustomer - this.totalPayableBill);
    }


    @Transient
    public Vector<PurchasedMedicines> getListofAllMedicinesPurchased() {
        return listofAllMedicinesPurchased;
    }

    public void setListofAllMedicinesPurchased(Vector<PurchasedMedicines> listofAllMedicinesPurchased) {
        this.listofAllMedicinesPurchased = listofAllMedicinesPurchased;
    }

    //--------------------------------- To String Method ---------------------------------------------------------------

    @Override
    public String toString() {
        return "Bills{" +
                "billNo='" + billNo + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerPhoneNo='" + customerPhoneNo + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", totalAmountOfBill=" + totalAmountOfBill +
                ", discountPercentage=" + discountPercentage +
                ", totalSavingsOnBill=" + totalSavingsOnBill +
                ", totalPayableBill=" + totalPayableBill +
                ", cashGivenByCustomer=" + cashGivenByCustomer +
                ", changeOfCashGivenBackToCustomer=" + changeOfCashGivenBackToCustomer +
                ", dateTimeOfBill=" + dateTimeOfBill +
                ", salesmanName='" + salesmanName + '\'' +
                '}';
    }
}
