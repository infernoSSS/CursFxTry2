package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import parser.Parser;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private Button getLogsButton;

    @FXML
    void initialize() {
            getLogsButton.setOnAction(event -> {
                scrollPane.setContent(new Label(Parser.getParser().getData()));
            });
    }
}

