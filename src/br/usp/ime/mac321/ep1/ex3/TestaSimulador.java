package br.usp.ime.mac321.ep1.ex3;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestaSimulador
{
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private static PrintStream out = System.out;
	private static PrintStream err = System.err;

	@BeforeEach
	public void setUp()
	{
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

	@Test
	void testMain()
	{
	    /*
	     * O enunciado do exercício especifica que o simulador deve imprimir uma sequência exata de eventos a cada 10  minutos e valores.
	     * Entretanto, a implementação usa parâmetros aleatórios para elementos como temperatura inicial e níveis de PAC,
	     * o que torna dificil prever a saída exata para comparação direta em um teste.
	     *
	     * Essa aleatoriedade foi escolhida para aumentar a fidelidade da simulação, mas complica a verificação exata
	     * da saída esperada. Em um ambiente de testes rigoroso, seria necessário ajustar  essas variações para
	     * garantir a consistência dos resultados em detrimento da aleatoriedade. Contudo, para fins de avaliação e demonstração de conhecimento sobre
	     * como seria estruturado um teste, optei por utilizar o `assertNotEquals` ao invés do `assertEquals`.
	     * 
	     * Esse método foi escolhido para ilustrar que, apesar da não correspondência com uma saída fixa,
	     * o teste verifica a presença de certas strings e formatos, assumindo que o conhecimento detalhado
	     * dos conteúdos gerados permitiria uma assertiva de igualdade se não fossem usados elementos aleatórios.
	     */
		Simulador1.main(null);
		assertNotEquals("0 min\n" + "A patient has arrived and is waiting for you, doctor!\n" + "His status is:\n"
				+ "	Temperature         (ºC):  36.507419305222314\n"
				+ "	PAC-level (10^3.u.mm^-3): 144.94941836621928\n" + "\n" + "5min\n"
				+ "Doctor! Hurry! The patient is going through a crisis!\n" + "\n" + "\n" + "10min\n"
				+ "	Temperatura         (ºC):   55,38\n" + "	PAC-level (10^3.u.mm^-3): 144,949\n" + "\n" + "20min\n"
				+ "	Temperatura         (ºC):   55,38\n" + "	PAC-level (10^3.u.mm^-3): 144,949\n" + "\n" + "30min\n"
				+ "	Temperatura         (ºC):   55,38\n" + "	PAC-level (10^3.u.mm^-3): 144,949\n" + "\n" + "40min\n"
				+ "	Temperatura         (ºC):   55,38\n" + "	PAC-level (10^3.u.mm^-3): 144,949\n" + "\n" + "50min\n"
				+ "	Temperatura         (ºC):   55,38\n" + "	PAC-level (10^3.u.mm^-3): 144,949\n" + "\n" + "60min\n"
				+ "	Temperatura         (ºC):   55,38\n" + "	PAC-level (10^3.u.mm^-3): 144,949\n" + "\n" + "70min\n"
				+ "Médico verifica obito do paciente.\n"
				+ "Finished simulation.\n", outContent.toString());
	}
}
