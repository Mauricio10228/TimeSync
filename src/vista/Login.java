package vista;

import controlador.UsuarioDAO;
import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    public Login() {
        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Inicio de Sesión - TimeSync");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500, 750);
        setLayout(new BorderLayout());

        // Panel de fondo
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

        // Tarjeta con elementos
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(40, 40, 40));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        tarjeta.setOpaque(true);

        // Logo
        JLabel lblLogo = new JLabel();
        lblLogo.setAlignmentX(CENTER_ALIGNMENT);
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_250x200.png"));
        lblLogo.setIcon(logoIcon);
        tarjeta.add(lblLogo);

        tarjeta.add(Box.createVerticalStrut(10));

        // Título
        JLabel lblTitulo = new JLabel("Iniciar sesión");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(lblTitulo);

        tarjeta.add(Box.createVerticalStrut(25));

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

        tarjeta.add(Box.createVerticalStrut(30));

        // Botón login
        btnLogin = new JButton("Iniciar sesión");
        btnLogin.setAlignmentX(CENTER_ALIGNMENT);
        btnLogin.setPreferredSize(new Dimension(200, 40));
        btnLogin.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnLogin.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnLogin.setBackground(new Color(0, 122, 255));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        btnLogin.setBorderPainted(false);
        tarjeta.add(btnLogin);

        tarjeta.add(Box.createVerticalStrut(25));

        // Texto pregunta
        JLabel lblPregunta = new JLabel("¿No tienes cuenta?");
        lblPregunta.setForeground(new Color(180, 180, 180));
        lblPregunta.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(lblPregunta);

        tarjeta.add(Box.createVerticalStrut(5));

      // Botón registrarse (estilo enlace con hover)
JButton btnRegistrar = new JButton("Registrarse");
btnRegistrar.setAlignmentX(CENTER_ALIGNMENT);
btnRegistrar.setFont(new Font("SansSerif", Font.PLAIN, 14));
btnRegistrar.setForeground(Color.WHITE);
btnRegistrar.setContentAreaFilled(false);
btnRegistrar.setBorderPainted(false);
btnRegistrar.setFocusPainted(false);
btnRegistrar.setCursor(new Cursor(Cursor.HAND_CURSOR));

// Efecto hover: cambia color al pasar el mouse
btnRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        btnRegistrar.setForeground(new Color(0, 122, 255)); // Azul claro
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        btnRegistrar.setForeground(Color.WHITE); // Vuelve al blanco
    }
});

tarjeta.add(btnRegistrar);

        // ScrollPane para evitar cortes
        JScrollPane scrollPane = new JScrollPane(tarjeta);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(370, 550));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centro.add(scrollPane);

        // ----------- Lógica de botones -----------

        btnLogin.addActionListener((ActionEvent e) -> {
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.autenticar(correo, contrasena);

            if (usuario != null) {
                switch (usuario.getRol().trim().toLowerCase()) {
                    case "organizador":
                        new MenuOrganizador(usuario).setVisible(true);
                        break;
                    case "cliente":
                        new MenuCliente(usuario).setVisible(true);
                        break;
                    case "admin":
                        new PanelAdmin().setVisible(true);
                        break;
                    default:
                        JOptionPane.showMessageDialog(this, "Rol desconocido");
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnRegistrar.addActionListener(e -> {
            new Registro().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
