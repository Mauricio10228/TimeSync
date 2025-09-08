package vista;

import controlador.EventoDAO;
import java.awt.Color;
import modelo.Evento;
import modelo.Usuario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOrganizador extends JFrame {

    private JTextField txtTitulo, txtDescripcion, txtFecha, txtLugar, txtPrecio, txtCupo;
    private JButton btnCrear;
    private Usuario organizador; // Guarda el usuario que organiza

    public PanelOrganizador(Usuario organizador) {
        Color azulSuave = new Color(173, 216, 230);
    getContentPane().setBackground(azulSuave);

        this.organizador = organizador;
        setTitle("Panel de Organizador - Crear Evento");
        setSize(400, 420); // Aumentado para incluir campo Lugar
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(30, 30, 100, 25);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(140, 30, 200, 25);
        add(txtTitulo);

        JLabel lblDescripcion = new JLabel("Descripción:");
        lblDescripcion.setBounds(30, 70, 100, 25);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(140, 70, 200, 25);
        add(txtDescripcion);

        JLabel lblFecha = new JLabel("Fecha (YYYY-MM-DD):");
        lblFecha.setBounds(30, 110, 150, 25);
        add(lblFecha);

        txtFecha = new JTextField();
        txtFecha.setBounds(180, 110, 160, 25);
        add(txtFecha);

        JLabel lblLugar = new JLabel("Lugar:");
        lblLugar.setBounds(30, 150, 100, 25);
        add(lblLugar);

        txtLugar = new JTextField();
        txtLugar.setBounds(140, 150, 200, 25);
        add(txtLugar);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(30, 190, 100, 25);
        add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(140, 190, 200, 25);
        add(txtPrecio);

        JLabel lblCupo = new JLabel("Cupo Máximo:");
        lblCupo.setBounds(30, 230, 100, 25);
        add(lblCupo);

        txtCupo = new JTextField();
        txtCupo.setBounds(140, 230, 200, 25);
        add(txtCupo);

        btnCrear = new JButton("Crear Evento");
        btnCrear.setBounds(120, 280, 150, 30);
        add(btnCrear);

        btnCrear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String titulo = txtTitulo.getText();
                    String descripcion = txtDescripcion.getText();
                    String fecha = txtFecha.getText();
                    String lugar = txtLugar.getText();
                    double precio = Double.parseDouble(txtPrecio.getText());
                    int cupo = Integer.parseInt(txtCupo.getText());

                    Evento evento = new Evento(
                        titulo,
                        descripcion,
                        fecha,
                        lugar,
                        precio,
                        cupo,
                        organizador.getId()
                    );

                    EventoDAO dao = new EventoDAO();

                    if (dao.insertarEvento(evento)) {
                        JOptionPane.showMessageDialog(PanelOrganizador.this,
                                "Evento creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(PanelOrganizador.this,
                                "Error al crear evento", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PanelOrganizador.this,
                            "Datos numéricos inválidos", "Error 777", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
