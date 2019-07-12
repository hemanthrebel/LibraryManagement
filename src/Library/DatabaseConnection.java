package Library;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.*;

public class DatabaseConnection {
    private String username = "root";
    private String password = "hem@1234";
    private String url = "jdbc:mysql://localhost:3306/library";
    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;
    private String query = null;

    protected void connect() {      //Later chaneg into constructor
        try {
            connection = DriverManager.getConnection(url,username,password);
            System.out.println("Connected to db");
        }
        catch (java.sql.SQLException e) {
            System.out.print("unable to connect to db " + e);
        }
    }

    protected String createTable(String tableName) {
        String res = "";
        int flag = -1;
        try {
            query = "show tables;";
            statement = connection.createStatement();
            preparedStatement = connection.prepareStatement(query);     //prepared statmnt improves perf
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if(resultSet.getString(1).equals(tableName)) {
                    flag = 0;break;
                }
            }
            if(flag == 0) {
                res = "Table Already Exists";
            }
            else {
                query = "create table " + tableName + "";
            }
        }
        catch (Exception e) {
            res = e.toString();
        }
        return res;
    }
}
