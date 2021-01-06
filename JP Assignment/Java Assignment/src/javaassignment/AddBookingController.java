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
import java.io.RandomAccessFile;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

class Customer{
    String name;
    String contact;
    String IC;
    
    public void setName(String a){
        name = a;
    }
    public void setContact(String a){
        contact = a;
    }
    public void setIC(String a){
        IC = a;
    }
    public String getName(){
        return name;
    }
    public String getContact(){
        return contact;
    }
    public String getIC(){
        return IC;
    }
}

class BookingDetails {
    int id;
    int nightCount;
    int roomCount;
    double fees;
    double tax;
    double total;
    LocalDate date;
    
    public void setID(int n){
        id = n;
    }
    public void setNights(int n){
        nightCount = n;
    }
    public void setRooms(int n){
        roomCount = n;
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
    public void setDate(LocalDate n){
        date = n;
    }
    public int getID(){
        return id;
    }
    public int getNightsCount(){
        return nightCount;
    }
    public int getRoomsCount(){
        return roomCount;
    }
    public double getFees(){
        return fees;
    }
    public double getTax(){
        return tax;
    }
    public double getTotal(){
        return total;
    }
    public LocalDate getDate(){
        return date;
    }
}


public class AddBookingController implements Initializable {

    LoginController login = new LoginController();
    HomeController home = new HomeController();
    BookingDetails Bdetails = new BookingDetails();
    Customer customer = new Customer();
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
    private Label lblContact;
    @FXML
    private Label lblIC;
     
    int count = 0;
    int line;
    int idCount = 1;
    String x;
    List<String> roomList = new ArrayList<String>();
    List<String> newRoomList = new ArrayList<String>(Arrays.asList(getString().split(", ")));
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime);
        checkfile();
        spinnerListener();
        validateTxt();
        countRoom();  
        updatePayment();
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
    
    private void checkfile() {
        try {
            FileReader filer = new FileReader(file + "\\Bookings.txt");
            System.err.println("File Exists!");
        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter(file + "\\Bookings.txt");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("File Created");
        }
   }

    @FXML
    private void Book(ActionEvent event) {
        String rname = txtRname.getText();
        String contact = txtContact.getText();
        String ic = txtIC.getText();
        LocalDate date = txtDate.getValue();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        
        
//        if(rname.isEmpty() || contact.isEmpty() || ic.isEmpty() || date == null){
//            alert.setTitle("Empty Fields");
//            alert.setHeaderText("One or more fields are empty.\nPlease fill in all the fields to proceed");
//            alert.showAndWait();
//        }
//        else if(contact.length() < 11){
//            alert.setTitle("Invalid Contact Number");
//            alert.setHeaderText("Please use a valid phone number. e.g '012-3456789'");
//            alert.showAndWait();
//        }
//        else if(ic.length() < 14){
//            alert.setTitle("Invalid IC");
//            alert.setHeaderText("Please use a valid identification number e.g '010234-56-7890'");
//            alert.showAndWait();
//        }
//        else if(Bdetails.getRoomsCount() == 0){
//            alert.setTitle("Room not Selected");
//            alert.setHeaderText("Please select one or more rooms to book");
//            alert.showAndWait();
//        }
//        else{
//            createBooking(Bdetails.getID(), rname, contact, ic, formatter.format(date), Bdetails.getNightsCount(), roomList, Bdetails.getRoomsCount(), Bdetails.getTotal());
//        }     
            countLines();
            customer.setName(rname);
            customer.setContact(contact);
            customer.setIC(ic);
            Bdetails.setDate(date);
            readFile();
    }
    
    private void createBooking(int id, String name, String contact, String ic, String date, int nights, List rooms, int rcount, double total){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\Bookings.txt", "rw");
            
            for(int i=0; i<line; i++){
                raf.readLine();
            }
            raf.writeBytes("Booking ID: BID" + id + "\n");
            raf.writeBytes("Reservee Name: " + name + "\n");
            raf.writeBytes("Contact: " + contact + "\n");
            raf.writeBytes("IC: " + ic + "\n");
            raf.writeBytes("Date: " + date + "\n");
            raf.writeBytes("Nights: " + nights + "\n");
            raf.writeBytes("Room No: " + rooms + "\n");
            raf.writeBytes("Room Count: " + rcount + "\n");
            raf.writeBytes("Total Fees: " + total + "\n");
            raf.writeBytes("\n");
        
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddBookingController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex){
            Logger.getLogger(AddBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void readFile(){
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\Bookings.txt", "r");
            Bdetails.setID(idCount);
            for(int i=1; i<line; i+=10){
                String id = raf.readLine().substring(12);
                String name = raf.readLine().substring(15);
                String contact = raf.readLine().substring(9);
                String ic = raf.readLine().substring(4);
                String date = raf.readLine().substring(6);
                String nights = raf.readLine().substring(8);
                String rooms = raf.readLine().substring(9);
                String roomCount = raf.readLine().substring(12);
                String total = raf.readLine().substring(12);
                
                setString(rooms);
                System.out.println(id + "\n" + name + "\n" + contact + "\n" + ic + "\n" + date + "\n" + nights + "\n" + rooms + "\n" + roomCount + "\n" + total);
               
                if(!id.isEmpty()){
                    idCount++;
                    Bdetails.setID(idCount);
                    System.out.println(Bdetails.getID());
                }
                if(!rooms.isEmpty()){
                    validateToggleBtn("Room 1A", tb_1A);
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);       
        }
    }
    
    private void setString(String a){
        x = a.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",\\s", ",");
        
    }
    
    private String getString(){
        return x;
    }
    
    
    private void countLines() {
        try {
            line = 0;
            RandomAccessFile raf = new RandomAccessFile(file + "\\Bookings.txt", "rw");
            for(int i=0; raf.readLine() != null; i++) {
                line++;
            }
            System.out.println("Number of lines: " + line);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void updatePayment(){
        Bdetails.setRooms(count);
        Bdetails.setNights(NumNightsSpinner.getValue());
        Bdetails.setFees(Bdetails.getNightsCount() * 350.0 * Bdetails.getRoomsCount());
        Bdetails.setTax(Bdetails.getFees() * 0.1);
        Bdetails.setTotal(Bdetails.getFees() + Bdetails.getTax());

        lblFees.setText(String.valueOf(Bdetails.getFees()));
        lblTax.setText(String.valueOf(Bdetails.getTax()));
        lblTotalfees.setText(String.valueOf(Bdetails.getTotal()));
    }
    
    private void spinnerListener(){
        SpinnerValueFactory<Integer> nightsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1);
        NumNightsSpinner.setValueFactory(nightsValueFactory);
             
        NumNightsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updatePayment();
        });    
    }
    
    private void validateTxt(){
        txtRname.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!newValue.matches("\\sa-zA-Z*")){
               txtRname.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));;
           }
           customer.setName(newValue);
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
            else if(!newValue.matches("^01\\d{1}-\\d{7,8}$")){
                String a = "Invalid Contact Number";
                validateEffects(txtContact, lblContact, false, a);
            }
            else if(newValue.matches("^01\\d{1}-\\d{7,8}$")){
                validateEffects(txtContact, lblContact, true, "");
                customer.setContact(newValue);
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
                validateEffects(txtIC, lblIC, true, "");
                customer.setIC(newValue);
            }
            else if(newValue.length() < 14){
                String a = "Invalid IC";
                validateEffects(txtIC, lblIC, false, a);
            }
        });
    }
    
    private void validateToggleBtn(String b, JFXToggleButton c){
      
        Iterator itr = newRoomList.iterator();
        while (itr.hasNext()) 
        { 
            String x = (String)itr.next(); 
            if (x.equals(b)){
                System.out.println("gay");
                c.setDisable(true);
                c.setToggleColor(Paint.valueOf("#bf0101"));
                c.setToggleLineColor(Paint.valueOf("#ff4545"));
            }                
        }
    }
    
    private void validateEffects(JFXTextField a, Label b, boolean c, String d){
        if(c == false){
            a.setFocusColor(Paint.valueOf("#ff1500"));
            a.setUnFocusColor(Paint.valueOf("#ff0000"));
            b.setText(d);
            b.setVisible(true);
        }
        else if(c == true){
            a.setFocusColor(Paint.valueOf("#4059a9"));
            a.setUnFocusColor(Paint.valueOf("#4d4d4d"));
            b.setVisible(false);
        }
    }
    private void selected(JFXToggleButton a, String b){
         if(a.isSelected()){
                roomList.add(b);
                count++;
                updatePayment();
                System.out.println(roomList);
                System.out.println(count);
            }
            else if(!a.isSelected()){
                Iterator itr = roomList.iterator();
                while (itr.hasNext()) 
                { 
                    String x = (String)itr.next(); 
                    if (x.equals(b) ){
                        itr.remove(); 
                        count--;
                        updatePayment();
                    }                
                }              
                System.out.println(roomList);
                System.out.println(count);
            }
     }
    
     private void countRoom() {  
        tb_1A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_1A, "Room 1A");           
        });
        
        tb_2A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_2A, "Room 2A");           
        });
        
        tb_3A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_3A, "Room 3A");           
        });
        
        tb_4A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_4A, "Room 4A");           
        });
        
        tb_5A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_5A, "Room 5A");           
        });
        
        tb_6A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_6A, "Room 6A");           
        });
        
        tb_7A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_7A, "Room 7A");           
        });
        
        tb_8A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_8A, "Room 8A");           
        });
        
        tb_9A.selectedProperty().addListener((observable, oldValue, newValue) -> {
            selected(tb_9A, "Room 9A");
        });
        
        tb_10A.selectedProperty().addListener((observable, oldValue, newValue) -> {
            selected(tb_10A, "Room 10A");           
        });
        
        tb_1B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_1B, "Room 1B");        
        });
        
        tb_2B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_2B, "Room 2B");           
        });
        
        tb_3B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_3B, "Room 3B");         
        });
        
        tb_4B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_4B, "Room 4B");           
        });
        
        tb_5B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_5B, "Room 5B");
        });
        
        tb_6B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_6B, "Room 6B");          
        });
        
        tb_7B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           selected(tb_7B, "Room 7B");         
        });
        
        tb_8B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            selected(tb_8B, "Room 8B");        
        });
        
        tb_9B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            selected(tb_9B, "Room 9B");
        });
        
        tb_10B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            selected(tb_10B, "Room 10B");
        });
    }
}
