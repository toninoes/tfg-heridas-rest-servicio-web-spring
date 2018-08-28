package unitary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Cuidado;
import rha.model.Diagnostico;
import rha.model.Grupodiagnostico;

@RunWith(MockitoJUnitRunner.class)
public class GrupodiagnosticoTest {
	
	private static final String NOMBRE1 = "Grupo1";
	private static final String NOMBRE2 = "Grupo2";
	
	@Mock
	List<Diagnostico> diagnosticosMock1;
	
	@Mock
	List<Diagnostico> diagnosticosMock2;
	
	@Mock
	List<Cuidado> cuidadosticosMock1;
	
	@Mock
	List<Cuidado> cuidadosticosMock2;
	
	@InjectMocks
	Grupodiagnostico grupodiagnosticoMock;
	
	@Test
	public void grupodiagnosticoTesting() {
		
		Grupodiagnostico grupodiagnosticoMock = new Grupodiagnostico(NOMBRE1, 
				diagnosticosMock1, cuidadosticosMock1);
		
		assertEquals(grupodiagnosticoMock.getNombre(), NOMBRE1);
		assertEquals(grupodiagnosticoMock.getDiagnosticos(), diagnosticosMock1);
		assertEquals(grupodiagnosticoMock.getCuidados(), cuidadosticosMock1);
		
		grupodiagnosticoMock.setNombre(NOMBRE2);
		grupodiagnosticoMock.setDiagnosticos(diagnosticosMock2);
		grupodiagnosticoMock.setCuidados(cuidadosticosMock2);
		
		assertEquals(grupodiagnosticoMock.getNombre(), NOMBRE2);
		assertEquals(grupodiagnosticoMock.getDiagnosticos(), diagnosticosMock2);
		assertEquals(grupodiagnosticoMock.getCuidados(), cuidadosticosMock2);
		
		
	}
	
	

}
