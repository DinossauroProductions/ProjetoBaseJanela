package com.dinossauroProductions.components;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.dinossauroProductions.entities.Entity;

public class Sprite extends Component{
	
	private BufferedImage sprite;
	private int x, y;
	
	
	
	//TODO: sistema de camadas, com cada sprite tendo uma prioridade a ser renderizado
	
	public Sprite(BufferedImage _sprite, Entity _origin) {
		
		this.sprite = _sprite;
		this.x = (int)_origin.getX();
		this.y = (int)_origin.getY();
		this.origin = _origin;
	}
	
	public void render(Graphics g) {
		
		x = (int)origin.getX();
		y = (int)origin.getY();
		
		g.drawImage(sprite, x, y, null);
		
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}
	
	
}
