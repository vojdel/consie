package controlador;

import static consie.Consie.app;
import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import vista.VInicio;

/**
 *
 * @author Diego
 */
public class CInicio implements MouseListener {
    
    public VInicio vista;
    
    public CInicio() {
        System.out.println("Iniciando CInicio");
        vista = new VInicio();
        
        ventana.setTitle("Inicio");
        if(panelPrincipal != null) marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);
        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        addListener();
    }
    
    private void addListener() {
        vista.getBtnSegEscuela().addMouseListener(this);
        vista.getBtnAdminPersonal().addMouseListener(this);
        vista.getBtnPedagogico().addMouseListener(this);
        vista.getBtnAsistencia().addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == vista.getBtnSegEscuela()) {
            app = new CAdminEscuela();
        } else if(e.getSource() == vista.getBtnPedagogico()) {
            app = new CNivelPedagogico();
        } else if(e.getSource() == vista.getBtnAsistencia()) {
            app = new CEstadistica();
        } else if(e.getSource() == vista.getBtnAdminPersonal()) {
            app = new CSeguimientoPersonal();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
