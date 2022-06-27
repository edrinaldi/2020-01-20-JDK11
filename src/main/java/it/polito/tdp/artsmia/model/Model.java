package it.polito.tdp.artsmia.model;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Model {
	Graph<String, DefaultWeightedEdge> grafo;
	
	public void creaGrafo() {
		// inizializzo il grafo
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		
	}
}
