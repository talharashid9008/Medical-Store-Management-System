package com.medical_store_management_system.GUI_Layer.Pharamcist_Controllers;

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

public class Pharmacist_Main_Menu_Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    //-------------------------- Main Heading Text Id ------------------------------------------------------------------
    TextAnimator textAnimator;

    @FXML
    private Text abcMedicalStore_TextHeading;

    @FXML
    private Button addMedicine_BTN;

    @FXML
    private Button checkExpiry_BTN;

    @FXML
    private StackPane contentArea;

    @FXML
    private Button deleteMedicine_BTN;

    @FXML
    private Button editMedicine_BTN;

    @FXML
    private Button logOut_BTN;

    @FXML
    private Button seeInventory_BTN;

    @FXML
    private Label loginUserName_Label;

    @FXML
    void logout_Admin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Login_Menu_Screen.fxml"));
        stage =(Stage)((Node)event.getSource()).getScene().getWindow();
        scene=new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void perform_CheckExpiryDate(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Check_Expiry_Pharamcist.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    void perform_Delete_Medicine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Delete_Medicine_Pharamacist.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    void perform_EditMedicine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Edit_Medicine_Pharamacist.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    void perform_SeeInventory(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("See_Inventroy_Pharmacist.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @FXML
    void perform_addNewMedicine(ActionEvent event) throws IOException {
        root = FXMLLoader.load(main.class.getResource("Add_New_Medicine_Screen.fxml"));
        contentArea.getChildren().removeAll();
        contentArea.getChildren().setAll(root);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginUserName_Label.setText(Login_Screen_Controller.getCurrentPharmacist().getPharmacistName());

        //----------------------------------- Animator -----------------------------------------------------------------
        TextOutput textOutput = textOut -> Platform.runLater(() -> abcMedicalStore_TextHeading.setText(textOut));
        textAnimator = new TextAnimator("ABC Medical Store",
                100, textOutput);

        Thread thread = new Thread(textAnimator);
        thread.setDaemon(true);
        thread.start();
    }
}
