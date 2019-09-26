package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Diego
 */
public class MEstudiante extends MPersona {

    private MEscuela escuela;

    private BD con = new BD();
    private ResultSet rs;

    public MEstudiante() {
    }

    public MEstudiante(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String genero, Date fechaNacimiento, String direccion, MEscuela escuela) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.direccion = direccion;
        this.escuela = escuela;
    }

    public MEstudiante[] selectTodo() throws SQLException {
        sql = "SELECT ci_estudiante, p_nombre_estudiante, s_nombre_estudiante, p_apellido_estudiante, s_apellido_estudiante, genero_estudiante, f_nacimiento_estudiante, direccion_estudiante, escuela.id_escuela, escuela.nombre_escuela "
                + "FROM estudiante "
                + "INNER JOIN escuela "
                + "ON estudiante.id_escuela = escuela.id_escuela "
                + "ORDER BY p_apellido_estudiante;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MEstudiante[] datos = new MEstudiante[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MEstudiante(rs.getString("ci_estudiante"), rs.getString("p_nombre_estudiante"), rs.getString("s_nombre_estudiante"), rs.getString("p_apellido_estudiante"), rs.getString("s_apellido_estudiante"), rs.getString("genero_estudiante"), rs.getDate("f_nacimiento_estudiante"), rs.getString("direccion_estudiante"), new MEscuela(rs.getInt("id_escuela")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT ci_estudiante, p_nombre_estudiante, s_nombre_estudiante, p_apellido_estudiante, s_apellido_estudiante, genero_estudiante, f_nacimiento_estudiante, direccion_estudiante, escuela.id_escuela, escuela.nombre_escuela "
                + "FROM estudiante "
                + "INNER JOIN escuela "
                + "ON estudiante.id_escuela = escuela.id_escuela "
                + "WHERE ci_estudiante='" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.cedula = rs.getString("ci_estudiante");
            this.primerNombre = rs.getString("p_nombre_estudiante");
            this.segundoNombre = rs.getString("s_nombre_estudiante");
            this.primerApellido = rs.getString("p_apellido_estudiante");
            this.segundoApellido = rs.getString("s_apellido_estudiante");
            this.genero = rs.getString("genero_estudiante");
            this.fechaNacimiento = rs.getDate("f_nacimiento_estudiante");
            this.direccion = rs.getString("direccion_estudiante");
            this.escuela = new MEscuela(rs.getInt("id_escuela"));
            con.desconectar();
        }
    }

    public MEstudiante[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT ci_estudiante, p_nombre_estudiante, s_nombre_estudiante, p_apellido_estudiante, s_apellido_estudiante, genero_estudiante, f_nacimiento_estudiante, direccion_estudiante, escuela.id_escuela, escuela.nombre_escuela "
                + "FROM estudiante "
                + "INNER JOIN escuela "
                + "ON estudiante.id_escuela = escuela.id_escuela "
                + "WHERE p_apellido_estudiante LIKE '%" + textoBuscar + "%' "
                + "ORDER BY p_apellido_estudiante";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MEstudiante[] datos = new MEstudiante[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MEstudiante(rs.getString("ci_estudiante"), rs.getString("p_nombre_estudiante"), rs.getString("s_nombre_estudiante"), rs.getString("p_apellido_estudiante"), rs.getString("s_apellido_estudiante"), rs.getString("genero_estudiante"), rs.getDate("f_nacimiento_estudiante"), rs.getString("direccion_estudiante"), new MEscuela(rs.getInt("id_escuela")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO estudiante (ci_estudiante, p_nombre_estudiante, s_nombre_estudiante, p_apellido_estudiante, s_apellido_estudiante, genero_estudiante, f_nacimiento_estudiante, direccion_estudiante, id_escuela) "
                + "VALUES ('" + cedula + "', '" + primerNombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + genero + "', '" + fechaNacimiento + "', '" + direccion + "', '" + escuela.getId() + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE estudiante SET "
                + "ci_estudiante='" + cedula + "', "
                + "p_nombre_estudiante='" + primerNombre + "', "
                + "s_nombre_estudiante='" + segundoNombre + "', "
                + "p_apellido_estudiante='" + primerApellido + "', "
                + "s_apellido_estudiante='" + segundoApellido + "' "
                + "genero_estudiante='" + genero + "' "
                + "f_nacimiento_estudiante='" + fechaNacimiento + "' "
                + "direccion_estudiante='" + direccion + "' "
                + "id_escuela='" + escuela.getId() + "' "
                + "WHERE ci_estudiante='" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM estudiante WHERE ci_estudiante='" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public MEscuela getEscuela() {
        return escuela;
    }

    public void setEscuela(MEscuela escuela) {
        this.escuela = escuela;
    }

}
