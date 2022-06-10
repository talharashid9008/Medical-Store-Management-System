package com.medical_store_management_system.GUI_Layer.Admin_Controllers;

import com.medical_store_management_system.Database_Connectivity.Bills_Connection_DB;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.beans.BeanInfo;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class See_Todays_Sales_Controller implements Initializable {
    @FXML
    private Label dateTime;

    @FXML
    private Label totalSales;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //------------------------ Setting Current Date and Time -------------------------------------------------------
        LocalDate currentDate=LocalDate.now();
        dateTime.setText(currentDate.toString());


        //------------------------ Get Total Sales of Today ------------------------------------------------------------

        int todaySales= Bills_Connection_DB.getTotalSalesOfToday(currentDate);
        totalSales.setText(String.valueOf(todaySales));
    }
}
