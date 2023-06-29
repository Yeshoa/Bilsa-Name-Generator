package entities;

import java.util.Random;

public class Nombre {
	private String nombre;
	private LetraGenerator letras= new LetraGenerator();

	public Nombre(int cant) {
		super();
		this.nombre = generarNombre(cant);
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LetraGenerator getLetras() {
		return letras;
	}

	public void setLetras(LetraGenerator letras) {
		this.letras = letras;
	}
	
	public String generarNombre(int cant) {
		StringBuilder nuevoNombre = new StringBuilder();
		for(int i=0;i<cant;i++) {
			nuevoNombre.append(letras.generarLetra(nuevoNombre.toString()));
		}
		String ultimaSilaba = letras.generarUltimaSilaba(nuevoNombre.toString());
//		System.out.print(ultimaSilaba + "-" );
		nuevoNombre.append(ultimaSilaba);
		return nuevoNombre.toString();
	}
	
//	public String generarSilaba(String prev) {
//		String silaba ="";
//		
//	}

}
