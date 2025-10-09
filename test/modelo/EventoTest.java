package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase Evento.
 */
public class EventoTest {

    private Evento evento;

    @BeforeEach
    public void setUp() {
        evento = new Evento(
                1,
                "Concierto de Rock",
                "Evento musical al aire libre",
                "2025-12-20",
                "19:00",
                "Parque Central",
                50000.0,
                100,
                10
        );
    }

    @Test
    public void testGetId() {
        assertEquals(1, evento.getId());
    }

    @Test
    public void testGetTitulo() {
        assertEquals("Concierto de Rock", evento.getTitulo());
    }

    @Test
    public void testGetDescripcion() {
        assertEquals("Evento musical al aire libre", evento.getDescripcion());
    }

    @Test
    public void testGetFecha() {
        assertEquals("2025-12-20", evento.getFecha());
    }

    @Test
    public void testGetHora() {
        assertEquals("19:00", evento.getHora());
    }

    @Test
    public void testGetLugar() {
        assertEquals("Parque Central", evento.getLugar());
    }

    @Test
    public void testGetPrecio() {
        assertEquals(50000.0, evento.getPrecio());
    }

    @Test
    public void testGetCupoMaximo() {
        assertEquals(100, evento.getCupoMaximo());
    }

    @Test
    public void testGetOrganizadorId() {
        assertEquals(10, evento.getOrganizadorId());
    }

    // ✅ También probamos el segundo constructor (sin ID ni hora explícita)
    @Test
    public void testConstructorAlternativo() {
        Evento eventoAlt = new Evento(
                "Charla de Tecnología",
                "Conferencia sobre IA",
                "2025-11-15",
                "Auditorio",
                0.0,
                50,
                5
        );

        assertEquals("Charla de Tecnología", eventoAlt.getTitulo());
        assertEquals("Conferencia sobre IA", eventoAlt.getDescripcion());
        assertEquals("2025-11-15", eventoAlt.getFecha());
        assertEquals("00:00", eventoAlt.getHora()); // valor por defecto
        assertEquals("Auditorio", eventoAlt.getLugar());
        assertEquals(0.0, eventoAlt.getPrecio());
        assertEquals(50, eventoAlt.getCupoMaximo());
        assertEquals(5, eventoAlt.getOrganizadorId());
    }
}
