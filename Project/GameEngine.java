package Project;

import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Class that handles the management, storage, removing, movement 
 * and collision of our game objects.
 * 
 * For storing the objects, we use LinkedLists which you can just assume
 * at this level behave like arrays. But because we want to delete and add
 * objects easily, LinkedLists are more suited for these operations. 
 * 
 * @author Moayad Alnammi
 *
 */

public class GameEngine {
	private LinkedList<AnyGroup> allGameObjects;	//stores the state of all our game objects
	private LinkedList<GroupA> ga;					//stores only the state of GroupA game objects; GoupA are friendlies
	private LinkedList<GroupB> gb;					//stores only the state of GroupB game objects; GroupB are enemies
	
	private LinkedList<Bullet> bullets;				//stores only the bullets to add EACH FRAME. this is done since we
													//might be adding many bullets from different shooters each frame. 
													//(player, timed enemies, etc.)
	private long time = System.currentTimeMillis();
	private PlayerShip player; 						//we keep a reference to the player for easy access

	public GameEngine() {
		ga = new LinkedList<GroupA>();
		gb = new LinkedList<GroupB>();
		allGameObjects = new LinkedList<AnyGroup>();
		bullets = new LinkedList<Bullet>();
	}
	
	/**
	 * 
	 * @return returns a linked list containing all the game objects
	 */
	public LinkedList<AnyGroup> getGameObjects()
	{
		return allGameObjects;
	}
	
	public PlayerShip getPlayer()
	{
		return player;
	}
	
	/**
	 * This method adds a game object to our game.
	 * It is stored in allGameObjects and ga or gb
	 * depending on its Group type. The player is special 
	 * and we keep a reference to it for easy access.
	 * 
	 * @param gObject the game object to be added to our game
	 */
	public void addGameObject(GameObject gObject)
	{

		allGameObjects.add(gObject);
		
		if(gObject instanceof PlayerShip)
		{
			player = (PlayerShip) gObject;
		}
		
		//add the object to its respective group
		if(gObject instanceof GroupA)
			ga.add((GroupA)gObject);
		else if(gObject instanceof GroupB)
			gb.add((GroupB)gObject);
	}

	/**
	 * This method adds a player bullet to our world according
	 * to the location given by paramMouseEvent. You need to finish 
	 * implementing this method given the guidelines below. 
	 * You should work out the actual invocation of this method through 
	 * the use of Mouse Listeners. Or you can just issue this command
	 * each frame by storing the status of the mouse.
	 * 
	 * @param paramMouseEvent
	 */
	public void addPlayerBullet() {
		//create a bullet in front of the player
		double x = player.getX() + player.getImage().getWidth()/2;
		double y = player.getY() + player.getImage().getHeight()/2;
		double angle = player.getRoatationAngle()+Math.toRadians(90);
		
		PlayerBullet bullet = new PlayerBullet(x, y, angle);
	//	bullet.setRotationAngle(player.getRoatationAngle());
		
		//x += speedX*Math.cos(rotationalAngle);
		//y += speedY*Math.sin(rotationalAngle); 

		bullets.add(bullet);

		}
	
	
	/**
	 * This method adds an enemy bullet to the shooter ship and
	 * the location of the player. You need to finish 
	 * implementing this method given the guidelines below. 
	 * You should work out the actual invocation of this method through 
	 * the use of Mouse Listeners. Or you can just issue this command
	 * each frame by storing the status of the mouse.
	 * 
	 * @param paramMouseEvent
	 */
	private void addEnemyBullet(EnemyShipShooter shooter) {
		//create a bullet targeted at the player
		//EnemyShipShooter bullet;
		BufferedImage image = null;
		


		//get player bounds to compute bullet coordinates
	
		
		//compute bullet velocity (magnitude and direction)
		double playerX = player.getX();
		double playerY = player.getY();
		double xDistance = playerX - shooter.getX();
		double yDistance = playerY - shooter.getY();
		double angle = Math.atan2(yDistance, xDistance);
		EnemyShipShooter bullet = new EnemyShipShooter(shooter.getX()+shooter.getImage().getWidth()/2, shooter.getY(), angle);
		
	//	EnemyFollowerShooter bullet1 = new EnemyFollowerShooter(shooter.getX()+shooter.getImage().getWidth()/2, shooter.getY() + shooter.getImage().getHeight()/2, angle);
	
		
	//	bullet.setRotationalAngle(angle);
	
	
		//create bullet	



		//add bullet to our game engine
		bullets.add(bullet);	
	//	bullets.add(bullet1);
		/* you can call a method in the shooter here to indicate that he has shot
		 * you would want to do this if you want the shooter to have a delay of 
		 * 1 second for example. So you would restart the timer every time he shot.
		 */
	}
	
	
	/**
	 * This method will loop on all our game objects and update their locations.
	 * It makes sense to make a move method for each object to do so. Also, if 
	 * it is time for my shooters to shoot, then I should create bullets for them.
	 */
	public void update()
	{
		//update/move all objects
		synchronized(this.allGameObjects)
		{
			for(AnyGroup gObject : allGameObjects)
			{
				
				gObject.update();
				
				if(gObject instanceof Shooter)
				{
					Shooter shooter = (Shooter) gObject;
					if(shooter.timeToShoot())
					{
						addEnemyBullet(shooter);
					}
				}
				if(gObject instanceof Follower)
				{
					Follower shooter = (Follower) gObject;
					if(shooter.timeToShoot())
					{
						addEnemyBullet(shooter);
					}
				}
				else if(gObject instanceof PlayerShip)
				{
					if(player.isFiring())
					{
						this.addPlayerBullet();
					}
				}
			}
			
			//add bullets to the world and clear the bullet list
			addBullets();
		}
		
	}
	
	/**
	 * This method processes the collisions between my GroupA objects and GroupB objects.
	 * This means that an object x does not collide with an object y if they are in the same group.
	 * The iterator objects being created are just a way to loop on linkedlists.
	 */
	public void processCollisions()
	{
		//check which group B objects each Group A object is colliding with
		Iterator<GroupA> gaIter = ga.iterator();
		Iterator<GroupB> gbIter = gb.iterator();

		GroupA gao = null;
		GroupB gbo = null;

		while(gaIter.hasNext())
		{
			gao = gaIter.next();
			
			gbIter = gb.iterator(); //reset iterator
			while(gbIter.hasNext())
			{
				gbo = gbIter.next();
				if(gao.getBounds().intersects(gbo.getBounds()))
				{
					//here you might want the GroupA object (Player or PlayerBullet) to be called
					//and process the collision by maybe killing both objects or one of them.
					//or even subtract from the player's health
					gao.processCollision(gbo);
				}
			}
		}
	}

	
	/**
	 * This method is called at the end of update() to add all the newly stored bullets
	 * each frame. Then erase the bullet list.
	 */
	private void addBullets()
	{
		for(Bullet bullet : bullets)
			addGameObject(bullet);
		
		bullets.clear();
	}
	
	/**
	 * This method is called to remove all dead objects from our world.
	 * As a rudimentary solution, we call this method each frame. However,
	 * in an intensive game world, we would mark these objects as dead (using boolean)
	 * and then perhaps remove them by calling this method in a thread when our list becomes
	 * filled with dead objects to justify the removal overhead.
	 */

	public void removeDeadObjects()
	{
		synchronized(this.allGameObjects)
		{
			Iterator<AnyGroup> iter = allGameObjects.iterator();
	
			AnyGroup go = null;
	
			while(iter.hasNext())
			{
				go = iter.next();
				
				if(!go.isAlive())
				{
					iter.remove();
					
					//remove the objects from their respective group
					if(go instanceof GroupA)
						ga.remove((GroupA)go);
					else if(go instanceof GroupB)
						gb.remove((GroupB)go);
				}
			}
		}
	}
}
