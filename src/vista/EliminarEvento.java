package vista;

import controlador.EventoDAO;
import java.awt.Color;
import modelo.Evento;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class EliminarEvento extends JFrame {
    private JTable tablaEventos;
    private JButton btnEliminar;
    private Usuario organizador;

    public EliminarEvento(Usuario organizador) {
        this.organizador = organizador;
Color azulSuave = new Color(173, 216, 230);
    getContentPane().setBackground(azulSuave);

        setTitle("Eliminar Evento - TimeSync");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Selecciona un evento para eliminar");
        lblTitulo.setBounds(20, 20, 300, 25);
        add(lblTitulo);

        tablaEventos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        scrollPane.setBounds(20, 60, 540, 200);
        add(scrollPane);

        btnEliminar = new JButton("Eliminar Evento");
        btnEliminar.setBounds(200, 280, 180, 30);
        add(btnEliminar);

        cargarEventos();

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
}
