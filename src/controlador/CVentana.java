package controlador;

import static consie.Consie.app;
import static consie.Consie.usuario;
import static consie.Consie.ventana;
import general.Menu;
import general.MenuDinam;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
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

        if (usuario.getFuncionario() != null) {
            vista.getNonUsuario().setText(usuario.getFuncionario().getPrimerNombre().concat(" ").concat(usuario.getFuncionario().getPrimerApellido()));
        } else {
            vista.getNonUsuario().setText(usuario.getUsuario());
        }

        /*if (ventana.getJMenuBar() == null) {
            menu = new Menu();
            ventana.setJMenuBar(menu.getMenuBar());
        }*/

        menuDinam = new MenuDinam();
        menuDinam.generarMenu(vista.getPanelMenu());

        ventana.setLocationRelativeTo(null);
        addListener();
        app = new CInicio();
    }

    private void addListener() {
        vista.getLabelCerrar().addMouseListener(this);
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

}
