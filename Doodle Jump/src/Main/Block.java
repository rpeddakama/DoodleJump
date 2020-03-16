package Main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Block extends GameObject{

	public Block(float x, float y, ID id) {
		super(x, y, id);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.green.darker());
		//g.fillRect(x, y, 100, 100);
		g.fillRect((int)x, (int)y, 32, 32);
		
		Graphics2D g2d = (Graphics2D) g;
//		g2d.setColor(Color.green);
//		g2d.draw(getBounds());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 32, 32);
	}

}
