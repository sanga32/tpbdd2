package candidats;

public class Ecole {
	
	int idEcole;
	String sigle, nomcomplet;
	int nbp;
	
	
	public Ecole(int id) {
		// TODO Auto-generated constructor stub
		idEcole = id;
	}


	public int getIdEcole() {
		return idEcole;
	}


	public void setIdEcole(int idEcole) {
		this.idEcole = idEcole;
	}


	public String getSigle() {
		return sigle;
	}


	public void setSigle(String sigle) {
		this.sigle = sigle;
	}


	public String getNomcomplet() {
		return nomcomplet;
	}


	public void setNomcomplet(String nomcomplet) {
		this.nomcomplet = nomcomplet;
	}


	public int getNbp() {
		return nbp;
	}


	public void setNbp(int nbp) {
		this.nbp = nbp;
	}
	
	public String toString(){
		return sigle + " " + nomcomplet ;
	}
}
