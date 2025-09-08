package vista;

import controlador.InscripcionDAO;
import java.awt.Color;
import modelo.Evento;
import modelo.Usuario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.List;

public class EventosInscritos extends JFrame {
    private JTable tablaEventos;
    private Usuario cliente;

    public EventosInscritos(Usuario cliente) {
        this.cliente = cliente;
Color azulSuave = new Color(173, 216, 230); // Azul claro
        setTitle("Mis Eventos Inscritos");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Eventos en los que estás inscrito");
        lblTitulo.setBounds(20, 20, 300, 25);
        add(lblTitulo);

        tablaEventos = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaEventos);
        scrollPane.setBounds(20, 60, 640, 200);
        add(scrollPane);

        JButton btnCancelar = new JButton("Cancelar inscripción");
        btnCancelar.setBounds(140, 280, 180, 30);
        add(btnCancelar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(350, 280, 120, 30);
        add(btnVolver);

        cargarEventosInscritos();

        btnCancelar.addActionListener((ActionEvent e) -> {
            int fila = tablaEventos.getSelectedRow();
            if (fila >= 0) {
                int idEvento = (int) tablaEventos.getValueAt(fila, 0);
                InscripcionDAO dao = new InscripcionDAO();
                boolean exito = dao.eliminarInscripcion(cliente.getId(), idEvento);
                if (exito) {
                    JOptionPane.showMessageDialog(this, "Inscripción cancelada correctamente");
                    cargarEventosInscritos();
                } else {
                    JOptionPane.showMessageDialog(this, "Error al cancelar inscripción", "Error", JOptionPane.ERROR_MESSAGE);
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

    private void cargarEventosInscritos() {
        InscripcionDAO dao = new InscripcionDAO();
        List<Evento> lista = dao.obtenerEventosInscritos(cliente.getId());

        String[] columnas = {"ID", "Título", "Fecha", "Lugar", "Precio"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (Evento e : lista) {
            Object[] fila = {e.getId(), e.getTitulo(), e.getFecha(), e.getLugar(), e.getPrecio()};
            model.addRow(fila);
        }

        tablaEventos.setModel(model);
    }
}
