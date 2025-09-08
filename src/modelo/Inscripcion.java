package modelo;

public class Inscripcion {
    private int id;
    private int clienteId;
    private int eventoId;
    private String fechaInscripcion;

    public Inscripcion(int clienteId, int eventoId, String fechaInscripcion) {
        this.clienteId = clienteId;
        this.eventoId = eventoId;
        this.fechaInscripcion = fechaInscripcion;
    }

    public int getClienteId() {
        return clienteId;
    }

    public int getEventoId() {
        return eventoId;
    }

    public String getFechaInscripcion() {
        return fechaInscripcion;
    }

    // Setters si los necesitas
}
