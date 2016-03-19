package Project;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Follower extends EnemyShipShooter implements GroupB{
	
	private BufferedImage followerImage;
	double xDistance;
	double yDistance;
	PlayerShip player;

	
	public Follower(PlayerShip player){
		super(0,0,0);
		followerImage = LoadImage("resources/images/follower.png");
		this.x = (int)(Math.random()*1100);
		this.y = (int)(Math.random()*600);

		speedX = 2;
		speedY = 2;
		this.player = player;
		
	}
	public void update(){
		xDistance = player.getX() - this.x;
		yDistance = player.getY() - this.y;
		this.rotationalAngle = Math.atan2(yDistance, xDistance);
		
		x += speedX*Math.cos(rotationalAngle);
		y += speedY*Math.sin(rotationalAngle);
		

		
		if(x + this.followerImage.getWidth()/2 < 0)
    		x = GamePanel.FRAME_WIDTH - this.followerImage.getWidth() / 2;
    	else if(x + this.followerImage.getWidth() / 2 > GamePanel.FRAME_WIDTH )
    		x = -this.followerImage.getWidth()/2;
    	else if(y + this.followerImage.getHeight()/2 < 0)
    		y = GamePanel.FRAME_HIGHT - this.followerImage.getHeight() / 2;
    	else if(y + this.followerImage.getHeight() / 2 > GamePanel.FRAME_HIGHT )
    		y = -this.followerImage.getHeight()/2;	
	}
	
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.followerImage.getWidth(), this.followerImage.getHeight());
	}

	@Override
	public void setDead() {
		this.isAlive = false;
		
	}
	public BufferedImage getImage(){
		return this.followerImage;
	}
	@Override
	public boolean isAlive() {

		return this.isAlive;
	}
	
	
}
