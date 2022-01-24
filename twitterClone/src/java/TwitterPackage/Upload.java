package TwitterPackage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig(maxFileSize = 1000000)
public class Upload extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        // https://www.codejava.net/coding/upload-files-to-database-servlet-jsp-mysql
        InputStream inputStream = null;
        String fileName = "";

        Part filePart = request.getPart("file");
        if (filePart != null) {
            fileName = extractFileName(filePart);

            inputStream = filePart.getInputStream();
        }

        try {
            HttpSession session = request.getSession();
            String username = session.getAttribute("username").toString();

            Connection connection = DBConnection.getConnection();
            String preparedSQL = "update user set image = ?, filename = ? "
                    + " where username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(preparedSQL);

            preparedStatement.setBlob(1, inputStream);
            preparedStatement.setString(2, fileName);
            preparedStatement.setString(3, username);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            String url = "/views/welcome.jsp";
            getServletContext().getRequestDispatcher(url).forward(request, response);

        } catch (SQLException exception) {
            System.out.println(exception);
        } catch (ClassNotFoundException exception) {
            System.out.println(exception);
        }

    }

    //https://www.codejava.net/java-ee/servlet/java-file-upload-example-with-servlet-30-api
    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                return s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        return "";
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
