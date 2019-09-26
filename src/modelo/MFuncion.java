package modelo;

import general.BD;
import static general.BD.sql;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class MFuncion {
    
    private int id;
    private String nombre;
    
    private BD con = new BD();
    private ResultSet rs;

    public MFuncion() {}

    public MFuncion(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public MFuncion[] selectTodo() throws SQLException {
        sql = "SELECT * FROM funcion "
                + "ORDER BY funcion;";
        System.out.println(sql);
        con.conectar();
        rs = con.consultarBD();
        
        if(rs != null) {
            rs.last();
            int contFilas = rs.getRow();
            rs.beforeFirst();
            
            MFuncion[] datos = new MFuncion[contFilas];
            
            for (int i = 0; i < contFilas; i++) {
                rs.next();
                datos[i] = new MFuncion(rs.getInt("id_funcion"), rs.getString("funcion"));
            }
            
            con.desconectar();
            return datos;
        } else {
            con.desconectar();
            return null;
        }
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
    
}
