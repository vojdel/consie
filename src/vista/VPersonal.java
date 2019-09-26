package vista;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Eggo
 */
public class VPersonal extends javax.swing.JPanel {

    /**
     * Creates new form VistaPersonal
     */
    public VPersonal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGrpGenero = new ButtonGroup();
        jLabel3 = new JLabel();
        jSeparator1 = new JSeparator();
        jScrollPane3 = new JScrollPane();
        jPanel5 = new JPanel();
        jLabel4 = new JLabel();
        cbxCedula = new JComboBox<>();
        txtCedula = new JTextField();
        jLabel2 = new JLabel();
        txtPNombre = new JTextField();
        jLabel1 = new JLabel();
        txtSNombre = new JTextField();
        jLabel5 = new JLabel();
        txtPApellido = new JTextField();
        jLabel7 = new JLabel();
        txtSApellido = new JTextField();
        jLabel10 = new JLabel();
        radBtnMasculino = new JRadioButton();
        radBtnFemenino = new JRadioButton();
        jLabel6 = new JLabel();
        cbxTelefono = new JComboBox<>();
        txtTelefono = new JTextField();
        jLabel9 = new JLabel();
        cbxCargo = new JComboBox<>();
        jLabel8 = new JLabel();
        jScrollPane2 = new JScrollPane();
        txtDireccion = new JTextArea();
        btnNuevo = new JButton();
        btnAgregar = new JButton();
        btnModificar = new JButton();
        btnEliminar = new JButton();
        jScrollPane1 = new JScrollPane();
        tabla = new JTable();

        setBackground(new Color(255, 255, 255));

        jLabel3.setFont(new Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setText(" Personal");
        jLabel3.setName("jLabel3"); // NOI18N

        jSeparator1.setName("jSeparator1"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jPanel5.setBackground(new Color(255, 255, 255));
        jPanel5.setFont(new Font("Corbel", 0, 12)); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        jLabel4.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Cédula:");
        jLabel4.setHorizontalTextPosition(SwingConstants.CENTER);
        jLabel4.setName("jLabel4"); // NOI18N

        cbxCedula.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        cbxCedula.setModel(new DefaultComboBoxModel<>(new String[] { "V", "E" }));
        cbxCedula.setName("cbxCedula"); // NOI18N

        txtCedula.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        txtCedula.setName("txtCedula"); // NOI18N

        jLabel2.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Primer Nombre:");
        jLabel2.setName("jLabel2"); // NOI18N

        txtPNombre.setFont(new Font("Corbel", 0, 12)); // NOI18N
        txtPNombre.setName("txtPNombre"); // NOI18N

        jLabel1.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("Segundo Nombre:");
        jLabel1.setName("jLabel1"); // NOI18N

        txtSNombre.setName("txtSNombre"); // NOI18N

        jLabel5.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Primer Apellido:");
        jLabel5.setName("jLabel5"); // NOI18N

        txtPApellido.setFont(new Font("Corbel", 0, 12)); // NOI18N
        txtPApellido.setName("txtPApellido"); // NOI18N

        jLabel7.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Segundo Apellido:");
        jLabel7.setName("jLabel7"); // NOI18N

        txtSApellido.setName("txtSApellido"); // NOI18N

        jLabel10.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Género:");
        jLabel10.setName("jLabel10"); // NOI18N

        radBtnMasculino.setBackground(new Color(255, 255, 255));
        btnGrpGenero.add(radBtnMasculino);
        radBtnMasculino.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        radBtnMasculino.setText("Masculino");
        radBtnMasculino.setName("radBtnMasculino"); // NOI18N

        radBtnFemenino.setBackground(new Color(255, 255, 255));
        btnGrpGenero.add(radBtnFemenino);
        radBtnFemenino.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        radBtnFemenino.setText("Femenino");
        radBtnFemenino.setName("radBtnFemenino"); // NOI18N

        jLabel6.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Teléfono:");
        jLabel6.setName("jLabel6"); // NOI18N

        cbxTelefono.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        cbxTelefono.setModel(new DefaultComboBoxModel<>(new String[] { "----", "0412", "0414", "0424", "0416", "0426", "0254" }));
        cbxTelefono.setName("cbxTelefono"); // NOI18N

        txtTelefono.setName("txtTelefono"); // NOI18N

        jLabel9.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel9.setText("Cargo:");
        jLabel9.setName("jLabel9"); // NOI18N

        cbxCargo.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        cbxCargo.setName("cbxCargo"); // NOI18N

        jLabel8.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Dirección:");
        jLabel8.setName("jLabel8"); // NOI18N

        jScrollPane2.setFont(new Font("Corbel", 0, 12)); // NOI18N
        jScrollPane2.setName("jScrollPane2"); // NOI18N
        jScrollPane2.setPreferredSize(new Dimension(100, 50));
        jScrollPane2.setRequestFocusEnabled(false);

        txtDireccion.setColumns(20);
        txtDireccion.setLineWrap(true);
        txtDireccion.setRows(3);
        txtDireccion.setName("txtDireccion"); // NOI18N
        jScrollPane2.setViewportView(txtDireccion);

        btnNuevo.setBackground(new Color(238, 24, 24));
        btnNuevo.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        btnNuevo.setForeground(new Color(255, 255, 255));
        btnNuevo.setText("Nuevo");
        btnNuevo.setBorder(new MatteBorder(null));
        btnNuevo.setBorderPainted(false);
        btnNuevo.setName("btnNuevo"); // NOI18N

        btnAgregar.setBackground(new Color(238, 24, 24));
        btnAgregar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        btnAgregar.setForeground(new Color(255, 255, 255));
        btnAgregar.setText("Agregar");
        btnAgregar.setBorder(new MatteBorder(null));
        btnAgregar.setBorderPainted(false);
        btnAgregar.setName("btnAgregar"); // NOI18N

        btnModificar.setBackground(new Color(238, 24, 24));
        btnModificar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        btnModificar.setForeground(new Color(255, 255, 255));
        btnModificar.setText("Modificar");
        btnModificar.setBorder(new MatteBorder(null));
        btnModificar.setBorderPainted(false);
        btnModificar.setName("btnModificar"); // NOI18N

        btnEliminar.setBackground(new Color(238, 24, 24));
        btnEliminar.setFont(new Font("Tahoma", 0, 12)); // NOI18N
        btnEliminar.setForeground(new Color(255, 255, 255));
        btnEliminar.setText("Eliminar");
        btnEliminar.setBorder(new MatteBorder(null));
        btnEliminar.setBorderPainted(false);
        btnEliminar.setName("btnEliminar"); // NOI18N

        GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6)
                    .addComponent(jLabel9)
                    .addComponent(jLabel8))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cbxCedula, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtSApellido, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(txtPApellido, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(txtSNombre, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                .addComponent(txtPNombre, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(cbxTelefono, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(radBtnMasculino)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(radBtnFemenino))
                            .addComponent(cbxCargo, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(cbxCedula, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedula, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtSNombre, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPApellido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSApellido, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(btnNuevo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAgregar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnModificar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminar, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(radBtnMasculino)
                    .addComponent(radBtnFemenino))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbxTelefono, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(cbxCargo, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel5);

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        tabla.setModel(new DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.setName("tabla"); // NOI18N
        jScrollPane1.setViewportView(tabla);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jSeparator1)
                    .addComponent(jLabel3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 251, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public JButton btnAgregar;
    public JButton btnEliminar;
    public JButton btnModificar;
    public JButton btnNuevo;
    public ButtonGroup btnGrpGenero;
    public JComboBox<String> cbxCargo;
    public JComboBox<String> cbxCedula;
    public JComboBox<String> cbxTelefono;
    public JLabel jLabel1;
    public JLabel jLabel10;
    public JLabel jLabel2;
    public JLabel jLabel3;
    public JLabel jLabel4;
    public JLabel jLabel5;
    public JLabel jLabel6;
    public JLabel jLabel7;
    public JLabel jLabel8;
    public JLabel jLabel9;
    public JPanel jPanel5;
    public JScrollPane jScrollPane1;
    public JScrollPane jScrollPane2;
    public JScrollPane jScrollPane3;
    public JSeparator jSeparator1;
    public JRadioButton radBtnFemenino;
    public JRadioButton radBtnMasculino;
    public JTable tabla;
    public JTextField txtCedula;
    public JTextArea txtDireccion;
    public JTextField txtPApellido;
    public JTextField txtPNombre;
    public JTextField txtSApellido;
    public JTextField txtSNombre;
    public JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables

    public JButton getBtnNuevo() {
        return btnNuevo;
    }

    public JTable getTabla() {
        return tabla;
    }

    public JButton getBtnAgregar() {
        return btnAgregar;
    }

    public JButton getBtnEliminar() {
        return btnEliminar;
    }

    public ButtonGroup getBtnGrpGenero() {
        return btnGrpGenero;
    }

    public JButton getBtnModificar() {
        return btnModificar;
    }

    public JComboBox<String> getCbxCedula() {
        return cbxCedula;
    }

    public JRadioButton getRadBtnFemenino() {
        return radBtnFemenino;
    }

    public JRadioButton getRadBtnMasculino() {
        return radBtnMasculino;
    }

    public JTextField getTxtCedula() {
        return txtCedula;
    }

    public JTextArea getTxtDireccion() {
        return txtDireccion;
    }

    public JComboBox<String> getCbxTelefono() {
        return cbxTelefono;
    }

    public JTextField getTxtTelefono() {
        return txtTelefono;
    }
    
    public JTextField getTxtPApellido() {
        return txtPApellido;
    }

    public JTextField getTxtPNombre() {
        return txtPNombre;
    }

    public JTextField getTxtSApellido() {
        return txtSApellido;
    }

    public JTextField getTxtSNombre() {
        return txtSNombre;
    }

   /* public JDateChooser getDateChooserFNacimiento() {
        return dateChooserFNacimiento;
    }*/

    public JComboBox<String> getCbxCargo() {
        return cbxCargo;
    }
}
