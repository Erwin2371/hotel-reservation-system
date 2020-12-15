/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class LoginController implements Initializable {
    
    @FXML
    private JFXTextField Unametxt;
    @FXML
    private JFXButton Loginbtn;
    @FXML
    private JFXPasswordField Passtxt;

    private int line;
    @FXML
    private JFXCheckBox shpass;
    @FXML
    private JFXTextField Passtxt2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createFolder();
        readfile();
        
    }   
    FXMain main = new FXMain();
    File file = new File("C:\\Program Files\\GitHub\\Lmao\\JP Assignment");

    private void createFolder(){
        if(!file.exists()){
            file.mkdir();
        }
        else{
            System.err.println("Directory Exists");
        }
    }
    
    private void readfile() {
        try {
            FileReader filer = new FileReader(file + "\\login.txt");
            System.err.println("File exists!");
        } catch (FileNotFoundException e) {
            try {
                FileWriter fw = new FileWriter(file + "\\login.txt");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("file Created");
        }
    }
    
    private void addCred(String uname, String pass) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\login.txt", "rw");
            
            for(int i=0; i<line; i++){
                raf.readLine();
            }
            raf.writeBytes("Username: " + uname + "\n");
            raf.writeBytes("Password: " + pass + "\n");
            raf.writeBytes("\n");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    private void checkCred(String uname, String pass, ActionEvent event) {
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\login.txt", "r");
            
            for(int i=1; i<line; i+=3){
                System.out.println("Count: "+ i);
                String username = raf.readLine().substring(10);
                String password = raf.readLine().substring(10); 
                System.out.println(username + "\n" + password);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(uname.equals(username) && pass.equals(password)){
                    alert.setTitle("Login");
                    alert.setHeaderText("Login Success");
                    Optional<ButtonType> result = alert.showAndWait();

                    if(result.get() == ButtonType.OK && result.isPresent()){
                        main.Login(event);
                    }
                    break;
                }
                else if(i == (line-2)){
                    alert.setTitle("Login");
                    alert.setHeaderText("Incorrect Username or Password");
                    alert.showAndWait();
                    break;
                }
                for(int k=1; k<=1; k++){
                    raf.readLine();
                }
            }
            
        } catch (FileNotFoundException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private boolean validateReg(String a) {
        boolean exist = false;
        
        try {
            RandomAccessFile raf = new RandomAccessFile(file + "\\login.txt", "r");
            
            for(int i=1; i<line; i+=3){
                System.out.println("Count: "+ i);
                String username = raf.readLine().substring(10); 
                System.out.println(username);
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                if(a.equals(username)){
                    exist = true;
                }
                for(int k=1; k<=2; k++){
                    raf.readLine();
                }
            }

        } catch (FileNotFoundException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);

        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }    
        return exist;
    }
    
    private void countLines() {
        try {
            line = 0;
            RandomAccessFile raf = new RandomAccessFile(file + "\\login.txt", "rw");
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
    
    @FXML
    private void Login(ActionEvent event) throws IOException{
        String a = Unametxt.getText();
        String b = Passtxt.getText();
        if(a.isEmpty() && b.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Login Error");
            alert.setHeaderText("Username and Password are empty");
            alert.showAndWait();
        }else{
            countLines();
            checkCred(a, b, event);
        }
    }  

    @FXML
    private void Register(ActionEvent event) throws IOException{
        String a = Unametxt.getText();
        String b = Passtxt.getText();
        countLines();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        if(a.isEmpty() && b.isEmpty()){
            alert.setTitle("Resgistration Error");
            alert.setHeaderText("Username and Password are empty");
            alert.showAndWait();
        }
        else if(b.length() < 8){
            alert.setTitle("Resgistration Error");
            alert.setHeaderText("Minimum password length requires\n8 or more characters");
            alert.setGraphic(new ImageView(this.getClass().getResource("warning.png").toString()));
            alert.showAndWait();
        }
        else if(validateReg(a) == true){
            alert.setTitle("Registration Error");
            alert.setHeaderText("The username already exists,\nTry again.");
            alert.showAndWait();
        }
        else{
            addCred(a, b);
        }
    }

    @FXML
    private void ShowPass(MouseEvent event) {
        String a = Passtxt.getText();
        String b = Passtxt2.getText();
                
        if(shpass.isSelected()){
            Passtxt.setVisible(false);
            Passtxt2.setVisible(true);
            Passtxt2.setText(a);
            Passtxt2.setEditable(true);
            Passtxt2.setDisable(false);
            if(a.isEmpty()){
                Passtxt2.setPromptText("Password");
            }
        }
        else if(!shpass.isSelected()){
            Passtxt.setVisible(true);
            Passtxt2.setVisible(false);
            Passtxt.setText(b);
            Passtxt2.setEditable(false);
        }
    }
}
