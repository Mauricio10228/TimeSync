package modelo;

public class Evento {
    private int id;
    private String titulo;
    private String descripcion;
    private String fecha;
    private String hora;
    private String lugar;
    private double precio;
    private int cupoMaximo;
    private int organizadorId;

    public Evento(int id, String titulo, String descripcion, String fecha, String hora, String lugar,
                  double precio, int cupoMaximo, int organizadorId) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = hora;
        this.lugar = lugar;
        this.precio = precio;
        this.cupoMaximo = cupoMaximo;
        this.organizadorId = organizadorId;
    }

    // ✅ Constructor ajustado (inicializa `hora` con valor por defecto)
    public Evento(String titulo, String descripcion, String fecha, String lugar,
                  double precio, int cupoMaximo, int organizadorId) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.hora = "00:00"; // Valor por defecto para hora
        this.lugar = lugar;
        this.precio = precio;
        this.cupoMaximo = cupoMaximo;
        this.organizadorId = organizadorId;
    }

    // Getters
    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescripcion() { return descripcion; }
    public String getFecha() { return fecha; }
    public String getHora() { return hora; }
    public String getLugar() { return lugar; }
    public double getPrecio() { return precio; }
    public int getCupoMaximo() { return cupoMaximo; }
    public int getOrganizadorId() { return organizadorId; }
}
