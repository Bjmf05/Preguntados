module ProyectoPreguntados {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires javafx.graphics;
    requires MaterialFX;
    requires java.base;
    requires jakarta.persistence;
    requires java.instrument;
    
    
    opens cr.ac.una.proyectopreguntados to javafx.fxml;
    opens cr.ac.una.proyectopreguntados.controller to javafx.fxml;
    opens cr.ac.una.proyectopreguntados.util to javafx.fxml;
    opens cr.ac.una.proyectopreguntados.model;
    
    exports cr.ac.una.proyectopreguntados;
    exports cr.ac.una.proyectopreguntados.controller;
    exports cr.ac.una.proyectopreguntados.util;
    exports cr.ac.una.proyectopreguntados.model;
}
