package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MRecaudo {

    private int id;
    private String nombre;
    private String frecuenciaEntrega;

    private BD con = new BD();
    private ResultSet rs;

    public MRecaudo() {
    }

    public MRecaudo(int id, String nombre, String frecuenciaEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.frecuenciaEntrega = frecuenciaEntrega;
    }

    public MRecaudo[] selectTodo() throws SQLException {
        con.setSql("SELECT * FROM recaudo ORDER BY nombre_recaudo;");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRecaudo[] datos = new MRecaudo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRecaudo(rs.getInt("id"), rs.getString("nombre_recaudo"), rs.getString("frecuencia_entrega_recaudo"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        con.setSql("SELECT * FROM recaudo WHERE id_recaudo='" + id + "';");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_recaudo");
            this.nombre = rs.getString("nombre_recaudo");
            this.frecuenciaEntrega = rs.getString("frecuencia_entrega_recaudo");
            con.desconectar();
        }
    }

    public MRecaudo[] buscar(String textoBuscar) throws SQLException {
        con.setSql("SELECT * FROM recaudo WHERE nombre_estado LIKE '%" + textoBuscar + "%' ORDER BY nombre_recaudo");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRecaudo[] datos = new MRecaudo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRecaudo(rs.getInt("id_recaudo"), rs.getString("nombre_recaudo"), rs.getString("frecuencia_entrega_recaudo"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        con.setSql("INSERT INTO recaudo(nombre_recaudo, frecuencia_entrega_recaudo) "
                + "VALUES('" + nombre + "', '" + frecuenciaEntrega + "');");
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        con.setSql("UPDATE recaudo SET "
                + "nombre='" + nombre + "', "
                + "frecuencia_entrega='" + frecuenciaEntrega + "' "
                        + "WHERE id_recaudo='" + id + "';");
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM recaudo WHERE id_recaudo='" + id + "';";
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

    public String getFrecuenciaEntrega() {
        return frecuenciaEntrega;
    }

    public void setFrecuenciaEntrega(String frecuenciaEntrega) {
        this.frecuenciaEntrega = frecuenciaEntrega;
    }

}
