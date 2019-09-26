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
import java.util.HashMap;
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

    VFuncionario vista;
    MFuncionario modelo;
    
    MCargo modeloCargo;
    MCargo[] datosCargo;
    
    DefaultComboBoxModel modeloComBox;
    DefaultTableModel modeloTabla;
    
    String arreglo;
    String[] array;
    String msj = "";
    boolean bol = true;
    HashMap<String, Integer> cargos;
    Funciones f = new Funciones();
    Validaciones val = new Validaciones();

    public CFuncionario() {
        vista = new VFuncionario();
        modelo = new MFuncionario();
        
        modeloCargo = new MCargo();
        
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
        
        cargos = modelo.hashCargo();
        
        addListener();

    }
    
    private void crearCbxCargo(MCargo[] datos) {
        modeloComBox = new DefaultComboBoxModel();
        modeloComBox.addElement("Seleccione...");
        
        for (MCargo datosX : datos) {
            modeloComBox.addElement(datosX.getNombre());
        }
        
        vista.getComBoxCargo().setModel(modeloComBox);
            System.out.println("ComboBox Cargo creado");
    }

    private void actualizarTabla(MFuncionario[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Primer Nombre");
        modeloTabla.addColumn("Segundo Nombre");
        modeloTabla.addColumn("Primer Apellido");
        modeloTabla.addColumn("Segundo Apellido");
        modeloTabla.addColumn("Cargo");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MFuncionario datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{
                    modelo.getCedula(),
                    modelo.getPrimerNombre(),
                    modelo.getSegundoNombre(),
                    modelo.getPrimerApellido(),
                    modelo.getSegundoApellido(),
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
        vista.getTxtPNombre().addKeyListener(this);
        vista.getTxtCedula().addKeyListener(this);
        vista.getTxtSNombre().addKeyListener(this);
        vista.getTxtPApellido().addKeyListener(this);
        vista.getTxtSApellido().addKeyListener(this);
    }

    private Boolean validar() {
        this.bol = true;
        
        if (vista.getTxtCedula().getText().isEmpty()) {
            msj += "El Campo Cedula no puede Estar Vacio\n";
            this.bol = false;
        }
        if (vista.getTxtPNombre().getText().isEmpty()) {
            msj += "EL Campo Primer Nombre no puede Estar Vacio\n";
            this.bol = false;
        }
        if (vista.getTxtSNombre().getText().isEmpty()) {
            msj += "El Campo Segundo Nombre no puede Estar Vacio\n";
            this.bol = false;
        }
        if (vista.getTxtPApellido().getText().isEmpty()) {
            msj += "El Campo Segundo Nombre no puede Estar Vacio\n";
            this.bol = false;
        }
        if (vista.getTxtSApellido().getText().isEmpty()) {
            msj += "El Campo Segundo Nombre no puede Estar Vacio\n";
            this.bol = false;
        }
        if (!vista.getRaBtnF().isFocusable() || !vista.getRaBtnF().isFocusable()) {
            msj += "Elija entre Femenino o Masculino\n";
            this.bol = false;
        }
        if (!vista.getComBoxCargo().isFocusable()) {
            msj += "Elija un Cargo\n";
            this.bol = false;
        }
        if (!bol) {
            JOptionPane.showMessageDialog(null, msj, "¡Alerta!", JOptionPane.ERROR_MESSAGE);
        }

        return true;
    }

    public void limpiar() {
        vista.getTxtCedula().setText("");
        vista.getTxtPNombre().setText("");
        vista.getTxtSNombre().setText("");
        vista.getTxtPApellido().setText("");
        vista.getTxtSApellido().setText("");
        vista.getTxtFNacimiento().setDate(new Date());
        vista.getTxtTelefono().setText("");
        vista.getRaBtnM().setSelected(false);
        vista.getRaBtnF().setSelected(false);
    }

   /* public int obtenerIdCargo(String[] cargos) throws SQLException {
        int apuntador = 0;

        for (int i = 0; i < modelo.countCargos(); i++) {
            if (cargos[i] == vista.getComBoxCargo().getSelectedItem()) {
                apuntador = Integer.parseInt(modelo.obtenerIdCargo()[i]);
            }

        }
        return apuntador;
    }

    public String[] obtenerCargo() throws SQLException {
        String apuntador2 = "";
        String apuntador3 = "";
        String[] array = new String[modelo.countCargos()];

        for (int j = 0; j < modelo.countCargos(); j++) {
            array[j] = modelo.getNombres_cargo()[j];
        }
        for (int h = 0; h < modelo.countCargos(); h++) {
            if (array[h] == modelo.getNombre_cargo()) {
                apuntador2 = array[h];
                apuntador3 = array[0];
                array[h] = apuntador3;
                array[0] = apuntador2;
            }
        }
        return array;
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == vista.getBtnNuevo()) {
            limpiar();
            vista.getComBoxCargo().setModel(modelo.obtenerCargo());
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (e.getSource() == vista.getBtnAgregar()) {
            validar();
            modelo.setCedula(Integer.parseInt(vista.getTxtCedula().getText()));
            modelo.setPrimerNombre(vista.getTxtPNombre().getText());
            modelo.setSegundoNombre(vista.getTxtSNombre().getText());
            modelo.setPrimerApellido(vista.getTxtPApellido().getText());
            modelo.setSegundoApellido(vista.getTxtSApellido().getText());
            if (vista.getRaBtnF().isSelected()) {
                modelo.setGenero("F");
            } else {
                modelo.setGenero("M");
            }
            modelo.setCargo(new MCargo(cargos.get(vista.getComBoxCargo().getModel().getSelectedItem())));
            modelo.setFechaNacimiento(new Date(vista.getTxtFNacimiento().getDate().toString()));
            modelo.setTelefono(vista.getTxtTelefono().getText());
            if (bol) {
                modelo.insert();
            }

            try {
                actualizarTabla(modelo.selectTodo());

            } catch (SQLException ex) {
                Logger.getLogger(CEstado.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            limpiar();
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (e.getSource() == vista.getBtnModificar()) {
            modelo.setPrimerNombre(vista.getTxtPNombre().getText());
            modelo.setSegundoNombre(vista.getTxtSNombre().getText());
            modelo.setPrimerApellido(vista.getTxtPApellido().getText());
            modelo.setSegundoApellido(vista.getTxtSApellido().getText());
            if (vista.getRaBtnF().isSelected()) {
                modelo.setGenero("F");
            } else {
                modelo.setGenero("M");
            }
            modelo.setCargo(new MCargo(cargos.get(vista.getComBoxCargo().getModel().getSelectedItem())));
            modelo.setFechaNacimiento(new Date(vista.getTxtFNacimiento().getDate().toString()));
            modelo.setTelefono(vista.getTxtTelefono().getText());
            modelo.update();

            try {
                actualizarTabla(modelo.selectTodo());

            } catch (SQLException ex) {
                Logger.getLogger(CEstado.class
                        .getName()).log(Level.SEVERE, null, ex);
            }

            limpiar();
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (e.getSource() == vista.getBtnEliminar()) {
            int filaSeleccionada = vista.getTabla().getSelectedRow();

            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString());
                modelo.setCedula(id);
                modelo.delete();

                try {
                    actualizarTabla(modelo.selectTodo());

                } catch (SQLException ex) {
                    Logger.getLogger(CEstado.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

                limpiar();
                vista.getBtnAgregar().setEnabled(true);
                vista.getBtnModificar().setEnabled(false);
                vista.getBtnEliminar().setEnabled(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == vista.getTabla()) {
            limpiar();
            int filaSeleccionada = vista.getTabla().getSelectedRow();
            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vista.getTabla().getValueAt(filaSeleccionada, 0).toString());
                try {
                    System.out.println(id);
                    modelo.select(id);

                } catch (SQLException ex) {
                    Logger.getLogger(CEstado.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                vista.getTxtCedula().setText(Integer.toString(modelo.getCedula()));
                vista.getTxtPNombre().setText((modelo.getPrimerNombre()));
                vista.getTxtSNombre().setText(modelo.getSegundoNombre());
                vista.getTxtPApellido().setText(modelo.getPrimerApellido());
                vista.getTxtSApellido().setText(modelo.getSegundoApellido());
                vista.getTxtFNacimiento().setDateFormatString(modelo.getFechaNacimiento().toString());
                vista.getTxtTelefono().setText(Integer.toString(Integer.parseInt(modelo.getTelefono())));
                if (!"F".equals(modelo.getGenero())) {
                    vista.getRaBtnM().setSelected(true);
                    vista.getRaBtnF().setSelected(false);
                } else {
                    vista.getRaBtnM().setSelected(false);
                    vista.getRaBtnF().setSelected(true);
                }
                vista.getComBoxCargo().getModel().setSelectedItem(modelo.getNombre_cargo());
                vista.getBtnAgregar().setEnabled(false);
                vista.getBtnModificar().setEnabled(true);
                vista.getBtnEliminar().setEnabled(true);
            }
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
        if (e.getSource() == vista.getTxtPNombre()) {
            val.Espacio(e);
            val.evitarPegar(e);
            val.soloLetras(e);
            val.Limite(e, vista.getTxtPNombre().getText(), 10);
        } else if(e.getSource() == vista.getTxtSNombre()){
            val.evitarPegar(e);
            val.soloLetras(e);  
            val.Limite(e, vista.getTxtPNombre().getText(), 30);          
        } else if (e.getSource() == vista.getTxtPApellido()) {
            val.Espacio(e);
            val.evitarPegar(e);
            val.soloLetras(e);
            val.Limite(e, vista.getTxtPNombre().getText(), 10);
        } else if(e.getSource() == vista.getTxtSApellido()){
            val.evitarPegar(e);
            val.soloLetras(e);  
            val.Limite(e, vista.getTxtPNombre().getText(), 30);          
        } else if(e.getSource() == vista.getTxtCedula()){
            val.evitarPegar(e);
            val.soloNumeros(e);  
            val.Limite(e, vista.getTxtPNombre().getText(), 30);  
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vista.getTxtCedula()) {
            String textoBuscar = vista.getTxtCedula().getText();
            try {
                actualizarTabla(modelo.buscar(textoBuscar));
            } catch (SQLException ex) {
                Logger.getLogger(CCargo.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (vista.getTxtCedula().getText().length() == 0 && e.getKeyCode() == 8) {
            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CUsuario.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == vista.getTxtPNombre()) {
            System.out.println("3");
            f.soloLetras(e);
            f.limitar(e, vista.getTxtPNombre().getText(), 10);
        }

        System.out.println(e); //8
        System.out.println(e.getKeyCode()); //8
        System.out.println(" :" + vista.getTxtCedula().getText() + ": ");
    }

}
