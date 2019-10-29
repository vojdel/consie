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
    private int numFrecuencia;

    private BD con = new BD();
    private ResultSet rs;

    public MRecaudo() {
    }

    public MRecaudo(int id) {
        this.id = id;
    }

    public MRecaudo(int id, String nombre, String frecuenciaEntrega) {
        this.id = id;
        this.nombre = nombre;
        this.frecuenciaEntrega = frecuenciaEntrega;
    }

    public MRecaudo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MRecaudo[] selectTodo() throws SQLException {
        con.setSql("SELECT * FROM recaudo ORDER BY id_recaudo;");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRecaudo[] datos = new MRecaudo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRecaudo(rs.getInt("id_recaudo"), rs.getString("nombre_recaudo"), rs.getString("frecuencia_entrega"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        con.setSql("SELECT * FROM recaudo WHERE id_recaudo='" + id + "' ORDER BY id_recaudo;");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_recaudo");
            this.nombre = rs.getString("nombre_recaudo");
            this.frecuenciaEntrega = rs.getString("frecuencia_entrega");
            this.numFrecuencia = rs.getInt("num_frecuencia");
            con.desconectar();
        }
    }

    public MRecaudo[] selecionaTodo() throws SQLException {
        con.setSql("SELECT * FROM recaudo ORDER BY id_recaudo;");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRecaudo[] datos = new MRecaudo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRecaudo(rs.getInt("id_recaudo"), rs.getString("nombre_recaudo"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public int count() throws SQLException {
        con.setSql("SELECT CAST(COUNT(id_recaudo) AS Integer) AS total FROM recaudo");
        con.conectar();
        rs = con.consultarBD();
        int datos;
        rs.next();
        datos = rs.getInt("total");
        return datos;
    }

    public MRecaudo[] buscar(String textoBuscar) throws SQLException {
        con.setSql("SELECT * FROM recaudo WHERE nombre_recaudo LIKE '%" + textoBuscar + "%' ORDER BY nombre_recaudo");
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRecaudo[] datos = new MRecaudo[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRecaudo(rs.getInt("id_recaudo"), rs.getString("nombre_recaudo"), rs.getString("frecuencia_entrega"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        con.setSql("INSERT INTO recaudo(nombre_recaudo, frecuencia_entrega, num_frecuencia) "
                + "VALUES('" + nombre + "', '" + frecuenciaEntrega + "', " + numFrecuencia + ");");
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        con.setSql("UPDATE recaudo SET "
                + "nombre_recaudo='" + nombre + "', "
                + "frecuencia_entrega='" + frecuenciaEntrega + "' ,"
                + "num_frecuencia='" + numFrecuencia + "' "
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

    public int getNumFrecuencia() {
        return numFrecuencia;
    }

    public void setNumFrecuencia(int numFrecuencia) {
        this.numFrecuencia = numFrecuencia;
    }

}
