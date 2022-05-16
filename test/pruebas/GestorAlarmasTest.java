package pruebas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.Date;

import org.junit.jupiter.api.*;

class GestorAlarmasTest {
	static GestorAlarmas GAS;
	static Alarma alarm;
	static Alarma alarmaI;
	static Alarma alarmaP;
	static Alarma alarmaR;
	static Alerta alert;
	static BBDD bbdd;
	static Float[] posAlert = {1.00f, 1.00f};
	static Float[] posUserV = {1.00f, 1.00f};
	static Float[] posUserF = {100.00f, 100.00f};
	static Float[] posicion = {0f, 0f};
	static Float[] posicionfallo1 = {50.1f, 50.1f};
	static Float[] posicionfallo2 = {-50.1f, -50.1f};
	static Float[] posicionnull = {9999f, 9999f};
	
	@BeforeAll
    static void setUpBeforeClass() throws Exception{
        GAS = new GestorAlarmas();
    }
	@Nested
	class crudAlarma{
		@BeforeEach
		void setUp() throws Exception {
			bbdd = BBDD.obtenerBBDD();
			alarmaI= new Alarma(0, "Alarma de Incendios");
		}

		@AfterEach
		void tearDown() throws Exception {
			bbdd.getAlarmas().clear(); //Como está implementado o patrón Singleton, é necesario eliminar as alarmas porque a BBDD vai seguir sendo a mesma
			bbdd=null;
		}
		
		@Test
		@DisplayName("CPB-002-01")
		void testCrudAlarma01() {
			alarmaP=GAS.crudAlarma(alarmaI, 0);
			assertSame(alarmaI, alarmaP, "Las alarmas non son la misma");
		}
		
		@Test
		@DisplayName("CPB-002-02")
		void testCrudAlarma02() {
			alarmaP=GAS.crudAlarma(alarmaI, 1);
			assertSame(alarmaI, alarmaP, "Las alarmas non son la misma");
			assertTrue(bbdd.getAlarmas().contains(alarmaI), "O arraylist non contén a alarma");
		}
		
		@Test
		@DisplayName("CPB-002-03")
		void testCrudAlarma03() {
			alarmaP=GAS.crudAlarma(alarmaI, 2);
			assertSame(alarmaI, alarmaP, "Las alarmas non son la misma");
		}
		
		@Test
		@DisplayName("CPB-002-04")
		void testCrudAlarma04() {
			alarmaP=GAS.crudAlarma(alarmaI, 3);
			assertSame(alarmaI, alarmaP, "Las alarmas non son la misma");
		}
		
		@Test
		@DisplayName("CPB-002-05")
		void testCrudAlarma05() {
			alarmaR=new Alarma(0, "Alarma de Robos");
			bbdd.getAlarmas().add(alarmaI); //Insertamos un elemento
			alarmaP=GAS.crudAlarma(alarmaR, 0);
			assertSame(alarmaI, alarmaP, "Las alarmas non son la misma");
		}
		
		@Test
		@DisplayName("CPB-002-06")
		void testCrudAlarma06() {
			alarmaR=new Alarma(0, "Alarma de Robos");
			bbdd.getAlarmas().add(alarmaI); //Insertamos un elemento
			alarmaP=GAS.crudAlarma(alarmaR, 2);
			assertEquals(alarmaR.getDescripcion(), alarmaI.getDescripcion(), "Las descripciones son distintas");
			assertNotSame(alarmaR, alarmaI, "Las alarmas son la misma");
			assertSame(alarmaP, alarmaR, "Las alarmas no son la misma");
		}
		
		@Test
		@DisplayName("CPB-002-07")
		void testCrudAlarma07() {
			alarmaR=new Alarma(0, "Alarma de Robos");
			bbdd.getAlarmas().add(alarmaI); //Insertamos un elemento
			alarmaP=GAS.crudAlarma(alarmaR, 3);
			assertSame(alarmaR, alarmaP, "As alarmas non son iguais");
			assertTrue(bbdd.getAlarmas().isEmpty());
			
		}
		
		@Test
		@DisplayName("CPB-002-08")
		void testCrudAlarma08() {
			alarmaR=new Alarma(1, "Alarma de Robos");
			bbdd.getAlarmas().add(alarmaI); //Insertamos un elemento
			alarmaP=GAS.crudAlarma(alarmaR, 0);
			assertSame(alarmaR, alarmaP, "Las alarmas non son la misma");
		}
		
		@Test
		@DisplayName("CPB-002-09")
		void testCrudAlarma09() {
			alarmaR=new Alarma(1, "Alarma de Robos");
			bbdd.getAlarmas().add(alarmaI); //Insertamos un elemento
			alarmaP=GAS.crudAlarma(alarmaR, 2);
			assertSame(alarmaR, alarmaP, "Las alarmas no son iguales");
			assertNotEquals(alarmaR.getDescripcion(), alarmaI.getDescripcion(), "Las descripciones son iguales");
		}
		
		@Test
		@DisplayName("CPB-002-10")
		void testCrudAlarma10() {
			alarmaR=new Alarma(1, "Alarma de Robos");
			bbdd.getAlarmas().add(alarmaI); //Insertamos un elemento
			alarmaP=GAS.crudAlarma(alarmaR, 3);
			assertSame(alarmaR, alarmaP, "Las alarmas non son la misma");
			assertFalse(bbdd.getAlarmas().isEmpty());
		}
	}
	@Nested
	@DisplayName("Recibir notificación alerta [PRB-004]")
	class recibirNotificacion{
		@BeforeEach
		void setUp() throws Exception {
			alarm = new Alarma(1, "Tormenta");
			alert=new Alerta("Activa", 1, posAlert, "ETSE", new Date(), alarm);
		}
		@Test
		void testCrearAlerta() {
			Alerta alerta2 = alert.crearAlerta("Activa", "ETSE", new Date(),alarm);
			assertEquals(alert,alerta2,"La alerta creada es diferente");
		}
		
		@Test
		void testRecibirAlerta() {
		Alerta alerta2=alert.crearAlerta("Activa", "ETSE", new Date(),alarm);
		
		MiembroUSC userA = new MiembroUSC("Jose", "1", "jose", "contrasena", "jose@gmail.com", 666666666, posicion);
		MiembroUSC userB = new MiembroUSC("Manuel", "2", "manuel", "contrasena", "manuel@gmail.com", 666666666, posicion);
		MiembroUSC userC = new MiembroUSC("Pedro", "3", "pedro", "contrasena", "pedro@gmail.com", 666666666,posicionfallo1 );
		MiembroUSC userD = new MiembroUSC("Carlos", "4", "carlos", "contrasena", "carlos@gmail.com", 666666666, posicionfallo2);
		MiembroUSC userE = new MiembroUSC("Ramon", "5", "ramon", "contrasena", "ramon@gmail.com", 666666666, posicionnull);

		BBDD.obtenerBBDD().getUsuarios().add(userA);
		BBDD.obtenerBBDD().getUsuarios().add(userB);	
		BBDD.obtenerBBDD().getUsuarios().add(userC);
		BBDD.obtenerBBDD().getUsuarios().add(userD);
		BBDD.obtenerBBDD().getUsuarios().add(userE);
		
		assertEquals(alerta2,GAS.informarAlarma(alert),"Error al informar alarma recorriendo bucle 2 veces.");
		
		
		}
	}
	@Nested
	@DisplayName("crud Protocolo [PRB-008]")
	class crudProtocolo{
		@BeforeEach
		void setUp() throws Exception {
			bbdd = BBDD.obtenerBBDD();
		}

		@AfterEach
		void tearDown() throws Exception {
			bbdd.getAlarmas().clear(); //Como está implementado o patrón Singleton, é necesario eliminar as alarmas porque a BBDD vai seguir sendo a mesma
			bbdd=null;
		}
		@Test
		void testCrudProtocolo1() {
			
			Protocolo protocolo1 = new Protocolo(2,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo1in = GAS.crudProtocolo(protocolo1, 0);
			assertEquals(protocolo1,protocolo1in,"Los protocolos no son iguales");
		}
		
		@Test
		void testCrudProtocolo2() {
			
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo1in = GAS.crudProtocolo(protocolo1, 1);
			assertEquals(protocolo1,protocolo1in,"Los protocolos no son iguales");
		}
		
		@Test
		void testCrudProtocolo3() {

			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(1,"Encender bomba de achique",3);
			Protocolo protocolo1in = GAS.crudProtocolo(protocolo1, 1);
			Protocolo protocolo2in = GAS.crudProtocolo(protocolo2, 2);
			assertEquals(protocolo1in.getTipo(),protocolo2in.getTipo(),"No se modificó correctamente el tipo del protocolo");
			assertEquals(protocolo1in.getDescripcion(),protocolo2in.getDescripcion(),"No se modificó correctamente la descripción del protocolo");
		}
		
		@Test
		void testCrudProtocolo4() {
			
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo1in = GAS.crudProtocolo(protocolo1, 1);
			Protocolo protocolo2in = GAS.crudProtocolo(protocolo1, 3);
			assertEquals(protocolo1in,protocolo2in,"No se borró el protocolo indicado");
			
		}
		
		@Test
		void testCrudProtocolo5() {
		
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(1,"Utilizar manguera",1);
			bbdd.getProtocolos().add(protocolo1);
			Protocolo protocoloaux = GAS.crudProtocolo(protocolo2, 0);
			assertTrue(bbdd.getProtocolos().contains(protocolo1),"El protocolo no está en la BBDD");
			assertFalse(bbdd.getProtocolos().contains(protocolo2),"El protocolo está en la BBDD");
			
		}
		
		@Test
		void testCrudProtocolo6() {
		
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(1,"Utilizar manguera",1);
			bbdd.getProtocolos().add(protocolo1);
			Protocolo protocoloaux=GAS.crudProtocolo(protocolo2, 2);
			assertEquals(protocolo2,protocoloaux,"Los protocolos no son iguales");
			assertTrue(bbdd.getProtocolos().contains(protocolo1),"El protocolo no está en la BBDD");
			assertFalse(bbdd.getProtocolos().contains(protocolo2),"El protocolo está en la BBDD");
			assertEquals(protocolo1.getDescripcion(),protocolo2.getDescripcion(),"La descripcion no se ha modificado");
		}
		
		@Test
		void testCrudProtocolo7() {
		
			Protocolo protocolo1 = new Protocolo(3,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(3,"Utilizar manguera",1);
			bbdd.getProtocolos().add(protocolo1);
			Protocolo protocoloaux=GAS.crudProtocolo(protocolo2, 3);
			assertEquals(protocolo2,protocoloaux,"Los protocolos no son iguales");
			assertFalse(bbdd.getProtocolos().contains(protocolo1));
		
		}
		
		@Test
		void testCrudProtocolo8() {
		
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(2,"Utilizar manguera",1);
			bbdd.getProtocolos().add(protocolo1);
			Protocolo protocoloaux=GAS.crudProtocolo(protocolo2, 0);
			assertEquals(protocolo2, protocoloaux,"Los protocolos no son iguales");
			assertNotEquals(protocolo1,protocoloaux,"Los protocolos son iguales");
			assertTrue(bbdd.getProtocolos().contains(protocolo1));
			assertFalse(bbdd.getProtocolos().contains(protocolo2));
		}
		
		@Test
		void testCrudProtocolo9() {
		
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(2,"Utilizar manguera",4);
			bbdd.getProtocolos().add(protocolo1);
			Protocolo protocoloaux=GAS.crudProtocolo(protocolo2, 2);
			assertEquals(protocolo2, protocoloaux,"Los protocolos no son iguales");
			assertTrue(bbdd.getProtocolos().contains(protocolo1),"El protocolo no está en la BBDD");
		}
		
		@Test
		void testCrudProtocolo10() {
		
			Protocolo protocolo1 = new Protocolo(1,"Utilizar extintor para apagar incendio",1);
			Protocolo protocolo2 = new Protocolo(2,"Utilizar manguera",1);
			bbdd.getProtocolos().add(protocolo1);
			Protocolo protocoloaux=GAS.crudProtocolo(protocolo2, 3);
			assertEquals(protocolo2, protocoloaux,"Los protocolos no son iguales");
			assertTrue(bbdd.getProtocolos().contains(protocolo1),"El protocolo no está en la BBDD");
			
		}
	}
	@Nested
	@DisplayName("Informar Alarma [PRB-009]")
	class informarAlarma{
		@BeforeEach
		void setUp() throws Exception {
			bbdd = BBDD.obtenerBBDD();
			alarm = new Alarma(1, "Tormenta");
			alert=new Alerta("Activa", 1, posAlert, "ETSE", new Date(), alarm);
		}

		@AfterEach
		void tearDown() throws Exception {
			bbdd.getAlarmas().clear(); //Como está implementado o patrón Singleton, é necesario eliminar as alarmas porque a BBDD vai seguir sendo a mesma
			bbdd=null;
		}
		@Test
		void testInformarAlarma1() {
			assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 1.");
		}
		@Test
		void testInformarAlarma2() {
			MiembroEquipo me = new MiembroEquipo("Pablo","12345678X","p12","p12","pepe@terra.net",123456789,posUserV,"1","activo");
			bbdd.getUsuarios().add(me);
			assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 2.");
		}
		@Test
		void testInformarAlarma3() {
			Usuario us = new Usuario("Ana","87654321Y","p11","p11","ana@terra.net",987654321);
			bbdd.getUsuarios().add(us);
			assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 3.");
		}
		@Test
		void testInformarAlarma4() {
			MiembroUSC musc = new MiembroUSC("Ana","87654321Y","p11","p11","ana@terra.net",987654321,posUserF);
			bbdd.getUsuarios().add(musc);
			assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 4.");
		}
		@Test
		void testInformarAlarma5() {
			MiembroUSC musc = new MiembroUSC("Ana","87654321Y","p11","p11","ana@terra.net",987654321,posUserV);
			bbdd.getUsuarios().add(musc);
			assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma camino nº 5.");
		}
		@Test
		void testInformarAlarma6() {
			MiembroUSC musc = new MiembroUSC("Ana","87654321Y","p31","p11","ana@terra.net",987654321,posUserV);
			MiembroUSC musc2 = new MiembroUSC("Pedro","1269183Y","p30","p10","pedro2@terra.net",981823321,posUserV);
			bbdd.getUsuarios().add(musc);
			bbdd.getUsuarios().add(musc2);
			assertEquals(alert,GAS.informarAlarma(alert),"Error al informar alarma recorriendo bucle 2 veces.");
		}
		@Test
		void testInformarAlarma7() {
			MiembroEquipo me = new MiembroEquipo("Pablo","12345678X","p12","p12","pepe@terra.net",123456789,posUserV,"1","activo");
			MiembroEquipo me2 = new MiembroEquipo("Pedro","8769183Y","p10","p10","pedro@terra.net",981823321,posUserV,"2","activo");
			bbdd.getUsuarios().add(me);
			bbdd.getUsuarios().add(me2);
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
}
