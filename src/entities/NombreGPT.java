package entities;

import java.util.Random;

public class NombreGPT {
    private String nombre;
    private Random random;
    private static final String VOWELS = "AEIOU";
    private static final String CONSONANTS = "BCDFGHJKLMNPRSTVWXZ";

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public NombreGPT() {
        random = new Random();
    }

    public String generarNombre(int silabas) {
        StringBuilder fullname = new StringBuilder();
        System.out.print(fullname.append(generarSilabaInicial()).toString());
        System.out.print("|");
        for (int i = 1; i < silabas; i++) {
        	System.out.print("-");
        	String sil = generarSilaba(fullname.toString());
            System.out.print(sil);
            fullname.append(sil);
        }
        System.out.println();
        nombre = fullname.toString();
        return nombre;
    }

    private String generarSilabaInicial() {
        StringBuilder silaba = new StringBuilder();
        char letra;
        if (random.nextInt(4) == 0) { // A 1/4
            letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
        } else {
            letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
        }
        silaba.append(letra);
        if (esVocal(letra)) { // IF A
            if (random.nextInt(3) == 0) { // 2/3 A~
                if (random.nextInt(16) == 0) { // 1/16 AA
                    letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                } else { // 15/16 AC
                    letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                }
                silaba.append(letra); // AA / AC
                if (esVocal(letra)) { // IF AA
                    if (random.nextInt(160) != 0) { // 1/160 AA~
                        if (random.nextInt(1230) == 0) { // 1/1230 AAA
                            letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                        } else { // 1229/1230 AAC
                            letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                        }
                        silaba.append(letra); // AAA / AAC
                    }
                }
            }
        } else { // IF C
        	System.out.println("1er Letra:"+silaba.toString());
        	if (random.nextInt(3) == 0) { // 1/3 CA
                letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                silaba.append(letra); // CA
                if (random.nextInt(100) < 65) { // 65% CA~
                    if (random.nextInt(2) == 0) { // 1/2 CAA
                        letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                    } else { // 1/2 CAC
                        letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                    }
                    silaba.append(letra); // CAA / CAC
                }
            } else { // CC
                letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                silaba.append(letra); // CC
                letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                silaba.append(letra); // CCA
                if (random.nextInt(100) < 65) { // 65% CCA~
                    if (random.nextInt(2) == 0) { // 1/2 CCAA
                        letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                    } else { // 1/2 CCAC
                        letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                    }
                    silaba.append(letra); // CCAA / CCAC
                }
            }
        	System.out.println("2da Letra:"+silaba.toString().charAt(1));
        }
        return silaba.toString();
    }

    private String generarSilaba(String prev) {
        StringBuilder silaba = new StringBuilder();
        char letra;
        if (esVocal(prev.charAt(prev.length() - 1))) {
            if (random.nextInt() % 11 != 0) { // 10/11 ~A-C..
                letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                silaba.append(letra);
                if (random.nextInt(20) != 0) { // 19/20 C~
                    silaba.append(silabaPostConsonante());
                }
            }
        } else { // ~C
            silaba.append(silabaPostConsonante());
        }
        return silaba.toString();
    }

    private String silabaPostConsonante() {
        StringBuilder silaba = new StringBuilder();
        char letra;
        if (random.nextInt(3) != 0) { // 2/3 C-A
            letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
            silaba.append(letra); // C-A
            if (random.nextInt(100) < 65) { // 65% C-A~
                if (random.nextInt(3) == 0) { // 1/3 C-AA
                    letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                } else { // C-AC 2/3
                    letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                }
                silaba.append(letra); // C-AA / C-AC
            }
        } else { // 1/3 C-C
            letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
            silaba.append(letra); // C-C
            if (random.nextInt(3) == 0) { // 1/3 CA
                letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                silaba.append(letra); // CA
                if (random.nextInt(100) < 65) { // 65% CA~
                    if (random.nextInt(2) == 0) { // 1/2 CAA
                        letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
                    } else { // 1/2 CAC
                        letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                    }
                    silaba.append(letra); // CAA / CAC
                }
            } else { // CC
                letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
                silaba.append(letra); // CC
                if (silaba.toString().charAt(silaba.length()-1) == 'J' || silaba.toString().charAt(silaba.length()-1) == 'H') {
	                letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
	                silaba.append(letra); // CCA
	                if (random.nextInt(100) < 65) { // 65% CCA~
	                    if (random.nextInt(2) == 0) { // 1/2 CCAA
	                        letra = VOWELS.charAt(random.nextInt(VOWELS.length()));
	                    } else { // 1/2 CCAC
	                        letra = CONSONANTS.charAt(random.nextInt(CONSONANTS.length()));
	                    }
	                    silaba.append(letra); // CCAA / CCAC
	                }
                }
            }
        }
        return silaba.toString();
    }

    public boolean esVocal(char letra) {
        return VOWELS.indexOf(Character.toUpperCase(letra)) != -1;
    }
    public boolean esConsonanteSuave(char letra) {
        boolean si = false;
        switch (letra) {
        case 'H': si=true;break;
        case 'J': si=true;break;
        case 'V': si=true;break;
        case 'W': si=true;break;
		default:throw new IllegalArgumentException("Unexpected value: " + letra);
		}
    	return si;
    }
    
}
