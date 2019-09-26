package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import vista.VInicio;

/**
 *
 * @author Diego
 */
public class CInicio {
    
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
        
    }
}
