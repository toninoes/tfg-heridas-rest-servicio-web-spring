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
import rha.model.Administrador;
import rha.repository.AdministradorRepository;
import rha.service.AdministradorService;

@RunWith(MockitoJUnitRunner.class)
public class AdministradorServiceTest {

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
	private static final Boolean NOENABLED = false;
	
	private Administrador p = new Administrador(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, NOENABLED,
			authoritiesMock, nacimientoMock, DNI);
	
	private Optional<Administrador> Administrador = Optional.of(p);
	
	@Mock
	private AdministradorRepository AdministradorRepositoryMock;
	
	@InjectMocks
	private AdministradorService service = new AdministradorService();
	
	@Before
    public void prepare() {
		List<Administrador> listado = new ArrayList<>();
		listado.add(Administrador.get());
		
		// getTodos
		when(AdministradorRepositoryMock.findAll()).thenReturn(listado);
		
		//getUno
		when(AdministradorRepositoryMock.findById(ID)).thenReturn(Administrador);
				
	}
	
	@Test
    public void testTodos() {
		List<Administrador> listado = service.findAll();
		
		Assert.assertNotNull(listado);
        Assert.assertTrue(listado.size() > 0);
        Assert.assertEquals(listado.get(0).getUsername(), USERNAME);
        Assert.assertEquals(listado.get(0).getPassword(), PASSWORD);
        Assert.assertEquals(listado.get(0).getFirstname(), FIRSTNAME);
        Assert.assertEquals(listado.get(0).getLastname(), LASTNAME);
	}
	
	@Test
    public void testUno() {
		Administrador unAdministrador = service.findById(ID);
 
        Assert.assertNotNull(unAdministrador);
        Assert.assertEquals(unAdministrador.getUsername(), USERNAME);
        Assert.assertEquals(unAdministrador.getPassword(), PASSWORD);
        Assert.assertEquals(unAdministrador.getFirstname(), FIRSTNAME);
        Assert.assertEquals(unAdministrador.getLastname(), LASTNAME);
    }
	
	@Test(expected = RecursoNoEncontradoException.class)
    public void testUnoFalla() {
        Administrador unAdministrador = service.findById(ID_NOEXISTE);
 
        Assert.assertNotNull(unAdministrador);
        Assert.assertEquals(unAdministrador.getUsername(), USERNAME);
        Assert.assertEquals(unAdministrador.getPassword(), PASSWORD);
        Assert.assertEquals(unAdministrador.getFirstname(), FIRSTNAME);
        Assert.assertEquals(unAdministrador.getLastname(), LASTNAME);
    }
	
}
