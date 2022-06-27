package it.polito.tdp.artsmia.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.artsmia.db.ArtsmiaDAO;

public class Model {
	private Graph<Integer, DefaultWeightedEdge> grafo;
	private ArtsmiaDAO dao;
	
	public Model() {
		// inizializzo il dao
		this.dao = new ArtsmiaDAO();
	}
	
	public void creaGrafo(String ruolo) {
		// inizializzo il grafo
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		
		// aggiungo i vertici
		Graphs.addAllVertices(this.grafo, this.dao.getVertici(ruolo));
		
		// aggiungo gli archi
		for(Adiacenza a : this.dao.getAdiacenze(ruolo)) {
			Graphs.addEdge(this.grafo, a.getA1(), a.getA2(), a.getPeso());
		}
		
		// console
		System.out.printf("# vertici: %d\n", this.grafo.vertexSet().size());
		System.out.printf("# archi: %d\n", this.grafo.edgeSet().size());
	}
	
	public List<Adiacenza> getArtistiConnessi() {
		List<Adiacenza> artistiConnessi = new ArrayList<>();
		for(DefaultWeightedEdge e : this.grafo.edgeSet()) {
			artistiConnessi.add(new Adiacenza(this.grafo.getEdgeSource(e), this.grafo.getEdgeTarget(e),
					(int)this.grafo.getEdgeWeight(e)));
		}
		Collections.sort(artistiConnessi);
		return artistiConnessi;
	}
	
	public List<String> getRuoli() {
		return this.dao.getRuoli();
	}
	
	public int nVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public boolean isGrafoCreato() {
		return this.grafo!=null;
}

}
