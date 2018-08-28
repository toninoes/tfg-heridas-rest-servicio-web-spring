package unitary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Sanitario;
import rha.model.Valoracion;

@RunWith(MockitoJUnitRunner.class)
public class ValoracionTest {
	
	@Mock
	Sanitario sanitarioMock1;
	
	@Mock
	Sanitario sanitarioMock2;
	
	private static final Double NOTA1 = 2.5;
	private static final Double NOTA2 = 5.0;
	
	private static final String observaciones1 = "observaciones1";
	private static final String observaciones2 = "observaciones2";
	
	@InjectMocks
	Valoracion valoracionMock;
	
	@Test
	public void valoracionTesting() {
		
		Valoracion valoracionMock = new Valoracion(sanitarioMock1, NOTA1, observaciones1);
		
		assertEquals(valoracionMock.getSanitario(), sanitarioMock1);
		assertEquals(valoracionMock.getNota(), NOTA1);
		assertEquals(valoracionMock.getObservaciones(), observaciones1);
		
		valoracionMock.setSanitario(sanitarioMock2);
		valoracionMock.setNota(NOTA2);
		valoracionMock.setObservaciones(observaciones2);
		
		assertEquals(valoracionMock.getSanitario(), sanitarioMock2);
		assertEquals(valoracionMock.getNota(), NOTA2);
		assertEquals(valoracionMock.getObservaciones(), observaciones2);
		
	}
}
