package com.example.smo;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    private Text lbServiceName;

    @FXML
    void initialize() throws SQLException {
        _DatabaseHandler dbHandler = new _DatabaseHandler();


        lbFirstName.setText(AutorizScaneController.FirstName);
        lbLastName.setText(AutorizScaneController.LastName);

        btnInvite.setOnAction(event ->{ //ивент на отображение данных в окне
            ResultSet result = dbHandler.getFirstEntryNum();
            while (true) {
                try {
                    if (!result.next()) break;
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                try {
                    lbQueueNumber.setText(result.getString(_Const.TALON_ID)); // отображение номеры талона
                    lbServiceName.setText(result.getString(_Const.SERVICE_TITLE)); // отображение названия услуги

                    if (result.getString(_Const.TALON_ID) != "") {
                        dbHandler.updateManagerToTalon(AutorizScaneController.idManager, result.getString(_Const.TALON_ID));
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }

        });
    }

//    обработчик на вызов клиента (первый из списка который имеет стату "вэйтинг") - done
//    обработчик на нажатие изменени статуса талона на "доне"(таким образом талон больше не вызывается)


//    новое окно с тало с лист вью
}
