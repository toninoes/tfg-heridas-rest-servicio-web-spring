package unitary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Cuidado;
import rha.model.Grupodiagnostico;
import rha.model.Sanitario;

@RunWith(MockitoJUnitRunner.class)
public class CuidadoTest {
	
	private static final String NOMBRE1 = "Cuidado1";
	private static final String NOMBRE2 = "Cuidado2";
	
	private static final String DESCRIPCION1 = "Descripción1";
	private static final String DESCRIPCION2 = "Descripción2";
	
	@Mock
	Grupodiagnostico grupodiagnosticoMock1;
	
	@Mock
	Grupodiagnostico grupodiagnosticoMock2;
	
	@Mock
	Sanitario sanitarioMock1;
	
	@Mock
	Sanitario sanitarioMock2;
	
	@InjectMocks
	Cuidado cuidadoMock;
	
	@Test
	public void cuidadoTesting() {
		Cuidado cuidadoMock = new Cuidado(NOMBRE1, DESCRIPCION1, grupodiagnosticoMock1, sanitarioMock1);
		
		assertEquals(cuidadoMock.getNombre(), NOMBRE1);
		assertEquals(cuidadoMock.getDescripcion(), DESCRIPCION1);
		assertEquals(cuidadoMock.getGrupodiagnostico(), grupodiagnosticoMock1);
		assertEquals(cuidadoMock.getSanitario(), sanitarioMock1);
		
		cuidadoMock.setNombre(NOMBRE2);
		cuidadoMock.setDescripcion(DESCRIPCION2);
		cuidadoMock.setGrupodiagnostico(grupodiagnosticoMock2);
		cuidadoMock.setSanitario(sanitarioMock2);
		
		assertEquals(cuidadoMock.getNombre(), NOMBRE2);
		assertEquals(cuidadoMock.getDescripcion(), DESCRIPCION2);
		assertEquals(cuidadoMock.getGrupodiagnostico(), grupodiagnosticoMock2);
		assertEquals(cuidadoMock.getSanitario(), sanitarioMock2);
	}

}
