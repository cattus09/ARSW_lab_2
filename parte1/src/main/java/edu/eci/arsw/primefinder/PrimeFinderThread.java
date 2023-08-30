package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{
	int a,b;
	// variable running, indica si el o los hilos estan en ejecucion
	boolean running = true;

	private List<Integer> primes=new LinkedList<Integer>();
	
	public PrimeFinderThread(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}
	// metodo que indica si el hilo esta en ejecucion, es syncronized para evitar conflictos,
	//si la variable es false se pone en espera
	public synchronized void isRunning(){
		while (!running){
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void run(){
		for (int i=a;i<=b;i++){						
			if (isPrime(i)){
				primes.add(i);
				System.out.println(i);
				isRunning();
			}
		}
	}
	
	boolean isPrime(int n) {
	    if (n%2==0) return false;
	    for(int i=3;i*i<=n;i+=2) {
	        if(n%i==0)
	            return false;
	    }
	    return true;
	}

	public List<Integer> getPrimes() {
		return primes;
	}
	
	public void pause(){
		running = false;
	}

	public String information(){
		return getName() + " " + primes.size();
	}
	// metodo que convierte running en true y despierta a todos los hilos.
	public synchronized void again(){
		running = true;
		notifyAll();
	}
	
}
