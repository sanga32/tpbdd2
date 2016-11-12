package test;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Rectangle;

public class AffImage extends JFrame { 
    JLabel prompt; 
    JPanel p1; 
    JTextField saisie; 
    ImagePaneTest d;  
    String filename;  
    public AffImage() throws Exception { 
        p1 = new JPanel(new FlowLayout()); 
        prompt = new JLabel("nom du fichier Image:"); 
        saisie = new JTextField(); 
        saisie.setColumns(30);
        p1.add(prompt); 
        p1.add(saisie);  
        d = new ImagePaneTest("photo.jpg");  
        p1.add(d); 
        
        saisie.addActionListener(
            new ActionListener(){ 
            public void actionPerformed(ActionEvent e){   
               p1.remove(d);  
               filename = saisie.getText(); 
               saisie.setText("");
               try{
                 d = new ImagePaneTest(filename); 
                 p1.add(d); 
                 p1.validate();  
                 p1.repaint();
               } 
               catch(Exception ex){  
                   System.out.println(ex); } 
             
            }
        }                    
        );
        
        this.setSize(500,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        this.setContentPane(p1);
        //this.pack();
        this.setVisible(true);
    }; 
    
    public static void main(String[] args) throws Exception {
        AffImage affImage = new AffImage();
    }
}
