package it.polito.tdp.artsmia.model;

public class TestModel {
	public static void main(String[] args) {
		Model m = new Model();
		m.creaGrafo("painter");
		for(Adiacenza a : m.getArtistiConnessi()) {
			System.out.println(a);
		}
	}
}
