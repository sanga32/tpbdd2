package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import candidats.Candidat;
import connexion.Mdp;
import testConnexion.Oracle;

public class CitationMapper {
	Candidat c;
	Connection conn;
	ResultSet rs;

	public CitationMapper(){
		conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
	
	
	public String getCitation(){
		
		String str = "";

		try {

			String req = "SELECT liste FROM (SELECT liste FROM citationbis ORDER BY dbms_random.value) WHERE rownum = 1";
			PreparedStatement ps = conn.prepareStatement(req);

			rs = ps.executeQuery();
			while (rs.next()) {
				str = rs.getString("liste");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return str;
	}

}
