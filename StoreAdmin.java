package com.medical_store_management_system.Business_Logic;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "store_admin", schema = "medical_store_management_system", catalog = "")
public class StoreAdmin {
    private int adminCnic;
    private String adminName;
    private String adminAddress;
    private String adminLoginUserName;
    private String adminLoginPassword;

    @Id
    @Column(name = "admin_CNIC")
    public int getAdminCnic() {
        return adminCnic;
    }

    public void setAdminCnic(int adminCnic) throws NegativeCINC_Number_Exception {
        if(adminCnic <0 || adminCnic ==0)
            throw new NegativeCINC_Number_Exception("Negative CNICN Number is Not Allowed");
        else
            this.adminCnic = adminCnic;
    }

    @Basic
    @Column(name = "admin_name")
    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    @Basic
    @Column(name = "admin_Address")
    public String getAdminAddress() {
        return adminAddress;
    }

    public void setAdminAddress(String adminAddress) {
        this.adminAddress = adminAddress;
    }

    @Basic
    @Column(name = "admin_Login_UserName")
    public String getAdminLoginUserName() {
        return adminLoginUserName;
    }

    public void setAdminLoginUserName(String adminLoginUserName) {
        this.adminLoginUserName = adminLoginUserName;
    }

    @Basic
    @Column(name = "admin_Login_Password")
    public String getAdminLoginPassword() {
        return adminLoginPassword;
    }

    public void setAdminLoginPassword(String adminLoginPassword) {
        this.adminLoginPassword = adminLoginPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StoreAdmin that = (StoreAdmin) o;
        return adminCnic == that.adminCnic && Objects.equals(adminName, that.adminName) && Objects.equals(adminAddress, that.adminAddress) && Objects.equals(adminLoginUserName, that.adminLoginUserName) && Objects.equals(adminLoginPassword, that.adminLoginPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(adminCnic, adminName, adminAddress, adminLoginUserName, adminLoginPassword);
    }

    //--------- To String ---------------------------
    @Override
    public String toString() {
        return "StoreAdmin{" +
                "adminCnic=" + adminCnic +
                ", adminName='" + adminName + '\'' +
                ", adminAddress='" + adminAddress + '\'' +
                ", adminLoginUserName='" + adminLoginUserName + '\'' +
                ", adminLoginPassword='" + adminLoginPassword + '\'' +
                '}';
    }
}
