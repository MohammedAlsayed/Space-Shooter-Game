package Project;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;


public class PlayerShip extends GameObject implements GroupA{

    private BufferedImage rocketImg, rocketCrashedImg, rocketFireImg;
	private boolean timeToShoot;   
	public static int health = 20;
	public static int score = 0;
    
    public PlayerShip()
    {    	
    	LoadContent();
    	
    	x = GamePanel.FRAME_WIDTH/2 - getImage().getWidth();
    	y = GamePanel.FRAME_HIGHT/2- getImage().getHeight();
    }
    
    private void LoadContent()
    {
    	rocketImg = LoadImage("resources/images/rocket21.png");
    	rocketCrashedImg = LoadImage("resources/images/firedShip.png");
    }

    /**
     * Here we move the rocket.
     */
    public void update(){
    	if(left)
    		x -= speedAccelerating;
    	if(right)
    		x += speedAccelerating;
    	if(up)
    		y -= speedAccelerating;
    	if(down)
    		y += speedAccelerating;
   
/*    	x += speedX;
    	y += speedY;*/
       	
    	
		if(x + this.rocketImg.getWidth()/2 < 0)
    		x = GamePanel.FRAME_WIDTH - this.rocketImg.getWidth() / 2;
    	else if(x + this.rocketImg.getWidth() / 2 > GamePanel.FRAME_WIDTH )
    		x = -this.rocketImg.getWidth()/2;
    	else if(y + this.rocketImg.getHeight()/2 < 0)
    		y = GamePanel.FRAME_HIGHT - this.rocketImg.getHeight() / 2;
    	else if(y + this.rocketImg.getHeight() / 2 > GamePanel.FRAME_HIGHT )
    		y = -this.rocketImg.getHeight()/2;	
		
		
		int mx = MouseInfo.getPointerInfo().getLocation().x - GamePanel.getFrames()[0].getLocation().x;
		int my = MouseInfo.getPointerInfo().getLocation().y - GamePanel.getFrames()[0].getLocation().y;
		
		my = my-30;
		mx = mx - 7;
		
		double xDistance = (mx - (getX()+getRocketImg().getWidth()/2));
		double yDistance = (my - (getY()+getRocketImg().getHeight()/2));
		double rotationAngle = (Math.atan2(xDistance, yDistance));
		setRotationAngle(-rotationAngle);
    }
    
	public BufferedImage getRocketImg() {
		return rocketImg;
	}
	
	public BufferedImage getImage()
	{
		return rocketImg;
	}
	
	public void setRocketImg(BufferedImage image) {
		this.rocketImg = image ;
	}

	public BufferedImage getRocketCrashedImg() {
		return rocketCrashedImg;
	}

	public BufferedImage getRocketFireImg() {
		return rocketFireImg;
	}

	public void setFiring(boolean b) {
		this.timeToShoot = b;
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.rocketImg.getWidth(), this.rocketImg.getHeight());
	}

	@Override
	public void processCollision(GroupB gbo) {
		this.health--;
		GameRender.healthWidth -= 20;
		if(this.health <= 0){
			isAlive = false;
		}
			
		
		gbo.setDead();
	}

	public boolean isFiring() {
		return this.timeToShoot;
	}
}
    

