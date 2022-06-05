module com.example.sv_iec61850_gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.pcap4j.core;

    opens com.example.sv_iec61850_gui to javafx.fxml;
    exports com.example.sv_iec61850_gui;
}