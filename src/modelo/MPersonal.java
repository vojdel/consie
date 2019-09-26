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
public class MPersonal extends MPersona {

    private MCargo cargo;

    private final BD con = new BD();
    private ResultSet rs;

    public MPersonal() {

    }

    public MPersonal(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String genero, String telefono, String direccion) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public MPersonal[] selectTodo() throws SQLException {
        sql = "SELECT ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, direccion_personal, telf_personal "
                + "FROM personal "
                + "ORDER BY p_apellido_personal;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MPersonal[] datos = new MPersonal[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MPersonal(rs.getString("ci_personal"), rs.getString("p_nombre_personal"), rs.getString("s_nombre_personal"), rs.getString("p_apellido_personal"), rs.getString("s_apellido_personal"), rs.getString("genero_personal"), rs.getString("direccion_personal"), rs.getString("telf_personal"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, f_nacimiento_personal, direccion_personal, telf_personal "
                + "FROM personal "
                + "ORDER BY p_apellido_personal;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.cedula = rs.getString("ci_personal");
            this.primerNombre = rs.getString("p_nombre_personal");
            this.segundoNombre = rs.getString("s_nombre_personal");
            this.primerApellido = rs.getString("p_apellido_personal");
            this.segundoApellido = rs.getString("s_apellido_personal");
            this.genero = rs.getString("genero_personal");
            this.fechaNacimiento = rs.getDate("f_nacimiento_personal");
            this.direccion = rs.getString("direccion_personal");
            this.telefono = rs.getString("telf_personal");
            con.desconectar();
        }
    }
    
     public MPersonal[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, f_nacimiento_personal, direccion_personal, telf_personal "
                + "FROM personal "
                + "WHERE p_apellido_estudiante LIKE '%" + textoBuscar + "%' "
                + "ORDER BY p_apellido_personal;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MPersonal[] datos = new MPersonal[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MPersonal(rs.getString("ci_personal"), rs.getString("p_nombre_personal"), rs.getString("s_nombre_personal"), rs.getString("p_apellido_personal"), rs.getString("s_apellido_personal"), rs.getString("genero_personal"), rs.getString("direccion_personal"), rs.getString("telf_personal"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }
     
     public void insert() {
        sql = "INSERT INTO personal (ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, direccion_personal, telf_personal) "
                + "VALUES ('" + cedula + "', '" + primerNombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + genero + "', '" + direccion + "', '" + telefono + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }
     
      public void update() {
        sql = "UPDATE personal SET "
                + "ci_personal='" + cedula + "', "
                + "p_nombre_personal='" + primerNombre + "', "
                + "s_nombre_personal='" + segundoNombre + "', "
                + "p_apellido_personal='" + primerApellido + "', "
                + "s_apellido_personal='" + segundoApellido + "' "
                + "genero_personal='" + genero + "' "
                + "direccion_personal='" + direccion + "' "
                + "telf_personal='" + telefono + "' "
                + "WHERE ci_personal='" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM personal WHERE ci_personal='" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

}
