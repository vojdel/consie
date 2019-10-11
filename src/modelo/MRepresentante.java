package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MRepresentante extends MPersona {

    private final BD con = new BD();
    private ResultSet rs;

    public MRepresentante() {
    }

    public MRepresentante(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String genero, String telefono, String direccion) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public MRepresentante[] selectTodo() throws SQLException {
        sql = "SELECT * FROM representante ORDER BY p_nombre_representante;";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRepresentante[] datos = new MRepresentante[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRepresentante(rs.getString("ci_representante"), rs.getString("p_nombre_representante"), rs.getString("s_nombre_representante"), rs.getString("p_apellido_representante"), rs.getString("s_apellido_representante"), rs.getString("genero_representante"), rs.getString("tlf_representante"), rs.getString("direccion_representante"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT * FROM representante WHERE ci_representante='" + id + "';";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.cedula = rs.getString("ci_representante");
            this.primerNombre = rs.getString("p_nombre_representante");
            this.segundoNombre = rs.getString("s_nombre_representante");
            this.primerApellido = rs.getString("p_apellido_representante");
            this.segundoApellido = rs.getString("s_apellido_representante");
            this.genero = rs.getString("genero_representante");
            this.telefono = rs.getString("tlf_representante");
            this.direccion = rs.getString("direccion_representante");
            con.desconectar();
        }
    }

    public MRepresentante[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT * FROM representante WHERE s_nombre_representante LIKE '%" + textoBuscar + "%' ORDER BY p_nombre_representante";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MRepresentante[] datos = new MRepresentante[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MRepresentante(rs.getString("cedula_representante"), rs.getString("primer_nombre_representante"), rs.getString("segundo_nombre_representante"), rs.getString("primer_apellido_representante"), rs.getString("segundo_apellido_representante"), rs.getString("genero_representante"), rs.getString("telefono_representante"), rs.getString("direccion_representante"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO representante("
                + "ci_representante, "
                + "p_nombre_representante, "
                + "s_nombre_representante, "
                + "p_apellido_representante, "
                + "s_apellido_representante, "
                + "genero_representante, "
                + "tlf_representante, "
                + "direccion_representante"
                + ") "
                + "VALUES("
                + "'" + cedula + "', "
                + "'" + primerNombre + "', "
                + "'" + segundoNombre + "', "
                + "'" + primerApellido + "', "
                + "'" + segundoApellido + "', "
                + "'" + genero + "', "
                + "'" + telefono + "', "
                + "'" + direccion + "'"
                + ");";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE representante SET "
                + "p_nombre_representante='" + primerNombre + "', "
                + "s_nombre_representante='" + segundoNombre + "', "
                + "p_apellido_representante='" + primerApellido + "', "
                + "s_apellido_representante='" + segundoApellido + "', "
                + "genero_representante='" + genero + "', "
                + "tlf_representante='" + telefono + "', "
                + "direccion_representante='" + direccion + "' "
                + "WHERE ci_representante='" + cedula + "'"
                + ";";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM representante WHERE ci_representante='" + cedula + "';";
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

}