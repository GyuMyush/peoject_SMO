package com.example.smo;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AutorizScaneController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enterBtn;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField userField;

    public static String FirstName = "";
    public static String LastName = "";

    @FXML
    void initialize() {

        enterBtn.setOnAction(event ->{
            String loginText = userField.getText().trim();
            String loginPassword = passwordField.getText().trim();

            if(!loginText.equals("") && !loginPassword.equals("")) {
                try {
                    loginUser(loginText, loginPassword);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else
                System.out.println("Введена пустая строка /~ ASC.java");

        });
    }

    private void loginUser(String loginText, String loginPassword) throws SQLException {
        _DatabaseHandler dbHandler = new _DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);

        ResultSet result = dbHandler.getUser(user);

        String userRole = "";
        while (result.next()) {
            userRole = result.getString(_Const.USERS_ROLE);
            FirstName = result.getString(_Const.USERS_FIRSTNAME);
            LastName = result.getString(_Const.USERS_LASTNAME);
        }


        if(userRole.equals("manager")) {
            openScene("Manager.fxml");
            System.out.println("манагер айден - /~ ASC.java");

        } if(userRole.equals("root")) {
            openScene("Administration.fxml");
            System.out.println("админ найдет - /~ ASC.java");

        } if(userRole.equals("kiosk")) {


            openScene("ChoosingService.fxml");
            System.out.println("киоск найдет - /~ ASC.java");

        } else {
            Shake userLoginAnim = new Shake(userField);
            Shake userPassAnim = new Shake(passwordField);
            userLoginAnim.playAnim();
            userPassAnim.playAnim();
            System.out.println(("error - неверный логин /~ ASC.java"));
        }
    }

    public void openScene(String window) {

        enterBtn.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(window));

            try {
                loader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }

}
