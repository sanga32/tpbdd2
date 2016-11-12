package vue;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.JPanel;

import persistance.PhotoMapper;
 
public class ImagePane extends JPanel {
     
    private static final long   serialVersionUID    = 1L;
     
    protected BufferedImage buffer;    
     
    public ImagePane(int idCand ) throws Exception{ 
    	PhotoMapper pm = new PhotoMapper();
        pm.getPhoto(idCand);
        //this.setPreferredSize(new Dimension(buffer.getWidth(), buffer.getHeight()));
    }  
         
    public void paintComponent(Graphics g) {
       g.drawImage(buffer,0,0,null);
     }
}

