package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MEstado {

    private int id;
    private String nombre;

    private BD con = new BD();
    private ResultSet rs;

    public MEstado() {
    }

    public MEstado(int id) {
        this.id = id;
    }

    public MEstado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MEstado[] selectTodo() throws SQLException {
        sql = "SELECT * FROM estado ORDER BY id_estado;";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MEstado[] datos = new MEstado[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MEstado(rs.getInt("id_estado"), rs.getString("estado"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT * FROM estado WHERE id_estado='" + id + "';";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_estado");
            this.nombre = rs.getString("estado");
            con.desconectar();
        }
    }

    public MEstado[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT * FROM estado WHERE estado LIKE '%" + textoBuscar + "%' ORDER BY estado";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MEstado[] datos = new MEstado[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MEstado(rs.getInt("id_estado"), rs.getString("estado"));
            }
            
            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO estado(estado) "
                + "VALUES('" + nombre + "');";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE estado SET estado='" + nombre + "' WHERE id_estado='" + id + "';";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM estado WHERE id_estado='" + id + "';";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
