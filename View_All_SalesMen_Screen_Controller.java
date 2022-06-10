package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.Business_Logic.Medicines;
import com.medical_store_management_system.Business_Logic.StoreSalesman;
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

public class View_All_SalesMen_Screen_Controller implements Initializable {

    @FXML
    private TableView<StoreSalesman> All_SaleMan_Table;
    @FXML
    private TableColumn<StoreSalesman, String> col_Salesman_Address;

    @FXML
    private TableColumn<StoreSalesman, String> col_Salesman_CNIC;

    @FXML
    private TableColumn<StoreSalesman, LocalDate> col_Salesman_DOB;

    @FXML
    private TableColumn<StoreSalesman, String> col_Salesman_LoginPassword;

    @FXML
    private TableColumn<StoreSalesman, String> col_Salesman_LoginUsername;

    @FXML
    private TableColumn<StoreSalesman, String> col_Salesman_Name;

    @FXML
    private TableColumn<StoreSalesman, String> col_Salesman_PhoneNo;

    @FXML
    private TextField searchKeyword_SalesMan;

    ObservableList<StoreSalesman> allStoreSalesMen = FXCollections.observableArrayList();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("hello");
        col_Salesman_CNIC.setCellValueFactory(new PropertyValueFactory<StoreSalesman, String>("salesmanCnic"));
        col_Salesman_Name.setCellValueFactory(new PropertyValueFactory<StoreSalesman, String>("salesmanName"));
        col_Salesman_Address.setCellValueFactory(new PropertyValueFactory<StoreSalesman, String>("salesmanAddress"));
        col_Salesman_PhoneNo.setCellValueFactory(new PropertyValueFactory<StoreSalesman, String>("salesmanPhoneNo"));
        col_Salesman_DOB.setCellValueFactory(new PropertyValueFactory<StoreSalesman, LocalDate>("salesmanDob"));
        col_Salesman_LoginUsername.setCellValueFactory(new PropertyValueFactory<StoreSalesman, String>("salesmanLoginUserName"));
        col_Salesman_LoginPassword.setCellValueFactory(new PropertyValueFactory<StoreSalesman, String>("salesmanLoginPassword"));

        allStoreSalesMen = StoreSalesman_Connection_DB.fetchAllSalesManRecords();
        All_SaleMan_Table.setItems(allStoreSalesMen);


        //------------ Searching Feature Implementation ----------------------------------------------------------------
        //-------- Intialize the Filtered List
        FilteredList<StoreSalesman> filteredData =new FilteredList<>(allStoreSalesMen, b->true);
        searchKeyword_SalesMan.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(Store_Sales_Man ->{

                //if no search value then display all the records or whatever records it have. no changes
                if(newValue.isEmpty() || newValue.isBlank() ||newValue==null)
                {
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();
                if(Store_Sales_Man.getSalesmanName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (String.valueOf(Store_Sales_Man.getSalesmanCnic()).toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Sales_Man.getSalesmanAddress().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Sales_Man.getSalesmanPhoneNo().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Sales_Man.getSalesmanLoginUserName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Sales_Man.getSalesmanLoginPassword().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Store_Sales_Man.getSalesmanDob().toString().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<StoreSalesman> storedData=new SortedList<>(filteredData);

        // Binding the sorted result with Table View
        storedData.comparatorProperty().bind(All_SaleMan_Table.comparatorProperty());

        //apply filtered and sorted data to the table view
        All_SaleMan_Table.setItems(storedData);
    }

    //------------------------------------- In Order To Delete A Particular Record -------------------------------------
    @FXML
    private TextField masterPasswordField;

    @FXML
    void perform_DeleteSalesman(ActionEvent event) {
        StoreSalesman selectedSalesMan= All_SaleMan_Table.getSelectionModel().getSelectedItem();
        System.out.println("Deleter Press");
        //---------------------------- Nothing has been selected and clicked the button---------------------------------
        if(selectedSalesMan ==null)
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
            if(StoreSalesman_Connection_DB.deleteAParticularSalmanRecord(selectedSalesMan))
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
        allStoreSalesMen = StoreSalesman_Connection_DB.fetchAllSalesManRecords();
        All_SaleMan_Table.setItems(allStoreSalesMen);
    }
}
