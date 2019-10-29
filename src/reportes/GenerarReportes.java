/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import general.BD;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Daniel
 */
public class GenerarReportes {

    private BD con;

    public GenerarReportes() {
    }

    public void reporteUsuarios() {

        try {

            JasperReport reporte = (JasperReport) JRLoader.loadObject("RUsuario.jasper");

            JasperPrint j = JasperFillManager.fillReport(reporte, null, con.getC());
            con.conectar();
            JasperViewer jv = new JasperViewer(j, false);
            jv.setTitle("Reporte Usuarios");
            jv.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al Mostrar el Reporte" + e);
        }
    }

}
