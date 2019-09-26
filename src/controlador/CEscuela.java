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
import modelo.MEstado;
import modelo.MMunicipio;
import modelo.MParroquia;
import vista.VEscuela;

/**
 *
 * @author Diego
 */
public class CEscuela implements ActionListener, ItemListener, MouseListener, KeyListener {

    VEscuela vista;
    MEscuela modelo;

    MParroquia modeloParroquia;
    MParroquia[] datosParroquia;

    MMunicipio modeloMunicipio;
    MMunicipio[] datosMunicipio;

    MEstado modeloEstado;
    MEstado[] datosEstado;

    DefaultTableModel modeloTabla;

    Funciones funciones = new Funciones();

    public CEscuela() {
        vista = new VEscuela();
        modelo = new MEscuela();

        modeloParroquia = new MParroquia();
        modeloMunicipio = new MMunicipio();
        modeloEstado = new MEstado();

        try {
            datosEstado = modeloEstado.selectTodo();
            crearComboBoxEstado(datosEstado);
        } catch (SQLException ex) {
            Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
        }

        vista.getCbxMunicipio().addItem("Seleccione un estado");
        vista.getCbxMunicipio().setEnabled(false);

        vista.getCbxParroquia().addItem("Seleccione un municipio");
        vista.getCbxParroquia().setEnabled(false);

        try {
            actualizarTabla(modelo.selectTodo());
            System.out.println("Tabla Escuela actualizada");
        } catch (SQLException ex) {
            Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Escuela");
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

    private void crearComboBoxEstado(MEstado[] datos) {
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

    private void crearCbxParroquia(MParroquia[] datos) {
        vista.getCbxParroquia().removeAllItems();
        vista.getCbxParroquia().addItem("Seleccione...");

        if (datos != null) {
            for (MParroquia datosX : datos) {
                modeloParroquia = datosX;
                vista.getCbxParroquia().addItem(modeloParroquia.getNombre());
            }

            vista.getCbxParroquia().setEnabled(true);
            System.out.println("ComboBox Parroquia creado");
        }
    }

    private void actualizarTabla(MEscuela[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Id");
        modeloTabla.addColumn("Estado");
        modeloTabla.addColumn("Municipio");
        modeloTabla.addColumn("Parroquia");
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Turno");
        modeloTabla.addColumn("Dirección");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MEscuela datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{modelo.getId(), modelo.getParroquia().getMunicipio().getEstado().getNombre(), modelo.getParroquia().getMunicipio().getNombre(), modelo.getParroquia().getNombre(), modelo.getNombre(), modelo.getTurno(), modelo.getDireccion()});
            }
        }

        funciones.ocultarColumnas(vista.getTabla(), new int[]{0});
    }

    private void addListener() {
        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getRadBtnManana().addItemListener(this);
        vista.getRadBtnTarde().addItemListener(this);
        vista.getRadBtnAmbos().addItemListener(this);
        vista.getCbxEstado().addItemListener(this);
        vista.getCbxMunicipio().addItemListener(this);
        vista.getTabla().addMouseListener(this);
    }

    private void limpiarFormulario() {
        vista.getTxtNombre().setText("");
        vista.getBtnGrpTurno().clearSelection();
        vista.getCbxEstado().setSelectedIndex(0);
        vista.getCbxMunicipio().setSelectedIndex(0);
        vista.getCbxParroquia().setSelectedIndex(0);
        vista.getTxtDireccion().setText("");
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
            int indice = vista.getCbxParroquia().getSelectedIndex() - 1;
            modelo.setParroquia(datosParroquia[indice]);
            modelo.setDireccion(vista.getTxtDireccion().getText());
            modelo.insert();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
            }

            limpiarFormulario();
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnModificar()) {
            modelo.setNombre(vista.getTxtNombre().getText());
            int indice = vista.getCbxParroquia().getSelectedIndex() - 1;
            modelo.setParroquia(datosParroquia[indice]);
            modelo.setDireccion(vista.getTxtDireccion().getText());
            modelo.update();

            try {
                actualizarTabla(modelo.selectTodo());
            } catch (SQLException ex) {
                Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }

                limpiarFormulario();
                vista.getBtnAgregar().setEnabled(true);
                vista.getBtnModificar().setEnabled(false);
                vista.getBtnEliminar().setEnabled(false);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent ie) {
        if (ie.getStateChange() == ItemEvent.SELECTED) {
            if (ie.getSource() == vista.getCbxEstado()) {
                try {
                    int indice;
                    indice = vista.getCbxEstado().getSelectedIndex() - 1;

                    if (indice >= 0) {
                        datosMunicipio = modeloMunicipio.selectPorEstado(datosEstado[indice].getId());

                        if (datosMunicipio != null) {
                            crearCbxMunicipio(datosMunicipio);
                        } else {
                            vista.getCbxMunicipio().removeAllItems();
                            vista.getCbxMunicipio().addItem("No hay municipios registrados");
                            vista.getCbxMunicipio().setEnabled(false);
                            vista.getCbxParroquia().setEnabled(false);
                        }
                    } else {
                        vista.getCbxMunicipio().removeAllItems();
                        vista.getCbxMunicipio().addItem("Seleccione un estado");
                        vista.getCbxMunicipio().setEnabled(false);
                        vista.getCbxParroquia().setEnabled(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ie.getSource() == vista.getCbxMunicipio()) {
                try {
                    int indice;
                    indice = vista.getCbxMunicipio().getSelectedIndex() - 1;

                    if (indice >= 0) {
                        datosParroquia = modeloParroquia.selectPorMunicipio(datosMunicipio[indice].getId());

                        if (datosParroquia != null) {
                            crearCbxParroquia(datosParroquia);
                        } else {
                            vista.getCbxParroquia().removeAllItems();
                            vista.getCbxParroquia().addItem("No hay parroquias registradas");
                            vista.getCbxParroquia().setEnabled(false);
                        }
                    } else {
                        vista.getCbxParroquia().removeAllItems();
                        vista.getCbxParroquia().addItem("Seleccione un municipio");
                        vista.getCbxParroquia().setEnabled(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (ie.getSource() == vista.getRadBtnManana()) {
                modelo.setTurno(vista.getRadBtnManana().getText());
            }

            if (ie.getSource() == vista.getRadBtnTarde()) {
                modelo.setTurno(vista.getRadBtnTarde().getText());
            }

            if (ie.getSource() == vista.getRadBtnAmbos()) {
                modelo.setTurno(vista.getRadBtnAmbos().getText());
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
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }

                vista.getTxtNombre().setText(modelo.getNombre());

                if (null != modelo.getTurno()) {
                    switch (modelo.getTurno()) {
                        case "Manana":
                            vista.getRadBtnManana().setSelected(true);
                            break;
                        case "Tarde":
                            vista.getRadBtnTarde().setSelected(true);
                            break;
                        case "Ambos":
                            vista.getRadBtnAmbos().setSelected(true);
                            break;
                        default:
                            break;
                    }
                }

                int indice;

                for (int i = 0; i < datosEstado.length; i++) {
                    if (modelo.getParroquia().getMunicipio().getEstado().getId() == datosEstado[i].getId()) {
                        indice = i + 1;
                        vista.getCbxEstado().setSelectedIndex(indice);

                        if (indice > 0) {
                            try {
                                datosMunicipio = modeloMunicipio.selectPorEstado(datosEstado[i].getId());

                                if (datosMunicipio != null) {
                                    crearCbxMunicipio(datosMunicipio);

                                    for (int j = 0; j < datosMunicipio.length; j++) {
                                        if (modelo.getParroquia().getMunicipio().getId() == datosMunicipio[j].getId()) {
                                            indice = j + 1;
                                            vista.getCbxMunicipio().setSelectedIndex(indice);

                                            if (indice > 0) {
                                                datosParroquia = modeloParroquia.selectPorMunicipio(datosMunicipio[j].getId());

                                                if (datosParroquia != null) {
                                                    crearCbxParroquia(datosParroquia);

                                                    for (int k = 0; k < datosParroquia.length; k++) {
                                                        if (modelo.getParroquia().getId() == datosParroquia[k].getId()) {
                                                            indice = k + 1;
                                                            vista.getCbxParroquia().setSelectedIndex(indice);
                                                        }
                                                    }
                                                    break;
                                                }
                                            }
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
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
    }

}
