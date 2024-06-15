package cr.ac.una.proyectopreguntados.controller;

import cr.ac.una.proyectopreguntados.model.JugadorDto;
import cr.ac.una.proyectopreguntados.service.JugadorService;
import cr.ac.una.proyectopreguntados.util.RespuestaEnt;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
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
    private BarChart<Number, String> barChartGraph;
    @FXML
    private CategoryAxis categoryYAxis;
    @FXML
    private NumberAxis numberXAxis;
    private JugadorDto jugadorDto;
    private ObservableList<JugadorDto> players = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadPlayer();
        categoryYAxis.setCategories(FXCollections.observableArrayList(
                "Total de Preguntas", "Acertadas", "Historia", "Arte", "Geografia", "Ciencias", "Entretenimiento", "Deporte"
        ));
        categoryYAxis.setStyle("--fx-background-color: black; -fx-font-size: 10px;");
    }

    @FXML
    private void onActionCbxNombre(ActionEvent event) {
        players.stream().filter(player -> player.getNombre().equals(cbxNombre.getValue())).findFirst().ifPresent(this::dataOfGrafic);
    }

    private void dataOfGrafic(JugadorDto jugador) {
        barChartGraph.getData().clear();
        //categoryYAxis = (CategoryAxis) barChartGraph.getYAxis();
        // Crear la serie de datos
        XYChart.Series<Number, String> serieOfData = new XYChart.Series<>();
        serieOfData.getData().clear();
        serieOfData.setName("Preguntas Respondidas");
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadPreguntas(), "Total de Preguntas"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAciertos(), "Acertadas"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAHistoria(), "Historia"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAArte(), "Arte"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAGeografia(), "Geografia"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadACiencia(), "Ciencias"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadAEntretenimiento(), "Entretenimiento"));
        serieOfData.getData().add(new XYChart.Data<>(jugador.getCantidadADeporte(), "Deporte"));
        
        barChartGraph.getData().addAll(serieOfData);
        for (XYChart.Series<Number, String> series : barChartGraph.getData()) {
            for (XYChart.Data<Number, String> data : series.getData()) {
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

    private void setValueBarChart(XYChart.Data<Number, String> data) {
        stpBarChart = (StackPane) data.getNode();
        Label label = new Label(String.format("%d", data.getXValue().intValue()));//data.getXValue().toString());
        label.setStyle("-fx-text-fill: black; -fx-font-size: 10px; -fx-font-weight: bold;");
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

}
