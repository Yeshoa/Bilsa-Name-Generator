package entities;

import java.util.Random;

public class LetraGenerator {
	private static final String CONSONANTS = "BCDFGHJKLMNPRSTVWXZ";
	private static final String VOWELS = "AEIOU";
	private static final String SOFT_CONSONANTS = "HJVW";
//	private static final String EXCEPTIONAL_VOWEL = "A";

	private Random random;

	public LetraGenerator() {
		random = new Random();
	}

	public char generarLetra(String prev) {
		int length = prev.length();// saco la cantidad de letras que tiene la palabra hasta ahora
		int prob = random.nextInt(100);
		// si la palabra tiene mas de 4 consonantes genera una vocal CCCC'A'
		if (length >= 4 && todasConsonantes(prev.substring(length - 4, length))) {
			return generarVocal(prev);
			// si tiene mas de 3 consonantes no suaves en fila genera una vocal o una
			// consonante suave CCC'A'
		} else if (length >= 3 && todasConsonantesNoSuaves(prev.substring(length - 3, length))) {
			return generarVocalOConsonanteSuave(prev);
			// si tiene 2 vocales seguidas, genera una consonante o una A
		} else if (length >= 2 && todasVocales(prev.substring(length - 2, length))) {
			// si tiene 3 vocales seguidas, si o si genera una consonante
			if (length >= 3 && todasVocales(prev.substring(length - 3, length))) {
				return generarConsonante(prev);
			} else {
				return generarConsonanteOExcepcionVocal(prev);
			}
		} else if (length == 1) {
			if (todasVocales(prev)) {
				return generarConsonante(prev);
			} else {
				return (random.nextBoolean()) ? generarVocalOConsonanteSuave(prev) : generarConsonante(prev);
			}
			// SI en la primer silaba HAY 2 CONSONANTES SEGUIDAS TIENE QUE GHAVER VOCAL
		} else if (length == 2 && todasConsonantesNoSuaves(prev.substring(length - 2, length))) {
			return generarVocalOConsonanteSuave(prev);
			// SI NO CUMPLE NINGUNA DE LAS ANTERIORES, Y LA ULTIMA ES VOCAL, ESTAS SON LAS
			// CHANCES
		} else if (length != 0) {
			if (esVocal(prev.charAt(length - 1))) {
				if (prob < 80) {// CONSONANTE NORMAL 80%
					return generarConsonanteNoSuave(prev);
				} else if (prob < 90) {// VOCAL 10%
					return generarVocal(prev);
				} else {// CONSONANTESUAVE 10%
					return generarConsonanteSuave(prev);
				}
			} else if (esConsonanteNoSuave(prev.charAt(length - 1))) {// SI LA ULTIMA ES CONSONANTE NO S
				if (prob < 66) {// VOCAL 66%
					return generarVocal(prev);
				} else if (prob < 88) {// CONSONANTE NORMAL 22%
					return generarConsonanteNoSuave(prev);
				} else {// CONSONANTESUAVE 12%
					return generarConsonanteSuave(prev);
				}
			} else {// SI ES CONSONANTE SUAVE
				if (prob < 65) {// VOCAL 65%
					return generarVocal(prev);
				} else if (prob < 88) {// CONSONANTE NORMAL 23%
					return generarConsonanteNoSuave(prev);
				} else {// CONSONANTESUAVE 12%
					return generarConsonanteSuave(prev);
				}
			}
		}
		return generarLetraAleatoria(prev);
	}

	public String generarUltimaSilaba(String prev) {
		StringBuilder silaba = new StringBuilder();
		int length = prev.length();
		char ultimaLetra = prev.charAt(length - 1);
		// si la ultima letra es vocal o consonante suave
		int prob = random.nextInt(100);
		if (todasConsonantes(prev)) { // para evitar que hayan todas consonantes
			silaba.append(generarVocal(prev));
		}
		if (!esConsonanteNoSuave(ultimaLetra)) {
			if (length >= 4 && todasConsonantes(prev.substring(length - 4, length))) {
				silaba.append(generarVocal(prev));
				if ((prob < 23)) {// 10% AA
					silaba.append(generarVocal(prev));
				} else if ((prob < 34)) {// 5% AJ
					silaba.append(generarConsonanteSuave(prev));
				} else {// 28% AC
					silaba.append(generarConsonanteNoSuave(prev));
				}
			} else if ((prob < 10)) {// 10% AA
				silaba.append(generarVocal(prev));
				silaba.append(generarVocal(prev));
			} else if ((prob < 20)) {// 10% CC
				silaba.append(generarConsonanteNoSuave(prev));
				silaba.append(generarConsonanteNoSuave(prev));
			} else if ((prob < 25)) {// 5% LL
				silaba.append("LL");
			} else if ((prob < 30)) {// 5% JA
				silaba.append(generarConsonanteSuave(prev));
				silaba.append(generarVocal(prev));
			} else if ((prob < 35)) {// 5% JC
				silaba.append(generarConsonanteSuave(prev));
				silaba.append(generarConsonanteNoSuave(prev));
			} else if ((prob < 40)) {// 5% CJ
				silaba.append(generarConsonanteNoSuave(prev));
				silaba.append(generarConsonanteSuave(prev));
			} else if ((prob < 45)) {// 5% AJ
				silaba.append(generarVocal(prev));
				silaba.append(generarConsonanteSuave(prev));
			} else if ((prob < 72)) {// 28% AC
				silaba.append(generarVocal(prev));
				silaba.append(generarConsonanteNoSuave(prev));
			} else if ((prob < 98)) {// 26% CA Literal
				silaba.append(generarConsonanteNoSuave(prev));
				silaba.append('A');
			} else {// 2% CA
				silaba.append(generarConsonanteNoSuave(prev));
				silaba.append(generarVocal(prev));
			}
		} else {
			if ((prob < 50)) {// 50% AC
				if ((prob < 12)) {// 12% AS
					silaba.append(generarLetraRango("EI"));
					silaba.append('S');
				} else if ((prob < 22)) {// 10% AM
					silaba.append(generarLetraRango("EI"));
					silaba.append('M');
				} else if ((prob < 28)) {// 6% AT
					silaba.append("AT");
				} else if ((prob < 34)) {// 6% AN
					silaba.append(generarLetraRango("AE"));
					silaba.append('N');
				} else if ((prob < 40)) {// 4% AR
					silaba.append(generarLetraRango("AE"));
					silaba.append('R');
				} else {// 10% AC
					silaba.append(generarVocal(prev));
					silaba.append(generarConsonanteNoSuave(prev));
				}
			} else if ((prob < 74)) {// 24% CA
				if ((prob > 62)) {// 12% CA
					silaba.append(generarConsonanteNoSuave(prev));
					silaba.append('A');
				} else if ((prob > 56)) {// 6% CE
					silaba.append(generarConsonanteNoSuave(prev));
					silaba.append('E');
				} else if ((prob > 52)) {// 4% CI
					silaba.append(generarConsonanteNoSuave(prev));
					silaba.append("I");
				} else {// 2% CO
					silaba.append(generarConsonanteNoSuave(prev));
					silaba.append('O');
				}
			} else if ((prob < 94)) {// 20% AJ
				if ((prob > 79)) {// 15% AJ LITERAL
					silaba.append(generarLetraRango("A"));
					silaba.append('J');
				} else if ((prob > 76)) {// 2% EJ LITERAL
					silaba.append(generarLetraRango("E"));
					silaba.append('J');
				} else {// 2% AJ
					silaba.append(generarVocal(prev));
					silaba.append(generarConsonanteSuave(prev));
				}
			} else {// 6% JA
				silaba.append(generarConsonanteSuave(prev));
				silaba.append(generarVocal(prev));
			}
		}
		return silaba.toString();
	}

	private char generarLetraRango(String letras) {
		return letras.charAt(random.nextInt(letras.length()));
	}

	private char generarLetraAleatoria(String prev) {
		return (random.nextBoolean()) ? generarConsonante(prev) : generarVocal(prev);
	}

	private char generarConsonante(String prev) {
		char letra;
		char lastCharacter = (prev.length() > 0) ? prev.charAt(prev.length() - 1) : ' ';
		do {
			int prob = random.nextInt(478);
			letra = 'V';// 9
			if (prob < 29) {
				letra = 'J';
			} else if (prob < 48) {
				letra = 'H';
			} else if (prob < 63) {
				letra = 'W';
			} else if (prob < 120) {
				letra = 'T';
			} else if (prob < 171) {
				letra = 'L';
			} else if (prob < 219) {
				letra = 'N';
			} else if (prob < 265) {
				letra = 'R';
			} else if (prob < 300) {
				letra = 'S';
			} else if (prob < 329) {
				letra = 'B';
			} else if (prob < 356) {
				letra = 'M';
			} else if (prob < 383) {
				letra = 'K';
			} else if (prob < 405) {
				letra = 'Z';
			} else if (prob < 423) {
				letra = 'D';
			} else if (prob < 441) {
				letra = 'G';
			} else if (prob < 451) {
				letra = 'C';
			} else if (prob < 457) {
				letra = 'F';
			} else if (prob < 463) {
				letra = 'P';
			} else if (prob < 469) {
				letra = 'X';
			}
		} while (letra == lastCharacter);
		return letra;
//		return CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
	}

	private char generarVocal(String prev) {
		char lastCharacter = (prev.length() > 0) ? prev.charAt(prev.length() - 1) : ' ';
		char letra;
		do {
			int prob = random.nextInt(190);
			letra = 'O';
			if (prob < 50) {
				letra = 'A';
			} else if (prob < 100) {
				letra = 'E';
			} else if (prob < 130) {
				letra = 'U';
			} else if (prob < 160) {
				letra = 'I';
			}
		} while (letra == lastCharacter);
		return letra;
//		return VOWELS.charAt(random.nextInt(VOWELS.length())); esto estaba antes para que sea 100% random
	}

	private char generarVocalOConsonanteSuave(String prev) {
		return (random.nextBoolean()) ? generarVocal(prev) : generarConsonanteSuave(prev);
	}

	private char generarConsonanteSuave(String prev) {
		char lastCharacter = (prev.length() > 0) ? prev.charAt(prev.length() - 1) : ' ';
		char letra;
		do {
			int prob = random.nextInt(72);
			letra = 'V';
			if (prob < 29) {
				letra = 'J';
			} else if (prob < 48) {
				letra = 'H';
			} else if (prob < 63) {
				letra = 'W';
			}
		} while (letra == lastCharacter);
		return letra;
//		return SOFT_CONSONANTS.charAt(random.nextInt(SOFT_CONSONANTS.length()));
	}

	private char generarConsonanteNoSuave(String prev) {
		char lastCharacter = (prev.length() > 0) ? prev.charAt(prev.length() - 1) : ' ';
		char letra;
		do {
			int prob = random.nextInt(406);
			letra = 'X';// 6
			if (prob < 57) {
				letra = 'T';
			} else if (prob < 108) {
				letra = 'L';
			} else if (prob < 156) {
				letra = 'N';
			} else if (prob < 202) {
				letra = 'R';
			} else if (prob < 237) {
				letra = 'S';
			} else if (prob < 266) {
				letra = 'B';
			} else if (prob < 293) {
				letra = 'M';
			} else if (prob < 320) {
				letra = 'K';
			} else if (prob < 342) {
				letra = 'Z';
			} else if (prob < 360) {
				letra = 'D';
			} else if (prob < 378) {
				letra = 'G';
			} else if (prob < 388) {
				letra = 'C';
			} else if (prob < 394) {
				letra = 'F';
			} else if (prob < 400) {
				letra = 'P';
			}
		} while (letra == lastCharacter);
		return letra;
//		String consonantesNoSuaves = CONSONANTS.replaceAll("[" + SOFT_CONSONANTS + "]", "");
//		return consonantesNoSuaves.charAt(random.nextInt(consonantesNoSuaves.length()));
	}

	private char generarConsonanteOExcepcionVocal(String prev) {
		int prob = random.nextInt(505);
		char letra = generarConsonante(prev);// 6
		if (prob > 406) {
			letra = 'A';
		}
		return letra;
//		return (random.nextBoolean()) ? generarConsonante() : EXCEPTIONAL_VOWEL.charAt(0);
	}

	private boolean todasConsonantes(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!esConsonante(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean todasConsonantesNoSuaves(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!esConsonanteNoSuave(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean todasVocales(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!esVocal(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private boolean esConsonante(char letra) {
		return CONSONANTS.indexOf(Character.toUpperCase(letra)) != -1;
	}

	private boolean esConsonanteSuave(char letra) {
		return SOFT_CONSONANTS.indexOf(Character.toUpperCase(letra)) != -1;
	}

	private boolean esConsonanteNoSuave(char letra) {
		return esConsonante(letra) && SOFT_CONSONANTS.indexOf(Character.toUpperCase(letra)) == -1;
	}

	private boolean esVocal(char letra) {
		return VOWELS.indexOf(Character.toUpperCase(letra)) != -1;
	}
}
