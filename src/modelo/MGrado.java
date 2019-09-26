/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Usuario
 */
public class MGrado {
   private int id;
    private String nombre;

    private BD con = new BD();
    private ResultSet rs;

    public MGrado() {
    }

    public MGrado(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public MGrado[] selectTodo() throws SQLException {
        sql = "SELECT * FROM grado ORDER BY nombre_grado;";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MGrado[] datos = new MGrado[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MGrado(rs.getInt("id_grado"), rs.getString("nombre_grado"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT * FROM grado WHERE id_grado=" + id + ";";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_grado");
            this.nombre = rs.getString("nombre_grado");
            con.desconectar();
        }
    }

    public MGrado[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT * FROM grado WHERE nombre_gradoLIKE '%" + textoBuscar + "%' ORDER BY nombre_grado";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MGrado[] datos = new MGrado[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MGrado(rs.getInt("id_grado"), rs.getString("nombre_grado"));
            }
            
            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO grado(nombre_grado) "
                + "VALUES('" + nombre + "');";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE grado SET nombre_grado='" + nombre + "' WHERE id_grado='" + id + "';";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM grado WHERE id_grado='" + id + "';";
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

