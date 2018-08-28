package unitary;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.jwt.model.security.User;
import rha.model.Centro;
import rha.model.UserCentro;

@RunWith(MockitoJUnitRunner.class)
public class UserCentroTest {

	@Mock
	User userMock1;
	
	@Mock
	User userMock2;
	
	@Mock
	Centro centroMock1;
	
	@Mock
	Centro centroMock2;
	
	@Mock
	Date inicioMock1;
	
	@Mock
	Date inicioMock2;
	
	@Mock
	Date finMock1;
	
	@Mock
	Date finMock2;
	
	@InjectMocks
	UserCentro usercentroMock;
	
	@Test
	public void userCentroTesting() {
		
		UserCentro usercentroMock = new UserCentro(userMock1, centroMock1, inicioMock1);
		usercentroMock.setFin(finMock1);
		
		assertEquals(usercentroMock.getUser(), userMock1);
		assertEquals(usercentroMock.getCentro(), centroMock1);
		assertEquals(usercentroMock.getInicio(), inicioMock1);
		assertEquals(usercentroMock.getFin(), finMock1);
		
		usercentroMock.setUser(userMock2);
		usercentroMock.setCentro(centroMock2);
		usercentroMock.setInicio(inicioMock2);
		usercentroMock.setFin(finMock2);
		
		assertEquals(usercentroMock.getUser(), userMock2);
		assertEquals(usercentroMock.getCentro(), centroMock2);
		assertEquals(usercentroMock.getInicio(), inicioMock2);
		assertEquals(usercentroMock.getFin(), finMock2);
		
		
	}
}
