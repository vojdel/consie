package general;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eggo
 */
public class BD {

    private String driver;
    private String driverUrl;
    private String usuario;
    private String clave;
    private Connection c;
    private Statement stmt;
    private ResultSet rs;
    public static String sql;

    public BD() {
        ini1();
    }

    private void ini1() {
        driver = "org.postgresql.Driver";
        driverUrl = "jdbc:postgresql://localhost:5432/consie";
        usuario = "postgres";
        clave = "123456";
    }

    private void ini2() {
        driver = "com.mysql.jdbc.Driver";
        driverUrl = "jdbc:mysql://localhost/consie";
        usuario = "root";
        clave = "";
    }

    public void conectar() {
        c = null;

        try {
            Class.forName(driver);
            c = DriverManager.getConnection(driverUrl, usuario, clave);

            if (c != null) {
                System.out.println("Base de datos abierta");
            }

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
    }

    public ResultSet consultarBD() {
        stmt = null;
        rs = null;

        try {
            stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                rs.beforeFirst();
                System.out.println("Consulta realizada");
                return rs;

            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public void actualizarBD() {
        stmt = null;

        try {
            stmt = c.createStatement();
            stmt.executeUpdate(sql);
            desconectar();

        } catch (SQLException e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }

        System.out.println("Actualización realizada");
    }

    public void desconectar() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (c != null) {
                c.close();
            }

        } catch (SQLException ex) {
            Logger.getLogger(BD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getC() {
        return c;
    }

    public static void setSql(String sql) {
        BD.sql = sql;
    }

}
