/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    protected void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to exit?");
       
        alert.setGraphic(new ImageView(this.getClass().getResource("warning.png").toString()));
        
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK) {
            Parent LoginView = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene LoginViewScene = new Scene(LoginView);

            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();

            window.setScene(LoginViewScene);
            window.show();
        }
    } 

    @FXML
    private void Booking(ActionEvent event) throws IOException{
        Parent BookingView = FXMLLoader.load(getClass().getResource("Bookings.fxml"));
        Scene BookingViewScene = new Scene(BookingView);
        
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(BookingViewScene);
        window.show();
    }

    @FXML
    private void AddBooking(ActionEvent event) throws IOException{
        Parent AddBookingView = FXMLLoader.load(getClass().getResource("AddBooking.fxml"));
        Scene AddBookingViewScene = new Scene(AddBookingView);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AddBookingViewScene);
        window.show();
    }
}

