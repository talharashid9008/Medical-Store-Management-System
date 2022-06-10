package com.medical_store_management_system.GUI_Layer.SalesMan_Controllers;

import com.medical_store_management_system.GUI_Layer.Main_Login_Screen.Login_Screen_Controller;
import com.medical_store_management_system.GUI_Layer.Text_Animation.TextAnimator;
import com.medical_store_management_system.GUI_Layer.Text_Animation.TextOutput;
import com.medical_store_management_system.main;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

public class Salesman_Main_Menu_Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    //-------------------------- Main Heading Text Id ------------------------------------------------------------------
    TextAnimator textAnimator;

    @FXML
    private Text abcMedicalStore_TextHeading;
    //------------------------- DASHBOARD BUTTONS - LEFT NAV BAR BUTTONS -----------------------------------------------
    @FXML
    private Button cart_BTN;

    @FXML
    private Button givePriceQuote_BTN;

    @FXML
    private Button logOut_BTN;

    @FXML
    private Button seeInventory_BTN;

    @FXML
    private Label loginUserName_Label;

    //------------------------- STACK PANE INSTANCE : WHICH WILL DISPLAY THE RESPECTIVE ACTION -------------------------
    @FXML
    private StackPane contentArea;

    //------------------------ HANDLE THE LOGOUT OF THE SALESMAN AND GO TO MAIN LOGIN SCREEN --------------------------
    @FXML
    void logout_SalesMan(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Login_Menu_Screen.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    //----------------------- HANDLE THE QUERY OF CHECK THE BILL FROM THE BILL NO --------------------------------------
    @FXML
    void perform_CheckBill(ActionEvent event) {

    }

    //---------------------- HANDLE THE QUERY OF GIVE PRICE QUOTATION TO A CERTAIN CUSTOMER ----------------------------
    @FXML
    void perform_GivePriceQuote(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Give_Price_Quotation_Salesman.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //---------------------- HANDLE THE OPENING OF THE CART FOR THE NEW CUSTOMER AND GIVE BILLS-------------------------
    @FXML
    void perform_OpenCart(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Cart_Salesman.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    //--------------------- HANDLE THE SEE INVENTORY ACTION OF THE SALES MAN -------------------------------------------
    @FXML
    void perform_SeeInventory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("See_Inventory_SalesMan.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginUserName_Label.setText(Login_Screen_Controller.getCurrenSalesMan().getSalesmanName());
        //----------------------------------------------- Animation ----------------------------------------------------
        TextOutput textOutput = textOut -> Platform.runLater(() -> abcMedicalStore_TextHeading.setText(textOut));
        textAnimator = new TextAnimator("ABC Medical Store",
                100, textOutput);

        Thread thread = new Thread(textAnimator);
        thread.start();
    }
}
