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
import java.util.Date;

/**
 *
 * @author Usuario
 */
public class MAnioEscolar {

    private final BD con = new BD();

    private ResultSet rs;
    private int id;
    private Date anioEscolarC;
    private Date anioEscolarF;

    public MAnioEscolar() {
    }

    public MAnioEscolar(int id, Date anioEscolarC, Date anioEscolarF) {
        this.id = id;
        this.anioEscolarC = anioEscolarC;
        this.anioEscolarF = anioEscolarF;
    }

    public MAnioEscolar[] selectTodo() throws SQLException {
        sql = "SELECT * FROM anio_escolar";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MAnioEscolar[] datos = new MAnioEscolar[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MAnioEscolar(rs.getInt("id_anio_escolar"), rs.getDate("anio_escolar_c"), rs.getDate("anio_escolar_f"));
            }

            con.desconectar();
            return datos;
        } else {
            return null;
        }
    }

    public MAnioEscolar[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT * FROM anio_escolar WHERE anio_escolar LIKE '"+textoBuscar+"'";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MAnioEscolar[] datos = new MAnioEscolar[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MAnioEscolar(rs.getInt("id_anio_escolar"), rs.getDate("anio_escolar_c"), rs.getDate("anio_escolar_f"));
            }

            con.desconectar();
            return datos;
        } else {
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT * FROM anio_escolar WHERE id_anio_escolar='" + id + "';";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_anio_escolar");
            this.anioEscolarC = rs.getDate("anio_escolar_c");
            this.anioEscolarF = rs.getDate("anio_escolar_f");
            con.desconectar();
        }
    }

    public void insert() {
        sql = "INSERT INTO anio_escolar("
                + "id_anio_escolar, "
                + "anio_escolar_c, "
                + "anio_escolar_f"
                + ") VALUES("
                + "" + id + ", "
                + "'" + anioEscolarC + "' ,"
                + "'" + anioEscolarF + "' )";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE anio_escolar SET "
                + "anio_escolar_c='" + anioEscolarC + "', "
                + "anio_escolar_f='" + anioEscolarF + "' "
                + "WHERE id_anio_Escolar='" + id + "';";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM anio_escolar WHERE id_anio_escolar='" + id + "';";
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

    public Date getAnioEscolarC() {
        return anioEscolarC;
    }

    public void setAnioEscolarC(Date anioEscolarC) {
        this.anioEscolarC = anioEscolarC;
    }

    public Date getAnioEscolarF() {
        return anioEscolarF;
    }

    public void setAnioEscolarF(Date anioEscolarF) {
        this.anioEscolarF = anioEscolarF;
    }

}
