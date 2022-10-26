package com.dinossauroProductions.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import com.dinossauroProductions.components.Component;

public class Entity implements Comparable<Entity>{
	
	protected double x, y;
	private ArrayList<Component> components;
	protected int layer = 0;
	
	public static final int FLOOR = 1, OBJECTS = 2, STATIC_ENTITIES = 3, MOVING_ENTITIES = 4, CEILING = 5;
	
	public Entity(double _x, double _y) {
		
		this.x = _x;
		this.y = _y;
		
		components = new ArrayList<Component>();
	}
	
	public Entity(ArrayList<Component> _components) {
		
		this.x = 0;
		this.y = 0;
		
		this.components = _components;
	}
	
	
	public Entity(ArrayList<Component> _components, double _x, double _y) {
		
		this.x = _x;
		this.y = _y;
		
		this.components = _components;
		
	}
	
	public void tick() {
		
		for(int i = 0; i < components.size(); i++) {
			
			components.get(i).tick();
			
		}
	}
	
	public void render(Graphics g) {
		
		for(int i = 0; i < components.size(); i++) {
			
			components.get(i).render(g);
			
		}
	}
	
	public void sortEntities(ArrayList<Entity> entities) {
		
		
		
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void addComponent(Component component){
		components.add(component);
	}
	
	public ArrayList<Component> getComponents(){
		return components;
	}

	public int getLayer() {
		return layer;
	}

	

	
	public int compareTo(Entity o) {
		
		if(this.layer == o.layer) {
			return 0;
		}
		else if(this.layer > o.layer) {
			return 1;
		}
		else if(this.layer < o.layer) {
			return -1;
		}
		return 0;
	}

}
