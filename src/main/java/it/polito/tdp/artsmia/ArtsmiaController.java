package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Adiacenza;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ArtsmiaController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	// pulisco l'area di testo
    	txtResult.clear();
    	
    	// controllo il grafo
    	if(!this.model.isGrafoCreato()) {
    		this.txtResult.setText("Errore: devi prima creare il grafo.");
    		return;
    	}
    	
    	// trovo gli artisti connessi
    	List<Adiacenza> connessi = this.model.getArtistiConnessi();
    	
    	// stampo il risultato
    	txtResult.setText("Artisti connessi:\n");
    	for(Adiacenza a : connessi) {
    		this.txtResult.appendText(a.toString() + "\n");
    	}
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	txtResult.clear();
    	txtResult.appendText("Calcola percorso");
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	// pulisco l'area di testo
    	txtResult.clear();
    	
    	// controllo il ruolo
    	String ruolo = this.boxRuolo.getValue();
    	if(ruolo == null) {
    		this.txtResult.setText("Errore: devi prima selezionare un ruolo.");
    		return;
    	}
    	
    	// creo il grafo
    	this.model.creaGrafo(ruolo);
    	
    	// stampo il risultato
    	txtResult.setText(String.format("Creato grafo con %d vertici e %d archi.", this.model.nVertici(), 
    			this.model.nArchi()));
    }

    public void setModel(Model model) {
    	this.model = model;
    	
    	// riempio la tendina con i ruoli
    	this.boxRuolo.getItems().clear();
    	this.boxRuolo.getItems().addAll(this.model.getRuoli());
    }

    
    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }
}
