package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author Daniel
 */
public class MFuncionario {

    private String cedula;
    private String primerNombre;
    private String segundoNombre;
    private String primerApellido;
    private String segundoApellido;
    private String genero;
    private Date fechaNacimiento;
    private String telefono;
    private String direccion;
    private MCargo cargo;
    private MUsuario usuario;

    private final BD con = new BD();
    private ResultSet rs;

    public MFuncionario() {
    }

    public MFuncionario(String cedula) {
        this.cedula = cedula;
    }

    public MFuncionario(String cedula, String primerNombre, String primerApellido) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.primerApellido = primerApellido;
    }

    public MFuncionario(String cedula, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String genero, Date fechaNacimiento, String telefono, String direccion, MCargo cargo, MUsuario usuario) {
        this.cedula = cedula;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.direccion = direccion;
        this.cargo = cargo;
        this.usuario = usuario;
    }

    public MFuncionario[] selectTodo() throws SQLException {
        sql = "SELECT "
                + "ci_funcionario, "
                + "p_nombre_fun, "
                + "s_nombre_fun, "
                + "p_apellido_fun, "
                + "s_apellido_fun, "
                + "genero, "
                + "f_nacimiento, "
                + "telefono, "
                + "direccion, "
                + "f.id_cargo, "
                + "nombre_cargo, "
                + "f.id_usuario "
                + "FROM funcionario AS f "
                + "INNER JOIN cargo AS c "
                + "ON f.id_cargo = c.id_cargo "
                + "ORDER BY ci_funcionario;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MFuncionario[] datos = new MFuncionario[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MFuncionario(
                        rs.getString("ci_funcionario"), 
                        rs.getString("p_nombre_fun"), 
                        rs.getString("s_nombre_fun"), 
                        rs.getString("p_apellido_fun"), 
                        rs.getString("s_apellido_fun"), 
                        rs.getString("genero"), 
                        rs.getDate("f_nacimiento"), 
                        rs.getString("telefono"), 
                        rs.getString("direccion"), 
                        new MCargo(
                                rs.getInt("id_cargo"), 
                                rs.getString("nombre_cargo")
                        ), 
                        new MUsuario(rs.getInt("id_usuario")
                        )
                );
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(String ci) throws SQLException {
        sql = "SELECT "
                + "ci_funcionario, "
                + "p_nombre_fun, "
                + "s_nombre_fun, "
                + "p_apellido_fun, "
                + "s_apellido_fun, "
                + "genero, "
                + "f_nacimiento, "
                + "telefono, "
                + "direccion, "
                + "id_cargo, "
                + "id_usuario "
                + "FROM funcionario "
                + "WHERE ci_funcionario = '" + ci + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.cedula = ci;
            this.primerNombre = rs.getString("p_nombre_fun");
            this.segundoNombre = rs.getString("s_nombre_fun");
            this.primerApellido = rs.getString("p_apellido_fun");
            this.segundoApellido = rs.getString("s_apellido_fun");
            this.genero = rs.getString("genero");
            this.fechaNacimiento = rs.getDate("f_nacimiento");
            this.telefono = rs.getString("telefono");
            this.direccion = rs.getString("direccion");
            this.cargo = new MCargo(rs.getInt("id_cargo"));
            this.usuario = new MUsuario(rs.getInt("id_usuario"));
            con.desconectar();
        }
    }

    public void selectPorUsuario(int id) throws SQLException {
        sql = "SELECT ci_funcionario, p_nombre_fun, p_apellido_fun "
                + "FROM funcionario "
                + "WHERE id_usuario = '" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.cedula = rs.getString("ci_funcionario");
            this.primerNombre = rs.getString("p_nombre_fun");
            this.primerApellido = rs.getString("p_apellido_fun");
            con.desconectar();
        }
    }

    public MFuncionario[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT "
                + "ci_funcionario, "
                + "p_nombre_fun, "
                + "s_nombre_fun, "
                + "p_apellido_fun, "
                + "s_apellido_fun, "
                + "genero, "
                + "f_nacimiento, "
                + "telefono, "
                + "direccion, "
                + "f.id_cargo, "
                + "nombre_cargo, "
                + "f.id_usuario "
                + "FROM funcionario AS f "
                + "INNER JOIN cargo AS c "
                + "ON f.id_cargo = c.id_cargo "
                + "WHERE ci_funcionario LIKE '%" + textoBuscar + "%' "
                + "ORDER BY ci_funcionario;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MFuncionario[] datos = new MFuncionario[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MFuncionario(
                        rs.getString("ci_funcionario"), 
                        rs.getString("p_nombre_fun"), 
                        rs.getString("s_nombre_fun"), 
                        rs.getString("p_apellido_fun"), 
                        rs.getString("s_apellido_fun"), 
                        rs.getString("genero"), 
                        rs.getDate("f_nacimiento"), 
                        rs.getString("telefono"), 
                        rs.getString("direccion"), 
                        new MCargo(
                                rs.getInt("id_cargo"), 
                                rs.getString("nombre_cargo")
                        ), 
                        new MUsuario(rs.getInt("id_usuario"))
                );
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO funcionario("
                + "ci_funcionario, "
                + "p_nombre_fun, "
                + "s_nombre_fun, "
                + "p_apellido_fun, "
                + "s_apellido_fun, "
                + "genero, "
                + "f_nacimiento, "
                + "telefono, "
                + "direccion, "
                + "id_cargo) "
                + "VALUES ("
                + "" + cedula + ", "
                + "'" + primerNombre + "', "
                + "'" + segundoNombre + "', "
                + "'" + primerApellido + "', "
                + "'" + segundoApellido + "', "
                + "'" + genero + "', "
                + "'" + fechaNacimiento + "', "
                + "'" + telefono + "', "
                + "'" + direccion + "', "
                + "" + cargo.getId() + ""
                + ");";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void crearUsuario() {
        sql = "UPDATE funcionario "
                + "SET id_usuario = '" + this.usuario.getId() + "' "
                + "WHERE ci_funcionario = '" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE funcionario "
                + "SET "
                + "p_nombre_fun = '" + primerNombre + "', "
                + "s_nombre_fun = '" + segundoNombre + "', "
                + "p_apellido_fun = '" + primerApellido + "', "
                + "s_apellido_fun = '" + segundoApellido + "',"
                + "genero = '" + genero + "', "
                + "f_nacimiento = '" + fechaNacimiento + "', "
                + "telefono = '" + telefono + "', "
                + "direccion = '" + direccion + "', "
                + "id_cargo = " + cargo.getId() + " "
                + "WHERE ci_funcionario = '" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM funcionario "
                + "WHERE ci_funcionario = '" + cedula + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public MCargo getCargo() {
        return cargo;
    }

    public void setCargo(MCargo cargo) {
        this.cargo = cargo;
    }

    public MUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(MUsuario usuario) {
        this.usuario = usuario;
    }

}
