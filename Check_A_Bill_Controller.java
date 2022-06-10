package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.Scanner;

public class Check_A_Bill_Controller {

    @FXML
    private TextField billNumber_Label;

    @FXML
    private TextArea customerBill;

    @FXML
    private Button findBill;

    @FXML
    void perform_FindBill(ActionEvent event) {
        //Getting the bill number for the text field
        String billNo = billNumber_Label.getText();
        Boolean findStatus = false;

        // making the file path
        String path = "Customer_Invoices\\Bill_" + billNo+".txt";
        //Getting the Particular Fill Details
        try {
            FileReader openBill = new FileReader(path);
            Scanner sc = new Scanner(openBill);
            String line = " ";

            while (sc.hasNext()) {
                line += sc.nextLine();
                line += "\n";
            }

            customerBill.setText(line);
        } catch (FileNotFoundException e) {
            findStatus = true;
        }

        if (findStatus == true) {
            Alert alertDeleteConfirm = new Alert(Alert.AlertType.CONFIRMATION);
            alertDeleteConfirm.setTitle("Bill Not Found in The Records");
            alertDeleteConfirm.setHeaderText("Kindly Check The Bill Number");
            alertDeleteConfirm.setContentText("Either You have entered a Wrong Bill Number or Bill Doesnot Exits :\n");

            Optional<ButtonType> result = alertDeleteConfirm.showAndWait();
        }
    }
}
