package vista;

import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuOrganizador extends JFrame {

    private Usuario organizador;

    public MenuOrganizador(Usuario organizador) {
        this.organizador = organizador;

        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Panel del Organizador - TimeSync");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Fondo principal
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(20, 20, 20));
        add(fondo, BorderLayout.CENTER);

        // Panel izquierdo con saludo y botones
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(250, getHeight()));
        panelIzquierdo.setBackground(new Color(30, 30, 30));
        panelIzquierdo.setBorder(BorderFactory.createEmptyBorder(30, 20, 30, 20));
        fondo.add(panelIzquierdo, BorderLayout.WEST);

        JLabel lblSaludo = new JLabel("Hola, " + organizador.getNombre());
        lblSaludo.setFont(new Font("SansSerif", Font.BOLD, 18));
        lblSaludo.setForeground(Color.WHITE);
        lblSaludo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaludo.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panelIzquierdo.add(lblSaludo);

        // Botones
        JButton btnVer = new JButton("Ver eventos");
        JButton btnCrear = new JButton("Crear evento");
        JButton btnEliminar = new JButton("Eliminar eventos");

        Dimension buttonSize = new Dimension(200, 40);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 14);

        for (JButton btn : new JButton[]{btnVer, btnCrear, btnEliminar}) {
            btn.setMaximumSize(buttonSize);
            btn.setFont(buttonFont);
            btn.setBackground(new Color(0, 122, 255));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorderPainted(false);
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            panelIzquierdo.add(btn);
            panelIzquierdo.add(Box.createVerticalStrut(15));
        }

        // Panel derecho con logo
        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBackground(new Color(20, 20, 20));
        fondo.add(panelDerecho, BorderLayout.CENTER);

        JLabel imagen = new JLabel(new ImageIcon("src/vista/TimeSync_resized_250x200.png"));
        imagen.setHorizontalAlignment(JLabel.CENTER);
        panelDerecho.add(imagen, BorderLayout.CENTER);

        // Acciones
        btnVer.addActionListener((ActionEvent e) -> {
            new PanelOrganizadorVerEventos(organizador).setVisible(true);
        });

        btnCrear.addActionListener((ActionEvent e) -> {
            new PanelOrganizador(organizador).setVisible(true);
        });

        btnEliminar.addActionListener((ActionEvent e) -> {
            new EliminarEvento(organizador).setVisible(true);
        });
    
    }
}
