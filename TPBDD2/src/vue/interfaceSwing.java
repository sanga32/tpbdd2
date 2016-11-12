package vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import candidats.Candidat;
import persistance.CandidatMapper;
import persistance.PhotoMapper;
import persistance.PostPreAdminMapper;

public class interfaceSwing extends JFrame {


	JTextField idCand;
	JPanel top ;
	JPanel center ;
	JPanel south;
	JPanel west;
	JPanel east;
	JPanel pane;
	JButton valider;
	public static int t = 0;
	
	Boolean ch;
	Candidat c;
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
		ch = null;
		this.setLocationRelativeTo(null);
		idCand.setPreferredSize(new Dimension(50, 30));
		pane.setLayout(new BorderLayout());
		JLabel label = new JLabel("Saisir ID candidat");

		idCand.addActionListener(
				new ActionListener(){ 


					public void actionPerformed(ActionEvent e){   
						c = new Candidat(Integer.parseInt(idCand.getText()));
						CandidatMapper cm = new CandidatMapper();
						center.removeAll();
						west.removeAll();
						south.removeAll();
						east.removeAll();
						c = cm.findById(Integer.parseInt(idCand.getText()));
						JTextArea j = new JTextArea(""+c);
						j.setEditable(false);
						System.out.println(""+c);
						center.add(j);
						JRadioButton A = null; 

						if ( c.getPa() == null ){
							A = new JRadioButton("(A) : Attendre un éventuel désistement");
						} else {
							if ( c.getPa().getNumeroVoeu() != 1){
								A = new JRadioButton("(A) : Attendre un désistement pour un voeu prioritaire");
							}else {
								A = new JRadioButton("(A) : Admission définitive");
							}

						}

						JRadioButton B = new JRadioButton("(B) : Renoncer à postuler");
						ButtonGroup choix = new ButtonGroup();
						A.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								ch = new Boolean(true);
							}
						});
						B.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								ch = new Boolean(false);
							}
						});
						choix.add(A);
						choix.add(B);
						valider.addActionListener(new ActionListener() {

							@Override
							public void actionPerformed(ActionEvent e) {
								// TODO Auto-generated method stub
								System.out.println(ch);
								if ( ch != null){
									PostPreAdminMapper pp = new PostPreAdminMapper();

									if (ch){
										pp.insert(c.getIdCand(), "A");
									} else {
										pp.insert(c.getIdCand(), "B");
									}
									JOptionPane jop1;      
									jop1 = new JOptionPane();
									jop1.showMessageDialog(null, "Votre choix à bien été pris en compte.", "Information", JOptionPane.INFORMATION_MESSAGE); 
									east.removeAll();
									east.add(new JLabel("Votre choix à déjà été pris en compte"));
									pane.updateUI();

								}
							}
						});
						east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
						if (c.getOption() == null ){
							east.add(A);
							east.add(B);
							east.add(valider);
						} else {
							east.add(new JLabel("Votre choix à déjà été pris en compte"));
						}
						PhotoMapper pm = new PhotoMapper();
						boolean isphoto=true;
						try {
							isphoto = pm.getPhoto(c.getIdCand());
							t++;
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						ImageIcon icon=null;
						if(isphoto){
						icon=new ImageIcon("photo"+(t-1)+".jpg");
						}else{
							icon = new ImageIcon("photo.jpg");
						}
						Image zoom = scaleImage(icon.getImage());//taille en pixels
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
		
		setSize(1200, 650);
		setLocation(10, 10);
		setSize(1200, 450);
		setResizable(false);
		setVisible(true);
	}

	public static void main(String [] args){
		JFrame frame = new interfaceSwing();

	}

	//avec une taille en pixels (=hauteur si portrait, largeur si paysage):
	public static Image scaleImage(Image source) {
		int width = 200;
		int height = 200;
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
	//avec un facteur (<1 pour rÃ©trÃ©cir, >1 pour agrandir):
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