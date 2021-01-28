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
import java.io.BufferedWriter;
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
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

class Staff {
    protected int ID;
    protected String Username;
    protected String Password;
    
    public void setID(int n){
        this.ID = n;
    } 
    public void setUsername(String n){
        this.Username = n;
    }
    public void setPassword(String n){
        this.Password = n;
    } 
    public int getID(){
        return ID;
    } 
}

public class LoginController implements Initializable {
    private FXMain main = new FXMain();
    private Staff st = new Staff();
    private static File file = new File(".Data");
    private static Alert alert = new Alert(Alert.AlertType.INFORMATION);
     
    @FXML
    private JFXTextField txtUname;
    @FXML
    private JFXPasswordField txtPass;
    @FXML
    private JFXCheckBox cbShowPass;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private JFXButton btnRegister;
    @FXML
    private JFXTextField txtPass2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createFolder();
        checkFile();
        readFile();
    }   
    
    @FXML
    private void Login(ActionEvent event) throws IOException{
        String a = txtUname.getText();
        String b = txtPass.getText();
        String c = txtPass2.getText();
        
        if(a.isEmpty() && b.isEmpty()){
            main.setAlert("Login Error", "Username and Password are empty");
            
        }else if(txtPass.isVisible()){          
            checkCred(a, b, event);

        }else if(txtPass2.isVisible()){
            checkCred(a, c, event);
        }
    }  
    
    @FXML
    private void ShowPass(ActionEvent event) {
        String a = txtPass.getText();
        String b = txtPass2.getText();
        
        if(cbShowPass.isSelected()){
            txtPass.setVisible(false);
            txtPass2.setVisible(true);
            txtPass2.setText(a);
            txtPass2.setEditable(true);
            txtPass2.setDisable(false);
            if(a.isEmpty()){
                txtPass2.setPromptText("Password");
            }
        }
        else if(!cbShowPass.isSelected()){
            txtPass.setVisible(true);
            txtPass2.setVisible(false);
            txtPass.setText(b);
            txtPass2.setEditable(false);
        }
    }
    
    @FXML
    private void Register(ActionEvent event){
        int id = st.getID();
        String a = txtUname.getText();
        String b = txtPass.getText();
        String c = txtPass2.getText();

        if(a.isEmpty() && b.isEmpty() && c.isEmpty()){ //validate empty fields and prompt alert 
            main.setAlert("Registration Error", "Username and Password are empty");
            
        }
        else if(b.length() < 8 && c.length() < 8){ //validate password field if less than 8 characters
            main.setAlert("Resgistration Error", "Minimum password length requires\n8 or more characters");
            
        }
        else if(checkDuplicate(a)){ //checks for existing staff username
            main.setAlert("Registration Error", "The username already exists,\nPlease use another username.");
            
        }
        else{
            st.setUsername(a);
            if(txtPass.isVisible()){
                st.setPassword(b);
                if(addCred(id, a, b)){ //create staff;
                    main.setAlert("Registration", "User Successfully Created");
                    clearfields();
                    readFile();
                }
                else if(!addCred(id, a, b)){
                    main.setAlert("Registration", "User Not Created");
                }
            }
            else if(txtPass2.isVisible()){
                st.setPassword(c);
                if(addCred(id, a, c)){ //create staff
                    main.setAlert("Registration", "User Successfully Created");
                    clearfields();
                    readFile();
                }
                else if(!addCred(id, a, b)){
                    main.setAlert("Registration", "User Not Created");
                }
            }
        }
    }

    private void createFolder(){
        //create folder if the folder exists
        if(!file.exists()){
            file.mkdir();
            System.out.println("Directory created");
        }
        else{
            System.err.println("Directory Exists");
        }
    }
    
     private void checkFile() {
        //check if the file exists in the folder
        try (FileReader filer = new FileReader(file + "\\Login.txt")){
            System.err.println("File exists!");
        } catch (FileNotFoundException e) {
            try (FileWriter fw = new FileWriter(file + "\\Login.txt");){
                System.out.println("file Created");
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private boolean addCred(int id, String uname, String pass) {
        //function to register staff
        boolean created = false;
        try (FileWriter fw = new FileWriter(file + "\\Login.txt", true)){
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
           
            pw.println(
                "ID: ST" + id + "\n" + "Username: " + uname + "\n" + "Password: " + pass + "\n"
            );
            created = true;
            
            pw.flush();
            pw.close();
            fw.close();
            bw.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return created;
    }

    
    private void checkCred(String uname, String pass, ActionEvent event) {
          //check if the username and password matches with the input txt field
          boolean found = false;
        try (Scanner x = new Scanner(new File(file + "\\Login.txt"))){ //to close scanner after done
            x.useDelimiter("\n");
            
            if(!x.hasNext()){
                main.setAlert("Records Null", "No records exist in the file");
                
            }
            
            while(x.hasNext() && !found){
                String id = x.next().substring(4);
                String username = x.next().substring(10);
                String password = x.next().substring(10);
                x.next();
                
                if(uname.equals(username) && pass.equals(password)){
                    found = true;
                    main.setAlert("Login", "Login Success");
                    main.Login(event);
                   
                }
            }
            if(!found){
                main.setAlert("Login Failed", "Incorrect Username or Password");
            }
            
        } catch (FileNotFoundException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
            
        } catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private void readFile(){ //calculate the total staff count and set the ID 
         int idCount = 1;
        
        String id = "";
        String username = "";
        String password = "";
        
        try (Scanner x = new Scanner(new File(file + "\\Login.txt"))){ 
           x.useDelimiter("\n");
           
           if(!x.hasNext()){
                idCount++;
                st.setID(idCount);
           }
           
           while(x.hasNext()){
               id = x.next().substring(4);
               username = x.next().substring(10);
               password = x.next().substring(10);
               x.next();
               
               if(id.matches(".*ST.*")){
                   idCount++;
                   st.setID(idCount);
//                   System.out.println("ID: " + id);
               }
//            System.out.println("ID: " + id + "\n" + "Username: " + username + "\n" + "Password: " + "\n" + password);
           }   
        }  catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    private boolean checkDuplicate(String a) {
        boolean exist = false;
        //check for existing staff username before register
        try (Scanner x = new Scanner(new File(file + "\\Login.txt"))){ 
           x.useDelimiter("\n");
           
           while(x.hasNext()){
               x.next();
               String username = x.next().substring(10);
               x.next();
               x.next();
               
               if(a.equals(username)){
                   exist = true;
               }
           }               

        }  catch (IOException e) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, e);
        }    
        return exist;
    }
    
    private void clearfields(){
        txtUname.clear();
        txtPass.clear();
        txtPass2.clear();
        
    }
}

