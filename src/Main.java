import screens.loginScreenGui;
import javax.swing.*;
import java.sql.*;


public class Main {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/log_in_db";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "pass420";

    public static void main(String[] args) throws SQLException {
//        Connection connect = DriverManager.getConnection(
//                DB_URL, DB_USERNAME, DB_PASSWORD
//        );
//
//        PreparedStatement statement = connect.prepareStatement("SELECT * FROM USERS");
//
//        ResultSet resultSet = statement.executeQuery();
//
//        while(resultSet.next()){
//            System.out.println(resultSet.getString("user_id"));
//            System.out.println(resultSet.getString("username"));
//            System.out.println(resultSet.getString("password"));
//        }

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new loginScreenGui().setVisible(true);
            }
        });
    }
}