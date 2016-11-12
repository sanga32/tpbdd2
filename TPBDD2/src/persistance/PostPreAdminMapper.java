package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import candidats.Candidat;
import connexion.Mdp;
import testConnexion.Oracle;

public class PostPreAdminMapper {
	Candidat c;
	Connection conn;
	ResultSet rs;

	public PostPreAdminMapper() {
		// TODO Auto-generated constructor stub
		conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void insert(int id, String option){
		try {
			
			String req = "insert into postpreadmin values(? , ?) ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setInt(1, id);
			ps.setString(2, option);

			int i = ps.executeUpdate();
			System.out.println(i+"");
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
	}
	
	
}
