package Project;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;


public class Bullet extends GameObject{
	//FIELDS
	
	//CONSTRUCTOR
	
	//Functions

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}
	@Override
	public BufferedImage getImage() {
		// TODO Auto-generated method stub
		return bulletImage;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	} 
	
}
