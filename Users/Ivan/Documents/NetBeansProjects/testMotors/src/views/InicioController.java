/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import views.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class InicioController implements Initializable {

    /**
     * Initializes the controller class.
     */
	
	 @FXML
	    private Button btnAcceso;
	    
	    public static EntityManagerFactory emf;
	    
	    @FXML
	    private void lanzarSegundaVentana(ActionEvent event ) throws IOException {
	        emf= Persistence.createEntityManagerFactory("testMotorsPU");
	        //	con este mtodo cargamos el form de gestin de miembros
	    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ensayoECA.fxml"));
	    	Parent root = loader.load();
	    	EnsayoECAController controlador = loader.getController();
	    	Scene scene = new Scene(root);
	    	Stage stage = new Stage();
	    	stage.initModality(Modality.APPLICATION_MODAL);
	    	stage.setTitle("Gestion ensayos");
	    	stage.getIcons().add(new Image("/images/portada.JPG"));
	    	stage.setScene(scene);
	    	stage.showAndWait();
	    	
	        stage.setOnCloseRequest(e ->{
	        emf.close();
	        Platform.exit();
	        System.exit(0);
	        });
	    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
