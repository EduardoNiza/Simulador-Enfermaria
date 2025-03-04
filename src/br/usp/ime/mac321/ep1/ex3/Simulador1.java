package br.usp.ime.mac321.ep1.ex3;

import br.usp.ime.mac321.ep1.ex1.Controlador;
import br.usp.ime.mac321.ep1.ex1.Evento;
import br.usp.ime.mac321.ep1.ex2.Paciente;

public class Simulador1 extends Controlador
{
    /* Membros necessários para representar um hospital */
    private Paciente patient = new Paciente();

    /* Variáveis auxiliares */
    private long t0 = System.currentTimeMillis();

    /*
     * Inicia a crise do paciente quando chamado. Depende da frequência de crises,
     * um atributo do paciente (crisisFrequency).
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
            return ("Paciente inicia surto infecioso\n\n");
        }
    }

    /*
     * Obtém a temperatura do paciente e os níveis de proteínas. Também funciona como
     * o evento principal, controlando a repetição dos eventos secundários (outros eventos).
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
            if (!(patient.isDead(getTime())))
            {
                insere(new consultVital(getTime() + 100));
            }
        }

        @Override
        public String descrição()
        {
            System.out.println("\n" + ((getTime() - t0) / 10) + "min");
            if (!(patient.isDead(getTime())))
            {
                return ("\tTemperatura         (ºC): " + (String.format("  %.2f", patient.currentT(getTime()))) + "\n"
                        + "\tNível de PAC (10^3.u.mm^-3): " + (String.format("%.3f", patient.currentPAC(getTime())))
                        + "\n");
            } else
            {
                patient.setDead();
                return ("Médico verifica obito do paciente.\n");            
            }
        }
    }

    /***************** Main *********************/

    public static void main(String[] args)
    {
        System.out.println("0 min");
        Simulador1 sim = new Simulador1();
        long taux = sim.t0;
        System.out.println("\n" + (((taux - sim.t0) + 50) / 10) + "min");
        sim.insere(sim.new beginCrisis(taux + 50));
        sim.insere(sim.new consultVital(taux + 100));
        sim.run();
    }
}
