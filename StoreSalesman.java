package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "store_salesman", schema = "medical_store_management_system", catalog = "")
public class StoreSalesman {
    private int salesmanCnic;
    private String salesmanName;
    private String salesmanAddress;
    private LocalDate salesmanDob;
    private String salesmanPhoneNo;
    private String salesmanLoginUserName;
    private String salesmanLoginPassword;

    @Id
    @Column(name = "salesman_CNIC")
    public int getSalesmanCnic() {
        return salesmanCnic;
    }

    public void setSalesmanCnic(int salesmanCnic) throws NegativeCINC_Number_Exception {
        if(salesmanCnic <0 || salesmanCnic ==0)
        {
            throw new NegativeCINC_Number_Exception("CNIC cannot be negative");
        }else
        {
            this.salesmanCnic=salesmanCnic;
        }
    }

    @Basic
    @Column(name = "salesman_Name")
    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    @Basic
    @Column(name = "salesman_Address")
    public String getSalesmanAddress() {
        return salesmanAddress;
    }

    public void setSalesmanAddress(String salesmanAddress) {
        this.salesmanAddress = salesmanAddress;
    }

    @Basic
    @Column(name = "salesman_DOB")
    public LocalDate getSalesmanDob() {
        return salesmanDob;
    }

    public void setSalesmanDob(LocalDate salesmanDob) throws Exception{
        LocalDate currentDate=LocalDate.now();
        if(salesmanDob ==null || currentDate.isBefore(salesmanDob))
        {
            throw new Exception("Date of Birth Date Issie");
        }
        else
        {
            this.salesmanDob = salesmanDob;
        }
    }

    @Basic
    @Column(name = "salesman_PhoneNO")
    public String getSalesmanPhoneNo() {
        return salesmanPhoneNo;
    }

    public void setSalesmanPhoneNo(String salesmanPhoneNo) {
        this.salesmanPhoneNo = salesmanPhoneNo;
    }

    @Basic
    @Column(name = "salesman_Login_UserName")
    public String getSalesmanLoginUserName() {
        return salesmanLoginUserName;
    }

    public void setSalesmanLoginUserName(String salesmanLoginUserName) {
        this.salesmanLoginUserName = salesmanLoginUserName;
    }

    @Basic
    @Column(name = "salesman_Login_Password")
    public String getSalesmanLoginPassword() {
        return salesmanLoginPassword;
    }

    public void setSalesmanLoginPassword(String salesmanLoginPassword) {
        this.salesmanLoginPassword = salesmanLoginPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreSalesman that = (StoreSalesman) o;
        return salesmanCnic == that.salesmanCnic && Objects.equals(salesmanName, that.salesmanName) && Objects.equals(salesmanAddress, that.salesmanAddress) && Objects.equals(salesmanDob, that.salesmanDob) && Objects.equals(salesmanPhoneNo, that.salesmanPhoneNo) && Objects.equals(salesmanLoginUserName, that.salesmanLoginUserName) && Objects.equals(salesmanLoginPassword, that.salesmanLoginPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(salesmanCnic, salesmanName, salesmanAddress, salesmanDob, salesmanPhoneNo, salesmanLoginUserName, salesmanLoginPassword);
    }

    //------------------------- To Strings -------------------------
    @Override
    public String toString() {
        return "StoreSalesman{" +
                "salesmanCnic=" + salesmanCnic +
                ", salesmanName='" + salesmanName + '\'' +
                ", salesmanAddress='" + salesmanAddress + '\'' +
                ", salesmanDob=" + salesmanDob +
                ", salesmanPhoneNo='" + salesmanPhoneNo + '\'' +
                ", salesmanLoginUserName='" + salesmanLoginUserName + '\'' +
                ", salesmanLoginPassword='" + salesmanLoginPassword + '\'' +
                '}';
    }
}
