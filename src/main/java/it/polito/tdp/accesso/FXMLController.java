package it.polito.tdp.accesso;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	int NumeroMassimoTentativi = 3;
	int TentativiEffettutati = 0;

	private boolean controlloPassword (String password) {
		
		boolean flagCaratteriSpeciali = false;
		boolean flagNumero = false;
		boolean flagMaiuscola = false;
		boolean flagLunghezza = false;
		
		if(password.contains("!") || password.contains("?") 
				|| password.contains("@") || password.contains("#") ) {
			flagCaratteriSpeciali = true;	}
			
		for(char c: password.toCharArray()) {
			if(Character.isDigit(c)==true) {
				flagNumero = true;	}
			if(Character.isUpperCase(c)==true) {
				flagMaiuscola = true;
			}   }
		
		if(password.length()>=7) {
			flagLunghezza = true;  }
		
		if(flagCaratteriSpeciali == true && flagNumero == true &&
				flagMaiuscola == true && flagLunghezza== true) {
			return true; }
		else {
			return false;	}
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea areaDiTesto;

    @FXML
    private Button bottoneAccesso;

    @FXML
    private Button bottoneClean;

    @FXML
    private TextField nome;

    @FXML
    private TextField password;

    @FXML
    private Label tentativi;

    @FXML
    void accesso(ActionEvent event) {
    	
    if(this.TentativiEffettutati<this.NumeroMassimoTentativi) {
    	String nome = this.nome.getText();   
    	
    	if(nome!= null) {
    		String password = this.password.getText();
    		
    		if(controlloPassword(password)==true) {
    			this.areaDiTesto.setText("Accesso avvenuto con successo!");
    			this.TentativiEffettutati = 0;
    			this.nome.setDisable(false);  }
    		else {
    			this.areaDiTesto.setText("Formato password non compatibile! \n "
    					+ "Deve contenere almeno 7 caratteri  tra i quali: \n "
    					+ "- un numero \n - una lettera maiuscola \n - un carattere tra !, ?, @, #");
    			this.TentativiEffettutati++;
    			this.nome.setDisable(true);
    			this.tentativi.setText(String.valueOf(NumeroMassimoTentativi-TentativiEffettutati));  }
    	}
    	else {
    		this.areaDiTesto.setText("Non si è inserito il nome utente!");  }
    	}
    else {
    	this.areaDiTesto.setText("Numero massimo di tentatiti raggiunto!"); }
    }

    @FXML
    void clean(ActionEvent event) {
    	this.nome.clear();
    	this.password.clear();
    	this.TentativiEffettutati = 0;
    	this.tentativi.setText(String.valueOf(NumeroMassimoTentativi-TentativiEffettutati)); 
    	this.nome.setDisable(false);   }

    @FXML
    void initialize() {
        assert areaDiTesto != null : "fx:id=\"areaDiTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bottoneAccesso != null : "fx:id=\"bottoneAccesso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert bottoneClean != null : "fx:id=\"bottoneClean\" was not injected: check your FXML file 'Scene.fxml'.";
        assert nome != null : "fx:id=\"nome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tentativi != null : "fx:id=\"tentativi\" was not injected: check your FXML file 'Scene.fxml'.";   }

}