package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import candidats.Candidat;
import candidats.Ecole;
import candidats.Epreuve;
import candidats.Preadmin;
import candidats.Voeu;
import connexion.Mdp;
import testConnexion.Oracle;

public class CandidatMapper {

	Candidat c;
	Connection conn;
	ResultSet rs;

	public CandidatMapper(){
		conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public Candidat findById(int id){
		try {

			c = new Candidat(id);
			String req = "Select NOM, PRENOM, DATENAIS, ADRESSE, RANG from candidats where IDCAND = ? ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setString(1, id+"");

			rs = ps.executeQuery();
			while (rs.next()) {
				c.setNom(rs.getString("NOM"));
				c.setPrenom(rs.getString("PRENOM"));
				c.setDatenais(rs.getString("DATENAIS"));
				c.setAdresse(rs.getString("ADRESSE"));
				c.setRang(rs.getInt("RANG"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		findNotes();
		calculMoyenne();
		getVoeux();
		getPreadmission();
		try {
			getOption();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	
	private void getVoeux() {
		// TODO Auto-generated method stub
		try {
			EcoleMapper em = new EcoleMapper();
			String req = "Select idcand, idecole, numero from voeux where IDCAND = ? ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setString(1, c.getIdCand()+"");
			Ecole e = null;
			rs = ps.executeQuery();
			while (rs.next()) {
				e = em.findById(rs.getInt("idecole"));
				Voeu v = new Voeu(c, e, rs.getInt("numero"));
				c.addVoeu(v);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void calculMoyenne() {
		// TODO Auto-generated method stub
		double moyenne = 0;
		int nbNotes = 0;
		Map<Epreuve, Double> map = c.getListeNotes();
		for(Entry<Epreuve, Double> entry : map.entrySet()) {
		    Epreuve e = entry.getKey();
		    moyenne += entry.getValue()*e.getCoeff();
		    nbNotes ++;
		}
		
		c.setMoyenne(moyenne);
	}

	private void findNotes(){
		try {
			EpreuveMapper em = new EpreuveMapper();
			String req = "Select idcand, idep, note from notes where IDCAND = ? ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setString(1, c.getIdCand()+"");
			Epreuve e;
			double d;
			rs = ps.executeQuery();
			while (rs.next()) {
				e = em.findById(rs.getInt("idep"));
				d = rs.getDouble("note");
				c.addNote(e, d);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	private void getPreadmission(){
		try {
			EcoleMapper em = new EcoleMapper();
			Preadmin p;
			String req = "Select idcand, ideco, rang, numerovoeu from preadmin where IDCAND = ? ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setInt(1, c.getIdCand());
			rs = ps.executeQuery();
			if ( rs.next()){
				Ecole e = em.findById(rs.getInt("ideco"));
				p = new Preadmin(e, rs.getInt("rang"), rs.getInt("numerovoeu"));
				c.setPa(p);
			} else {
				c.setPa(null);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private void getOption() throws SQLException {
		// TODO Auto-generated method stub
		String req = "Select options from postpreadmin where IDCAND = ? ";
		PreparedStatement ps = conn.prepareStatement(req);
		ps.setInt(1, c.getIdCand());
		rs = ps.executeQuery();
		if (rs.next()){
			c.setOption(rs.getString("options"));
		} else {
			c.setOption(null);
		}
	}
	
}
