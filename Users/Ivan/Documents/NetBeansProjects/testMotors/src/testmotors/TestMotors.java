/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testmotors;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



/**
 *
 * @author Usuario
 */
public class TestMotors extends Application {
    
    //private final Image imageOng = new Image("/images/ong.jpg");
    private final AnchorPane container = new AnchorPane();
	
    public static EntityManagerFactory emf;
    
    @Override
    public void start(Stage primaryStage) throws IOException {  
        emf = Persistence.createEntityManagerFactory("testMotorsPU");
    	Parent root = FXMLLoader.load(getClass().getResource("/views/inicio.fxml"));
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Bienvenido TestMotors");
        primaryStage.getIcons().add(new Image("/images/portada.JPG"));
        ImageView imageView = new ImageView();
        imageView.setFitHeight(240);
        imageView.setFitWidth(320);
        container.getChildren().add(imageView);
        primaryStage.setResizable(false);
        primaryStage.show();
        
        primaryStage.setOnCloseRequest(e ->{
        	emf.close();
        	Platform.exit();
        	System.exit(0);
        });              
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}