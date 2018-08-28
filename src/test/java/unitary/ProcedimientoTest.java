package unitary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Procedimiento;
import rha.model.Proceso;

@RunWith(MockitoJUnitRunner.class)
public class ProcedimientoTest {
	
	private static final String CODIGO1 = "Código1";
	private static final String CODIGO2 = "Código2";
	
	private static final String NOMBRE1 = "Procedimiento1";
	private static final String NOMBRE2 = "Procedimiento2";
	
	@Mock
	List<Proceso> procesosMock1;
	
	@Mock
	List<Proceso> procesosMock2;
	
	@InjectMocks
	Procedimiento procedimientoMock;
	
	@Test
	public void procedimientoTesting() {
		
		Procedimiento procedimientoMock = new Procedimiento(CODIGO1, NOMBRE1);
		procedimientoMock.setProcesos(procesosMock1);
		
		assertEquals(procedimientoMock.getCodigo(), CODIGO1);
		assertEquals(procedimientoMock.getNombre(), NOMBRE1);
		assertEquals(procedimientoMock.getProcesos(), procesosMock1);
		
		procedimientoMock.setCodigo(CODIGO2);
		procedimientoMock.setNombre(NOMBRE2);
		procedimientoMock.setProcesos(procesosMock2);
		
		assertEquals(procedimientoMock.getCodigo(), CODIGO2);
		assertEquals(procedimientoMock.getNombre(), NOMBRE2);
		assertEquals(procedimientoMock.getProcesos(), procesosMock2);
		
	}

}
