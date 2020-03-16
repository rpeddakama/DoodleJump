package Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private Handler handler;
	private int width = 50, height = 60;
	private GameObject player;
	
	private float gravity = 0.5f;
	private final float MAX_SPEED = 5;
	private int timer = 150;

	private Font font = new Font("Arial", Font.BOLD, 200);
	public Player(float x, float y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		
	}

	public void tick() {
		if(timer <= 0) {
			x+=velX;
			y+=velY;
		}
		
		timer--;		
		if(falling || jumping) velY+=gravity;
		if(gravity > MAX_SPEED) gravity = MAX_SPEED;
		
		collision();
	}

	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)x, (int)y, width, height);
		g.setColor(Color.white);
		g.fillRect((int)x+5, (int)y+10, 15, 15);
		g.fillRect((int)x+width-20, (int)y+10, 15, 15);
		g.fillRect((int)x+5, (int)y+35, 40, 10);
		
		g.setFont(font);
		if(timer < 150 && timer > 100)g.drawString("3", Game.WIDTH/2-50, (int)y);
		if(timer < 100 && timer > 50)g.drawString("2", Game.WIDTH/2-50, (int)y);
		if(timer < 50 && timer > 0)g.drawString("1", Game.WIDTH/2-50, (int)y);
		
		Graphics2D g2d = (Graphics2D) g;
		
		g2d.setColor(Color.red);
		g2d.draw(getBounds());
		g2d.draw(getBoundsLeft());
		g2d.draw(getBoundsRight());
		g2d.draw(getBoundsTop());
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x+10, (int)y+height/2, width-20, height/2);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+10, 5, height-20);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int)x+width-5, (int)y+10, 5, height-20);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+10, (int)y, width-20, height/2);
	}
	
	public void collision() {
		for(int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(handler.object.get(i).getId() == ID.Player) player = handler.object.get(i);
			if(tempObject.getId() == ID.Block || tempObject.getId() == ID.JumpPlatform) {
				if(getBounds().intersects(tempObject.getBounds())) {
					velY = 0;
					y = tempObject.getY()-60; 
					if(tempObject.getId() == ID.JumpPlatform) {
						handler.removeObject(tempObject);
						player.setVelY(-20);
					}
					falling = false;
					jumping = false;
				}
				
				if(getBoundsTop().intersects(tempObject.getBounds())) {
					velY = 0;
					y = tempObject.getY()+38;
				}
				
				else if(getBoundsLeft().intersects(tempObject.getBounds())) {
					if(tempObject.getId() == ID.Block) x = tempObject.getX()+35;
					if(tempObject.getId() == ID.JumpPlatform) x = tempObject.getX()+100;
				}
				
				else if(getBoundsRight().intersects(tempObject.getBounds())) {
					x = tempObject.getX()-50;
				}
				
				
				else falling = true;
				
			}
		}
	}

}
