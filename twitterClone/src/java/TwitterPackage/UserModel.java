package TwitterPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserModel {

    public static ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("select * from user");

            while (results.next()) {
                int id = results.getInt("id");
                String username = results.getString("username");
                String passwordHash = results.getString("passwordHash");

                users.add(new User(id, username, passwordHash));
            }

            results.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return users;
    }
    
    public static void findPerson(int id) {
 try{
        Connection connection = DBConnection.getConnection();
        String preparedSQL = "select username from user where id = ? ";
        PreparedStatement statement = connection.prepareStatement(preparedSQL);
        statement.execute();
        
        
 }      catch (SQLException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(UserModel.class.getName()).log(Level.SEVERE, null, ex);
        }

}

    public static String addUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "insert into user (id, username, passwordHash) "
                    + " values (?, ?, ? )";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setInt(1, user.getId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getPasswordHash());

            statement.execute();

        } catch (SQLException ex) {
            return ex.toString();
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return "";
    }

    public static void updateUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "update user set username = ?, passwordHash = ? "
                    + " where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getPasswordHash());
            statement.setInt(3, user.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void deleteUser(User user) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "delete from user where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setInt(1, user.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void followUser(User user, User userFollowing) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "insert into following (userId, followsUserId) "
                    + " values ( ?, ? )";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setInt(1, user.getId());
            statement.setInt(2, userFollowing.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
