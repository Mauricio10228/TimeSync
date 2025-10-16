package vista;

import controlador.EventoDAO;
import modelo.Evento;
import modelo.Usuario;
import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PanelOrganizador extends JFrame {

    private JTextField txtTitulo, txtDescripcion, txtFecha, txtLugar, txtPrecio, txtCupo;
    private JButton btnCrear;
    private Usuario organizador;

    public PanelOrganizador(Usuario organizador) {
        this.organizador = organizador;

        // Activar tema FlatLaf Dark
        FlatDarkLaf.setup();

        setTitle("Panel de Organizador - Crear Evento");
        setSize(500, 650);
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
        tarjeta.setOpaque(true);

        // Logo
        JLabel logo = new JLabel();
        logo.setAlignmentX(CENTER_ALIGNMENT);
        ImageIcon icon = new ImageIcon(getClass().getResource("/vista/TimeSync_resized_250x200.png"));
        logo.setIcon(icon);
        tarjeta.add(logo);

        tarjeta.add(Box.createVerticalStrut(10));

        // Título
        JLabel lblTitulo = new JLabel("Crear nuevo evento");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 22));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
        tarjeta.add(lblTitulo);

        tarjeta.add(Box.createVerticalStrut(20));

        // Campo título
        txtTitulo = new JTextField();
        txtTitulo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtTitulo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtTitulo.setBorder(BorderFactory.createTitledBorder("Título"));
        tarjeta.add(txtTitulo);

        tarjeta.add(Box.createVerticalStrut(15));

        // Campo descripción
        txtDescripcion = new JTextField();
        txtDescripcion.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtDescripcion.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtDescripcion.setBorder(BorderFactory.createTitledBorder("Descripción"));
        tarjeta.add(txtDescripcion);

        tarjeta.add(Box.createVerticalStrut(15));

        // Campo fecha
        txtFecha = new JTextField();
        txtFecha.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtFecha.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtFecha.setBorder(BorderFactory.createTitledBorder("Fecha (YYYY-MM-DD)"));
        tarjeta.add(txtFecha);

        tarjeta.add(Box.createVerticalStrut(15));

        // Campo lugar
        txtLugar = new JTextField();
        txtLugar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtLugar.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtLugar.setBorder(BorderFactory.createTitledBorder("Lugar"));
        tarjeta.add(txtLugar);

        tarjeta.add(Box.createVerticalStrut(15));

        // Campo precio
        txtPrecio = new JTextField();
        txtPrecio.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtPrecio.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtPrecio.setBorder(BorderFactory.createTitledBorder("Precio"));
        tarjeta.add(txtPrecio);

        tarjeta.add(Box.createVerticalStrut(15));

        // Campo cupo
        txtCupo = new JTextField();
        txtCupo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        txtCupo.setFont(new Font("SansSerif", Font.PLAIN, 14));
        txtCupo.setBorder(BorderFactory.createTitledBorder("Cupo Máximo"));
        tarjeta.add(txtCupo);

        tarjeta.add(Box.createVerticalStrut(25));

        // Botón crear evento
        btnCrear = new JButton("Crear Evento");
        btnCrear.setAlignmentX(CENTER_ALIGNMENT);
        btnCrear.setPreferredSize(new Dimension(200, 40));
        btnCrear.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        btnCrear.setFont(new Font("SansSerif", Font.BOLD, 16));
        btnCrear.setBackground(new Color(0, 122, 255));
        btnCrear.setForeground(Color.WHITE);
        btnCrear.setFocusPainted(false);
        btnCrear.setBorderPainted(false);
        tarjeta.add(btnCrear);

        tarjeta.add(Box.createVerticalGlue());

        // ScrollPane para evitar cortes
        JScrollPane scrollPane = new JScrollPane(tarjeta);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(420, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        centro.add(scrollPane);

        // Acción botón crear
        btnCrear.addActionListener((ActionEvent e) -> {
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
                    JOptionPane.showMessageDialog(this,
                            "Evento creado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Error al crear evento", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Datos numéricos inválidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        Usuario demo = new Usuario("Valentina", "org@mail.com", "123", "organizador");
        SwingUtilities.invokeLater(() -> new PanelOrganizador(demo).setVisible(true));
    }
}
