package test;

import entities.ExponentialRandomNumberGenerator;
import entities.Nombre;
import entities.NombreGPT;

public class Test {

	public static void main(String[] args) {
//		int cant = 15;
//		for (int i=0;i<cant;i++) {
//			NombreGPT generator = new NombreGPT();
//			generator.generarNombre(ExponentialRandomNumberGenerator.finalGenerator());
			Nombre generator = new Nombre(ExponentialRandomNumberGenerator.generateRandomNumber());
			System.out.print(generator.getNombre() + " ");
//			if (i!= cant-1) {
//				System.out.print("---");
//			}
//	        if ((i+1)% 12 == 0) {
//	            System.out.println();
//	        }
//		}
//		System.out.println();
	}

}
