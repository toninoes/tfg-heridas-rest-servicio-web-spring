package unitary.modeltest;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import rha.model.Cita;
import rha.model.Paciente;
import rha.model.Sala;

@RunWith(MockitoJUnitRunner.class)
public class CitaTest {

	private static final Long ORDEN1 = (long) 1;
	private static final Long ORDEN2 = (long) 5;
	
	@Mock
	Paciente pacienteMock1;
	
	@Mock
	Paciente pacienteMock2;
	
	@Mock
	Sala salaMock1;
	
	@Mock
	Sala salaMock2;
	
	@Mock
	Date fechaMock1;
	
	@Mock
	Date fechaMock2;
	
	@InjectMocks
	Cita citaMock;
	
	@Test
	public void citaTesting() {
		Cita citaMock = new Cita(pacienteMock1, salaMock1, fechaMock1, ORDEN1);
		
		assertEquals(citaMock.getPaciente(), pacienteMock1);
		assertEquals(citaMock.getSala(), salaMock1);
		assertEquals(citaMock.getFecha(), fechaMock1);
		assertEquals(citaMock.getOrden(), ORDEN1);
		
		citaMock.setPaciente(pacienteMock2);
		citaMock.setSala(salaMock2);
		citaMock.setFecha(fechaMock2);
		citaMock.setOrden(ORDEN2);
		
		assertEquals(citaMock.getPaciente(), pacienteMock2);
		assertEquals(citaMock.getSala(), salaMock2);
		assertEquals(citaMock.getFecha(), fechaMock2);
		assertEquals(citaMock.getOrden(), ORDEN2);
		
		
	}
}
