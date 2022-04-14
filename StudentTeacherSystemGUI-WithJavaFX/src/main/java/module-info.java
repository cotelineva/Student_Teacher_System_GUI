module com.example.tema6_v2 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.tema6_v2 to javafx.fxml;
    exports com.example.tema6_v2;
    exports com.example.tema6_v2.GUI;
    opens com.example.tema6_v2.GUI to javafx.fxml;
}