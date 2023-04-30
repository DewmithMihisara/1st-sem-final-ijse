package lk.ijse.project_dkf.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static DBConnection dbConnection;
    private static Properties props=new Properties();
    private static final String URL="jdbc:mysql://localhost:3306/dkf";

    static {
        props.setProperty("user","root");
        props.setProperty("password", "Dew@2005");
    }
    private Connection con;
    private DBConnection() throws SQLException {
        con=DriverManager.getConnection(URL,props);
    }
    public static DBConnection getInstance() throws SQLException {
        return (null == dbConnection)? dbConnection=new DBConnection(): dbConnection;
    }
    public Connection getConnection(){return con;}
}
