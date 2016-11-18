package candidats;

import persistance.CandidatMapper;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CandidatMapper cm = new CandidatMapper();
		
		Candidat c = cm.findById(500);
		System.out.println(c);
	}

}
