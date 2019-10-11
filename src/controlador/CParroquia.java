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
import modelo.MEstado;
import modelo.MMunicipio;
import modelo.MParroquia;
import vista.VParroquia;

/**
 *
 * @author Diego
 */
public class CParroquia implements ActionListener, ItemListener, MouseListener, KeyListener {

    VParroquia vista;
    MParroquia modelo;

    MMunicipio modeloMunicipio;
    MMunicipio[] datosMunicipio;

    MEstado modeloEstado;
    MEstado[] datosEstado;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CParroquia() {
        vista = new VParroquia();
        modelo = new MParroquia();

        modeloMunicipio = new MMunicipio();
        modeloEstado = new MEstado();

        try {
            datosEstado = modeloEstado.selectTodo();
            crearCbxEstado(datosEstado);
        } catch (SQLException ex) {
            Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }

        vista.getCbxMunicipio().addItem("Seleccione un estado");
        vista.getCbxMunicipio().setEnabled(false);

        try {
            actualizarTabla(modelo.selectTodo());
            System.out.println("Tabla actualizada");
        } catch (SQLException ex) {
            Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Parroquia");
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
            if (ie.getSource() == vista.getCbxEstado()) {
                actualizarCbxMunicipio();
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

    private void crearCbxMunicipio(MMunicipio[] datos) {
        vista.getCbxMunicipio().removeAllItems();
        vista.getCbxMunicipio().addItem("Seleccione...");

        if (datos != null) {
            for (MMunicipio datosX : datos) {
                modeloMunicipio = datosX;
                vista.getCbxMunicipio().addItem(modeloMunicipio.getNombre());
            }

            vista.getCbxMunicipio().setEnabled(true);
            System.out.println("ComboBox Municipio creado");
        }
    }

    private void actualizarTabla(MParroquia[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Municipio");
        modeloTabla.addColumn("Nombre");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MParroquia datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getMunicipio().getEstado().getNombre(), modelo.getMunicipio().getNombre(), modelo.getNombre()});
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
        vista.getCbxEstado().addItemListener(this);
        vista.getTabla().addMouseListener(this);
    }

    private void limpiarFormulario() {
        vista.getCbxEstado().setSelectedIndex(0);
        vista.getCbxMunicipio().setSelectedIndex(0);
        vista.getTxtNombre().setText("");
    }

    private void agregar() {
        modelo.setNombre(vista.getTxtNombre().getText());
        int indice = vista.getCbxMunicipio().getSelectedIndex() - 1;
        modelo.setMunicipio(datosMunicipio[indice]);
        modelo.insert();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiarFormulario();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void modificar() {
        modelo.setNombre(vista.getTxtNombre().getText());
        int indice = vista.getCbxMunicipio().getSelectedIndex() - 1;
        modelo.setMunicipio(datosMunicipio[indice]);
        modelo.update();

        try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
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
                Logger.getLogger(CMunicipio.class.getName()).log(Level.SEVERE, null, ex);
            }

            vista.getTxtNombre().setText(modelo.getNombre());

            int indice;

            for (int i = 0; i < datosEstado.length; i++) {
                if (modelo.getMunicipio().getEstado().getId() == datosEstado[i].getId()) {
                    indice = i + 1;
                    vista.getCbxEstado().setSelectedIndex(indice);

                    if (indice > 0) {
                        try {
                            datosMunicipio = modeloMunicipio.selectPorEstado(datosEstado[i].getId());

                            if (datosMunicipio != null) {
                                crearCbxMunicipio(datosMunicipio);
                                indice = 0;

                                for (int j = 0; j < datosMunicipio.length; j++) {
                                    if (modelo.getMunicipio().getId() == datosMunicipio[j].getId()) {
                                        indice = j + 1;
                                        vista.getCbxMunicipio().setSelectedIndex(indice);
                                        break;
                                    }
                                }
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    break;
                }
            }

            vista.getBtnAgregar().setEnabled(false);
            vista.getBtnModificar().setEnabled(true);
            vista.getBtnEliminar().setEnabled(true);
        }
    }

    private void actualizarCbxMunicipio() {
        try {
            int indice;
            indice = vista.getCbxEstado().getSelectedIndex() - 1;

            if (indice > 0) {
                datosMunicipio = modeloMunicipio.selectPorEstado(datosEstado[indice].getId());

                if (datosMunicipio != null) {
                    crearCbxMunicipio(datosMunicipio);
                } else {
                    vista.getCbxMunicipio().removeAllItems();
                    vista.getCbxMunicipio().addItem("No hay municipios registrados");
                    vista.getCbxMunicipio().setEnabled(false);
                }
            } else {
                vista.getCbxMunicipio().removeAllItems();
                vista.getCbxMunicipio().addItem("Seleccione un estado");
                vista.getCbxMunicipio().setEnabled(false);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
