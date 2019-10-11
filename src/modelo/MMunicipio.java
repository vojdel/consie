package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MMunicipio {

    private int id;
    private String nombre;
    private MEstado estado;

    private BD con = new BD();
    private ResultSet rs;

    public MMunicipio() {
    }

    public MMunicipio(int id, String nombre, MEstado estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public MMunicipio[] selectTodo() throws SQLException {
        sql = "SELECT municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado=estado.id_estado "
                + "ORDER BY estado;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MMunicipio[] datos = new MMunicipio[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado=estado.id_estado "
                + "WHERE id_municipio='" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_municipio");
            this.nombre = rs.getString("municipio");
            this.estado = new MEstado(rs.getInt("id_estado"), rs.getString("estado"));
            con.desconectar();
        }
    }

    public MMunicipio[] selectPorEstado(int idEstado) throws SQLException {
        sql = "SELECT municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado=estado.id_estado "
                + "WHERE municipio.id_estado='" + idEstado + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MMunicipio[] datos = new MMunicipio[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public MMunicipio[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado=estado.id_estado "
                + "WHERE municipio LIKE '%" + textoBuscar + "%' "
                + "ORDER BY estado";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MMunicipio[] datos = new MMunicipio[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado")));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO municipio (municipio, id_estado) VALUES ('" + nombre + "', '" + estado.getId() + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE municipio SET municipio='" + nombre + "', id_estado ='" + estado.getId() + "' WHERE id_municipio='" + id + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM municipio WHERE id_municipio='" + id + "';";
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

    public MEstado getEstado() {
        return estado;
    }

    public void setEstado(MEstado estado) {
        this.estado = estado;
    }

}
