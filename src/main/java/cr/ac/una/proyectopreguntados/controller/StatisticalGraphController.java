package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.CompetidorDto;
import cr.ac.una.proyectopreguntados.model.CompetidorPK;
import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.model.Respuesta;
import cr.ac.una.proyectopreguntados.service.JugadorService;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import java.net.URL;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marconi Calvo Campos
 */
public class StatisticalGraphController extends Controller implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private MFXComboBox<String> cbxNombre;
    @FXML
    private StackPane stpBarChart;
    @FXML
    private BarChart<Long, String> barChartGraph;
    @FXML
    private CategoryAxis categoryYAxis;
    @FXML
    private NumberAxis numberXAxis;
    @FXML
    private MFXButton btnExit;
    private JugadorDto jugadorDto;
    private ObservableList<JugadorDto> players = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void onActionCbxNombre(ActionEvent event) {
        loadPlayer();
        //cbxNombre.getText();
        //dataOfGrafic(jugadorDto);
    }

    private void dataOfGrafic(JugadorDto jugador) {
        categoryYAxis = (CategoryAxis) barChartGraph.getYAxis();
        // Crear la serie de datos para "Respondidas"
        XYChart.Series<Long, String> serieOfData = new XYChart.Series<>();
        serieOfData.setName("Preguntas Respondidas");
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadPreguntas(), "Total de Preguntas"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAciertos(), "Acertadas"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAHistoria(), "Historia"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAArte(), "Arte"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAGeografia(), "Geografia"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadACiencia(), "Ciencias"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAEntretenimiento(), "Entretenimiento"));

        barChartGraph.getData().addAll(serieOfData);
        for (XYChart.Series<Long, String> series : barChartGraph.getData()) {
            for (XYChart.Data<Long, String> data : series.getData()) {
                data.nodeProperty().addListener((observable, oldNode, newNode) -> {
                    if (newNode != null) {
                        setValueBarChart(data);
                    }
                });
                if (data.getNode() != null) {
                    setValueBarChart(data);
                }
            }
        }
    }

    private void setValueBarChart(XYChart.Data<Long, String> data) {
        stpBarChart = (StackPane) data.getNode();
        Label label = new Label(data.getXValue().toString());
        stpBarChart.setMinHeight(40); // Ajusta el tamaño mínimo de las barras
        stpBarChart.getChildren().add(label);
        StackPane.setAlignment(label, javafx.geometry.Pos.CENTER_RIGHT);
    }

    private void loadPlayer() {
        JugadorService jugadorService = new JugadorService();
        RespuestaEnt respuesta = jugadorService.getAllPlayers();
        players.addAll((List<JugadorDto>) respuesta.getResultado("Jugadores"));
        ObservableList<String> playerNames = players.stream().map(JugadorDto::getNombre)
                .collect(Collectors.toCollection(FXCollections::observableArrayList));
        cbxNombre.setItems(playerNames);
    }

    @Override
    public void initialize() {
    }

    @FXML
    private void onActionExit(ActionEvent event) {
        ((Stage) root.getScene().getWindow()).close();
    }

}