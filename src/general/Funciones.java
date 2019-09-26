package general;

import java.awt.event.KeyEvent;
import javax.swing.JTable;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Daniel
 */
public class Funciones {

    public void ocultarColumnas(JTable tbl, int columna[]) {
        for (int i = 0; i < columna.length; i++) {
            tbl.getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getColumnModel().getColumn(columna[i]).setMinWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
            tbl.getTableHeader().getColumnModel().getColumn(columna[i]).setMaxWidth(0);
        }
    }

    public String encriptar(String textoSinEncriptar) {
        String textoEncriptadoConMD5 = DigestUtils.md5Hex(textoSinEncriptar);
        return textoEncriptadoConMD5;
    }

    public void soloLetras(KeyEvent e) {
        char c = e.getKeyChar();
        if (Character.isDigit(c)) {
            e.consume();
            System.out.println(c);
        }
    }

    public void soloNumeros(KeyEvent e) {
        char c = e.getKeyChar();
        if (!Character.isDigit(c)) {
            e.consume();
            System.out.println(c);
        }
    }

    public void limitar(KeyEvent e, String campo, int cantidad) {
        char c = e.getKeyChar();
        int tamanio = campo.length();
        if (tamanio >= cantidad) {
            e.consume();
            System.out.println(c);
        }
    }

}
