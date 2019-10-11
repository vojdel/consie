/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import general.Funciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.MAnioEscolar;
import vista.VAnioEscolar;

/**
 *
 * @author Usuario
 */
public class CAnioEscolar implements ActionListener, MouseListener {

    VAnioEscolar vista;
    MAnioEscolar modelo;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CAnioEscolar() {
        vista = new VAnioEscolar();
        modelo = new MAnioEscolar();
        funciones = new Funciones();

        ventana.setTitle("Año Escolar");
        marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);

        // Datos de la Tabla
        try {
            actualizarTabla(modelo.selectTodo());
            System.out.println("Tabla actualizada");
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        addListener();
    }

    private void addListener() {

        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getTabla().addMouseListener(this);
    }

    private void actualizarTabla(MAnioEscolar[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Comienzo del Año Escolar");
        modeloTabla.addColumn("Fin del Año Escolar");
        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MAnioEscolar datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getAnioEscolarC(), modelo.getAnioEscolarF()});
            }
        }

        funciones.ocultarColumnas(vista.getTabla(), new int[]{0});
    }

    private void agregar() {
        modelo.setAnioEscolarC(vista.getDateComienzo().getDate());
        modelo.setAnioEscolarF(vista.getDateFin().getDate());
        modelo.insert();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
    }

    private void modificar() {
        modelo.setAnioEscolarC(vista.getDateComienzo().getDate());
        modelo.setAnioEscolarF(vista.getDateFin().getDate());
        modelo.update();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
    }

    private void eliminar() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            String ci;
            ci = vista.getTabla().getValueAt(vista.getTabla().getSelectedRow(), 0).toString();
            modelo.setId(Integer.parseInt(ci));
            modelo.delete();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEstudiante.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpiarFormulario();
        }
    }

    private void limpiarFormulario() {
        vista.getDateComienzo().setDate(new Date());
        vista.getDateFin().setDate(new Date());
        vista.getBtnNuevo().setEnabled(true);
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
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

            vista.getDateComienzo().setDate(modelo.getAnioEscolarC());
            vista.getDateFin().setDate(modelo.getAnioEscolarF());
            vista.getBtnAgregar().setEnabled(false);
            vista.getBtnModificar().setEnabled(true);
            vista.getBtnEliminar().setEnabled(true);
        }
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
}
