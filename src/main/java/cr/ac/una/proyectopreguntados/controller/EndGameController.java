/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.proyectopreguntados.controller;

import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class EndGameController extends Controller implements Initializable {

    @FXML
    private Label lblFinishBy;
    @FXML
    private Label lblWinner;
    @FXML
    private MFXButton btnAccept;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void onActionBtnAccept(ActionEvent event) {
        getStage().close();
    }

    @Override
    public void initialize() {
   }

    public Label getLblFinishBy() {
        return lblFinishBy;
    }

    public void setLblFinishBy(Label lblFinishBy) {
        this.lblFinishBy = lblFinishBy;
    }

    public Label getLblWinner() {
        return lblWinner;
    }

    public void setLblWinner(Label lblWinner) {
        this.lblWinner = lblWinner;
    }
}
