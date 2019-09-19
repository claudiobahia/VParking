package model;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name="InicializacaoPool", urlPatterns ="/inicializacao", loadOnStartup=0)
public class InicializacaoPool extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        try {
            System.out.println("Iniciando...");
            ConnectionPool.create();
            System.out.println("Iniciado!");
        } catch(IOException | SQLException e) {
            System.out.println("Erro na criação do pool!");
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void destroy(){
        System.out.println("Destruindo...");
            ConnectionPool.destroy();
    }
}
