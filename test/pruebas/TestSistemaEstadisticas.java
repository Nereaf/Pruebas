package pruebas;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestSistemaEstadisticas {

    SistemaEstadisticas sistema;

    @BeforeEach
    void setUpBeforeEach() throws Exception{
        sistema = new SistemaEstadisticas();
    }

    @Test
    @DisplayName("Prueba [CPB-005-01]")
    void testRegistrarAlertas() {
        Alerta alerta = new Alerta();
        String mensaje = "Se han registrado 1 alertas.";
        assertEquals(true, sistema.registrarAlerta(alerta));
        assertEquals(mensaje, sistema.consultarEstadisticas());
    }

    @Test
    @DisplayName("Prueba [CPB-005-02]")
    void testAlertaNull() {
        Alerta alertaNull = null;
        String mensaje = "Se han registrado 0 alertas.";
        assertEquals(true, sistema.registrarAlerta(alertaNull));
        assertEquals(mensaje, sistema.consultarEstadisticas());
    }

    @Test
    @DisplayName("Prueba [CPB-005-03]")
    void testBBDDNoDisponible(){
        sistema = null;
        Alerta alerta = new Alerta();
        assertEquals(false, sistema.registrarAlerta(alerta));
    }
}
