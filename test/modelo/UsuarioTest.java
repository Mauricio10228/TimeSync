package modelo;

import org.junit.Test;
import static org.junit.Assert.*;

public class UsuarioTest {
    
    @Test
    public void testConstructorConId() {
        Usuario user = new Usuario(1, "Juan", "juan@mail.com", "1234", "admin");
        assertEquals(1, user.getId());
        assertEquals("Juan", user.getNombre());
        assertEquals("juan@mail.com", user.getCorreo());
        assertEquals("1234", user.getContrasena());
        assertEquals("admin", user.getRol());
    }

    @Test
    public void testConstructorSinId() {
        Usuario user = new Usuario("Carlos", "c@mail.com", "abcd", "cliente");
        assertEquals(0, user.getId());
        assertEquals("Carlos", user.getNombre());
        assertEquals("c@mail.com", user.getCorreo());
        assertEquals("abcd", user.getContrasena());
        assertEquals("cliente", user.getRol());
    }

    @Test
    public void testGetId() {
        Usuario user = new Usuario(10, "Ana", "ana@mail.com", "pass", "admin");
        assertEquals(10, user.getId());
    }

    @Test
    public void testGetNombre() {
        Usuario user = new Usuario(1, "Pedro", "p@mail.com", "123", "cliente");
        assertEquals("Pedro", user.getNombre());
    }

    @Test
    public void testGetCorreo() {
        Usuario user = new Usuario(1, "Pedro", "p@mail.com", "123", "cliente");
        assertEquals("p@mail.com", user.getCorreo());
    }

    @Test
    public void testGetContrasena() {
        Usuario user = new Usuario(1, "Pedro", "p@mail.com", "123", "cliente");
        assertEquals("123", user.getContrasena());
    }

    @Test
    public void testGetRol() {
        Usuario user = new Usuario(1, "Pedro", "p@mail.com", "123", "cliente");
        assertEquals("cliente", user.getRol());
    }

    @Test
    public void testSetNombre() {
        Usuario user = new Usuario(1, "Old", "o@mail.com", "123", "admin");
        user.setNombre("Nuevo");
        assertEquals("Nuevo", user.getNombre());
    }

    @Test
    public void testSetCorreo() {
        Usuario user = new Usuario(1, "Test", "old@mail.com", "123", "admin");
        user.setCorreo("new@mail.com");
        assertEquals("new@mail.com", user.getCorreo());
    }

    @Test
    public void testSetContrasena() {
        Usuario user = new Usuario(1, "Test", "mail@mail.com", "old", "admin");
        user.setContrasena("newpass");
        assertEquals("newpass", user.getContrasena());
    }

    @Test
    public void testSetRol() {
        Usuario user = new Usuario(1, "Test", "mail@mail.com", "pass", "oldRol");
        user.setRol("newRol");
        assertEquals("newRol", user.getRol());
    }
}
