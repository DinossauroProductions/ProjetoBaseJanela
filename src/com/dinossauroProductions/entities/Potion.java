package com.dinossauroProductions.entities;

import java.awt.image.BufferedImage;

import com.dinossauroProductions.main.Main;

public class Potion extends Entity{
	
	public static BufferedImage POTION = Main.getImageSector(Main.spritesheet1, 32, 16, 16, 16);

	public Potion(double _x, double _y) {
		super(_x, _y);
		this.layer = Entity.STATIC_ENTITIES;
	}

}
