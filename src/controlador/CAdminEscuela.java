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
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.MAdminEscuela;
import modelo.MEscuela;
import modelo.MEstado;
import modelo.MMunicipio;
import modelo.MParroquia;
import modelo.MPersonal;
import vista.VAdminEscuela;
import vista.VAsignarPersonal;
import vista.VEntregarRecaudo;

/**
 *
 * @author Diego
 */
public class CAdminEscuela implements ActionListener, MouseListener, KeyListener, ItemListener {

    private VAdminEscuela vista;
    private VEntregarRecaudo vistaRecaudos;
    private VAsignarPersonal vistaPersonal;
    private MAdminEscuela modelo;
    private MPersonal modeloPersonal;

    private DefaultTableModel modeloTabla;

    private MParroquia modeloParroquia;
    private MParroquia[] datosParroquia;

    private MMunicipio modeloMunicipio;
    private MMunicipio[] datosMunicipio;

    private MEstado modeloEstado;
    private MEstado[] datosEstado;

    private Funciones funciones = new Funciones();

    public CAdminEscuela() {
        // vista = new VAdminEscuela();
        vista = new VAdminEscuela();
        vistaRecaudos = new VEntregarRecaudo();
        vistaPersonal = new VAsignarPersonal();
        modelo = new MAdminEscuela();
        modeloPersonal = new MPersonal();

        modeloParroquia = new MParroquia();
        modeloMunicipio = new MMunicipio();
        modeloEstado = new MEstado();

        /* try {
            actualizarTabla(modelo.selectTodo());
        } catch (SQLException ex) {
            Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        cambioVentana("Administración Escuelas", vista);

        try {
            datosEstado = modeloEstado.selectTodo();
            crearComboBoxEstado(datosEstado, vistaRecaudos.getCbxEstado());
            crearComboBoxEstado(datosEstado, vistaPersonal.getCbxEstado());
        } catch (SQLException ex) {
            Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
        }

        vistaRecaudos.getCbxMunicipio().addItem("Seleccione un estado");
        vistaRecaudos.getCbxMunicipio().setEnabled(false);

        vistaRecaudos.getCbxParroquia().addItem("Seleccione un municipio");
        vistaRecaudos.getCbxParroquia().setEnabled(false);

        vistaPersonal.getCbxMunicipio().addItem("Seleccione un estado");
        vistaPersonal.getCbxMunicipio().setEnabled(false);

        vistaPersonal.getCbxParroquia().addItem("Seleccione un municipio");
        vistaPersonal.getCbxParroquia().setEnabled(false);

        ventana.setTitle("Administrar Escuelas");
        marco.remove(panelPrincipal);
        vista.setBounds(290, 70, 670, 590);
        marco.add(vista);
        panelPrincipal = vista;
        ventana.repaint();
        ventana.validate();
        addListener();
    }

    private void addListener() {
        // Principal
        vista.getBtnEntregaRecaudos().addActionListener(this);
        vista.getBtnAsignarPersonal().addActionListener(this);
        vista.getTablas().addMouseListener(this);

        // Entrega de Recaudos
        vistaRecaudos.getBtnAtras().addActionListener(this);
        vistaRecaudos.getBtnAgregar().addActionListener(this);
        vistaRecaudos.getBtnAnual().addActionListener(this);
        vistaRecaudos.getBtnListo().addActionListener(this);
        vistaRecaudos.getBtnMensual().addActionListener(this);
        vistaRecaudos.getBtnReiniciar().addActionListener(this);
        vistaRecaudos.getBtnTrimestral().addActionListener(this);
        vistaRecaudos.getBtnEliminar().addActionListener(this);
        vistaRecaudos.getCbxEstado().addItemListener(this);
        vistaRecaudos.getCbxMunicipio().addItemListener(this);
        vistaRecaudos.getCbxParroquia().addItemListener(this);
        vistaRecaudos.getTxtEscuela().addKeyListener(this);
        vistaRecaudos.getTablaEntrega().addMouseListener(this);
        vistaRecaudos.getTablaEscuela().addMouseListener(this);
        vistaRecaudos.getTablaRecaudo().addMouseListener(this);

        // Asignar Personal
        vistaPersonal.getBtnAtras().addActionListener(this);
        vistaPersonal.getBtnAgregar().addActionListener(this);
        vistaPersonal.getBtnListo().addActionListener(this);
        vistaPersonal.getBtnReiniciar().addActionListener(this);
        vistaPersonal.getBtnEliminar().addActionListener(this);
        vistaPersonal.getCbxEstado().addItemListener(this);
        vistaPersonal.getCbxMunicipio().addItemListener(this);
        vistaPersonal.getCbxParroquia().addItemListener(this);
        vistaPersonal.getCbxCedula().addItemListener(this);
        vistaPersonal.getTxtEscuela().addKeyListener(this);
        vistaPersonal.getTxtPersonal().addKeyListener(this);
        vistaPersonal.getTablaPersonal().addMouseListener(this);
        vistaPersonal.getTablaEscuela().addMouseListener(this);
        vistaPersonal.getTablaEscuelaPersonal().addMouseListener(this);
    }

    private void actualizarTablaEscuela(JTable tabla, String columns[], MAdminEscuela[] datos) {
        modeloTabla = new DefaultTableModel();
        for (int i = 0; i < columns.length; i++) {
            modeloTabla.addColumn(columns[i]);
        }
        tabla.setModel(modeloTabla);

        if (datos != null) {
            for (MAdminEscuela datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(
                        new Object[]{
                            modelo.getEscuela().getId(),
                            modelo.getEscuela().getNombre(),
                            modelo.getEstado().getId(),
                            modelo.getEstado().getNombre(),
                            modelo.getMunicipio().getId(),
                            modelo.getMunicipio().getNombre(),
                            modelo.getParroquia().getId(),
                            modelo.getParroquia().getNombre()
                        }
                );
            }
        }

        funciones.ocultarColumnas(tabla, new int[]{0, 2, 4, 6});
    }

    private void actualizarTablaPersonal(JTable tabla, MPersonal[] datos) {

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Primer Nombre");
        modeloTabla.addColumn("Segundo Nombre");
        modeloTabla.addColumn("Primer Apellido");
        modeloTabla.addColumn("Segundo Apellido");
        modeloTabla.addColumn("Género");
        modeloTabla.addColumn("Dirección");
        modeloTabla.addColumn("Telefono");
        modeloTabla.addColumn("cargo");

        tabla.setModel(modeloTabla);

        if (datos != null) {
            for (MPersonal datosX : datos) {
                modeloPersonal = datosX;
                modeloTabla.addRow(
                        new Object[]{
                            modeloPersonal.getCedula(),
                            modeloPersonal.getPrimerNombre(),
                            modeloPersonal.getSegundoNombre(),
                            modeloPersonal.getPrimerApellido(),
                            modeloPersonal.getSegundoApellido(),
                            modeloPersonal.getGenero(),
                            modeloPersonal.getTelefono(),
                            modeloPersonal.getDireccion(),
                            modeloPersonal.getCargo().getNombre()
                        }
                );
            }
        }
    }

    private void actualizarTablaEscuelaPersonal(JTable tabla, MAdminEscuela[] datos) {

        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("id");
        modeloTabla.addColumn("Cédula");
        modeloTabla.addColumn("Nombre(s)");
        modeloTabla.addColumn("cargo");
        modeloTabla.addColumn("id_escuela");
        modeloTabla.addColumn("escuela");

        tabla.setModel(modeloTabla);

        if (datos != null) {
            for (MAdminEscuela datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(
                        new Object[]{
                            modelo.getId(),
                            modelo.getPersonal().getCedula(),
                            modelo.getPersonal().getPrimerNombre() + " "
                            + modelo.getPersonal().getSegundoNombre(),
                            modelo.getPersonal().getCargo().getNombre(),
                            modelo.getEscuela().getId(),
                            modelo.getEscuela().getNombre()
                        }
                );
            }
        }
        funciones.ocultarColumnas(tabla, new int[]{0, 4});
    }

    private void cambioVentana(String titulo, JPanel panel) {
        ventana.setTitle(titulo);
        marco.remove(panelPrincipal);
        panel.setBounds(290, 70, 670, 590);
        marco.add(panel);
        panelPrincipal = panel;
        ventana.repaint();
        ventana.validate();
    }

    private void crearComboBoxEstado(MEstado[] datos, JComboBox cbx) {
        cbx.addItem("Seleccione...");

        if (datos != null) {
            for (MEstado datosX : datos) {
                modeloEstado = datosX;
                cbx.addItem(modeloEstado.getNombre());
            }

            System.out.println("ComboBox Estado creado");
        }
    }

    private void crearCbxMunicipio(MMunicipio[] datos, JComboBox cbx) {
        cbx.removeAllItems();
        cbx.addItem("Seleccione...");

        if (datos != null) {
            for (MMunicipio datosX : datos) {
                modeloMunicipio = datosX;
                cbx.addItem(modeloMunicipio.getNombre());
            }

            cbx.setEnabled(true);
            System.out.println("ComboBox Municipio creado");
        }
    }

    private void crearCbxParroquia(MParroquia[] datos, JComboBox cbx) {
        cbx.removeAllItems();
        cbx.addItem("Seleccione...");

        if (datos != null) {
            for (MParroquia datosX : datos) {
                modeloParroquia = datosX;
                cbx.addItem(modeloParroquia.getNombre());
            }

            cbx.setEnabled(true);
            System.out.println("ComboBox Parroquia creado");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == vistaPersonal.getTablaEscuela()) {
            int filaSeleccionada = vistaPersonal.getTablaEscuela().getSelectedRow();

            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vistaPersonal.getTablaEscuela().getValueAt(filaSeleccionada, 0).toString());

                try {
                    actualizarTablaEscuelaPersonal(
                            vistaPersonal.getTablaEscuelaPersonal(),
                            modelo.selectAsignarPersonal(id)
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (me.getSource() == vistaPersonal.getTablaEscuela()) {
            int filaSeleccionada = vistaPersonal.getTablaEscuela().getSelectedRow();

            if (filaSeleccionada >= 0) {
                int id;
                id = Integer.parseInt(vistaPersonal.getTablaEscuela().getValueAt(filaSeleccionada, 0).toString());

                try {
                    actualizarTablaEscuelaPersonal(
                            vistaPersonal.getTablaEscuelaPersonal(),
                            modelo.selectAsignarPersonal(id)
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }
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
    public void actionPerformed(ActionEvent e) {
        // Principal
        if (e.getSource() == vista.getBtnEntregaRecaudos()) {
            cambioVentana("Entrega de Recaudos", vistaRecaudos);
        }
        if (e.getSource() == vista.getBtnAsignarPersonal()) {
            cambioVentana("Asignar Personal", vistaPersonal);
        }
        if (e.getSource() == vista.getBtnCambioEscuela()) {
            System.out.println("aun no esta listo!");
            //cambioVentana("Entrega de Recaudos", vistaRecaudos);
        }

        // Entrega de Recaudos
        if (e.getSource() == vistaRecaudos.getBtnAtras()) {
            cambioVentana("Administración Escuelas", vista);
        }

        // Asignar Personal
        if (e.getSource() == vistaPersonal.getBtnAtras()) {
            cambioVentana("Administración Escuelas", vista);
        }
        if (e.getSource() == vistaPersonal.getBtnAgregar()) {
            
            int filaSeleccionadaEscuela = vistaPersonal.getTablaEscuela().getSelectedRow();
            int filaSeleccionadaPersonal = vistaPersonal.getTablaPersonal().getSelectedRow();

            if (filaSeleccionadaEscuela >= 0) {
                int idEscuela;
                idEscuela = Integer.parseInt(vistaPersonal.getTablaEscuela().getValueAt(filaSeleccionadaEscuela, 0).toString());
                String idPersonal;
                idPersonal = vistaPersonal.getTablaPersonal().getValueAt(filaSeleccionadaPersonal, 0).toString();

                try {
                    modelo.setEscuela(new MEscuela(idEscuela));
                    modelo.setPersonal(new MPersonal(idPersonal));
                    modelo.insertAsignarPersonal();
                    actualizarTablaEscuelaPersonal(
                            vistaPersonal.getTablaEscuelaPersonal(),
                            modelo.selectAsignarPersonal(idEscuela)
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (e.getSource() == vistaPersonal.getBtnEliminar()) {
            int filaSeleccionada = vistaPersonal.getTablaEscuelaPersonal().getSelectedRow();
            int filaSeleccionadaEscuela = vistaPersonal.getTablaEscuela().getSelectedRow();

            if (filaSeleccionada >= 0) {
                
                int idEscuela;
                idEscuela = Integer.parseInt(vistaPersonal.getTablaEscuela().getValueAt(filaSeleccionadaEscuela, 0).toString());
                int id;
                id = Integer.parseInt(vistaPersonal.getTablaEscuelaPersonal().getValueAt(filaSeleccionada, 0).toString());

                try {
                    modelo.setId(id);
                    modelo.deleteAsignarPersonal();
                    actualizarTablaEscuelaPersonal(
                            vistaPersonal.getTablaEscuelaPersonal(),
                            modelo.selectAsignarPersonal(idEscuela)
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getSource() == vistaRecaudos.getTxtEscuela()) {

            String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
            try {
                actualizarTablaEscuela(
                        vistaRecaudos.getTablaEscuela(),
                        columns,
                        modelo.buscarEscuela(vistaRecaudos.getTxtEscuela().getText(),
                                vistaRecaudos.getCbxEstado().getSelectedIndex(),
                                vistaRecaudos.getCbxMunicipio().getSelectedIndex(),
                                vistaRecaudos.getCbxParroquia().getSelectedIndex()
                        )
                );
            } catch (SQLException ex) {
                Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == vistaPersonal.getTxtEscuela()) {

            String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
            try {
                actualizarTablaEscuela(
                        vistaPersonal.getTablaEscuela(),
                        columns,
                        modelo.buscarEscuela(
                                vistaPersonal.getTxtEscuela().getText(),
                                vistaPersonal.getCbxEstado().getSelectedIndex(),
                                vistaPersonal.getCbxMunicipio().getSelectedIndex(),
                                vistaPersonal.getCbxParroquia().getSelectedIndex()
                        )
                );
            } catch (SQLException ex) {
                Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (e.getSource() == vistaPersonal.getTxtPersonal()) {
            try {
                actualizarTablaPersonal(vistaPersonal.getTablaPersonal(), modeloPersonal.busquedaDinamica(
                        vistaPersonal.getCbxCedula().getSelectedItem().toString(),
                        vistaPersonal.getTxtPersonal().getText()
                )
                );
            } catch (SQLException ex) {
                Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e
    ) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            // Entrgar Recaudos
            if (e.getSource() == vistaRecaudos.getCbxEstado()) {
                try {
                    int indice;
                    indice = vistaRecaudos.getCbxEstado().getSelectedIndex() - 1;

                    if (indice >= 0) {
                        datosMunicipio = modeloMunicipio.selectPorEstado(datosEstado[indice].getId());

                        if (datosMunicipio != null) {
                            crearCbxMunicipio(datosMunicipio, vistaRecaudos.getCbxMunicipio());
                        } else {
                            vistaRecaudos.getCbxMunicipio().removeAllItems();
                            vistaRecaudos.getCbxMunicipio().addItem("No hay municipios registrados");
                            vistaRecaudos.getCbxMunicipio().setEnabled(false);
                            vistaRecaudos.getCbxParroquia().setEnabled(false);
                        }
                    } else {
                        vistaRecaudos.getCbxMunicipio().removeAllItems();
                        vistaRecaudos.getCbxMunicipio().addItem("Seleccione un estado");
                        vistaRecaudos.getCbxMunicipio().setEnabled(false);
                        vistaRecaudos.getCbxParroquia().setEnabled(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
                }

                String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
                try {
                    actualizarTablaEscuela(
                            vistaRecaudos.getTablaEscuela(),
                            columns,
                            modelo.buscarEscuela(
                                    vistaRecaudos.getTxtEscuela().getText(),
                                    vistaRecaudos.getCbxEstado().getSelectedIndex(),
                                    vistaRecaudos.getCbxMunicipio().getSelectedIndex(),
                                    vistaRecaudos.getCbxParroquia().getSelectedIndex()
                            )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (e.getSource() == vistaRecaudos.getCbxMunicipio()) {
                try {
                    int indice;
                    indice = vistaRecaudos.getCbxMunicipio().getSelectedIndex() - 1;

                    if (indice >= 0) {
                        datosParroquia = modeloParroquia.selectPorMunicipio(datosMunicipio[indice].getId());

                        if (datosParroquia != null) {
                            crearCbxParroquia(datosParroquia, vistaRecaudos.getCbxParroquia());
                        } else {
                            vistaRecaudos.getCbxParroquia().removeAllItems();
                            vistaRecaudos.getCbxParroquia().addItem("No hay parroquias registradas");
                            vistaRecaudos.getCbxParroquia().setEnabled(false);
                        }
                    } else {
                        vistaRecaudos.getCbxParroquia().removeAllItems();
                        vistaRecaudos.getCbxParroquia().addItem("Seleccione un municipio");
                        vistaRecaudos.getCbxParroquia().setEnabled(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }

                String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
                try {
                    actualizarTablaEscuela(
                            vistaRecaudos.getTablaEscuela(),
                            columns,
                            modelo.buscarEscuela(
                                    vistaRecaudos.getTxtEscuela().getText(),
                                    vistaRecaudos.getCbxEstado().getSelectedIndex(),
                                    vistaRecaudos.getCbxMunicipio().getSelectedIndex(),
                                    vistaRecaudos.getCbxParroquia().getSelectedIndex()
                            )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (e.getSource() == vistaRecaudos.getCbxParroquia()) {

                String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
                try {
                    actualizarTablaEscuela(
                            vistaRecaudos.getTablaEscuela(),
                            columns,
                            modelo.buscarEscuela(
                                    vistaRecaudos.getTxtEscuela().getText(),
                                    vistaRecaudos.getCbxEstado().getSelectedIndex(),
                                    vistaRecaudos.getCbxMunicipio().getSelectedIndex(),
                                    vistaRecaudos.getCbxParroquia().getSelectedIndex()
                            )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            // Asignar Personal
            //Escuela
            if (e.getSource() == vistaPersonal.getCbxEstado()) {
                try {
                    int indice2;
                    indice2 = vistaPersonal.getCbxEstado().getSelectedIndex() - 1;

                    if (indice2 >= 0) {
                        datosMunicipio = modeloMunicipio.selectPorEstado(datosEstado[indice2].getId());

                        if (datosMunicipio != null) {
                            crearCbxMunicipio(datosMunicipio, vistaPersonal.getCbxMunicipio());
                        } else {
                            vistaPersonal.getCbxMunicipio().removeAllItems();
                            vistaPersonal.getCbxMunicipio().addItem("No hay municipios registrados");
                            vistaPersonal.getCbxMunicipio().setEnabled(false);
                            vistaPersonal.getCbxParroquia().setEnabled(false);
                        }
                    } else {
                        vistaPersonal.getCbxMunicipio().removeAllItems();
                        vistaPersonal.getCbxMunicipio().addItem("Seleccione un estado");
                        vistaPersonal.getCbxMunicipio().setEnabled(false);
                        vistaPersonal.getCbxParroquia().setEnabled(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CParroquia.class.getName()).log(Level.SEVERE, null, ex);
                }

                String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
                try {
                    actualizarTablaEscuela(
                            vistaPersonal.getTablaEscuela(),
                            columns,
                            modelo.buscarEscuela(
                                    vistaPersonal.getTxtEscuela().getText(),
                                    vistaPersonal.getCbxEstado().getSelectedIndex(),
                                    vistaPersonal.getCbxMunicipio().getSelectedIndex(),
                                    vistaPersonal.getCbxParroquia().getSelectedIndex()
                            )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (e.getSource() == vistaPersonal.getCbxMunicipio()) {
                try {
                    int indice;
                    indice = vistaPersonal.getCbxMunicipio().getSelectedIndex() - 1;

                    if (indice >= 0) {
                        datosParroquia = modeloParroquia.selectPorMunicipio(datosMunicipio[indice].getId());

                        if (datosParroquia != null) {
                            crearCbxParroquia(datosParroquia, vistaPersonal.getCbxParroquia());
                        } else {
                            vistaPersonal.getCbxParroquia().removeAllItems();
                            vistaPersonal.getCbxParroquia().addItem("No hay parroquias registradas");
                            vistaPersonal.getCbxParroquia().setEnabled(false);
                        }
                    } else {
                        vistaPersonal.getCbxParroquia().removeAllItems();
                        vistaPersonal.getCbxParroquia().addItem("Seleccione un municipio");
                        vistaPersonal.getCbxParroquia().setEnabled(false);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(CEscuela.class.getName()).log(Level.SEVERE, null, ex);
                }

                String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
                try {
                    actualizarTablaEscuela(
                            vistaPersonal.getTablaEscuela(),
                            columns,
                            modelo.buscarEscuela(vistaPersonal.getTxtEscuela().getText(),
                                    vistaPersonal.getCbxEstado().getSelectedIndex(),
                                    vistaPersonal.getCbxMunicipio().getSelectedIndex(),
                                    vistaPersonal.getCbxParroquia().getSelectedIndex()
                            )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (e.getSource() == vistaPersonal.getCbxParroquia()) {

                String columns[] = {"id", "escuela", "id", "estado", "id", "municipio", "id", "parroquia"};
                try {
                    actualizarTablaEscuela(
                            vistaPersonal.getTablaEscuela(),
                            columns,
                            modelo.buscarEscuela(
                                    vistaPersonal.getTxtEscuela().getText(),
                                    vistaPersonal.getCbxEstado().getSelectedIndex(),
                                    vistaPersonal.getCbxMunicipio().getSelectedIndex(),
                                    vistaPersonal.getCbxParroquia().getSelectedIndex()
                            )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            if (e.getSource() == vistaPersonal.getCbxCedula()) {
                try {
                    actualizarTablaPersonal(vistaPersonal.getTablaPersonal(), modeloPersonal.busquedaDinamica(
                            vistaPersonal.getCbxCedula().getSelectedItem().toString(),
                            vistaPersonal.getTxtPersonal().getText()
                    )
                    );
                } catch (SQLException ex) {
                    Logger.getLogger(CCargo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }
    }
}
