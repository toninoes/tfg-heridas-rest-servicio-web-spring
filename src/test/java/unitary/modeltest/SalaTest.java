package unitary.modeltest;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.config.salas.SalaConfig;
import rha.model.Centro;
import rha.model.Cita;
import rha.model.Sala;

@RunWith(MockitoJUnitRunner.class)
public class SalaTest {

	private static final String NOMBRE1 = "Sala1";
	private static final String NOMBRE2 = "Sala2";
	
	@Mock
	Centro centroMock1;
	
	@Mock
	Centro centroMock2;
	
	@Mock
	Set<Cita> citasMock1;
	
	@Mock
	Set<Cita> citasMock2;
	
	@Mock
	SalaConfig salaConfigMock1;
	
	@Mock
	SalaConfig salaConfigMock2;
	
	@InjectMocks
	Sala salaMock;
	
	@Test
	public void salaTesting() {
		Sala salaMock = new Sala(NOMBRE1, centroMock1);
		salaMock.setCitas(citasMock1);
		salaMock.setSalaConfig(salaConfigMock1);
		
		assertEquals(salaMock.getNombre(), NOMBRE1);
		assertEquals(salaMock.getCentro(), centroMock1);
		assertEquals(salaMock.getCitas(), citasMock1);
		assertEquals(salaMock.getSalaConfig(), salaConfigMock1);
		
		salaMock.setNombre(NOMBRE2);
		salaMock.setCentro(centroMock2);
		salaMock.setCitas(citasMock2);
		salaMock.setSalaConfig(salaConfigMock2);
		
		assertEquals(salaMock.getNombre(), NOMBRE2);
		assertEquals(salaMock.getCentro(), centroMock2);
		assertEquals(salaMock.getCitas(), citasMock2);
		assertEquals(salaMock.getSalaConfig(), salaConfigMock2);
		
		
	}
}
