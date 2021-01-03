/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

class Booking {
   protected String booking_id;
   protected String booking_date;
   
   
}

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
    private JFXButton Bookbtn;
    @FXML
    private Label Feeslb;
    @FXML
    private Label Taxlb;
    @FXML
    private Label Totalfeeslb;
    @FXML
    private Label dateTime;
    @FXML
    private Spinner<Integer> NumNightsSpinner;

    
    /**
     * Initializes the controller class.
     */
    
    FXMain main = new FXMain();
    File file = new File(".Data");
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime);
        SpinnerValueFactory<Integer> nightsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1);
        NumNightsSpinner.setValueFactory(nightsValueFactory);
        readfile();
        
        Rnamtxt.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!newValue.matches("\\sa-zA-Z*")){
               Rnamtxt.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));;
           }
       });
        
        Contacttxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[\\d-]+")){
                Contacttxt.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        
        ICtxt.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[\\d-]+")){
                ICtxt.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
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
    
    private void readfile() {
        try {
            FileReader filer = new FileReader(file + "\\Bookings.txt");
            System.err.println("File exists!");
        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter(file + "\\Bookings.txt");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("file Created");
        }
   }

    @FXML
    private void createbooking(ActionEvent event) {
        String a = Rnamtxt.getText();
        String b = Contacttxt.getText();
        String c = ICtxt.getText();
        Integer d = NumNightsSpinner.getValue();
        
    }
}

