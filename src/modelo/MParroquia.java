package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MParroquia {
    
    private int id;
    private String nombre;
    private MMunicipio municipio;

    private BD con = new BD();
    private ResultSet rs;

    public MParroquia() {
    }

    public MParroquia(int id, String nombre, MMunicipio municipio) {
        this.id = id;
        this.nombre = nombre;
        this.municipio = municipio;
    }

    public MParroquia[] selectTodo() throws SQLException {
        sql = "SELECT parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio = municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado = estado.id_estado "
                + "ORDER BY estado;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MParroquia[] datos = new MParroquia[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MParroquia(rs.getInt("id_parroquia"), rs.getString("parroquia"), new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado"))));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio = municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado = estado.id_estado "
                + "WHERE id_parroquia='" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_parroquia");
            this.nombre = rs.getString("parroquia");
            this.municipio = new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado")));
            con.desconectar();
        }
    }

    public MParroquia[] selectPorMunicipio(int idMunicipio) throws SQLException {
        sql = "SELECT parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio=municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado=estado.id_estado "
                + "WHERE parroquia.id_municipio='" + idMunicipio + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MParroquia[] datos = new MParroquia[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MParroquia(rs.getInt("id_parroquia"), rs.getString("parroquia"), new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado"))));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public MParroquia[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio = municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado = estado.id_estado "
                + "WHERE parroquia LIKE '%" + textoBuscar + "%' "
                + "ORDER BY estado";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MParroquia[] datos = new MParroquia[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MParroquia(rs.getInt("id_parroquia"), rs.getString("parroquia"), new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado"))));
            }
            
            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }
    
    public void insert() {
        sql = "INSERT INTO parroquia (parroquia, id_municipio) "
                + "VALUES('" + nombre + "','" + municipio.getId() + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE parroquia SET "
                + "parroquia='" + nombre + "', "
                + "id_municipio='" + municipio.getId() + "' "
                + "WHERE id_parroquia='" + id + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM parroquia WHERE id_parroquia='" + id + "';";
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

    public MMunicipio getMunicipio() {
        return municipio;
    }

    public void setMunicipio(MMunicipio municipio) {
        this.municipio = municipio;
    }

}
