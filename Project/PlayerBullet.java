package Project;

import java.awt.Rectangle;

public class PlayerBullet extends Bullet implements GroupA{
	
	private PlayerShip player;
	
	public PlayerBullet (double x, double y, double angle){
		this.bulletImage = LoadImage("resources/images/bullet3.png");	
		speedX = 10;
		speedY = 10;
		this.x = x - bulletImage.getWidth()/2;
		this.y = y - bulletImage.getHeight()/2;
		this.rotationalAngle = angle;
	//	this.player = player;
	//	rotationalAngle = player.getRoatationAngle();
	}
	public void update(){
	//	setRotationAngle(player.getRoatationAngle());
		
		x += speedX*Math.cos(rotationalAngle);
		y += speedY*Math.sin(rotationalAngle);
		
		if(x  < -40)
    		this.isAlive = false;
    	else if(x > GamePanel.FRAME_WIDTH )
    		this.isAlive = false;
    	
		if(y  < -40)
    		this.isAlive = false;
    	else if(y > GamePanel.FRAME_HIGHT )
    		this.isAlive = false;
	}
	@Override
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, this.bulletImage.getWidth(), this.bulletImage.getHeight());
	}

	@Override
	public void processCollision(GroupB gbo) {
		this.isAlive = false;
		PlayerShip.score += 5;
		gbo.setDead();
	}
}
