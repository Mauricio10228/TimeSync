package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuOrganizador extends JFrame {

    private Usuario organizador;

    public MenuOrganizador(Usuario organizador) {
        this.organizador = organizador;
        setTitle("Panel del Organizador - TimeSync");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color azulSuave = new Color(173, 216, 230); // Azul claro

        // Panel izquierdo con botones y saludo
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setLayout(new BoxLayout(panelIzquierdo, BoxLayout.Y_AXIS));
        panelIzquierdo.setPreferredSize(new Dimension(250, getHeight()));
        panelIzquierdo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelIzquierdo.setBackground(azulSuave);

        JLabel lblSaludo = new JLabel("Hola, " + organizador.getNombre(), JLabel.CENTER);
        lblSaludo.setFont(new Font("Arial", Font.BOLD, 18));
        lblSaludo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblSaludo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        JButton btnVer = new JButton("Ver eventos");
        JButton btnCrear = new JButton("Crear evento");
        JButton btnEliminar = new JButton("Eliminar eventos");

        Dimension buttonSize = new Dimension(200, 40);
        btnVer.setMaximumSize(buttonSize);
        btnCrear.setMaximumSize(buttonSize);
        btnEliminar.setMaximumSize(buttonSize);

        panelIzquierdo.add(lblSaludo);
        panelIzquierdo.add(Box.createVerticalStrut(20));
        panelIzquierdo.add(btnVer);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(btnCrear);
        panelIzquierdo.add(Box.createVerticalStrut(10));
        panelIzquierdo.add(btnEliminar);

        // Panel derecho con logo centrado
        JLabel imagen = new JLabel(new ImageIcon("src/vista/TimeSync_resized_for_organizer.png"));
        imagen.setHorizontalAlignment(JLabel.CENTER);

        JPanel panelDerecho = new JPanel(new BorderLayout());
        panelDerecho.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panelDerecho.setBackground(azulSuave);
        panelDerecho.add(imagen, BorderLayout.CENTER);

        // Agregar paneles al frame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

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
