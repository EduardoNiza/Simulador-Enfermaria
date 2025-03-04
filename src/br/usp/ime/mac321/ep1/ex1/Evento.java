package br.usp.ime.mac321.ep1.ex1;

abstract public class Evento {
    private long evTime;

    public Evento(long eventTime) {
        evTime = eventTime;
    }

    public boolean pronto() {
        return System.currentTimeMillis() >= evTime;
    }
    
	public long getTime()
	{
		return (this.evTime);
	}


    abstract public void ação();
    abstract public String descrição();
}
