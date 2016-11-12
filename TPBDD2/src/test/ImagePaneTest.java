package test;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import java.awt.image.BufferedImage;

import java.io.File;

import javax.imageio.ImageIO;

import javax.swing.JPanel;
 
public class ImagePaneTest extends JPanel {
     
    private static final long   serialVersionUID    = 1L;
     
    protected BufferedImage buffer;    
     
    public ImagePaneTest(String filename) throws Exception{ 
        buffer = ImageIO.read(new File(filename)); 
        this.setPreferredSize(new Dimension(buffer.getWidth(), buffer.getHeight()));
    }  
         
    public void paintComponent(Graphics g) {
       g.drawImage(buffer,0,0,null);
     }
}

