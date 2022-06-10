package com.medical_store_management_system.GUI_Layer.Pharamcist_Controllers;

import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativePriceException;
import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeQuantityException;
import com.medical_store_management_system.Business_Logic.Medicines;
import com.medical_store_management_system.Database_Connectivity.Medicine_Connection_DB;
import com.medical_store_management_system.Business_Logic.Utility.Filing_System.Medicines_FileHandling;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.ResourceBundle;

public class Add_New_Medicine_Controller implements Initializable {

    @FXML
    private Button addDetails_Medicine;

    @FXML
    private TextArea medicineBasicDescription;

    @FXML
    private TextField medicineBatchNo;

    @FXML
    private DatePicker medicineDOEXpiry;

    @FXML
    private DatePicker medicineDOManufacture;

    @FXML
    private TextField medicineDossage;

    @FXML
    private Label medicineDrugAdd_Status;

    @FXML
    private TextField medicineDrugCode;

    @FXML
    private TextField medicineManufacture;

    @FXML
    private TextField medicineName;

    @FXML
    private TextField medicineQuantity;

    @FXML
    private ComboBox<String> medicineType;

    @FXML
    private TextField medicineDealerPrice;

    @FXML
    private TextField medicineRetailPrice;

    @FXML
    private TextField medicineGenericName;


    //--------------------- Add New Medicine Details -------------------------------------------------------------------
    @FXML
    void perform_Add_New_Medicine(ActionEvent event) {

        boolean holdDatabaseInsertion = false;
        //------ Taking Text Form the field ----------------------------------------------------------------------------
        String medicineName_Str = medicineName.getText();
        String medicineDrugCode_Str = medicineDrugCode.getText();
        String medicineBatchNo_Str = medicineBatchNo.getText();

        String medicineManufactureName_Str = medicineManufacture.getText();
        String medicineDosage_Str = medicineDossage.getText();
        LocalDate medicineDOManufacture_Str = medicineDOManufacture.getValue();
        LocalDate medicineDOExpiry_Str = medicineDOEXpiry.getValue();
        String medicineQuantity_Str = medicineQuantity.getText();
        String medicineDescription_Str = medicineBasicDescription.getText();
        String medicineType_Str = medicineType.getValue();

        String medicineRetailPrice_Str = medicineRetailPrice.getText();
        String medicineDealerPrice_Str = medicineDealerPrice.getText();
        String medicineGenericName_Str = medicineGenericName.getText();

        //------------------------------------------ Create A New Medicine Object --------------------------------------
        Medicines newMedicine = new Medicines();
        newMedicine.setMedicineName(medicineName_Str);
        newMedicine.setMedicineDrugCode(medicineDrugCode_Str);
        newMedicine.setMedicineBatchNo(medicineBatchNo_Str);

        //------------------------------------------------------------ Negative Value Check Exception :Retailer -------------------
        try {
            int intRetailPrice = Integer.parseInt(medicineRetailPrice_Str);
            newMedicine.setMedicineRetailPrice(intRetailPrice);
        } catch (NegativePriceException E) {
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Retail Price Cannot Be Negative");
            alertDeleteConfirm.setHeaderText("Retail Price Value Cannot be Negative Or Less Than 0 ");
            alertDeleteConfirm.setContentText("Kindly Fix The Retail Price Value");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        } catch (NumberFormatException E) {
            //----------------------------------------------------- Exception For Type Mismatching : Retail Price ------
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Change the Retail Price Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        //------------------------------------------------------------ Negative Value Check Exception -------------------
        try {
            int intDealerPrice = Integer.parseInt(medicineDealerPrice_Str);
            newMedicine.setMedicineDealerPrice(intDealerPrice);
        } catch (NegativePriceException e) {
//            e.printStackTrace();
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Retail Price Cannot Be Negative");
            alertDeleteConfirm.setHeaderText("Dealer Price Value Cannot be Negative Or Less Than 0 ");
            alertDeleteConfirm.setContentText("Kindly Fix The Retail Price Value");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        } catch (NumberFormatException E) {
            //----------------------------------------------------- Exception For Type Mismatching : Dealer Price ------
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Change the Dealer Price Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }
        //----------------------------------------------------- Exception For Type Mismatching : Dealer Price ------------------------------------------------------------------------------------

        newMedicine.setMedicineManufacturerName(medicineManufactureName_Str);
        newMedicine.setMedicineDosage(medicineDosage_Str);

        //--------------------------------------------------- Handling Local Date Format Exception On Manufacture Date -------------------------------------------------------------
        try {
            newMedicine.setMedicineDateOfManufacture(medicineDOManufacture_Str);
        } catch (Exception E) {
//            E.printStackTrace();
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Manufacture Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type\n Or The Date of Manufacture should be less then today's date");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }


        //--------------------------------------------------- Handling Local Date Format Exception On Expire Date -------------------------------------------------------------
        try {
            newMedicine.setMedicineDateOfExpiry(medicineDOExpiry_Str);
        } catch (Exception E) {
//            E.printStackTrace();
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Expiry Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        newMedicine.setMedicineDescription(medicineType_Str);
        newMedicine.setMedicineGenericName(medicineGenericName_Str);

        //--------------------------------------------------- Exception Handling of The Negative Quantity Value ----------------------------------------------------------------
        try {
            int intQuantity = Integer.parseInt(medicineQuantity_Str);
            newMedicine.setMedicineQuantity(intQuantity);
        } catch (NegativeQuantityException e) {
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Quantity Cannot Be Negative");
            alertDeleteConfirm.setHeaderText("Kindly Enter The Quantity in Positive Integers Value");
            alertDeleteConfirm.setContentText("Kindly Correct the quantity to a positive integer value!");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        } catch (NumberFormatException E) {
            //----------------------------------------------------- Exception For Type Mismatching : Quantity Price ------
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("In Quantity Input Field :You have entered a string in the integer value data type variable. Kindly Change the Dealer Price Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        newMedicine.setMedicineType(medicineType_Str);
        newMedicine.setMedicineDescription(medicineDescription_Str);

        //----------------------------- Adding To The Database ---------------------------------------------------------
        if (holdDatabaseInsertion == false) {
            if (Medicine_Connection_DB.insertNewMedicine(newMedicine)) {
                System.out.println("New Medicine Inserted !!!!");
                medicineDrugAdd_Status.setText("New Dug Inserted Successfully");
                Medicines_FileHandling.insertMedicineDetailsInFile(newMedicine);
            } else {
                System.out.println("New Medicine Inserted Failure !!!!");
                medicineDrugAdd_Status.setText("New Dug Inserted UnSuccessfully");
            }
        } else {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("There are possible issues With Your Inputs");
            alertDeleteConfirm.setHeaderText("Cannot Add New Medicine Becuase Your Inputs Are Not Upto the mark ");
            alertDeleteConfirm.setContentText("Kindly Fix The Values That Were Described By The Pop Up");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
            medicineDrugAdd_Status.setText("Fix The Issue Value");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // -------By Default the Add Medicine Details Button Should Always Be Default to avoid null entries ------------
        addDetails_Medicine.setDisable(true);

        //-------------------------------------------------- Filling the Combo Box With Different Choices --------------
        ArrayList<String> medicineTypeList = new ArrayList<>();
        medicineTypeList.add("Tablet");
        medicineTypeList.add("Injection");
        medicineTypeList.add("Drops");
        medicineTypeList.add("Cream");
        medicineTypeList.add("Spray");
        medicineTypeList.add("Syrup");
        medicineTypeList.add("Disinfectant");
        medicineTypeList.add("Dressing");
        medicineType.getItems().setAll(medicineTypeList);
    }

    //------------------------ Disable Add Medicine Details Button if Text Field is Empty ------------------------------
    @FXML
    void handleKeyReleased(KeyEvent event) {
        String medicineName_Str = medicineName.getText();
        String medicineDrugCode_Str = medicineDrugCode.getText();
        String medicineBatchNo_Str = medicineBatchNo.getText();

        String medicineManufactureName_Str = medicineManufacture.getText();
        String medicineDosage_Str = medicineDossage.getText();
        String medicineQuantity_Str = medicineQuantity.getText();
        String medicineDescription_Str = medicineBasicDescription.getText();
        String medicineGenericName_Str = medicineGenericName.getText();
        String medicineRetailPrice_Str = medicineRetailPrice.getText();
        String medicineDealerPrice_Str = medicineDealerPrice.getText();

        boolean disableButtons = (medicineName_Str.isEmpty() || medicineName_Str.trim().isEmpty()) ||                 //--------> Check if the field is empty or not
                (medicineDrugCode_Str.isEmpty() || medicineDrugCode_Str.trim().isEmpty()) ||
                (medicineBatchNo_Str.isEmpty() || medicineBatchNo_Str.trim().isEmpty()) ||
                (medicineGenericName_Str.isEmpty() || medicineGenericName_Str.trim().isEmpty()) ||
                (medicineManufactureName_Str.isEmpty() || medicineManufactureName_Str.trim().isEmpty()) ||
                (medicineDosage_Str.isEmpty() || medicineDosage_Str.trim().isEmpty()) ||
                (medicineQuantity_Str.isEmpty() || medicineQuantity_Str.trim().isEmpty()) ||
                (medicineRetailPrice_Str.isEmpty() || medicineRetailPrice_Str.trim().isEmpty()) ||
                (medicineDealerPrice_Str.isEmpty() || medicineDealerPrice_Str.trim().isEmpty()) ||
                (medicineDescription_Str.isEmpty() || medicineDescription_Str.trim().isEmpty());
        System.out.println(disableButtons);
        addDetails_Medicine.setDisable(disableButtons);
    }
}
