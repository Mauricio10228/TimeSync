package vista;

import controlador.EventoDAO;
import controlador.InscripcionDAO;
import modelo.Evento;
import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

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

        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Panel del Cliente - TimeSync");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Fondo principal
        JPanel fondo = new JPanel(new BorderLayout());
        fondo.setBackground(new Color(20, 20, 20));
        add(fondo, BorderLayout.CENTER);

        // Panel central con FlowLayout
        JPanel centro = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 30));
        centro.setOpaque(false);
        fondo.add(centro, BorderLayout.CENTER);

        // Tarjeta
        JPanel tarjeta = new JPanel();
        tarjeta.setBackground(new Color(40, 40, 40));
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        tarjeta.setPreferredSize(new Dimension(700, 500));
        tarjeta.setOpaque(true);
        centro.add(tarjeta);

        // Logo
        JLabel logo = new JLabel();
        logo.setAlignmentX(CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_250x200.png"));
        logo.setIcon(icon);
        tarjeta.add(logo);

        tarjeta.add(Box.createVerticalStrut(10));

        // Título
        JLabel lblTitulo = new JLabel("Eventos disponibles");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(lblTitulo);

        tarjeta.add(Box.createVerticalStrut(20));

        // Tabla de eventos
        tablaEventos = new JTable();
        tablaEventos.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tablaEventos.setRowHeight(25);
        tablaEventos.setGridColor(new Color(80, 80, 80));
        tablaEventos.setForeground(Color.WHITE);
        tablaEventos.setBackground(new Color(60, 60, 60));
        tablaEventos.getTableHeader().setBackground(new Color(30, 30, 30));
        tablaEventos.getTableHeader().setForeground(Color.WHITE);
        tablaEventos.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));

       JScrollPane scrollPane = new JScrollPane(tablaEventos);
scrollPane.setPreferredSize(new Dimension(800, 320)); // ← altura ajustada para 5 eventos
scrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
tarjeta.add(scrollPane);


        tarjeta.add(Box.createVerticalStrut(20));

        // Botón Inscribirse
        JButton btnInscribirse = new JButton("Inscribirse en evento");
        btnInscribirse.setAlignmentX(CENTER_ALIGNMENT);
        btnInscribirse.setPreferredSize(new Dimension(200, 40));
        btnInscribirse.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnInscribirse.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnInscribirse.setBackground(new Color(0, 122, 255));
        btnInscribirse.setForeground(Color.WHITE);
        btnInscribirse.setFocusPainted(false);
        btnInscribirse.setBorderPainted(false);
        tarjeta.add(btnInscribirse);

        tarjeta.add(Box.createVerticalStrut(15));

        // Botón Volver
        JButton btnVolver = new JButton("Volver");
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

        // Eventos
        cargarEventos();

        // Lógica botones
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
                    JOptionPane.showMessageDialog(this, "Error al inscribirse", "Error", JOptionPane.ERROR_MESSAGE);
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
