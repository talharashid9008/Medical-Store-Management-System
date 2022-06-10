package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeQuantityException;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "store_pharmacist", schema = "medical_store_management_system", catalog = "")
public class StorePharmacist {
    private int pharmacistCnic;
    private String pharmacistName;
    private String pharmacistAddress;
    private LocalDate pharmacistDob;
    private String pharmacistPhoneNo;
    private String pharmacistLicenseNo;
    private String pharmacistLoginUserName;
    private String pharmacistLoginPassword;

    @Id
    @Column(name = "pharmacist_CNIC")
    public int getPharmacistCnic() {
        return pharmacistCnic;
    }

    public void setPharmacistCnic(int pharmacistCnic) throws NegativeCINC_Number_Exception {
        if (pharmacistCnic < 0 || pharmacistCnic == 0) {
            throw new NegativeCINC_Number_Exception("CNIC cannot be negative");
        } else {
            this.pharmacistCnic = pharmacistCnic;
        }
    }

    @Basic
    @Column(name = "pharmacist_Name")
    public String getPharmacistName() {
        return pharmacistName;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    @Basic
    @Column(name = "pharmacist_Address")
    public String getPharmacistAddress() {
        return pharmacistAddress;
    }

    public void setPharmacistAddress(String pharmacistAddress) {
        this.pharmacistAddress = pharmacistAddress;
    }

    @Basic
    @Column(name = "pharmacist_DOB")
    public LocalDate getPharmacistDob() {
        return pharmacistDob;
    }

    public void setPharmacistDob(LocalDate pharmacistDob) throws Exception {
        LocalDate currentDate = LocalDate.now();
        if (pharmacistDob == null || currentDate.isBefore(pharmacistDob)) {
            throw new Exception("Either Date Of Birth Format is Wrong or You have Entered A Wrong Date Like Today's Date or Future Date");
        } else {
            this.pharmacistDob = pharmacistDob;
        }
    }

    @Basic
    @Column(name = "pharmacist_PhoneNO")
    public String getPharmacistPhoneNo() {
        return pharmacistPhoneNo;
    }

    public void setPharmacistPhoneNo(String pharmacistPhoneNo) {
        this.pharmacistPhoneNo = pharmacistPhoneNo;
    }

    @Basic
    @Column(name = "pharmacist_LicenseNo")
    public String getPharmacistLicenseNo() {
        return pharmacistLicenseNo;
    }

    public void setPharmacistLicenseNo(String pharmacistLicenseNo) {
        this.pharmacistLicenseNo = pharmacistLicenseNo;
    }

    @Basic
    @Column(name = "pharmacist_Login_UserName")
    public String getPharmacistLoginUserName() {
        return pharmacistLoginUserName;
    }

    public void setPharmacistLoginUserName(String pharmacistLoginUserName) {
        this.pharmacistLoginUserName = pharmacistLoginUserName;
    }

    @Basic
    @Column(name = "pharmacist_Login_Password")
    public String getPharmacistLoginPassword() {
        return pharmacistLoginPassword;
    }

    public void setPharmacistLoginPassword(String pharmacistLoginPassword) {
        this.pharmacistLoginPassword = pharmacistLoginPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StorePharmacist that = (StorePharmacist) o;
        return pharmacistCnic == that.pharmacistCnic && Objects.equals(pharmacistName, that.pharmacistName) && Objects.equals(pharmacistAddress, that.pharmacistAddress) && Objects.equals(pharmacistDob, that.pharmacistDob) && Objects.equals(pharmacistPhoneNo, that.pharmacistPhoneNo) && Objects.equals(pharmacistLicenseNo, that.pharmacistLicenseNo) && Objects.equals(pharmacistLoginUserName, that.pharmacistLoginUserName) && Objects.equals(pharmacistLoginPassword, that.pharmacistLoginPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pharmacistCnic, pharmacistName, pharmacistAddress, pharmacistDob, pharmacistPhoneNo, pharmacistLicenseNo, pharmacistLoginUserName, pharmacistLoginPassword);
    }


    //------------------ To String --------------------
    @Override
    public String toString() {
        return "StorePharmacist{" +
                "pharmacistCnic=" + pharmacistCnic +
                ", pharmacistName='" + pharmacistName + '\'' +
                ", pharmacistAddress='" + pharmacistAddress + '\'' +
                ", pharmacistDob=" + pharmacistDob +
                ", pharmacistPhoneNo='" + pharmacistPhoneNo + '\'' +
                ", pharmacistLicenseNo='" + pharmacistLicenseNo + '\'' +
                ", pharmacistLoginUserName='" + pharmacistLoginUserName + '\'' +
                ", pharmacistLoginPassword='" + pharmacistLoginPassword + '\'' +
                '}';
    }
}
