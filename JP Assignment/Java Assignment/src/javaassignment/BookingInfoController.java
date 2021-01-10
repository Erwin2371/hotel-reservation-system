/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.input.KeyEvent;
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
    AddBookingController binfo = new AddBookingController();
    FXMain main = new FXMain();
    File file = new File(".Data");

    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnLogout;
    @FXML
    private Label dateTime;
    @FXML
    private Label lblContact;
    @FXML
    private Label lblIC;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private JFXButton btnBooking;
    @FXML
    private JFXTextField txtRname;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtIC;
    @FXML
    private Spinner<?> NumNightsSpinner;
    @FXML
    private JFXToggleButton tb_1A;
    @FXML
    private JFXToggleButton tb_2A;
    @FXML
    private JFXToggleButton tb_3A;
    @FXML
    private JFXToggleButton tb_5A;
    @FXML
    private JFXToggleButton tb_6A;
    @FXML
    private JFXToggleButton tb_7A;
    @FXML
    private JFXToggleButton tb_8A;
    @FXML
    private JFXToggleButton tb_9A;
    @FXML
    private JFXToggleButton tb_10A;
    @FXML
    private JFXToggleButton tb_4A;
    @FXML
    private JFXToggleButton tb_1B;
    @FXML
    private JFXToggleButton tb_2B;
    @FXML
    private JFXToggleButton tb_3B;
    @FXML
    private JFXToggleButton tb_5B;
    @FXML
    private JFXToggleButton tb_6B;
    @FXML
    private JFXToggleButton tb_7B;
    @FXML
    private JFXToggleButton tb_8B;
    @FXML
    private JFXToggleButton tb_9B;
    @FXML
    private JFXToggleButton tb_10B;
    @FXML
    private JFXToggleButton tb_4B;
    @FXML
    private JFXButton btnBook;
    @FXML
    private Label lblFees;
    @FXML
    private Label lblTax;
    @FXML
    private Label lblTotalfees;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField txtSearch;
    
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
    private static Scanner x;
    
    @FXML
    private void search(KeyEvent event){
        boolean found = false;
        String id = ""; String name = ""; String contact = ""; String ic = ""; String date[]; String nightCount; String rooms; String roomCount; String fee; String tax; String total;
        try {
            x = new Scanner(new FileReader(file + "\\Bookings.txt"));
            x.useDelimiter("\n");
            
            while(x.hasNext() && !found){
                id = x.next().substring(12);
                name = x.next().substring(15);
                binfo.customer.setName(name);
                contact = x.next().substring(9);
                ic = x.next().substring(4);
                date = x.next().substring(6).replaceAll("\\[", "").replaceAll("\\]", "").split(", ");
                nightCount = x.next().substring(8);
                rooms = x.next().substring(9);
                roomCount = x.next().substring(12);
                fee = x.next().substring(5);
                tax = x.next().substring(5);
                total = x.next().substring(12);
                x.next();
                
                String x = txtSearch.getText();
                if(x.equals(id) || x.equals(name) || x.equals(contact) || x.equals(ic)){
                    found = true;
                }  
                if(found == true){
                    txtRname.setText(name);
                    txtContact.setText(contact);
                    txtIC.setText(ic);
                    txtDate.setValue(LocalDate.parse(date[0], formatter));
                    System.out.println(Arrays.toString(date));
                }
            }
        } catch (Exception ex) {
             Logger.getLogger(BookingInfoController.class.getName()).log(Level.SEVERE, null, ex);
             
        }
        
    }

    @FXML
    private void Booking(ActionEvent event) {
    }

    @FXML
    private void Save(ActionEvent event) {
    }

    @FXML
    private void Clear(ActionEvent event) {
    }
}
