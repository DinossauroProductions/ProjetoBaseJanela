package com.dinossauroProductions.components;

import java.awt.image.BufferedImage;

import com.dinossauroProductions.entities.Entity;

public class AnimatedSprite extends Component{
	
	private int index = 0, maxIndex = 3;
	private int frames = 0, maxFrames = 60;
	private BufferedImage[] sprites;
	

	public AnimatedSprite(Entity _origin, BufferedImage[] _sprites, int frameSpeed) {
		
		this.origin = _origin;
		
		maxIndex = _sprites.length-1;
		maxFrames = frameSpeed;
		sprites = _sprites;
	}
	

	public void tick() {
		
		//runs the animation cycle
		
		frames++;
		if(frames > maxFrames) {
			frames = 0;
			index++;
			if(index > maxIndex) {
				index = 0;
			}
		}
		
		//updates the sprite of the origin based on the cycle
		
		for(int i = 0; i < origin.getComponents().size(); i++) {
			
			if(origin.getComponents().get(i) instanceof Sprite){
				
				((Sprite) origin.getComponents().get(i)).setSprite(sprites[index]);
				return;
			}
			System.out.println("Erro: A entidade "+origin+" não possui Sprite para ser alterada.");
		
		}
		

	}
	
	
	public void setMaxFrames(int maxFrames) {
		this.maxFrames = maxFrames;
	}

	
}
