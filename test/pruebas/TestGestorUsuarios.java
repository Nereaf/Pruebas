package pruebas;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestGestorUsuarios {

    static GestorUsuarios gu;
    static MiembroUSC usr;

    @BeforeAll
    static void setUpBeforeClass() {
        gu = new GestorUsuarios();
        usr = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        BBDD bbdd = BBDD.obtenerBBDD();
        bbdd.getUsuarios().add(usr);
    }

    @Test
    @DisplayName("Prueba [CPB-007-01]")
    void testCamino1() {
        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        usuario.setCorreo("");
        assertNull(gu.crudUsuario(usuario, 1));
    }

    @Test
    @DisplayName("Prueba [CPB-007-02]")
    void testCamino2() {
        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        usuario.setCorreo("emailIncorrecto");
        assertNull(gu.crudUsuario(usuario, 1));
    }

    @Test
    @DisplayName("Prueba [CPB-007-03]")
    void testCamino3() {
        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        usuario.setContrasena("contrasenaFalsa");
        assertNull(gu.crudUsuario(usuario, 1));
    }

    @Test
    @DisplayName("Prueba [CPB-007-04]")
    void testCamino4() {
        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        usuario.setNombre("nombreNuevo");
        usuario.setDNI("DNINuevo");
        usuario.setTelef(1111);
        usuario.setUsr(usuario.getUsr());
        assertEquals(usr, gu.crudUsuario(usuario, 1));
    }

    @Test
    @DisplayName("Prueba [CPB-007-05]")
    void testCamino5(){
        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        assertEquals(usr, gu.crudUsuario(usuario, 2));
        assertEquals(0, BBDD.obtenerBBDD().getUsuarios().size());
    }

    @Test
    @DisplayName("Prueba [CPB-007-06]")
    void testCamino6(){
        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        assertNull(gu.crudUsuario(usuario, 5));
    }

}
