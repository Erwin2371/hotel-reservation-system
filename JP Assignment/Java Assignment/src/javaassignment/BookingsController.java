/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javaassignment.LoginController;
import javaassignment.HomeController;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BookingsController implements Initializable {

    @FXML
    private Label BIDlb;
    @FXML
    private Label Rnamelb;
    @FXML
    private Label Contactlb;
    @FXML
    private Label IClb;
    @FXML
    private JFXDatePicker Infodatetxt;
    @FXML
    private Spinner<?> Inforoomstxt;
    @FXML
    private Spinner<?> Infonightstxt;
    @FXML
    private Label TotalPaymentlb;
    
    LoginController login = new LoginController();
    HomeController home = new HomeController();
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnBookings;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnEdit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   

    @FXML
    private void Logout(ActionEvent event) throws IOException {
        home.Logout(event);
    }  
    
    @FXML
    private void Home(ActionEvent event) throws IOException {
        login.Home(event);
    }
    
//    @FXML
//    private void handleClicks (ActionEvent event) {
//        if(event.getSource() == btnBookings)
//        {
//
//        }
//        else if (event.getSource() == btnHome)
//        {
//            
//        }
//    }
}
