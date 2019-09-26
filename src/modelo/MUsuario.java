package modelo;

import consie.Consie;
import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Daniel
 */
public class MUsuario {

    private int id;
    private String usuario;
    private String contrasenia;
    private String preguntaSeguridad;
    private String respuestaSecreta;
    private MFuncionario funcionario;
    private MFuncion[] funciones;

    private String contrasenia2;

    private final BD con = new BD();
    private ResultSet rs;
    private final String p[] = new String[]{"Deporte Favorito", "¿Actividad Favorita?", "¿Nacimiento de tu Hija/Hijo?", "¿Cumpleaños de tu padre?", "¿Nombre de tu primera mascota?"};

    public MUsuario() {
    }

    public MUsuario(int id, String usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public MUsuario(int id) {
        this.id = id;
    }

    public MUsuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
    }

    public MUsuario(int id, String usuario, MFuncionario funcionario) {
        this.id = id;
        this.usuario = usuario;
        this.funcionario = funcionario;
    }

    public MUsuario(int id_usaurio, String usuario, String p_secreta, String r_secreta) {
        id = id_usaurio;
        this.usuario = usuario;
        this.preguntaSeguridad = p_secreta;
        this.respuestaSecreta = r_secreta;
    }

    public MUsuario[] selectTodo() throws SQLException {
        sql = "SELECT id_usuario, usuario, f.ci_funcionario, p_nombre_fun, s_nombre_fun, p_apellido_fun, s_apellido_fun "
                + "FROM usuario AS u "
                + "INNER JOIN funcionario AS f "
                + "ON u.ci_funcionario = f.ci_funcionario;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();
        System.out.println("antes01");
        if (rs != null) {System.out.println("despues01");
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MUsuario[] datos = new MUsuario[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MUsuario(rs.getInt("id_usuario"), rs.getString("usuario"));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT id_usuario, usuario, p_secreta, respuesta, id_funcion, f.funcion "
                + "FROM usuario AS u "
                + "INNER JOIN usuario_funcion AS uf "
                + "ON u.id_usuario=uf.id_usuario "
                + "INNER JOIN funcion AS f "
                + "ON uf.id_funcion=f.id_funcion "
                + "WHERE id_usuario=" + id + ";";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            id = id;
            this.usuario = rs.getString("usuario");
            this.preguntaSeguridad = rs.getString("p_secreta");
            this.respuestaSecreta = rs.getString("respuesta");
            con.desconectar();
        }
    }

    public MUsuario[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT id_usuario, usuario, ci_funcionario as cedula, (SELECT \n"
                + "concat(p_nombre_fun, ' ', s_nombre_fun, ' ', p_apellido_fun, ' ', s_apellido_fun )\n"
                + " FROM usuario as u inner join funcionario as f\n"
                + "on u.funcionario=f.ci_funcionario AND u.usuario LIKE '%" + textoBuscar + "%') as funcionario, contrasenia, p_secreta, respuesta FROM usuario u inner join funcionario as f\n"
                + "on u.funcionario=f.ci_funcionario\n "
                + "WHERE usuario LIKE '%" + textoBuscar + "%' ORDER BY id_usuario DESC";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MUsuario[] datos = new MUsuario[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                if (rs.getInt("cedula") == 0) {
                    datos[i] = new MUsuario(rs.getInt("id_usuario"), rs.getString("usuario"), rs.getString("p_secreta"), rs.getString("respuesta"));
                } else {
                    datos[i] = new MUsuario(rs.getInt("id_usuario"), rs.getString("usuario"), rs.getString("p_secreta"), rs.getString("respuesta"));
                }
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public Boolean iniciarSesion(String usuario, String password) throws SQLException {
        Boolean sw;
        sql = "SELECT usuario.id_usuario, usuario "
                + "FROM usuario "
                + "WHERE usuario='" + usuario + "' AND contrasenia='" + password + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_usuario");
            this.usuario = rs.getString("usuario");
            sw = true;
            System.out.println("Sesión iniciada");
        } else {
            sw = false;
            System.out.println("Usuario no existe");
        }
        con.desconectar();
        return sw;
    }

    public void selectPermisos() throws SQLException {
        sql = "SELECT uf.id_funcion, funcion "
                + "FROM usuario_funcion AS uf "
                + "INNER JOIN funcion "
                + "ON uf.id_funcion = funcion.id_funcion "
                + "WHERE id_usuario = " + id + ";";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            funciones = new MFuncion[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                funciones[i] = new MFuncion(rs.getInt("id_funcion"), rs.getString("funcion"));
            }

            con.desconectar();
        }
    }

    public void permisos(String textoBuscar) throws SQLException {
        sql = "SELECT u.id_usuario as id, usuario, funcion "
                + "FROM usuario AS u "
                + "INNER JOIN usuario_funcion as uf "
                + "ON u.id_usuario = uf.id_usuario "
                + "INNER JOIN funcion AS f "
                + "ON uf.id_funcion = f.id_funcion "
                + "WHERE u.usuario = '" + textoBuscar + "';";
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.setId(rs.getInt("id"));
            this.setUsuario(rs.getString("usuario"));
            con.desconectar();
        }
    }

    /*Funcion*/
    public DefaultComboBoxModel obtenerFuncion() {
        sql = "SELECT * FROM funcion ORDER BY id_funcion;";
        con.conectar();
        rs = con.consultarBD();
        DefaultComboBoxModel listaModelo = new DefaultComboBoxModel();
        listaModelo.addElement("Seleccione");

        try {
            while (rs.next()) {
                listaModelo.addElement(rs.getString("funcion"));
            }
            con.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return listaModelo;
    }

    public HashMap<String, Integer> hashFuncion() {
        sql = "SELECT * FROM funcion ORDER BY id_funcion;";
        con.conectar();
        rs = con.consultarBD();
        HashMap<String, Integer> hashModelo = new HashMap<String, Integer>();

        try {
            while (rs.next()) {
                hashModelo.put(rs.getString("funcion"), rs.getInt("id_funcion"));
            }
            con.desconectar();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return hashModelo;
    }

    public void insert() throws SQLException {
        sql = "SELECT insertarusuario("
                + "    '" + usuario + "',"
                + "    '" + contrasenia + "',"
                + "    '" + preguntaSeguridad + "',"
                + "    '" + respuestaSecreta + "',"
                + ");";
        sql = "INSERT INTO usuario (usuario, funcionario, contrasenia, p_secreta, respuesta) "
                + "VALUES ('" + usuario + "', '" + contrasenia + "', '" + preguntaSeguridad + "', '" + respuestaSecreta + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();

        sql = "SELECT id_usuario "
                + "FROM usuario;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            id = rs.getInt("id_usuario");
        }

        for (MFuncion funcionesX : funciones) {
            sql = "INSERT INTO usuario_funcion (id_usuario, id_funcion) "
                    + "VALUES ('" + id + "', '" + funcionesX.getId() + "')";
            System.out.println(sql);
            con.conectar();
            con.actualizarBD();
        }

        con.desconectar();
    }

    public void update() {
        sql = "SELECT ModeficarUsuario("
                + "" + id + ", '" + usuario + "', '" + preguntaSeguridad + "', '" + respuestaSecreta + "' "
                + ");";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void updatePass() {
        sql = "UPDATE usuario SET contrasenia='" + contrasenia + "' "
                + "WHERE id_usuario = " + id + ";";

        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "SELECT EliminarUsuario(" + id + ", " + Consie.usuario2[0] + ");";
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getPreguntaSeguridad() {
        return preguntaSeguridad;
    }

    public void setPreguntaSeguridad(String pregunta) {
        this.preguntaSeguridad = pregunta;
    }

    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }

    public void setRespuestaSecreta(String respuestaSecreta) {
        this.respuestaSecreta = respuestaSecreta;
    }

    public MFuncionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(MFuncionario funcionario) {
        this.funcionario = funcionario;
    }

    public MFuncion[] getFunciones() {
        return funciones;
    }

    public void setFunciones(MFuncion[] funciones) {
        this.funciones = funciones;
    }

}
