package Project;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class EnemyShipShooter extends Bullet implements GroupB{

	private BufferedImage enemyBulletImage;
	private long time = System.currentTimeMillis();
	
	
	public EnemyShipShooter(double x, double y, double angle) {
		this.x = x;
		this.y = y;
		this.rotationalAngle = angle;
		enemyBulletImage = LoadImage("resources/images/bullet.png");
		speedX = 5;
		speedY = 5;
	}
	
	public void update()
	{
		x += speedX * Math.cos(rotationalAngle);
		y += speedY * Math.sin(rotationalAngle);
	
		if(x  < -40)
    		this.isAlive = false;
    	else if(x > GamePanel.FRAME_WIDTH )
    		this.isAlive = false;
    	
		if(y  < -40)
    		this.isAlive = false;
    	else if(y > GamePanel.FRAME_HIGHT )
    		this.isAlive = false;
	}
	
	public BufferedImage getImage()
	{
		return enemyBulletImage;
	}
	
	public boolean timeToShoot(){
		if((System.currentTimeMillis() - time >= 1000)){
			time = System.currentTimeMillis();
			return true;
		}
		else 
			return false;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, enemyBulletImage.getWidth(), enemyBulletImage.getHeight());
	}

	@Override
	public void setDead() {
		this.isAlive = false;
	}
	
}
