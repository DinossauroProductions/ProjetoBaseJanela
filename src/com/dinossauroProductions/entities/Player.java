package com.dinossauroProductions.entities;

import java.awt.image.BufferedImage;

import com.dinossauroProductions.main.Main;

public class Player extends Entity{
	
	
	public static BufferedImage[] WALKING = {
			Main.getImageSector(Main.spritesheet1, 80, 0, 16, 16),
			Main.getImageSector(Main.spritesheet1, 96, 0, 16, 16),
			Main.getImageSector(Main.spritesheet1, 112, 0, 16, 16),
			Main.getImageSector(Main.spritesheet1, 128, 0, 16, 16) };
	 
	
	//a player should have a sprite, a animatedsprite, a statemachine as components
	
	  
	public Player(double _x, double _y) {
		super(_x, _y);
		this.layer = Entity.MOVING_ENTITIES;
		
	}

}
