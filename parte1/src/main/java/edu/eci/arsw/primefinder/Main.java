package edu.eci.arsw.primefinder;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		String input = "";
        long elapsedTime;

		ArrayList<PrimeFinderThread> threads = new ArrayList<>();

		PrimeFinderThread pft1=new PrimeFinderThread(0, 9999999);
		PrimeFinderThread pft2=new PrimeFinderThread(10000000, 19999999);
		PrimeFinderThread pft3=new PrimeFinderThread(20000000, 30000000);
		
		threads.add(pft1);
		threads.add(pft2);
		threads.add(pft3);

		for(PrimeFinderThread thread:threads){
			thread.start();
		}
		//Controlamos la pausa y la reanudacion de los hilos
		try (Scanner scanner = new Scanner(System.in)) {
			while(Objects.equals(input, "")){
				//Calculamos el tiempo transcurrido, esperamos a que hallan pasado 5 segundos
				do {elapsedTime = System.currentTimeMillis() - startTime;} while (elapsedTime < 5000); 
				for(PrimeFinderThread thread:threads){
					System.out.println(thread.information());
				}
				for(PrimeFinderThread thread:threads){
					thread.pause();
				}
				// Se espera que el ususario oprima enter para reanudar los hilos
				System.out.println("Oprima enter");
				input = scanner.nextLine();
				// Reanudamos todos los hilos
				for(PrimeFinderThread thread:threads){
					thread.again();
				}
				//Actualizamos el valor de startTime
				startTime = System.currentTimeMillis();
			}
		}

	}

	
}
