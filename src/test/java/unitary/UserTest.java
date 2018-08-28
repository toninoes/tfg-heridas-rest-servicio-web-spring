package unitary;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.jwt.model.security.Authority;
import rha.jwt.model.security.User;
import rha.model.UserCentro;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
	
	private static final String USERNAME1 = "user1";
	private static final String USERNAME2 = "user2";
	
	private static final String PASSWORD1 = "pass1";
	private static final String PASSWORD2 = "pass2";
	
	private static final String FIRSTNAME1 = "firstname1";
	private static final String FIRSTNAME2 = "firstname2";
	
	private static final String LASTNAME1 = "lastname1";
	private static final String LASTNAME2 = "lastname2";
	
	private static final String EMAIL1 = "email1";
	private static final String EMAIL2 = "email2";
	
	private static final String DNI1 = "dni1";
	private static final String DNI2 = "dni2";
	
	@Mock
	Date nacimientoMock1;
	
	@Mock
	Date nacimientoMock2;
	
	private static final Long HISTORIA1 = (long) 1;
	private static final Long HISTORIA2 = (long) 5;
	
	private static final String COLEGIADO1 = "a112951";
	private static final String COLEGIADO2 = "b112951";
	
	private static final Boolean SIENABLED = true;
	private static final Boolean NOENABLED = false;
	
	@Mock
	List<Authority> authoritiesMock1;
	
	@Mock
	List<Authority> authoritiesMock2;
	
	@Mock
	Set<UserCentro> userCentrosMock1;
	
	@Mock
	Set<UserCentro> userCentrosMock2;
	
	@InjectMocks
	User userMock;
	
	@Test
	public void userTesting() {
		User userMock = new User(USERNAME1, PASSWORD1, FIRSTNAME1, LASTNAME1, EMAIL1, NOENABLED,
				authoritiesMock1, nacimientoMock1, DNI1);
		userMock.setHistoria(HISTORIA1);
		userMock.setColegiado(COLEGIADO1);
		userMock.setUserCentros(userCentrosMock1);
		
		assertEquals(userMock.getUsername(), USERNAME1);
		assertEquals(userMock.getPassword(), PASSWORD1);
		assertEquals(userMock.getFirstname(), FIRSTNAME1);
		assertEquals(userMock.getLastname(), LASTNAME1);
		assertEquals(userMock.getEmail(), EMAIL1);
		assertEquals(userMock.isEnabled(), NOENABLED);
		assertEquals(userMock.getAuthorities(), authoritiesMock1);
		assertEquals(userMock.getNacimiento(), nacimientoMock1);
		assertEquals(userMock.getDni(), DNI1);
		assertEquals(userMock.getHistoria(), HISTORIA1);
		assertEquals(userMock.getColegiado(), COLEGIADO1);
		assertEquals(userMock.getUserCentros(), userCentrosMock1);
		
		userMock.setUsername(USERNAME2);
		userMock.setPassword(PASSWORD2);
		userMock.setFirstname(FIRSTNAME2);
		userMock.setLastname(LASTNAME2);
		userMock.setEmail(EMAIL2);
		userMock.setEnabled(SIENABLED);
		userMock.setAuthorities(authoritiesMock2);
		userMock.setNacimiento(nacimientoMock2);
		userMock.setDni(DNI2);
		userMock.setHistoria(HISTORIA2);
		userMock.setColegiado(COLEGIADO2);
		userMock.setUserCentros(userCentrosMock2);
		
		assertEquals(userMock.getUsername(), USERNAME2);
		assertEquals(userMock.getPassword(), PASSWORD2);
		assertEquals(userMock.getFirstname(), FIRSTNAME2);
		assertEquals(userMock.getLastname(), LASTNAME2);
		assertEquals(userMock.getEmail(), EMAIL2);
		assertEquals(userMock.isEnabled(), SIENABLED);
		assertEquals(userMock.getAuthorities(), authoritiesMock2);
		assertEquals(userMock.getNacimiento(), nacimientoMock2);
		assertEquals(userMock.getDni(), DNI2);
		assertEquals(userMock.getHistoria(), HISTORIA2);
		assertEquals(userMock.getColegiado(), COLEGIADO2);
		assertEquals(userMock.getUserCentros(), userCentrosMock2);
		
		
		
		
	}
	
	

}
