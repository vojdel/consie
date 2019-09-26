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
import modelo.MEscuela;
import modelo.MEstudiante;
import vista.VEstudiante;

/**
 *
 * @author Diego
 */
public class CEstudiante implements ActionListener, ItemListener, MouseListener, KeyListener {

    VEstudiante vista;
    MEstudiante modelo;

    MEscuela modeloEscuela;
    MEscuela[] datosEscuela;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CEstudiante() {
        vista = new VEstudiante();
        modelo = new MEstudiante();

        modeloEscuela = new MEscuela();

        try {
            datosEscuela = modeloEscuela.selectTodo();
            crearComboBoxEscuela(modeloEscuela.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            actualizarTabla(modelo.selectTodo());
            System.out.println("Tabla actualizada");
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Estudiante");
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

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnNuevo()) {
            limpiarFormulario();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnAgregar()) {
            agregar();
        } else if (ae.getSource() == vista.getBtnModificar()) {
            modificar();
        } else if (ae.getSource() == vista.getBtnEliminar()) {
            eliminar();
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
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vista.getTabla()) {
            llenarFormulario();
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

    private void crearComboBoxEscuela(MEscuela[] datos) {
        vista.getCbxEscuela().addItem("Seleccione...");

        if (datos != null) {
            for (MEscuela datosX : datos) {
                modeloEscuela = datosX;
                vista.getCbxEscuela().addItem(modeloEscuela.getNombre());
            }

            System.out.println("ComboBox Escuela creado");
        }
    }

    private void actualizarTabla(MEstudiante[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Primer Nombre");
        modeloTabla.addColumn("Segundo Nombre");
        modeloTabla.addColumn("Primer Apellido");
        modeloTabla.addColumn("Segundo Apellido");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Fecha de Nacimiento");
        modeloTabla.addColumn("Edad");
        modeloTabla.addColumn("Dirección");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MEstudiante datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getCedula(), modelo.getPrimerNombre(), modelo.getSegundoNombre(), modelo.getPrimerApellido(), modelo.getSegundoApellido(), modelo.getGenero(), modelo.getFechaNacimiento(), modelo.getEdad(), modelo.getDireccion()});
            }
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

    private void limpiarFormulario() {
        vista.getTxtCedula().setText("");
        vista.getTxtPNombre().setText("");
        vista.getTxtSNombre().setText("");
        vista.getTxtPApellido().setText("");
        vista.getTxtSApellido().setText("");
        vista.getBtnGrpGenero().clearSelection();
        vista.getDateChooserFNacimiento().cleanup();
        vista.getTxtEdad().setText("");
        vista.getTxtDireccion().setText("");
        vista.getCbxEscuela().setSelectedIndex(0);
    }

    private void agregar() {
        modelo.setCedula(vista.getTxtCedula().getText());
        modelo.setPrimerNombre(vista.getTxtPNombre().getText());
        modelo.setSegundoNombre(vista.getTxtSNombre().getText());
        modelo.setPrimerApellido(vista.getTxtPApellido().getText());
        modelo.setSegundoApellido(vista.getTxtSApellido().getText());
        modelo.setFechaNacimiento(vista.getDateChooserFNacimiento().getDate());
        modelo.setDireccion(vista.getTxtDireccion().getText());
        int indice = vista.getCbxEscuela().getSelectedIndex() - 1;
        modelo.setEscuela(datosEscuela[indice]);
        modelo.insert();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void modificar() {
        modelo.setCedula(vista.getTxtCedula().getText());
        modelo.setPrimerNombre(vista.getTxtPNombre().getText());
        modelo.setSegundoNombre(vista.getTxtSNombre().getText());
        modelo.setPrimerApellido(vista.getTxtPApellido().getText());
        modelo.setSegundoApellido(vista.getTxtSApellido().getText());
        modelo.setFechaNacimiento(vista.getDateChooserFNacimiento().getDate());
        modelo.setDireccion(vista.getTxtDireccion().getText());
        int indice = vista.getCbxEscuela().getSelectedIndex() - 1;
        modelo.setEscuela(datosEscuela[indice]);
        modelo.update();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void eliminar() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            String ci;
            ci = vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString();
            modelo.setCedula(ci);
            modelo.delete();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpiarFormulario();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        }
    }

    private void llenarFormulario() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            int id;
            id = Integer.parseInt(vista.getTabla().getValueAt(filaSeleccionada, 0).toString());

            try {
                modelo.select(id);
            } catch (SQLException ex) {
                Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtCedula().setText(modelo.getCedula());
            vista.getTxtPNombre().setText(modelo.getPrimerNombre());
            vista.getTxtSNombre().setText(modelo.getSegundoNombre());
            vista.getTxtPApellido().setText(modelo.getPrimerApellido());
            vista.getTxtSApellido().setText(modelo.getSegundoApellido());

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

            vista.getDateChooserFNacimiento().setDate(modelo.getFechaNacimiento());
            vista.getTxtDireccion().setText(modelo.getDireccion());

            int indice;

            for (int i = 0; i < datosEscuela.length; i++) {
                if (modelo.getEscuela().getId() == datosEscuela[i].getId()) {
                    indice = i + 1;
                    vista.getCbxEscuela().setSelectedIndex(indice);
                    break;
                }
            }

            vista.getBtnAgregar().setEnabled(false);
            vista.getBtnModificar().setEnabled(true);
            vista.getBtnEliminar().setEnabled(true);
        }
    }

}
