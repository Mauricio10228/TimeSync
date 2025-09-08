package vista;

import controlador.UsuarioDAO;
import modelo.Usuario;

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
        setTitle("Registro de Usuario");
        setSize(450, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(new Color(173, 216, 230)); // Azul celeste suave

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(50, 40, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(160, 40, 200, 25);
        add(txtNombre);

        JLabel lblCorreo = new JLabel("Correo:");
        lblCorreo.setBounds(50, 80, 100, 25);
        add(lblCorreo);

        txtCorreo = new JTextField();
        txtCorreo.setBounds(160, 80, 200, 25);
        add(txtCorreo);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 120, 100, 25);
        add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(160, 120, 200, 25);
        add(txtContrasena);

        JLabel lblRol = new JLabel("Rol:");
        lblRol.setBounds(50, 160, 100, 25);
        add(lblRol);

        cbRol = new JComboBox<>(new String[]{"cliente", "organizador"});
        cbRol.setBounds(160, 160, 200, 25);
        add(cbRol);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(140, 220, 160, 35);
        add(btnRegistrar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(140, 270, 160, 35);
        add(btnVolver);

        // Logo TimeSync en esquina inferior izquierda
        JLabel lblLogo = new JLabel();
      ImageIcon icon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_500x400.png"));
lblLogo.setIcon(new ImageIcon(icon.getImage().getScaledInstance(100, 80, Image.SCALE_SMOOTH)));
lblLogo.setBounds(10, 370, 100, 80); // <- coincide con el escalado
add(lblLogo);

        // Acción botón Registrar
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

        // Acción botón Volver
        btnVolver.addActionListener((ActionEvent e) -> {
            new Login().setVisible(true);
            dispose();
        });
    }
}
