package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.GUI_Layer.Main_Login_Screen.Login_Screen_Controller;
import com.medical_store_management_system.GUI_Layer.Text_Animation.TextAnimator;
import com.medical_store_management_system.GUI_Layer.Text_Animation.TextOutput;
import com.medical_store_management_system.main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Store_Admin_Menu_Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //------------------------------------- Animator -------------------------------------------------------------------
    TextAnimator textAnimator;

    @FXML
    private Text abcMedicalStore_TextHeading;

    //------------------------ Ad New Sales Man ------------------------------------------------------------------------
    @FXML
    private Label loginUserName_Label;

    @FXML
    private Button addNewSalesMan;

    @FXML
    private Button addNewSalesMan_BTN;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button seeBills_BTN;

    @FXML
    private Button seeInventory_BTN;

    @FXML
    private Button seeSales_BTN;

    @FXML
    private Button viewAllPharmacist_BTN;

    @FXML
    private Button viewAllSalesMen_BTN;

    //---------------------------------- Add New Admin/Owner -----------------------------------------------------------
    @FXML
    void perfrom_AddNewAdmin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Add_New_Admin.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //---------------------------------- Add New Sales Man -----------------------------------------------------------
    @FXML
    void addNewSalesManInStore(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Add_New_SalesMan.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //---------------------------------- Add New Pharmacist -----------------------------------------------------------
    @FXML
    void addNewPharmacist(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Add_New_Pharamist.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //------------------------------ Display All Sales Man -------------------------------------------------------------
    @FXML
    void displayAllSalesMan(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("viewAll_SalesMen_Screen.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //------------------------------ Display All Pharmacist List -------------------------------------------------------
    @FXML
    void displayAllPharmacist(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("viewAll_Pharmacists_Screen.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }


    //----------------------------- See All Bills ----------------------------------------------------------------------
    @FXML
    void checkABill(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Check_A_Bill.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //----------------------------- See Inventory ----------------------------------------------------------------------
    @FXML
    void seeInventory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("See_Inventory_Admin.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }
    //----------------------------- See Sales --------------------------------------------------------------------------
    @FXML
    void seeSales(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("See_Todays_Sales.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
        contentArea.setAlignment(root, Pos.CENTER);
    }

    //---------------------------- Logout Admin ------------------------------------------------------------------------
    @FXML
    void logout_Admin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Login_Menu_Screen.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginUserName_Label.setText(Login_Screen_Controller.getCurrentAdmin().getAdminName());

        //----------------------------------- Animator -----------------------------------------------------------------
        TextOutput textOutput = textOut -> Platform.runLater(() -> abcMedicalStore_TextHeading.setText(textOut));
        textAnimator = new TextAnimator("ABC Medical Store",
                100, textOutput);

        Thread thread = new Thread(textAnimator);
        thread.setDaemon(true);
        thread.start();
    }
}
