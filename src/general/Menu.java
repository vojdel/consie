package general;

import static consie.Consie.app;
import controlador.CAdminAsistencia;
import controlador.CAdminEscuela;
import controlador.CCargo;
import controlador.CEscuela;
import controlador.CEstado;
import controlador.CEstudiante;
import controlador.CFuncionario;
import controlador.CGrado;
import controlador.CVentana;
import controlador.CMunicipio;
import controlador.CNivelPedagogico;
import controlador.CParroquia;
import controlador.CPersonal;
import controlador.CRecaudo;
import controlador.CRepresentante;
import controlador.CSeccion;
import controlador.CSeguimientoPersonal;
import controlador.CUsuario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Eggo
 */
public class Menu implements ActionListener {

    private final JMenuBar menuBar;
    private final JMenu menuArchivo;
    private final JMenu menuMaestro;
    private final JMenu menuProcesos;
    private final JMenu menuReportes;
    private final JMenuItem menuItemInicio;
    private final JMenuItem menuItemCargo;
    private final JMenuItem menuItemEscuela;
    private final JMenuItem menuItemEstado;
    private final JMenuItem menuItemEstudiante;
    private final JMenuItem menuItemFuncionario;
    private final JMenuItem menuItemGrado;
    private final JMenuItem menuItemMunicipio;
    private final JMenuItem menuItemParroquia;
    private final JMenuItem menuItemPersonal;
    private final JMenuItem menuItemRecaudo;
    private final JMenuItem menuItemRepresentante;
    private final JMenuItem menuItemSeccion;
    private final JMenuItem menuItemUsuario;
    private final JMenuItem menuItemNivelPedagogico;
    private final JMenuItem menuItemAdminEscuela;
    private final JMenuItem menuItemAdminPersonal;
    private final JMenuItem menuItemAdminAsistencia;

    public Menu() {
        menuBar = new JMenuBar();

        menuArchivo = new JMenu("Archivo");
        menuMaestro = new JMenu("Maestro");
        menuProcesos = new JMenu("Procesos");
        menuReportes = new JMenu("Reportes");

        menuItemInicio = new JMenuItem("Inicio");

        menuItemCargo = new JMenuItem("Cargo");
        menuItemEscuela = new JMenuItem("Escuela");
        menuItemEstado = new JMenuItem("Estado");
        menuItemEstudiante = new JMenuItem("Estudiante");
        menuItemFuncionario = new JMenuItem("Funcionario");
        menuItemGrado = new JMenuItem("Grado");
        menuItemMunicipio = new JMenuItem("Municipio");
        menuItemParroquia = new JMenuItem("Parroquia");
        menuItemPersonal = new JMenuItem("Personal");
        menuItemRecaudo = new JMenuItem("Recaudo");
        menuItemRepresentante = new JMenuItem("Representante");
        menuItemSeccion = new JMenuItem("Sección");
        menuItemUsuario = new JMenuItem("Usuario");

        menuItemNivelPedagogico = new JMenuItem("Nivel Pedagógico");
        menuItemAdminEscuela = new JMenuItem("Administrar Escuelas");
        menuItemAdminPersonal = new JMenuItem("Administrar Personal");
        menuItemAdminAsistencia = new JMenuItem("Administrar Asistencia");

        menuArchivo.add(menuItemInicio);

        menuMaestro.add(menuItemCargo);
        menuMaestro.add(menuItemEscuela);
        menuMaestro.add(menuItemEstado);
        menuMaestro.add(menuItemEstudiante);
        menuMaestro.add(menuItemFuncionario);
        menuMaestro.add(menuItemGrado);
        menuMaestro.add(menuItemMunicipio);
        menuMaestro.add(menuItemParroquia);
        menuMaestro.add(menuItemPersonal);
        menuMaestro.add(menuItemRecaudo);
        menuMaestro.add(menuItemRepresentante);
        menuMaestro.add(menuItemSeccion);
        menuMaestro.add(menuItemUsuario);

        menuProcesos.add(menuItemNivelPedagogico);
        menuProcesos.add(menuItemAdminEscuela);
        menuProcesos.add(menuItemAdminPersonal);
        menuProcesos.add(menuItemAdminAsistencia);

        menuBar.add(menuArchivo);
        menuBar.add(menuMaestro);
        menuBar.add(menuProcesos);
        menuBar.add(menuReportes);

        addListener();
    }

    private void addListener() {
        menuItemInicio.addActionListener(this);
        menuItemCargo.addActionListener(this);
        menuItemEscuela.addActionListener(this);
        menuItemEstado.addActionListener(this);
        menuItemEstudiante.addActionListener(this);
        menuItemFuncionario.addActionListener(this);
        menuItemGrado.addActionListener(this);
        menuItemMunicipio.addActionListener(this);
        menuItemParroquia.addActionListener(this);
        menuItemPersonal.addActionListener(this);
        menuItemRecaudo.addActionListener(this);
        menuItemRepresentante.addActionListener(this);
        menuItemSeccion.addActionListener(this);
        menuItemUsuario.addActionListener(this);

        menuItemNivelPedagogico.addActionListener(this);
        menuItemAdminEscuela.addActionListener(this);
        menuItemAdminPersonal.addActionListener(this);
        menuItemAdminAsistencia.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == menuItemInicio) {
            app = new CVentana();
        } else if (ae.getSource() == menuItemCargo) {
            app = new CCargo();
        } else if (ae.getSource() == menuItemEscuela) {
            app = new CEscuela();
        } else if (ae.getSource() == menuItemEstado) {
            app = new CEstado();
        } else if (ae.getSource() == menuItemEstudiante) {
            app = new CEstudiante();
        } else if (ae.getSource() == menuItemFuncionario) {
            app = new CFuncionario();
        } else if (ae.getSource() == menuItemGrado) {
            app = new CGrado();
        } else if (ae.getSource() == menuItemMunicipio) {
            app = new CMunicipio();
        } else if (ae.getSource() == menuItemParroquia) {
            app = new CParroquia();
        } else if (ae.getSource() == menuItemPersonal) {
            app = new CPersonal();
        } else if (ae.getSource() == menuItemRecaudo) {
            app = new CRecaudo();
        } else if (ae.getSource() == menuItemRepresentante) {
            app = new CRepresentante();
        } else if (ae.getSource() == menuItemSeccion) {
            app = new CSeccion();
        } else if (ae.getSource() == menuItemUsuario) {
            app = new CUsuario();
        } else if (ae.getSource() == menuItemNivelPedagogico) {
            app = new CNivelPedagogico();
        } else if (ae.getSource() == menuItemAdminEscuela) {
            app = new CAdminEscuela();
        } else if (ae.getSource() == menuItemAdminPersonal) {
            app = new CSeguimientoPersonal();
        } else if (ae.getSource() == menuItemAdminAsistencia) {
            app = new CAdminAsistencia();
        }
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
