package vista;

import controlador.UsuarioDAO;
import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Registro extends JFrame {
    private JTextField txtNombre;
    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JComboBox<String> cbRol;
    private JButton btnRegistrar;
    private JButton btnVolver;

    public Registro() {
        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Registro de Usuario - TimeSync");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 750);
        setLayout(new BorderLayout());

        // Fondo oscuro
        JPanel fondo = new JPanel();
        fondo.setBackground(new Color(20, 20, 20));
        fondo.setLayout(new BorderLayout());
        add(fondo, BorderLayout.CENTER);

        // Espacio superior
        JPanel panelSuperior = new JPanel();
        panelSuperior.setOpaque(false);
        panelSuperior.setPreferredSize(new Dimension(0, 80));
        fondo.add(panelSuperior, BorderLayout.NORTH);

        // Panel central
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centro.setOpaque(false);
        fondo.add(centro, BorderLayout.CENTER);

        // Tarjeta
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(40, 40, 40));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        tarjeta.setOpaque(true);

        // Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_250x200.png"));
        lblLogo.setIcon(icon);
        tarjeta.add(lblLogo);

        tarjeta.add(Box.createVerticalStrut(10));

        // Título
        JLabel lblTitulo = new JLabel("Registro de Usuario");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(lblTitulo);

        tarjeta.add(Box.createVerticalStrut(25));

        // Campo nombre
        txtNombre = new JTextField();
        txtNombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtNombre.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtNombre.setBorder(BorderFactory.createTitledBorder("Nombre"));
        tarjeta.add(txtNombre);

        tarjeta.add(Box.createVerticalStrut(20));

        // Campo correo
        txtCorreo = new JTextField();
        txtCorreo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtCorreo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtCorreo.setBorder(BorderFactory.createTitledBorder("Correo"));
        tarjeta.add(txtCorreo);

        tarjeta.add(Box.createVerticalStrut(20));

        // Campo contraseña
        txtContrasena = new JPasswordField();
        txtContrasena.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtContrasena.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtContrasena.setBorder(BorderFactory.createTitledBorder("Contraseña"));
        tarjeta.add(txtContrasena);

        tarjeta.add(Box.createVerticalStrut(20));

        // Combo rol
        cbRol = new JComboBox<>(new String[]{"cliente", "organizador"});
        cbRol.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        cbRol.setFont(new Font("SansSerif", Font.PLAIN, 14));
        cbRol.setBorder(BorderFactory.createTitledBorder("Rol"));
        tarjeta.add(cbRol);

        tarjeta.add(Box.createVerticalStrut(30));

        // Botón registrar
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setAlignmentX(CENTER_ALIGNMENT);
        btnRegistrar.setPreferredSize(new Dimension(200, 40));
        btnRegistrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnRegistrar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnRegistrar.setBackground(new Color(0, 122, 255));
        btnRegistrar.setForeground(Color.WHITE);
        btnRegistrar.setFocusPainted(false);
        btnRegistrar.setBorderPainted(false);
        tarjeta.add(btnRegistrar);

        tarjeta.add(Box.createVerticalStrut(20));

        // Botón volver
        btnVolver = new JButton("Volver");
        btnVolver.setAlignmentX(CENTER_ALIGNMENT);
        btnVolver.setPreferredSize(new Dimension(200, 40));
        btnVolver.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnVolver.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnVolver.setBackground(new Color(100, 100, 100));
        btnVolver.setForeground(Color.WHITE);
        btnVolver.setFocusPainted(false);
        btnVolver.setBorderPainted(false);
        tarjeta.add(btnVolver);

        tarjeta.add(Box.createVerticalGlue());

        // Scroll para evitar cortes
        JScrollPane scrollPane = new JScrollPane(tarjeta);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(370, 550));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centro.add(scrollPane);

        // ----------- Lógica de botones -----------

        btnRegistrar.addActionListener((ActionEvent e) -> {
            String nombre = txtNombre.getText();
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());
            String rol = cbRol.getSelectedItem().toString();

            Usuario usuario = new Usuario(nombre, correo, contrasena, rol);
            UsuarioDAO dao = new UsuarioDAO();

            if (dao.insertarUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
                new Login().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Error al registrar usuario", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnVolver.addActionListener((ActionEvent e) -> {
            new Login().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Registro().setVisible(true));
    }
}
