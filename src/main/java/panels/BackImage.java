package panels;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

class BackImage extends JPanel{
	  Image img;
	  public BackImage()
	  {
	      try {
	          img=ImageIO.read(new File("src/imgs/play.png"));
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
	  }
	  public void paintComponent(Graphics g)
	  {
	      g.drawImage(img, 0, 0, 640, 400,this);
	  }
}