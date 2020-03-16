package Main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 500;
	public static final int HEIGHT = 800;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	Camera cam;
	
	public Game() {
		handler = new Handler();
		
		handler.createLevel();
`		handler.addObject(new Player(100, 500, ID.Player, handler));
		this.addKeyListener(new KeyInput(handler));
		cam = new Camera(0,0);
	}
	
	public synchronized void start() {
		if (running) return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
		
		
	}
	
//	public synchronized void stop() {
//		if (running = false) return;
//		try {
//			thread.join();
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		running = false;
//	}
	
	public void run() {
		requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
		 long now = System.nanoTime();
		 delta += (now - lastTime) / ns;
		 lastTime = now;
		 while(delta >= 1){
		  tick();
		  updates++;
		  delta--;
		 }
		 render();
		 frames++;
		   
		 if(System.currentTimeMillis() - timer > 1000){
		  timer += 1000;
		  //System.out.println("FPS: " + frames + " TICKS: " + updates);
		  frames = 0;
		  updates = 0;
		 }
		}


	}
	
	public void tick() {
		handler.tick();
		for(int i = 0; i < handler.object.size(); i++) {
			if(handler.object.get(i).getId() == ID.Player) {
				cam.tick(handler.object.get(i));
			}
		}
	}
	
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
				
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		g2d.translate(cam.getX(), cam.getY());
		
		handler.render(g);
		
		g2d.translate(-cam.getX(), -cam.getY());

		g.dispose();
		bs.show();
	}
	
	public static int clamp(int var, int min, int max) {
		if(var <= min) return var = min;
		if(var >= max) return var = max;
		else return var;
	}
	
	public static void main(String args[]) {
		new Window(WIDTH, HEIGHT, "Game", new Game());
	}
}
