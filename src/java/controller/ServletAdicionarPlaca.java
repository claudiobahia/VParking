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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ConnectionPool;
import model.Veiculo;

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
//    Vendo as vagas pra não ter repetição
    private final int reguladorNumeroVagas = 21;

    private int gerarVaga(ArrayList<Integer> listaVaga) {
        Random r = new Random();
        int gerado = -1;
        boolean verificador = true;
        if (listaVaga.size() != this.reguladorNumeroVagas) {
            while (verificador) {
                gerado = r.nextInt(this.reguladorNumeroVagas);
                if (!listaVaga.contains(gerado)) {
                    verificador = false;
                }
            }
        }
        return gerado;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

//        Pegando os dados para salvar
        String placa = request.getParameter("edtPlacaAdicionar");

        Date data = new Date();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//        Terminando o preparamento dos dados
        response.setContentType("text/html;charset=UTF-8");
        if (placa.isEmpty()) {
//            Caso placa venha com erro, amostra erro e opção voltar index
            try (PrintWriter out = response.getWriter()) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet NewServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Campo da placa se encontra vazia!</h1><br><a href=redirect.jsp>Voltar</a>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
//            Caso não tenha erro na placa, salve
            try {
//                Conectando o banco
                Connection sqlConnection = ConnectionPool.getConnection();
//                Verificando para não criar em uma vaga já em uso
                PreparedStatement preparedStatement
                        = sqlConnection.prepareStatement("select vaga from estacionamento order by id desc");
                ResultSet resultSet = preparedStatement.executeQuery();
                ArrayList<Integer> listaVaga = new ArrayList<>();
                while (resultSet.next()) {
                    listaVaga.add(Integer.parseInt(resultSet.getString("vaga")));
                }
                int novavaga = gerarVaga(listaVaga);
                if (novavaga == -1) {
                    try (PrintWriter out = response.getWriter()) {
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Servlet NewServlet</title>");
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>O estacionamento está lotado!</h1><br><a href=redirect.jsp>Voltar</a>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                } else {
                    int vaga = novavaga;
                    preparedStatement = sqlConnection.prepareStatement(
                            "insert into estacionamento (placa,vaga,data) values(?,?,?)");
//                Jogando para o DB
                    preparedStatement.setString(1, placa);
                    preparedStatement.setInt(2, vaga);
                    preparedStatement.setString(3, fmt.format(data));
                    preparedStatement.executeUpdate();
//                Mensagem de teste
//                TODO todo Todo fazer essa menssagem em algum lugar pra ficar legal
                    preparedStatement.close();
                    resultSet.close();
                    request.setAttribute("message", "Dado cadastrado!");
                    request.getRequestDispatcher("redirect.jsp").forward(request, response);
                }
            } catch (SQLException e) {
                System.out.println(e);
                request.setAttribute("message", "Ocorreu um erro com o Banco de Dados");
                request.getRequestDispatcher("redirect.jsp").forward(request, response);
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
