package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import general.Funciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.MEstado;
import modelo.MMunicipio;
import vista.VMunicipio;

/**
 *
 * @author Diego
 */
public class CMunicipio implements ActionListener, MouseListener, KeyListener {

    VMunicipio vista;
    MMunicipio modelo;

    MEstado modeloEstado;
    MEstado[] datosEstado;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CMunicipio() {
        vista = new VMunicipio();
        modelo = new MMunicipio();
        
        modeloEstado = new MEstado();

        try {
            datosEstado = modeloEstado.selectTodo();
            crearCbxEstado(datosEstado);
        } catch (SQLException ex) {
            Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            actualizarTabla(modelo.selectTodo());
            System.out.println("Tabla actualizada");
        } catch (SQLException ex) {
            Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Municipio");
        marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);
        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        addListener();
    }

    private void crearCbxEstado(MEstado[] datos) {
        vista.getCbxEstado().addItem("Seleccione...");

        if (datos != null) {
            for (MEstado datosX : datos) {
                modeloEstado = datosX;
                vista.getCbxEstado().addItem(modeloEstado.getNombre());
            }

            System.out.println("ComboBox Estado creado");
        }
    }

    private void actualizarTabla(MMunicipio[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Nombre");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MMunicipio datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getEstado().getNombre(), modelo.getNombre()});
            }

            funciones.ocultarColumnas(vista.getTabla(), new int[]{0});
            System.out.println("Tabla actualizada");
        }
    }

    private void addListener() {
        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getTabla().addMouseListener(this);
    }

    private void limpiarFormulario() {
        vista.getCbxEstado().setSelectedIndex(0);
        vista.getTxtNombre().setText("");
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnNuevo()) {
            limpiarFormulario();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnAgregar()) {
            modelo.setNombre(vista.getTxtNombre().getText());
            int indice = vista.getCbxEstado().getSelectedIndex() - 1;
            modelo.setEstado(datosEstado[indice]);
            modelo.insert();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpiarFormulario();
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnModificar()) {
            modelo.setNombre(vista.getTxtNombre().getText());
            int indice = vista.getCbxEstado().getSelectedIndex() - 1;
            modelo.setEstado(datosEstado[indice]);
            modelo.update();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpiarFormulario();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnEliminar()) {
            int filaSeleccionada = vista.getTabla().getSelectedRow();

            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString());
                modelo.setId(id);
                modelo.delete();

                try {
                    actualizarTabla(modelo.selectTodo());
                } catch (SQLException ex) {
                    Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                }

                limpiarFormulario();
                vista.getBtnAgregar().setEnabled(true);
                vista.getBtnModificar().setEnabled(false);
                vista.getBtnEliminar().setEnabled(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.getTabla()) {
            int filaSeleccionada = vista.getTabla().getSelectedRow();

            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vista.getTabla().getValueAt(filaSeleccionada, 0).toString());

                try {
                    modelo.select(id);
                } catch (SQLException ex) {
                    Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista.getTxtNombre().setText(modelo.getNombre());

                int indice;

                for (int i = 0; i < datosEstado.length; i++) {
                    if (modelo.getEstado().getId() == datosEstado[i].getId()) {
                        indice = i + 1;
                        vista.getCbxEstado().setSelectedIndex(indice);
                        break;
                    }
                }

                vista.getBtnAgregar().setEnabled(false);
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
            }
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

    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }
    
}
