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

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.MenuBar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class EnsayoECAController implements Initializable {

	public static EntityManagerFactory emf;

    @FXML
    private Button btn_save_test;
    @FXML
    private Button btn_mca;
    @FXML
    private TextField txt_ens_tmenor_15s;
    @FXML
    private TextField txt_ens_tmenor_30s;
    @FXML
    private TextField txt_ens_tmenor_45s;
    @FXML
    private TextField txt_ens_tmenor_1;
    @FXML
    private TextField txt_ens_tmenor_2;
    @FXML
    private TextField txt_ens_tmenor_3;
    @FXML
    private TextField txt_ens_tmenor_5;
    @FXML
    private TextField txt_ens_tmenor_7;
    @FXML
    private TextField txt_ens_tmenor_9;
    @FXML
    private TextField txt_ens_tmenor_10;
    @FXML
    private TextField txt_ens_tmenor_15;
    @FXML
    private TextField txt_ens_tmenor_res_15s;
    @FXML
    private TextField txt_ens_tmenor_res_30s;
    @FXML
    private TextField txt_ens_tmenor_res_45s;
    @FXML
    private TextField txt_ens_tmenor_res_1;
    @FXML
    private TextField txt_ens_tmenor_res_2;
    @FXML
    private TextField txt_ens_tmenor_res_3;
    @FXML
    private TextField txt_ens_tmenor_res_5;
    @FXML
    private TextField txt_ens_tmenor_res_7;
    @FXML
    private TextField txt_ens_tmenor_res_9;
    @FXML
    private TextField txt_ens_tmenor_res_10;
    @FXML
    private TextField txt_ens_tmenor_res_15;
    @FXML
    private TextField txt_ens_tmayor_15s;
    @FXML
    private TextField txt_ens_tmayor_res_15s;
    @FXML
    private TextField txt_ens_tmayor_30s;
    @FXML
    private TextField txt_ens_tmayor_res_30s;
    @FXML
    private TextField txt_ens_tmayor_res_45s;
    @FXML
    private TextField txt_ens_tmayor_45s;
    @FXML
    private TextField txt_ens_tmayor_1;
    @FXML
    private TextField txt_ens_tmayor_2;
    @FXML
    private TextField txt_ens_tmayor_3;
    @FXML
    private TextField txt_ens_tmayor_5;
    @FXML
    private TextField txt_ens_tmayor_7;
    @FXML
    private TextField txt_ens_tmayor_9;
    @FXML
    private TextField txt_ens_tmayor_res_9;
    @FXML
    private TextField txt_ens_tmayor_res_7;
    @FXML
    private TextField txt_ens_tmayor_res_5;
    @FXML
    private TextField txt_ens_tmayor_res_3;
    @FXML
    private TextField txt_ens_tmayor_res_2;
    @FXML
    private TextField txt_ens_tmayor_res_1;
    @FXML
    private TextField txt_ens_tmayor_10;
    @FXML
    private TextField txt_ens_tmayor_15;
    @FXML
    private TextField txt_ens_tmayor_res_10;
    @FXML
    private TextField txt_ens_tmayor_res_15;
    @FXML
    private TextField text_id_ensayo;
    @FXML
    private ChoiceBox<?> choice_tecnico;
    @FXML
    private ChoiceBox<?> choice_maquina;
    @FXML
    private TextField text_fecha;
    @FXML
    private ChoiceBox<?> choice_cliente;
    @FXML
    private TextField txt_cap_megger_tmenor;
    @FXML
    private TextField txt_cap_megger_tmayor;
    @FXML
    private TextField text_tension_est;
    @FXML
    private TextField txt_tension_rotor;
    @FXML
    private TextField txt_corr_estator;
    @FXML
    private TextField txt_corr_rotor;
    @FXML
    private TextField txt_potencia;
    @FXML
    private Button btn_SV;
    @FXML
    private Button btn_results_show;
    @FXML
    private TextField txt_resohm_uv;
    @FXML
    private TextField txt_resohm_vw;
    @FXML
    private TextField txt_resohm_uw;
    @FXML
    private Button btn_corregir_40;
    @FXML
    private TextField txt_resohm_corr_uv;
    @FXML
    private TextField txt_resohm_corr_vw;
    @FXML
    private TextField txt_resohm_corr_uw;
    @FXML
    private TextField txt_dif_resohm;
    @FXML
    private TextField txt_cap_120;
    @FXML
    private TextField txt_cap_1kh;
    @FXML
    private TextField txt_dd_tmenor;
    @FXML
    private TextField txt_dar_menor;
    @FXML
    private TextField txt_ct_menor;
    @FXML
    private TextField txt_dd_tmayor;
    @FXML
    private TextField txt_ip_mayor;
    @FXML
    private TextField txt_dar_mayor;
    @FXML
    private TextField txt_ct_mayor;
    @FXML
    private TextField txt_sonda;
    @FXML
    private TextField txt_temp_devanado;
    @FXML
    private MenuBar menu;
    @FXML
    private Menu menu_cliente;
    @FXML
    private Menu menu_maquina;
    @FXML
    private Menu menu_tecnico;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    void open_cliente(ActionEvent event) throws IOException {
     	 emf= Persistence.createEntityManagerFactory("testMotorsPU");
         //	con este mtodo cargamos el form de gestin de miembros
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/clientes.fxml"));
     	Parent root = loader.load();
     	//MiembrosController controlador = loader.getController();
     	Scene scene = new Scene(root);
     	Stage stage = new Stage();
     	stage.initModality(Modality.APPLICATION_MODAL);
     	stage.setTitle("Gestión de clientes");
     	stage.getIcons().add(new Image("/images/portada.JPG"));
     	stage.setScene(scene);
     	stage.showAndWait();
         
         stage.setOnCloseRequest(e ->{
         emf.close();
         Platform.exit();
         System.exit(0);
         });
    }

    @FXML
    void open_maquina(ActionEvent event) throws IOException {
     	 emf= Persistence.createEntityManagerFactory("testMotorsPU");
         //	con este mtodo cargamos el form de gestin de miembros
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/maquinas.fxml"));
     	Parent root = loader.load();
     	//MiembrosController controlador = loader.getController();
     	Scene scene = new Scene(root);
     	Stage stage = new Stage();
     	stage.initModality(Modality.APPLICATION_MODAL);
     	stage.setTitle("Gestión de máquinas");
     	stage.getIcons().add(new Image("/images/portada.JPG"));
     	stage.setScene(scene);
     	stage.showAndWait();
         
         stage.setOnCloseRequest(e ->{
         emf.close();
         Platform.exit();
         System.exit(0);
         });
    }

    @FXML
    void open_tecnico(ActionEvent event) throws IOException {
      	 emf= Persistence.createEntityManagerFactory("testMotorsPU");
         //	con este mtodo cargamos el form de gestin de miembros
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/tecnicos.fxml"));
     	Parent root = loader.load();
     	//MiembrosController controlador = loader.getController();
     	Scene scene = new Scene(root);
     	Stage stage = new Stage();
     	stage.initModality(Modality.APPLICATION_MODAL);
     	stage.setTitle("Gestión de los técnicos");
     	stage.getIcons().add(new Image("/images/portada.JPG"));
     	stage.setScene(scene);
     	stage.showAndWait();
         
         stage.setOnCloseRequest(e ->{
         emf.close();
         Platform.exit();
         System.exit(0);
         });
    }
    @FXML
    private void save_test(ActionEvent event) {
    }

    @FXML
    private void acc_step_voltage(ActionEvent event) throws IOException {
      	 emf= Persistence.createEntityManagerFactory("testMotorsPU");
         //	con este mtodo cargamos el form de gestin de miembros
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/StepVoltage.fxml"));
     	Parent root = loader.load();
     	//MiembrosController controlador = loader.getController();
     	Scene scene = new Scene(root);
     	Stage stage = new Stage();
     	stage.initModality(Modality.APPLICATION_MODAL);
     	stage.setTitle("Ensayo de CC Step Voltage");
     	stage.getIcons().add(new Image("/images/portada.JPG"));
     	stage.setScene(scene);
     	stage.showAndWait();
         
         stage.setOnCloseRequest(e ->{
         emf.close();
         Platform.exit();
         System.exit(0);
         });
    	
    }

    @FXML
    void goTo_MCA(ActionEvent event) throws IOException {
     	 emf= Persistence.createEntityManagerFactory("testMotorsPU");
         //	con este mtodo cargamos el form de gestin de miembros
     	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/ensayoMCA.fxml"));
     	Parent root = loader.load();
     	//MiembrosController controlador = loader.getController();
     	Scene scene = new Scene(root);
     	Stage stage = new Stage();
     	stage.initModality(Modality.APPLICATION_MODAL);
     	stage.setTitle("Ensayo MCA");
     	stage.getIcons().add(new Image("/images/portada.JPG"));
     	stage.setScene(scene);
     	stage.showAndWait();
         
         stage.setOnCloseRequest(e ->{
         emf.close();
         Platform.exit();
         System.exit(0);
         });
    }
    @FXML
    private void results_show(ActionEvent event) throws IOException {
   	 emf= Persistence.createEntityManagerFactory("testMotorsPU");
     //	con este mtodo cargamos el form de gestin de miembros
 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/resultados.fxml"));
 	Parent root = loader.load();
 	//MiembrosController controlador = loader.getController();
 	Scene scene = new Scene(root);
 	Stage stage = new Stage();
 	stage.initModality(Modality.APPLICATION_MODAL);
 	stage.setTitle("Resultados del ensayo");
 	stage.getIcons().add(new Image("/images/portada.JPG"));
 	stage.setScene(scene);
 	stage.showAndWait();
     
     stage.setOnCloseRequest(e ->{
     emf.close();
     Platform.exit();
     System.exit(0);
     });
    	
    	
    }

    @FXML
    private void corregir_res_ohm(ActionEvent event) {
    }

    @FXML
    private void txt_ip_menor(ActionEvent event) {
    }
    
}
