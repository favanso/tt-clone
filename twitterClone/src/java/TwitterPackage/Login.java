package TwitterPackage;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, NoSuchAlgorithmException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action") == null) {

            String url = "/login.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("login")) {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || username.equals("") || password == null || password.equals("")) {
                String url = "/views/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {
                String url = "/views/welcome.jsp";

                try {
                    Connection connection = DBConnection.getConnection();
                    String preparedSQL = "select passwordHash from user where username = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);

                    preparedStatement.setString(1, username);
                    ResultSet result = preparedStatement.executeQuery();
                    String storedPasswordHash = null;
                    while (result.next()) {
                        storedPasswordHash = result.getString("passwordHash");
                    }

                    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
                    String hashedPassword = toHexString(getSHA(password));

                    if (hashedPassword.equals(storedPasswordHash)) {
                        HttpSession session = request.getSession();
                        session.setAttribute("username", username);

                        getServletContext().getRequestDispatcher(url).forward(request, response);

                    } else {
                        url = "/views/error.jsp";
                        getServletContext().getRequestDispatcher(url).forward(request, response);
                    }

                    connection.close();

                } catch (SQLException exception) {
                    System.out.println(exception);
                } catch (ClassNotFoundException exception) {
                    System.out.println(exception);
                }

            }

        } else if (request.getParameter("action").equalsIgnoreCase("create_account")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            if (username == null || username.equals("") || password == null || password.equals("")) {
                String url = "/views/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {
                String url = "/views/welcome.jsp";
                String hashedPassword = toHexString(getSHA(password));

                User user = new User(0, username, hashedPassword);

                String result = UserModel.addUser(user);

                HttpSession session = request.getSession();
                session.setAttribute("username", username);

                getServletContext().getRequestDispatcher(url).forward(request, response);

            }
        }
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");

        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash);

        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16));

        // Pad with leading zeros
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
