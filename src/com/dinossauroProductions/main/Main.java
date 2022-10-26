package com.dinossauroProductions.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.dinossauroProductions.components.AnimatedSprite;
import com.dinossauroProductions.components.Sprite;
import com.dinossauroProductions.entities.CeilingTile;
import com.dinossauroProductions.entities.Entity;
import com.dinossauroProductions.entities.FloorTile;
import com.dinossauroProductions.entities.Player;
import com.dinossauroProductions.entities.Potion;


public class Main extends Canvas implements Runnable, KeyListener, MouseListener{
	
	
	//engine specifics
	
	private static final long serialVersionUID = 1L;
	public static JFrame frame;
	private Thread thread;
	public boolean isRunning = true; 
	public static final int WIDTH = 640/2;  //640 * 2 = 1280   16
	public static final int HEIGHT = 360/2; //380 * 2 = 720    9
	public static final double SCALE = 2*2;
	public int maxFPS = 60;
	private BufferedImage image;
	public static int FPS = 0;
	public Random rand;
	
	
	//game objects
	
	public static BufferedImage spritesheet1;
	
	public static ArrayList<Entity> entities = new ArrayList<Entity>();

	public Player player;
	

	public Main() {
		rand = new Random();
		addKeyListener(this);
		addMouseListener(this);
		setPreferredSize(new Dimension((int)(WIDTH*SCALE),(int)(HEIGHT*SCALE)));
		initFrame();
		
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		
		
		spritesheet1 = getImageAsset("/spritesheet1.png");
		
		
		for(int xx = 0; xx < WIDTH/16; xx++) {
			for(int yy = 0; yy < (HEIGHT/16)+1; yy++) {
				
				Entity ent = new FloorTile(xx*16, yy*16);
				Sprite sprite = new Sprite(getImageSector(spritesheet1, 0, 16, 16, 16), ent);
				ent.addComponent(sprite);
				entities.add(ent); 
			}
		}
		
		for(int xx = 0; xx < WIDTH/16; xx++) {
			for(int yy = 0; yy < (HEIGHT/16)+1-9; yy++) {
				
				Entity ent = new CeilingTile(xx*16, yy*16);
				ent.addComponent(new Sprite(CeilingTile.CEILING, ent));
				entities.add(ent); 
			}
		}
		
		
		
		player = new Player(WIDTH/2, HEIGHT/2);
		entities.add(player);
		player.addComponent(new Sprite(null, player));
		player.addComponent(new AnimatedSprite(player, Player.WALKING, 15));
		
		Entity ent = new Potion((WIDTH/2)-8, HEIGHT/2-48);
		ent.addComponent(new Sprite(Potion.POTION, ent));
		entities.add(ent);
		
		
		
		
		Collections.sort(entities);
		
		
		
		System.out.println("Há um total de "+entities.size()+" entidades");
		
		/*
		 * entities.add(new Entity(WIDTH/2, HEIGHT/2)); 
		 * Sprite sprite = new Sprite(getImageSector(spritesheet1, 0, 0, 16, 16), entities.get(0));
		 * entities.get(0).addComponent(sprite);
		 */
		
	}
	
	public static void main(String args[]) {
		Main game = new Main();
		game.start();
	}
	
	public void initFrame() {
		frame = new JFrame("Game");
		frame.add(this);
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		isRunning = true;
		thread.start();
	}
	
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		double timer = System.currentTimeMillis();
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / ns;
			lastTime = now;
			if(delta >= 1) {
				tick();
				render();
				frames++;
				delta--;
			}
			
			if(System.currentTimeMillis() - timer >= 1000){
				
				FPS = frames;
				frames = 0;
				timer+=1000;
				
			}
			
		}
		
		stop();
	}
	public void tick() {
		
		//aplicar lógica
		
		
		for(int i = 0; i < entities.size(); i++) {
			
			entities.get(i).tick();
			
		}
		
		
		
		//
		
	}
	public void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = image.getGraphics();
		
		g.setColor(new Color(255, 255, 255));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		//render stuff
		
		
		for(int i = 0; i < entities.size(); i++) {
			
			entities.get(i).render(g);
			
		}
		
		
		
			
		//stop render
		
		
		g.dispose();
		g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, (int)(WIDTH*SCALE), (int)(HEIGHT*SCALE), null);
		
		bs.show();
		
	}
	
	
	public BufferedImage getImageAsset(String path) {
		
		BufferedImage image;
		
		try {
			
			image = ImageIO.read(getClass().getResource(path));
			return image;
			
			
		} catch (IOException e) {
			
			System.out.println("Imagem de path "+path+" não foi encontrada.");
			e.printStackTrace();
			return null;
		}
		
	}
	
	public static BufferedImage getImageSector(BufferedImage image, int x, int y, int width, int height) {
		
		if(x > image.getWidth() || y > image.getHeight()) {
			//crop fora da imagem
			System.out.println("Crop impossível");
			return null;
		}
		else if(width > image.getWidth() || height > image.getHeight()) {
			//crop grande demais
			System.out.println("Crop impossível");
			return null;
		}
		else if(x + width > image.getWidth() || y + height > image.getHeight()) {
			//crop pega fora da tela
			System.out.println("Crop impossível");
			return null;
		}
		
		return image.getSubimage(x, y, width, height);
		
	}
	

	public void keyPressed(KeyEvent e) {
		
		
	}

	public void keyReleased(KeyEvent e) {
		
		/*
		if(e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
			
		}
		*/
				
	}

	public void keyTyped(KeyEvent e) {
		
		
	}

	public void mouseClicked(MouseEvent e) {
		
		
	}

	public void mouseEntered(MouseEvent arg0) {
		
		
	}

	public void mouseExited(MouseEvent arg0) {
	
		
	}
	
	public void mousePressed(MouseEvent e) {
		
		
	}

	public void mouseReleased(MouseEvent arg0) {
		
		
	}

}
