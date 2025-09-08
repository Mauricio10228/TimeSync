package controlador;

import modelo.DBConnection;
import modelo.Evento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAO {

    public boolean inscribirCliente(int clienteId, int eventoId) {
        String sql = "INSERT INTO inscripciones (cliente_id, evento_id) VALUES (?, ?)";
        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            stmt.setInt(2, eventoId);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error al inscribir cliente: " + e.getMessage());
            return false;
        }
    }

    public List<Evento> obtenerEventosInscritos(int clienteId) {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT e.* FROM eventos e " +
                     "INNER JOIN inscripciones i ON e.id = i.evento_id " +
                     "WHERE i.cliente_id = ?";

        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Evento e = new Evento(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("descripcion"),
                        rs.getString("fecha"),
                        null, // hora eliminada
                        rs.getString("lugar"),
                        rs.getDouble("precio"),
                        rs.getInt("cupo_maximo"),
                        rs.getInt("organizador_id")
                );
                lista.add(e);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener eventos inscritos: " + e.getMessage());
        }

        return lista;
    }

    public boolean eliminarInscripcion(int clienteId, int eventoId) {
        String sql = "DELETE FROM inscripciones WHERE cliente_id = ? AND evento_id = ?";
        try (Connection conn = DBConnection.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, clienteId);
            stmt.setInt(2, eventoId);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al cancelar inscripción: " + e.getMessage());
            return false;
        }
    }
}
