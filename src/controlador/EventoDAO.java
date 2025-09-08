package controlador;

import modelo.DBConnection;
import modelo.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    public boolean insertarEvento(Evento evento) {
        String sql = "INSERT INTO eventos (titulo, descripcion, fecha, lugar, precio, cupo_maximo, organizador_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, evento.getTitulo());
            stmt.setString(2, evento.getDescripcion());
            stmt.setString(3, evento.getFecha());
            stmt.setString(4, evento.getLugar());
            stmt.setDouble(5, evento.getPrecio());
            stmt.setInt(6, evento.getCupoMaximo());
            stmt.setInt(7, evento.getOrganizadorId());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al insertar evento: " + e.getMessage());
            return false;
        }
    }

    public List<Evento> obtenerEventos() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT id, titulo, descripcion, fecha, lugar, precio, cupo_maximo, organizador_id FROM eventos";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("fecha"),
                    null, // hora no utilizada
                    rs.getString("lugar"),
                    rs.getDouble("precio"),
                    rs.getInt("cupo_maximo"),
                    rs.getInt("organizador_id")
                );
                lista.add(evento);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener eventos: " + e.getMessage());
        }

        return lista;
    }

    public List<Evento> obtenerEventosPorOrganizador(int organizadorId) {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM eventos WHERE organizador_id = ?";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, organizadorId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Evento evento = new Evento(
                    rs.getInt("id"),
                    rs.getString("titulo"),
                    rs.getString("descripcion"),
                    rs.getString("fecha"),
                    rs.getString("hora"),  // aunque no se use, se puede capturar
                    rs.getString("lugar"),
                    rs.getDouble("precio"),
                    rs.getInt("cupo_maximo"),
                    rs.getInt("organizador_id")
                );
                lista.add(evento);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener eventos por organizador: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminarEvento(int idEvento) {
        String sql = "DELETE FROM eventos WHERE id = ?";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar evento: " + e.getMessage());
            return false;
        }
    }
}
