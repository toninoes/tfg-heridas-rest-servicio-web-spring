package unitary;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
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
import rha.jwt.model.security.Authority;
import rha.model.Sanitario;
import rha.repository.SanitarioRepository;
import rha.service.SanitarioService;

@RunWith(MockitoJUnitRunner.class)
public class SanitarioServiceTest {

	private static final Long ID = (long) 1;
	private static final Long ID_NOEXISTE = (long) 2;
	
	private static final String USERNAME = "user";
	private static final String PASSWORD = "pass";
	private static final String FIRSTNAME = "firstname";
	private static final String LASTNAME = "lastname";
	private static final String EMAIL = "email";
	private static final String DNI = "dni";
	
	@Mock
	Date nacimientoMock;
	
	@Mock
	List<Authority> authoritiesMock;
	private static final String COLEGIADO = "NumColegiado";
	private static final Boolean NOENABLED = false;
	
	private Sanitario p = new Sanitario(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, NOENABLED,
			authoritiesMock, nacimientoMock, DNI, COLEGIADO);
	
	private Optional<Sanitario> Sanitario = Optional.of(p);
	
	@Mock
	private SanitarioRepository sanitarioRepositoryMock;
	
	@InjectMocks
	private SanitarioService service = new SanitarioService();
	
	@Before
    public void prepare() {
		List<Sanitario> listado = new ArrayList<>();
		listado.add(Sanitario.get());
		
		// getTodos
		when(sanitarioRepositoryMock.findAll()).thenReturn(listado);
		
		//getUno
		when(sanitarioRepositoryMock.findById(ID)).thenReturn(Sanitario);
				
	}
	
	@Test
    public void testTodos() {
		List<Sanitario> listado = service.findAll();
		
		Assert.assertNotNull(listado);
        Assert.assertTrue(listado.size() > 0);
        Assert.assertEquals(listado.get(0).getUsername(), USERNAME);
        Assert.assertEquals(listado.get(0).getPassword(), PASSWORD);
        Assert.assertEquals(listado.get(0).getFirstname(), FIRSTNAME);
        Assert.assertEquals(listado.get(0).getLastname(), LASTNAME);
	}
	
	@Test
    public void testUno() {
		Sanitario unSanitario = service.findById(ID);
 
        Assert.assertNotNull(unSanitario);
        Assert.assertEquals(unSanitario.getUsername(), USERNAME);
        Assert.assertEquals(unSanitario.getPassword(), PASSWORD);
        Assert.assertEquals(unSanitario.getFirstname(), FIRSTNAME);
        Assert.assertEquals(unSanitario.getLastname(), LASTNAME);
        Assert.assertEquals(unSanitario.getColegiado(), COLEGIADO);
    }
	
	@Test(expected = RecursoNoEncontradoException.class)
    public void testUnoFalla() {
        Sanitario unSanitario = service.findById(ID_NOEXISTE);
 
        Assert.assertNotNull(unSanitario);
        Assert.assertEquals(unSanitario.getUsername(), USERNAME);
        Assert.assertEquals(unSanitario.getPassword(), PASSWORD);
        Assert.assertEquals(unSanitario.getFirstname(), FIRSTNAME);
        Assert.assertEquals(unSanitario.getLastname(), LASTNAME);
        Assert.assertEquals(unSanitario.getColegiado(), COLEGIADO);
    }
	
}
