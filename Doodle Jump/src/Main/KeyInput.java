package Main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{

	private Handler handler;
	private boolean[] keyDown = new boolean[2];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
		
		keyDown[0] = false;
		keyDown[1] = false;

	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_D) {tempObject.setVelX(5); keyDown[0] = true;}
				if(key == KeyEvent.VK_A) {tempObject.setVelX(-5); keyDown[1] = true;}
				/*if(key == KeyEvent.VK_SPACE && !tempObject.isJumping()) {
					tempObject.jumping = true;
					tempObject.setVelY(-20);
				}*/
			}
		if(key == KeyEvent.VK_ESCAPE)System.exit(1);
				
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(int i=0; i<handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			if(tempObject.getId() == ID.Player) {
				if(key == KeyEvent.VK_D)keyDown[0] = false;
				if(key == KeyEvent.VK_A)keyDown[1] = false;
				
				if(!keyDown[0] && !keyDown[1])tempObject.setVelX(0);
			}
				
		}
	}


}
