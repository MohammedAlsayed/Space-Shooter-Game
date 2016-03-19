package Project;

import java.awt.*;
import java.awt.geom.AffineTransform;

import javax.swing.*;


public class GamePanel extends JFrame{
	GameRender gameRender;
	private PlayerShip player;
	
	public static int FRAME_WIDTH = 1200;
	public static int FRAME_HIGHT = 720;
	public static Rectangle recPanel;
	public GamePanel(PlayerShip player, GameEngine gEngine){
		this.player = player;		
		setSize(FRAME_WIDTH,FRAME_HIGHT);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		recPanel = this.getBounds();
		gameRender = new GameRender(player, gEngine );
		add(gameRender, BorderLayout.CENTER);
		
	}
	
	
	
	public void setController(Controller controller) {
		gameRender.addKeyListener(controller);
		gameRender.addMouseMotionListener(controller);
		gameRender.addMouseListener(controller);
		gameRender.requestFocus();
	}
	
}
