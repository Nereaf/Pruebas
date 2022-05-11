package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.Test;

class GestorAlarmasTest {

	@Test
	void testInformarAlarma() {
		Float[] pos = {12.3f, 12.4f};
		Alarma alarm = new Alarma(1, "Incendio");
		Alerta alert=new Alerta("Urgente", 1, pos, "ETSE", new Date(), alarm);
		GestorAlarmas GAS = new GestorAlarmas();
		assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma.");
	}

}
