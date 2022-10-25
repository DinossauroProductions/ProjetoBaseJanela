package com.dinossauroProductions.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import com.dinossauroProductions.components.Component;

public class Entity {
	
	private double x, y;
	private ArrayList<Component> components;
	
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

}
