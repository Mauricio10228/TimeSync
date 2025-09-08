package vista;

import controlador.EventoDAO;
import modelo.Evento;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelOrganizadorVerEventos extends JFrame {
    private JTable tablaEventos;
    private Usuario organizador;

    public PanelOrganizadorVerEventos(Usuario organizador) {
        Color azulSuave = new Color(173, 216, 230);
    getContentPane().setBackground(azulSuave);

        this.organizador = organizador;
        setTitle("Tus eventos creados - TimeSync");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel lblTitulo = new JLabel("Eventos que has creado", JLabel.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        add(lblTitulo, BorderLayout.NORTH);

        tablaEventos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        add(scrollPane, BorderLayout.CENTER);

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
}
