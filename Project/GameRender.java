package Project;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.font.TextAttribute;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GameRender extends JPanel{
	
	PlayerShip player;
	GameEngine gEngine;
	private Image background; 
	private BufferedImage loseImg;
	private BufferedImage winImg;
	//AffineTransform at = new AffineTransform();
	static int healthWidth = 400;
	private String words = "Score:";
	private String healthBar = "Player's Health: ";
	
	public GameRender(PlayerShip player, GameEngine gEngine){
			this.player = player;
			this.gEngine = gEngine;
			loseImg = LoadImage("resources/images/lose.png");
		}
	
	public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			setBackground("resources/images/background1.jpg");
			g2d.drawImage(background, 0, 0, null);
			
			
			/*RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
			rh.put(RenderingHints.KEY_RENDERING,
	               RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setRenderingHints(rh);*/
			
			   Font font = new Font("Serif", Font.PLAIN, 18);

		        AttributedString as1 = new AttributedString(words);
		        as1.addAttribute(TextAttribute.FONT, font);

		        AttributedString as2 = new AttributedString(healthBar);
		        as2.addAttribute(TextAttribute.FONT, font);
	       
			g2d.setColor(Color.blue);
		//	g2d.drawString("Player's Health", , 1000);
			g2d.fillRect(700,650, healthWidth, 20);
			
			//draw all objects
			synchronized(gEngine.getGameObjects())
			{
				for(AnyGroup gObject : gEngine.getGameObjects())
				{
					gObject.draw(g2d);
				}
			}
			if(PlayerShip.health <= 0){
				g2d.drawImage(player.getRocketCrashedImg(),(int) player.getX(),(int) player.getY(), null);
				g2d.drawImage(loseImg, GamePanel.FRAME_WIDTH/2- loseImg.getWidth()/2, GamePanel.FRAME_HIGHT/2- loseImg.getHeight()/2, null);

				Game.running = false;
			}
			
			// Setting the coordination
			g2d.setColor(Color.white);
			g2d.drawString(as2.getIterator(), 580, 665);
	        g2d.drawString(as1.getIterator(), 1090, 20);
	        g2d.drawString(player.score+"", 1140, 20);
			g2d.drawString("x: "+player.getX()+" y: "+player.getY(), 10, 18);
		}
	
	// Setting the Background
	private Image setBackground(String fileName){
		this.background = new ImageIcon(this.getClass().getResource(fileName)).getImage();
		return background;
	}
	  private BufferedImage LoadImage(String FileName){
		    BufferedImage img = null;
		    try{
		        img = ImageIO.read(new File(this.getClass().getResource(FileName).toURI()));
		    }catch(IOException e){
		        System.out.println("Error : No image found!!");
		    }catch (URISyntaxException e) {
		        System.out.println("Error : Wrong URI");
		    }
	    return img;
	   }
}