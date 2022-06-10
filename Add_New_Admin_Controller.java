package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import com.medical_store_management_system.Business_Logic.StoreAdmin;
import com.medical_store_management_system.Database_Connectivity.StoreAdmin_Connection_DB;
import com.medical_store_management_system.Business_Logic.Utility.Filing_System.Admins_FileHandling;
import com.medical_store_management_system.main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Optional;

public class Add_New_Admin_Controller {
    @FXML
    private TextField adminAddress;

    @FXML
    private TextField adminCNIC;

    @FXML
    private TextField adminName;

    @FXML
    private TextField adminPassword;

    @FXML
    private TextField adminUsername;

    @FXML
    private TextField masterPassword;

    @FXML
    private Label add_status;

    @FXML
    void perform_AddAdmin(ActionEvent event) {
        boolean holdDatabaseInsertion = false;
        String adminName_Str = adminName.getText();
        String adminPassword_Str = adminPassword.getText();
        String adminAddress_Str = adminAddress.getText();
        int adminCINC_Str = 0;
        //--------------------------- Checking Input Mismatch For CNIC Exception ---------------------------------------
        try {
            adminCINC_Str = Integer.parseInt(adminCNIC.getText());
        } catch (NumberFormatException E) {
            System.out.println(E);
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Expiry Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type\n Or The Date is Too Greater");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();

        }

        String adminUsername_Str = adminUsername.getText();
        String masterPassword_Str = masterPassword.getText();


        if (masterPassword_Str.equals(main.getMasterPassword())) {
            StoreAdmin newAdmin = new StoreAdmin();
            newAdmin.setAdminName(adminName_Str);
            newAdmin.setAdminAddress(adminAddress_Str);
            try {
                newAdmin.setAdminCnic(adminCINC_Str);
            } catch (NegativeCINC_Number_Exception e) {
                Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
                alertDeleteConfirm.setTitle("Negative CNIC Number is Not Allowed ");
                alertDeleteConfirm.setHeaderText("Setup Admin CNIC : Cannot set the admin CNIC to be a negative number or Less than Zero Number");
                alertDeleteConfirm.setContentText("Kindly Fix this isse");
                Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
                add_status.setText("Admin CNIC is Negative Exception");
            }
            newAdmin.setAdminLoginUserName(adminUsername_Str);
            newAdmin.setAdminLoginPassword(adminPassword_Str);

            //------------ Inserting the New Admin in the Database ----------------------------------------------------
            if (holdDatabaseInsertion == false) {
                if (StoreAdmin_Connection_DB.insertAdmin(newAdmin)) {
                    add_status.setText("New Admin Added Successfully");
                    Admins_FileHandling.insertAdminInFile(newAdmin);
                } else {
                    add_status.setText("New Admin Added Unsuccessfully");
                }
            } else {
                Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
                alertDeleteConfirm.setTitle("There are possible issues With Your Inputs");
                alertDeleteConfirm.setHeaderText("Cannot Add New Medicine Because Your Inputs Are Not Upto the mark ");
                alertDeleteConfirm.setContentText("Kindly Fix The Values That Were Described By The Pop Up");
                Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
                add_status.setText("Fix The Issue Value");
            }

        } else {
            add_status.setText("Invalid Master Password. Find The Real Admin");
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Invalid Master Password");
            alertDeleteConfirm.setHeaderText("Cannot Add The New Admin Because You Don't Know The Master Password");
            alertDeleteConfirm.setContentText("Kindly Find The Store Owner To Add The New Admin");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

    }
}
