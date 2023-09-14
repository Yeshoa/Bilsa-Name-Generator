package test;

import entities.ExponentialRandomNumberGenerator;
import entities.Nombre;
import entities.*;

public class Test {

	public static void main(String[] args) {
//		int cant = 15;
//		for (int i=0;i<cant;i++) {
		boolean encontrado = false;
		int trys = 0;
		while(!encontrado) {
//			NombreGPT generator = new NombreGPT();
//			generator.generarNombre(ExponentialRandomNumberGenerator.finalGenerator());
			trys++;
			Nombre generator = new Nombre(ExponentialRandomNumberGenerator.generateRandomNumber());
			if(generator.getNombre().equals("VJNUS")) encontrado=true;
			System.out.println(generator.getNombre() + " " + trys);
			WordSaver.saveWord(generator.getNombre());
			
		}
		System.out.println("LISTO");
	}

}
