package unitary;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Cura;
import rha.model.Imagen;
import rha.model.Proceso;
import rha.model.Sanitario;

@RunWith(MockitoJUnitRunner.class)
public class CuraTest {
	
	private static final String EVOLUCION1 = "Evolución1";
	private static final String EVOLUCION2 = "Evolución2";
	
	private static final String TRATAMIENTO1 = "Tratamiento1";
	private static final String TRATAMIENTO2 = "Tratamiento2";
	
	private static final String RECOMENDACIONES1 = "Recomendaciones1";
	private static final String RECOMENDACIONES2 = "Recomendaciones2";
	
	private static final Boolean SIVALORADA = true;
	private static final Boolean NOVALORADA = false;
	
	@Mock
	Proceso procesoMock1;
	
	@Mock
	Proceso procesoMock2;
	
	@Mock
	Sanitario sanitarioMock1;
	
	@Mock
	Sanitario sanitarioMock2;
	
	@Mock
	List<Imagen> imagenesMock1;
	
	@Mock
	List<Imagen> imagenesMock2;
	
	@InjectMocks
	Cura curaMock;
	
	@Test
	public void curaTesting() {
		
		Cura curaMock = new Cura(EVOLUCION1, TRATAMIENTO1, RECOMENDACIONES1, procesoMock1);
		curaMock.setSanitario(sanitarioMock1);
		curaMock.setImagenes(imagenesMock1);
		curaMock.setValorada(NOVALORADA);
		
		assertEquals(curaMock.getEvolucion(), EVOLUCION1);
		assertEquals(curaMock.getTratamiento(), TRATAMIENTO1);
		assertEquals(curaMock.getRecomendaciones(), RECOMENDACIONES1);
		assertEquals(curaMock.getProceso(), procesoMock1);
		assertEquals(curaMock.getSanitario(), sanitarioMock1);
		assertEquals(curaMock.getImagenes(), imagenesMock1);
		assertEquals(curaMock.getValorada(), NOVALORADA);
		
		curaMock.setEvolucion(EVOLUCION2);
		curaMock.setTratamiento(TRATAMIENTO2);
		curaMock.setRecomendaciones(RECOMENDACIONES2);
		curaMock.setProceso(procesoMock2);
		curaMock.setSanitario(sanitarioMock2);
		curaMock.setImagenes(imagenesMock2);
		curaMock.setValorada(SIVALORADA);
		
		assertEquals(curaMock.getEvolucion(), EVOLUCION2);
		assertEquals(curaMock.getTratamiento(), TRATAMIENTO2);
		assertEquals(curaMock.getRecomendaciones(), RECOMENDACIONES2);
		assertEquals(curaMock.getProceso(), procesoMock2);
		assertEquals(curaMock.getSanitario(), sanitarioMock2);
		assertEquals(curaMock.getImagenes(), imagenesMock2);
		assertEquals(curaMock.getValorada(), SIVALORADA);
				
	}
	

}
