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

			String req = "SELECT  extractvalue(liste,'citations/citation[1]/text()')  FROM (SELECT liste FROM citationsbis ORDER BY dbms_random.value) WHERE rownum = 1";
			PreparedStatement ps = conn.prepareStatement(req);

			rs = ps.executeQuery();
			while (rs.next()) {
				str = rs.getString(1);
				System.out.println(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return str;
	}

}
