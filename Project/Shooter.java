
package Project;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Shooter extends EnemyShipShooter implements GroupB{
	
	private BufferedImage shooterImage;
	private double delta;
	private double lastShot;
	private double timer; 
	private static PlayerShip player;
	
	public Shooter(){
		super(0,0,0);
		shooterImage = LoadImage("resources/images/shooter.png");
		this.x = (int)(Math.random()*1100);
		this.y = (int)(Math.random()*600);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, shooterImage.getWidth(), shooterImage.getHeight());
	}

	@Override
	public void setDead() {
		this.isAlive = false;
		
	}
	public BufferedImage getImage(){
		return shooterImage;
	}

	@Override
	public void update() {
	}

	@Override
	public boolean isAlive() {
		return this.isAlive;
	}
}
