package vista;

import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MenuCliente extends JFrame {

    private Usuario cliente;

    public MenuCliente(Usuario cliente) {
        this.cliente = cliente;

        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Panel del Cliente - TimeSync");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Fondo principal
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(20, 20, 20));
        add(fondo, BorderLayout.CENTER);

        // Panel central
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        centro.setOpaque(false);
        fondo.add(centro, BorderLayout.CENTER);

        // Tarjeta
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(40, 40, 40));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        tarjeta.setPreferredSize(new Dimension(400, 400));
        tarjeta.setOpaque(true);
        centro.add(tarjeta);

        // Logo
        JLabel logo = new JLabel();
        logo.setAlignmentX(CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_250x200.png"));
        logo.setIcon(icon);
        tarjeta.add(logo);

        tarjeta.add(Box.createVerticalStrut(10));

        // Bienvenida
        JLabel lblBienvenida = new JLabel("Bienvenido, " + cliente.getNombre());
        lblBienvenida.setForeground(Color.WHITE);
        lblBienvenida.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblBienvenida.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(lblBienvenida);

        tarjeta.add(Box.createVerticalStrut(30));

        // Botón ver eventos
        JButton btnVerEventos = new JButton("Ver eventos disponibles");
        btnVerEventos.setAlignmentX(CENTER_ALIGNMENT);
        btnVerEventos.setPreferredSize(new Dimension(250, 40));
        btnVerEventos.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnVerEventos.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVerEventos.setBackground(new Color(0, 122, 255));
        btnVerEventos.setForeground(Color.WHITE);
        btnVerEventos.setFocusPainted(false);
        btnVerEventos.setBorderPainted(false);
        tarjeta.add(btnVerEventos);

        tarjeta.add(Box.createVerticalStrut(20));

        // Botón mis inscripciones
        JButton btnMisInscripciones = new JButton("Mis eventos inscritos");
        btnMisInscripciones.setAlignmentX(CENTER_ALIGNMENT);
        btnMisInscripciones.setPreferredSize(new Dimension(250, 40));
        btnMisInscripciones.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnMisInscripciones.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnMisInscripciones.setBackground(new Color(100, 100, 100));
        btnMisInscripciones.setForeground(Color.WHITE);
        btnMisInscripciones.setFocusPainted(false);
        btnMisInscripciones.setBorderPainted(false);
        tarjeta.add(btnMisInscripciones);

        tarjeta.add(Box.createVerticalGlue());

        // Acciones
        btnVerEventos.addActionListener((ActionEvent e) -> {
            new PanelCliente(cliente).setVisible(true);
            dispose();
        });

        btnMisInscripciones.addActionListener((ActionEvent e) -> {
            new EventosInscritos(cliente).setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        Usuario demo = new Usuario("paparruru", "demo@mail.com", "123", "cliente");
        SwingUtilities.invokeLater(() -> new MenuCliente(demo).setVisible(true));
    }
}
