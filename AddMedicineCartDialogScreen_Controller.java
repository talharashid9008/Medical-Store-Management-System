package com.medical_store_management_system.GUI_Layer.SalesMan_Controllers;


import com.medical_store_management_system.Business_Logic.Medicines;
import com.medical_store_management_system.Business_Logic.PurchasedMedicines;
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

public class AddMedicineCartDialogScreen_Controller implements Initializable {

    @FXML
    private TableView<Medicines> All_Medicine_Table;

    @FXML
    private TableColumn<Medicines, String> colMGenericName;

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
    private TextField searchKeyword_MedicineInventory;

    @FXML
    private TextField desiredQuantity;
    @FXML
    private TextField medicineName;

    @FXML
    private TextField totalPrice;

    @FXML
    private Button addToCart_BTN;

    private PurchasedMedicines newMedicineInCart = new PurchasedMedicines();

    @FXML
    private TextField retailUnitPrice;

    //---------------------------------- Dialog Pane Default Buttons ---------------------------------------------------
    ObservableList<Medicines> allMedicineList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //------------------------------------ Disabling The Possible Edit of These Text Fiels--------------------------
        retailUnitPrice.setDisable(true);
        medicineName.setDisable(true);

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
        //-------- Initialize the Filtered List
        FilteredList<Medicines> filteredData = new FilteredList<>(allMedicineList, b -> true);
        searchKeyword_MedicineInventory.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(Medicine -> {

                //if no search value then display all the records or whatever records it have. no changes
                if (newValue.isEmpty() || newValue.isBlank() || newValue == null) {
                    return true;
                }
                String searchKeyWord = newValue.toLowerCase();
                if (Medicine.getMedicineName().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineDrugCode().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineBatchNo().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineManufacturerName().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineDosage().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineDateOfManufacture().toString().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineDateOfExpiry().toString().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineType().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineDescription().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else if (Medicine.getMedicineGenericName().toLowerCase().indexOf(searchKeyWord) > -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<Medicines> storedData = new SortedList<>(filteredData);

        // Binding the sorted result with Table View
        storedData.comparatorProperty().bind(All_Medicine_Table.comparatorProperty());

        //apply filtered and sorted data to the table view
        All_Medicine_Table.setItems(storedData);
    }

    //--------------------- Implementation Will Be Perform Add To Cart --------------------------------------------------
    @FXML
    public void perform_AddToCart(ActionEvent event) {
        int qauntityInt=0;
        try {
            qauntityInt=Integer.parseInt(desiredQuantity.getText());
        }catch (NumberFormatException E) {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Fix the Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
            return;
        }

        if (medicineName.getText().isBlank() || desiredQuantity.getText().isBlank() || retailUnitPrice.getText().isBlank()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Medicine is Selected");
            alert.setHeaderText(null);
            alert.setContentText("Please Select A Medicine From the table that you want to Add In The Cart");
            alert.showAndWait();
            return;
        }


        if (Integer.parseInt(desiredQuantity.getText()) == 0)
            return;

        //-------------------------------------------------- Check For Greater Then Quantity --------------------------------------------------
        Medicines selectedMedicine = All_Medicine_Table.getSelectionModel().getSelectedItem();
        if ((selectedMedicine.getMedicineQuantity() < Integer.parseInt(desiredQuantity.getText())))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quantity Error Occured..");
            alert.setHeaderText(null);
            alert.setContentText("You have Entered the Wrong Quantity. You have entered a Quantity Greater Then The Stock Quantity");
            alert.showAndWait();
            return;
        }

        //--------------------------------------------- Check for if a negative value of Quantity has been Entered -----------------------------
        if ((Integer.parseInt(desiredQuantity.getText())) <0)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Quantity Error Occured..");
            alert.setHeaderText(null);
            alert.setContentText("You have Entered the Wrong Quantity. You have entered a Quantity Less Then The 0");
            alert.showAndWait();
            return;
        }


        this.newMedicineInCart.setQuantity(Integer.parseInt(desiredQuantity.getText()));

        this.newMedicineInCart.calculateAmount();

        totalPrice.setText(String.valueOf(this.newMedicineInCart.getAmount()));
        totalPrice.setDisable(true);

        System.out.println(newMedicineInCart.getAmount());
        addToCart_BTN.setDisable(true);

        //--------------------------------------------------------- Update Medicine Quantity ------------------------------------------------
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
    }

    //---------------------- method to use the row selected in the table get details from the selected medicine---------
    @FXML
    void inventoryRowSelected(MouseEvent event) {
        Medicines selectedMedicine = All_Medicine_Table.getSelectionModel().getSelectedItem();
        if (selectedMedicine == null)
            return;
        //------------------------------------- InventoryRowSelected ---------------------------------------------------
        medicineName.setText(selectedMedicine.getMedicineName());
        medicineName.setDisable(true);
        retailUnitPrice.setText(String.valueOf(selectedMedicine.getMedicineRetailPrice()));
        retailUnitPrice.setDisable(true);


        this.newMedicineInCart.setMedicineInCartDetails(selectedMedicine);

    }

    //------------------------------------- Return the New Medicine That is Just Added To The Cart ---------------------
    public PurchasedMedicines getNewMedicineInCart() {
        if (this.newMedicineInCart.getQuantity() == 0)
            return null;
        else
            return this.newMedicineInCart;
    }
}
