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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import modelo.MRecaudo;
import vista.VRecaudo;

/**
 *
 * @author Diego
 */
public class CRecaudo implements ActionListener, MouseListener, KeyListener, ItemListener {

    VRecaudo vista;
    MRecaudo modelo;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CRecaudo() {
        vista = new VRecaudo();
        modelo = new MRecaudo();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Recaudo");
        marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);
        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
        vista.getDateDia().setDate(new Date());
        vista.getDateDia().setEnabled(false);
        vista.getCbxTrimestre().setEnabled(false);
        addListener();
    }

    private void actualizarTabla(MRecaudo[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Frecuencia de entrega");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MRecaudo datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getNombre(), modelo.getFrecuenciaEntrega()});
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
        vista.getComBoxFrecuencia().addItemListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == vista.getBtnNuevo()) {

            vista.getTxtNombre().setText("");
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);

        } else if (ae.getSource() == vista.getBtnAgregar()) {

            modelo.setNombre(vista.getTxtNombre().getText());
            String num_frecuencia_entrega = vista.getCbxTrimestre().getItemAt(vista.getCbxTrimestre().getSelectedIndex());
            modelo.setFrecuenciaEntrega((String) vista.getComBoxFrecuencia().getSelectedItem());

            if (!"Seleccione".equals(vista.getCbxTrimestre().getSelectedItem())) {

                modelo.setNumFrecuencia(Integer.parseInt(num_frecuencia_entrega));

            } else if (vista.getDateDia().isEnabled() == false) {

                modelo.setNumFrecuencia(Integer.parseInt(vista.getDateDia().getDate().toString()));
                System.out.println(vista.getDateDia().getDate().toString());

            }
            modelo.insert();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CRecaudo.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtNombre().setText("");
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnModificar()) {
            modelo.setNombre(vista.getTxtNombre().getText());
            modelo.update();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtNombre().setText("");
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

                vista.getTxtNombre().setText("");
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
                    Logger.getLogger(CEstado.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista.getTxtNombre().setText(modelo.getNombre());
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

    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (e.getSource() == vista.getComBoxFrecuencia()) {
                if ("Trimestral".equals(vista.getComBoxFrecuencia().getSelectedItem())) {

                    vista.getDateDia().setEnabled(false);
                    vista.getCbxTrimestre().setEnabled(true);

                } else if ("Mensual".equals(vista.getComBoxFrecuencia().getSelectedItem())) {

                    vista.getCbxTrimestre().setEnabled(false);
                    vista.getDateDia().setEnabled(true);

                }
            }
        }
    }
}
