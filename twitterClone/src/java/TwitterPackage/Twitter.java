package TwitterPackage;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Twitter extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        if (request.getParameter("action") == null || request.getParameter("action").equalsIgnoreCase("userlist")) {
            ArrayList<User> users = UserModel.getUsers();

            request.setAttribute("users", users);

            String url = "/userList.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);
        } else if (request.getParameter("action").equalsIgnoreCase("addUser")) {

            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            if (userName == null || userName.isBlank() || password == null || password.isBlank()) {
                String url = "/views/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {

                try {
                    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
                    String hashedPassword = toHexString(getSHA(password));
                    User user = new User(0, userName, hashedPassword);
                    String result = UserModel.addUser(user);

                    if (!result.isEmpty()) {
                        String url = "/views/error.jsp";
                        getServletContext().getRequestDispatcher(url).forward(request, response);
                    }

                } catch (NoSuchAlgorithmException ex) {
                    System.out.println(ex);
                }
            }

            response.sendRedirect("Twitter");
        } else if (request.getParameter("action").equalsIgnoreCase("updateUser")) {

            String id = request.getParameter("id");
            String userName = request.getParameter("username");
            String password = request.getParameter("password");

            if (id == null || id.isBlank() || userName == null || userName.isBlank() || password == null || password.isBlank()) {
                String url = "/views/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {

                try {
                    //https://www.geeksforgeeks.org/sha-256-hash-in-java/
                    String hashedPassword = toHexString(getSHA(password));

                    User user = new User(Integer.parseInt(id), userName, hashedPassword);

                    UserModel.updateUser(user);

                } catch (NoSuchAlgorithmException ex) {
                    String url = "/views/error.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                }
            }
            response.sendRedirect("Twitter");
        } else if (request.getParameter("action").equalsIgnoreCase("deleteUser")) {

            String id = request.getParameter("id");

            if (id == null || id.isBlank()) {
                String url = "/views/error.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
            } else {

                User user = new User(Integer.parseInt(id), "", "");
                UserModel.deleteUser(user);
            }
            
            response.sendRedirect("Twitter");
        } else if (request.getParameter("action").equalsIgnoreCase("following")) {
            HttpSession session = request.getSession();
            String UserId = session.getAttribute("username").toString();
            String followerId = request.getParameter("id");

            User user = new User(Integer.parseInt(UserId), "", "");
            User userFollower = new User(Integer.parseInt(followerId), "", "");

            UserModel.followUser(user, userFollower);

        }
        
    }

    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String toHexString(byte[] hash) {

        BigInteger number = new BigInteger(1, hash);

        StringBuilder hexString = new StringBuilder(number.toString(16));

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
        processRequest(request, response);
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
        processRequest(request, response);
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
