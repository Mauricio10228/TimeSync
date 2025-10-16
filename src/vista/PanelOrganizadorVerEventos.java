package vista;

import controlador.EventoDAO;
import modelo.Evento;
import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelOrganizadorVerEventos extends JFrame {
    private JTable tablaEventos;
    private Usuario organizador;

    public PanelOrganizadorVerEventos(Usuario organizador) {
        this.organizador = organizador;

        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Tus eventos creados - TimeSync");
        setSize(800, 600);
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
        JLabel lblTitulo = new JLabel("Eventos que has creado");
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
        scrollPane.setPreferredSize(new Dimension(620, 320)); // altura para mostrar 5 eventos
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80)));
        tarjeta.add(scrollPane);

        tarjeta.add(Box.createVerticalGlue());

        // Cargar eventos
        cargarEventosDelOrganizador();
    }

    private void cargarEventosDelOrganizador() {
        EventoDAO dao = new EventoDAO();
        List<Evento> lista = dao.obtenerEventosPorOrganizador(organizador.getId());

        String[] columnas = {"ID", "Título", "Fecha", "Lugar", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Evento e : lista) {
            Object[] fila = {
                e.getId(),
                e.getTitulo(),
                e.getFecha(),
                e.getLugar(),
                e.getPrecio()
            };
            model.addRow(fila);
        }

        tablaEventos.setModel(model);
    }

    public static void main(String[] args) {
        Usuario demo = new Usuario("Valentina", "org@mail.com", "123", "organizador");
        SwingUtilities.invokeLater(() -> new PanelOrganizadorVerEventos(demo).setVisible(true));
    }
}
