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
	private String token;
	private long id;
	
	
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
		token = getToken(); //necesario para sucesivas peticiones

		cabecera.add("Authorization", token);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		Procedimiento procedimiento = new Procedimiento("AAA", "BBB");

		HttpEntity<Procedimiento> entity = new HttpEntity<Procedimiento>(procedimiento, cabecera);

		ResponseEntity<Procedimiento> response = restTemplate.exchange(
				crearUrlConPuerto("/api/procedimientos"),
				HttpMethod.POST, entity, Procedimiento.class);
		
		id = response.getBody().getId(); //necesario para sucesivas peticiones

		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
	}
	
	
	/*
	 * LEER: Objeto creado en el @Before
	 * 
	 * Comprueba que coincidan los campos que se crearon.
	 */
	@Test
	public void test2() {
		cabecera.add("Authorization", token);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entity = new HttpEntity<String>(null, cabecera);

		ResponseEntity<Procedimiento> response = restTemplate.exchange(
				crearUrlConPuerto("/api/procedimientos/" + id),
				HttpMethod.GET, entity, Procedimiento.class);

		assertThat(response.getBody().getCodigo(), equalTo("AAA"));
		assertThat(response.getBody().getNombre(), equalTo("BBB"));
	}
	

	/*
	 * MODIFICAR: Objeto creado en el @Before.
	 * 
	 * Comprueba que obtiene mensaje OK (200)
	 */
	@Test
	public void test3() { 
		cabecera.add("Authorization", token);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		Procedimiento procedimiento = new Procedimiento("XXX", "YYY");

		HttpEntity<Procedimiento> entity = new HttpEntity<Procedimiento>(procedimiento, cabecera);

		ResponseEntity<Procedimiento> response = restTemplate.exchange(
				crearUrlConPuerto("/api/procedimientos/" + id),
				HttpMethod.PUT, entity, Procedimiento.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	
	/*
	 * ELIMINAR: Objeto creado en el @Before
	 * Comprueba que obtiene mensaje NO_CONTENT (204)
	 */
	@After
	public void test4() {
		cabecera.add("Authorization", token);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> entity = new HttpEntity<String>(null, cabecera);

		ResponseEntity<String> response = restTemplate.exchange(
				crearUrlConPuerto("/api/procedimientos/" + id),
				HttpMethod.DELETE, entity, String.class);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.NO_CONTENT));
	}	
	
	
	private String crearUrlConPuerto(String uri) {
		return "http://localhost:" + port + uri;
	}	
	

	private String getToken() {
		Autenticar autenticar = new Autenticar("admin@user.es", "admin");
		cabecera.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<Autenticar> entity = new HttpEntity<Autenticar>(autenticar, cabecera);

		ResponseEntity<TokenResponse> response = restTemplate.exchange(
				crearUrlConPuerto("/auth"),
				HttpMethod.POST, entity, TokenResponse.class);

		return "Bearer " + response.getBody().getToken();
	}

}
