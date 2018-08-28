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
import rha.model.Paciente;
import rha.repository.PacienteRepository;
import rha.service.PacienteService;

@RunWith(MockitoJUnitRunner.class)
public class PacienteServiceTest {

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
	private static final Long HISTORIA = (long) 1;
	private static final Boolean NOENABLED = false;
	
	private Paciente p = new Paciente(USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, NOENABLED,
			authoritiesMock, nacimientoMock, DNI, HISTORIA);
	
	private Optional<Paciente> paciente = Optional.of(p);
	
	@Mock
	private PacienteRepository pacienteRepositoryMock;
	
	@InjectMocks
	private PacienteService service = new PacienteService();
	
	@Before
    public void prepare() {
		List<Paciente> listado = new ArrayList<>();
		listado.add(paciente.get());
		
		// getTodos
		when(pacienteRepositoryMock.findAll()).thenReturn(listado);
		
		//getUno
		when(pacienteRepositoryMock.findById(ID)).thenReturn(paciente);
				
	}
	
	@Test
    public void testTodos() {
		List<Paciente> listado = service.findAll();
		
		Assert.assertNotNull(listado);
        Assert.assertTrue(listado.size() > 0);
        Assert.assertEquals(listado.get(0).getUsername(), USERNAME);
        Assert.assertEquals(listado.get(0).getPassword(), PASSWORD);
        Assert.assertEquals(listado.get(0).getFirstname(), FIRSTNAME);
        Assert.assertEquals(listado.get(0).getLastname(), LASTNAME);
        Assert.assertEquals(listado.get(0).getHistoria(), HISTORIA);
	}
	
	@Test
    public void testUno() {
		Paciente unPaciente = service.findById(ID);
 
        Assert.assertNotNull(unPaciente);
        Assert.assertEquals(unPaciente.getUsername(), USERNAME);
        Assert.assertEquals(unPaciente.getPassword(), PASSWORD);
        Assert.assertEquals(unPaciente.getFirstname(), FIRSTNAME);
        Assert.assertEquals(unPaciente.getLastname(), LASTNAME);
        Assert.assertEquals(unPaciente.getHistoria(), HISTORIA);
    }
	
	@Test(expected = RecursoNoEncontradoException.class)
    public void testUnoFalla() {
        Paciente unPaciente = service.findById(ID_NOEXISTE);
 
        Assert.assertNotNull(unPaciente);
        Assert.assertEquals(unPaciente.getUsername(), USERNAME);
        Assert.assertEquals(unPaciente.getPassword(), PASSWORD);
        Assert.assertEquals(unPaciente.getFirstname(), FIRSTNAME);
        Assert.assertEquals(unPaciente.getLastname(), LASTNAME);
    }
	
}
