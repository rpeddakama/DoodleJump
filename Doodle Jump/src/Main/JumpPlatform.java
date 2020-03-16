package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class JumpPlatform extends GameObject {
	
	private int width = 100;
	private int height = 20;

	public JumpPlatform(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, width, height);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,width,height);
	}

}
