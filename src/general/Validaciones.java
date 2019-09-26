/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package general;

import com.sun.glass.events.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Validaciones implements ActionListener, MouseListener {
    
    /*---FUNCIÓN PARA EVITAR EL COPY & PASTE---*/
    public void evitarPegar(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();

        if (evt.isControlDown() || evt.isShiftDown()) {

            JOptionPane.showMessageDialog(null, "¡NO SE PERMITE COPIAR Y PEGAR!", "Error!", JOptionPane.ERROR_MESSAGE);
            evt.consume();

        }

    }

    /*---FUNCIÓN PARA PERMITIR SOLO LETRAS---*/
    public void soloLetras(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();

        String cad = ("" + c).toUpperCase();
        c = cad.charAt(0);
        evt.setKeyChar(c);

        if (!Character.isLetter(c) && (c != KeyEvent.VK_BACKSPACE)) {

            JOptionPane.showMessageDialog(null, "¡NO SE PERMITEN CARACTERES ESPECIALES Y/O NÚMEROS!", "Error!", JOptionPane.ERROR_MESSAGE);
            evt.consume();

        }

    }

    /*---FUNCIÓN PARA PERMITIR SOLO NÚMEROS---*/
    public void soloNumeros(java.awt.event.KeyEvent evt) {

        char c = evt.getKeyChar();

        if (!Character.isDigit(c) && (c != KeyEvent.VK_BACKSPACE)) {

            JOptionPane.showMessageDialog(null, "¡NO SE PERMITEN CARACTERES ESPECIALES Y/O LETRAS!", "Error!", JOptionPane.ERROR_MESSAGE);
            evt.consume();

        }

    }

    /*---FUNCIÓN PARA VALIDAR LA CANTIDAD DE CARACTERES INGRESADO A CAJA DE TEXTOS---*/
    public void Limite(java.awt.event.KeyEvent evt, String e, int a) {

        if (e.length() >= a) {

            JOptionPane.showMessageDialog(null, "¡SE HA EXCEDIDO EL NÚMERO DE CARACTERES PERMITIDO!", "Error!", JOptionPane.ERROR_MESSAGE);
            evt.consume();

        }

    }

    /*---FUNCIÓN PARA EVITAR ESPACIOS---*/
    public void Espacio(java.awt.event.KeyEvent evt) {
        char c = evt.getKeyChar();
        if ((c == ' ')) {

            JOptionPane.showMessageDialog(null, "¡NO SE PERMITEN CARACTERES ESPECIALES!", "Error!", JOptionPane.ERROR_MESSAGE);
            evt.consume();

        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
