package pruebas;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.time.Duration;
import java.util.Date;

import org.junit.jupiter.api.*;

class CentralitaTest {
	static GestorAlarmas GAS;
	static Centralita cen;
	@BeforeAll
    static void setUpBeforeClass() throws Exception{
        GAS = mock(GestorAlarmas.class);
        cen = new Centralita(GAS);
	}
	@Test
	void testNotificarAlerta1() {
		Float[] pos = {12.3f, 12.4f};
		Alarma alarm = new Alarma(1, "Incendio");
		Alerta alert=new Alerta("Urgente", 1, pos, "ETSE", new Date(), alarm);
		assertTrue(cen.notificarAlerta(alert),"Error al notificar una alerta.");
	}
	@Test
	void testNotificarAlerta2() {
		assertTrue(cen.notificarAlerta(null),"Error al notificar una alerta nula.");
	}
	@Nested
	@DisplayName("Pruebas de rendimiento [NFR-0002]")
	class Rendimiento{
		@Test 
		void limiteTestNotificarAlertaRendimiento1() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testNotificarAlerta1();
			});
		}
		@Test
		void limiteTestNotificarAlertaRendimiento2() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testNotificarAlerta2();
			});
		}
	}

}
