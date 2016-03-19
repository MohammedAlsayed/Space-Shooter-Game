package Project;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.*;


public class Controller implements KeyListener, MouseMotionListener, MouseListener{
	
	
	private boolean running;
	private Thread thread;
	private long time = System.currentTimeMillis();
	private PlayerShip player;
	private GameEngine gEngine;
	
	public static int lastOffsetX, lastOffsetY,  newX, newY;
	
	public Controller(PlayerShip player, GameEngine gEngine){
		super();
		this.player = player;
		this.gEngine = gEngine;
	}
	
	
	public void keyPressed(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_A){
			player.setLeft(true);
		}
		if(keyCode == KeyEvent.VK_D){
			player.setRight(true);
		}
		if(keyCode == KeyEvent.VK_W)
			player.setUp(true);
		if(keyCode == KeyEvent.VK_S)
			player.setDown(true);
	}
	public void keyReleased(KeyEvent key) {
		int keyCode = key.getKeyCode();
		if(keyCode == KeyEvent.VK_A){
			player.setLeft(false);
			resetPostion();
		}
			
		if(keyCode == KeyEvent.VK_D){
			player.setRight(false);
			resetPostion();
		}
		
		if(keyCode == KeyEvent.VK_W){
			player.setUp(false);
			resetPostion();
		}
			
		if(keyCode == KeyEvent.VK_S){
			player.setDown(false);
			resetPostion();
		}
	}
	public void keyTyped(KeyEvent key) {
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	// Here are they equation to calculate the angle between the mouse  cursor and the image
	public void mouseMoved(MouseEvent e) {
	
		
	}
	
	public void resetPostion(){
		player.setSpeedX(0);
		player.setSpeedY(0);
	}

	public void mouseClicked(MouseEvent e) {
	//	gEngine.addPlayerBullet();
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getModifiers() == 4 && (System.currentTimeMillis() - time >= 200))
		{
			time = System.currentTimeMillis();
			gEngine.addPlayerBullet();
		}
	}

	public void mouseReleased(MouseEvent e) {
		
	}
}
