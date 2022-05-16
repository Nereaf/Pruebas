package pruebas;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class TestGestorUsuarios {

    static GestorUsuarios gu;
    static MiembroUSC usr;
    static MiembroUSC b;
    static BBDD bbdd;
    @BeforeAll
    static void setUpBeforeClass() {
        gu = new GestorUsuarios();
        usr = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
        bbdd = BBDD.obtenerBBDD();
        bbdd.getUsuarios().add(usr);
    }
    @Nested
    @DisplayName("Notificar Alarma [PRB-003]")
    class notificarAlarma{
    	@BeforeEach
    	void setUp() throws Exception {
    		b = new MiembroUSC("Manolo", "45334567Z", "ElBicho123", "4321", "CR7@gmail.com", 666879786, new Float[]{(float)0, (float)0});
    		bbdd.getUsuarios().add(b);
    		
    	}
    	@AfterEach
    	void tearDown() throws Exception {
    		bbdd.getUsuarios().clear();
    	}

    	@Test
    	@DisplayName("CPN-003-01")
    	void testNotificarAlarma01() {
    		
    		Alarma alarma = new Alarma(0, "Alarma de Incendios");
    		Alerta alerta= new Alerta("Activa", 0, new Float[] {(float) 2.453,(float) 6.4564}, "ETSE", new Date(), alarma);
    		Boolean activacion=null;
    		
    		activacion=gu.notificarAlarma(alerta);
    		
    		assertTrue(activacion, "A alarma non se activa");
    		
    	}
    	
    	@Test
    	@DisplayName("CPN-003-02")
    	void testNotificarAlarma02() {
    		
    		Alerta alerta= new Alerta("Activa", 0, new Float[] {(float) 2.453,(float) 6.4564}, "ETSE", new Date(), null);
    		Boolean activacion=null;
    		
    		activacion=gu.notificarAlarma(alerta);
    		
    		assertFalse(activacion, "La alarma se activa");
    		
    	}
    	
    	@Test
    	@DisplayName("CPN-003-03")
    	void testNotificarAlarma03() {
    		
    		Alerta alerta= null;
    		Boolean activacion=null;
    		
    		activacion=gu.notificarAlarma(alerta);
    		
    		assertFalse(activacion, "La alarma se activa");
    		
    	}
    	
    	@Test
    	@DisplayName("CPN-003-04")
    	void testNotificarAlarma04() {
    		
    		Alarma alarma = new Alarma(0, "Alarma de Incendios");;
    		Alerta alerta= new Alerta("Activa", 0, null, "ETSE", new Date(), alarma);
    		Boolean activacion=null;
    		
    		activacion=gu.notificarAlarma(alerta);
    		
    		assertFalse(activacion, "La alarma se activa");
    		
    	}

    	@Test
    	@DisplayName("CPN-003-05")
    	void testNotificarAlarma05() {
    		
    		Alarma alarma = new Alarma(0, "Alarma de Incendios");;
    		Alerta alerta = new Alerta("Activa", 0, new Float[] {(float) 2.453,(float) 6.4564}, "ETSE", null, alarma);
    		Boolean activacion=null;
    		
    		activacion=gu.notificarAlarma(alerta);
    		
    		assertFalse(activacion, "La alarma se activa");
    		
    	}
    }
    @Nested
    @DisplayName("crudUsuario")
    class crudUsuario{
    	
	    @Test
	    void testCamino1() {
	        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
	        usuario.setCorreo("");
	        assertNull(gu.crudUsuario(usuario, 1));
	    }
	
	    @Test
	    void testCamino2() {
	        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
	        usuario.setCorreo("emailIncorrecto");
	        assertNull(gu.crudUsuario(usuario, 1));
	    }
	
	    @Test
	    void testCamino3() {
	        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
	        usuario.setContrasena("contrasenaFalsa");
	        assertNull(gu.crudUsuario(usuario, 1));
	    }
	
	    @Test
	    void testCamino4() {
	        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
	        usuario.setNombre("nombreNuevo");
	        usuario.setDNI("DNINuevo");
	        usuario.setTelef(1111);
	        usuario.setUsr(usuario.getUsr());
	        assertEquals(usr, gu.crudUsuario(usuario, 1));
	    }
	
	    @Test
	    void testCamino5(){
	        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
	        assertEquals(usr, gu.crudUsuario(usuario, 2));
	        assertEquals(0, BBDD.obtenerBBDD().getUsuarios().size());
	    }
	
	    @Test
	    void testCamino6(){
	        MiembroUSC usuario = new MiembroUSC("nombre", "DNI", "usuario", "contrasena", "email", 1234, null);
	        assertNull(gu.crudUsuario(usuario, 5));
	    }
    } 
    @Nested
    @DisplayName("crudUsuarioAdmin [PRB-005]")
    class crudUsuarioAdmin{
    	@AfterEach
    	void tearDown() throws Exception {
    		bbdd.getUsuarios().clear();
    	}
    		@Test
    		@DisplayName("Prueba [CPB-010-01]")
    		void crudUsuario01() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "juan", "65985488L", "juanjo", "1234", "juanitojuanjo@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			MiembroUSC usr2= gu.crudUsuario(admin, usc, 1);
    			assertNull(usr2, "no se ha devulveo lo esperado");
    		

    		}
    		@Test
    		@DisplayName("Prueba [CPB-010-02]")
    		void crudUsuario02() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "juan", "65985488L", "juanjo", "1234", "juanitojuanjo@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			MiembroUSC usr2= gu.crudUsuario(admin, usc, 1);
    			assertNull(usr2, "no we ha devulveo lo mismo");
    		}

    		@Test
    		@DisplayName("Prueba [CPB-010-03]")
    		void crudUsuario03() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "4321", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			MiembroUSC usr2= gu.crudUsuario(admin, usc, 1);
    			assertNull(usr2, "no we ha devulveo lo mismo");
    		}

    		@Test
    		@DisplayName("CDP-005-04")
    		void crudUsuario04() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			MiembroUSC usr2= gu.crudUsuario(admin, usc, 2);
    			assertNull(usr2, "no we ha devulveo lo mismo");
    		}
    		@Test
    		@DisplayName("Prueba [CPB-010-05]")
    		void crudUsuario05() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "juan", "65985488L", "juanjo", "1234", "juanitojuanjo@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			bbdd.getUsuarios().add(usc);
    			gu.crudUsuario(admin, usc, 1);
    			assertTrue(bbdd.getUsuarios().contains(usc), "no contiene al usuario");
    		}
    		@Test
    		@DisplayName("Prueba [CPB-010-06]")
    		void crudUsuario06() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			gu.crudUsuario(admin, usc, 1);
    			assertTrue(bbdd.getUsuarios().contains(usc), "no contiene al usuario");

    			}

    		@Test
    		@DisplayName("Prueba [CPB-010-07]")
    		void crudUsuario07() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			bbdd.getUsuarios().add(usc);
    			MiembroUSC usr2= gu.crudUsuario(admin, usc, 1);
    			assertTrue(usr2.equals(usc),"no se ha devuelto lo mismo");
    		}

    		@Test
    		@DisplayName("Prueba [CPB-010-08]")
    		void crudUsuario08() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			bbdd.getUsuarios().add(usc);
    			MiembroUSC usr1 = new MiembroUSC("pepote", "54897562M", "pepe03", "1234", "pepe@pepe.pepe", 456897154, ubicacion);
    			usc= gu.crudUsuario(admin, usr1, 2);
    			assertEquals(usc,usr1, "los usuarios no son los mismo");
    		}

    		@Test
    		@DisplayName("Prueba [CPB-010-09]")
    		void crudUsuario09() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			bbdd.getUsuarios().add(usc);
    			
    			assertNotNull( gu.crudUsuario(admin, usc, 3), "error al eliminar el usuario");
    			assertNull(gu.crudUsuario(admin, usc, 4), "los usuarios no son los mismo");
    		}

    		@Test
    		@DisplayName("Prueba [CPB-010-10]")
    		void crudUsuario10() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			bbdd.getUsuarios().add(usc);
    			MiembroUSC usc1= new MiembroUSC(null,null ,null, null, "pepe@pepe.pepe", null,null);
    			usc1=gu.crudUsuario(admin, usc1, 4);
    			assertEquals(usc,usc1,"Los usuarios no son iguales");
    			
    		}

    		@Test
    		@DisplayName("Prueba [CPB-010-11]")
    		void crudUsuario11() {
    			Administrador ad= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			bbdd.getUsuarios().add(ad);
    			Administrador admin= new Administrador( "mariana", "65985458L", "mariana02", "1234", "mariana@rai.usc.es", 659874534, "administrativo");
    			Float[] ubicacion= {45.45f, 89.54f};
    			MiembroUSC usc= new MiembroUSC("pepe", "54897562M", "pepecito", "1234", "pepe@pepe.pepe", 89875645,ubicacion );
    			bbdd.getUsuarios().add(usc);
    			assertNull(gu.crudUsuario(admin, usc, 5), "no se ha devuelto null");
    			
    		}
    }
    
}
