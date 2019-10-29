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
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.MSeccion;
import vista.VSeccion;

/**
 *
 * @author Diego
 */
public class CSeccion implements ActionListener, MouseListener, KeyListener {

    VSeccion vista;
    MSeccion modelo;
    Validaciones val;
    Funciones f;
    HashMap<String, Integer> grados;
    DefaultTableModel modeloTabla;

    public CSeccion() {
        vista = new VSeccion();
        modelo = new MSeccion();
        grados = modelo.hashGrado();
        val = new Validaciones();
        f = new Funciones();

        try {
            actualizarTabla(modelo.selectTodo());

        } catch (SQLException sq) {

            Logger.getLogger(CSeccion.class.getName()).log(Level.SEVERE, null, sq);

        }
        ventana.setTitle("Seccion");
        marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);
        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        vista.getCbxGrado().setModel(modelo.obtenerGrado());
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        addListeners();
    }

    private void actualizarTabla(MSeccion[] dt) {
        modeloTabla = new DefaultTableModel();

        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Seccion");
        modeloTabla.addColumn("Grado");
        modeloTabla.addColumn("Nº Estudiantes");

        vista.getTabla().setModel(modeloTabla);

        f.ocultarColumnas(vista.getTabla(), new int[]{0});

        if (dt != null) {
            for (MSeccion ms : dt) {
                modelo = ms;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getSeccion(), modelo.getGrado(), modelo.getN_estudiantes()});
            }
        }
    }

    private void addListeners() {
        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
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
                    Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista.getTxtSeccion().setText(modelo.getSeccion());
                vista.getCbxGrado().getModel().setSelectedItem(modelo.getGrado());
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

            vista.getTxtSeccion().setText("");
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);

        } else if (ae.getSource() == vista.getBtnAgregar()) {

            modelo.setSeccion(vista.getTxtSeccion().getText());
            modelo.setId_grado(grados.get(vista.getCbxGrado().getModel().getSelectedItem()));
            modelo.insert();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtSeccion().setText("");
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnModificar()) {

            modelo.setSeccion(vista.getTxtSeccion().getText());
            modelo.setId_grado(grados.get(vista.getCbxGrado().getModel().getSelectedItem()));
            modelo.update();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtSeccion().setText("");
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
                    Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista.getTxtSeccion().setText("");
                vista.getBtnAgregar().setEnabled(true);
                vista.getBtnModificar().setEnabled(false);
                vista.getBtnEliminar().setEnabled(false);
            }
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getSource() == vista.getTxtSeccion()) {
            val.Espacio(e);
            val.limite(e, vista.getTxtSeccion().getText(), 15);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
