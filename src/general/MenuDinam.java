package general;

import static consie.Consie.app;
import static consie.Consie.usuario;
import controlador.CAdminAsistencia;
import controlador.CAdminEscuela;
import controlador.CCargo;
import controlador.CEscuela;
import controlador.CEstado;
import controlador.CEstudiante;
import controlador.CFuncionario;
import controlador.CGrado;
import controlador.CVentana;
import controlador.CInicioSesion;
import controlador.CMunicipio;
import controlador.CNivelPedagogico;
import controlador.CParroquia;
import controlador.CPersonal;
import controlador.CRecaudo;
import controlador.CRepresentante;
import controlador.CSeccion;
import controlador.CSeguimientoPersonal;
import controlador.CUsuario;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import modelo.MFuncion;

/**
 *
 * @author Diego
 */
public class MenuDinam implements MouseListener {

    private JLabel menuInicio;
    private JLabel menuCargo;
    private JLabel menuEscuela;
    private JLabel menuEstado;
    private JLabel menuEstudiante;
    private JLabel menuFuncionario;
    private JLabel menuGrado;
    private JLabel menuMunicipio;
    private JLabel menuParroquia;
    private JLabel menuPersonal;
    private JLabel menuRecaudo;
    private JLabel menuRepresentante;
    private JLabel menuSeccion;
    private JLabel menuUsuario;
    private JLabel menuPedagogico;
    private JLabel menuAdminPersonal;
    private JLabel menuAdminEscuela;
    private JLabel menuEstadistica;
    private JLabel menuCerrarSesion;

    private Icon iconoInicio;
    private Icon iconoCargo;
    private Icon iconoEscuela;
    private Icon iconoEstado;
    private Icon iconoEstudiante;
    private Icon iconoFuncionario;
    private Icon iconoGrado;
    private Icon iconoMunicipio;
    private Icon iconoParroquia;
    private Icon iconoPersonal;
    private Icon iconoRecaudo;
    private Icon iconoRepresentante;
    private Icon iconoSeccion;
    private Icon iconoUsuario;
    
    private MFuncion[] funciones;

    public MenuDinam() {
        iconoInicio = new ImageIcon("img/icons8_home_30px.png");
        iconoCargo = new ImageIcon("img/icons8_work_30px.png");
        iconoEscuela = new ImageIcon("img/icons8_school_building_30px.png");
        iconoEstado = new ImageIcon("img/icons8_map_marker_30px.png");
        iconoEstudiante = new ImageIcon("img/icons8_students_30px_2.png");
        iconoFuncionario = new ImageIcon("img/icons8_administrator_female_30px_1.png");
        iconoGrado = new ImageIcon("img/icons8_graduation_cap_30px.png");
        iconoMunicipio = new ImageIcon("img/icons8_map_marker_30px.png");
        iconoParroquia = new ImageIcon("img/icons8_map_marker_30px.png");
        iconoPersonal = new ImageIcon("img/icons8_contract_job_30px.png");
        iconoRecaudo = new ImageIcon("img/icons8_documents_30px.png");
        iconoRepresentante = new ImageIcon("img/icons8_family_30px.png");
        iconoSeccion = new ImageIcon("img/icons8_class_30px.png");
        iconoUsuario = new ImageIcon("img/icons8_user_30px.png");
        
        menuInicio = new JLabel("Inicio");
        menuInicio.setFont(new Font("Tahoma", 0, 18));
        menuInicio.setForeground(new Color(255, 255, 255));
        menuInicio.setIcon(iconoInicio);

        menuCargo = new JLabel("Cargo");
        menuCargo.setFont(new Font("Tahoma", 0, 18));
        menuCargo.setForeground(new Color(255, 255, 255));
        menuCargo.setIcon(iconoCargo);

        menuEscuela = new JLabel("Escuela");
        menuEscuela.setFont(new Font("Tahoma", 0, 18));
        menuEscuela.setForeground(new Color(255, 255, 255));
        menuEscuela.setIcon(iconoEscuela);

        menuEstado = new JLabel("Estado");
        menuEstado.setFont(new Font("Tahoma", 0, 18));
        menuEstado.setForeground(new Color(255, 255, 255));
        menuEstado.setIcon(iconoEstado);

        menuEstudiante = new JLabel("Estudiante");
        menuEstudiante.setFont(new Font("Tahoma", 0, 18));
        menuEstudiante.setForeground(new Color(255, 255, 255));
        menuEstudiante.setIcon(iconoEstudiante);

        menuFuncionario = new JLabel("Funcionario");
        menuFuncionario.setFont(new Font("Tahoma", 0, 18));
        menuFuncionario.setForeground(new Color(255, 255, 255));
        menuFuncionario.setIcon(iconoFuncionario);

        menuGrado = new JLabel("Grado");
        menuGrado.setFont(new Font("Tahoma", 0, 18));
        menuGrado.setForeground(new Color(255, 255, 255));
        menuGrado.setIcon(iconoGrado);

        menuMunicipio = new JLabel("Municipio");
        menuMunicipio.setFont(new Font("Tahoma", 0, 18));
        menuMunicipio.setForeground(new Color(255, 255, 255));
        menuMunicipio.setIcon(iconoMunicipio);

        menuParroquia = new JLabel("Parroquia");
        menuParroquia.setFont(new Font("Tahoma", 0, 18));
        menuParroquia.setForeground(new Color(255, 255, 255));
        menuParroquia.setIcon(iconoParroquia);

        menuPersonal = new JLabel("Personal");
        menuPersonal.setFont(new Font("Tahoma", 0, 18));
        menuPersonal.setForeground(new Color(255, 255, 255));
        menuPersonal.setIcon(iconoPersonal);

        menuRecaudo = new JLabel("Recaudo");
        menuRecaudo.setFont(new Font("Tahoma", 0, 18));
        menuRecaudo.setForeground(new Color(255, 255, 255));
        menuRecaudo.setIcon(iconoRecaudo);

        menuRepresentante = new JLabel("Representante");
        menuRepresentante.setFont(new Font("Tahoma", 0, 18));
        menuRepresentante.setForeground(new Color(255, 255, 255));
        menuRepresentante.setIcon(iconoRepresentante);

        menuSeccion = new JLabel("Seccion");
        menuSeccion.setFont(new Font("Tahoma", 0, 18));
        menuSeccion.setForeground(new Color(255, 255, 255));
        menuSeccion.setIcon(iconoSeccion);

        menuUsuario = new JLabel("Usuario");
        menuUsuario.setFont(new Font("Tahoma", 0, 18));
        menuUsuario.setForeground(new Color(255, 255, 255));
        menuUsuario.setIcon(iconoUsuario);

        menuPedagogico = new JLabel("Nivel Pedagógico");
        menuAdminPersonal = new JLabel("Administrar Personal");
        menuAdminEscuela = new JLabel("Administrar Escuela");
        menuEstadistica = new JLabel("Estadística");

        menuCerrarSesion = new JLabel("Cerrar Sesión");

        addListener();
    }

    public void generarMenu(JPanel panelMenu) {
        int x, y, w, h;
        x = 20;
        y = 20;
        w = 200;
        h = 30;
        funciones = usuario.getFunciones();
        panelMenu.add(menuInicio);
        menuInicio.setBounds(x, y, w, h);

        if (funciones != null) {
            for (MFuncion funcionesX : funciones) {
                if ("cargo".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuCargo);
                    menuCargo.setBounds(x, y += 30, w, h);
                }
                if ("escuela".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuEscuela);
                    menuEscuela.setBounds(x, y += 30, w, h);
                }
                if ("estado".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuEstado);
                    menuEstado.setBounds(x, y += 30, w, h);
                }
                if ("estudiante".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuEstudiante);
                    menuEstudiante.setBounds(x, y += 30, w, h);
                }
                if ("funcionario".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuFuncionario);
                    menuFuncionario.setBounds(x, y += 30, w, h);
                }
                if ("grado".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuGrado);
                    menuGrado.setBounds(x, y += 30, w, h);
                }
                if ("municipio".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuMunicipio);
                    menuMunicipio.setBounds(x, y += 30, w, h);
                }
                if ("parroquia".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuParroquia);
                    menuParroquia.setBounds(x, y += 30, w, h);
                }
                if ("personal".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuPersonal);
                    menuPersonal.setBounds(x, y += 30, w, h);
                }
                if ("recaudo".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuRecaudo);
                    menuRecaudo.setBounds(x, y += 30, w, h);
                }
                if ("representante".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuRepresentante);
                    menuRepresentante.setBounds(x, y += 30, w, h);
                }
                if ("seccion".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuSeccion);
                    menuSeccion.setBounds(x, y += 30, w, h);
                }
                if ("usuario".equals(funcionesX.getNombre())) {
                    panelMenu.add(menuUsuario);
                    menuUsuario.setBounds(x, y += 30, w, h);
                }
            }
        }
    }

    private void addListener() {
        menuInicio.addMouseListener(this);

        menuCargo.addMouseListener(this);
        menuEscuela.addMouseListener(this);
        menuEstado.addMouseListener(this);
        menuEstudiante.addMouseListener(this);
        menuFuncionario.addMouseListener(this);
        menuGrado.addMouseListener(this);
        menuMunicipio.addMouseListener(this);
        menuParroquia.addMouseListener(this);
        menuPersonal.addMouseListener(this);
        menuRecaudo.addMouseListener(this);
        menuRepresentante.addMouseListener(this);
        menuSeccion.addMouseListener(this);
        menuUsuario.addMouseListener(this);

        menuPedagogico.addMouseListener(this);
        menuAdminPersonal.addMouseListener(this);
        menuAdminEscuela.addMouseListener(this);
        menuEstadistica.addMouseListener(this);

        menuCerrarSesion.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent me) {
        if (me.getSource() == menuInicio) {
            app = new CVentana();
        } else if (me.getSource() == menuCargo) {
            app = new CCargo();
        } else if (me.getSource() == menuEscuela) {
            app = new CEscuela();
        } else if (me.getSource() == menuEstado) {
            app = new CEstado();
        } else if (me.getSource() == menuEstudiante) {
            app = new CEstudiante();
        } else if (me.getSource() == menuFuncionario) {
            app = new CFuncionario();
        } else if (me.getSource() == menuGrado) {
            app = new CGrado();
        } else if (me.getSource() == menuMunicipio) {
            app = new CMunicipio();
        } else if (me.getSource() == menuParroquia) {
            app = new CParroquia();
        } else if (me.getSource() == menuPersonal) {
            app = new CPersonal();
        } else if (me.getSource() == menuRecaudo) {
            app = new CRecaudo();
        } else if (me.getSource() == menuRepresentante) {
            app = new CRepresentante();
        } else if (me.getSource() == menuSeccion) {
            app = new CSeccion();
        } else if (me.getSource() == menuUsuario) {
            app = new CUsuario();
        } else if (me.getSource() == menuPedagogico) {
            app = new CNivelPedagogico();
        } else if (me.getSource() == menuAdminPersonal) {
            app = new CSeguimientoPersonal();
        } else if (me.getSource() == menuAdminEscuela) {
            app = new CAdminEscuela();
        } else if (me.getSource() == menuEstadistica) {
            app = new CAdminAsistencia();
        } else if (me.getSource() == menuCerrarSesion) {
            app = new CInicioSesion();
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

}
