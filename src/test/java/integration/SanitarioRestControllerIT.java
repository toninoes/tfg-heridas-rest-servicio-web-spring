package integration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;

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
import rha.jwt.model.security.User;
import rha.model.mapping.Autenticar;
import rha.model.mapping.TokenResponse;

@RunWith(SpringRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SanitarioRestControllerIT {

	@LocalServerPort
	private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders cabecera = new HttpHeaders();
	
	private static String URLSERVICIO	= "/api/sanitarios/";
	private static String USERNAME		= "admin@user.es";
	private static String PASSWORD		= "admin";
	
	private static String TOKEN;	// Token de autenticación
	private static long DEFAULT_SANITARIO_ID = 2;	// ID del sanitario creado por defecto
	
	
	@Before
	public void setToken() { 
		TOKEN = getToken(); //necesario para sucesivas peticiones
	}
	
	
	/*
	 * LEER TODOS
	 * 
	 * Comprueba obtener mensaje de estado OK (200)
	 */
	public void test1() { 
		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		HttpEntity<String> entidad = new HttpEntity<String>(null, cabecera);

		@SuppressWarnings("rawtypes")
		ResponseEntity<ArrayList> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO),
				HttpMethod.GET, entidad, ArrayList.class);

		assertThat(respuesta.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	
	/*
	 * LEER UNO: Sanitario
	 * 
	 * Comprueba que coincidan los campos almacenados.
	 */
	@Test
	public void test2() {
		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entidad = new HttpEntity<String>(null, cabecera);

		ResponseEntity<User> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO + DEFAULT_SANITARIO_ID),
				HttpMethod.GET, entidad, User.class);

		assertThat(respuesta.getStatusCode(), equalTo(HttpStatus.OK));
	}
	
	
	/*
	 * LEER UNO INEXISTENTE: Sanitario 1
	 * 
	 * Comprueba que coincidan los campos almacenados.
	 */
	@Test
	public void test3() {
		cabecera.add("Authorization", TOKEN);
		cabecera.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		
		HttpEntity<String> entidad = new HttpEntity<String>(null, cabecera);

		ResponseEntity<User> respuesta = restTemplate.exchange(
				crearUrlConPuerto(URLSERVICIO + 1000),
				HttpMethod.GET, entidad, User.class);

		assertThat(respuesta.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
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
