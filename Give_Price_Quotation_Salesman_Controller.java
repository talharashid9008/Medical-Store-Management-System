package com.medical_store_management_system.GUI_Layer.SalesMan_Controllers;


import com.medical_store_management_system.Business_Logic.Medicines;
import com.medical_store_management_system.Database_Connectivity.Medicine_Connection_DB;
import com.medical_store_management_system.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Give_Price_Quotation_Salesman_Controller implements Initializable {
    @FXML
    private TableView<Medicines> All_Medicine_Table;

    @FXML
    private TableColumn<Medicines, String > colMGenericName;

    @FXML
    private TableColumn<Medicines, String> colMedicineDossage;

    @FXML
    private TableColumn<Medicines, LocalDate> col_DOManufacture;

    @FXML
    private TableColumn<Medicines, LocalDate> col_DateExpiry;

    @FXML
    private TableColumn<Medicines, String> col_DrugCode;

    @FXML
    private TableColumn<Medicines, Integer> col_MDealerPrice;

    @FXML
    private TableColumn<Medicines, String> col_MManufactureName;

    @FXML
    private TableColumn<Medicines, Integer> col_MRetailPrice;

    @FXML
    private TableColumn<Medicines, String> col_MedicineBatchNo;

    @FXML
    private TableColumn<Medicines, String> col_MedicineName;

    @FXML
    private TableColumn<Medicines, String> col_MedicineType;

    @FXML
    private TableColumn<Medicines, String> col_Medicine_Description;

    @FXML
    private TableColumn<Medicines, Integer> col_Quantity;

    @FXML
    private Button givePriceQuotation_BTN;

    @FXML
    private TextField searchKeyword_MedicineInventory;

    ObservableList<Medicines> allMedicineList= FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        col_MedicineName.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineName"));
        col_DrugCode.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineDrugCode"));
        col_MedicineBatchNo.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineBatchNo"));
        col_MRetailPrice.setCellValueFactory(new PropertyValueFactory<Medicines, Integer>("medicineRetailPrice"));
        col_MDealerPrice.setCellValueFactory(new PropertyValueFactory<Medicines, Integer>("medicineDealerPrice"));
        col_MManufactureName.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineManufacturerName"));
        colMedicineDossage.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineDosage"));
        col_DOManufacture.setCellValueFactory(new PropertyValueFactory<Medicines, LocalDate>("medicineDateOfManufacture"));
        col_DateExpiry.setCellValueFactory(new PropertyValueFactory<Medicines, LocalDate>("medicineDateOfExpiry"));
        col_Quantity.setCellValueFactory(new PropertyValueFactory<Medicines, Integer>("medicineQuantity"));
        col_Medicine_Description.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineDescription"));
        col_MedicineType.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineType"));
        colMGenericName.setCellValueFactory(new PropertyValueFactory<Medicines, String>("medicineGenericName"));

        try {
            allMedicineList= Medicine_Connection_DB.fetchAllMedicines();
        } catch (Exception e) {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Some Database Issue");
            alert.setHeaderText(null);
            alert.setContentText("There is an issue occurend while fetching from Database. Kindly check if there is a Negative value in quantity/price. Kindly see Database");
            alert.showAndWait();
            return;
        }
        All_Medicine_Table.setItems(allMedicineList);

        //--------------------- Implementing Search Functionality ------------------------------------------------------
//------------ Searching Feature Implementation ----------------------------------------------------------------
        //-------- Intialize the Filtered List
        FilteredList<Medicines> filteredData =new FilteredList<>(allMedicineList, b->true);
        searchKeyword_MedicineInventory.textProperty().addListener((observable, oldValue, newValue) ->{
            filteredData.setPredicate(Medicine ->{

                //if no search value then display all the records or whatever records it have. no changes
                if(newValue.isEmpty() || newValue.isBlank() ||newValue==null)
                {
                    return true;
                }
                String searchKeyWord=newValue.toLowerCase();
                if(Medicine.getMedicineName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineDrugCode().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineBatchNo().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineManufacturerName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineDosage().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineDateOfManufacture().toString().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineDateOfExpiry().toString().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineType().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineDescription().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else if (Medicine.getMedicineGenericName().toLowerCase().indexOf(searchKeyWord) >-1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Medicines> storedData=new SortedList<>(filteredData);

        // Binding the sorted result with Table View
        storedData.comparatorProperty().bind(All_Medicine_Table.comparatorProperty());

        //apply filtered and sorted data to the table view
        All_Medicine_Table.setItems(storedData);
    }

    @FXML
    void perform_GivePriceQuotation(ActionEvent event) throws IOException {
        Medicines selectedMedicine= All_Medicine_Table.getSelectionModel().getSelectedItem();
        System.out.println("Editor Press");
        //---------------------------- Nothing has been selected and clicked the button---------------------------------
        if(selectedMedicine ==null)
        {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Medicine is Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please Select A Medicine From the table that you want to give price quotation");
            alert.showAndWait();
            return;
        }

        FXMLLoader fxmlLoader=new FXMLLoader();
        fxmlLoader.setLocation(main.class.getResource("Price_Quote_Calculation_Dialog_Salesman.fxml"));

        DialogPane priceQuote=fxmlLoader.load();
        Price_Quote_Calculation_Dialog_Salesman_Controller givePriceQuoteDialog=fxmlLoader.getController();
        givePriceQuoteDialog.setMedicineDialogWithDetails(selectedMedicine);

        Dialog<ButtonType> dialog=new Dialog<>();
        dialog.setDialogPane(priceQuote);
        dialog.setTitle("Medicine Price Quotation");

        Optional<ButtonType> clickedButton =dialog.showAndWait();
    }
}
