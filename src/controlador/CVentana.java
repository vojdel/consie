package controlador;

import static consie.Consie.app;
import static consie.Consie.funcionarioX;
import static consie.Consie.usuarioX;
import static consie.Consie.ventana;
import general.Menu;
import general.MenuDinam;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import modelo.MFuncionario;
import vista.VVentana;

/**
 *
 * @author Eggo
 */
public class CVentana implements MouseListener {

    public static VVentana marco;
    public static JPanel panelPrincipal;

    public VVentana vista;

    Menu menu;
    MenuDinam menuDinam;

    public CVentana() {
        System.out.println("Iniciando CVentana");
        vista = new VVentana();
        marco = vista;

        ventana.setSize(1000, 700);
        ventana.getContentPane().removeAll();
        vista.setBounds(0, 0, 1000, 700);
        ventana.add(vista);

        funcionarioX = new MFuncionario();

        try {
            funcionarioX.selectPorUsuario(usuarioX.getId());
        } catch (SQLException ex) {
            Logger.getLogger(CVentana.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (funcionarioX.getPrimerNombre() != null) {
            vista.getNonUsuario().setText(funcionarioX.getPrimerNombre().concat(" ").concat(funcionarioX.getPrimerApellido()));
        } else {
            vista.getNonUsuario().setText(usuarioX.getUsuario());
        }

        menuDinam = new MenuDinam();
        menuDinam.generarMenu(vista.getPanelMenu());

        ventana.setLocationRelativeTo(null);
        addListener();
        app = new CInicio();
    }

    private void addListener() {
        vista.getLabelCerrar().addMouseListener(this);
        vista.getLabelLogout().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.getLabelCerrar()) {
            System.exit(0);
        } else if (me.getSource() == vista.getLabelLogout()) {
            usuarioX = null;
            app = new CInicioSesion();
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

}
