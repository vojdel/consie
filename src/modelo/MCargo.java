package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MCargo {

    private int id;
    private String nombre;

    private BD con = new BD();
    private ResultSet rs;

    public MCargo() {
    }

    public MCargo(int id) {
        this.id = id;
    }

    public MCargo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MCargo[] selectTodo() throws SQLException {
        sql = "SELECT * FROM cargo ORDER BY nombre_cargo;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MCargo[] datos = new MCargo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MCargo(rs.getInt("id_cargo"), rs.getString("nombre_cargo"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT * FROM cargo WHERE id_cargo='" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_cargo");
            this.nombre = rs.getString("nombre_cargo");
            con.desconectar();
        }
    }

    public MCargo[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT * FROM cargo WHERE nombre_cargo LIKE '%" + textoBuscar + "%' ORDER BY nombre_cargo";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MCargo[] datos = new MCargo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MCargo(rs.getInt("id_cargo"), rs.getString("nombre_cargo"));
            }
            
            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO cargo(nombre_cargo) "
                + "VALUES('" + nombre + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE cargo SET nombre_cargo='" + nombre + "' WHERE id_cargo='" + id + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM cargo WHERE id_cargo='" + id + "';";
        System.out.println(sql);
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
