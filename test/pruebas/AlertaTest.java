package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.*;

class AlertaTest {

	@Test
	
	void testCrearAlerta() {
		Alarma al= new Alarma(8, "terremoto");
		Date date= new Date();
		Alerta alerta= new Alerta(null, 5,new Float[] {-180.0f,-180.0f}, null,  null,  null);
		Alerta esperada= new Alerta("activo", 5,new Float[] {-180.0f,-180.0f}, "ETSE",  date,  al);
		assertEquals(esperada, alerta.crearAlerta("activo", "ETSE" , date, al) );
	}
   @Test
	void testCrearAlerta2() {
		Alarma al= new Alarma(8, "terremoto");
		Date date= new Date();
		Alerta alerta= new Alerta(null, 5,new Float[] {-181.0f,-181.0f}, null,  null,  null);
		Alerta esperada= new Alerta("activo", 5,new Float[] {-181.0f,-181.0f}, "ETSE",  date,  al);
		assertNull(alerta.crearAlerta("activo", "ETSE" , date, al), "no se ha introducido error" );
	}
   @Test
	void testCrearAlerta3() {
		Alarma al= new Alarma(8, "terremoto");
		Date date= new Date();
		Alerta alerta= new Alerta(null, 5,new Float[] {180.0f,180.0f}, null,  null,  null);
		Alerta esperada= new Alerta("activo", 5,new Float[] {180.0f,180.0f}, "ETSE",  date,  al);
		assertEquals(esperada, alerta.crearAlerta("activo", "ETSE" , date, al) );
	}
   @Test
	void testCrearAlerta4() {
		Alarma al= new Alarma(8, "terremoto");
		Date date= new Date();
		Alerta alerta= new Alerta(null, 5,new Float[] {181.0f,181.0f}, null,  null,  null);
		Alerta esperada= new Alerta("activo", 5,new Float[] {181.0f,181.0f}, "ETSE",  date,  al);
		assertNull(alerta.crearAlerta("activo", "ETSE" , date, al), "no se ha introducido error" );
	}
}