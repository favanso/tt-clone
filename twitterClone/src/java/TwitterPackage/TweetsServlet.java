/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package TwitterPackage;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
        
/**
 *
 * @author Fernando
 */
public class TweetsServlet extends HttpServlet {

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
        
            if (request.getParameter("action") == null) {
                ArrayList<Tweets> tweets = TweetModel.getTweets();

                request.setAttribute("tweets", tweets);

                String url = "/tweets.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);
             } else if (request.getParameter("action").equalsIgnoreCase("addTweet")) {
                String url = "/views/welcome.jsp";
                getServletContext().getRequestDispatcher(url).forward(request, response);

            } else if (request.getParameter("action").equalsIgnoreCase("updateTweet")) {

                String id = request.getParameter("id");
                String userId = request.getParameter("userId");
                String content = request.getParameter("content");
                String dateTime = request.getParameter("dateTime");
                String likes = request.getParameter("likes");

                if (id == null || id.isBlank() || userId == null || userId.isBlank()
                        || content == null || content.isBlank()
                        || dateTime == null || dateTime.isBlank()
                        || likes == null || likes.isBlank()) {
                    String url = "/views/error.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else {
                    Tweets tweet = new Tweets(Integer.parseInt(id), Integer.parseInt(userId), content, dateTime, Integer.parseInt(likes));
                    TweetModel.updateTweet(tweet);

                }
                response.sendRedirect("TweetsServlet");
            }else if(request.getParameter("action").equalsIgnoreCase("addTweetReal")){
                
                //String userId = request.getParameter("userId");
                String content = request.getParameter("tweet");
                //String dateTime = new java.util.Date().toString();
                

                if (content == null || content.isBlank()) {
                    String url = "/views/error.jsp";
                    getServletContext().getRequestDispatcher(url).forward(request, response);
                } else {
                    Tweets tweet = new Tweets(0, 0, content, "", 0);
                    TweetModel.addTweets(tweet);
                    response.sendRedirect("TweetsServlet");
                }
            }

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
