package it.polito.tdp.artsmia.model;

public class TestModel {
	public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo("manufa");
		for(Adiacenza a : m.getArtistiConnessi()) {
			System.out.println(a);
		}
		
		m.calcolaPercorso(10300);
		System.out.println("Cammino più lungo: " + m.getPercorso());
		System.out.println("Numero di esposizioni: " + m.getnEsposizioni());
	}
}
