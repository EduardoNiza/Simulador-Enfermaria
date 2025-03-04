package br.usp.ime.mac321.ep1.ex2;

import java.util.Random;

public class Paciente
{
	/***************** Configuração de pseudo-aleatoriedade *********************/
	// Utiliza a classe Random para gerar valores aleatórios para temperatura e PAC.
	private Random rd = new Random();
	private static Random rd2 = new Random();
	private double tBaseMin = 36;
	private double tBaseMax = 37;
	private double pBaseMin = 100;
	private double pBaseMax = 150;
	private static double increaseTMin = 5;
	private static double increaseTMax = 20;

	/***************** Variaveis Aleatorias *********************/
	// Define a temperatura inicial e basal do paciente usando valores aleatórios dentro dos limites estabelecidos.

	private double temperature = (tBaseMin + (tBaseMax - tBaseMin) * (rd.nextDouble()));
	private double baseTemp = temperature;
	private double protein = (pBaseMin + (pBaseMax - pBaseMin) * (rd.nextDouble()));
	private double basePAC = protein;


	/******************* Variáveis de tempo *********************/
	private long born = System.currentTimeMillis(); // Registro do momento de criação do paciente.
	private long auxT; // Auxiliar para controle de tempo de crise de temperatura.
	private long auxP = (-1); // Auxiliar para controle de tempo de crise de PAC.
	private long auxAdmin = (-1); // Auxiliar para tempo de administração de medicamento.
	private long auxRecover = (-1); // Auxiliar para tempo de recuperação após medicamento.

	/******************* Atributos do paciente *********************/
	private double speedR; // Velocidade de recuperação de PAC sem medicamento.
	private long crisisFrequency; // Frequência com que crises ocorrem.
	private double increaseTemp; // Aumento de temperatura durante uma crise.

	/******************* Construtor *********************/
	public Paciente(double speedR, long crisisFrequency, double increaseTemp)
	{
		this.speedR = speedR;
		this.crisisFrequency = crisisFrequency;
		this.increaseTemp = increaseTemp;
		// Informa o status inicial do paciente ao ser criado.
		System.out.println("A patient has arrived and is waiting for you, doctor!\nHis status is:");
		System.out.println("\tTemperature         (ºC):  " + currentT(born));
		System.out.println("\tPAC-level (10^3.u.mm^-3): " + currentPAC(born));
	}

	public Paciente(double speedR)
	{
		this(speedR, 1200, (increaseTMin + (increaseTMax - increaseTMin) * (rd2.nextDouble())));
	}

	public Paciente()
	{
		this((38 * Math.pow(10, -4)), 1200, (increaseTMin + (increaseTMax - increaseTMin) * (rd2.nextDouble())));
	}

	/******************* Retorna informações *********************/
	// Métodos para obter variáveis auxiliares e atributos do paciente.
	public long getauxP()
	{
		return (auxP);
	}

	public long getauxAdmin()
	{
		return (auxAdmin);
	}

	public long getauxRecover()
	{
		return (auxRecover);
	}

	public long getFreq()
	{
		return (crisisFrequency);
	}

	public double getSpeedR()
	{
		return (this.speedR);
	}

	public double getIncreaseTemp()
	{
		return (this.increaseTemp);
	}

	// Método para checar os sinais vitais no momento atual; atualiza se necessário.
	public void getVitals(long currentTime)
	{
		if (currentT(currentTime) == 0 && currentPAC(currentTime) == 0)
		{
		}
	}

	/******************* Métodos de Medicamento e Cura *********************/
	// Método para iniciar a administração de um medicamento.
	public void receiveMedicine(long auxAdmin)
	{
		this.auxRecover = (-1);
		if (this.auxAdmin == (-1))
		{
			System.out.println("  Administering medicines!");
			this.auxAdmin = auxAdmin;
		}
	}

	// Método para aplicar os efeitos do medicamento nos sinais vitais do paciente.
	public void medicineEffect(long currentTime, double speedT, double speedC)
	{
		System.out.println("  Vitals are currently influenced by medicines");
		temperature = tempDrug(currentTime, speedT);
		protein = pacDrug(currentTime, speedC);
	}
	// Método para interromper o medicamento.
	public void stopMedicine(long auxRecover)
	{
		this.auxAdmin = (-1);
		if (this.auxRecover == (-1))
		{
			System.out.println("  Medicines effect has passed! Patient is currently healing PAC-levels");
			this.auxRecover = auxRecover;
		}
	}

	public void healingEffect(long currentTime)
	{
		protein = pacNoDrug(currentTime, speedR);
	}

	/******************* Métodos de Crise *********************/
	// Método para iniciar uma crise, aumentando a temperatura.
	public void startCrisis()
	{
		auxT = System.currentTimeMillis();
		temperature += increaseTemp;
	}

	public void setBeginPACCrisis(long time)
	{
		if (auxP == (-1))
		{
			this.auxP = time;
		}
	}

	/******************* Funções Matemáticas *********************/
	// Método para administrar o medicamento.
	public void adminDrug()
	{
		System.out.println("Aceite o remédio");
		auxAdmin = System.currentTimeMillis();
	}
	// As Funções para calcular os efeitos de medicamentos na temperatura e no PAC.
	public double tempDrug(long currentTime, double speedT)
	{
		double exponent;
		exponent = Math.pow(Math.E, -speedT * (currentTime - auxAdmin));
		return (baseTemp + ((temperature - baseTemp) * exponent));
	}

	public double pacDrug(long currentTime, double speedC)
	{
		double exponent;
		exponent = Math.pow(Math.E, -speedC * (currentTime - auxAdmin));
		return (basePAC * exponent);
	}

	public double pacNoDrug(long currentTime, double speedR)
	{
		double exponent;
		exponent = Math.pow(Math.E, -speedR * (currentTime - auxRecover));
		return (basePAC - (basePAC - protein) * exponent);
	}

	/******************* Verificadores de Sinais Vitais *********************/
	// Métodos para obter a temperatura e o PAC atuais do paciente.
	public double currentT(long currentTime)
	{
		return (temperature);
	}

	public double currentPAC(long currentTime)
	{
		if (protein < 50)
		{
			setBeginPACCrisis(currentTime);
		}
		if ((auxP != (-1)) && (protein >= 50))
		{
			this.auxP = (-1);
		}
		return (protein);
	}

	/******************* Morto ou Vio *********************/
	// Método para verificar se o paciente está morto com base em critérios específicos do enunciado.
	public boolean isDead(long currentTime)
	{
		return ((((currentTime - auxT) >= 600) && (temperature > 41))
				|| ((((currentTime - auxP) >= 300) && (auxP != -1)) && (protein < 50)));
	}
	// Método para marcar o paciente como morto, tornando os sinais vitais negativos.
	public void setDead()
	{
		temperature *= -1;
		protein *= -1;
	}
}
