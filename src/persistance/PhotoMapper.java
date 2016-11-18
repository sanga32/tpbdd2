package persistance;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import testConnexion.Oracle;
import vue.interfaceSwing;
import connexion.Mdp;

public class PhotoMapper {
	Connection conn;
	ResultSet rs;
	String s;

	public PhotoMapper(){
		conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public boolean getPhoto(int idCand) throws Exception { 
		String sql = "SELECT photo FROM photo WHERE idCand ="+idCand ;
		PreparedStatement statement = conn.prepareStatement(sql);
		rs = statement.executeQuery();
		BufferedImage b;
		if(!rs.next()){
			System.out.println("test");
			return false;

		}else{
			Blob blob = rs.getBlob(1);
			try {
				s= "photo.jpg";
				File fileOut= new File("photo"+interfaceSwing.t+".jpg");
				FileOutputStream fos = new FileOutputStream(fileOut);
				fos.write( blob.getBytes(1,(int) blob.length()) );
				fos.close();
			} catch(Exception e) {
				e.printStackTrace();

			}
		}
		return true;
	}; 
}
