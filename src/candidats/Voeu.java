package candidats;

public class Voeu {
	private Candidat c;
	private Ecole ecole;
	private int priorite;
	public Voeu(Candidat c, Ecole e, int priorite) {
		super();
		this.c = c;
		this.ecole = e;
		this.priorite = priorite;
	}
	public Candidat getCand() {
		return c;
	}
	public void setCand(Candidat c) {
		this.c = c;
	}
	public Ecole getEcole() {
		return ecole;
	}
	public void setEcole(Ecole e) {
		this.ecole = e;
	}
	public int getPriorite() {
		return priorite;
	}
	public void setPriorite(int priorite) {
		this.priorite = priorite;
	}
	
	public String toString(){
		return ""+"Voeu n°" + priorite+ ": "+ ecole+"\n";
	}
}
