/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author user
 */
public class AddBookingController implements Initializable {

    LoginController login = new LoginController();
    HomeController home = new HomeController();
    
    @FXML
    private JFXButton Homebtn;
    @FXML
    private JFXButton Bookingsbtn;
    @FXML
    private JFXButton Logoutbtn;
    @FXML
    private JFXTextField Rnamtxt;
    @FXML
    private JFXTextField Contacttxt;
    @FXML
    private JFXTextField ICtxt;
    @FXML
    private JFXDatePicker Datetxt;
    @FXML
    private Spinner<?> Nightstxt;
    @FXML
    private JFXButton Bookbtn;
    @FXML
    private Label Feeslb;
    @FXML
    private Label Taxlb;
    @FXML
    private Label Totalfeeslb;
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
    private void Home(ActionEvent event) throws IOException{
        main.Login(event);
    }

    @FXML
    private void Booking(ActionEvent event) throws IOException {
        main.Booking(event);
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException{
        main.Logout(event);
    }
    
    
    
}
