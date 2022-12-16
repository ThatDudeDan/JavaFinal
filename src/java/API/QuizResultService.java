/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package API;

import DB.QuizAccessor;
import DB.quizResultAccessor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.QuizResult;
import java.util.List;
import com.google.gson.Gson;
import DB.quizResultAccessor;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mysql.cj.Session;
import entity.Quiz;
import java.sql.Array;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Scanner;
import javax.servlet.http.HttpSession;
/**
 *
 * @author cliff
 */
@WebServlet(name = "QuizResultService", urlPatterns = {"/quizResultService/Items", "/quizResultService/Items/*"})
public class QuizResultService extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuizResultService</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuizResultService at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
         if(request.getPathInfo()==null){
            DB.quizResultAccessor qra = new quizResultAccessor();
            List<QuizResult> allItems = qra.getQuizResultsbyQuery();
            HttpSession sesh = request.getSession();
            sesh.setAttribute("allQuizResults", allItems);

            }
        try (PrintWriter out = response.getWriter()) {
               DB.quizResultAccessor qra = new quizResultAccessor();
               
               QuizResult result = qra.getQuizResultsByID(request.getPathInfo().substring(1));
                Gson gson = new Gson();
                out.print(gson.toJson(result));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
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
        try (PrintWriter out = response.getWriter()) {        
               DB.quizResultAccessor qra = new quizResultAccessor();
                Gson g = new Gson();
                Scanner sc = new Scanner(request.getReader());
                String jsonData = sc.nextLine();
                JsonObject jobj = new Gson().fromJson(jsonData, JsonObject.class);
                String quizID = jobj.get("quizID").toString().replaceAll("\"", "");
                String username = jobj.get("username").toString().replaceAll("\"", "");
                String userTemp = jobj.get("userAnswers").toString().replaceAll("\"", "").replace("[","").replace("]", "");
                String[] userT = userTemp.split(",");
                List<String> userArray = Arrays.asList(userT);
                Timestamp startTemp = Timestamp.valueOf(jobj.get("startTime").toString().replaceAll("\"", ""));
                Timestamp endTemp =  Timestamp.valueOf(jobj.get("endTime").toString().replaceAll("\"", ""));
                int scoreNum = Integer.parseInt(jobj.get("scoreNumerator").toString());
                int scoreDim = Integer.parseInt(jobj.get("scoreDenumerator").toString());
                //System.out.print(startTemp);
                System.out.print(jsonData);
                System.out.print(jsonData);
                QuizResult results = new QuizResult(qra.getResultCount(), quizID, username,userArray, startTemp,endTemp,scoreNum,scoreDim);
                boolean bool = qra.insertResult(results);
                out.println(qra.getResultCount());
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
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
