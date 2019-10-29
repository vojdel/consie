package controlador;

import static consie.Consie.ventana;
import static controlador.CVentana.marco;
import static controlador.CVentana.panelPrincipal;
import general.Validaciones;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.MIndicador;
import vista.VIndicador;

/**
 *
 * @author Diego
 */
public class CIndicador implements ActionListener, MouseListener, KeyListener {

    private VIndicador vista;
    private MIndicador modelo;

    private DefaultTableModel modeloTabla;
    private MIndicador[] datos;

    private Validaciones val = new Validaciones();
    private boolean bol = true;
    String msj = "";

    String[] prioridades = {"Baja", "Media", "Alta"};

    public CIndicador() {
        vista = new VIndicador();
        modelo = new MIndicador();

        try {
            datos = modelo.selectTodo();
            actualizarTabla(datos);
        } catch (SQLException ex) {
            Logger.getLogger(CIndicador.class.getName()).log(Level.SEVERE, null, ex);
        }

        ventana.setTitle("Indicador");
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
            limpiar();
            vista.getBtnAgregar().setEnabled(true);
            vista.getBtnModificar().setEnabled(false);
            vista.getBtnEliminar().setEnabled(false);
        } else if (ae.getSource() == vista.getBtnAgregar()) {
            validar();

            if (bol) {
                agregar();
            }
        } else if (ae.getSource() == vista.getBtnModificar()) {
            validar();

            if (bol) {
                modificar();
            }
        } else if (ae.getSource() == vista.getBtnEliminar()) {
            int r = JOptionPane.showConfirmDialog(null, "¿Seguro que desea eliminar el registro?", "Advertencia", JOptionPane.YES_NO_OPTION);
            System.out.println(r);

            if (r > 0) {
                eliminar();
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
        if (ke.getSource() == vista.getTxtNombre()) {
            val.limite(ke, vista.getTxtNombre().getText(), 100);
            val.soloLetras(ke);
            val.Espacio(ke);
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        if (ke.getSource() == vista.getTxtNombre()) {
            String textoBuscar = vista.getTxtNombre().getText();

            try {
                actualizarTabla(modelo.buscar(textoBuscar));
            } catch (SQLException ex) {
                Logger.getLogger(CIndicador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (vista.getTxtNombre().getText().length() == 0) {
            try {
                datos = modelo.selectTodo();
                actualizarTabla(datos);
            } catch (SQLException ex) {
                Logger.getLogger(CIndicador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void actualizarTabla(MIndicador[] datos) {
        modeloTabla = new DefaultTableModel();
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Prioridad");

        vista.getTabla().setModel(modeloTabla);

        if (datos != null) {
            for (MIndicador datosX : datos) {
                modelo = datosX;
                modeloTabla.addRow(new Object[]{
                    modelo.getNombre(),
                    prioridades[modelo.getPrioridad() - 1]});
            }

            System.out.println("Tabla Indicador actualizada");
        }
    }

    private void addListener() {
        vista.getBtnNuevo().addActionListener(this);
        vista.getBtnAgregar().addActionListener(this);
        vista.getBtnModificar().addActionListener(this);
        vista.getBtnEliminar().addActionListener(this);
        vista.getTabla().addMouseListener(this);
        vista.getTxtNombre().addKeyListener(this);
    }

    private void limpiar() {
        vista.getTxtNombre().setText("");
        vista.getCbxPrioridad().setSelectedIndex(0);
    }

    private boolean validar() {
        bol = true;
        msj = "";

        if (vista.getTxtNombre().getText().isEmpty()) {
            msj += "El campo Nombre no puede estar vacío\n";
            bol = false;
        }
        if (vista.getCbxPrioridad().getSelectedIndex() == 0) {
            msj += "Seleccione una Prioridad";
            bol = false;
        }

        if (!bol) {
            JOptionPane.showMessageDialog(null, msj, "Advertencia", JOptionPane.WARNING_MESSAGE);
        }

        return true;
    }

    private void agregar() {
        modelo.setNombre(vista.getTxtNombre().getText());
        int indice = vista.getCbxPrioridad().getSelectedIndex();
        modelo.setPrioridad(indice);

        modelo.insert();

        try {
            datos = modelo.selectTodo();
            actualizarTabla(datos);
        } catch (SQLException ex) {
            Logger.getLogger(CIndicador.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiar();
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void modificar() {
        modelo.setNombre(vista.getTxtNombre().getText());
        int indice = vista.getCbxPrioridad().getSelectedIndex();
        modelo.setPrioridad(indice);

        modelo.update();

        try {
            datos = modelo.selectTodo();
            actualizarTabla(datos);
        } catch (SQLException ex) {
            Logger.getLogger(CIndicador.class.getName()).log(Level.SEVERE, null, ex);
        }

        limpiar();
        vista.getBtnAgregar().setEnabled(true);
        vista.getBtnModificar().setEnabled(false);
        vista.getBtnEliminar().setEnabled(false);
    }

    private void eliminar() {
        int filaSeleccionada = vista.getTabla().getSelectedRow();

        if (filaSeleccionada >= 0) {
            int id;
            id = datos[filaSeleccionada].getId();
            modelo.setId(id);
            modelo.delete();

            try {
                datos = modelo.selectTodo();
                actualizarTabla(datos);
            } catch (SQLException ex) {
                Logger.getLogger(CIndicador.class.getName()).log(Level.SEVERE, null, ex);
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
            String nombre;
            int prioridad;
            nombre = datos[filaSeleccionada].getNombre();
            prioridad = datos[filaSeleccionada].getPrioridad();

            vista.getTxtNombre().setText(nombre);
            vista.getCbxPrioridad().setSelectedItem(prioridades[prioridad - 1]);

            vista.getBtnAgregar().setEnabled(false);
            vista.getBtnModificar().setEnabled(true);
            vista.getBtnEliminar().setEnabled(true);
        }
    }

}
