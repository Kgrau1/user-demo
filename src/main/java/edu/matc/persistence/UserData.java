package edu.matc.persistence;

import edu.matc.entity.SearchedUsers;
import edu.matc.entity.SearchedUsers;
import edu.matc.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {

    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * An empty constructor
     */
    public UserData() {
    }

    public SearchedUsers getAllUsers() {

        SearchedUsers search = new SearchedUsers();
        Database database = Database.getInstance();
        Connection connection = null;
        String sql = "SELECT * FROM users";

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                createUserFromResults(results, search);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.info("SearchUser.getAllUsers()...SQL Exception: " + e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: " + e);
        }
        return search;
    }

    /**
     * Searches the database for employees based on the given search type and term
     * @param searchType the type of search to preform
     * @param searchTerm the term to search for
     * @return a SearchedUsers object containing the results of the search
     */
    public SearchedUsers searchEmployee(String searchType, String searchTerm) {

        Database database = Database.getInstance();
        Connection connection = database.getConnection();
        SearchedUsers search = new SearchedUsers();

        if (searchType.equals("id")) {
            search.setSearchTerm(searchTerm);
            searchByEmployeeId(connection, search);
        } else if (searchType.equals("first_name")) {
            search.setSearchTerm(searchTerm);
            // searchByEmployeeFirstName(connection, search);
        } else if (searchType.equals("last_name")) {
            search.setSearchTerm(searchTerm);
            // searchByEmployeeLastName(connection, search);
        }
        return search;
    }
    /**
     * Searches the database for employees with the given employee ID.
     * @param connection a connection to the database
     * @param search a User object containing the search term
     */
    private void searchByEmployeeId(Connection connection, SearchedUsers search) {

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String queryString = "SELECT * FROM users WHERE id = ?";
            preparedStatement = connection.prepareStatement(queryString);
            preparedStatement.setString(1, search.getSearchTerm());
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                createUserFromResults(resultSet, search);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }

                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }

    private User createUserFromResults(ResultSet results, SearchedUsers search) throws SQLException {
        User user = new User();
        user.setId(results.getInt("id"));
        user.setFirstName(results.getString("first_name"));
        user.setLastName(results.getString("last_name"));
        user.setUserName(results.getString("user_name"));
        user.setDateOfBirth(LocalDate.parse(results.getString("date_of_birth")));
        search.addFoundEmployee(user);

        return user;
    }

}