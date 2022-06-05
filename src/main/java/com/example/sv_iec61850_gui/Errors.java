package com.example.sv_iec61850_gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class Errors {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text textError;

    @FXML
    void initialize() {

    }
    public void errorWindow(String error){
        Stage window = new Stage();
        Pane pane = new Pane();


        Scene scene = new Scene(pane, 500, 100);
        window.setScene(scene);
        window.setTitle("Error");
        window.showAndWait();

        textError.setText(error);
    }

}