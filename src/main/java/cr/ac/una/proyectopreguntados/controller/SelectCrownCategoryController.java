package cr.ac.una.proyectopreguntados.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author PC
 */
public class SelectCrownCategoryController extends Controller implements Initializable {

    @FXML
    private ImageView imgSports;
    @FXML
    private ImageView imgEntertainment;
    @FXML
    private ImageView imgScience;
    @FXML
    private ImageView imgHistory;
    @FXML
    private ImageView imgGeography;
    @FXML
    private ImageView imgArt;
    @FXML
    private VBox vbxSports;
    @FXML
    private VBox vbxEntertainment;
    @FXML
    private VBox vbxScience;
    @FXML
    private VBox vbxHistory;
    @FXML
    private VBox vbxGeography;
    @FXML
    private VBox vbxArt;
    @FXML
    private HBox root2;
    @FXML
    private HBox root1;
    private String playerSelection;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clear();
        deleteCharacter();
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onMouseExitedImgSports(MouseEvent event) {
        dropShadowBlack(imgSports);
    }

    @FXML
    private void onMouseEnteredImgSports(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#A8412B";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);

        imgSports.setEffect(dropShadow);
    }

    @FXML
    private void onMouseClickedImgSports(MouseEvent event) {
        playerSelection = "Deporte";
        getStage().close();
    }

    @FXML
    private void onMouseExitedImgEntertainment(MouseEvent event) {
        dropShadowBlack(imgEntertainment);
    }

    @FXML
    private void onMouseEnteredImgEntertainment(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#ED0E86";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);
        imgEntertainment.setEffect(dropShadow);
    }

    @FXML
    private void onMouseClickedImgEntertainment(MouseEvent event) {
        playerSelection = "Entretenimiento";
        getStage().close();
    }

    @FXML
    private void onMouseExitedImgScience(MouseEvent event) {
        dropShadowBlack(imgScience);
    }

    @FXML
    private void onMouseEnteredImgScience(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#4EC772";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);
        imgScience.setEffect(dropShadow);
    }

    @FXML
    private void onMouseClickedImgScience(MouseEvent event) {
        playerSelection = "Ciencia";
        getStage().close();
    }

    @FXML
    private void onMouseExitedImgHistory(MouseEvent event) {
        dropShadowBlack(imgHistory);
    }

    @FXML
    private void onMouseEnteredImgHistory(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#BF6A17";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);
        imgHistory.setEffect(dropShadow);

    }

    @FXML
    private void onMouseClickedImgHistory(MouseEvent event) {
        playerSelection = "Historia";
        getStage().close();
    }

    @FXML
    private void onMouseExitedImgGeography(MouseEvent event) {
        dropShadowBlack(imgGeography);
    }

    @FXML
    private void onMouseEnteredImgGeography(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#6ACEDA";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);
        imgGeography.setEffect(dropShadow);

    }

    @FXML
    private void onMouseClickedImgGeography(MouseEvent event) {
        playerSelection = "Geografía";
        getStage().close();
    }

    @FXML
    private void onMouseExitedImgArt(MouseEvent event) {
        dropShadowBlack(imgArt);
    }

    @FXML
    private void onMouseEnteredImgArt(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#EAAC34";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);
        imgArt.setEffect(dropShadow);
    }

    @FXML
    private void onMouseClickedImgArt(MouseEvent event) {
        playerSelection = "Arte";
        getStage().close();
    }

    private void dropShadowBlack(ImageView image) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        dropShadow.setColor(Color.BLACK);
        image.setEffect(dropShadow);
    }

    private void clear() {
        dropShadowBlack(imgSports);
        dropShadowBlack(imgEntertainment);
        dropShadowBlack(imgScience);
        dropShadowBlack(imgHistory);
        dropShadowBlack(imgGeography);
        dropShadowBlack(imgArt);
    }

    private void deleteCharacter() {
        String art = "F";
        String geography = "F";
        String enterteinment = "F";
        String sport = "F";
        String history = "F";
        String science = "F";
        if (science.equals("T")) {
            root2.getChildren().remove(vbxScience);
        }
        if (enterteinment.equals("T")) {
            root1.getChildren().remove(vbxEntertainment);
        }
        if (sport.equals("T")) {
            root1.getChildren().remove(vbxSports);
        }

        if (history.equals("T")) {
            root2.getChildren().remove(vbxHistory);
        }
        if (geography.equals("T")) {
            root2.getChildren().remove(vbxGeography);
        }
        if (art.equals("T")) {
            root2.getChildren().remove(vbxArt);
        }
    }

    public String getPlayerSelection() {
        return playerSelection;
    }

    public void setPlayerSelection(String playerSelection) {
        this.playerSelection = playerSelection;
    }

}
