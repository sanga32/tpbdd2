package candidats;

public class Preadmin {
	Ecole e;
	int rang;
	int numeroVoeu;
	public Preadmin(Ecole e, int rang, int numeroVoeu) {
		super();
		this.e = e;
		this.rang = rang;
		this.numeroVoeu = numeroVoeu;
	}
	public Ecole getE() {
		return e;
	}
	public void setE(Ecole e) {
		this.e = e;
	}
	public int getRang() {
		return rang;
	}
	public void setRang(int rang) {
		this.rang = rang;
	}
	public int getNumeroVoeu() {
		return numeroVoeu;
	}
	public void setNumeroVoeu(int numeroVoeu) {
		this.numeroVoeu = numeroVoeu;
	}
	
	
}
