/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaassignment;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;
import javafx.util.Duration;
/**
 *
 * @author user
 */
public class FXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("BookingInfo.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setTitle("Hotel Reservation System");
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    @FXML
    public void showTime(Label a) {
        Timeline now = new Timeline(new KeyFrame(Duration.ZERO, e-> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            a.setText(LocalDateTime.now().format(dtf));
        }), new KeyFrame(Duration.seconds(1)));
            now.setCycleCount(Animation.INDEFINITE);
            now.play();
    }
    
    @FXML
    protected void Login(ActionEvent event) throws IOException{
        Parent HomeView = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene HomeViewScene = new Scene(HomeView);
        
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(HomeViewScene);
        window.setResizable(false);
        window.show();
    }
    
    @FXML
    protected void Logout(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.WARNING, null , ButtonType.YES, ButtonType.NO);
        alert.setTitle("Exit");
        alert.setHeaderText("Are you sure you want to Logout?");
        
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.YES) {
            Parent LoginView = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene LoginViewScene = new Scene(LoginView);

            Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
            window.setScene(LoginViewScene); 
            window.setResizable(false);
            window.show();
        }
    } 
    
    @FXML
    protected void Booking(ActionEvent event) throws IOException{
        Parent BookingView = FXMLLoader.load(getClass().getResource("BookingInfo.fxml"));
        Scene BookingViewScene = new Scene(BookingView);
        
        Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
        window.setScene(BookingViewScene);
        window.setResizable(false);
        window.show();
    }

    @FXML
    protected void AddBooking(ActionEvent event) throws IOException{
        Parent AddBookingView = FXMLLoader.load(getClass().getResource("AddBooking.fxml"));
        Scene AddBookingViewScene = new Scene(AddBookingView);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AddBookingViewScene);
        window.setResizable(false);
        window.show();
    }
}
