/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author user
 */
public class HomeController implements Initializable {
    private File file = new File(".Data");
    private final String curFile = "\\Bookings.txt";
    private FXMain main = new FXMain();
    
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
    @FXML
    private Label dateTime = new Label();
    @FXML
    private Label lblRoomCount;

    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        main.showTime(dateTime); 
        readRooms();
    }
    
    @FXML
    protected void Logout(ActionEvent event) throws IOException {
        main.Logout(event);
    } 

    @FXML
    protected void Booking(ActionEvent event) throws IOException{
        main.Booking(event);
    }

    @FXML
    private void AddBooking(ActionEvent event) throws IOException{
        main.AddBooking(event);
    }
    
    private void readRooms(){
        List<String> Room;
        List<String> temp = new ArrayList<String>();
        String id; String name; String contact; String ic; String email; String date; String nightCount; String rooms; 
        String roomCount; String fee; String tax; String total;
        try (Scanner x = new Scanner(new FileReader(file + curFile))){
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
                Room = Arrays.asList(a.split(", "));
                if(date.contains(LocalDate.now().toString())){
                    for(int i=0; i<Room.size(); i++){
                        if(!temp.contains(Room.get(i))){
                            temp.add(Room.get(i));
                            System.out.println(Room.get(i));
                        }
                    }
                }
            }
            
            lblRoomCount.setText(String.valueOf(temp.size()) + "/20");
        } catch (Exception ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);            
        } 
    }
}

