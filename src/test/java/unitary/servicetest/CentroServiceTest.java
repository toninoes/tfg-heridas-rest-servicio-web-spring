package unitary.servicetest;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.exception.RecursoNoEncontradoException;
import rha.model.Centro;
import rha.repository.CentroRepository;
import rha.service.CentroService;

@RunWith(MockitoJUnitRunner.class)
public class CentroServiceTest {
	
	private static final Long ID = (long) 1;
	private static final Long ID_NOEXISTE = (long) 2;
	
	private static final String NOMBRE1 = "Centro1";
	private static final String NOMBRE2 = "Centro2";
	
	private static final String DIRECCION1 = "Dirección1";
	private static final String DIRECCION2 = "Dirección2";
	
	private static final String TELEFONO1 = "Teléfono1";
	private static final String TELEFONO2 = "Teléfono2";
	
	private Centro c1 = new Centro(NOMBRE1, DIRECCION1, TELEFONO1);
	private Centro c2 = new Centro(NOMBRE2, DIRECCION2, TELEFONO2);
	private Optional<Centro> centro = Optional.of(c1);
	
	@Mock
	private CentroRepository centroRepositoryMock;
	
	@InjectMocks
	private CentroService service = new CentroService();
	
	@Before
    public void prepare() {
		List<Centro> listado = new ArrayList<>();
		listado.add(centro.get());
		
		// getTodos
		when(centroRepositoryMock.findAll()).thenReturn(listado);
		
		//getUno
		when(centroRepositoryMock.findById(ID)).thenReturn(centro);
				
	}
	
	@Test
    public void testTodos() {
		List<Centro> listado = service.findAll();
		
		Assert.assertNotNull(listado);
        Assert.assertTrue(listado.size() > 0);
        Assert.assertEquals(listado.get(0).getNombre(), NOMBRE1);
        Assert.assertEquals(listado.get(0).getDireccion(), DIRECCION1);
        Assert.assertEquals(listado.get(0).getTelefono(), TELEFONO1);
	}
	
	@Test
    public void testUno() {
        Centro unCentro = service.findById(ID);
 
        Assert.assertNotNull(unCentro);
        Assert.assertEquals(unCentro.getNombre(), NOMBRE1);
        Assert.assertEquals(unCentro.getDireccion(), DIRECCION1);
        Assert.assertEquals(unCentro.getTelefono(), TELEFONO1);
    }
	
	@Test(expected = RecursoNoEncontradoException.class)
    public void testUnoFalla() {
        Centro unCentro = service.findById(ID_NOEXISTE);
 
        Assert.assertNotNull(unCentro);
        Assert.assertEquals(unCentro.getNombre(), NOMBRE1);
    }
	
	@Test
    public void testCrear() {
        service.create(c1);
    }
	
	@Test
    public void testUpdate() {
		service.update(ID, c2);
    }
	
	@Test(expected = RecursoNoEncontradoException.class)
    public void testUpdateFalla() {
        service.update(ID_NOEXISTE, c2);
    }
	
	
	@Test
    public void testDeleteCorrecto() {
        service.delete(ID);
    }
	
	@Test(expected = RecursoNoEncontradoException.class)
    public void testDeleteFalla() {
        service.delete(ID_NOEXISTE);
    }

}
