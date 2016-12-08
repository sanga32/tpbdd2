package candidats;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import testConnexion.Oracle;

public class Candidat {
	private int idCand, rang;
	private String nom, prenom, datenais, adresse;
	private Map<Epreuve, Double> listeNotes;
	private double moyenne;
	private List<Voeu> listeVoeux;
	private Preadmin pa;
	private String option;
	private Admin a;
	
	public Preadmin getPa() {
		return pa;
	}

	public void setPa(Preadmin pa) {
		this.pa = pa;
	}

	public int getRang() {
		return rang;
	}

	public void setRang(int rang) {
		this.rang = rang;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getDatenais() {
		return datenais;
	}

	public void setDatenais(String datenais) {
		this.datenais = datenais;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public Candidat(int idCand){
		this.idCand = idCand;
		listeNotes = new HashMap<Epreuve, Double>();
		listeVoeux = new ArrayList<Voeu>();
		option = null;
		a = null;
		
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public int getIdCand() {
		return idCand;
	}

	public void setIdCand(int idCand) {
		this.idCand = idCand;
	}

	public Map<Epreuve, Double> getListeNotes() {
		return listeNotes;
	}

	public void setListeNotes(Map<Epreuve, Double> listeNotes) {
		this.listeNotes = listeNotes;
	}

	public double getMoyenne() {
		return moyenne;
	}

	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public List<Voeu> getListeVoeux() {
		return listeVoeux;
	}

	public void setListeVoeux(List<Voeu> listeVoeux) {
		this.listeVoeux = listeVoeux;
	}

	public Candidat() {
		// TODO Auto-generated constructor stub
	}
	
	public String toString(){
		String voeux= "";
		for ( Voeu v : listeVoeux){
			voeux+= v;
		}
		String s ="Candidat n°"+idCand + "\nNom: "+ nom + "\nPrenom: "+prenom+"\nDate de naissance: "+datenais+ "\nAdresse: "+adresse+ "\nNotes: "+ listeNotes+"\n"+"Moyenne: "+ moyenne+"\n"+voeux+"\n";
		if (pa == null){
			s += "Ce candidat n'est admis dans aucune école";
		} else {
			s+= "Ce candidat est admis dans l'école : " + pa.getE().nomcomplet + " conformément à son voeu n°"+ pa.getNumeroVoeu();
		}
		return s;
	}
	
	public void addVoeu(Voeu v){
		listeVoeux.add(v);
	}
	
	public void addNote(Epreuve e, double d){
		listeNotes.put(e, d);
	}

	public void setAdmission(Admin a) {
		// TODO Auto-generated method stub
		this.a = a;
	}
}
