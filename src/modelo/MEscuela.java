package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MEscuela {

    private int id;
    private String nombre;
    private String turno;
    private String direccion;
    private MParroquia parroquia;

    private BD con = new BD();
    private ResultSet rs;

    public MEscuela() {
    }

    public MEscuela(int id) {
        this.id = id;
    }

    public MEscuela(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public MEscuela(int id, String nombre, String turno, String direccion, MParroquia parroquia) {
        this.id = id;
        this.nombre = nombre;
        this.turno = turno;
        this.direccion = direccion;
        this.parroquia = parroquia;
    }

    public MEscuela[] selectTodo() throws SQLException {
        sql = "SELECT id_escuela, nombre_escuela, turno_escuela, direccion_escuela, parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM escuela "
                + "INNER JOIN parroquia "
                + "ON escuela.id_parroquia = parroquia.id_parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio = municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado = estado.id_estado "
                + "ORDER BY nombre_escuela;";
        /* Para mostrar como ejemplo en el salon
        sql = "SELECT * FROM view_escuela;"; */
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MEscuela[] datos = new MEscuela[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MEscuela(rs.getInt("id_escuela"), rs.getString("nombre_escuela"), rs.getString("turno_escuela"), rs.getString("direccion_escuela"), new MParroquia(rs.getInt("id_parroquia"), rs.getString("parroquia"), new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado")))));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void select(int id) throws SQLException {
        sql = "SELECT id_escuela, nombre_escuela, turno_escuela, direccion_escuela, parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM escuela "
                + "INNER JOIN parroquia "
                + "ON escuela.id_parroquia = parroquia.id_parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio = municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado = estado.id_estado "
                + "WHERE id_escuela='" + id + "';";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.next();
            this.id = rs.getInt("id_escuela");
            this.nombre = rs.getString("nombre_escuela");
            this.turno = rs.getString("turno_escuela");
            this.direccion = rs.getString("direccion_escuela");
            this.parroquia = new MParroquia(rs.getInt("id_parroquia"), rs.getString("parroquia"), new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado"))));
            con.desconectar();
        }
    }

    public MEscuela[] buscar(String textoBuscar) throws SQLException {
        sql = "SELECT id_escuela, nombre_escuela, turno_escuela, direccion_escuela, parroquia.id_parroquia, parroquia.parroquia, municipio.id_municipio, municipio.municipio, estado.id_estado, estado.estado "
                + "FROM escuela "
                + "INNER JOIN parroquia "
                + "ON escuela.id_parroquia = parroquia.id_parroquia "
                + "INNER JOIN municipio "
                + "ON parroquia.id_municipio = municipio.id_municipio "
                + "INNER JOIN estado "
                + "ON municipio.id_estado = estado.id_estado "
                + "WHERE nombre_escuela LIKE '%" + textoBuscar + "%' "
                + "ORDER BY nombre_escuela";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();

        if (rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MEscuela[] datos = new MEscuela[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MEscuela(rs.getInt("id_escuela"), rs.getString("nombre_escuela"), rs.getString("turno_escuela"), rs.getString("direccion_escuela"), new MParroquia(rs.getInt("id_parroquia"), rs.getString("parroquia"), new MMunicipio(rs.getInt("id_municipio"), rs.getString("municipio"), new MEstado(rs.getInt("id_estado"), rs.getString("estado")))));
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public void insert() {
        sql = "INSERT INTO escuela(nombre_escuela, turno_escuela, direccion_escuela, id_parroquia) "
                + "VALUES('" + nombre + "', '" + turno + "', '" + direccion + "', '" + parroquia.getId() + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void update() {
        sql = "UPDATE escuela SET "
                + "nombre_escuela='" + nombre + "', "
                + "turno_escuela='" + turno + "', "
                + "direccion_escuela='" + direccion + "', "
                + "id_parroquia='" + parroquia.getId() + "' "
                + "WHERE id_escuela='" + id + "';";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();
    }

    public void delete() {
        sql = "DELETE FROM escuela WHERE id_escuela='" + id + "';";
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

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public MParroquia getParroquia() {
        return parroquia;
    }

    public void setParroquia(MParroquia parroquia) {
        this.parroquia = parroquia;
    }

}
