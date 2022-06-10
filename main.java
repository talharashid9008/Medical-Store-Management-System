package com.medical_store_management_system;

import com.medical_store_management_system.Database_Connectivity.Database_Connection_Test;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        if(!Database_Connection_Test.testDatabaseConnection())
        {
            Alert alertDeleteConfirm= new Alert(Alert.AlertType.ERROR);
            alertDeleteConfirm.setTitle("MYSQL Database Connection Error");
            alertDeleteConfirm.setHeaderText("Please Fix The Database Eror");
            alertDeleteConfirm.setContentText("Medical Store System is using MYSQL Database. Cannot find MySQL Kindly Fix the issue");
            Optional<ButtonType> result=alertDeleteConfirm.showAndWait();
        }
        else
        {
            File file = new File("Assests/pills.png");
            Image taskBarIcon = new Image(file.toURI().toString());

            FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Login_Menu_Screen.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setTitle("ABC Medical Store!");
            stage.getIcons().add(taskBarIcon);
            stage.setScene(scene);
            stage.show();
        }


    }

    private static String masterPassword="hello";

    public static String getMasterPassword() {
        return masterPassword;
    }

    public static void main(String[] args) {

        launch();
    }
}