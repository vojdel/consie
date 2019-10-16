package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author carlos
 */
public class MSeccion {

    private int id;
    private int id_grado;
    private int n_estudiantes;
    private String nombre_seccion;
    private String grado;
    private ResultSet rs;
    public final BD con = new BD();
    
    public MSeccion(){
    }
    
    public MSeccion(int id, String nombre_seccion) {
        this.id = id;
        this.nombre_seccion = nombre_seccion;
    }
    
    public MSeccion(int id, String nombre_seccion, String grado, int n_estudiantes) {
        this.id = id;
        this.nombre_seccion = nombre_seccion;
        this.grado = grado;
        this.n_estudiantes = n_estudiantes;
    }
    
    public MSeccion[] selectTodo() throws SQLException {
        sql = "SELECT * FROM seccion AS s INNER JOIN grado AS g ON s.id_grado2=g.id_grado "
                + "ORDER BY id_seccion;";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MSeccion[] datos = new MSeccion[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MSeccion(rs.getInt("id_seccion"), rs.getString("nombre_seccion"), rs.getString("nombre_grado"), rs.getInt("n_estudiantes"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public MSeccion[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT * FROM seccion AS s INNER JOIN grado AS g ON s.id_grado2=g.id_grado "
                + "WHERE nombre_seccion LIKE '%" + textoBuscar + "%' ORDER BY nombre_grado;";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();
            MSeccion[] datos = new MSeccion[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MSeccion(rs.getInt("id"), rs.getString("nombre_seccion"), rs.getString("nombre_grado"), rs.getInt("n_estudiantes"));
            }

            con.desconectar();
            return datos;

        } else {
            con.desconectar();
            return null;
        }

    }

    public void select(int id) throws SQLException {
        sql = "SELECT * FROM seccion AS s INNER JOIN grado AS g ON s.id_grado2=g.id_grado "
                + "WHERE id_seccion=" + id + ";";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_seccion");
            this.nombre_seccion = rs.getString("nombre_seccion");
            this.grado = rs.getString("nombre_grado");
            this.n_estudiantes = rs.getInt("n_estudiantes");
            con.desconectar();
        }

    }
    
    public void insert() {
        sql = "INSERT INTO seccion(nombre_seccion, id_grado2, n_estudiantes)"
                + " VALUES('" + nombre_seccion + "', "+id_grado+", "+n_estudiantes+");";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE seccion SET nombre_seccion='" + nombre_seccion + "',"
                + " id_grado2="+id_grado+", n_estudiantes="+n_estudiantes+" WHERE id_seccion='" + id + "';";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM seccion WHERE id_seccion='" + id + "';";
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

    public String getSeccion() {
        return nombre_seccion;

    }

    public void setSeccion(String nombre_seccion) {
        this.nombre_seccion = nombre_seccion;
    }

    public int getId_grado() {
        return id_grado;
    }

    public void setId_grado(int id_grado) {
        this.id_grado = id_grado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public int getN_estudiantes() {
        return n_estudiantes;
    }

    public void setN_estudiantes(int n_estudiantes) {
        this.n_estudiantes = n_estudiantes;
    }
    
}
