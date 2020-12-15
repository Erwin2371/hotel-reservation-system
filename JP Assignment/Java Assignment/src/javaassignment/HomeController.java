/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {
 
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnBookings;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnAddBooking;
    @FXML
    private JFXButton btnEditBooking;
    @FXML
    private Label dateTime = new Label();

    /**
     * Initializes the controller class.
     */
    
    FXMain main = new FXMain();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime); 
    }
    
    @FXML
    protected void Logout(ActionEvent event) throws IOException {
        main.Logout(event);
    } 

    @FXML
    protected void Booking(ActionEvent event) throws IOException{
        main.Booking(event);
    }

    @FXML
    private void AddBooking(ActionEvent event) throws IOException{
        main.AddBooking(event);
    }
}

