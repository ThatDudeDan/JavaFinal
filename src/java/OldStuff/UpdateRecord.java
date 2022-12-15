/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import entity.ConnectString;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author cliff
 */
@WebServlet(urlPatterns = {"/UpdateRecord"})
public class UpdateRecord extends HttpServlet {

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
            Connection conn = null;
            System.out.print("Testing");
// private int id;
//    private String title;
//    private String artist;
//    private double price;
//    private boolean issold;
            try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(ConnectString.getConnectionString(), ConnectString.getUser(), ConnectString.getPassword());            PreparedStatement Addstmt = conn.prepareStatement("select * from songitems where songID = ?");
            PreparedStatement UpdateStmt = conn.prepareStatement("UPDATE songitems set title = ?, artist = ?, price = ?, isSold = ? where SongID = ?");
            //Set Vars
            boolean isSold = false;
            if (request.getParameter("isSold").equals("on"))
            {
               isSold = true;
            }
            UpdateStmt.setString(1, request.getParameter("title"));
            UpdateStmt.setString(2, request.getParameter("artist"));
            UpdateStmt.setDouble(3, Double.parseDouble(request.getParameter("price")));
            UpdateStmt.setBoolean(4, isSold);
            System.out.println(request.getParameter("id"));
            UpdateStmt.setInt(5, Integer.parseInt(request.getParameter("id")));
            UpdateStmt.executeUpdate();
            System.out.print("Testing");
  
            } catch (SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("Error", ex.getMessage());
            // forward to JSP
            String path = "/ErrorPage.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
        }
        // store allItems in session

        // forward to JSP
        String path = "/GetRecords";
        RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);
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