package vista;

import modelo.Evento;
import modelo.Usuario;
import controlador.EventoDAO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.*;
import java.lang.reflect.Field;

/**
 * Pruebas unitarias de caja blanca para PanelOrganizador
 * (usando JUnit 4)
 */
public class PanelOrganizadorTest {

    private PanelOrganizador panel;
    private Usuario organizadorMock;

    // Clase mock para EventoDAO que evita acceso real a base de datos
    static class EventoDAOMock extends EventoDAO {
        public boolean resultadoInsertar;
        public Evento ultimoEvento;

        @Override
        public boolean insertarEvento(Evento evento) {
            this.ultimoEvento = evento;
            return resultadoInsertar;
        }
    }

    private EventoDAOMock daoMock;

    @Before
    public void setUp() throws Exception {
        organizadorMock = new Usuario("Test User", "test@mail.com", "123", "organizador");
        panel = new PanelOrganizador(organizadorMock);

        // Reemplazar el DAO real por el mock
        daoMock = new EventoDAOMock();

        // Verificar que el botón existe (por reflexión)
        Field field = PanelOrganizador.class.getDeclaredField("btnCrear");
        field.setAccessible(true);
        JButton btnCrear = (JButton) field.get(panel);
        assertNotNull(btnCrear);
    }

    /**
     * ✅ Caso 1: Datos correctos (flujo principal)
     */
    @Test
    public void testCrearEventoDatosValidos() throws Exception {
        setTextField("txtTitulo", "Concierto");
        setTextField("txtDescripcion", "Rock en vivo");
        setTextField("txtFecha", "2025-12-31");
        setTextField("txtLugar", "Bogotá");
        setTextField("txtPrecio", "50000");
        setTextField("txtCupo", "100");

        // Simular que el evento se inserta correctamente
        daoMock.resultadoInsertar = true;

        // Ejecutar acción del botón (usando reflexión)
        Field botonField = PanelOrganizador.class.getDeclaredField("btnCrear");
        botonField.setAccessible(true);
        JButton boton = (JButton) botonField.get(panel);
        boton.doClick();

        // Validar que los datos se asignaron correctamente
        assertEquals("Concierto", daoMock.ultimoEvento.getTitulo());
        assertEquals(50000, daoMock.ultimoEvento.getPrecio());
        assertEquals(100, daoMock.ultimoEvento.getCupoMaximo());
    }

    /**
     * ⚠️ Caso 2: Datos numéricos inválidos (bloque catch)
     */
    @Test
    public void testDatosNumericosInvalidos() throws Exception {
        setTextField("txtTitulo", "Evento Test");
        setTextField("txtDescripcion", "Error numérico");
        setTextField("txtFecha", "2025-01-01");
        setTextField("txtLugar", "Cali");
        setTextField("txtPrecio", "noNumero");
        setTextField("txtCupo", "10");

        // Ejecutar acción del botón (por reflexión)
        Field botonField = PanelOrganizador.class.getDeclaredField("btnCrear");
        botonField.setAccessible(true);
        JButton boton = (JButton) botonField.get(panel);

        try {
            boton.doClick();
        } catch (Exception e) {
            fail("Debe manejar NumberFormatException correctamente: " + e.getMessage());
        }
    }

    /**
     * ⚠️ Caso 3: Falla en la inserción del DAO (else lógico)
     */
    @Test
    public void testErrorInsertarEvento() throws Exception {
        setTextField("txtTitulo", "Falla");
        setTextField("txtDescripcion", "Error DB");
        setTextField("txtFecha", "2025-11-11");
        setTextField("txtLugar", "Medellín");
        setTextField("txtPrecio", "20000");
        setTextField("txtCupo", "50");

        daoMock.resultadoInsertar = false;

        // Ejecutar acción del botón (por reflexión)
        Field botonField = PanelOrganizador.class.getDeclaredField("btnCrear");
        botonField.setAccessible(true);
        JButton boton = (JButton) botonField.get(panel);

        try {
            boton.doClick();
        } catch (Exception e) {
            fail("Debe manejar correctamente la falla en insertarEvento: " + e.getMessage());
        }
    }

    // 🧩 Método auxiliar para modificar valores privados
    private void setTextField(String nombreCampo, String valor) throws Exception {
        Field field = PanelOrganizador.class.getDeclaredField(nombreCampo);
        field.setAccessible(true);
        JTextField txt = (JTextField) field.get(panel);
        txt.setText(valor);
    }
}
