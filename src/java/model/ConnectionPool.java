package model;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class ConnectionPool {
   
    private static ConnectionPool pool = null;
    private BasicDataSource dataSource = null;
    
    private ConnectionPool() throws IOException, SQLException {
        String driver, strConn, user, pwd;

        // Recupera os parâmetros da conexão do arquivo de propriedades
        Properties props = new Properties();
        InputStream input = getClass().getClassLoader().getResourceAsStream("config/db.properties");
        props.load(input);
        input.close();

        driver = props.getProperty("driver");
        strConn = props.getProperty("conexao");
        user = props.getProperty("user");
        pwd = props.getProperty("pwd");

        System.out.println("->"+driver);
        System.out.println("->"+strConn);
        System.out.println("->"+user);
        System.out.println("->"+pwd);
        
        // Caso as propriedades não existam gera uma exceção
        if (driver == null || strConn == null || user == null || pwd == null || 
            driver.isEmpty() || strConn.isEmpty())
                throw new SQLException("Não foi possível carregar as propriedades da conexão JDBC");

        // Inicializa o pool de conexões
        dataSource = new BasicDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(strConn);
        dataSource.setUsername(user);
        dataSource.setPassword(pwd);
        
        // O arquivo de propriedades pode conter outros parâmetros para inicializar o pool
        dataSource.setInitialSize(1);
    }
    
    public static void create() throws IOException, SQLException {
        pool = new ConnectionPool();
    }
    
    public static synchronized Connection getConnection() throws SQLException {
        return pool.dataSource != null ? pool.dataSource.getConnection() : null;
    }
    
    public static synchronized void destroy(){
        if (pool.dataSource != null) {
            try {
                pool.dataSource.close();
            } catch (Exception ex) {
                Logger.getLogger(ConnectionPool.class.getName()).log(Level.SEVERE, null, ex);
            }
            pool.dataSource = null;
        }
    }
}
