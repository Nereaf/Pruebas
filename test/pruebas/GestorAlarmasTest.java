package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Date;

import org.junit.jupiter.api.*;

class GestorAlarmasTest {
	static GestorAlarmas GAS;
	static Alarma alarm;
	static Alerta alert;
	static Float[] posAlert = {1.00f, 1.00f};
	static Float[] posUserV = {1.00f, 1.00f};
	static Float[] posUserF = {100.00f, 100.00f};
	@BeforeAll
    static void setUpBeforeClass() throws Exception{
        GAS = new GestorAlarmas();
        alarm = new Alarma(1, "Incendio");
	alert=new Alerta("Urgente", 1, posAlert, "ETSE", new Date(), alarm);
    }
	@Test
	void testInformarAlarma1() {
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 1.");
	}
	@Test
	void testInformarAlarma2() {
		MiembroEquipo me = new MiembroEquipo("Pablo","12345678X","p12","p12","pepe@terra.net",123456789,posUserV,"1","activo");
		BBDD.obtenerBBDD().getUsuarios().add(me);
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 2.");
	}
	@Test
	void testInformarAlarma3() {
		Usuario us = new Usuario("Ana","87654321Y","p11","p11","ana@terra.net",987654321);
		BBDD.obtenerBBDD().getUsuarios().add(us);
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 3.");
	}
	@Test
	void testInformarAlarma4() {
		MiembroUSC musc = new MiembroUSC("Ana","87654321Y","p11","p11","ana@terra.net",987654321,posUserF);
		BBDD.obtenerBBDD().getUsuarios().add(musc);
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 4.");
	}
	@Test
	void testInformarAlarma5() {
		MiembroUSC musc = new MiembroUSC("Ana","87654321Y","p11","p11","ana@terra.net",987654321,posUserV);
		BBDD.obtenerBBDD().getUsuarios().add(musc);
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 5.");
	}
	@Test
	void testInformarAlarma6() {
		MiembroUSC musc = new MiembroUSC("Ana","87654321Y","p31","p11","ana@terra.net",987654321,posUserV);
		MiembroUSC musc2 = new MiembroUSC("Pedro","1269183Y","p30","p10","pedro2@terra.net",981823321,posUserV);
		BBDD.obtenerBBDD().getUsuarios().add(musc);
		BBDD.obtenerBBDD().getUsuarios().add(musc2);
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma recorriendo bucle 2 veces.");
	}
	@Test
	void testInformarAlarma7() {
		MiembroEquipo me = new MiembroEquipo("Pablo","12345678X","p12","p12","pepe@terra.net",123456789,posUserV,"1","activo");
		MiembroEquipo me2 = new MiembroEquipo("Pedro","8769183Y","p10","p10","pedro@terra.net",981823321,posUserV,"2","activo");
		BBDD.obtenerBBDD().getUsuarios().add(me);
		BBDD.obtenerBBDD().getUsuarios().add(me2);
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma recorriendo bucle 2 veces.");
	}
	@Nested
	@DisplayName("Pruebas de rendimiento [NFR-0002]")
	class Rendimiento{
		@Test 
		void limiteTestInformarAlarmaRendimiento1() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma1();
			});
		}
		@Test
		void limiteTestInformarAlarmaRendimiento2() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma2();
			});
		}
		@Test 
		void limiteTestInformarAlarmaRendimiento3() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma3();
			});
		}
		@Test
		void limiteTestInformarAlarmaRendimiento4() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma4();
			});
		}
		@Test 
		void limiteTestInformarAlarmaRendimiento5() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma5();
			});
		}
		@Test 
		void limiteTestInformarAlarmaRendimiento6() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma6();
			});
		}
		@Test 
		void limiteTestInformarAlarmaRendimiento7() {
			assertTimeoutPreemptively(Duration.ofSeconds(2), ()->{
				testInformarAlarma7();
			});
		}
	}
}

