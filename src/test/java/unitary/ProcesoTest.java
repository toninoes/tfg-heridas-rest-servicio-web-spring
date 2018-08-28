package unitary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Cura;
import rha.model.Diagnostico;
import rha.model.Paciente;
import rha.model.Procedimiento;
import rha.model.Proceso;

@RunWith(MockitoJUnitRunner.class)
public class ProcesoTest {
	
	private static final String anamnesis1 = "anamnesis1";
	private static final String anamnesis2 = "anamnesis2";
	
	@Mock
	Diagnostico diagnosticoMock1;
	
	@Mock
	Diagnostico diagnosticoMock2;
	
	@Mock
	Procedimiento procedimientoMock1;
	
	@Mock
	Procedimiento procedimientoMock2;
	
	@Mock
	Paciente pacienteMock1;
	
	@Mock
	Paciente pacienteMock2;
	
	@Mock
	List<Cura> curasMock1;
	
	@Mock
	List<Cura> curasMock2;
	
	private static final String observaciones1 = "observaciones1";
	private static final String observaciones2 = "observaciones2";
	
	@InjectMocks
	Proceso procesoMock;
	
	@Test
	public void procesoTesting() {
		Proceso procesoMock = new Proceso(anamnesis1, diagnosticoMock1, procedimientoMock1, 
				pacienteMock1, curasMock1, observaciones1);
		
		assertEquals(procesoMock.getAnamnesis(), anamnesis1);
		assertEquals(procesoMock.getDiagnostico(), diagnosticoMock1);
		assertEquals(procesoMock.getProcedimiento(), procedimientoMock1);
		assertEquals(procesoMock.getPaciente(), pacienteMock1);
		assertEquals(procesoMock.getCuras(), curasMock1);
		assertEquals(procesoMock.getObservaciones(), observaciones1);
		
		procesoMock.setAnamnesis(anamnesis2);
		procesoMock.setDiagnostico(diagnosticoMock2);
		procesoMock.setProcedimiento(procedimientoMock2);
		procesoMock.setPaciente(pacienteMock2);
		procesoMock.setCuras(curasMock2);
		procesoMock.setObservaciones(observaciones2);
		
		assertEquals(procesoMock.getAnamnesis(), anamnesis2);
		assertEquals(procesoMock.getDiagnostico(), diagnosticoMock2);
		assertEquals(procesoMock.getProcedimiento(), procedimientoMock2);
		assertEquals(procesoMock.getPaciente(), pacienteMock2);
		assertEquals(procesoMock.getCuras(), curasMock2);
		assertEquals(procesoMock.getObservaciones(), observaciones2);
	}

}
