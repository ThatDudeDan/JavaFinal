/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import entity.ConnectString;
import entity.SongItem;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "GetRecords", urlPatterns = {"/GetRecords"})
public class GetRecords extends HttpServlet {

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
            List<SongItem> allItems = new ArrayList();
            Connection conn = null;
            System.out.print("Testing");

            try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            conn = DriverManager.getConnection(ConnectString.getConnectionString(), ConnectString.getUser(), ConnectString.getPassword());            PreparedStatement Addstmt = conn.prepareStatement("select * from songitems where songID = ?");
            PreparedStatement selectAllStatement = conn.prepareStatement("select * from SongItems");
            ResultSet rs = selectAllStatement.executeQuery();
            System.out.print("Testing");

             while (rs.next()) {
                int id = rs.getInt("SongId");
                String title = rs.getString("title");
                String artist = rs.getString("Artist");
                double price = rs.getDouble("price");
                boolean issold = rs.getBoolean("isSold");
                SongItem item = new SongItem(id, title, artist, price, issold);   
                allItems.add(item);
            }
            }
            catch (SQLException ex) {
            HttpSession session = request.getSession();
            session.setAttribute("Error", ex.getMessage());
            // forward to JSP
            String path = "/ErrorPage.jsp";
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.forward(request, response);
        }
        System.out.print(allItems.size());
        // store allItems in session
        HttpSession session = request.getSession();
        session.setAttribute("theSongItems", allItems);

        // forward to JSP
        String path = "/ShowRecords.jsp";
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
