package vista;

import controlador.UsuarioDAO;
import modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    public Login() {
        setTitle("Inicio de Sesión - TimeSync");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Fondo azul brillante
        getContentPane().setBackground(new Color(173, 216, 230)); // Azul celeste suave

        // Logo correctamente cargado desde classpath
        JLabel lblLogo = new JLabel();
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_250x200.png"));
        lblLogo.setIcon(logoIcon);
        lblLogo.setBounds(125, 20, 250, 100); // Centrado y proporcionado
        add(lblLogo);

        // Correo
        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setForeground(Color.BLACK);
        lblCorreo.setFont(new Font("Arial", Font.PLAIN, 16));
        lblCorreo.setBounds(80, 150, 100, 30);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(180, 150, 220, 30);
        txtCorreo.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txtCorreo);

        // Contraseña
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setForeground(Color.BLACK);
        lblContrasena.setFont(new Font("Arial", Font.PLAIN, 16));
        lblContrasena.setBounds(80, 200, 100, 30);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(180, 200, 220, 30);
        txtContrasena.setFont(new Font("Arial", Font.PLAIN, 14));
        add(txtContrasena);

        // Botón Iniciar Sesión
        btnLogin = new JButton("Iniciar Sesión");
        btnLogin.setBounds(150, 270, 180, 40);
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.setFont(new Font("Arial", Font.BOLD, 16));
        add(btnLogin);

        // Botón Registrarse
        JButton btnRegistrar = new JButton("Registrarse");
        btnRegistrar.setBounds(150, 330, 180, 35);
        btnRegistrar.setBackground(Color.WHITE);
        btnRegistrar.setFont(new Font("Arial", Font.PLAIN, 14));
        add(btnRegistrar);

        // Acción para iniciar sesión
        btnLogin.addActionListener((ActionEvent e) -> {
            String correo = txtCorreo.getText();
            String contrasena = new String(txtContrasena.getPassword());

            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.autenticar(correo, contrasena);

            if (usuario != null) {
                String rol = usuario.getRol().trim().toLowerCase();
                switch (rol) {
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

        // Acción para registrarse
        btnRegistrar.addActionListener(e -> {
            new Registro().setVisible(true);
            dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Login().setVisible(true));
    }
}
