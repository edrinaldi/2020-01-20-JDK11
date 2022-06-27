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
	private List<Integer> percorsoOtt;
	private int nEsposizioni;
	private int nEsposizioniOtt;
	
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
		System.out.printf("# vertici: %s\n", this.grafo.vertexSet());
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
	
	public void calcolaPercorso(int idPartenza) {
		this.percorsoOtt = new ArrayList<>();

		List<Integer> parziale = new ArrayList<>();
		parziale.add(idPartenza);
		this.ricerca(parziale);
	}
	
	private void ricerca(List<Integer> parziale) {
		if(parziale.size() > this.percorsoOtt.size()) {
			
			// aggiorno la soluzione ottima
			this.percorsoOtt = new ArrayList<>(parziale);
			this.nEsposizioniOtt = this.nEsposizioni;
		}
		for(Integer a : Graphs.neighborListOf(this.grafo, parziale.get(parziale.size()-1))) {
			// inizio un ramo
			
			int peso = (int)this.grafo.getEdgeWeight(this.grafo.getEdge(parziale.get(parziale.size()-1), a));
			
			if(parziale.size() == 1) {
				// parziale contiene solo la partenza
				
				this.nEsposizioni = peso;
			}
			if(!parziale.contains(a) && peso == this.nEsposizioni) {
				parziale.add(a);
				this.ricerca(parziale);
				parziale.remove(parziale.get(parziale.size()-1));
			}
		}
	}
	
	public List<Integer> getPercorso() {
		return this.percorsoOtt;
	}
	
	public int getnEsposizioni() {
		return this.nEsposizioniOtt;
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
	
	public boolean isArtistaNelGrafo(int id) {
		for(Integer a : this.grafo.vertexSet()) {
			if(a == id) {
				return true;
			}
		}
		return false;
	}

}
