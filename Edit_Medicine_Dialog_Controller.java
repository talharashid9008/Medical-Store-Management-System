package com.medical_store_management_system.GUI_Layer.Pharamcist_Controllers;


import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativePriceException;
import com.medical_store_management_system.Business_Logic.Custom_Exceptions.NegativeQuantityException;
import com.medical_store_management_system.Business_Logic.Medicines;
import com.medical_store_management_system.Database_Connectivity.Medicine_Connection_DB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;

public class Edit_Medicine_Dialog_Controller {

    Medicines editMedicine;
    @FXML
    private TextArea medicineBasicDescription;

    @FXML
    private TextField medicineBatchNo;

    @FXML
    private DatePicker medicineDOEXpiry;

    @FXML
    private DatePicker medicineDOManufacture;

    @FXML
    private TextField medicineDealerPrice;

    @FXML
    private TextField medicineDossage;

    @FXML
    private Label medicineDrugAdd_Status;

    @FXML
    private TextField medicineDrugCode;

    @FXML
    private TextField medicineGenericName;

    @FXML
    private TextField medicineManufacture;

    @FXML
    private TextField medicineName;

    @FXML
    private TextField medicineQuantity;

    @FXML
    private TextField medicineRetailPrice;

    @FXML
    private ComboBox<String> medicineType;

    public void setMedicine(Medicines selectedMedicine) {
        medicineName.setText(selectedMedicine.getMedicineName());
        medicineDrugCode.setText(selectedMedicine.getMedicineDrugCode());
        medicineDrugCode.setDisable(true);

        medicineBatchNo.setText(selectedMedicine.getMedicineBatchNo());
        medicineRetailPrice.setText(String.valueOf(selectedMedicine.getMedicineRetailPrice()));
        medicineDealerPrice.setText(String.valueOf(selectedMedicine.getMedicineDealerPrice()));
        medicineManufacture.setText(selectedMedicine.getMedicineManufacturerName());
        medicineDossage.setText(selectedMedicine.getMedicineDosage());
        medicineDOManufacture.setValue(selectedMedicine.getMedicineDateOfManufacture());
        medicineDOEXpiry.setValue(selectedMedicine.getMedicineDateOfExpiry());
        medicineQuantity.setText(String.valueOf(selectedMedicine.getMedicineQuantity()));
        medicineBasicDescription.setText(selectedMedicine.getMedicineDescription());

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

        //------------- Searching Which Medicine Type Was Selected Before -----------------------------------------------
        int indexofType = -1;
        for (String temp : medicineTypeList) {
            if (temp.equals(selectedMedicine.getMedicineType()))
                indexofType++;
        }

        medicineGenericName.setText(selectedMedicine.getMedicineGenericName());
        medicineType.getSelectionModel().select(indexofType);
    }


    @FXML
    void perform_UpdateDetails(ActionEvent event) {

        boolean holdDatabaseInsertion = false;
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

        //-------------------------------------------- Creating New Object of Medicines --------------------------------------------------------
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
            int intDealerPrice = Integer.parseInt(medicineRetailPrice_Str);
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


        newMedicine.setMedicineManufacturerName(medicineManufactureName_Str);
        newMedicine.setMedicineDosage(medicineDosage_Str);

        //--------------------------------------------------- Handling Local Date Format Exception On Manufacture Date -------------------------------------------------------------
        try {
            newMedicine.setMedicineDateOfManufacture(medicineDOManufacture_Str);
        } catch (Exception E) {
            E.printStackTrace();
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Manufacture Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }

        //--------------------------------------------------- Handling Local Date Format Exception On Expire Date -------------------------------------------------------------
        try {
            newMedicine.setMedicineDateOfExpiry(medicineDOExpiry_Str);
        } catch (Exception E) {
            E.printStackTrace();
            holdDatabaseInsertion = true;
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Expiry Local Date Issue Exception");
            alertDeleteConfirm.setHeaderText("Kindly Select Date From The Date Calender Option Only For The Correct Input");
            alertDeleteConfirm.setContentText("Kindly Select Date From The Date Calender Option Only For The Correct Input Issue Becuase You have entered a Wrong Type Possible A String Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }
        newMedicine.setMedicineDescription(medicineDescription_Str);
        newMedicine.setMedicineType(medicineType_Str);
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

        if (holdDatabaseInsertion == false) {
            //------------------------------------------ Creating Confirmation Alert Box ------------------------------------------------------------
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle(medicineDrugCode_Str + " is going to be updated");
            alert.setHeaderText("Medicine Record Update");
            alert.setContentText("Medicine Record Update Pop Up Confirmation\n" +
                    medicineDrugCode_Str + "\n" + medicineName_Str + "\n");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (Medicine_Connection_DB.updateMedicineDetails(newMedicine)) {
                    medicineDrugAdd_Status.setText("Update Successful");
                } else {
                    medicineDrugAdd_Status.setText("Update Unsuccessful");
                }
            }
        } else {
            medicineDrugAdd_Status.setText("Fix The Issue Value");
        }

        System.out.println(newMedicine);
    }
}
