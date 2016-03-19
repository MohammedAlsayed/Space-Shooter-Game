package Project;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public abstract class GameObject implements AnyGroup{
	 /**
     * X coordinate of the rocket.
     */
	protected BufferedImage bulletImage;
    protected double x;
	protected double y;
	protected double rotationalAngle;
    protected double speedAccelerating = 5;
    protected double speedX ;
    protected double speedY ;
    /**
     * In which Direction is the ship going
     */
    protected boolean left, right, up, down, isAlive = true;
	private AffineTransform at = new AffineTransform();
    
	public abstract BufferedImage getImage();
	
    public double getX(){
    	return x;
    }
    public double getY(){
    	return y;
    }
    public void setX(double x){
    	this.x = x;
    }
    public void setY(double y){
    	this.y = y;
    }
    public void setSpeedY(double y){
    	this.speedY = y;
    }
    public double getSpeedY(){
    	return speedY;
    }
    public double getSpeedX(){
    	return speedY;
    }
    public void setSpeedX(double x){
    	this.speedX = x;
    }
    public double getRoatationAngle(){
    	return rotationalAngle;
    }
    public void setRotationAngle(double angle){
    	this.rotationalAngle = angle;
    }
   
    public void setLeft(boolean b){left = b; }
  
    public void setRight(boolean b){right = b; }
  
    public void setUp(boolean b){up = b; }
    
    public void setDown(boolean b){down = b; }
   
    public BufferedImage LoadImage(String FileName){
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
    
    
    public void draw(Graphics2D g2d)
    {
    	at.setToIdentity();
    	at.translate(x, y);
		// Here I am rotating my image
		at.rotate(this.rotationalAngle, this.getImage().getWidth()/2, this.getImage().getHeight()/2);	
		
		g2d.drawImage(this.getImage(), at, null);
    }
}
