package entities;

import java.util.Random;

public class ExponentialRandomNumberGenerator {
	private static Random random = new Random();;
//	private double exponentialRate;

	public static int generateRandomNumber() {
	    double randomNumber = random.nextDouble();
	    double cumulativeProbability = 0.0;
	    
	    // Definir los porcentajes de probabilidad para cada número
	    double[] probabilities = {0.01, 0.15, 0.30, 0.30, 0.15, 0.06, 0.01, 0.01, 0.01, 0.01};
	    
	    for (int number = 1; number <= probabilities.length; number++) {
	        cumulativeProbability += probabilities[number - 1];
	        
	        if (randomNumber <= cumulativeProbability) {
	            return number;
	        }
	    }
	    
	    // Si el número generado es mayor a 9, retornar 10 (más de 9 con un 1%)
	    return 10;
	}
//	public ExponentialRandomNumberGenerator(double exponentialRate) {
//		random = new Random();
//		this.exponentialRate = exponentialRate;
//	}
//
//	public int generateRandomOneTwoTree() {
//		random = new Random();
//		double[] probabilities = new double[] {0.99999, 0.90};
//		double randomNumber = random.nextDouble();
//		int number = 1;
//
//		for (int i = 0; i < probabilities.length; i++) {
//			if (randomNumber <= probabilities[i]) {
//				number += 1;
//			}
//		}
//		return number;
//	}
//
//	public int generateRandomNumber() {//arreglar esto
//		double randomNumber = random.nextDouble();
//		double exponentialValue = -Math.log(randomNumber) / exponentialRate;
//		return (int) Math.min(exponentialValue, 10) + generateRandomOneTwoTree();
//	}
//
//	public static int finalGenerator() {
//		double exponentialRate = 0.91;
//		ExponentialRandomNumberGenerator generator = new ExponentialRandomNumberGenerator(exponentialRate);
//		int randomNum = generator.generateRandomNumber();
//		return randomNum;
//	}
}