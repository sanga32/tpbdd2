package candidats;

public class Epreuve {

	private int idCand;
	private String intitule;
	private double coeff;
	
	public Epreuve(int idCand) {
		this.idCand = idCand;
	}
	public int getIdCand() {
		return idCand;
	}
	public void setIdCand(int idCand) {
		this.idCand = idCand;
	}
	public String getIntitule() {
		return intitule;
	}
	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}
	public double getCoeff() {
		return coeff;
	}
	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
	
	public String toString(){
		return ""+ intitule;
	}
	
}
