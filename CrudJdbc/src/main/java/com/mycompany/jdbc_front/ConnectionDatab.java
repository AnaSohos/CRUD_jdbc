package com.mycompany.jdbc_front;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDatab {

    private String login;
    private String password;
    private String url;

    private String host;
    private String dataDb;

    private Connection pgConnection;
    private Properties properties = new Properties();

    public ConnectionDatab(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
    }

    public ConnectionDatab(String url, Properties properties) {
        this.url = url;
        this.properties = properties;
    }

    public ConnectionDatab() {

    }

    public void initProperties() throws IOException, ClassNotFoundException {

        properties.setProperty("url", url);
        properties.setProperty("login", login);
        properties.setProperty("password", password);
        properties.setProperty("characterEncoding", "UTF-8");
        properties.setProperty("useUnocode", "true");

    }

    public void init() throws IOException, ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            pgConnection = DriverManager.getConnection(url, login, password);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void setNameDataBasses(String dataDb) {
        this.dataDb = dataDb;
    }

    public void sqlQuery(String query) {

        try {
            Statement stmt = pgConnection.createStatement();
            stmt.executeUpdate(query);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    public void finalize() {
        try {
            pgConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

    public ResultSet resultSetQuery(String query) {
        try {
            Statement stmt = pgConnection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            return rs;
        } catch (SQLException e) {
        }
        return null;

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUrl() {
        return url;
    }

    public String getHost() {
        return host;
    }

    public String getDataDb() {
        return dataDb;
    }

    public Connection getPgConnection() {
        return pgConnection;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setDataDb(String dataDb) {
        this.dataDb = dataDb;
    }

    public void setPgConnection(Connection pgConnection) {
        this.pgConnection = pgConnection;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public static Properties cfg() throws IOException {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/java/resource/cfg.properties");
            property.load(fis);

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        return property;
    }

}
