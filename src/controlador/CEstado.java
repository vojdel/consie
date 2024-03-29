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
import vista.VEstado;

/**
 *
 * @author Diego
 */
public class CEstado implements ActionListener, MouseListener, KeyListener {

    VEstado vista;
    MEstado modelo;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CEstado() {
        vista = new VEstado();
        modelo = new MEstado();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Estado");
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
            vista.getTxtNombre().setText("");
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

    private void actualizarTabla(MEstado[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MEstado datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getNombre()});
            }

            System.out.println("Tabla actualizada");
        }

        funciones.ocultarColumnas(vista.getTabla(), new int[]{0});
    }

    private void addListener() {
        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getTabla().addMouseListener(this);
    }

    private void limpiarFormulario() {
        vista.getTxtNombre().setText("");
    }

    private void agregar() {
        modelo.setNombre(vista.getTxtNombre().getText());
        modelo.insert();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void modificar() {
        modelo.setNombre(vista.getTxtNombre().getText());
        modelo.update();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void eliminar() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            int id;
            id = Integer.parseInt(vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString());
            modelo.setId(id);
            modelo.delete();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtNombre().setText(modelo.getNombre());
            vista.getBtnAgregar().setEnabled(false);
            vista.getBtnModificar().setEnabled(true);
            vista.getBtnEliminar().setEnabled(true);
        }
    }

}
