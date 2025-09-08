package vista;

import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuCliente extends JFrame {

    private Usuario cliente;

    public MenuCliente(Usuario cliente) {
        this.cliente = cliente;

        setTitle("Panel del Cliente - TimeSync");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido, " + cliente.getNombre());
        lblBienvenida.setBounds(30, 20, 300, 30);
        add(lblBienvenida);

        JButton btnVerEventos = new JButton("Ver eventos disponibles");
        btnVerEventos.setBounds(180, 80, 220, 30);
        add(btnVerEventos);

        JButton btnMisInscripciones = new JButton("Mis eventos inscritos");
        btnMisInscripciones.setBounds(180, 130, 220, 30);
        add(btnMisInscripciones);

        
        btnVerEventos.addActionListener((ActionEvent e) -> {
            new PanelCliente(cliente).setVisible(true);
        });

        btnMisInscripciones.addActionListener((ActionEvent e) -> {
            new EventosInscritos(cliente).setVisible(true);
        });

       
        
    }
}
