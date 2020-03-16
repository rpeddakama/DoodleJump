package Main;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

public class Handler {
	
	private Random r = new Random();
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i = 0; i < object.size(); i++) {
			GameObject tempObject = object.get(i);
			tempObject.render(g);
		}
	}
	
	public void addObject(GameObject object) {
		this.object.add(object);
	}
	
	public void removeObject(GameObject object) {
		this.object.remove(object);
	}
	
	public void createLevel() {
		//floor
		for(int i = 0; i <= Game.WIDTH; i+=32) {
			addObject(new Block(i, Game.HEIGHT-35, ID.Block));
		}
		
		//left
		for(int i = Game.HEIGHT-35; i > -1200; i-=32) {
			addObject(new Block(0, i, ID.Block));
		}
		
		//right
		for(int i = Game.HEIGHT-35; i > -1200; i-=32) {
			addObject(new Block(Game.WIDTH-32, i, ID.Block));
		}
		
		//platform
		addObject(new JumpPlatform(100, 600, ID.JumpPlatform));
		for(int i = 350; i > -2000; i-=250) {
		addObject(new JumpPlatform(50 + r.nextInt(Game.WIDTH-150),i,ID.JumpPlatform));
		}
	}
}
