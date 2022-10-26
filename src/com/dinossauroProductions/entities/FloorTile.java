package com.dinossauroProductions.entities;

public class FloorTile extends Entity{
	
	//FloorTiles need sprites to work properly, so remember to add them to these as soon as they are created

	
	public FloorTile(double _x, double _y) {
		
		
		super(_x, _y);
		
		this.layer = Entity.FLOOR;
		
	}
	
	

}
