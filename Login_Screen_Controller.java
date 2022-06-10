package com.medical_store_management_system.GUI_Layer.Main_Login_Screen;

import com.medical_store_management_system.Business_Logic.StoreAdmin;

import com.medical_store_management_system.Business_Logic.StorePharmacist;
import com.medical_store_management_system.Business_Logic.StoreSalesman;
import com.medical_store_management_system.Database_Connectivity.StoreAdmin_Connection_DB;

import com.medical_store_management_system.Database_Connectivity.StorePharmacist_Connection_DB;
import com.medical_store_management_system.Database_Connectivity.StoreSalesman_Connection_DB;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Login_Screen_Controller implements Initializable {

    private static StoreAdmin currentAdmin;
    private static StoreSalesman currenSalesMan;
    private static StorePharmacist currentPharmacist;


    TextAnimator textAnimator;

    @FXML
    private Text abcMedicalStore_TextHeading;

    @FXML
    private ImageView imageView;

    private Stage stage;
    private Scene scene;
    private Parent root;

    private int loginAs;
    //- Radio Buttons

    @FXML
    private RadioButton asAdmin;

    @FXML
    private RadioButton asPharmacist;

    @FXML
    private RadioButton asSalesMan;

    @FXML
    private ToggleGroup loginAsChoice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File file = new File("Assests/doctor.png");
        Image image = new Image(file.toURI().toString());
        imageView.setImage(image);
        login_BTN.setDisable(true);

        asAdmin.setToggleGroup(loginAsChoice);
        asPharmacist.setToggleGroup(loginAsChoice);
        asSalesMan.setToggleGroup(loginAsChoice);

        //----------------------------------------------- Animation ----------------------------------------------------
        TextOutput textOutput = textOut -> Platform.runLater(() -> abcMedicalStore_TextHeading.setText(textOut));

        textAnimator = new TextAnimator("ABC Medical Store",
                100, textOutput);

        Thread thread = new Thread(textAnimator);
        thread.setDaemon(true);
        thread.start();
    }

    @FXML
    private PasswordField loginPassword;

    @FXML
    private Label loginStatusLabel;

    @FXML
    private TextField loginUserName;

    @FXML
    private Button login_BTN;

    @FXML
    void perform_Login(ActionEvent event) throws IOException {
        String userName_login = loginUserName.getText();
        String password_login = loginPassword.getText();

        System.out.println(userName_login + "\t" + password_login);

        if (loginAs == 1) //admin
        {
            StoreAdmin isAdminExistInDB = StoreAdmin_Connection_DB.findStoreAdmin(userName_login, password_login);
            currentAdmin=isAdminExistInDB;
            if (isAdminExistInDB != null) {
                System.out.println("Admin Exist in Database");
                loginStatusLabel.setText("Admin Exist in Database");

                System.out.println("Mouse Clicked ");
                root = FXMLLoader.load(main.class.getResource("Store_Admin_Menu_Screen.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Admin Doesn't Exist in Database");
                loginStatusLabel.setText("Admin Doesn't Exist in Database");
                Alert alertDeleteConfirm= new Alert(Alert.AlertType.ERROR);
                alertDeleteConfirm.setTitle("Admin Doesnot Exist In The Database");
                alertDeleteConfirm.setHeaderText("Cannot Find The Username and Password");
                alertDeleteConfirm.setContentText("Kindly Check Your Username and Password");
                Optional<ButtonType> result=alertDeleteConfirm.showAndWait();

            }
        } else if (loginAs == 2)         //salesman
        {
            StoreSalesman isStoreSalesmanExist = StoreSalesman_Connection_DB.findStoreSalesMan(userName_login, password_login);
            currenSalesMan=isStoreSalesmanExist;
            if (isStoreSalesmanExist != null) {
                System.out.println("Salesman Exist in Database");
                loginStatusLabel.setText("Salesman Exist in Database");
                System.out.println("Mouse Clicked ");
                root = FXMLLoader.load(main.class.getResource("Salesman_MainMenu_Screen.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Store Salesman Doesn't Exist in Database");
                loginStatusLabel.setText("Store Salesman Username & Password Doesn't Exist in Database");
                Alert alertDeleteConfirm= new Alert(Alert.AlertType.ERROR);
                alertDeleteConfirm.setTitle("Salesman Doesnot Exist In The Database");
                alertDeleteConfirm.setHeaderText("Cannot Find The Username and Password");
                alertDeleteConfirm.setContentText("Kindly Check Your Username and Password");
                Optional<ButtonType> result=alertDeleteConfirm.showAndWait();
            }
        } else if (loginAs == 3)         //pharmacist
        {
            StorePharmacist isPharmacistExit=StorePharmacist_Connection_DB.findStorePharmacist(userName_login,password_login);
            currentPharmacist=isPharmacistExit;
            if (isPharmacistExit != null) {
                System.out.println("Pharmacist Exist in Database");
                loginStatusLabel.setText("Pharmacist Exist in Database");

                System.out.println("Mouse Clicked ");
                root = FXMLLoader.load(main.class.getResource("Pharmacist_Menu.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } else {
                System.out.println("Pharmacist Doesn't Exist in Database");
                loginStatusLabel.setText("Pharmacist Doesn't Exist in Database");
                Alert alertDeleteConfirm= new Alert(Alert.AlertType.ERROR);
                alertDeleteConfirm.setTitle("Pharamacist Doesnot Exist In The Database");
                alertDeleteConfirm.setHeaderText("Cannot Find The Username and Password");
                alertDeleteConfirm.setContentText("Kindly Check Your Username and Password");
                Optional<ButtonType> result=alertDeleteConfirm.showAndWait();
            }
        }

    }

    @FXML
    void getRole(ActionEvent event) {
        if (asAdmin.isSelected())
            loginAs = 1;
        else if (asSalesMan.isSelected())
            loginAs = 2;
        else if (asPharmacist.isSelected())
            loginAs = 3;
    }

    @FXML
    public void handleKeyReleased() {
//        if no text is entered then we will disable the login button if text is space also lock the button
        String userName_login = loginUserName.getText();
        String password_login = loginPassword.getText();

        System.out.println(userName_login + "\t" + password_login);
        boolean checkRadioSelected = asAdmin.isSelected() || asSalesMan.isSelected() || asPharmacist.isSelected();

        System.out.println("radio status " + checkRadioSelected);
        boolean disableButtons = (userName_login.isEmpty() || userName_login.trim().isEmpty()) || (password_login.isEmpty() || password_login.trim().isEmpty()) && checkRadioSelected;
        login_BTN.setDisable(disableButtons);
    }


    //------------------------------------ Getters and Settes of Instance Variables of Actors -------------------------------------
    public static StoreAdmin getCurrentAdmin() {
        return currentAdmin;
    }

    public static void setCurrentAdmin(StoreAdmin currentAdmin) {
        Login_Screen_Controller.currentAdmin = currentAdmin;
    }

    public static StoreSalesman getCurrenSalesMan() {
        return currenSalesMan;
    }

    public static void setCurrenSalesMan(StoreSalesman currenSalesMan) {
        Login_Screen_Controller.currenSalesMan = currenSalesMan;
    }

    public static StorePharmacist getCurrentPharmacist() {
        return currentPharmacist;
    }

    public static void setCurrentPharmacist(StorePharmacist currentPharmacist) {
        Login_Screen_Controller.currentPharmacist = currentPharmacist;
    }
}
