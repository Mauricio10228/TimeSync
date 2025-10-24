
package modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class EventoTest {
    
    // ✅ Prueba constructor con ID
    @Test
    public void testConstructorConId() {
        Evento evento = new Evento(1, "Concierto", "Musica en vivo", "2025-10-10", "20:00", "Teatro",
                                   50.5, 100, 10);
        assertEquals(1, evento.getId());
        assertEquals("Concierto", evento.getTitulo());
        assertEquals("Musica en vivo", evento.getDescripcion());
        assertEquals("2025-10-10", evento.getFecha());
        assertEquals("20:00", evento.getHora());
        assertEquals("Teatro", evento.getLugar());
        assertEquals(50.5, evento.getPrecio(), 0.001);
        assertEquals(100, evento.getCupoMaximo());
        assertEquals(10, evento.getOrganizadorId());
    }

    // ✅ Prueba constructor SIN ID (hora por defecto "00:00")
    @Test
    public void testConstructorSinId() {
        Evento evento = new Evento("Fiesta", "Cumpleaños", "2025-05-05", "Salon",
                                   30.0, 50, 5);
        assertEquals(0, evento.getId()); // int por defecto = 0
        assertEquals("Fiesta", evento.getTitulo());
        assertEquals("Cumpleaños", evento.getDescripcion());
        assertEquals("2025-05-05", evento.getFecha());
        assertEquals("00:00", evento.getHora()); // valor por defecto
        assertEquals("Salon", evento.getLugar());
        assertEquals(30.0, evento.getPrecio(), 0.001);
        assertEquals(50, evento.getCupoMaximo());
        assertEquals(5, evento.getOrganizadorId());
    }

    // ✅ Getters individuales (opcional, pero recomendables)

    @Test
    public void testGetId() {
        Evento evento = new Evento(10, "Test", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 1);
        assertEquals(10, evento.getId());
    }

    @Test
    public void testGetTitulo() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 1);
        assertEquals("Titulo", evento.getTitulo());
    }

    @Test
    public void testGetDescripcion() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 1);
        assertEquals("Desc", evento.getDescripcion());
    }

    @Test
    public void testGetFecha() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 1);
        assertEquals("2025-01-01", evento.getFecha());
    }

    @Test
    public void testGetHora() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 1);
        assertEquals("10:00", evento.getHora());
    }

    @Test
    public void testGetLugar() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 1);
        assertEquals("Lugar", evento.getLugar());
    }

    @Test
    public void testGetPrecio() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 99.99, 20, 1);
        assertEquals(99.99, evento.getPrecio(), 0.001);
    }

    @Test
    public void testGetCupoMaximo() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 50, 1);
        assertEquals(50, evento.getCupoMaximo());
    }

    @Test
    public void testGetOrganizadorId() {
        Evento evento = new Evento(1, "Titulo", "Desc", "2025-01-01", "10:00", "Lugar", 10.0, 20, 7);
        assertEquals(7, evento.getOrganizadorId());
    }
}