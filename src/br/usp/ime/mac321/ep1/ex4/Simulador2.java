package br.usp.ime.mac321.ep1.ex4;

import br.usp.ime.mac321.ep1.ex1.*;
import br.usp.ime.mac321.ep1.ex2.*;

public class Simulador2 extends Controlador
{
	/* Declaração de membros necessários do Hospital */
	private Paciente patient;
	private Medico med;
	private Droga ckk;

	/* Variáveis auxiliares */
	private long t0 = System.currentTimeMillis();
	private int i = 0;

	/***************** Eventos ********************/

	/* Instancia o paciente, médico e medicamento */
	private class beginHospital extends Evento
	{
		public beginHospital(long eTime)
		{
			super(eTime);
		}

		@Override
		public void ação()
		{
			patient = new Paciente(38 * Math.pow(10, -4));
			med = new Medico(100);
			med.addDrug("CKK");
			System.out.print("Drug being tested -> ");
			med.seeDrugs();
			ckk = new Droga(27 * Math.pow(10, -4), 54 * Math.pow(10, -4));
			System.out.println("\n" + (((t0 - t0) + 50) / 10) + "min");
		}

		@Override
		public String descrição()
		{
			return ("");
		}
	}

	/*
	 * Inicia a crise do paciente quando chamado. Depende da frequência de crise, um atributo do paciente.
	 */
	private class beginCrisis extends Evento
	{
		public beginCrisis(long eTime)
		{
			super(eTime);
		}

		@Override
		public void ação()
		{
			patient.startCrisis();
		}

		@Override
		public String descrição()
		{
			return ("\n  Doctor! Hurry! The patient is going through a crisis!\n");
		}
	}

	/*
	 * Obtém a temperatura e os níveis de proteínas do paciente. Esta ação não imprime 
	 * informações em todas as chamadas para evitar saturação de dados na tela.
	 */
	private class consultVital extends Evento
	{
		public consultVital(long eTime)
		{
			super(eTime);
		}

		@Override
		public void ação()
		{
			patient.getVitals(getTime());
		}

		@Override
		public String descrição()
		{
			if ((i % 2) == 1)
			{
				i++;
				return ("\tTemperature         (ºC): " + (String.format("  %.2f", patient.currentT(getTime()))) + "\n"
						+ "\tPAC-level (10^3.u.mm^-3): " + (String.format("%07.3f", patient.currentPAC(getTime())))
						+ "\n");
			} else
			{
				i++;
				return ("");
			}
		}
	}


	/*
	 * Administra medicamento e inicia a recuperação natural, evitando 
	 * a sobreposição de eventos no mesmo instante da simulação.
	 */
	private class adminMedicine extends Evento
	{

		public adminMedicine(long eTime)
		{
			super(eTime);
		}

		@Override
		public void ação()
		{
			if (med.willApply(patient.currentT(getTime()), patient.currentPAC(getTime())))
			{
				patient.receiveMedicine(getTime());
				patient.medicineEffect(getTime(), ckk.getT(), ckk.getC());
			}
		}

		@Override
		public String descrição()
		{
			return ("");
		}

	}

	private class naturalRecover extends Evento
	{

		public naturalRecover(long eTime)
		{
			super(eTime);
		}

		@Override
		public void ação()
		{
			if (!(med.willApply(patient.currentT(getTime()), patient.currentPAC(getTime()))))
			{
				patient.stopMedicine(getTime());
				patient.healingEffect(getTime());
			}
		}

		@Override
		public String descrição()
		{
			return ("");
		}

	}

	/* Evento Controlador: organiza e comanda todos os outros eventos dependentes */
	private class Treatment extends Evento
	{

		public Treatment(long eTime)
		{
			super(eTime);
		}

		@Override
		public void ação()
		{
			System.out.println("\n" + ((getTime() - t0) / 10) + "min");
			if ((getTime() - t0) <= 3600)
			{
				if (!(patient.isDead(getTime())))
				{
					insere(new consultVital(getTime()));
					if (med.willApply(patient.currentT(getTime()), patient.currentPAC(getTime())))
					{
						insere(new adminMedicine(getTime()));
						insere(new consultVital(getTime()));
						insere(new naturalRecover(getTime()));
					} else
					{
						insere(new naturalRecover(getTime()));
						insere(new consultVital(getTime()));
						insere(new adminMedicine(getTime()));
					}
					if (((getTime() - t0) % patient.getFreq()) == 0)
					{
						insere(new beginCrisis(getTime()));
					}
					insere(new Treatment(getTime() + med.getFreqMonitoring()));
				} else
				{
					patient.setDead();
					System.out.println("  I'm sorry to deliver sad news, unfortunately, the patient didn't make it.");
				}
			} else
			{
				System.out.println("Hooray! The patient has survived 6 hours of simulation!");
			}
		}

		@Override
		public String descrição()
		{
			return ("");
		}

	}

	/***************** Main *********************/

	public static void main(String[] args)
	{
		System.out.println("Started simulation");
		System.out.println("0 min");
		Simulador2 sim = new Simulador2();
		long taux = sim.t0;
		sim.insere(sim.new beginHospital(taux));
		sim.insere(sim.new beginCrisis(taux + 50));
		sim.insere(sim.new Treatment(taux + 100));
		sim.run();
	}
}