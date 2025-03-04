package br.usp.ime.mac321.ep1.ex2;

public class Droga
{
	/******************* Atributos de medicamentos *********************/
	private double speedT;
	private double speedC;

	/******************* Construtor *********************/
	public Droga(double speedT, double speedC)
	{
		this.speedT = speedT;
		this.speedC = speedC;
	}

	public Droga()
	{
		this(27 * Math.pow(10, -4), 54 * Math.pow(10, -4));
	}

	/******************* Retorna informações sobre medicamento *********************/
	public double getT()
	{
		return (speedT);
	}

	public double getC()
	{
		return (speedC);
	}
}