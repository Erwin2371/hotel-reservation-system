package javaassignment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

class Customer{
    private String name;
    private String contact;
    private String IC;
    private String email;
    
    public void setName(String a){
        name = a;
    }
    public void setContact(String a){
        contact = a;
    }
    public void setIC(String a){
        IC = a;
    }
    public void setEmail(String n){
        email = n;
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
    public String getEmail(){
        return email;
    }
}

class Room {
    private int roomCount;
    private String rooms;
    
    public void setRooms(String n){
        this.rooms = n;
    }
    public void setRoomCount(int n){
        this.roomCount = n;
    }
    public String getRooms(){
        return rooms;
    }
    public int getRoomCount(){
        return roomCount;
    }
}

class Payment extends Room {
    private int nightCount;
    private double fees;
    private double tax;
    private double total;

    public void setNightCount(int n){
        this.nightCount = n;
    }
    public void setFees(double n){
        this.fees = n;
    }
    public void setTax(double n){
        this.tax = n;
    }
    public void setTotal(double n){
        this.total = n;
    }
    public int getNightCount(){
        return nightCount;
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
    public void calFees(){
       this.fees = getNightCount() * super.getRoomCount() * 350.0; 
    }
    public void calTax(){
        this.tax = (getFees() * 0.1) + (10 * getNightCount());
    }
    public void calTotal(){
        this.total = getFees() + getTax();
    }
}

class BookingDetails extends Payment{
    private int id;
    private String date;
    
    public void setID(int n){
        this.id = n;
    }   
    public void setDate(String n){
        this.date = n;
    }
    public int getID(){
        return id;
    } 
    public String getDate(){
        return date;
    }
}

public class AddBookingController implements Initializable {
    private Customer cu = new Customer();
    private BookingDetails bd = new BookingDetails();
    private final FXMain main = new FXMain();
    private final File file = new File(".Data");
    private final String curFile = "\\Bookings.txt";
    private int count = 0;
    private List<String> RoomList = new ArrayList<String>(); // RoomList and DateList used for creating records
    private List<String> newRoomList = new ArrayList<String>(); //newRoomList and newDateList used for reading records
    private List<LocalDate> DateList;
    private List<String> newDateList;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final SpinnerValueFactory<Integer> nightsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1); //set the spinner init, min and max value
    
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
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField txtEmail;
    @FXML
    private Label lblEmail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime);
        checkFile();
        spinnerListener();
        validateTxt();
        validateDays();
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
        //go to home page
        main.Login(event);
    }

    @FXML
    private void Booking(ActionEvent event) throws IOException {
        //go to booking info page
        main.Booking(event);
    }

    @FXML
    private void Logout(ActionEvent event) throws IOException{
        //go to login page
        main.Logout(event);
    }
    
    @FXML
    private void Clear(ActionEvent event) {
        //clear all fields including resetting toggle button
        clearfields();
    }
    
    private void checkFile() {
        //check file exists or not
        try {
            FileReader filer = new FileReader(file + "\\Bookings.txt");
            System.out.println("File Exists!");
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
        String email = txtEmail.getText();
        
        //validate is there is empty fields
        if(rname.isEmpty() || contact.isEmpty() || ic.isEmpty()){
            main.setAlert("Empty Fields", "One or more fields are empty.\nPlease fill in all the fields to proceed");
        }
        else if(txtDate.getValue() == null){
            main.setAlert("Date Not Selected", "Please Select a date from the date picker to Book");
        }
        //validate if the contact is invalid
        else if(contact.replaceAll("-", "").length() < 10){
            main.setAlert("Invalid Contact Number", "Please use a valid phone number. e.g '012-3456789'");
        }
        //validate if the ic is invalid
        else if(ic.replaceAll("-", "").length() < 12){
            main.setAlert("Invalid IC", "Please use a valid identification number e.g '010234-56-7890'");
        }
        //validate if the email is invalid
        else if(!txtEmail.getText().matches("^[A-Za-z0-9+_.-]+@(.+)") || lblEmail.isVisible()){
            main.setAlert("Invalid Email", "Please use a valid email before booking e.g 'johndoe@gmail.com'");
        }
        //validate if no toggle btns are selected
        else if(bd.getRoomCount() == 0){
            main.setAlert("Room not Selected", "Please select one or more rooms to book");
        }
        else{
            cu.setName(rname); //set name variable
            cu.setContact(contact); //set contact variable 
            cu.setIC(ic); //set ic variable
            cu.setEmail(email); //set email variable
            String date = DateList.toString(); 
            bd.setDate(date); //set date variable
            //create the booking 
            if(createBooking(bd.getID(), cu.getName(), cu.getContact(), cu.getIC(), cu.getEmail(), bd.getDate(), bd.getNightCount(), RoomList, 
                    bd.getRoomCount(), bd.getFees(), bd.getTax(), bd.getTotal())){
                main.setAlert("create Booking", "Booking Success");
            }
            else{
                main.setAlert("Create Booking", "Booking Failed");
            }
        }           
    }
    
    private boolean createBooking(int id, String name, String contact, String ic, String email, String date, 
            int nights, List rooms, int rcount, double fee, double tax, double total){
        boolean created = false;
        try {
            FileWriter fw = new FileWriter(file + "\\Bookings.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            
            pw.println(
                "Booking ID: BID" + id + "\n" +
                "Reservee Name: " + name + "\n" +
                "Contact: " + contact + "\n" +
                "IC: " + ic + "\n" +
                "Email: " + email + "\n" +
                "Date: " + date + "\n" +
                "Nights: " + nights + "\n" +
                "Room No: " + rooms + "\n" +
                "Room Count: " + rcount + "\n" +
                "Fee: " + fee + "\n" +
                "Tax: " + tax + "\n" +
                "Total Fees: " + total + "\n"
            );          
            pw.flush();
            pw.close();
            fw.close();
            bw.close();
            created = true;
            clearfields();
            readDate(formatter.format(txtDate.getValue()));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddBookingController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex){
            Logger.getLogger(AddBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return created;
    }
    
    private void readDate(String d){
        int idCount = 1;
        List<String> sameDR = new ArrayList<String>();
        String id; String name; String contact; String ic; String email; String date; String nightCount; String rooms; 
        String roomCount; String fee; String tax; String total;
        
        try(Scanner x = new Scanner(new File(file + curFile))){
            x.useDelimiter("\n");
            
            while(x.hasNext()){
                id = x.next().substring(12);
                name = x.next().substring(15);
                contact = x.next().substring(9);
                ic = x.next().substring(4);
                email = x.next().substring(7);
                date = x.next().substring(6);
                nightCount = x.next().substring(8);
                rooms = x.next().substring(9);
                roomCount = x.next().substring(12);
                fee = x.next().substring(5);
                tax = x.next().substring(5);
                total = x.next().substring(12);
                x.next();

                String a = rooms.replaceAll("\\[", "").replaceAll("\\]", "");
                String b = date.replaceAll("\\[", "").replaceAll("\\]", "");
                List<String> r = Arrays.asList(a.split(", "));
                List<String> q = Arrays.asList(b.split(", "));
                if(id.matches(".*BID.*")){
                    idCount++;
                    if(idCount >= bd.getID()){
                        bd.setID(idCount);
                    }
                }
                if(date.contains(d)){
                    for(int i=0; i<r.size(); i++){
                        if(!sameDR.contains(r.get(i))){
                            sameDR.add(r.get(i));
                        }
                    }
                }
                Iterator itr = newDateList.iterator();
                while(itr.hasNext()){
                    String z = (String)itr.next();
   
                    if(date.contains(z)){
                        for(int i=0; i<r.size(); i++){
                            if(!newRoomList.contains(r.get(i))){ //Do not add the string if the Room already exist in the array
                                newRoomList.add(r.get(i));
                            }
                        } 
                    }
                }
                System.out.println("sameDR: " + sameDR);
                for(int i=0; i<r.size(); i++){//if the array doesn't have the first date searched from the text then remove the room
                    if(!newDateList.contains(q.get(0))&&!sameDR.contains(r.get(i))){ //remove the room if searchDate does not contain the date
                          newRoomList.remove(r.get(i)); 
                    }
                }                    
                checkContain();
            }
            System.out.println("newRoomList: " + newRoomList);
        } catch (Exception ex) {
            Logger.getLogger(BookingInfoController.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    private void checkContain(){
        isContain("Room 1A", tb_1A); isContain("Room 2A", tb_2A); isContain("Room 3A", tb_3A);                   
        isContain("Room 4A", tb_4A); isContain("Room 5A", tb_5A); isContain("Room 6A", tb_6A);
        isContain("Room 7A", tb_7A); isContain("Room 8A", tb_8A); isContain("Room 9A", tb_9A);                   
        isContain("Room 10A", tb_10A);

        isContain("Room 1B", tb_1B); isContain("Room 2B", tb_2B); isContain("Room 3B", tb_3B);                   
        isContain("Room 4B", tb_4B); isContain("Room 5B", tb_5B); isContain("Room 6B", tb_6B);                   
        isContain("Room 7B", tb_7B); isContain("Room 8B", tb_8B); isContain("Room 9B", tb_9B);                   
        isContain("Room 10B", tb_10B);
    }
         
    private void isContain(String a, JFXToggleButton b){
        if((newRoomList.contains(a))){ //validate the rooms for current date first and set it to green
               b.setToggleColor(Paint.valueOf("#bf0101"));
               b.setToggleLineColor(Paint.valueOf("#ff4545"));
               b.setDisable(true);
               b.setSelected(true);
        }
        if(!newRoomList.contains(a)){ //reset the toggle button if newRoomList array do not contain the element a / clear off selected Rooms triggered by number spinner
            b.setDisable(false);
            b.setSelected(false);
            b.setToggleColor(Paint.valueOf("#009688"));
            b.setToggleLineColor(Paint.valueOf("#77c2bb"));
        }
    }
    
    private void spinnerListener(){
    //listener for number spinner to change the number value and updates the fees, tax and total fees
        NumNightsSpinner.setValueFactory(nightsValueFactory);

        NumNightsSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            updatePayment();
            if(txtDate.getValue() != null){
                getDays();
                readDate(formatter.format(txtDate.getValue()));
            }
        });
    }
    
    private void validateDays(){
        //determine all the dates from the start date from datepicker to the end date given by the number spinner
        txtDate.valueProperty().addListener((observable, oldValue, newValue) -> {
            getDays();
            newRoomList.clear();
            cleartgbtn(); 
            readDate(formatter.format(txtDate.getValue()));
        });
    }
    
    private void getDays(){ //to find the end dates and the dates between the start and end date based on the number spinner value
        LocalDate startDate = txtDate.getValue();
        String d = formatter.format(startDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
           calendar.setTime(sdf.parse(d));
        }catch(ParseException ex){
           Logger.getLogger(AddBookingController.class.getName()).log(Level.SEVERE, null, ex);
        }
        calendar.add(Calendar.DAY_OF_MONTH, bd.getNightCount()-1);
        String end = sdf.format(calendar.getTime());
        LocalDate endDate = LocalDate.parse(end);
        long numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate)+1;
        DateList = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i)).collect(Collectors.toList()); 
        bd.setDate(DateList.toString());
        String a = DateList.toString().replaceAll("\\[", "").replaceAll("\\]", "");
        newDateList = Arrays.asList(a.split(", "));
        bd.setDate(a);
//        System.out.println("DateList: "+DateList);
        System.out.println("newDateList: "+ newDateList);       
        
    }
    
    private void validateTxt(){
        //validate all txt fields
        txtRname.textProperty().addListener((observable, oldValue, newValue) -> {
           if(!newValue.matches("\\sa-zA-Z*")){
               txtRname.setText(newValue.replaceAll("[^\\sa-zA-Z]", ""));;
           }
       });
        
        txtContact.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()){
                validateEffects(txtContact, lblContact, true, "");
            }
            else if(!newValue.matches("^[\\d-]+")){
                txtContact.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else if(newValue.length() > 11){
                txtContact.setText(txtContact.getText().substring(0, 11));
            }
            else if(newValue.replaceAll("-", "").length() >= 10 && !newValue.matches("^\\d{3}-\\d{7,8}$")){
                //validate contact format
                txtContact.setText(txtContact.getText().replaceAll("^(\\d{3})(\\d{7,8})$", "$1-$2"));    
                validateEffects(txtContact, lblContact, true, "");
                cu.setContact(newValue);
            }
            else if(newValue.replaceAll("-", "").length() == 10 && newValue.matches("^01\\d{1}-\\d{7,8}$")){
                validateEffects(txtContact, lblContact, true, "");
                cu.setContact(newValue);
            }
            else if(newValue.length() < 11){
                String a = "Invalid Contact Number";
                validateEffects(txtContact, lblContact, false, a);
            }
        });
        
        txtIC.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()){
                validateEffects(txtIC, lblIC, true, "");
            }
            else if(!newValue.matches("^[\\d-]+")){
                txtIC.setText(newValue.replaceAll("[^\\d]", ""));
            }
            else if(newValue.length() > 14){
                txtIC.setText(txtIC.getText().substring(0, 14));
            }
            else if(newValue.replaceAll("-", "").length() == 12 && newValue.matches("^\\d{6}-\\d{2}-\\d{4}$")){
                validateEffects(txtIC, lblIC, true, "");
                cu.setIC(newValue);
            }
            else if(newValue.replaceAll("-", "").length() == 12 && !newValue.matches("^\\d{6}-\\d{2}-\\d{4}$")){
                //validate ic format
                txtIC.setText(txtIC.getText().replaceAll("^(\\d{6})(\\d{2})(\\d{4})$", "$1-$2-$3"));
                validateEffects(txtIC, lblIC, true, "");
                cu.setIC(newValue);
            }
            else if(newValue.length() < 14){
                String a = "Invalid IC";
                validateEffects(txtIC, lblIC, false, a);
            }
            else if(!newValue.matches("^\\d{6}-\\d{2}-\\d{4}$")){
                String a = "Invalid IC";
                validateEffects(txtIC, lblIC, false, a);
            }
        });
        
        txtEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.isEmpty()){
                validateEffects(txtEmail, lblEmail, true, "");
            }
            else if(newValue.matches("^[A-Za-z0-9+_.-]+@(.+)")){
                validateEffects(txtEmail, lblEmail, true, "");
            }
            else if(!newValue.matches("^[A-Za-z0-9+_.-]+@(.+)")){
                String a = "Invalid Email";
                validateEffects(txtEmail, lblEmail, false, a);
            }
        });
    }
    
    private void validateEffects(JFXTextField a, Label b, boolean c, String d){
        //add styling to txt field if the value is in the wrong format in the validatetxt function
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
      
    protected void updatePayment(){
        //function to update and set the variables
        bd.setRoomCount(count);
        bd.setNightCount(NumNightsSpinner.getValue());
        bd.calFees();
        bd.calTax();
        bd.calTotal();

        lblFees.setText(String.valueOf(bd.getFees()));
        lblTax.setText(String.valueOf(bd.getTax()));
        lblTotalfees.setText(String.valueOf(bd.getTotal()));
    }
    
    private void clearbtn(JFXToggleButton a){
        if(a.isSelected() && a.isDisable()){ //maintain the rooms that are cancel for the datepicker date
            
        }
        else{
            a.setSelected(false);
            a.setDisable(false);
            a.setToggleColor(Paint.valueOf("#009688"));
            a.setToggleLineColor(Paint.valueOf("#77c2bb"));
        }
    }
    private void isSelected(JFXToggleButton a, String b){
        //to determine if toggle btn is selected and it is not disabled
         if(a.isSelected() && a.isDisable() == false){
             //if selected then add the String 'b' into the RoomList array and increment the count - for roomCount
                RoomList.add(b);
                bd.setRooms(RoomList.toString());
                count++;
                updatePayment();
                System.out.println("RoomList: " + bd.getRooms());
                System.out.println("RoomCount: " + count);
            }
            else if(!a.isSelected()){
                //iterator to loop through the array to find if the String 'b' exists in the array
                Iterator itr = RoomList.iterator();
                while (itr.hasNext()) 
                { 
                    String x = (String)itr.next(); 
                    if (x.equals(b) ){ 
                        //remove the String 'b' from the RoomList array if the toggle btn is deselected
                        itr.remove(); 
                        count--; //decrement the count - used for roomCount
                        updatePayment();
                    }                
                }              
                System.out.println("RoomList: " + RoomList);
                System.out.println("RoomCount: " + count);
            }
     }
    
    private void clearfields(){
         //clear all txtfields and reset all toggle btn
        txtRname.clear();
        txtContact.clear();
        txtIC.clear();
        txtEmail.clear();
        txtDate.setValue(LocalDate.now());
        NumNightsSpinner.getValueFactory().setValue(1);
        
        clearbtn(tb_1B); clearbtn(tb_2B); clearbtn(tb_3B); clearbtn(tb_4A); clearbtn(tb_5A);
        clearbtn(tb_6B); clearbtn(tb_7B); clearbtn(tb_8B); clearbtn(tb_9A); clearbtn(tb_10A);

        clearbtn(tb_1B); clearbtn(tb_2B); clearbtn(tb_3B); clearbtn(tb_4B); clearbtn(tb_5B);
        clearbtn(tb_6B); clearbtn(tb_7B); clearbtn(tb_8B); clearbtn(tb_9B); clearbtn(tb_10B);
        cleartgbtn();
     }
     
     private void cleartgbtn(){        
        //set disable to false
        tb_1A.setSelected(false); tb_2A.setSelected(false); tb_3A.setSelected(false); tb_4A.setSelected(false); tb_5A.setSelected(false);
        tb_6A.setSelected(false); tb_7A.setSelected(false); tb_8A.setSelected(false); tb_9A.setSelected(false); tb_10A.setSelected(false);
        
        tb_1B.setSelected(false); tb_2B.setSelected(false); tb_3B.setSelected(false); tb_4B.setSelected(false); tb_5B.setSelected(false);
        tb_6B.setSelected(false); tb_7B.setSelected(false); tb_8B.setSelected(false); tb_9B.setSelected(false); tb_10B.setSelected(false);
        
        setColor();
        
        //set all toggle button to false
        tb_1A.setDisable(false); tb_2A.setDisable(false); tb_3A.setDisable(false); tb_4A.setDisable(false); tb_5A.setDisable(false);
        tb_6A.setDisable(false); tb_7A.setDisable(false); tb_8A.setDisable(false); tb_9A.setDisable(false); tb_10A.setDisable(false);
        
        tb_1B.setDisable(false); tb_2B.setDisable(false); tb_3B.setDisable(false); tb_4B.setDisable(false); tb_5B.setDisable(false);
        tb_6B.setDisable(false); tb_7B.setDisable(false); tb_8B.setDisable(false); tb_9B.setDisable(false); tb_10B.setDisable(false); 
        
    }
    
    private void setColor(){
        tb_1A.setToggleColor(Paint.valueOf("#009688")); tb_2A.setToggleColor(Paint.valueOf("#009688")); tb_3A.setToggleColor(Paint.valueOf("#009688")); tb_4A.setToggleColor(Paint.valueOf("#009688"));
        tb_5A.setToggleColor(Paint.valueOf("#009688")); tb_6A.setToggleColor(Paint.valueOf("#009688")); tb_7A.setToggleColor(Paint.valueOf("#009688")); tb_8A.setToggleColor(Paint.valueOf("#009688"));
        tb_9A.setToggleColor(Paint.valueOf("#009688")); tb_10A.setToggleColor(Paint.valueOf("#009688"));
        
        tb_1B.setToggleColor(Paint.valueOf("#009688")); tb_2B.setToggleColor(Paint.valueOf("#009688")); tb_3B.setToggleColor(Paint.valueOf("#009688")); tb_4B.setToggleColor(Paint.valueOf("#009688"));
        tb_5B.setToggleColor(Paint.valueOf("#009688")); tb_6B.setToggleColor(Paint.valueOf("#009688")); tb_7B.setToggleColor(Paint.valueOf("#009688")); tb_8B.setToggleColor(Paint.valueOf("#009688"));
        tb_9B.setToggleColor(Paint.valueOf("#009688")); tb_10B.setToggleColor(Paint.valueOf("#009688"));
        
        //setToggleLine
        tb_1A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_2A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_3A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_4A.setToggleLineColor(Paint.valueOf("#77c2bb"));
        tb_5A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_6A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_7A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_8A.setToggleLineColor(Paint.valueOf("#77c2bb"));
        tb_9A.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_10A.setToggleLineColor(Paint.valueOf("#77c2bb"));
        
        tb_1B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_2B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_3B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_4B.setToggleLineColor(Paint.valueOf("#77c2bb"));
        tb_5B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_6B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_7B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_8B.setToggleLineColor(Paint.valueOf("#77c2bb"));
        tb_9B.setToggleLineColor(Paint.valueOf("#77c2bb")); tb_10B.setToggleLineColor(Paint.valueOf("#77c2bb"));
    } 
    
     protected void countRoom() {
         //add listener for each toggle button to determine the total roomCount using the selected() function
        tb_1A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_1A, "Room 1A");           
        });
        
        tb_2A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_2A, "Room 2A");           
        });
        
        tb_3A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_3A, "Room 3A");           
        });
        
        tb_4A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_4A, "Room 4A");           
        });
        
        tb_5A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_5A, "Room 5A");           
        });
        
        tb_6A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_6A, "Room 6A");           
        });
        
        tb_7A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_7A, "Room 7A");           
        });
        
        tb_8A.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_8A, "Room 8A");           
        });
        
        tb_9A.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isSelected(tb_9A, "Room 9A");
        });
        
        tb_10A.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isSelected(tb_10A, "Room 10A");           
        });
        
        tb_1B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_1B, "Room 1B");        
        });
        
        tb_2B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_2B, "Room 2B");           
        });
        
        tb_3B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_3B, "Room 3B");         
        });
        
        tb_4B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_4B, "Room 4B");           
        });
        
        tb_5B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_5B, "Room 5B");
        });
        
        tb_6B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_6B, "Room 6B");          
        });
        
        tb_7B.selectedProperty().addListener((observable, oldValue, newValue) -> {
           isSelected(tb_7B, "Room 7B");         
        });
        
        tb_8B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isSelected(tb_8B, "Room 8B");        
        });
        
        tb_9B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isSelected(tb_9B, "Room 9B");
        });
        
        tb_10B.selectedProperty().addListener((observable, oldValue, newValue) -> {
            isSelected(tb_10B, "Room 10B");
        });
    }
}