package vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdmin extends JFrame {

    public PanelAdmin() {
        setTitle("Panel de Administrador - TimeSync");
        setSize(400, 250);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Bienvenido al Panel de Administrador");
        lblTitulo.setBounds(50, 20, 300, 25);
        add(lblTitulo);

        JButton btnVerUsuarios = new JButton("Ver Usuarios");
        btnVerUsuarios.setBounds(100, 60, 200, 30);
        add(btnVerUsuarios);

        JButton btnVerEventos = new JButton("Ver Eventos");
        btnVerEventos.setBounds(100, 100, 200, 30);
        add(btnVerEventos);

        JButton btnSalir = new JButton("Cerrar Sesión");
        btnSalir.setBounds(100, 140, 200, 30);
        add(btnSalir);

        // Acciones
        btnVerUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí podrías mostrar un JOptionPane o abrir otra ventana
                JOptionPane.showMessageDialog(null, "Funcionalidad de ver usuarios aún no implementada.");
            }
        });

        btnVerEventos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí podrías mostrar eventos existentes o estadísticas
                JOptionPane.showMessageDialog(null, "Funcionalidad de ver eventos aún no implementada.");
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el panel
                new Login().setVisible(true); // Retorna al login
            }
        });
    }
}
