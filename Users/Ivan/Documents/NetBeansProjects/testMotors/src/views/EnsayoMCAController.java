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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Ivan
 */
public class EnsayoMCAController implements Initializable {

    @FXML
    private TextField txt_imp_uv;
    @FXML
    private TextField txt_imp_vw;
    @FXML
    private TextField txt_imp_uw;
    @FXML
    private TextField txt_ind_uv;
    @FXML
    private TextField txt_ind_vw;
    @FXML
    private TextField txt_ind_uw;
    @FXML
    private TextField txt_if_uv;
    @FXML
    private TextField txt_if_vw;
    @FXML
    private TextField txt_if_uw;
    @FXML
    private TextField txt_afase_uw;
    @FXML
    private TextField txt_afase_vw;
    @FXML
    private TextField txt_afase_uv;
    @FXML
    private Button btn_save_mca;
    @FXML
    private Button btn_edit_mca;
    @FXML
    private Button btn_exit;
    @FXML
    private Button btn_new_mca;
    @FXML
    private ListView<?> list_mca;
    @FXML
    private ChoiceBox<?> ch_real_por;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void save_mca(ActionEvent event) {
    }

    @FXML
    private void edit_mca(ActionEvent event) {
    }

    @FXML
    private void exit(ActionEvent event) {
    }

    @FXML
    private void nuevo_mca(ActionEvent event) {
    }
    
}
