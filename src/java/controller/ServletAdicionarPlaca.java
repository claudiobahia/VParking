/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ConnectionPool;

/**
 *
 * @author claud
 */
public class ServletAdicionarPlaca extends HttpServlet {

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
        String placa = request.getParameter("edtPlacaAdicionar");

        Random r = new Random();

        int id = r.nextInt(100000);

        int vaga = r.nextInt(20);

        Date data = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        response.setContentType("text/html;charset=UTF-8");
        if (placa.isEmpty()) {
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Campo da placa se encontra vazia!</h1><br><a href=/VParking/index.htm>Voltar</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            try {
                Connection sqlConnection = ConnectionPool.getConnection();
                PreparedStatement preparedStatement = sqlConnection.prepareStatement(
                        "insert into estacionamento values(?,?,?,?)");
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, placa);
                preparedStatement.setInt(3, vaga);
                preparedStatement.setString(4, fmt.format(data));
                preparedStatement.executeUpdate();
                request.setAttribute("message", "Dado cadastrado!");
                request.getRequestDispatcher("index.htm").forward(request, response);
            } catch (SQLException e) {
                System.out.println(e);
                request.setAttribute("message", "Ocorreu um erro com o Banco de Dados");
                request.getRequestDispatcher("index.htm").forward(request, response);
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