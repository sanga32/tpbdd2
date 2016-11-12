package testConnexion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.sql.Date;

public class InsertImage {

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException  {
		// TODO Auto-generated method stub

		try {
		Connection conn = Oracle.getConnection("DELPORTE2", "wanadoo1");
		conn.setAutoCommit(false);

		String sql = "INSERT INTO PHOTO (idcand, photo) VALUES (?, ?)";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, 15);
		File fichier = new File("/home/m1miage/delportek/Images/photo15.jpg");
		FileInputStream fis = new FileInputStream(fichier);
		stmt.setBinaryStream(2, fis, (int) fichier.length());
		stmt.execute();
		conn.commit();
		fis.close();
		conn.close();
		} catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}

}
