package Project;


public class Game {
	volatile PlayerShip player;
	Controller controller;
	Bullet bullet;
	GamePanel gamePanel;
	public static boolean running;
	private GameEngine gEngine;
	
	public Game(){
		player = new PlayerShip();
		
		gEngine = new GameEngine();

		gamePanel = new GamePanel(player, gEngine);
		
		controller = new Controller(player, gEngine);
		gamePanel.setVisible(true);
		gamePanel.setController(controller);
		
	
		
		initializeWorld();
		
	}
	private void initializeWorld() {
		//add player to world
		gEngine.addGameObject(player);
		
		//add enemies
		// Follower
		// Shooter
		gEngine.addGameObject(new Shooter());
		gEngine.addGameObject(new Follower(player));
		gEngine.addGameObject(new Follower(player));
		gEngine.addGameObject(new Follower(player));
		gEngine.addGameObject(new Follower(player));
		gEngine.addGameObject(new Shooter());
		gEngine.addGameObject(new Follower(player));
		gEngine.addGameObject(new Shooter());
		gEngine.addGameObject(new Follower(player));
	
	}
	
	
	public void run(){
		
		running = true;
		// Game Loop
		while(running){
			gEngine.update();
			
			gamePanel.repaint();
			
			gEngine.processCollisions();
			gEngine.removeDeadObjects();
			
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String [] args){
		Game g = new Game();
		g.run();
	
	}
	
}
