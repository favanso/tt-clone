package TwitterPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TweetModel {

    public static ArrayList<Tweets> getTweets() {
        ArrayList<Tweets> tweets = new ArrayList<>();

        try {
            Connection connection = DBConnection.getConnection();

            Statement statement = connection.createStatement();

            ResultSet results = statement.executeQuery("select * from tweet");

            while (results.next()) {
                int id = results.getInt("id");
                int userId = results.getInt("userId");
                String content = results.getString("contents");
                String dateTime = results.getString("dateTime");
                int likes = results.getInt("likes");

                tweets.add(new Tweets(id, userId, content, dateTime, likes));
            }

            results.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return tweets;
    }

    public static String addTweets(Tweets tweet) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "insert into tweet (id, userId, contents, dateTime, likes) "
                    + " values ( ?, ?, ?, ?, ? )";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setInt(1, tweet.getId());
            statement.setInt(2, tweet.getUserId());
            statement.setString(3, tweet.getContent());
            statement.setString(4, tweet.getDateTime());
            statement.setInt(5, tweet.getLikes());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return "";
    }

    public static void updateTweet(Tweets tweet) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "update tweet set id = ?, userId = ?, contents = ?, dateTime = ?, likes = ?"
                    + " where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setInt(1, tweet.getId());
            statement.setInt(2, tweet.getUserId());
            statement.setString(3, tweet.getContent());
            statement.setString(4, tweet.getDateTime());
            statement.setInt(5, tweet.getLikes());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }

    public static void deleteTweet(Tweets tweet) {
        try {
            Connection connection = DBConnection.getConnection();

            String preparedSQL = "delete from tweet where id = ? ";
            PreparedStatement statement = connection.prepareStatement(preparedSQL);

            statement.setInt(1, tweet.getId());

            statement.execute();

        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (ClassNotFoundException ex) {
            System.out.println(ex);
        }
    }
}
