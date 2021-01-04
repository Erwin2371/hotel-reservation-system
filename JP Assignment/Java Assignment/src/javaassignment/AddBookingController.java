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
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

class Booking {
   protected String booking_id;
   protected String booking_date;
   protected String reservee;
   
}

class BookingDetails {
    
    int nights;
    int rooms;
    double fees;
    double tax;
    double total;
    
    public void setNights(int n){
        nights = n;
    }
    public void setRooms(int n){
        rooms = n;
    }
    public void setFees(double n){
        fees = n;
    }
    public void setTax(double n){
        tax = n;
    }
    public void setTotal(double n){
        total = n;
    } 
}


public class AddBookingController implements Initializable {

    LoginController login = new LoginController();
    HomeController home = new HomeController();
    BookingDetails BDetails = new BookingDetails();
    FXMain main = new FXMain();
    File file = new File(".Data");
    
    @FXML
    protected Label dateTime;
    @FXML
    protected Spinner<Integer> NumNightsSpinner;
    @FXML
    private JFXTextField txtRname;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtIC;
    @FXML
    private JFXDatePicker txtDate;
    @FXML
    private JFXButton btnBook;
    @FXML
    private Label lblFees;
    @FXML
    private Label lblTax;
    @FXML
    private Label lblTotalfees;
    @FXML
    private JFXButton btnHome;
    @FXML
    private JFXButton btnBooking;
    @FXML
    private JFXButton btnLogout;
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

    int count = 0;
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime);
        readfile();
        calculate();
        validate();
        countRoom();
        
        BDetails.nights = NumNightsSpinner.getValue();
        BDetails.fees = BDetails.nights * 350.0;
        BDetails.tax = BDetails.fees * 0.1;
        BDetails.total = BDetails.fees + BDetails.tax;

        lblFees.setText(String.valueOf(BDetails.fees));
        lblTax.setText(String.valueOf(BDetails.tax));
        lblTotalfees.setText(String.valueOf(BDetails.total));   

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
        String name = txtRname.getText();
        String contact = txtContact.getText();
        String IC = txtIC.getText();
        Integer nights = NumNightsSpinner.getValue();
        LocalDate date = txtDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        
        System.out.println(formatter.format(date));
    }
    
    private void calculate(){
        SpinnerValueFactory<Integer> nightsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1);
        NumNightsSpinner.setValueFactory(nightsValueFactory);
        
        
        
        NumNightsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            BDetails.nights = NumNightsSpinner.getValue();
            BDetails.fees = BDetails.nights * 350.0;
            BDetails.tax = BDetails.fees * 0.1;
            BDetails.total = BDetails.fees + BDetails.tax;
            
            lblFees.setText(String.valueOf(BDetails.fees));
            lblTax.setText(String.valueOf(BDetails.tax));
            lblTotalfees.setText(String.valueOf(BDetails.total));

        });
        
        
    }
    
    private void validate(){
        txtRname.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!newValue.matches("\\sa-zA-Z*")){
               txtRname.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));;
           }
       });
        
        txtContact.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[\\d-]+")){
                txtContact.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else if(newValue.length() > 11){
                txtContact.setText(txtContact.getText().substring(0, 11));
            }
            else if(newValue.length() >= 10 && newValue.matches("^\\d{3}-\\d{7,8}$") == false){
                txtContact.setText(txtContact.getText().replaceAll("^(\\d{3})(\\d{7,8})$", "$1-$2"));
            }
        });
        
        txtIC.textProperty().addListener((observable, oldValue, newValue) -> {
            if(!newValue.matches("^[\\d-]+")){
                txtIC.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else if(newValue.length() > 14){
                txtIC.setText(txtIC.getText().substring(0, 14));
            }
            else if(newValue.length() == 12 && newValue.matches("^\\d{6}-\\d{2}-\\d{4}$") == false){
                txtIC.setText(txtIC.getText().replaceAll("^(\\d{6})(\\d{2})(\\d{4})$", "$1-$2-$3"));
            }
        });
    }
    
     private void countRoom() {
        
        List<String> room = new ArrayList<String>();

        tb_1A.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(tb_1A.isSelected()){
                room.add("Room 1A");
                System.out.println(room);
                count++;
                System.out.println(count);
            }
            else if(!tb_1A.isSelected()){
                 Iterator itr = room.iterator(); 
                while (itr.hasNext()) 
                { 
                    String x = (String)itr.next(); 
                    if (x == "Room 1A"){
                        itr.remove(); 
                        count--;
                    }                
                } 
                
                System.out.println(room);
                System.out.println(count);
            }           
        });
    }
}
