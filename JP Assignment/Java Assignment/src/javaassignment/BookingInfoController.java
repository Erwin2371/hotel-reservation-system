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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BookingInfoController implements Initializable {

    LoginController login = new LoginController();
    HomeController home = new HomeController();
    
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
    @FXML
    private Label dateTime;
    /**
     * Initializes the controller class.
     */
    
    FXMain main = new FXMain();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime);
    }   
    
    @FXML
    private void Back(MouseEvent event) throws IOException {
        Parent HomeView = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene HomeViewScene = new Scene(HomeView);
        
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        
        window.setScene(HomeViewScene);
        window.show();
    }
    
    @FXML
    private void Logout(ActionEvent event) throws IOException {
        main.Logout(event);
    }  
    
    @FXML
    private void Home(ActionEvent event) throws IOException {
        main.Login(event);
    }
}