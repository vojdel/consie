package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.VSeguimientoPersonal;


/**
 *
 * @author Diego
 */
public class CSeguimientoPersonal implements MouseListener {
    VSeguimientoPersonal vista;

    public CSeguimientoPersonal() {
        vista = new VSeguimientoPersonal();
        ventana.setTitle("Seguimiento de personal");
        marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);
        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        addListeners();
    }

    private void addListeners() {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
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
