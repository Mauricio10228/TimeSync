package vista;

import controlador.EventoDAO;
import controlador.InscripcionDAO;
import modelo.Evento;
import modelo.Usuario;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class PanelCliente extends JFrame {

    private JTable tablaEventos;
    private Usuario cliente;
    private List<Evento> listaEventos;

    public PanelCliente(Usuario cliente) {
        this.cliente = cliente;

        setTitle("Panel del Cliente - TimeSync");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Color azulSuave = new Color(173, 216, 230);

        // Panel izquierdo: texto y tabla
        JPanel panelIzquierdo = new JPanel();
        panelIzquierdo.setBackground(azulSuave);
        panelIzquierdo.setPreferredSize(new Dimension(500, 0));
        panelIzquierdo.setLayout(null);

        JLabel lblTitulo = new JLabel("Eventos Disponibles");
        lblTitulo.setBounds(20, 20, 200, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panelIzquierdo.add(lblTitulo);

        tablaEventos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        scrollPane.setBounds(20, 60, 450, 200);
        panelIzquierdo.add(scrollPane);

        JButton btnInscribirse = new JButton("Inscribirse en evento");
        btnInscribirse.setBounds(150, 280, 200, 30);
        panelIzquierdo.add(btnInscribirse);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(150, 320, 200, 30);
        panelIzquierdo.add(btnVolver);

        // Panel derecho: logo centrado
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(azulSuave);
        panelDerecho.setLayout(new BorderLayout());

        JLabel logo = new JLabel(new ImageIcon("src/assets/logo.png")); // Cambia si tu ruta es distinta
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        panelDerecho.add(logo, BorderLayout.CENTER);

        // Añadir paneles al frame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelDerecho, BorderLayout.CENTER);

        // Eventos
        cargarEventos();

        btnInscribirse.addActionListener((ActionEvent e) -> {
            int fila = tablaEventos.getSelectedRow();
            if (fila >= 0) {
                Evento eventoSeleccionado = listaEventos.get(fila);
                int idEvento = eventoSeleccionado.getId();

                InscripcionDAO dao = new InscripcionDAO();
                boolean exito = dao.inscribirCliente(cliente.getId(), idEvento);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Inscripción exitosa");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al inscribirse", "Error 666", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento primero.");
            }
        });

        btnVolver.addActionListener(e -> {
            new MenuCliente(cliente).setVisible(true);
            dispose();
        });
    }

    private void cargarEventos() {
        EventoDAO dao = new EventoDAO();
        listaEventos = dao.obtenerEventos();

        String[] columnas = {"Lugar", "Título", "Fecha", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Evento e : listaEventos) {
            Object[] fila = {e.getLugar(), e.getTitulo(), e.getFecha(), e.getPrecio()};
            model.addRow(fila);
        }

        tablaEventos.setModel(model);
    }
}
