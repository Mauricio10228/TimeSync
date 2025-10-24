import modelo.Inscripcion;
import org.junit.Test;
import static org.junit.Assert.*;

public class InscripcionTest {

    @Test
    public void testGetClienteId() {
        Inscripcion inscripcion = new Inscripcion(10, 20, "2025-01-01");
        assertEquals(10, inscripcion.getClienteId());
    }

    @Test
    public void testGetEventoId() {
        Inscripcion inscripcion = new Inscripcion(10, 20, "2025-01-01");
        assertEquals(20, inscripcion.getEventoId());
    }

    @Test
    public void testGetFechaInscripcion() {
        Inscripcion inscripcion = new Inscripcion(10, 20, "2025-01-01");
        assertEquals("2025-01-01", inscripcion.getFechaInscripcion());
    }
}
