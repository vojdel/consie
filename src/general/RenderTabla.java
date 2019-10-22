/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import java.awt.Color;
import java.awt.Component;
import java.text.DateFormat;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Daniel
 */
public class RenderTabla extends JCheckBox implements TableCellRenderer {
 
    private JComponent component = new JCheckBox();
 
    /** Constructor de clase */
    public RenderTabla() {
        setOpaque(true);
    }
 
    @Override
  public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
      //Color de fondo de la celda
      ( (JCheckBox) component).setBackground( new Color(0,200,0) );
      //obtiene valor boolean y coloca valor en el JCheckBox
      boolean b = ((Boolean) value).booleanValue();
      ( (JCheckBox) component).setSelected( b );
      return ( (JCheckBox) component);
  }
}
