package com.example.smo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.animation.PauseTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class ChoosingServiceController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> lstService;

    @FXML
    private Text title;
    public Integer selectItem;

    @FXML
    void initialize() throws SQLException {
        _DatabaseHandler dbHandler = new _DatabaseHandler();
        ResultSet result = dbHandler.getService();

        while (result.next()) {
            lstService.getItems().add(result.getString(_Const.SERVICE_TITLE));
        }

        lstService.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                selectItem = lstService.getSelectionModel().getSelectedIndex() + 1;
                System.out.println("Вывод: услуга "+ selectItem);

                dbHandler.postGenerateTalon(selectItem);
                openPopup("PopUp.fxml");



            }
        });
    }

//    открытие ПопАп окна (автозакрытие через 10 сек)
    public void openPopup(String window) {

        lstService.getScene().getWindow();


        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(window));

        try {
            loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.show();

        PauseTransition delay = new PauseTransition(Duration.seconds(10));   // запустится на 10 секунды
        delay.setOnFinished(event -> {
            stage.close();  // закроем окно
        });
        delay.play();
    }




}
