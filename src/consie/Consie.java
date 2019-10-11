package consie;

import controlador.CInicioSesion;
import javax.swing.JFrame;
import modelo.MFuncionario;
import modelo.MUsuario;

/**
 *
 * @author Diego
 */
public class Consie {

    public static JFrame ventana;
    public static Object app;
    public static MUsuario usuarioX;
    public static MFuncionario funcionarioX;
    public static Object[] usuario2 = new Object[3];

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ventana = new JFrame();
        ventana.setLayout(null);
        ventana.setUndecorated(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        app = new CInicioSesion();

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
}
