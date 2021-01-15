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
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class BookingInfoController implements Initializable {
    private BookingDetails bd = new BookingDetails();
    private Customer cu = new Customer();
    private FXMain main = new FXMain();
    private File file = new File(".Data");
    private final String curFile = "\\Bookings.txt";
    private List<String>RoomList = new ArrayList<String>(); //RoomList and DateList is used for saving records 
    private List<LocalDate>DateList;
    private List<String>newRoomList; //newRoomList and newDateList is used to read records and validate rooms and dates
    private List<String>newDateList = new ArrayList<String>(); 
    private List<String>searchRoom = new ArrayList<String>(); //searchRoom is used for validate rooms
    private List<String>searchDate;
    private int count;
    private long numOfDaysBetween;
    private final Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private final Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION, null, ButtonType.YES, ButtonType.NO);
    private final SpinnerValueFactory<Integer> nightsValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 31, 1);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

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
    private JFXTextField txtRname;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtIC;
    @FXML
    private Spinner<Integer> NumNightsSpinner;
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
    private Label lblFees;
    @FXML
    private Label lblTax;
    @FXML
    private Label lblTotalfees;
    @FXML
    private JFXButton btnClear;
    @FXML
    private JFXTextField txtSearch;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnDelete;
    
    
    @FXML
    private JFXButton btnBooking;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkFile();
        setDisable();
        main.showTime(dateTime);
        validateTxt();
        validateDays();
        countRoom();
        spinnerListener();
        lblFees.setText(null); lblTax.setText(null); lblTotalfees.setText(null);
    }   
    
    private void checkFile(){
        try {
            FileReader filer = new FileReader(file + curFile);
            System.out.println("File Exist");
        } catch (FileNotFoundException ex) {
            System.out.println("File Do Not Exist");
        }
        
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
  
    @FXML
    private void Save(ActionEvent event) {
        String a = txtSearch.getText();
        String b = txtRname.getText();
        String c = txtContact.getText();
        String d = txtIC.getText();
        LocalDate  e = txtDate.getValue();
        
        if(a.isEmpty()){
            alert.setTitle("BID not specified");
            alert.setHeaderText("The BID was not specified in the search field.\nKey in the BID in the search field before saving.");
            alert.showAndWait();  
        }
        else if(!a.isEmpty() && txtRname.isDisable()){
            alert.setTitle("BID Not Searched");
            alert.setHeaderText("The record was not searched.\nKey in the BID value and press 'Search Icon' in order to Save.");
            alert.showAndWait();
        }
        if(b.isEmpty() || c.isEmpty() || d.isEmpty()){
            alert.setTitle("Empty Fields");
            alert.setHeaderText("One or more fields are empty.\nPlease fill in all the fields to proceed");
            alert.showAndWait();
        }
        else if(e == null){
            alert.setTitle("Date Not Selected");
            alert.setHeaderText("Please Select a date from the date picker to Book");
            alert.showAndWait();
        }
        //validate if the txtcontact is invalid
        else if(c.replaceAll("-", "").length() < 10){
            alert.setTitle("Invalid Contact Number");
            alert.setHeaderText("Please use a valid phone number. e.g '012-3456789'");
            alert.showAndWait();
        }
        //validate if the txtic is invalid
        else if(d.replaceAll("-", "").length() < 12){
            alert.setTitle("Invalid IC");
            alert.setHeaderText("Please use a valid identification number e.g '010234-56-7890'");
            alert.showAndWait();
        }
        //validate if no toggle btns are selected
        else if(bd.getRoomCount() == 0){
            alert.setTitle("Room not Selected");
            alert.setHeaderText("Please select one or more rooms to book");
            alert.showAndWait();
        }
        else if(!a.isEmpty() && !txtRname.isDisable()){
            alert1.setTitle("Save");
            alert1.setHeaderText("Confirm Save?");
            Optional<ButtonType> result = alert1.showAndWait();
            
            if(result.get() == ButtonType.YES && result.isPresent()){
                cu.setName(b);
                cu.setContact(c);
                cu.setIC(d);
                if(saveRecord(a)){
                    alert.setTitle("Save Success");
                    alert.setHeaderText("Record Saved.");
                    alert.showAndWait();
                }
            }
        }
    }
    
    @FXML
    private void Delete(ActionEvent event) {
        String a = txtSearch.getText();
        if(a.isEmpty()){
            alert.setTitle("BID not specified");
            alert.setHeaderText("The BID was not specified in the search field.\nSearch the BID before deleting.");
            alert.showAndWait();
        }
        else if(!a.isEmpty() && txtRname.isDisable()){
            alert.setTitle("BID not specified");
            alert.setHeaderText("The BID was not specified in the search field.\nSearch the BID before deleting.");
            alert.showAndWait();
        }
        else if(!a.isEmpty() && !txtRname.isDisable()){
            alert1.setTitle("Delete");
            alert1.setHeaderText("Confirm Delete?");
            Optional<ButtonType> result = alert1.showAndWait();

            if(result.get() == ButtonType.YES && result.isPresent()){
                if(removeRecord(a)){
                    alert.setTitle("Delete Success");
                    alert.setHeaderText("Record Deleted.");
                    alert.showAndWait();
                    
                }
            }
        }
    }

    @FXML
    private void Clear(ActionEvent event) {
        clearfields();
    }
    
    private boolean saveRecord(String BID){
        boolean saved = false;
        String tmpFile = "\\temp.txt";
        File oldFile = new File(file + curFile);
        File newFile = new File(file + tmpFile);
        getDays(); //set the DateList array
        
        String id; String name; String contact; String ic; String date; String nightCount; String rooms; 
        String roomCount; String fee; String tax; String total;
        
        String newName = cu.getName(); String newContact = cu.getContact(); String newIC = cu.getIC(); int newNightCount = bd.getNightCount();
        double newFee = bd.getFees() ; double newTax = bd.getTax(); double newTotal = bd.getTotal(); String newRoom = bd.getRooms();
        
        try (Scanner x = new Scanner(new FileReader(file + curFile))){
            FileWriter fw = new FileWriter(file + tmpFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x.useDelimiter("\n");
            while(x.hasNext()){
                id = x.next();
                name = x.next();
                contact = x.next();
                ic = x.next();
                date = x.next();
                nightCount = x.next();
                rooms = x.next();
                roomCount = x.next();
                fee = x.next();
                tax = x.next();
                total = x.next();
                x.next();
 
                if(id.substring(12).equals(BID)){
                    pw.println(
                        "Booking ID: " + BID + "\n" + 
                        "Reservee Name: " + newName + "\n" + 
                        "Contact: " + newContact + "\n" + 
                        "IC: " + newIC + "\n" + 
                        "Date: " + this.DateList + "\n" + 
                        "Nights: " + newNightCount + "\n" + 
                        "Room No: " + newRoom + "\n" + 
                        "Room Count: " + count + "\n" + 
                        "Fee: " + newFee + "\n" + 
                        "Tax: " + newTax + "\n" + 
                        "Total Fees: " + newTotal + "\n"
                    );
                    
                    saved = true;
                }
                if(!id.substring(12).equals(BID)){
                    System.out.println(id + "\n" + name + "\n" + contact + "\n" + ic + "\n" + date + "\n" + nightCount + "\n" +  rooms + "\n" + roomCount + 
                    "\n" + fee + "\n" + tax + "\n" + total + "\n");
                    
                    pw.println(id + "\n" + name + "\n" + contact + "\n" + ic + "\n" + date + "\n" + nightCount + "\n" +  rooms + "\n" + roomCount + "\n" + 
                    fee + "\n" + tax + "\n" + total + "\n");
                }
            }        
            x.close();
            pw.flush();
            pw.close();
            fw.close();
            bw.close();
            if(oldFile.delete()){
                System.err.println("deleted");
            }
            else if(!oldFile.delete()){
                System.err.println("Not deleted");
            }
            File dump = new File(file + curFile);
            newFile.renameTo(dump);
            clearfields();
        } catch (Exception ex) {
            Logger.getLogger(BookingInfoController.class.getName()).log(Level.SEVERE, null, ex);            
        } 
        return saved;
    }
    
    private boolean removeRecord(String BID){
        boolean deleted = false;
        String tmpFile = "\\temp.txt";
        File oldFile = new File(file + curFile);
        File newFile = new File(file + tmpFile);
        
        String id; String name; String contact; String ic; String date; String nightCount; String rooms; 
        String roomCount; String fee; String tax; String total;
 
        try (Scanner x = new Scanner(new FileReader(file + curFile))){
            FileWriter fw = new FileWriter(file + tmpFile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            x.useDelimiter("\n");
            while(x.hasNext()){
                id = x.next();
                name = x.next();
                contact = x.next();
                ic = x.next();
                date = x.next();
                nightCount = x.next();
                rooms = x.next();
                roomCount = x.next();
                fee = x.next();
                tax = x.next();
                total = x.next();
                x.next();
 
                if(id.substring(12).equals(BID)){
                    deleted = true;
                }
                if(!id.substring(12).equals(BID)){
//                    System.out.println(id + "\n" + name + "\n" + contact + "\n" + ic + "\n" + date + "\n" + nightCount + "\n" +  rooms + "\n" + roomCount + "\n" + fee + "\n" + tax + "\n" + total + "\n");
                    pw.println(id + "\n" + name + "\n" + contact + "\n" + ic + "\n" + date + "\n" + nightCount + "\n" +  rooms + "\n" + roomCount + "\n" + fee + "\n" + tax + "\n" + total + "\n");
                }
            }        
            x.close();
            pw.flush();
            pw.close();
            fw.close();
            bw.close();
            if(oldFile.delete()){
                System.err.println("deleted");
            }
            else if(!oldFile.delete()){
                System.err.println("Not deleted");
            }
            File dump = new File(file + curFile);
            newFile.renameTo(dump);
            clearfields();
        } catch (Exception ex) {
            Logger.getLogger(BookingInfoController.class.getName()).log(Level.SEVERE, null, ex);            
        }
        return deleted;
    }  

    @FXML
    private void searchRecord(ActionEvent event) {
        boolean found = false;
        searchRoom.clear();
        String id = ""; String name = ""; String contact = ""; String ic = ""; String date; String nightCount; String rooms; 
        String roomCount; String fee; String tax; String total;
        try (Scanner x = new Scanner(new FileReader(file + curFile))){
            x.useDelimiter("\n");
            if(!x.hasNext()){                              
                alert.setTitle("No Records");
                alert.setHeaderText("There are no records contained");
                alert.showAndWait();            
            }
            while(x.hasNext() && !found){
                //loop through the record until no more records to loop or the record has been found
                id = x.next().substring(12);
                name = x.next().substring(15);;
                contact = x.next().substring(9);
                ic = x.next().substring(4);
                date = x.next().substring(6).replaceAll("\\[", "").replaceAll("\\]", "");
                nightCount = x.next().substring(8);
                rooms = x.next().substring(9);
                roomCount = x.next().substring(12);
                fee = x.next().substring(5);
                tax = x.next().substring(5);
                total = x.next().substring(12);
                x.next();
                
                String s = txtSearch.getText();              
                if(s.equals(id)){
                    //change the bool to true to stop looping 
                    found = true;
                }  
                if(found == true){
                    //logic to do if the record is found
                    setEnable();
                    String a = rooms.replaceAll("\\[", "").replaceAll("\\]", "");
                    newRoomList = Arrays.asList(a.split(", "));
                    newDateList = Arrays.asList(date.split(", "));
                    txtRname.setText(name); txtContact.setText(contact); txtIC.setText(ic); txtDate.setValue(LocalDate.parse(newDateList.get(0), formatter)); 
                    NumNightsSpinner.getValueFactory().setValue(Integer.parseInt(nightCount));;lblFees.setText(fee); lblTax.setText(tax); lblTotalfees.setText(total);
                    System.out.println("newRoomList: " + newRoomList);
                    readDate(formatter.format(txtDate.getValue()));
                    cleartgbtn();
                    setDisable();
                    setEnable();
                    checkContain();
                    
                }
                else if(!found && !x.hasNext()){
                    //logic to do if the record if not found/exist
                    alert.setTitle("Recrod Not Found");
                    alert.setHeaderText("The record you're searching for does not exist.");
                    alert.showAndWait();
                    clearfields();
                    break;
                }
            }    
        } catch (Exception ex) {
             Logger.getLogger(BookingInfoController.class.getName()).log(Level.SEVERE, null, ex);            
        }
    }
    
    private void readDate(String d){
        
        String id = ""; String name = ""; String contact = ""; String ic = ""; String date; String nightCount; String rooms; 
        String roomCount; String fee; String tax; String total;
        
        try (Scanner x = new Scanner(new FileReader(file + curFile))){
            x.useDelimiter("\n");            
            while(x.hasNext()){
                id = x.next().substring(12);
                name = x.next().substring(15);
                contact = x.next().substring(9);
                ic = x.next().substring(4);
                date = x.next().substring(6);
                nightCount = x.next().substring(8);
                rooms = x.next().substring(9);
                roomCount = x.next().substring(12);
                fee = x.next().substring(5);
                tax = x.next().substring(5);
                total = x.next().substring(12);
                x.next();
                
                String a = rooms.replaceAll("\\[", "").replaceAll("\\]", "");
                String[] r = a.split(", ");
                Iterator itr = searchDate.iterator(); 
//                while(itr.hasNext()){
//                    System.out.println("date: " + date);
//                    String dl = (String)itr.next();
//                    System.out.println(dl);
                    if(date.contains(d)){ //adds room whenenver the date contains the same date
                        for(int i=0; i<r.length; i++){
                            System.out.println(true);
                            if(!searchRoom.contains(r[i])){ //Do not add the string if the Room already exist in the array
                                searchRoom.add(r[i]);
                            }
                        }
                    }
//                }
                checkContain();
                if(date.contains(d)){ //adds room whenenver the date contains the same date
                    for(int i=0; i<r.length; i++){
                        if(!searchRoom.contains(r[i])){ //Do not add the string if the Room already exist in the array
                            searchRoom.add(r[i]);
                        }
                    }
                }
            }
            System.out.println("newRoomList: " + newRoomList);
            System.out.println("newDateList: " + newDateList);
            System.out.println("searchRoom: " + searchRoom);
        } catch (Exception ex) {
            Logger.getLogger(BookingInfoController.class.getName()).log(Level.SEVERE, null, ex);            
        } 
    }
    
    private void checkContain(){
        isContain(tb_1A, "Room 1A"); isContain(tb_2A, "Room 2A"); isContain(tb_3A, "Room 3A"); isContain(tb_4A, "Room 4A"); 
        isContain(tb_5A, "Room 5A"); isContain(tb_6A, "Room 6A"); isContain(tb_7A, "Room 7A"); isContain(tb_8A, "Room 8A");
        isContain(tb_9A, "Room 9A"); isContain(tb_10A, "Room 10A");

        isContain(tb_1B, "Room 1B"); isContain(tb_2B, "Room 2B"); isContain(tb_3B, "Room 3B"); isContain(tb_4B, "Room 4B"); 
        isContain(tb_5B, "Room 5B"); isContain(tb_6B, "Room 6B"); isContain(tb_7B, "Room 7B"); isContain(tb_8B, "Room 8B");
        isContain(tb_9B, "Room 9B"); isContain(tb_10B, "Room 10B");
    }
    
    private void isContain(JFXToggleButton a, String b){
        //this function validates room on same date
        Iterator itr = searchRoom.iterator();
        while(itr.hasNext()){
            String y = (String)itr.next();
            if((newRoomList.contains(b))){ //validate the rooms for current date first and set it to green
                a.setToggleColor(Paint.valueOf("#009688"));
                a.setToggleLineColor(Paint.valueOf("#77c2bb"));
                a.setDisable(false);
                a.setSelected(true);
            }
            else if(y.equals(b)){ //then only validate for rooms that are canceled set it to red and disabled
                a.setToggleColor(Paint.valueOf("#bf0101"));
                a.setToggleLineColor(Paint.valueOf("#ff4545"));
                a.setDisable(true);
                a.setSelected(true);

            }
            else if(!searchRoom.contains(b)){ //when datepicker is changed it readDate() will be triggered and reset the toggle button to normal
                a.setToggleColor(Paint.valueOf("#009688"));
                a.setToggleLineColor(Paint.valueOf("#77c2bb"));
                a.setDisable(false);
                a.setSelected(false);

            }
        }
    }
    
    private void isSelected(JFXToggleButton a, String b){
        //to determine if toggle btn is selected and it is not disabled
        if(a.isSelected() && !a.isDisable()){ //increment the room count
                RoomList.add(b);
                bd.setRooms(RoomList.toString());
                count++;
                updatePayment(); //updates the fees after adding the room
                System.out.println("Room List: " + RoomList + "\n"+ count);
            }
        else if(!a.isSelected()){//decrement the roomCount
            Iterator itr = RoomList.iterator();
            while (itr.hasNext()) 
            { 
                String x = (String)itr.next(); 
                if (x.equals(b)){ 
                    count--;
                    itr.remove();
                    bd.setRooms(RoomList.toString());
                    updatePayment(); //updates the fees after removing the room
                }
            }          
            System.out.println("RoomList: " + RoomList + "\n"+ count);
        }
        if(a.isSelected() && !newRoomList.contains(b) && !a.isDisable()){ //to show selected rooms other than the current rooms                     
            a.setToggleColor(Paint.valueOf("#e9610c"));
            a.setToggleLineColor(Paint.valueOf("#f69051"));           
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
            searchRoom.clear();
            readDate(formatter.format(txtDate.getValue()));
            cleartgbtn();
            setEnable();
            checkContain();
        });
    }
    
     private void validateTxt(){
         //validating textfields
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
            if(!newValue.matches("^[\\d-]+")){
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
    }
    
    private void validateEffects(JFXTextField a, Label b, boolean c, String d){
        //add styling to txt field
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
    
    private void getDays(){
        LocalDate startDate = txtDate.getValue();
        String d = formatter.format(startDate);
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try{
           calendar.setTime(sdf.parse(d));
        }catch(ParseException ex){
           Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        calendar.add(Calendar.DAY_OF_MONTH, bd.getNightCount()-1);
        String end = sdf.format(calendar.getTime());
        LocalDate endDate = LocalDate.parse(end);
        numOfDaysBetween = ChronoUnit.DAYS.between(startDate, endDate)+1;
        DateList = IntStream.iterate(0, i -> i + 1).limit(numOfDaysBetween).mapToObj(i -> startDate.plusDays(i)).collect(Collectors.toList());
        String a = DateList.toString().replaceAll("\\[", "").replaceAll("\\]", "");
        bd.setDate(a);
        searchDate = Arrays.asList(a.split(", "));
        System.out.println("DateList: " +DateList);
        System.out.println("searchDate: " +searchDate);
    }
    
    private void cleartgbtn(){
        //set all toggle button to false
        tb_1A.setSelected(false); tb_2A.setSelected(false); tb_3A.setSelected(false); tb_4A.setSelected(false); tb_5A.setSelected(false);
        tb_6A.setSelected(false); tb_7A.setSelected(false); tb_8A.setSelected(false); tb_9A.setSelected(false); tb_10A.setSelected(false);
        
        tb_1B.setSelected(false); tb_2B.setSelected(false); tb_3B.setSelected(false); tb_4B.setSelected(false); tb_5B.setSelected(false);
        tb_6B.setSelected(false); tb_7B.setSelected(false); tb_8B.setSelected(false); tb_9B.setSelected(false); tb_10B.setSelected(false);
        
        //setToggle color
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
    
    private void clearfields(){
        //clear txtfields
        txtSearch.clear();
        txtRname.clear();
        txtContact.clear();
        txtIC.clear();
        txtDate.getEditor().clear();
        NumNightsSpinner.getValueFactory().setValue(1);

        cleartgbtn();
        setDisable();
    }
    
    private void setDisable(){ //diable txtfields and toggle buttons
        txtRname.setDisable(true);
        txtContact.setDisable(true);
        txtIC.setDisable(true);
        txtDate.setDisable(true);
        NumNightsSpinner.setDisable(true);
        
        tb_1A.setDisable(true); tb_2A.setDisable(true); tb_3A.setDisable(true); tb_4A.setDisable(true); tb_5A.setDisable(true);
        tb_6A.setDisable(true); tb_7A.setDisable(true); tb_8A.setDisable(true); tb_9A.setDisable(true); tb_10A.setDisable(true);
        
        tb_1B.setDisable(true); tb_2B.setDisable(true); tb_3B.setDisable(true); tb_4B.setDisable(true); tb_5B.setDisable(true);
        tb_6B.setDisable(true); tb_7B.setDisable(true); tb_8B.setDisable(true); tb_9B.setDisable(true); tb_10B.setDisable(true);
    }
    
    private void setEnable(){ //enable txtfields and togglebuttons
        txtRname.setDisable(false);
        txtContact.setDisable(false);
        txtIC.setDisable(false);
        txtDate.setDisable(false);
        NumNightsSpinner.setDisable(false);
        
        tb_1A.setDisable(false); tb_2A.setDisable(false); tb_3A.setDisable(false); tb_4A.setDisable(false); tb_5A.setDisable(false);
        tb_6A.setDisable(false); tb_7A.setDisable(false); tb_8A.setDisable(false); tb_9A.setDisable(false); tb_10A.setDisable(false);
        
        tb_1B.setDisable(false); tb_2B.setDisable(false); tb_3B.setDisable(false); tb_4B.setDisable(false); tb_5B.setDisable(false);
        tb_6B.setDisable(false); tb_7B.setDisable(false); tb_8B.setDisable(false); tb_9B.setDisable(false); tb_10B.setDisable(false);
    }
    
    private void countRoom() {
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