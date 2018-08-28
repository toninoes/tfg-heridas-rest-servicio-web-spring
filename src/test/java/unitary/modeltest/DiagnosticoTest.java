package unitary.modeltest;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Diagnostico;
import rha.model.Grupodiagnostico;
import rha.model.Proceso;

@RunWith(MockitoJUnitRunner.class)
public class DiagnosticoTest {
	
	private static final String CODIGO1 = "Código1";
	private static final String CODIGO2 = "Código2";
	
	private static final String NOMBRE1 = "Diagnóstico1";
	private static final String NOMBRE2 = "Diagnóstico2";
	
	@Mock
	List<Proceso> procesosMock1;
	
	@Mock
	List<Proceso> procesosMock2;
	
	@Mock
	Grupodiagnostico grupodiagnosticoMock1;
	
	@Mock
	Grupodiagnostico grupodiagnosticoMock2;
	
	@InjectMocks
	Diagnostico diagnosticoMock;
	
	@Test
	public void diagnosticoTesting() {
		
		Diagnostico diagnosticoMock = new Diagnostico(CODIGO1, NOMBRE1);
		diagnosticoMock.setProcesos(procesosMock1);
		diagnosticoMock.setGrupodiagnostico(grupodiagnosticoMock1);
		
		assertEquals(diagnosticoMock.getCodigo(), CODIGO1);
		assertEquals(diagnosticoMock.getNombre(), NOMBRE1);
		assertEquals(diagnosticoMock.getProcesos(), procesosMock1);
		assertEquals(diagnosticoMock.getGrupodiagnostico(), grupodiagnosticoMock1);
		
		diagnosticoMock.setCodigo(CODIGO2);
		diagnosticoMock.setNombre(NOMBRE2);
		diagnosticoMock.setProcesos(procesosMock2);
		diagnosticoMock.setGrupodiagnostico(grupodiagnosticoMock2);
		
		assertEquals(diagnosticoMock.getCodigo(), CODIGO2);
		assertEquals(diagnosticoMock.getNombre(), NOMBRE2);
		assertEquals(diagnosticoMock.getProcesos(), procesosMock2);
		assertEquals(diagnosticoMock.getGrupodiagnostico(), grupodiagnosticoMock2);
		
	}
	
	

}
