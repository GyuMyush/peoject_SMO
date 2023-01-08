package com.example.smo;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PopUp {


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnDone;

    @FXML
    private Label lblNum;

    @FXML
    void initialize() throws SQLException {
        _DatabaseHandler dbHandler = new _DatabaseHandler();
        ResultSet result = dbHandler.getLastEntryNum();

        while (result.next()) {
            lblNum.setText(result.getString(_Const.TALON_ID));
        }


        btnDone.setOnAction(event ->{
            btnDone.getScene().getWindow().hide();
        });




    }


}
