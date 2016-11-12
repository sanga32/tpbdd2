package vue;

import javax.swing.*;

import persistance.CandidatMapper;
import persistance.PhotoMapper;
import testConnexion.Oracle;
import candidats.Candidat;
import connexion.Mdp;
import javafx.scene.layout.Pane;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class interfaceSwing extends JFrame {


	JTextField idCand;
	JPanel top ;
	JPanel center ;
	JPanel south;
	JPanel west;
	JPanel east;
	JPanel pane;
	JButton valider;
	//ImagePane image;

	public interfaceSwing() {
		super("Fiche candidat");

		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
				System.exit(0);
			}
		};
		pane = new JPanel();
		idCand= new JTextField(10);
		top = new JPanel();
		center = new JPanel();
		south = new JPanel();
		west = new JPanel();
		east = new JPanel();
		valider = new JButton("Valider");

		this.setLocationRelativeTo(null);
		idCand.setPreferredSize(new Dimension(50, 30));
		pane.setLayout(new BorderLayout());
		JLabel label = new JLabel("Saisir ID candidat");

		idCand.addActionListener(
				new ActionListener(){ 

					public String getCitation(){
						Candidat c;
						Connection conn;
						ResultSet rs;
						String str = "";

						conn = Oracle.getConnection("DELPORTE2", Mdp.mdp);
						try {
							conn.setAutoCommit(false);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

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

					public void actionPerformed(ActionEvent e){   
						Candidat c = new Candidat(Integer.parseInt(idCand.getText()));
						CandidatMapper cm = new CandidatMapper();
						center.removeAll();
						west.removeAll();
						south.removeAll();
						east.removeAll();
						c = cm.findById(Integer.parseInt(idCand.getText()));
						JTextArea j = new JTextArea(""+c);
						j.setEditable(false);
						center.add(j);
						System.out.println(""+c);

						JRadioButton A = new JRadioButton("A");
						JRadioButton B = new JRadioButton("B");
						ButtonGroup choix = new ButtonGroup();
						A.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub

							}
						});
						B.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub

							}
						});
						choix.add(A);
						choix.add(B);

						east.add(A);
						east.add(B);
						PhotoMapper pm = new PhotoMapper();
						try {
							pm.getPhoto(c.getIdCand());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ImageIcon icon = new ImageIcon("photo.jpg");
						//Image zoom = scaleImage(icon.getImage(), 0.5d);//facteur
						Image zoom = scaleImage(icon.getImage());//taille en pixels
						System.out.println(icon.toString());
						Icon iconScaled = new ImageIcon(zoom);
						JLabel ball = new JLabel(iconScaled);

						JLabel image = new JLabel( iconScaled);
						image.setSize(30, 30);
						west.add(image);
						pane.add(west, BorderLayout.WEST);						
						pane.add(south, BorderLayout.SOUTH);
						pane.add(center, BorderLayout.CENTER);
						pane.add(east, BorderLayout.EAST);

						pane.updateUI();
					}
				}                    
				);



		top.add(label);
		top.add(idCand);
		pane.add(top, BorderLayout.NORTH);

		this.setContentPane(pane);
		addWindowListener(l);
		setSize(850, 650);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String [] args){
		JFrame frame = new interfaceSwing();

	}

	//avec une taille en pixels (=hauteur si portrait, largeur si paysage):
	public static Image scaleImage(Image source) {
		int width = 150;
		int height = 150;
		/*double f = 0;
		if (width < height) {//portrait
			f = (double) height / (double) width;
			width = (int) (size / f);
			height = size;
		} else {//paysage
			f = (double) width / (double) height;
			width = size;
			height = (int) (size / f);
		}*/
		return scaleImage(source, width, height);
	}
	/*
	//avec un facteur (<1 pour rétrécir, >1 pour agrandir):
	public static Image scaleImage(final Image source, final double factor) {
	    int width = (int) (source.getWidth(null) * factor);
	    int height = (int) (source.getHeight(null) * factor);
	    return scaleImage(source, width, height);
	}*/
	public static Image scaleImage(Image source, int width, int height) {
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(source, 0, 0, width, height, null);
		g.dispose();
		return img;
	}
}