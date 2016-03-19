# Space-Shooter-Game
This is a project of my university java programming course (ICS 201).
A game where the user controls a space ship around the screen and shoot down enemies.

------- Project Description ------

The main method is located in the Game.java file. We have two types of enemies Follower and Shooter(stationary). You can add enemies by adding in the initialize world method for:

1- shooter: gEngine.addGameObject(new Shooter());
2- follower: gEngine.addGameObject(new Follower(player));

However enemies are already added.
