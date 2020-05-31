/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class ClientesController implements Initializable {

    @FXML
    private TextField idCliente;
    @FXML
    private TextField RSocial;
    @FXML
    private TextField contacto;
    @FXML
    private Button btnAddCliente;
    @FXML
    private Button btnEditarCliente;
    @FXML
    private Button btnDeleteCliente;
    @FXML
    private TextField via;
    @FXML
    private TextField numero;
    @FXML
    private TextField localidad;
    @FXML
    private TextField provincia;
    @FXML
    private TableColumn<?, ?> idcliente;
    @FXML
    private TableColumn<?, ?> rSocial;
    @FXML
    private TableColumn<?, ?> ctoCliente;
    @FXML
    private TableColumn<?, ?> tbleViewVia;
    @FXML
    private TableColumn<?, ?> tbleViewNum;
    @FXML
    private TableColumn<?, ?> tbleViewLoc;
    @FXML
    private TableColumn<?, ?> tbleViewProv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addCliente(ActionEvent event) {
    }

    @FXML
    private void EditCliente(ActionEvent event) {
    }

    @FXML
    private void deleteCliente(ActionEvent event) {
    }
    
}
