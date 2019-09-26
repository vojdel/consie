package controlador;

import static consie.Consie.ventana;
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
        ventana.getContentPane().removeAll();
        ventana.add(vista);
        ventana.setTitle("AdminPersonal");
        ventana.pack();
        addListeners();
    }

    private void addListeners() {
        vista.getLabelCerrar().addMouseListener(this);
        vista.getLabelMenu().addMouseListener(this);
        vista.getPanelMenu().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.getLabelCerrar()) {
            System.exit(0);
        } else if (me.getSource() == vista.getLabelMenu()) {
            vista.getPanelMenu().setSize(200, 3000);
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
        if (me.getSource() == vista.getPanelMenu()) {
            vista.getPanelMenu().setSize(50, 3000);
        }
    }
}
