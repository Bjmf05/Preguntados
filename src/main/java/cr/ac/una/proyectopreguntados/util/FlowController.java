/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.una.proyectopreguntados.util;

import cr.ac.una.proyectopreguntados.App;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.logging.Level;

import cr.ac.una.proyectopreguntados.controller.LogInController;
import cr.ac.una.proyectopreguntados.controller.PrincipalController;
import cr.ac.una.proyectopreguntados.controller.SixPlayerBoardController;
import cr.ac.una.proyectopreguntados.model.Partida;
import cr.ac.una.proyectopreguntados.model.PartidaDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import cr.ac.una.proyectopreguntados.controller.Controller;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.image.Image;

public class FlowController {

    private static FlowController INSTANCE = null;
    private static Stage mainStage;
    private static ResourceBundle idioma;
    private static HashMap<String, FXMLLoader> loaders = new HashMap<>();

    private FlowController() {
    }

    private static void createInstance() {
        if (INSTANCE == null) {
            synchronized (FlowController.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FlowController();
                }
            }
        }
    }

    public static FlowController getInstance() {
        if (INSTANCE == null) {
            createInstance();
        }
        return INSTANCE;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }

    public void InitializeFlow(Stage stage, ResourceBundle idioma) {
        getInstance();
        this.mainStage = stage;
        this.idioma = idioma;
    }

    private FXMLLoader getLoader(String name) {
        FXMLLoader loader = loaders.get(name);
        if (loader == null) {
            synchronized (FlowController.class) {
                if (loader == null) {
                    try {
                        loader = new FXMLLoader(App.class.getResource("view/" + name + ".fxml"), this.idioma);
                        loader.load();
                        loaders.put(name, loader);
                    } catch (Exception ex) {
                        loader = null;
                        java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Creando loader [" + name + "].", ex);
                    }
                }
            }
        }
        return loader;
    }

    public void goMain() {
        try {
            Parent root = FXMLLoader.load(App.class.getResource("view/PrincipalView.fxml"), this.idioma);
            Scene scene = new Scene(root);
            MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
            mainStage.setScene(scene);
            mainStage.show();
            boardView();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error inicializando la vista base.", ex);
        }
    }

    private void boardView() {
        PartidaDto partida = (PartidaDto) AppContext.getInstance().get("Partida");
        int numPlayers = partida.getJugadores().intValue();
        switch (numPlayers) {
            case 2:
                FlowController.getInstance().goView("TwoPlayerBoardView");
                break;
            case 3:
                FlowController.getInstance().goView("ThreePlayerBoardView");
                break;
            case 4:
                FlowController.getInstance().goView("FourPlayerBoardView");
                break;
            case 5:
                FlowController.getInstance().goView("FivePlayerBoardView");
                break;
            case 6:
                FlowController.getInstance().goView("SixPlayerBoardView");
                break;
        }

    }

    public Object getControllerBoard() {

        PartidaDto partida = (PartidaDto) AppContext.getInstance().get("Partida");
        int numPlayers = partida.getJugadores().intValue();
        SixPlayerBoardController controller;
        switch (numPlayers) {
            case 2:
                return controller = (SixPlayerBoardController) FlowController.getInstance().getController("TwoPlayerBoardView");
            case 3:
                return controller = (SixPlayerBoardController) FlowController.getInstance().getController("ThreePlayerBoardView");
            case 4:
                return controller = (SixPlayerBoardController) FlowController.getInstance().getController("FourPlayerBoardView");
            case 5:
                return controller = (SixPlayerBoardController) FlowController.getInstance().getController("FivePlayerBoardView");
            case 6:
                return controller = (SixPlayerBoardController) FlowController.getInstance().getController("SixPlayerBoardView");
            default:
                return null;
        }

    }
    public void deleteAll() {
        loaders.clear();
    }

    public void goView(String viewName) {
        goView(viewName, "Center", null);
    }

    public void goView(String viewName, String accion) {
        goView(viewName, "Center", accion);
    }

    public void goView(String viewName, String location, String accion) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setAccion(accion);
        controller.initialize();
        Stage stage = controller.getStage();
        if (stage == null) {
            stage = this.mainStage;
            controller.setStage(stage);
        }
        switch (location) {
            case "Center":
                VBox vBox = ((VBox) ((BorderPane) stage.getScene().getRoot()).getCenter());
                vBox.getChildren().clear();
                vBox.getChildren().add(loader.getRoot());
                break;
            case "Top":
                break;
            case "Bottom":
                break;
            case "Right":
                break;
            case "Left":
                VBox vBoxLeft = ((VBox) ((BorderPane) stage.getScene().getRoot()).getLeft());
                vBoxLeft.getChildren().clear();
                vBoxLeft.getChildren().add(loader.getRoot());
                break;
            default:
                break;
        }
    }

    public void goViewInStage(String viewName, Stage stage) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.setStage(stage);
        stage.getScene().setRoot(loader.getRoot());
        MFXThemeManager.addOn(stage.getScene(), Themes.DEFAULT, Themes.LEGACY);

    }

    public void goViewInWindow(String viewName) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        InputStream inputStream = App.class.getResourceAsStream("/cr/ac/una/proyectopreguntados/resources/Corona.png");
        stage.getIcons().add(new Image(inputStream));
        stage.setTitle("Preguntados");
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void goViewInWindowModal(String viewName, Stage parentStage, Boolean resizable) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.setTitle(controller.getNombreVista());
        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initOwner(parentStage);
        stage.centerOnScreen();
        stage.showAndWait();

    }

    public void showViewInVBox(String viewName, VBox vbox) {
        try {
            FXMLLoader loader = getLoader(viewName);
            Parent view = loader.getRoot();
            vbox.getChildren().clear();
            vbox.getChildren().add(view);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(FlowController.class.getName()).log(Level.SEVERE, "Error showing view in VBox.", ex);
        }
    }

    public Controller getController(String viewName) {
        return getLoader(viewName).getController();
    }

    public void limpiarLoader(String view) {
        this.loaders.remove(view);
    }

    public static void setIdioma(ResourceBundle idioma) {
        FlowController.idioma = idioma;
    }

    public void initialize() {
        this.loaders.clear();
    }

    public void salir() {
        this.mainStage.close();
    }

    public void delete(String parametro) {
        loaders.remove(parametro);
    }

    public void goViewInWindowModalOfCard(String viewName, Stage parentStage, Boolean resizable, double width, double height) {
        FXMLLoader loader = getLoader(viewName);
        Controller controller = loader.getController();
        controller.initialize();
        Stage stage = new Stage();
        stage.setTitle(controller.getNombreVista());
        stage.setResizable(resizable);
        stage.setOnHidden((WindowEvent event) -> {
            controller.getStage().getScene().setRoot(new Pane());
            controller.setStage(null);
        });
        controller.setStage(stage);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        MFXThemeManager.addOn(scene, Themes.DEFAULT, Themes.LEGACY);
        stage.setScene(scene);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initOwner(parentStage);
        double startPositionX = 200;
        double startPositionY = 400;
        stage.setX(startPositionX);
        stage.setY(startPositionY);
        Platform.runLater(() -> {
            double endPositionX = (Screen.getPrimary().getVisualBounds().getWidth()) / 2;
            double endPositionY = (Screen.getPrimary().getVisualBounds().getHeight()) / 2;
            // Obtener las propiedades x e y del Stage como DoubleProperty
            DoubleProperty xProperty = new SimpleDoubleProperty(startPositionX);
            DoubleProperty yProperty = new SimpleDoubleProperty(startPositionY);

            // Crear KeyValue para animar las propiedades x e y
            KeyValue keyValueX = new KeyValue(xProperty, endPositionX);
            KeyValue keyValueY = new KeyValue(yProperty, endPositionY);

            Timeline timeline = new Timeline();
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), keyValueX, keyValueY);
            timeline.getKeyFrames().add(keyFrame);

            // Agregar un listener para actualizar la posición del Stage durante la animación
            timeline.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
                stage.setX(xProperty.get());
                stage.setY(yProperty.get());
            });
            timeline.play();
        });
        stage.showAndWait();
    }
}
