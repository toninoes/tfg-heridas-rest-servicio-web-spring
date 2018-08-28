package unitary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Cura;
import rha.model.Imagen;

@RunWith(MockitoJUnitRunner.class)
public class ImagenTest {

	private static final String NOMBRE1 = "Imagen1";
	private static final String NOMBRE2 = "Imagen2";
	
	private static final String DESCRIPCION1 = "descripcion1";
	private static final String DESCRIPCION2 = "descripcion2";
	
	@Mock
	Cura curaMock1;
	
	@Mock
	Cura curaMock2;
	
	@InjectMocks
	Imagen imagenMock;
	
	@Test
	public void imagenTesting() {
		Imagen imagenMock = new Imagen(NOMBRE1, curaMock1, DESCRIPCION1);
		
		assertEquals(imagenMock.getNombre(), NOMBRE1);
		assertEquals(imagenMock.getCura(), curaMock1);
		assertEquals(imagenMock.getDescripcion(), DESCRIPCION1);
		
		imagenMock.setNombre(NOMBRE2);
		imagenMock.setCura(curaMock2);
		imagenMock.setDescripcion(DESCRIPCION2);
		
		assertEquals(imagenMock.getNombre(), NOMBRE2);
		assertEquals(imagenMock.getCura(), curaMock2);
		assertEquals(imagenMock.getDescripcion(), DESCRIPCION2);
		
	}
	
}
