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
public class MAdminEscuela {

    private int id;
    private MEscuela escuela;
    private MPersonal personal;
    private MRecaudo recaudo;
    private MEstado estado;
    private MMunicipio municipio;
    private MParroquia parroquia;
    private Date fechaEntrega;

    private final BD con = new BD();
    private ResultSet rs;

    public MAdminEscuela() {
    }

    public MAdminEscuela(int id, MEscuela escuela, MRecaudo recaudo) {
        this.id = id;
        this.escuela = escuela;
        this.recaudo = recaudo;
    }

    public MAdminEscuela(int id, MEscuela escuela, MRecaudo recaudo, Date fechaEntrega) {
        this.id = id;
        this.escuela = escuela;
        this.recaudo = recaudo;
        this.fechaEntrega = fechaEntrega;
    }

    public MAdminEscuela(int id, MEscuela escuela, MPersonal personal) {
        this.id = id;
        this.escuela = escuela;
        this.personal = personal;
    }

    public MAdminEscuela(MEscuela escuela, MEstado estado, MMunicipio municipo, MParroquia parroquia) {
        this.escuela = escuela;
        this.estado = estado;
        this.municipio = municipo;
        this.parroquia = parroquia;
    }

    public MAdminEscuela(MPersonal personal) {
        this.personal = personal;
    }

    public MAdminEscuela(MRecaudo recaudo) {
        this.recaudo = recaudo;
    }

    /* Selects */
    public MAdminEscuela[] selectEntregaRecaudo(int id) throws SQLException {
        sql = "SELECT * FROM escuela as e INNER JOIN escuela_recaudo AS er ON "
                + "e.id_escuela=er.id_escuela INNER JOIN recaudo AS r ON"
                + "er.id_recaudo=r.id_recaudo INNER JOIN entrega_recaudo AS enr ON "
                + "enr.id_escuela_recaudo=er.id_escuela_recaudo "
                + "WHERE e.id_escuela=" + id + ";";
        con.conectar();
        rs = con.consultarBD();
        System.out.println("antes01");
        if (rs != null) {
            System.out.println("despues01");
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MAdminEscuela[] datos = new MAdminEscuela[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MAdminEscuela(
                        rs.getInt("id_escuela_recaudo"),
                        new MEscuela(
                                rs.getInt("id_escuela"),
                                rs.getString("nombre_escuela")
                        ),
                        new MRecaudo(
                                rs.getInt("id_recaudo"),
                                rs.getString("nombre_recaudo")
                        ),
                        rs.getDate("fecha_entrega")
                );
            }

            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
    }

    public MAdminEscuela[] selectRecaudo(int id, String option) throws SQLException {
        sql = "SELECT DISTINCT r.id_recaudo, r.nombre_recaudo FROM escuela AS"
                + " e INNER JOIN escuela_recaudo AS er ON "
                + "e.id_escuela=er.id_escuela INNER JOIN recaudo AS r ON "
                + "er.id_recaudo=r.id_recaudo ";
        if ("Mensual".equals(option) || "Trimestral".equals(option) || "Anual".equals(option)) {
            sql += "WHERE e.id_escuela=" + id + " AND r.frecuencia_entrega='" + option + "' ;";
        } else {
            sql += "WHERE e.id_escuela=" + id + " ORDER BY r.id_recaudo;";
        }
        con.conectar();
        rs = con.consultarBD();
        System.out.println("antes01");
        System.out.println(sql);
        if (rs != null) {
            System.out.println("despues01");
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MAdminEscuela[] datos = new MAdminEscuela[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MAdminEscuela(
                        new MRecaudo(
                                rs.getInt("id_recaudo"),
                                rs.getString("nombre_recaudo")
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

    public MAdminEscuela[] buscarEscuela(String escuela, int estado, int municipio, int parroquia) throws SQLException {
        sql = "SELECT id_escuela, nombre_escuela, turno_escuela, direccion_escuela, p.id_parroquia,"
                + " p.parroquia, m.id_municipio, m.municipio, es.id_estado, es.estado "
                + "FROM escuela AS e "
                + "INNER JOIN parroquia AS p "
                + "ON e.id_parroquia = p.id_parroquia "
                + "INNER JOIN municipio AS m "
                + "ON p.id_municipio = m.id_municipio "
                + "INNER JOIN estado AS es "
                + "ON m.id_estado = es.id_estado "
                + "WHERE e.nombre_escuela LIKE '%" + escuela + "%' and (es.id_estado=" + estado + ""
                + " or m.id_municipio=" + municipio + " or p.id_parroquia=" + parroquia + ")"
                + "ORDER BY nombre_escuela LIMIT 10;";
        con.conectar();
        rs = con.consultarBD();
        System.out.println(escuela + " " + estado + " " + " " + municipio + " " + parroquia);
        System.out.println("antes01");
        if (rs != null) {
            System.out.println("despues01");
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MAdminEscuela[] datos = new MAdminEscuela[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MAdminEscuela(
                        new MEscuela(rs.getInt("id_escuela"),
                                rs.getString("nombre_escuela")
                        ),
                        new MEstado(
                                rs.getInt("id_estado"),
                                rs.getString("estado")
                        ),
                        new MMunicipio(
                                rs.getInt("id_municipio"),
                                rs.getString("municipio")
                        ),
                        new MParroquia(
                                rs.getInt("id_parroquia"),
                                rs.getString("parroquia")
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

    public MAdminEscuela[] selectAsignarPersonal(int id) throws SQLException {
        sql = "SELECT * FROM personal AS p INNER JOIN escuela_personal AS ep ON "
                + "p.ci_personal=ep.ci_personal INNER JOIN escuela AS e ON "
                + "ep.id_escuela=e.id_escuela INNER JOIN cargo AS c ON "
                + "p.id_cargo=c.id_cargo "
                + "WHERE e.id_escuela=" + id + ";";
        con.conectar();
        rs = con.consultarBD();
        System.out.println("antes01");
        if (rs != null) {
            System.out.println("despues01");
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();

            MAdminEscuela[] datos = new MAdminEscuela[contFilas];

            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MAdminEscuela(
                        rs.getInt("id_escuela_personal"),
                        new MEscuela(
                                rs.getInt("id_escuela"),
                                rs.getString("nombre_escuela")
                        ),
                        new MPersonal(
                                rs.getString("ci_personal"),
                                rs.getString("p_nombre_personal"),
                                rs.getString("s_nombre_personal"),
                                new MCargo(
                                        rs.getString("nombre_cargo")
                                )
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

    public int countRecaudo(int id) throws SQLException {

        con.setSql("SELECT CAST(COUNT(*) AS Integer) AS total FROM escuela_recaudo "
                + "WHERE id_escuela=" + id + ";");
        con.conectar();
        rs = con.consultarBD();
        int datos;
        rs.next();
        datos = rs.getInt("total");
        return datos;
    }

    /* Inserts */
    public void insertEntregaRecaudo() {
        sql = "INSERT INTO entrega_recaudo "
                + "VALUES"
                + "(" + escuela.getId() + ", "
                + "" + fechaEntrega + ")";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    public void insertRecaudo() {
        sql = "INSERT INTO escuela_recaudo "
                + "(id_escuela ,id_recaudo)"
                + "VALUES"
                + "(" + escuela.getId() + ", "
                + "" + recaudo.getId() + ")";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    public void insertAsignarPersonal() {
        sql = "INSERT INTO escuela_personal "
                + "VALUES"
                + "(" + escuela.getId() + ", "
                + "'" + personal.getCedula() + "');";
        System.out.println(sql);
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    /* Updates */
    public void updateEntregaRecaudo() {
        sql = "";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    public void updateAsignarPersonal() {
        sql = "";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    /* Deletes */
    public void deleteEntregaRecaudo() {
        sql = "DELETE FROM escuela_recaudo"
                + " WHERE id_recaudo=" + recaudo.getId() + ";";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    public void deleteAsignarPersonal() {
        sql = "DELETE FROM escuela_personal"
                + " WHERE id_escuela_personal=" + id + ";";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    public void deleteRecaudo() {
        sql = "DELETE FROM escuela_recaudo "
                + "WHERE id_escuela=" + escuela.getId() + ""
                + " AND "
                + "id_recaudo=" + recaudo.getId() + ";";
        con.conectar();
        con.actualizarBD();
        con.desconectar();

    }

    /* Getter and Setter */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MEscuela getEscuela() {
        return escuela;
    }

    public void setEscuela(MEscuela escuela) {
        this.escuela = escuela;
    }

    public MPersonal getPersonal() {
        return personal;
    }

    public void setPersonal(MPersonal personal) {
        this.personal = personal;
    }

    public MRecaudo getRecaudo() {
        return recaudo;
    }

    public void setRecaudo(MRecaudo recaudo) {
        this.recaudo = recaudo;
    }

    public MEstado getEstado() {
        return estado;
    }

    public MMunicipio getMunicipio() {
        return municipio;
    }

    public MParroquia getParroquia() {
        return parroquia;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

}
