package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeCINC_Number_Exception;
import com.medical_store_management_system.Business_Logic.StoreSalesman;
import com.medical_store_management_system.Database_Connectivity.StoreSalesman_Connection_DB;
import com.medical_store_management_system.Business_Logic.Utility.Filing_System.Salesman_FileHandling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Add_New_SalesMan_Controller implements Initializable {
    @FXML
    private Button newSalesman_Add_Button;

    @FXML
    private TextField newSalesman_Address;

    @FXML
    private TextField newSalesman_CNIC;

    @FXML
    private DatePicker newSalesman_DOB;

    @FXML
    private TextField newSalesman_Name;

    @FXML
    private TextField newSalesman_Password;

    @FXML
    private TextField newSalesman_PhoneNo;

    @FXML
    private Label newSalesman_Status;

    @FXML
    private TextField newSalesman_UserName;

    @FXML
    void addNewSalesman(ActionEvent event) {
        boolean holdDatabaseInsertion = false;
        String newSales_Name_str = newSalesman_Name.getText();
        String newSalesman_Address_str = newSalesman_Address.getText();
        String newSalesman_PhoneNo_str = newSalesman_PhoneNo.getText();
        String newSalesman_Password_str = newSalesman_Password.getText();
        String newSalesman_CNIC_str = newSalesman_CNIC.getText();
        String newSalesman_UserName_str = newSalesman_UserName.getText();
        LocalDate newSalesman_DOB_str = newSalesman_DOB.getValue();

        //----------------------------------------- Creating a New Salesman Object -------------------------------------------------------------
        StoreSalesman newSalesMan = new StoreSalesman();

        newSalesMan.setSalesmanName(newSales_Name_str);
        newSalesMan.setSalesmanLoginUserName(newSalesman_UserName_str);
        newSalesMan.setSalesmanLoginPassword(newSalesman_Password_str);
        newSalesMan.setSalesmanAddress(newSalesman_Address_str);
        newSalesMan.setSalesmanPhoneNo(newSalesman_PhoneNo_str);

        //----------------------------------------- Checking Negative CNIC Value Exception -----------------------------------------------------
        try {
            int intCNIC_No = Integer.valueOf(newSalesman_CNIC_str);
            newSalesMan.setSalesmanCnic(intCNIC_No);
        } catch (NegativeCINC_Number_Exception e) {
            System.out.println(e);
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("CNIC Field : Cannot Be Negative");
            alertDeleteConfirm.setHeaderText("Sales Man CNIC Value Cannot be Negative Or Less Than 0 ");
            alertDeleteConfirm.setContentText("Kindly Fix The CNIC Value");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }catch (NumberFormatException E) {
            //----------------------------------------------------- Exception For Type Mismatching : Dealer Price ------
            System.out.println(E);
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("CNIC Field :Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("CNIC Field : String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("CNIC Field : You have entered a string in the integer value data type variable. Kindly Change the Dealer Price Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        //---------------------------------------- Checking The Date of Birth -------------------------------------------------------------------
        try {
            newSalesMan.setSalesmanDob(newSalesman_DOB_str);
        } catch (Exception e) {
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Manufacture Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type\n Or The Date is Too Greater");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        //----------------------------------------- Inserting new Salesman To The Database ------------------------------------------------------
        if (holdDatabaseInsertion == false) {
            if (StoreSalesman_Connection_DB.insertNewSalesMan(newSalesMan)) {
                newSalesman_Status.setText("New Sales Man Added Successful");
                Salesman_FileHandling.insertNewSalesmanInFile(newSalesMan);
            } else {
                newSalesman_Status.setText("New Sales Man Added Unsuccessful");
            }
        }
        else {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("There are possible issues With Your Inputs");
            alertDeleteConfirm.setHeaderText("Cannot Add New Medicine Becuase Your Inputs Are Not Upto the mark ");
            alertDeleteConfirm.setContentText("Kindly Fix The Values That Were Described By The Pop Up");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
            newSalesman_Status.setText("Fix The Issue Value");
        }
    }

    // Disable Add User Button if No Text Field Enter
    @FXML
    public void handleKeyReleased() {
//        if no text is entered then we will disable the login button if text is space also lock the button
        String newSales_Name_str = newSalesman_Name.getText();
        String newSalesman_Address_str = newSalesman_Address.getText();
        String newSalesman_PhoneNo_str = newSalesman_PhoneNo.getText();
        String newSalesman_Password_str = newSalesman_Password.getText();
        String newSalesman_CNIC_str = newSalesman_CNIC.getText();
        String newSalesman_UserName_str = newSalesman_UserName.getText();


//
        boolean disableButtons = (newSales_Name_str.isEmpty() || newSales_Name_str.trim().isEmpty()) ||
                (newSalesman_Address_str.isEmpty() || newSalesman_Address_str.trim().isEmpty()) ||
                (newSalesman_PhoneNo_str.isEmpty() || newSalesman_PhoneNo_str.trim().isEmpty()) ||
                (newSalesman_Password_str.isEmpty() || newSalesman_Password_str.trim().isEmpty()) ||
                (newSalesman_CNIC_str.isEmpty() || newSalesman_CNIC_str.trim().isEmpty()) ||
                (newSalesman_UserName_str.isEmpty() || newSalesman_UserName_str.trim().isEmpty());
        newSalesman_Add_Button.setDisable(disableButtons);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newSalesman_Add_Button.setDisable(true);
    }
}
