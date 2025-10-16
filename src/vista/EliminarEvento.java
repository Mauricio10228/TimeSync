package vista;

import controlador.EventoDAO;
import modelo.Evento;
import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

public class EliminarEvento extends JFrame {
    private JTable tablaEventos;
    private JButton btnEliminar;
    private Usuario organizador;

    public EliminarEvento(Usuario organizador) {
        this.organizador = organizador;

        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Eliminar Evento - TimeSync");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
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
        tarjeta.setPreferredSize(new Dimension(620, 400));
        tarjeta.setOpaque(true);
        centro.add(tarjeta);

        // Título
        JLabel lblTitulo = new JLabel("Selecciona un evento para eliminar");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 20));
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
        scrollPane.setPreferredSize(new Dimension(580, 200));
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        tarjeta.add(scrollPane);

        tarjeta.add(Box.createVerticalStrut(20));

        // Botón eliminar
        btnEliminar = new JButton("Eliminar Evento");
        btnEliminar.setAlignmentX(CENTER_ALIGNMENT);
        btnEliminar.setPreferredSize(new Dimension(200, 40));
        btnEliminar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnEliminar.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnEliminar.setBackground(new Color(255, 80, 80));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setBorderPainted(false);
        tarjeta.add(btnEliminar);

        tarjeta.add(Box.createVerticalGlue());

        // Cargar eventos
        cargarEventos();

        // Acción botón eliminar
        btnEliminar.addActionListener((ActionEvent e) -> {
            int fila = tablaEventos.getSelectedRow();
            if (fila >= 0) {
                int idEvento = (int) tablaEventos.getValueAt(fila, 0);
                EventoDAO dao = new EventoDAO();
                boolean exito = dao.eliminarEvento(idEvento);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Evento eliminado correctamente");
                    cargarEventos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar evento", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un evento primero.");
            }
        });
    }

    private void cargarEventos() {
        EventoDAO dao = new EventoDAO();
        List<Evento> lista = dao.obtenerEventosPorOrganizador(organizador.getId());

        String[] columnas = {"ID", "Título", "Fecha", "Lugar", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Evento e : lista) {
            Object[] fila = {e.getId(), e.getTitulo(), e.getFecha(), e.getLugar(), e.getPrecio()};
            model.addRow(fila);
        }

        tablaEventos.setModel(model);
    }

    public static void main(String[] args) {
        Usuario demo = new Usuario("Valentina", "org@mail.com", "123", "organizador");
        SwingUtilities.invokeLater(() -> new EliminarEvento(demo).setVisible(true));
    }
}
