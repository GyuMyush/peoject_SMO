package com.example.smo;

import java.lang.constant.Constable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class _DatabaseHandler extends _Configs{
    Connection dbConnection;
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }

//    методы для манипуляции с БД

//    метод для добавления юзера (для администратора)
    public void singUpUser(User user) {

        String insert = "INSERT INTO " + _Const.USER_TABLE + "(" + _Const.USERS_FIRSTNAME
                + "," + _Const.USERS_LASTNAME + "," + _Const.USERS_MIDDLENAME
                + "," + _Const.USERS_USERNAME + "," + _Const.USERS_PASSWORD
                + "," + _Const.USERS_ROLE + ")" +
                "VALUES(?,?,?,?,?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);

            prSt.setString(1, user.getFirstName());
            prSt.setString(2, user.getLast_name());
            prSt.setString(3, user.getMiddle_name());
            prSt.setString(4, user.getLogin());
            prSt.setString(5, user.getPassword());
            prSt.setString(6, user.getRole());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    выгрузка списка юзеров
    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + _Const.USER_TABLE + " WHERE " + _Const.USERS_USERNAME + "=? AND " + _Const.USERS_PASSWORD + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);

            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSet;
    }

    //вывод списка услуг на табло киоска
    public ResultSet getService() {
        ResultSet resSrvc = null;
        String select = "SELECT " + _Const.SERVICE_TITLE + " FROM " + _Const.SERVICE_TABLE;

        try {
            PreparedStatement v = getDbConnection().prepareStatement(select);

            resSrvc = v.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        return resSrvc;
    }

    //    добавление талона в очередь
    public void postGenerateTalon(Integer selectItem) {

        String insert = "INSERT INTO " + _Const.TALON_TABLE + "("
                + _Const.SERVICE_ID + ","
                + _Const.TALON_STATUS + ")" +
                "VALUES (?,?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, String.valueOf(selectItem));
            prSt.setString(2, "waiting");

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getLastEntryNum() {
        ResultSet resLast = null;
        String select = "SELECT " + _Const.TALON_ID + " FROM " + _Const.TALON_TABLE + " ORDER BY " + _Const.TALON_ID + " DESC LIMIT 1";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(String.valueOf(select));
            resLast = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resLast;
    }


}
