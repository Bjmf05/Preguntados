package cr.ac.una.proyectopreguntados.controller;

import java.net.URL;
import java.util.ResourceBundle;

import cr.ac.una.proyectopreguntados.model.CompetidorDto;
import cr.ac.una.proyectopreguntados.util.AppContext;
import cr.ac.una.proyectopreguntados.util.FlowController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
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
    @FXML
    private ImageView imgDuel;
    private ObservableList<CompetidorDto> competitorsDuel= FXCollections.observableArrayList();
    private boolean competitorsIsFilled = false;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        clear();
        competitorsDuel= FXCollections.observableArrayList();
        competitorsIsFilled = false;
        disableDuel();
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
        dropShadowBlack(imgDuel);
    }

    private void deleteCharacter() {
        SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController) FlowController.getInstance().getControllerBoard();
        CompetidorDto competidorDto = sixPlayerBoardController.getCurrentCompetitor();
        String art = competidorDto.getArte();
        String geography = competidorDto.getGeografia();
        String enterteinment = competidorDto.getEntretenimiento();
        String sport = competidorDto.getDeporte();
        String history = competidorDto.getHistoria();
        String science = competidorDto.getCiencias();
        if (science.equals("A")) {
            root2.getChildren().remove(vbxScience);
        }
        if (enterteinment.equals("A")) {
            root1.getChildren().remove(vbxEntertainment);
        }
        if (sport.equals("A")) {
            root1.getChildren().remove(vbxSports);
        }

        if (history.equals("A")) {
            root2.getChildren().remove(vbxHistory);
        }
        if (geography.equals("A")) {
            root2.getChildren().remove(vbxGeography);
        }
        if (art.equals("A")) {
            root2.getChildren().remove(vbxArt);
        }
    }

    public String getPlayerSelection() {
        return playerSelection;
    }

    public void setPlayerSelection(String playerSelection) {
        this.playerSelection = playerSelection;
    }

    @FXML
    private void onMouseExitedImgDuel(MouseEvent event) {
        dropShadowBlack(imgDuel);
    }

    @FXML
    private void onMouseEnteredImgDuel(MouseEvent event) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(5.0);
        String hexColor = "#A53C1B";
        Color color = Color.web(hexColor);
        dropShadow.setColor(color);
        imgArt.setEffect(dropShadow);
    }

    @FXML
  private void onMouseClickedImgDuel(MouseEvent event) {
        playerSelection = "Duelo";
      AppContext.getInstance().set("competitorsDuel", competitorsDuel);
      FlowController.getInstance().goViewInWindowModal("SelectDuelView", getStage(), true);
      FlowController.getInstance().delete("SelectDuelView");
      getStage().close();
}

private void disableDuel(){
    SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController) FlowController.getInstance().getControllerBoard();
    imgDuel.setDisable(true);
    CompetidorDto competidorDto = sixPlayerBoardController.getCurrentCompetitor();
    if(hasCrown(competidorDto)){
        ObservableList<CompetidorDto> competidores = getCompetitors();
        int playersWithCrown = countPlayersWithCrown(competidores);
        if(playersWithCrown > 1){
            imgDuel.setDisable(false);
        }
    }

}
private ObservableList<CompetidorDto> getCompetitors() {
    SixPlayerBoardController sixPlayerBoardController = (SixPlayerBoardController) FlowController.getInstance().getControllerBoard();
    sixPlayerBoardController.getPlayers();
    return (ObservableList<CompetidorDto>) AppContext.getInstance().get("competitorsPlayer");
}

private int countPlayersWithCrown(ObservableList<CompetidorDto> competidores) {

    int playersWithCrown = 0;
    for (CompetidorDto competidor : competidores) {
        if (hasCrown(competidor)) {
            playersWithCrown++;
            if (!competitorsIsFilled){
            competitorsDuel.add(competidor);}
        }
    }
    competitorsIsFilled = true;
    return playersWithCrown;
}

private boolean hasCrown(CompetidorDto competidor) {
    return competidor.getArte().equals("A") || competidor.getGeografia().equals("A") || competidor.getEntretenimiento().equals("A") || competidor.getDeporte().equals("A") || competidor.getHistoria().equals("A") || competidor.getCiencias().equals("A");
}

}
