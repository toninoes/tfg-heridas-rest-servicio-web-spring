package integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runners.MethodSorters;
import rha.Application;
import rha.model.Procedimiento;
import rha.model.mapping.Autenticar;
import rha.model.mapping.TokenResponse;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProcedimientoRestControllerIT {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders cabecera = new HttpHeaders();
	
	private static String URLSERVICIO	= "/api/procedimientos/";
	private static String USERNAME		= "admin@user.es";
	private static String PASSWORD		= "admin";
	
	private static String TOKEN;	// Token de autenticación
	private static long ID;			// ID del objeto que se creará.
	
	
	/*
	 * CREAR
	 * Obtiene además:
	 *     - Token de autenticación, y lo guarda para siguientes peticiones de @Test.
	 *     - Id del objeto recién, necesario para las demás peticiones.
	 * 
	 * Comprueba obtener mensaje de estado CREATED (201)
	 */
	@Before
	public void test1() { 
		TOKEN = getToken(); //necesario para sucesivas peticiones

		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		Procedimiento nuevo = new Procedimiento("AAA", "BBB");

		HttpEntity<Procedimiento> entidad = new HttpEntity<Procedimiento>(nuevo, cabecera);

		ResponseEntity<Procedimiento> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO),
				HttpMethod.POST, entidad, Procedimiento.class);
		
		ID = respuesta.getBody().getId(); //necesario para sucesivas peticiones

		assertThat(respuesta.getStatusCode(), equalTo(HttpStatus.CREATED));
	}
	
	
	/*
	 * LEER: Objeto creado en el @Before
	 * 
	 * Comprueba que coincidan los campos que se crearon.
	 */
	@Test
	public void test2() {
		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entidad = new HttpEntity<String>(null, cabecera);

		ResponseEntity<Procedimiento> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO + ID),
				HttpMethod.GET, entidad, Procedimiento.class);

		assertThat(respuesta.getBody().getCodigo(), equalTo("AAA"));
		assertThat(respuesta.getBody().getNombre(), equalTo("BBB"));
	}
	

	/*
	 * MODIFICAR: Objeto creado en el @Before.
	 * 
	 * Comprueba que obtiene mensaje OK (200)
	 */
	@Test
	public void test3() { 
		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		Procedimiento editar = new Procedimiento("XXX", "YYY");

		HttpEntity<Procedimiento> entidad = new HttpEntity<Procedimiento>(editar, cabecera);

		ResponseEntity<Procedimiento> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO + ID),
				HttpMethod.PUT, entidad, Procedimiento.class);

		assertThat(respuesta.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	
	/*
	 * ELIMINAR: Objeto creado en el @Before
	 * Comprueba que obtiene mensaje NO_CONTENT (204)
	 */
	@After
	public void test4() {
		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> entidad = new HttpEntity<String>(null, cabecera);

		ResponseEntity<String> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO + ID),
				HttpMethod.DELETE, entidad, String.class);

		assertThat(respuesta.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
	}	
	
	
	private String crearUrlConPuerto(String url) {
		return "http://localhost:" + port + url;
	}	
	

	private String getToken() {
		Autenticar autenticar = new Autenticar(USERNAME, PASSWORD);
		cabecera.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Autenticar> entidad = new HttpEntity<Autenticar>(autenticar, cabecera);

		ResponseEntity<TokenResponse> respuesta = restTemplate.exchange(
				crearUrlConPuerto("/auth"),
				HttpMethod.POST, entidad, TokenResponse.class);

		return "Bearer " + respuesta.getBody().getToken();
	}

}
