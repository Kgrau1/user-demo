package edu.matc.controller;

import edu.matc.entity.SearchedUsers;
import edu.matc.entity.User;
import edu.matc.persistence.Database;
import edu.matc.persistence.UserData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * A simple servlet to welcome the user.
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/searchUser"}
)

public class SearchUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        UserData userData = new UserData();
        SearchedUsers search = null;

        if (req.getParameter("submit").equals("search")) {
            String searchType = req.getParameter("searchType");
            String searchTerm = req.getParameter("searchTerm");

            search = userData.searchEmployee(searchType, searchTerm);
        } else {
            search = userData.getAllUsers();
        }

        HttpSession session = req.getSession();
        session.setAttribute("search", search);

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/results.jsp");
        dispatcher.forward(req, resp);
    }
}