package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import general.Funciones;
import general.Validaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.MCargo;
import modelo.MFuncionario;
import vista.VFuncionario;

/**
 *
 * @author Daniel
 */
public class CFuncionario implements ActionListener, MouseListener, KeyListener {

    private VFuncionario vista;
    private MFuncionario modelo;

    private MCargo modeloCargo;
    private MCargo[] datosCargo;

    private DefaultComboBoxModel modeloCbxCargo;
    private DefaultTableModel modeloTabla;

    private String msj = "";
    private boolean bol = true;
    private Funciones f = new Funciones();
    private Validaciones val = new Validaciones();

    public CFuncionario() {
        vista = new VFuncionario();
        modelo = new MFuncionario();

        modeloCargo = new MCargo();

        vista.getTxtFNacimiento().setDate(new Date());

        try {
            datosCargo = modeloCargo.selectTodo();
            crearCbxCargo(datosCargo);
        } catch (SQLException ex) {
            Logger.getLogger(CFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Funcionario");
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

    private void crearCbxCargo(MCargo[] datos) {
        modeloCbxCargo = new DefaultComboBoxModel();
        modeloCbxCargo.addElement("Seleccione...");

        for (MCargo datosX : datos) {
            modeloCbxCargo.addElement(datosX.getNombre());
        }

        vista.getCbxCargo().setModel(modeloCbxCargo);
        System.out.println("ComboBox Cargo creado");
    }

    private void actualizarTabla(MFuncionario[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Apellido");
        modeloTabla.addColumn("Cargo");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MFuncionario datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{
                    modelo.getCedula(),
                    modelo.getPrimerNombre() + " " + modelo.getSegundoNombre(),
                    modelo.getPrimerApellido() + " " + modelo.getSegundoApellido(),
                    modelo.getCargo().getNombre()});
            }

            System.out.println("Tabla Funcionario actualizada");
        }
    }

    private void addListener() {
        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getTabla().addMouseListener(this);
        vista.getTxtCedula().addKeyListener(this);
        vista.getTxtPNombre().addKeyListener(this);
        vista.getTxtSNombre().addKeyListener(this);
        vista.getTxtPApellido().addKeyListener(this);
        vista.getTxtSApellido().addKeyListener(this);
    }

    private void limpiar() {
        vista.getTxtCedula().setText("");
        vista.getTxtPNombre().setText("");
        vista.getTxtSNombre().setText("");
        vista.getTxtPApellido().setText("");
        vista.getTxtSApellido().setText("");
        vista.getBtnGrpGenero().clearSelection();
        vista.getTxtFNacimiento().setDate(new Date());
        vista.getTxtTelefono().setText("");
        vista.getTxtDireccion().setText("");
        vista.getCbxCargo().setSelectedIndex(0);
    }

    private Boolean validar() {
        this.bol = true;
        msj = "";

        if (vista.getTxtCedula().getText().isEmpty()) {
            msj += "El campo Cédula no puede estar vacío\n";
            this.bol = false;
        }
        if (vista.getTxtPNombre().getText().isEmpty()) {
            msj += "El campo Primer Nombre no puede estar vacío\n";
            this.bol = false;
        }
        if (vista.getTxtPApellido().getText().isEmpty()) {
            msj += "El campo Primer Apellido no puede estar vacío\n";
            this.bol = false;
        }
        if (vista.getBtnGrpGenero().getSelection() == null) {
            msj += "Elija entre Femenino o Masculino\n";
            this.bol = false;
        }
        if (vista.getCbxCargo().getSelectedIndex() == 0) {
            msj += "Elija un Cargo\n";
            this.bol = false;
        }
        if (!bol) {
            JOptionPane.showMessageDialog(null, msj, "¡Alerta!", JOptionPane.ERROR_MESSAGE);
        }

        return true;
    }

    private void agregar() {
        modelo.setCedula(vista.getTxtCedula().getText());
        modelo.setPrimerNombre(vista.getTxtPNombre().getText());
        modelo.setSegundoNombre(vista.getTxtSNombre().getText());
        modelo.setPrimerApellido(vista.getTxtPApellido().getText());
        modelo.setSegundoApellido(vista.getTxtSApellido().getText());

        if (vista.getRaBtnF().isSelected()) {
            modelo.setGenero("f");
        } else {
            modelo.setGenero("m");
        }

        modelo.setFechaNacimiento(vista.getTxtFNacimiento().getDate());
        modelo.setTelefono(vista.getTxtTelefono().getText());
        modelo.setDireccion(vista.getTxtDireccion().getText());
        int indice = vista.getCbxCargo().getSelectedIndex() - 1;
        modelo.setCargo(datosCargo[indice]);

        if (bol) {
            modelo.insert();
        }

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiar();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void modificar() {
        modelo.setPrimerNombre(vista.getTxtPNombre().getText());
        modelo.setSegundoNombre(vista.getTxtSNombre().getText());
        modelo.setPrimerApellido(vista.getTxtPApellido().getText());
        modelo.setSegundoApellido(vista.getTxtSApellido().getText());

        if (vista.getRaBtnF().isSelected()) {
            modelo.setGenero("f");
        } else {
            modelo.setGenero("m");
        }

        modelo.setFechaNacimiento(vista.getTxtFNacimiento().getDate());
        modelo.setTelefono(vista.getTxtTelefono().getText());
        modelo.setDireccion(vista.getTxtDireccion().getText());

        int indice = vista.getCbxCargo().getSelectedIndex() - 1;
        modelo.setCargo(datosCargo[indice]);

        if (bol) {
            modelo.update();
        }

        try {
            actualizarTabla(modelo.selectTodo());

        } catch (SQLException ex) {
            Logger.getLogger(CFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiar();
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void eliminar() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            String cedula;
            cedula = vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString();
            modelo.setCedula(cedula);
            modelo.delete();

            try {
                actualizarTabla(modelo.selectTodo());

            } catch (SQLException ex) {
                Logger.getLogger(CFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpiar();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        }
    }

    private void llenarFormulario() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            String cedula;
            cedula = vista.getTabla().getValueAt(filaSeleccionada, 0).toString();

            try {
                modelo.select(cedula);
            } catch (SQLException ex) {
                Logger.getLogger(CFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtCedula().setText(modelo.getCedula());
            vista.getTxtPNombre().setText((modelo.getPrimerNombre()));
            vista.getTxtSNombre().setText(modelo.getSegundoNombre());
            vista.getTxtPApellido().setText(modelo.getPrimerApellido());
            vista.getTxtSApellido().setText(modelo.getSegundoApellido());

            switch (modelo.getGenero()) {
                case "f":
                    vista.getRaBtnF().setSelected(true);
                    break;
                case "m":
                    vista.getRaBtnM().setSelected(true);
                    break;
                default:
                    break;
            }

            vista.getTxtFNacimiento().setDate(modelo.getFechaNacimiento());
            vista.getTxtTelefono().setText(modelo.getTelefono());
            vista.getTxtDireccion().setText(modelo.getDireccion());

            int indice;

            for (int i = 0; i < datosCargo.length; i++) {
                if (modelo.getCargo().getId() == datosCargo[i].getId()) {
                    indice = i + 1;
                    vista.getCbxCargo().setSelectedIndex(indice);
                    break;
                }
            }

            vista.getBtnAgregar().setEnabled(false);
            vista.getBtnModificar().setEnabled(true);
            vista.getBtnEliminar().setEnabled(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnNuevo()) {
            limpiar();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (e.getSource() == vista.getBtnAgregar()) {
            validar();

            if (bol) {
                agregar();
            }
        } else if (e.getSource() == vista.getBtnModificar()) {
            validar();

            if (bol) {
                modificar();
            }
        } else if (e.getSource() == vista.getBtnEliminar()) {
            JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el registro?", "¡Advertencia!", JOptionPane.YES_NO_OPTION);
            eliminar();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.getTabla()) {
            llenarFormulario();
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

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.getTxtCedula()) {

            String textoBuscar = vista.getTxtCedula().getText();

            try {
                actualizarTabla(modelo.buscar(textoBuscar));
            } catch (SQLException ex) {
                Logger.getLogger(CFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
            val.Espacio(e);
            val.evitarPegar(e);
            val.soloNumeros(e);
            val.limite(e, vista.getTxtCedula().getText(), 10);
        } else if (e.getSource() == vista.getTxtPNombre()) {
            val.Espacio(e);
            val.evitarPegar(e);
            val.soloLetras(e);
            val.limite(e, vista.getTxtPNombre().getText(), 30);
        } else if (e.getSource() == vista.getTxtSNombre()) {
            val.evitarPegar(e);
            val.soloLetras(e);
            val.limite(e, vista.getTxtSNombre().getText(), 30);
        } else if (e.getSource() == vista.getTxtPApellido()) {
            val.Espacio(e);
            val.evitarPegar(e);
            val.soloLetras(e);
            val.limite(e, vista.getTxtPApellido().getText(), 30);
        } else if (e.getSource() == vista.getTxtSApellido()) {
            val.evitarPegar(e);
            val.soloLetras(e);
            val.limite(e, vista.getTxtSApellido().getText(), 30);
        } else if (e.getSource() == vista.getTxtCedula()) {
            val.evitarPegar(e);
            val.soloNumeros(e);
            val.limite(e, vista.getTxtCedula().getText(), 10);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
