package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public MPersonal(String cedula, String primerNombre, String segundoNombre, MCargo cargo) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.cargo = cargo;
    }

    public MPersonal(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String genero, String telefono, String direccion, MCargo cargo) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cargo = cargo;
    }

    public MPersonal[] selectTodo() throws SQLException {
        sql = "SELECT * FROM view_personal;";
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
                datos[i] = new MPersonal(rs.getString("ci_personal"), rs.getString("p_nombre_personal"), rs.getString("s_nombre_personal"), rs.getString("p_apellido_personal"), rs.getString("s_apellido_personal"), rs.getString("genero_personal"), rs.getString("direccion_personal"), rs.getString("telf_personal"), new MCargo(rs.getString("nombre_cargo")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, direccion_personal, telf_personal, c.id_cargo "
                + "FROM personal AS p INNER JOIN cargo AS c ON p.id_cargo=c.id_cargo "
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
            this.direccion = rs.getString("direccion_personal");
            this.telefono = rs.getString("telf_personal");
            this.cargo = new MCargo(rs.getInt("id_cargo"));
            con.desconectar();
        }
    }

    public MPersonal[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, direccion_personal, telf_personal, c.nombre_cargo "
                + "FROM personal AS p INNER JOIN cargo AS c ON p.id_cargo=c.id_cargo "
                + "WHERE p_apellido_personal LIKE '%" + textoBuscar + "%' "
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
                datos[i] = new MPersonal(rs.getString("ci_personal"), rs.getString("p_nombre_personal"), rs.getString("s_nombre_personal"), rs.getString("p_apellido_personal"), rs.getString("s_apellido_personal"), rs.getString("genero_personal"), rs.getString("direccion_personal"), rs.getString("telf_personal"), new MCargo(rs.getString("nombre_cargo")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public MPersonal[] busquedaDinamica(String nacionalidad, String cedula) throws SQLException {
        sql = "SELECT ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, direccion_personal, telf_personal, c.nombre_cargo "
                + "FROM personal AS p INNER JOIN cargo AS c ON p.id_cargo=c.id_cargo "
                + "WHERE ci_personal LIKE '%"+ nacionalidad +"-" + cedula + "%' "
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
                datos[i] = new MPersonal(rs.getString("ci_personal"), rs.getString("p_nombre_personal"), rs.getString("s_nombre_personal"), rs.getString("p_apellido_personal"), rs.getString("s_apellido_personal"), rs.getString("genero_personal"), rs.getString("direccion_personal"), rs.getString("telf_personal"), new MCargo(rs.getString("nombre_cargo")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }    }

    public void insert() {
        sql = "INSERT INTO personal (ci_personal, p_nombre_personal, s_nombre_personal, p_apellido_personal, s_apellido_personal, genero_personal, direccion_personal, telf_personal, id_cargo) "
                + "VALUES ('" + cedula + "', '" + primerNombre + "', '" + segundoNombre + "', '" + primerApellido + "', '" + segundoApellido + "', '" + genero + "', '" + direccion + "', '" + telefono + "', '" + cargo.getId() + "');";
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
                + "s_apellido_personal='" + segundoApellido + "', "
                + "genero_personal='" + genero + "', "
                + "direccion_personal='" + direccion + "', "
                + "telf_personal='" + telefono + "', "
                + "id_cargo='" + cargo.getId() + "' "
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

    public MCargo getCargo() {
        return cargo;
    }

    public void setCargo(MCargo cargo) {
        this.cargo = cargo;
    }

}
