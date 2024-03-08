package felipefabossi.javadbexemplo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionFactory {

    private final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // URL de acesso ao banco de dados.
    private final String URL = "jdbc:mysql://localhost:3306/contatodb";
    // Nome de usuário para acesso ao banco de dados.
    private final String USER = "admin";
    // Senha de usuário para acesso ao banco de dados.
    private final String PASS = "root";

    public Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection con, PreparedStatement ps) {
        closeConnection(con);
        try {
            if (ps != null) {
                ps.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(Connection con, PreparedStatement ps, ResultSet rs) {
        closeConnection(con, ps);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
