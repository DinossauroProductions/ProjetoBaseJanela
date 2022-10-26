package com.dinossauroProductions.entities;

import java.awt.image.BufferedImage;

import com.dinossauroProductions.main.Main;

public class CeilingTile extends Entity{
	
	//CeilingTiles need sprites to work properly, so remember to add them as soon as they are created

	public static BufferedImage CEILING = Main.getImageSector(Main.spritesheet1, 48, 16, 16, 16);
	
	public CeilingTile(double _x, double _y) {
		super(_x, _y);
		this.layer = super.CEILING;
	}

}
