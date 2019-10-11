package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MIndicador {

    private int id;
    private String nombre;
    private int prioridad;

    private BD con = new BD();
    private ResultSet rs;

    public MIndicador() {
    }

    public MIndicador(int id, String nombre, int prioridad) {
        this.id = id;
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public MIndicador[] selectTodo() throws SQLException {
        sql = "SELECT id_indicador, nombre_indicador, prioridad_indicador "
                + "FROM indicador "
                + "ORDER BY nombre_indicador;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MIndicador[] datos = new MIndicador[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MIndicador(rs.getInt("id_indicador"), rs.getString("nombre_indicador"), rs.getInt("prioridad_indicador"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT id_indicador, nombre_indicador, prioridad_indicador "
                + "FROM indicador "
                + "WHERE id_indicador = '" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_indicador");
            this.nombre = rs.getString("nombre_indicador");
            this.prioridad = rs.getInt("prioridad_indicador");

            con.desconectar();
        }
    }
    
    public MIndicador[] buscar(String texto) throws SQLException {
        sql = "SELECT id_indicador, nombre_indicador, prioridad_indicador "
                + "FROM indicador "
                + "WHERE nombre_indicador = '%"+texto+"%' "
                + "ORDER BY nombre_indicador;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();
        
        if(rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();
            
            MIndicador[] datos = new MIndicador[contFilas];
            
            for(int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MIndicador(rs.getInt("id_indicador"), rs.getString("nombre_indicador"), rs.getInt("prioridad_indicador"));
            }
            
            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO indicador(nombre_indicador, prioridad_indicador) "
                + "VALUES ('" + nombre + "', '" + prioridad + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE indicador SET "
                + "nombre_indicador = '" + nombre + "', "
                + "prioridad_indicador = '" + prioridad + "' "
                + "WHERE id_indicador = '" + id + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM indicador "
                + "WHERE id_indicador = '" + id + "';";
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

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

}
