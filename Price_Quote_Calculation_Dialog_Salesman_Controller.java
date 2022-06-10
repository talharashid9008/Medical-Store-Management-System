package com.medical_store_management_system.GUI_Layer.SalesMan_Controllers;


import com.medical_store_management_system.Business_Logic.Medicines;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Price_Quote_Calculation_Dialog_Salesman_Controller {

    Medicines quotedMedicineReference;

    @FXML
    private Button calculatePrice;

    @FXML
    private TextField desiredQuantity;

    @FXML
    private TextField medicineDossage;

    @FXML
    private TextField medicineGenericName;

    @FXML
    private TextField medicineManufacture;

    @FXML
    private TextField medicineName;

    @FXML
    private TextField totalPrice;

    @FXML
    private TextField medicineUnitPriceRetail;


    //--------------------------------------- Give Price Quotation -----------------------------------------------------
    @FXML
    void perform_GiveQuotationPrice(ActionEvent event) {
        int desiredQuantityOfMedicine=Integer.parseInt(desiredQuantity.getText());
        int totalPriceValue=desiredQuantityOfMedicine * this.quotedMedicineReference.getMedicineRetailPrice();
        totalPrice.setText(String.valueOf(totalPriceValue));

        totalPrice.setDisable(true);
    }

    public void setMedicineDialogWithDetails(Medicines selectedMedicine)
    {
        this.quotedMedicineReference=selectedMedicine;
        medicineName.setText(selectedMedicine.getMedicineName());
        medicineDossage.setText(selectedMedicine.getMedicineDosage());
        medicineManufacture.setText(selectedMedicine.getMedicineManufacturerName());
        medicineGenericName.setText(selectedMedicine.getMedicineGenericName());
        medicineUnitPriceRetail.setText(String.valueOf(selectedMedicine.getMedicineRetailPrice()));

        medicineUnitPriceRetail.setDisable(true);
        medicineManufacture.setDisable(true);
        medicineName.setDisable(true);
        medicineDossage.setDisable(true);
        medicineGenericName.setDisable(true);

    }



}
