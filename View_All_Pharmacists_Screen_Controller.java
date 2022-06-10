package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.Business_Logic.StorePharmacist;
import com.medical_store_management_system.Business_Logic.StoreSalesman;
import com.medical_store_management_system.Database_Connectivity.StorePharmacist_Connection_DB;
import com.medical_store_management_system.Database_Connectivity.StoreSalesman_Connection_DB;
import com.medical_store_management_system.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class View_All_Pharmacists_Screen_Controller implements Initializable {

    @FXML
    private TableView<StorePharmacist> Pharmacist_Table;

    @FXML
    private TableColumn<StorePharmacist, String> pharm_CNIC;

    @FXML
    private TableColumn<StorePharmacist, String> phram_Address;

    @FXML
    private TableColumn<StorePharmacist, LocalDate> phram_DOB;

    @FXML
    private TableColumn<StorePharmacist, String> phram_LicenseNo;

    @FXML
    private TableColumn<StorePharmacist, String> phram_LoginPassword;

    @FXML
    private TableColumn<StorePharmacist, String> phram_LoginUserName;

    @FXML
    private TableColumn<StorePharmacist, String> phram_Name;

    @FXML
    private TableColumn<StorePharmacist, String> phram_PhoneNumber;

    @FXML
    private TextField searchKeyWord_TextField;

    ObservableList<StorePharmacist> allStorePharmacists = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello");
        pharm_CNIC.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistCnic"));
        phram_Name.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistName"));
        phram_Address.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistAddress"));
        phram_DOB.setCellValueFactory(new PropertyValueFactory<StorePharmacist, LocalDate>("pharmacistDob"));
        phram_PhoneNumber.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistPhoneNo"));
        phram_LicenseNo.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistLicenseNo"));
        phram_LoginUserName.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistLoginUserName"));
        phram_LoginPassword.setCellValueFactory(new PropertyValueFactory<StorePharmacist, String>("pharmacistLoginPassword"));


        allStorePharmacists = StorePharmacist_Connection_DB.fetchAllPharmacistRecords();
        Pharmacist_Table.setItems(allStorePharmacists);

        //-------- Intialize the Filtered List
        FilteredList<StorePharmacist> filteredData =new FilteredList<>(allStorePharmacists,b->true);
        searchKeyWord_TextField.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(Store_Pharmacist ->{

                //if no search value then display all the records or whatever records it have. no changes
                if(newValue.isEmpty() || newValue.isBlank() ||newValue==null)
                {
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();
                if(Store_Pharmacist.getPharmacistName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (String.valueOf(Store_Pharmacist.getPharmacistCnic()).toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Pharmacist.getPharmacistAddress().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Pharmacist.getPharmacistPhoneNo().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Pharmacist.getPharmacistLicenseNo().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Pharmacist.getPharmacistLoginUserName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Pharmacist.getPharmacistLoginPassword().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<StorePharmacist> storedData=new SortedList<>(filteredData);

        // Binding the sorted result with Table View
        storedData.comparatorProperty().bind(Pharmacist_Table.comparatorProperty());

        //apply filtered and sorted data to the table view
        Pharmacist_Table.setItems(storedData);
    }

    //----------------------------------------- Delete A Pharmacist Record ---------------------------------------------
    @FXML
    private TextField masterPasswordField;

    @FXML
    void perform_DeleteSalesman(ActionEvent event) {
        StorePharmacist selectPharmacist= Pharmacist_Table.getSelectionModel().getSelectedItem();
        System.out.println("Deleter Press");
        //---------------------------- Nothing has been selected and clicked the button---------------------------------
        if(selectPharmacist ==null)
        {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Medicine is Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please Select A Medicine From the table that you want to delete");
            alert.showAndWait();
            return;
        }


        if(masterPasswordField.getText().equals(main.getMasterPassword()))
        {
            if(StorePharmacist_Connection_DB.deleteParticularPharmacistRecord(selectPharmacist))
            {
                Alert alertSuccess= new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Delete A Salesman Record ");
                alertSuccess.setHeaderText("Delete A Salesman Record Success Massage.");
                alertSuccess.setContentText("Confirmation Massage : The Record has been delete successfully :\n");
                alertSuccess.showAndWait();
            }
            else
            {
                Alert alertError= new Alert(Alert.AlertType.ERROR);
                alertError.setTitle("Delete A Salesman Record ");
                alertError.setHeaderText("Error In Deleting A Salesman Record");
                alertError.setContentText("Error Massage : The Record has been delete successfully :\n");
                alertError.showAndWait();
            }
        }
        else
        {
            Alert alertError= new Alert(Alert.AlertType.ERROR);
            alertError.setTitle("Invalid Master Password");
            alertError.setHeaderText("Cannot Delete Salesman Record.");
            alertError.setContentText("Please Enter The Correct Master Password To Delete A Particular Record :\n");
            alertError.showAndWait();
        }

//      Now Update the table view
        allStorePharmacists = StorePharmacist_Connection_DB.fetchAllPharmacistRecords();
        Pharmacist_Table.setItems(allStorePharmacists);
    }
}
