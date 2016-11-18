package persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import candidats.Candidat;
import candidats.Ecole;
import connexion.Mdp;
import testConnexion.Oracle;

public class EcoleMapper {
	Ecole e;
	Connection conn;
	ResultSet rs;

	public EcoleMapper(){
		conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public Ecole findById(int id){
		try {

			e = new Ecole(id);
			String req = "Select ideco, sigle, nomcomplet, nbp from ecoles where ideco = ? ";
			PreparedStatement ps = conn.prepareStatement(req);
			ps.setString(1, id+"");

			rs = ps.executeQuery();
			while (rs.next()) {
				e.setNbp(rs.getInt("nbp"));
				e.setNomcomplet(rs.getString("nomcomplet"));
				e.setSigle(rs.getString("sigle"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return e;
	}
}
