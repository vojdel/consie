package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import general.Funciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.MRepresentante;
import vista.VRepresentante;

/**
 *
 * @author Diego
 */
public class CRepresentante implements ActionListener, ItemListener, MouseListener, KeyListener {

    VRepresentante vista;
    MRepresentante modelo;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CRepresentante() {
        vista = new VRepresentante();
        modelo = new MRepresentante();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Representante");
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

    private void actualizarTabla(MRepresentante[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Primer Nombre");
        modeloTabla.addColumn("Segundo Nombre");
        modeloTabla.addColumn("Primer Apellido");
        modeloTabla.addColumn("Segundo Apellido");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Teléfono");
        modeloTabla.addColumn("Dirección");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MRepresentante datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getCedula(), modelo.getPrimerNombre(), modelo.getSegundoNombre(), modelo.getPrimerApellido(), modelo.getSegundoApellido(), modelo.getGenero(), modelo.getTelefono(), modelo.getDireccion()});
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
        vista.getRadBtnMasculino().addItemListener(this);
        vista.getRadBtnFemenino().addItemListener(this);
        vista.getTabla().addMouseListener(this);
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
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }

                if (null != modelo.getGenero()) {
                    switch (modelo.getGenero()) {
                        case "Masculino":
                            vista.getRadBtnMasculino().setSelected(true);
                            break;
                        case "Femenino":
                            vista.getRadBtnFemenino().setSelected(true);
                            break;
                        default:
                            break;
                    }
                }

                vista.getTxtCedula().setText(modelo.getCedula());
                vista.getTxtPrimerNombre().setText(modelo.getPrimerNombre());
                vista.getTxtSegundoNombre().setText(modelo.getSegundoNombre());
                vista.getTxtPrimerApellido().setText(modelo.getPrimerApellido());
                vista.getTxtSegundoApellido().setText(modelo.getSegundoApellido());
                String tlf[] = modelo.getTelefono().split("-");
                vista.getCbxTelefono().setSelectedItem(tlf[0]);
                vista.getTxtTelefono().setText(tlf[1]);
                vista.getTxtDireccion().setText(modelo.getDireccion());
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
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnNuevo()) {
            vista.getTxtCedula().setText("");
            vista.getTxtPrimerNombre().setText("");
            vista.getTxtSegundoNombre().setText("");
            vista.getTxtPrimerApellido().setText("");
            vista.getTxtSegundoApellido().setText("");
            vista.getRadBtnMasculino().setSelected(false);
            vista.getRadBtnFemenino().setSelected(false);
            vista.getTxtTelefono().setText("");
            vista.getTxtDireccion().setText("");
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnAgregar()) {
            modelo.setCedula(vista.getTxtCedula().getText());
            modelo.setPrimerNombre(vista.getTxtPrimerNombre().getText());
            modelo.setSegundoNombre(vista.getTxtSegundoNombre().getText());
            modelo.setPrimerApellido(vista.getTxtPrimerApellido().getText());
            modelo.setSegundoApellido(vista.getTxtSegundoApellido().getText());
            modelo.setTelefono(vista.getCbxTelefono().getSelectedItem().toString() + "-" + vista.getTxtTelefono().getText());
            modelo.setDireccion(vista.getTxtDireccion().getText());
            modelo.insert();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtCedula().setText("");
            vista.getTxtPrimerNombre().setText("");
            vista.getTxtSegundoNombre().setText("");
            vista.getTxtPrimerApellido().setText("");
            vista.getTxtSegundoApellido().setText("");
            vista.getRadBtnMasculino().setSelected(false);
            vista.getRadBtnFemenino().setSelected(false);
            vista.getTxtTelefono().setText("");
            vista.getTxtDireccion().setText("");
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnModificar()) {
            modelo.setCedula(vista.getTxtCedula().getText());
            modelo.setPrimerNombre(vista.getTxtPrimerNombre().getText());
            modelo.setSegundoNombre(vista.getTxtSegundoNombre().getText());
            modelo.setPrimerApellido(vista.getTxtPrimerApellido().getText());
            modelo.setSegundoApellido(vista.getTxtSegundoApellido().getText());
            modelo.setTelefono(vista.getCbxTelefono().getSelectedItem().toString() + "-" + vista.getTxtTelefono().getText());
            modelo.setDireccion(vista.getTxtDireccion().getText());
            modelo.update();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtCedula().setText("");
            vista.getTxtPrimerNombre().setText("");
            vista.getTxtSegundoNombre().setText("");
            vista.getTxtPrimerApellido().setText("");
            vista.getTxtSegundoApellido().setText("");
            vista.getRadBtnMasculino().setSelected(false);
            vista.getRadBtnFemenino().setSelected(false);
            vista.getTxtTelefono().setText("");
            vista.getTxtDireccion().setText("");
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnEliminar()) {
            int filaSeleccionada = vista.getTabla().getSelectedRow();

            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString());
                modelo.setCedula(Integer.toString(id));
                modelo.delete();

                try {
                    actualizarTabla(modelo.selectTodo());
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista.getTxtCedula().setText("");
                vista.getTxtPrimerNombre().setText("");
                vista.getTxtSegundoNombre().setText("");
                vista.getTxtPrimerApellido().setText("");
                vista.getTxtSegundoApellido().setText("");
                vista.getRadBtnMasculino().setSelected(false);
                vista.getRadBtnFemenino().setSelected(false);
                vista.getTxtTelefono().setText("");
                vista.getTxtDireccion().setText("");
                vista.getBtnAgregar().setEnabled(true);
                vista.getBtnModificar().setEnabled(false);
                vista.getBtnEliminar().setEnabled(false);
            }
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            if (ie.getSource() == vista.getRadBtnMasculino()) {
                modelo.setGenero(vista.getRadBtnMasculino().getText());
            }

            if (ie.getSource() == vista.getRadBtnFemenino()) {
                modelo.setGenero(vista.getRadBtnFemenino().getText());
            }

            if (ie.getSource() == vista.getRadBtnMasculino()) {
                modelo.setGenero(vista.getRadBtnMasculino().getText());
            }

            if (ie.getSource() == vista.getRadBtnFemenino()) {
                modelo.setGenero(vista.getRadBtnFemenino().getText());
            }
        }
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
