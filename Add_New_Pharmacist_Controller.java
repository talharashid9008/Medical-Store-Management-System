package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import com.medical_store_management_system.Business_Logic.StorePharmacist;
import com.medical_store_management_system.Database_Connectivity.StorePharmacist_Connection_DB;
import com.medical_store_management_system.Business_Logic.Utility.Filing_System.Pharmacist_FileHandling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Add_New_Pharmacist_Controller implements Initializable {
    @FXML
    private Button addNewPharmacist_BTN;

    @FXML
    private TextField newPharmacist_Address;

    @FXML
    private TextField newPharmacist_CNIC;

    @FXML
    private DatePicker newPharmacist_DOB;

    @FXML
    private TextField newPharmacist_License;

    @FXML
    private TextField newPharmacist_Name;

    @FXML
    private TextField newPharmacist_Password;

    @FXML
    private TextField newPharmacist_PhoneNo;

    @FXML
    private Label newPharmacist_Status;

    @FXML
    private TextField newPharmacist_UserName;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addNewPharmacist_BTN.setDisable(true);
    }

    @FXML
    void addNewPharmacist(ActionEvent event) {
        boolean holdDatabaseInsertion = false;
        //------------------------------- Taking Data From the Input Field of the Form Add New Pharmacist --------------
        String newPharmacist_CNIC_str = newPharmacist_CNIC.getText();
        String newPharmacist_Name_str = newPharmacist_Name.getText();
        String newPharmacist_Address_str = newPharmacist_Address.getText();
        LocalDate newPharmacist_DOB_str = newPharmacist_DOB.getValue();
        String newPharmacist_Password_str = newPharmacist_Password.getText();
        String newPharmacist_UserName_str = newPharmacist_UserName.getText();
        String newPharmacist_PhoneNo_str = newPharmacist_PhoneNo.getText();
        String newPharmacist_License_str = newPharmacist_License.getText();

        //--------------- Making a New Object For New Pharmacist ------------------------------------------------------
        StorePharmacist newPharmacist = new StorePharmacist();
        newPharmacist.setPharmacistName(newPharmacist_Name_str);
        try {
            int intCINC = Integer.valueOf(newPharmacist_CNIC_str);
            newPharmacist.setPharmacistCnic(intCINC);
        } catch (NegativeCINC_Number_Exception e) {
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("CNIC Cannot Be Negative");
            alertDeleteConfirm.setHeaderText("Sales Man CNIC Value Cannot be Negative Or Less Than 0 ");
            alertDeleteConfirm.setContentText("Kindly Fix The CNIC Value");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        } catch (NumberFormatException E) {
            //----------------------------------------------------- Exception For Type Mismatching : CNIC of Pharmacist------
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Change the Dealer Price Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        try {
            newPharmacist.setPharmacistDob(newPharmacist_DOB_str);
        } catch (Exception e) {
//            e.printStackTrace();
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Expiry Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type\n Or The Date is Too Greater");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }
        newPharmacist.setPharmacistAddress(newPharmacist_Address_str);
        newPharmacist.setPharmacistPhoneNo(newPharmacist_PhoneNo_str);
        newPharmacist.setPharmacistLicenseNo(newPharmacist_License_str);
        newPharmacist.setPharmacistLoginUserName(newPharmacist_UserName_str);
        newPharmacist.setPharmacistLoginPassword(newPharmacist_Password_str);

        System.out.println(newPharmacist);

        //------------ Inserting the New Pharmacist in the Database ----------------------------------------------------
       if(holdDatabaseInsertion ==false)
       {
           if (StorePharmacist_Connection_DB.insertPharmacist(newPharmacist)) {
               newPharmacist_Status.setText("New Pharmacist Added Successfully");
               Pharmacist_FileHandling.insertPharmacistInFile(newPharmacist);
           } else {
               newPharmacist_Status.setText("New Pharmacist Added Unsuccessfully");
           }
       }
       else
       {
           Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
           alertDeleteConfirm.setTitle("There are possible issues With Your Inputs");
           alertDeleteConfirm.setHeaderText("Cannot Add New Medicine Becuase Your Inputs Are Not Upto the mark ");
           alertDeleteConfirm.setContentText("Kindly Fix The Values That Were Described By The Pop Up");
           Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
           newPharmacist_Status.setText("Fix The Issue Value");
       }
    }

    // Disable Add User Button if No Text Field Enter
    @FXML
    public void handleKeyReleased() {
//        if no text is entered then we will disable the login button if text is space also lock the button
        String newPharmacist_Name_str = newPharmacist_Name.getText();
        String newPharmacist_Address_str = newPharmacist_Address.getText();
        String newPharmacist_CNIC_str = newPharmacist_CNIC.getText();
        String newPharmacist_Password_str = newPharmacist_Password.getText();
        String newPharmacist_UserName_str = newPharmacist_UserName.getText();
        String newPharmacist_PhoneNo_str = newPharmacist_PhoneNo.getText();
        LocalDate newPharmacist_DOB_str = newPharmacist_DOB.getValue();
        String newPharmacist_License_str = newPharmacist_License.getText();


//
        boolean disableButtons = (newPharmacist_Name_str.isEmpty() || newPharmacist_Name_str.trim().isEmpty()) ||
                (newPharmacist_Address_str.isEmpty() || newPharmacist_Address_str.trim().isEmpty()) ||
                (newPharmacist_CNIC_str.isEmpty() || newPharmacist_CNIC_str.trim().isEmpty()) ||
                (newPharmacist_Password_str.isEmpty() || newPharmacist_Password_str.trim().isEmpty()) ||
                (newPharmacist_UserName_str.isEmpty() || newPharmacist_UserName_str.trim().isEmpty()) ||
                (newPharmacist_License_str.isEmpty() || newPharmacist_License_str.trim().isEmpty()) ||
                (newPharmacist_PhoneNo_str.isEmpty() || newPharmacist_PhoneNo_str.trim().isEmpty());
        addNewPharmacist_BTN.setDisable(disableButtons);
    }


}
