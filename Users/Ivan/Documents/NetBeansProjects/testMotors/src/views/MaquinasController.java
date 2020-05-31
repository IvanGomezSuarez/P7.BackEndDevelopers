/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class MaquinasController implements Initializable {

    @FXML
    private TextField txt_marca;
    @FXML
    private TextField txt_numserie;
    @FXML
    private TextField txt_rpm;
    @FXML
    private TextField txt_coseno;
    @FXML
    private TextField txt_Uestator;
    @FXML
    private TextField txt_Urotor;
    @FXML
    private TextField txt_potencia;
    @FXML
    private TextField txt_rodLA;
    @FXML
    private TextField txt_grasa;
    @FXML
    private TextField txt_tipo;
    @FXML
    private TextField txt_frec;
    @FXML
    private TextField txt_forma;
    @FXML
    private TextField txt_ip;
    @FXML
    private TextField txt_Iestator;
    @FXML
    private TextField txt_Irotor;
    @FXML
    private TextField txt_accionamiento;
    @FXML
    private TextField txt_rodLOA;
    @FXML
    private TextField txt_anoFabr;
    @FXML
    private TextArea txt_Area;
    @FXML
    private MenuButton choice_maquina;
    @FXML
    private ListView<?> listaMaquinas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
