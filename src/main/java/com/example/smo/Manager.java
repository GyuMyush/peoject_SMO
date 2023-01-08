package com.example.smo;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class Manager {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDone;

    @FXML
    private Button btnInvite;

    @FXML
    private Button btnReject;
    @FXML
    private Text lbFirstName;

    @FXML
    private Text lbLastName;

    @FXML
    private Text lbQueueNumber;

    @FXML
    private Label lbServiceName;

    @FXML
    void initialize() {
        lbFirstName.setText(AutorizScaneController.FirstName);
        lbLastName.setText(AutorizScaneController.LastName);

        btnInvite.setOnAction(event ->{

        });
    }

//    обработчик на вызов клиента (первый из списка который имеет стату "вэйтинг")
//    обработчик на нажатие изменени статуса талона на "доне"(таким образом талон больше не вызывается)
}
