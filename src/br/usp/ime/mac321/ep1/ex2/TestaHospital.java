package br.usp.ime.mac321.ep1.ex2;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class TestaHospital
{
	private Medico med;
	private Paciente pac;
	private Droga drug;

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private static PrintStream out = System.out;
	private static PrintStream err = System.err;

	@BeforeEach
	public void setUp() 
	{
		pac = new Paciente(1, 1, 5);
		med = new Medico(1, 1);
		drug = new Droga(1, 1);
		System.setOut(new PrintStream(outContent));
		System.setErr(new PrintStream(errContent));
	}

	@AfterEach
	public void cleanUpStreams()
	{
		String output = outContent.toString();
		System.setOut(out);
		System.setErr(err);
		System.out.print(output);
	}

	/************ test for Paciente.java ***************/
	/* testes dos getters da classe do Paciente */
	@Test
	public void testConstructorandGetters0()
	{
		assertEquals(5, pac.getIncreaseTemp());
	}

	@Test
	public void testConstructorandGetters1()
	{
		assertEquals(1, pac.getFreq());
	}

	@Test
	public void testConstructorandGetters2()
	{
		assertEquals(1, pac.getSpeedR());
	}

	@Test
	public void testGetter0()
	{
		assertEquals(-1, pac.getauxP());
	}

	@Test
	public void testGetter1()
	{
		assertEquals(-1, pac.getauxAdmin());
	}

	@Test
	public void testGetter2()
	{
		assertEquals(-1, pac.getauxRecover());
	}

	@Test
	public void testTemperature()
	{
		assertTrue((pac.currentT(0) > 36) && (pac.currentT(0) < 37));
	}

	@Test
	public void testCrisis()
	{
		pac.startCrisis();
		for (int i = 0; i < 6; i++)
		{
			assertTrue(pac.currentT(System.currentTimeMillis()) > 41);
		}
	}

	@Test
	public void testAlive()
	{
		assertFalse(pac.isDead(0));
	}

	@Test
	public void testSetDead()
	{
		pac.setDead();
		assertTrue((pac.currentT(0) < 0) && (pac.currentPAC(0) < 0));
	}

	/************ testes de Medico.java ***************/
	@Test
	public void testMedicConstructorandGetters0()
	{
		assertEquals(1, med.getFreqMonitoring());
	}

	@Test
	public void testMedicConstructorandGetters1()
	{
		assertEquals(1, med.getFreqAdmin());
	}

	/* teste dos métodos de medicamento */
	@Test
	public void testAddDrug()
	{
	    assertEquals(0, med.getNumDrugs());
	    med.addDrug("Fischer");
	    assertEquals(1, med.getNumDrugs());
	    med.seeDrugs();
	    String expectedOutput = "Drug[0]: Fischer" + System.lineSeparator();
	    assertEquals(expectedOutput, outContent.toString());
	}


	/* verifica se o médico irá aplicar o medicamento */
	@Test
	public void testApply()
	{
		assertTrue(med.willApply(0, 100));
		assertFalse(med.willApply(100, 0));
	}

	/************ test for Droga.java ***************/
	/* testa os  getters do medicamento */
	@Test
	public void testDrugConstructorandGetters0()
	{
		assertEquals(1, drug.getT());
	}

	@Test
	public void testDrugConstructorandGetters1()
	{
		assertEquals(1, drug.getC());
	}
}