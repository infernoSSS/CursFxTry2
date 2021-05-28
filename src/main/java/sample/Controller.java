package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    private TextField comPortName;

    @FXML
    private Button getLogsButton;

    @FXML
    void initialize() {
            getLogsButton.setOnAction(event -> {
                //System.out.println(comPortName.getText());
                boolean portValid = Parser.getParser().setCOMPort(comPortName.getText());
                if(portValid) {
                    try {
                        scrollPane.setContent(new Label(Parser.getParser().getData()));
                        Thread.sleep(1000);
                    } catch (Exception e) {}
                }else {
                    scrollPane.setContent(new Label("invalid port name"));
                }
            });

        //System.out.println("portvalid");
    }
}