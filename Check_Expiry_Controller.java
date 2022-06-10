package com.medical_store_management_system.GUI_Layer.Pharamcist_Controllers;


import com.medical_store_management_system.Business_Logic.Medicines;
import com.medical_store_management_system.Database_Connectivity.Medicine_Connection_DB;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class Check_Expiry_Controller implements Initializable {
    //-------------------------------- Table View of All Medicines -----------------------------------------------------
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

    //---------------------------------- Search Field Keyword ----------------------------------------------------------
    @FXML
    private TextField searchKeyword_MedicineInventory;

    //------------------------------------ Initialize The Table View Content By Fetching From DB ----------------------
    ObservableList<Medicines> allMedicineList= FXCollections.observableArrayList();
    ObservableList<Medicines> expiredMedicines= FXCollections.observableArrayList();
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

        //------------------------------ We Will Display on the Expired Medicines in table -----------------------------
        LocalDate currentSystemDate=LocalDate.now();

        System.out.println(currentSystemDate);
        for(Medicines expiredMedicine : allMedicineList)
        {
            if(currentSystemDate.isAfter(expiredMedicine.getMedicineDateOfExpiry()))
            {
                System.out.println(currentSystemDate.isBefore(expiredMedicine.getMedicineDateOfExpiry()));
                expiredMedicines.add(expiredMedicine);
            }
        }


        All_Medicine_Table.setItems(expiredMedicines);

        //--------------------- Implementing Search Functionality ------------------------------------------------------
        //------------ Searching Feature Implementation ----------------------------------------------------------------
        //-------- Intialize the Filtered List
        FilteredList<Medicines> filteredData =new FilteredList<>(expiredMedicines, b->true);
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
    void perform_RemoveExpiredMedicine(ActionEvent event) {
        Medicines selectedMedicine= All_Medicine_Table.getSelectionModel().getSelectedItem();
        System.out.println("Deleter Press");
        //---------------------------- Nothing has been selected and clicked the button---------------------------------
        if(selectedMedicine ==null)
        {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("No Medicine is Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please Select A Medicine From the table that you want to delete");
            alert.showAndWait();
            return;
        }

        //------------------ Creating an alert for conformation of delete of medicine ----------------------------------
        //**** if press OK Delete from Database
        //**** if press cancel stop to delete from database

        Alert alertDeleteConfirm= new Alert(Alert.AlertType.CONFIRMATION);
        alertDeleteConfirm.setTitle("Delete A Medicine From Record");
        alertDeleteConfirm.setHeaderText("Please Confirm the Delete of Medicine");
        alertDeleteConfirm.setContentText("Medicine Details which is to be deleted :\n"+selectedMedicine);

        Optional<ButtonType> result=alertDeleteConfirm.showAndWait();
        if(result.isPresent() && result.get()==ButtonType.OK)
        {
            if(Medicine_Connection_DB.deleteParticularMedicine(selectedMedicine))
            {
                System.out.println("Medicine Deleted");
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

                Alert alertSuccess= new Alert(Alert.AlertType.INFORMATION);
                alertSuccess.setTitle("Delete A Medicine From Record");
                alertSuccess.setHeaderText("Your Selected Medicine Is Delete From The System");
                alertSuccess.setContentText("Following Medicine Is Deleted :\n"+selectedMedicine);
            }
            else
            {
                Alert alertFailure= new Alert(Alert.AlertType.ERROR);
                alertFailure.setTitle("Delete A Medicine From Record");
                alertFailure.setHeaderText("Cannot Delete A Medicine - Delete Operation Failed");
                alertFailure.setContentText("Following Medicine Is Not Deleted :\n"+selectedMedicine);
            }
        }
        else
        {
            System.out.println("Medicine Not Deleted");
        }
    }

    @FXML
    void rowClicked(MouseEvent event) {
        Medicines selectedMedicine= All_Medicine_Table.getSelectionModel().getSelectedItem();
        System.out.println("Deleter Press"+selectedMedicine);
    }
}
