package database;

import java.sql.*;

public class JDBC {

    private static final String DB_URL = "jdbc:mysql://your-database-url:3306/your-database-name";
    private static final String DB_USERNAME = "your-username";
    private static final String DB_PASSWORD = "your-password";

    public static boolean verifyUsername(String username){
        try{
            Connection connect = DriverManager.getConnection(
                    DB_URL, DB_USERNAME, DB_PASSWORD
            );

            PreparedStatement verifyUsernameQuery = connect.prepareStatement("SELECT * FROM USERS WHERE USERNAME = ?");
            verifyUsernameQuery.setString(1, username);

            ResultSet resultSet = verifyUsernameQuery.executeQuery();
            // true - resultSet found the username in the database. So the username is duplicate
            // false - username is not duplicate
            if(resultSet.next())
                return false;
            else
                return true;
        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean verifyUserLogIn(String username, String password){
        try{
            Connection connect = DriverManager.getConnection(
                    DB_URL, DB_USERNAME, DB_PASSWORD
            );

            PreparedStatement verifyUsernameQuery = connect.prepareStatement(
                    "SELECT * FROM users WHERE USERNAME = '" + username + "' AND PASSWORD = '" + password + "'");

            ResultSet resultSet = verifyUsernameQuery.executeQuery();
            if(resultSet.next())
                return true;
            else
                return false;
        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean insertUserSignUp(String username, String password){
        try{
            Connection connect = DriverManager.getConnection(
                    DB_URL, DB_USERNAME, DB_PASSWORD
            );

            PreparedStatement numberOfUsersQuery = connect.prepareStatement(
                    "SELECT COUNT(*) FROM USERS");
            ResultSet numOfUsersSet = numberOfUsersQuery.executeQuery();
            numOfUsersSet.next();
            int numOfUsers = numOfUsersSet.getInt(1);

            PreparedStatement verifyUsernameQuery = connect.prepareStatement(
                    "INSERT INTO USERS(USER_ID, USERNAME, PASSWORD) " +
                            "VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
            verifyUsernameQuery.setInt(1, numOfUsers + 1);
            verifyUsernameQuery.setString(2, username);
            verifyUsernameQuery.setString(3, password);
            verifyUsernameQuery.executeUpdate();

            ResultSet resultSet = verifyUsernameQuery.getGeneratedKeys();
            if(resultSet.next()){
                return true;
            } return false;

        } catch(Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
