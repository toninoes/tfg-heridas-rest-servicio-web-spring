package unitary;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Centro;
import rha.model.Grupodiagnostico;
import rha.model.Sala;
import rha.model.UserCentro;

@RunWith(MockitoJUnitRunner.class)
public class CentroTest {
	
	private static final String NOMBRE1 = "Centro1";
	private static final String NOMBRE2 = "Centro2";
	
	private static final String DIRECCION1 = "Dirección1";
	private static final String DIRECCION2 = "Dirección2";
	
	private static final String TELEFONO1 = "Teléfono1";
	private static final String TELEFONO2 = "Teléfono2";
	
	@Mock
	Set<UserCentro> userCentrosMock1;
	
	@Mock
	Set<UserCentro> userCentrosMock2;
	
	@Mock
	List<Sala> salasMock1;
	
	@Mock
	List<Sala> salasMock2;
	
	@InjectMocks
	Grupodiagnostico centroMock;
	
	@Test
	public void centroTesting() {
		
		Centro centroMock = new Centro(NOMBRE1, DIRECCION1, TELEFONO1);
		centroMock.setUserCentros(userCentrosMock1);
		centroMock.setSalas(salasMock1);
		
		assertEquals(centroMock.getNombre(), NOMBRE1);
		assertEquals(centroMock.getDireccion(), DIRECCION1);
		assertEquals(centroMock.getTelefono(), TELEFONO1);
		assertEquals(centroMock.getUserCentros(), userCentrosMock1);
		assertEquals(centroMock.getSalas(), salasMock1);
		
		centroMock.setNombre(NOMBRE2);
		centroMock.setDireccion(DIRECCION2);
		centroMock.setTelefono(TELEFONO2);
		centroMock.setUserCentros(userCentrosMock2);
		centroMock.setSalas(salasMock2);
		
		assertEquals(centroMock.getNombre(), NOMBRE2);
		assertEquals(centroMock.getDireccion(), DIRECCION2);
		assertEquals(centroMock.getTelefono(), TELEFONO2);
		assertEquals(centroMock.getUserCentros(), userCentrosMock2);
		assertEquals(centroMock.getSalas(), salasMock2);
		
	}

}
