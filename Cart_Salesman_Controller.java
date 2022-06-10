package com.medical_store_management_system.GUI_Layer.SalesMan_Controllers;

import com.medical_store_management_system.Business_Logic.*;
import com.medical_store_management_system.Database_Connectivity.Bills_Connection_DB;
import com.medical_store_management_system.Database_Connectivity.Medicine_Connection_DB;
import com.medical_store_management_system.Database_Connectivity.Purchased_Medicines_Connection_DB;
import com.medical_store_management_system.GUI_Layer.Main_Login_Screen.Login_Screen_Controller;
import com.medical_store_management_system.Business_Logic.Utility.Filing_System.Bills_File_Handling;
import com.medical_store_management_system.Business_Logic.Utility.Filing_System.PurchasedMedicines_FileHandling;
import com.medical_store_management_system.Business_Logic.Utility.Print_Bill;
import com.medical_store_management_system.main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;

public class Cart_Salesman_Controller implements Initializable {

    private int totalAmount = 0;
    private Bills newBill = new Bills();
    @FXML
    private Button addMedicine_BTN;

    @FXML
    private TextField billCustomerName;

    @FXML
    private TextField billCustomerPhoneNo;

    @FXML
    private TextField billNo;


    @FXML
    private TextField discountOffered;


    @FXML
    private ComboBox<String> paymentMethods;

    @FXML
    private Button printBill_BTN;

    @FXML
    private Button refreshCart_BTN;

    @FXML
    private TextField savingsOnBillAmount;

    @FXML
    private TextField totalAmouunt;

    @FXML
    private TextField totalBillPayableAmount;


    //------------------------------ Table View of Added Medicines in the cart -----------------------------------------
    @FXML
    private TableColumn<PurchasedMedicines, Integer> colAmount;

    @FXML
    private TableColumn<PurchasedMedicines, LocalDate> colExpiryDate;

    @FXML
    private TableColumn<PurchasedMedicines, String> colMedicineName;

    @FXML
    private TableColumn<PurchasedMedicines, Integer> colQuantity;

    @FXML
    private TableColumn<PurchasedMedicines, Integer> colUnitPrice;

    @FXML
    private TableColumn<PurchasedMedicines, Integer> colItemsCounter;

    @FXML
    private TableView<PurchasedMedicines> cartViewTable;

    @FXML
    private TextField amountPaidByCustomer;


    @FXML
    private TextField changeAmount;


    private double discountPercentage = 0;
    private double savingInBill = 0;
    private double totalPayableBill = 0;
    //------------------------------------- FX Collection Array List For Table View ------------------------------------
    ObservableList<PurchasedMedicines> allMedicinesInCart = FXCollections.observableArrayList();

    //-------------------- Initialize the table view -------------------------------------------------------------------
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colItemsCounter.setCellValueFactory(new PropertyValueFactory<PurchasedMedicines, Integer>("srNo_Table"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<PurchasedMedicines, Integer>("unitPrice"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<PurchasedMedicines, Integer>("quantity"));
        colMedicineName.setCellValueFactory(new PropertyValueFactory<PurchasedMedicines, String>("medicineName"));
        colExpiryDate.setCellValueFactory(new PropertyValueFactory<PurchasedMedicines, LocalDate>("medicineExpiryDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<PurchasedMedicines, Integer>("amount"));

        cartViewTable.setItems(allMedicinesInCart);
        billNo.setDisable(true);

        //---------------------------------- FILLING THE PAYMENT METHODS -----------------------------------------------
        ArrayList<String> paymentOpts = new ArrayList<>();
        paymentOpts.add("Cash");
        paymentOpts.add("Credit/Debit Card");
        paymentOpts.add("Easypaisa");
        paymentOpts.add("JazzCash");
        paymentMethods.getItems().setAll(paymentOpts);

        paymentMethods.setValue(paymentOpts.get(0));    //--------- Setting By Default To Cast Payment Option

        //---------------------------------- Ready The Billing Area  ---------------------------------------
        double totalAmountOfMedicines_Variable = 0;
        for (PurchasedMedicines currentMedicine : allMedicinesInCart) {
            totalAmountOfMedicines_Variable += (double) currentMedicine.getAmount();
        }

        this.totalAmouunt.setText(String.valueOf(totalAmountOfMedicines_Variable));
        this.totalAmouunt.setDisable(true);

        this.discountPercentage = Integer.parseInt(discountOffered.getText()) / 100;
        this.savingInBill = (double) (discountPercentage * totalAmountOfMedicines_Variable);
        this.totalPayableBill = totalAmountOfMedicines_Variable - savingInBill;

        changeAmount.setDisable(true);
        amountPaidByCustomer.setDisable(true);

    }

    ///----------------- Implementation of to Perform Add New Medicine In The Cart ------------------------------------
    @FXML
    void perform_AddNewMedicineInCart(ActionEvent event) throws IOException {
        PurchasedMedicines newMedicine;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(main.class.getResource("addMedicine_Cart_Dialog_Screen.fxml"));

        DialogPane addMedicinePopUP = fxmlLoader.load();
        AddMedicineCartDialogScreen_Controller addMedicineDialogPopUp = fxmlLoader.getController();

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setDialogPane(addMedicinePopUP);
        dialog.setTitle("Add A Medicine");

        Optional<ButtonType> clickedButton = dialog.showAndWait();
        if (clickedButton.get() == ButtonType.OK) {
            newMedicine = addMedicineDialogPopUp.getNewMedicineInCart();
            if (newMedicine != null) {
                allMedicinesInCart.add(newMedicine);
                cartViewTable.setItems(allMedicinesInCart);
                totalAmount += newMedicine.getAmount();
            }
        }

        totalAmouunt.setText(String.valueOf(totalAmount));
    }

    @FXML
    void perform_PrintBill(ActionEvent event) {
        Print_Bill.printBill(newBill);
        PurchasedMedicines.restSrNo();
        refreshCart_BTN.setDisable(false);
        Bills_File_Handling.insertBillInFile(this.newBill);


        //--------------------- Displaying Confirmation of Print Bill --------------------------------------------------
        Alert alertSucess= new Alert(Alert.AlertType.INFORMATION);
        alertSucess.setTitle("Customer Bill Generated Successfully");
        alertSucess.setHeaderText("Customer Bill is generated.");
        alertSucess.setContentText("Customer Bill is generated successfully. You can now entertain another customer");
        alertSucess.showAndWait();
    }

    @FXML
    void perform_RefreshCart(ActionEvent event) {
        allMedicinesInCart.clear();
        cartViewTable.getItems().clear();
        amountPaidByCustomer.clear();
        billCustomerPhoneNo.clear();
        billCustomerName.clear();
        billNo.clear();
        totalAmouunt.clear();
        discountOffered.clear();
        savingsOnBillAmount.clear();
        totalBillPayableAmount.clear();
        amountPaidByCustomer.clear();
        changeAmount.clear();

        addMedicine_BTN.setDisable(false);
    }

    @FXML
    void readyBIll(ActionEvent event) {

        if (billCustomerName.getText().isEmpty() || billCustomerPhoneNo.getText().isEmpty() || paymentMethods.getValue().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Basic Bill Details Are Not Filled Yet ");
            alert.setHeaderText(null);
            alert.setContentText("Please Fill Customer Name, Phone No, Payment Method First");
            alert.showAndWait();
            return;
        }

        Vector<PurchasedMedicines> allMedicinesInCart_Vector = new Vector<>();
        int totalAmountOfMedicines_Variable = 0;
        for (PurchasedMedicines currentMedicine : allMedicinesInCart) {
            totalAmountOfMedicines_Variable += (double) currentMedicine.getAmount();
            allMedicinesInCart_Vector.add(currentMedicine);
        }

        if (totalAmountOfMedicines_Variable <= 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("No Medicine is Added");
            alert.setHeaderText(null);
            alert.setContentText("Please Select A Medicine From The Cart First");
            alert.showAndWait();
            return;
        }

        this.totalAmouunt.setText(String.valueOf(totalAmountOfMedicines_Variable));
        this.totalAmouunt.setDisable(true);

        this.discountPercentage = Double.valueOf(discountOffered.getText()) / 100;
        this.savingInBill = (int) (discountPercentage * totalAmountOfMedicines_Variable);
        this.totalPayableBill = totalAmountOfMedicines_Variable - savingInBill;


        this.discountOffered.setDisable(true);
        savingsOnBillAmount.setText(Double.toString(savingInBill));
        savingsOnBillAmount.setText(Double.toString(savingInBill));
        totalBillPayableAmount.setText(Double.toString(totalPayableBill));

        //--
        changeAmount.setDisable(true);
        savingsOnBillAmount.setDisable(true);

        billNo.setText(newBill.getBillNo());
        //---------------------------------------- Creating A New Bill -------------------------------------------------
        newBill.setSystemTimeDate();
        newBill.setCustomerName(billCustomerName.getText());
        newBill.setPaymentMethod(paymentMethods.getValue());
        newBill.setCustomerPhoneNo(billCustomerPhoneNo.getText());
        newBill.setTotalMedicinesPurchased(allMedicinesInCart_Vector);
        newBill.setTotalAmountOfBill(totalAmountOfMedicines_Variable);
        newBill.setDiscountPercentage(Integer.parseInt(discountOffered.getText()));
        newBill.setTotalSavingsOnBill(savingInBill);
        newBill.setTotalPayableBill((int) totalPayableBill);
        newBill.setSalesmanName(Login_Screen_Controller.getCurrenSalesMan().getSalesmanName());

        refreshCart_BTN.setDisable(true);
        addMedicine_BTN.setDisable(true);

        changeAmount.setDisable(false);
        amountPaidByCustomer.setDisable(false);
        billNo.setText(newBill.getBillNo());

    }


    //------------------------------Update Change Text Field Automatically --------------------------------------------
    @FXML
    void updateChangeAmount(KeyEvent event) {
        int amountPaidBy = 0;
        try {
            amountPaidBy = Integer.parseInt(amountPaidByCustomer.getText());
        } catch (NumberFormatException E) {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Fix the Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
            return;

        }
        if (amountPaidByCustomer.getText().isEmpty())
            return;

        newBill.setCashGivenByCustomer(amountPaidBy);
        newBill.setChangeAmount();

        changeAmount.setText(String.valueOf(newBill.getChangeOfCashGivenBackToCustomer()));
    }

    //----------------------------- Finalize Button Will Change The Values in DB ---------------------------------------
    @FXML
    void perfrom_FinalizeBill(ActionEvent event) {
        int amountPiad_Int = 0;
        try {
            amountPiad_Int = Integer.parseInt(amountPaidByCustomer.getText());
        } catch (NumberFormatException E) {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Fix the Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
            return;

        } catch (Exception E) {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("Type Mismatch Exception Occurred");
            alertDeleteConfirm.setHeaderText("String in Place of Integer Data Type");
            alertDeleteConfirm.setContentText("You have entered a string in the integer value data type variable. Kindly Fix the Value To Integer Type");
            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
            return;
        }
        if (amountPiad_Int < 0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("The Total Amount Is Negative");
            alert.setHeaderText(null);
            alert.setContentText("The total amount paid by customer cannot be negative");
            alert.showAndWait();
            return;
        }

        if ((double) amountPiad_Int < totalPayableBill) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("The Total Payable Amount is Less then the Total Payable Bill");
            alert.setHeaderText(null);
            alert.setContentText("Kindly Enter Full Payable Bill Amount");
            alert.showAndWait();
            return;
        }


        for (PurchasedMedicines updateMedicine : allMedicinesInCart) {
            if (updateMedicine.getQuantity() <= Medicine_Connection_DB.getMedicineRecord(updateMedicine.getDrugCode()).getMedicineQuantity()) {
                if (Medicine_Connection_DB.updateMedicineQuantity(updateMedicine.getDrugCode(), updateMedicine.getQuantity())) {
                    System.out.println("Medicine Quantity is Updated");
                    printBill_BTN.setDisable(false);

                    //----------- Setting the Bill Number For All Medicines Purchased --------------------------------------
                    updateMedicine.setBillNo(newBill.getBillNo());

                    //----------- Insert in to the file --------------------------------------------------------------------
                    PurchasedMedicines_FileHandling.insertPurchasedMedicineDetailsInFile(updateMedicine);
                } else
                    System.out.println("Medicine Quantity is Not Updated");
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Medicine Quantity Issue");
                alert.setHeaderText(null);
                alert.setContentText("Sorry We don't have that much quantity to perform. Current Medicine Quantity is: " + Medicine_Connection_DB.getMedicineRecord(updateMedicine.getDrugCode()).getMedicineQuantity());
                alert.showAndWait();
                return;
            }
        }

        //--------------------------------------------- Add Bill Details In the Bills Table-----------------------------------------------
        Bills_Connection_DB.insertNewBill(newBill);

        //---------------------------------------------- Add Medicines Details in the MedicineInCarts Table ------------------------------
        for (PurchasedMedicines updateMedicine : allMedicinesInCart) {

            if (Purchased_Medicines_Connection_DB.insertNew_MedicinesInCartRecord(updateMedicine))
                System.out.println("Purchase Medicine Updated");
            else
                System.out.println("Medicine Quantity is Not Updated");
        }
}
}
