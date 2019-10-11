package controlador;

import static consie.Consie.app;
import static consie.Consie.usuarioX;
import static consie.Consie.ventana;
import general.Funciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.MUsuario;
import vista.VSesion;

/**
 *
 * @author Diego, Daniel
 */
public class CInicioSesion implements ActionListener, MouseListener {

    VSesion vista;
    Funciones f = new Funciones();

    public CInicioSesion() {
        System.out.println("Iniciando CInicioSesion");
        vista = new VSesion();
        usuarioX = new MUsuario();

        ventana.setSize(444, 442);
        ventana.getContentPane().removeAll();
        ventana.setTitle("Iniciar Sesión");
        vista.setBounds(0, 0, 444, 442);
        ventana.getContentPane().add(vista);
        ventana.setLocationRelativeTo(null);
        addListener();
    }

    private void addListener() {
        vista.getTxtUsuario().addActionListener(this);
        vista.getTxtClave().addActionListener(this);
        vista.getBtnIniciarSesion().addActionListener(this);
        vista.getLabelCerrar().addMouseListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnIniciarSesion()
                || ae.getSource() == vista.getTxtUsuario()
                || ae.getSource() == vista.getTxtClave()) {
            iniciarSesion();
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.getLabelCerrar()) {
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    private void iniciarSesion() {
        Boolean respuesta;

        // Si algún campo está vacío
        if (vista.getTxtUsuario().getText().isEmpty() || vista.getTxtClave().getText().isEmpty()) {
            String msj = "";

            if (vista.getTxtUsuario().getText().isEmpty()) {
                msj += "CAMPO USUARIO VACÍO \n";
            }

            if (vista.getTxtClave().getText().isEmpty()) {
                msj += "CAMPO CONTRASEÑA VACÍO \n";
            }

            JOptionPane.showMessageDialog(null, msj, "ERROR!", JOptionPane.ERROR_MESSAGE);
        } else {
            String nombreUsuario = vista.getTxtUsuario().getText();
            String clave = f.encriptar(vista.getTxtClave().getText());

            try {
                respuesta = usuarioX.iniciarSesion(nombreUsuario, clave);

                if (respuesta != false) {
                    try {
                        usuarioX.selectPermisos();
                        app = new CVentana();
                    } catch (SQLException ex) {
                        Logger.getLogger(CInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    // Si la contraseña o el usuarioX son incorrectos
                    JOptionPane.showMessageDialog(null, "¡USUARIO (" + vista.getTxtUsuario().getText() + ") O CONTERASEÑA INCORRECTA!", "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(CInicioSesion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
