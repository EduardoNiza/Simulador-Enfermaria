package br.usp.ime.mac321.ep1.ex2;

public class Medico
{
    /******************* Atributos do Médico *********************/
	private long freqMonitoring;
	private long freqAdmin;

    /******************* Conjunto de Medicamentos do Médico *********************/
	private int nDrugs = 0;
	private String[] Drugs = new String[20];

    /******************* Construtores *********************/
	public Medico(long freqMonitoring, long freqAdmin)
	{
		this.freqMonitoring = freqMonitoring;
		this.freqAdmin = freqAdmin;
	}

	public Medico(long freqMonitoring)
	{
		this(freqMonitoring, 1);
	}

	public Medico()
	{
		this(100, 1);
	}

    /******************* Métodos de Configuração *********************/
	public void addDrug(String Drug)
	{
		Drugs[nDrugs++] = Drug;
	}

	/******************* Métodos de Consulta *********************/
	public long getFreqMonitoring()
	{
		return (this.freqMonitoring);
	}

	public long getFreqAdmin()
	{
		return (this.freqAdmin);
	}

	public long getNumDrugs()
	{
		return (this.nDrugs);
	}

	public void seeDrugs()
	{
		for (int i = 0; i < nDrugs; i++)
		{
			System.out.println("Drug[" + i + "]: " + Drugs[i]);
		}
	}

	/******************* Métodos *********************/
	/*
	 * Este método realiza uma análise funcional das funções relacionadas à redução de temperatura 
	 * e proteínas. A análise começou com a plotagem de gráficos para essas 
	 * funções e a investigação de seus parâmetros críticos. Foi observado que a taxa de 
	 * diminuição da temperatura é mais rápida que a das proteínas. Portanto, a administração do 
	 * medicamento é fundamentada, pois tras consigo benefícios, especialmente quando consideramos um período 
	 * o tempo crítico de 30 minutos. Através de uma análise matemática detalhada, incluindo o 
	 * cálculo de derivadas para pontos extremos, foi estabelecido que o valor crítico de 33 para os 
	 * níveis de proteínas é o limiar para a recuperação de proteínas e para a interrupção da 
	 * medicação. Isso se deve ao aumento da temperatura durante uma crise. 
	 * Além disso, a análise das derivadas mostrou que a diferença entre as taxas de mudança da 
	 * temperatura e das proteínas tende a zero, o que sugere que a temperatura tem um 
	 * limite superior claro, prevenindo a hipotermia e focando a atenção nos níveis de 
	 * proteínas como um indicador mais crítico para a administração do tratamento.
	 */

	public boolean willApply(double temperature, double pac)
	{
		return (/* temperature > 37 && */pac > 33);
	}
}