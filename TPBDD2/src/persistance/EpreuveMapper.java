package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import candidats.Epreuve;
import connexion.Mdp;
import testConnexion.Oracle;

public class EpreuveMapper {

	Epreuve e;
	Connection conn;
	ResultSet rs;

	public EpreuveMapper(){
		conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}

	public Epreuve findById(int idEp){
		try {

			e = new Epreuve(idEp);
			String req = "Select intitule, coef from epreuve where idEp=? ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setString(1, idEp+"");

			rs = ps.executeQuery();
			while (rs.next()) {
				e.setIntitule(rs.getString("INTITULE"));
				e.setCoeff(rs.getDouble("COEF"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return e;
	}
	
}
